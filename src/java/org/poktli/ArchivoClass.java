package org.poktli;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.poktli.classdatos.*;

public class ArchivoClass {
	//Identificadores de biblioteca de constantes
	//segun especificaciones de archivo CLASS.
	public static final int CONSTANT_Class = 7;
	public static final int CONSTANT_Fieldref = 9;
	public static final int CONSTANT_Methodref = 10;
	public static final int CONSTANT_InterfaceMethodref = 11;
	public static final int CONSTANT_String = 8;
	public static final int CONSTANT_Integer = 3;
	public static final int CONSTANT_Float = 4;
	public static final int CONSTANT_Long = 5;
	public static final int CONSTANT_Double = 6;
	public static final int CONSTANT_NameAndType = 12;
	public static final int CONSTANT_Utf8 = 1; 
	 
	//Contenido de archivo
	//segun especificaciones de archivo CLASS.
	int encabezadoMagic		= 0;		//Debe ser 0xCAFEBABE segun especificaciones
	int verionMayor			= 0;
	int versionMenor		= 0;
	ConstBase[] bibConstantes = null;
	int mascaraAccesoClase	= 0;
	int indiceClaseThis		= 0; 		//Indice en las constantes
	int indiceClaseSuper	= 0; 		//Indice en las constantes
	int[] indicesInterfaces	= null;		//Interfaces que implementa esta clase, indice en las constantes
	Elemento[] campos = null;	//Campos de la clase
	Elemento[] metodos = null;	//Metodos de la clase
	Atributo[] atributos = null;
	
	public ArchivoClass(){
		//Nada
	}
	
	public ConstBase[] bibConstantes(){
		return bibConstantes;
	}
	
	public String nombreClaseThis(){
		assert (indiceClaseThis>0 && indiceClaseThis<=bibConstantes.length);
		assert bibConstantes[indiceClaseThis-1] instanceof ConstClass;
		ConstBase constanteClass = bibConstantes[indiceClaseThis-1];
		if(constanteClass instanceof ConstClass){
			int indiceUtf8 = ((ConstClass)constanteClass).indiceConstNombre();
			assert (indiceUtf8>0 && indiceUtf8<=bibConstantes.length);
			assert bibConstantes[indiceUtf8-1] instanceof ConstUtf8;
			ConstBase constanteNombre = bibConstantes[indiceUtf8-1];
			if(constanteNombre instanceof ConstUtf8){
				return ((ConstUtf8)constanteNombre).valorCadena();
			}
		}
		return null;
	}
	
	public String nombreClaseSuper(){
		assert (indiceClaseSuper>0 && indiceClaseSuper<=bibConstantes.length);
		assert bibConstantes[indiceClaseSuper-1] instanceof ConstClass;
		ConstBase constanteClass = bibConstantes[indiceClaseSuper-1];
		if(constanteClass instanceof ConstClass){
			int indiceUtf8 = ((ConstClass)constanteClass).indiceConstNombre();
			assert (indiceUtf8>0 && indiceUtf8<=bibConstantes.length);
			assert bibConstantes[indiceUtf8-1] instanceof ConstUtf8;
			ConstBase constanteNombre = bibConstantes[indiceUtf8-1];
			if(constanteNombre instanceof ConstUtf8){
				return ((ConstUtf8)constanteNombre).valorCadena();
			}
		}
		return null;
	}

	public boolean cargarClaseDesdeArchivo(String rutaArchivoClass){
		boolean exito = false;
		//Abrir archivo
		FileInputStream lectorArch = null; 
		DataInputStream flujo = null;
		try {
			lectorArch = new FileInputStream(rutaArchivoClass);
			flujo = new DataInputStream(lectorArch);
			//Interpretar contenido
			exito = cargarClaseDesdeFlujo(flujo);
		} catch(Exception exc) {
			Log.error("Archivo no existe: '"+rutaArchivoClass+"'.");
		}
		//Cerrar archivo
		if(flujo!=null){ try { flujo.close(); } catch(Exception exc) {} }
		if(lectorArch!=null){ try { lectorArch.close(); } catch(Exception exc) {} }
		return exito;
	}
	
	public boolean guardarClaseEnArchivo(String rutaArchivoClass){
		boolean exito = false;
		//Abrir archivo
		FileOutputStream escritorArch = null; 
		DataOutputStream flujo = null;
		try {
			escritorArch = new FileOutputStream(rutaArchivoClass);
			flujo = new DataOutputStream(escritorArch);
			//Interpretar contenido
			exito = guardarClaseEnFlujo(flujo);
		} catch(Exception exc) {
			Log.error("Archivo no existe: '"+rutaArchivoClass+"'.");
		}
		//Cerrar archivo
		if(flujo!=null){ try { flujo.close(); } catch(Exception exc) {} }
		if(escritorArch!=null){ try { escritorArch.close(); } catch(Exception exc) {} }
		return exito;
	}
	
	public boolean cargarClaseDesdeFlujo(DataInputStream lector){
		boolean exito = false; 
		if(lector!=null){
			try {
				encabezadoMagic = lector.readInt();
				if(encabezadoMagic!=0xCAFEBABE){
					Log.error("Archivo no es java class.");
				} else {
					//Leer version
					versionMenor  	= lector.readUnsignedShort();
					verionMayor  	= lector.readUnsignedShort();
					Log.acumulativo("Version de java class: '"+verionMayor+"."+versionMenor+"'.");
					int poolCount  	= lector.readUnsignedShort();
					//Leer piscina de constantes
					boolean piscinaConstanteEsCorrecta = false;
					Log.acumulativo("PoolCount: '"+poolCount+"'.");
					if(poolCount>0){
						bibConstantes		= new ConstBase[poolCount - 1];
						boolean errorLeyendoConstantes = false;
						int iPool;
						for(iPool=1; iPool<poolCount; iPool++){ //poolCount inicia en 1 (cero es un valor no valido)
							int tipoEtiqueta		= lector.readUnsignedByte();
							ConstBase constante		= constanteSegunTipo(tipoEtiqueta, lector);
							if(constante==null){
								errorLeyendoConstantes = true;
								break;
							}
							bibConstantes[iPool-1] 	= constante;
							Log.acumulativo("Constante #"+(iPool)+": "+bibConstantes[iPool-1]+".");
							//------------------------------------------
							//--- El documento de especificaciones de archivo CLASS
							//--- establece que despues de una entrada CONST_long y CONST_double
							//--- debe considerarse la siguiente entrada como existente pero no-usable.
							//--- Es decir, es una casilla fantasma que ocupa un indice en la tabla,
							//--- pero no ocupe espacio en el archivo CLASS.
							//--- Nota: en el documento oficial colocan esta nota debajo de la explicacion:
							//--- "In retrospect, making 8-byte constants take two constant pool entries was a poor choice."
							//------------------------------------------
							if((constante instanceof ConstLong) || (constante instanceof ConstDouble)){
								iPool++;
								bibConstantes[iPool-1] = new ConstNoUsable();
								Log.acumulativo("Constante #"+(iPool)+": "+bibConstantes[iPool-1]+".");
							}
						}
						//Verificar piscina de constantes
						if(!errorLeyendoConstantes){
							piscinaConstanteEsCorrecta = true;
							for(iPool=0; iPool<bibConstantes.length; iPool++){
								if(!bibConstantes[iPool].verificarValores(bibConstantes)){
									assert false;
									piscinaConstanteEsCorrecta = false;
									Log.error("Piscina de constantes no es valida en archivo CLASS.");
									break;
								}
							}
						}
					}
					if(piscinaConstanteEsCorrecta){
						mascaraAccesoClase = lector.readUnsignedShort(); Log.acumulativo("MascaraAccesoClase("+strMascaraAccesoClase(mascaraAccesoClase)+").");
						indiceClaseThis = lector.readUnsignedShort(); Log.acumulativo("IndiceClaseThis("+indiceClaseThis+").");
						indiceClaseSuper = lector.readUnsignedShort(); Log.acumulativo("IndiceClaseSuper("+indiceClaseSuper+").");
						int conteoInterfaces = lector.readUnsignedShort(); Log.acumulativo("ConteoInterfaces("+conteoInterfaces+").");
						if(conteoInterfaces>0){
							indicesInterfaces = new int[conteoInterfaces];
							int iInterf;
							for(iInterf=0; iInterf<conteoInterfaces; iInterf++){
								indicesInterfaces[iInterf] = lector.readUnsignedShort();
							}
						}
						//Campos
						int conteoCampos = lector.readUnsignedShort(); Log.acumulativo("ConteoCampos("+conteoCampos+").");
						if(conteoCampos>0){
							campos = new Elemento[conteoCampos];
							int iCmp;
							for(iCmp=0; iCmp<conteoCampos; iCmp++){
								campos[iCmp] = new Elemento();
								campos[iCmp].cargarDatosCampoDesdeFlujo(lector, bibConstantes, "");
								//Log.informarivo("Campo #"+(iCmp+1)+" mascaraAcceso("+strMascaraAccesoCampo(mascaraAccesoC)+") indiceNombre("+indiceNombre+") indiceDescriptor("+indiceDescriptor+") conteoAtributos("+conteoAtributos+").");
							}
						}
						//Metodos
						int conteoMetodos = lector.readUnsignedShort(); Log.acumulativo("ConteoMetodos("+conteoMetodos+").");
						if(conteoMetodos>0){
							metodos = new Elemento[conteoMetodos];
							int iMtd;
							for(iMtd=0; iMtd<conteoMetodos; iMtd++){
								metodos[iMtd] = new Elemento();
								metodos[iMtd].cargarDatosMetodoDesdeFlujo(lector, bibConstantes, "");
								//Log.informarivo("Metodo #"+(iMtd+1)+" mascaraAcceso("+Elemento.strMascaraAccesoMetodo(mascaraAccesoC)+") indiceNombre("+indiceNombre+") indiceDescriptor("+indiceDescriptor+") conteoAtributos("+conteoAtributos+").");
							}
						}
						//Atributos
						int conteoAtributos = lector.readUnsignedShort(); Log.acumulativo("ConteoAtributos("+conteoAtributos+").");
						if(conteoAtributos>0){
							atributos = new Atributo[conteoAtributos];
							int iAtrb;
							for(iAtrb=0; iAtrb<conteoAtributos; iAtrb++){
								int indiceNomAtributo = lector.readUnsignedShort();
								byte[] datosAtributo = null;
								int bytesDatosAtributo = lector.readInt();
								if(bytesDatosAtributo>0){
									datosAtributo = new byte[bytesDatosAtributo];
									lector.read(datosAtributo);
								}
								atributos[iAtrb] = ArchivoClass.atributoSegunTipo(indiceNomAtributo, datosAtributo, bibConstantes, "   "); assert atributos[iAtrb]!=null;
							}
						}
						//
						exito = true;
					}
				}
			} catch(Exception exc){
				Log.acumulativo("Excepcion, leyendo archivo class: " + exc);
			}
		}
		return exito;
	}
	
	public boolean guardarClaseEnFlujo(DataOutputStream lector){
		boolean exito = false; 
		try {
			lector.writeInt(encabezadoMagic);
			lector.writeShort(versionMenor);
			lector.writeShort(verionMayor);
			lector.writeShort(bibConstantes.length+1); //poolCount inicia en 1 (cero es un valor no valido)
			int iPool;
			for(iPool=0; iPool<bibConstantes.length; iPool++){
				ConstBase constante	= bibConstantes[iPool];
				if(!(constante instanceof ConstNoUsable)){
					//------------------------------------------
					//--- El documento de especificaciones de archivo CLASS
					//--- establece que despues de una entrada CONST_long y CONST_double
					//--- debe considerarse la siguiente entrada como existente pero no-usable.
					//--- Es decir, es una casilla fantasma que ocupa un indice en la tabla,
					//--- pero no ocupe espacio en el archivo CLASS.
					//--- Nota: en el documento oficial colocan esta nota debajo de la explicacion:
					//--- "In retrospect, making 8-byte constants take two constant pool entries was a poor choice."
					//------------------------------------------
					lector.writeByte(constante.etiqueta());				
					constante.guardarDatosEnFlujo(lector);
				}
			}
			lector.writeShort(mascaraAccesoClase);
			lector.writeShort(indiceClaseThis);
			lector.writeShort(indiceClaseSuper);
			//Interfaces
			if(indicesInterfaces==null){
				lector.writeShort(0);
			} else {
				lector.writeShort(indicesInterfaces.length);
				int iInterf; for(iInterf=0; iInterf<indicesInterfaces.length; iInterf++) lector.writeShort(indicesInterfaces[iInterf]);
			}
			//Campos
			if(campos==null){
				lector.writeShort(0);
			} else {
				lector.writeShort(campos.length);
				int iCmp; for(iCmp=0; iCmp<campos.length; iCmp++) campos[iCmp].guardarDatosEnFlujo(lector);
			}
			//Metodos
			if(metodos==null){
				lector.writeShort(0);
			} else {
				lector.writeShort(metodos.length);
				int iMtd; for(iMtd=0; iMtd<metodos.length; iMtd++) metodos[iMtd].guardarDatosEnFlujo(lector);
			}
			//Atributos
			if(atributos==null){
				lector.writeShort(0);
			} else {
				lector.writeShort(atributos.length);
				int iAtrb; for(iAtrb=0; iAtrb<atributos.length; iAtrb++) atributos[iAtrb].guardarDatosEnFlujo(lector);
			}
			//
			exito = true;
		} catch(Exception exc){
			Log.acumulativo("Excepcion, escribiendo archivo class: " + exc);
			exc.printStackTrace();
		}
		return exito;
	}
	
	public int incrementarBibConstantes(ConstBase nuevaConstante){
		ConstBase[] bibConstantesN = new ConstBase[bibConstantes.length + 1];
		int i; for(i=0; i<bibConstantes.length; i++) bibConstantesN[i] = bibConstantes[i];
		bibConstantesN[i] = nuevaConstante;
		bibConstantes = bibConstantesN;
		return bibConstantes.length - 1;
	}
	
	public int asegurarRegistroUTf8(String cadena){
		//Buscar el indice de la cadena existente
		int iConst;
		for(iConst=0; iConst<bibConstantes.length; iConst++){
			if(bibConstantes[iConst] instanceof ConstUtf8){
				if(((ConstUtf8)bibConstantes[iConst]).valorCadena().equals(cadena)){
					return iConst;
				}
			}
		}
		//Registrar nueva cadena (no existia)
		return incrementarBibConstantes(new ConstUtf8(cadena));
	}

	public static ConstBase constanteSegunTipo(int tipoEtiqueta, DataInputStream lector){
		ConstBase obj = null;
		switch(tipoEtiqueta){
			case CONSTANT_Class: obj = new ConstClass(lector); break;
			case CONSTANT_Fieldref: obj = new ConstFieldRef(lector); break;
			case CONSTANT_Methodref: obj = new ConstMethodRef(lector); break;
			case CONSTANT_InterfaceMethodref: obj = new ConstInterfaceMethodRef(lector); break;
			case CONSTANT_String: obj = new ConstString(lector); break;
			case CONSTANT_Integer: obj = new ConstInteger(lector); break;
			case CONSTANT_Float: obj = new ConstFloat(lector); break;
			case CONSTANT_Long: obj = new ConstLong(lector); break;
			case CONSTANT_Double: obj = new ConstDouble(lector); break;
			case CONSTANT_NameAndType: obj = new ConstNameAndType(lector); break;
			case CONSTANT_Utf8: obj = new ConstUtf8(lector); break;
			default:
				Log.error("Etiqueta de constante no reconocible("+tipoEtiqueta+").");
				break;
		}
		assert obj!=null;
		assert obj.etiqueta()==tipoEtiqueta;
		return obj;
	}
	
	public static Atributo atributoSegunTipo(int indiceNomAtributo, byte[] datosAtributo, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		assert (indiceNomAtributo>0 && indiceNomAtributo<=bibConstantes.length); 
		assert (bibConstantes[indiceNomAtributo-1] instanceof ConstUtf8);
		//
		String nombreAtributo = ((ConstUtf8)bibConstantes[indiceNomAtributo-1]).valorCadena();
		if(datosAtributo==null) Log.acumulativo(espaciosAntesDeTexto + "Atributo: '"+nombreAtributo+"' (0 bytes).");
		else Log.acumulativo(espaciosAntesDeTexto + "Atributo: '"+nombreAtributo+"' ("+datosAtributo.length+" bytes).");
		//
		if(nombreAtributo.equals("Code")) return new AtributoCode(indiceNomAtributo, datosAtributo, bibConstantes, espaciosAntesDeTexto);
		else if(nombreAtributo.equals("LineNumberTable")) return new AtributoLineasNum(indiceNomAtributo, datosAtributo, bibConstantes, espaciosAntesDeTexto);
		else if(nombreAtributo.equals("LocalVariableTable")) return new AtributoLocalVars(indiceNomAtributo, datosAtributo, bibConstantes, espaciosAntesDeTexto);
		else if(nombreAtributo.equals("InnerClasses")) return new AtributoInnerClasses(indiceNomAtributo, datosAtributo, bibConstantes, espaciosAntesDeTexto);
		else return new AtributoGenerico(indiceNomAtributo, datosAtributo, bibConstantes);
	}
	
	public static String strMascaraAccesoClase(int mascaraAcceso){
		String str = new String();
		if((mascaraAcceso & 0x0001)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PUBLIC"; }
		if((mascaraAcceso & 0x0010)!=0){ if(str.length()!=0) str += ", "; str += "ACC_FINAL"; }
		if((mascaraAcceso & 0x0020)!=0){ if(str.length()!=0) str += ", "; str += "ACC_SUPER"; }
		if((mascaraAcceso & 0x0200)!=0){ if(str.length()!=0) str += ", "; str += "ACC_INTERFACE"; }
		if((mascaraAcceso & 0x0400)!=0){ if(str.length()!=0) str += ", "; str += "ACC_ABSTRACT"; }
		//assert mascaraAcceso!=0; //Cuando los permisos no son explicitos, es posible que mascara sea cero.
		//assert str.length()!=0;
		return str;
	}
}
