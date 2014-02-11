package org.poktli.classdatos;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;

import org.poktli.ArchivoClass;
import org.poktli.Log;

public class AtributoCode implements Atributo {

	private int _indiceNomAtributo 	= 0;
	private int _maxPila = 0;
	private int _maxLocales = 0;
	private byte[] codigo = null;
	private CodeException[] tablaExcepciones = null;
	private Atributo[] atributos = null;
	
	public AtributoCode(ArchivoClass objClase){
		_indiceNomAtributo = objClase.asegurarRegistroUTf8("Code") + 1;
		_maxPila = 1;
		_maxLocales = 1;
		//Codigo
		codigo = new byte[1];
		codigo[0] = (byte)OpCode.OPCODE_return;
		//Escepciones
		tablaExcepciones = null;
		//Atributos
		atributos = new Atributo[2];
		//Atributo LineNumberTable
		atributos[0] = new AtributoLineasNum(objClase.asegurarRegistroUTf8("LineNumberTable") + 1);
		//Atributo LocalVariableTable
		AtributoLocalVars variablesLocales = new AtributoLocalVars(objClase.asegurarRegistroUTf8("LocalVariableTable") + 1);
		variablesLocales.registrarLocalVar(0, codigo.length, objClase.asegurarRegistroUTf8("this") + 1, objClase.asegurarRegistroUTf8("L" + objClase.nombreClaseThis() + ";") + 1, 0);
		atributos[1] = variablesLocales;
	}
		
	public AtributoCode(int iNom, byte[] datos, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		_indiceNomAtributo = iNom;
		ByteBuffer wrapped = ByteBuffer.wrap(datos);
		//Propiedades de pila
		_maxPila 		= wrapped.getShort();
		_maxLocales 	= wrapped.getShort();
		Log.acumulativo(espaciosAntesDeTexto + "   PilaMax("+_maxPila+") LocalsMax("+_maxLocales+").");
		//Codigo
		int bytesCodigo = wrapped.getInt();
		if(bytesCodigo>0){
			codigo		= new byte[bytesCodigo];
			wrapped.get(codigo);
		}
		assert wrapped.arrayOffset() <= datos.length;
		//Tabla de excepciones
		int longTablaExcepciones = wrapped.getShort(); Log.acumulativo(espaciosAntesDeTexto + "   Tabla excepciones: " + longTablaExcepciones);
		if(longTablaExcepciones>0){
			tablaExcepciones = new CodeException[longTablaExcepciones];
			int i;
			for(i=0; i<longTablaExcepciones; i++){
    	      	tablaExcepciones[i] = new CodeException(wrapped, bibConstantes, espaciosAntesDeTexto);
    	      	assert wrapped.arrayOffset() <= datos.length;
			}
		}
		assert wrapped.arrayOffset() <= datos.length;
		//Atributos
		int conteoAtributos = wrapped.getShort(); Log.acumulativo(espaciosAntesDeTexto + "   Cant atributos: " + conteoAtributos);
		if(conteoAtributos>0){
			atributos = new Atributo[conteoAtributos];
			int i;
			for(i=0; i<conteoAtributos; i++){
				int indNombreAtrrb = wrapped.getShort();
				int bytesDatosAtrrb = wrapped.getInt();
				byte[] datosAttrb = null;
				if(bytesDatosAtrrb>0){
					datosAttrb = new byte[bytesDatosAtrrb];
					wrapped.get(datosAttrb);
				}
				assert wrapped.arrayOffset() <= datos.length;
				atributos[i] = ArchivoClass.atributoSegunTipo(indNombreAtrrb, datosAttrb, bibConstantes, espaciosAntesDeTexto + "   ");
			}
		}
		assert wrapped.arrayOffset() <= datos.length;
		//Imprimir codigo fuente
		Log.acumulativo(espaciosAntesDeTexto + "   Bytes Code " + bytesCodigo);
		//
		analizarOpCodes(bibConstantes, espaciosAntesDeTexto+"   ");
	}
	
	public void analizarOpCodes(ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		int bytesCodigo = codigo.length;
		int iByte = 0;
		while(iByte<bytesCodigo){
			OpCode datosCode = OpCode.dameOpCode(codigo, iByte);
			int opCode = datosCode.valorOpCode();
			int bytesPosteriores = datosCode.bytesPosteriores();
			int datosBytesPosteriores = 0;
			boolean datoEsMethodRef = false;
			boolean datoEsMethodInterfaceRef = false;
			String descParametro = "";
			if(bytesPosteriores==1){
				datosBytesPosteriores = (codigo[iByte+1] & 0xFF); assert datosBytesPosteriores>=0;
			} else if(bytesPosteriores==2){
				datosBytesPosteriores = ((codigo[iByte+1] & 0xFF) << 8) | (codigo[iByte+2] & 0xFF); //assert datosBytesPosteriores>=0;
				switch(opCode){
					case OpCode.OPCODE_invokespecial:
					case OpCode.OPCODE_invokestatic:
					case OpCode.OPCODE_invokevirtual:
						datoEsMethodRef = true; break;
					default: break;
				}
			} else if(bytesPosteriores==4){
				datosBytesPosteriores = ((codigo[iByte+1] & 0xFF) << 8) | (codigo[iByte+2] & 0xFF); //assert datosBytesPosteriores>=0;
				switch(opCode){
					case OpCode.OPCODE_invokeinterface:
						datoEsMethodInterfaceRef = true; break;
					default: break;
				}
			}
			//Averiguar el nombre del metodo invocado
			if(datoEsMethodRef){
				assert (datosBytesPosteriores>0 && datosBytesPosteriores<=bibConstantes.length);
				assert (bibConstantes[datosBytesPosteriores-1] instanceof ConstMethodRef);
				if(datosBytesPosteriores>0 && datosBytesPosteriores<=bibConstantes.length){
					if(bibConstantes[datosBytesPosteriores-1] instanceof ConstMethodRef){
						ConstMethodRef methodRef = (ConstMethodRef) bibConstantes[datosBytesPosteriores-1];
						assert (methodRef.indiceNomYTipo()>0 && methodRef.indiceNomYTipo()<=bibConstantes.length);
						assert bibConstantes[methodRef.indiceNomYTipo()-1] instanceof ConstNameAndType;
						ConstNameAndType nomTipo = (ConstNameAndType)bibConstantes[methodRef.indiceNomYTipo()-1];
						assert (nomTipo.indiceConstNombre()>0 && nomTipo.indiceConstNombre()<=bibConstantes.length);
						assert bibConstantes[nomTipo.indiceConstNombre()-1] instanceof ConstUtf8;
						ConstUtf8 nomMetodo = (ConstUtf8)bibConstantes[nomTipo.indiceConstNombre()-1];
						descParametro = " " + nomMetodo.valorCadena();
					} else descParametro = " ERROR_INDICE_NO_MethodRef";
				} else descParametro = " ERROR_INDICE_FUERA_LIMITES";
			}
			//Averiguar el nombre del metodo virtual invocado
			if(datoEsMethodInterfaceRef){
				assert (datosBytesPosteriores>0 && datosBytesPosteriores<=bibConstantes.length);
				assert (bibConstantes[datosBytesPosteriores-1] instanceof ConstInterfaceMethodRef);
				if(datosBytesPosteriores>0 && datosBytesPosteriores<=bibConstantes.length){
					if(bibConstantes[datosBytesPosteriores-1] instanceof ConstInterfaceMethodRef){
						ConstInterfaceMethodRef methodRef = (ConstInterfaceMethodRef) bibConstantes[datosBytesPosteriores-1];
						assert (methodRef.indiceNomYTipo()>0 && methodRef.indiceNomYTipo()<=bibConstantes.length);
						assert bibConstantes[methodRef.indiceNomYTipo()-1] instanceof ConstNameAndType;
						ConstNameAndType nomTipo = (ConstNameAndType)bibConstantes[methodRef.indiceNomYTipo()-1];
						assert (nomTipo.indiceConstNombre()>0 && nomTipo.indiceConstNombre()<=bibConstantes.length);
						assert bibConstantes[nomTipo.indiceConstNombre()-1] instanceof ConstUtf8;
						ConstUtf8 nomMetodo = (ConstUtf8)bibConstantes[nomTipo.indiceConstNombre()-1];
						descParametro = " " + nomMetodo.valorCadena();
					} else descParametro = " ERROR_INDICE_NO_InterfaceMethodRef";
				} else descParametro = " ERROR_INDICE_FUERA_LIMITES";
			}
			Log.acumulativo(espaciosAntesDeTexto + "OpCode "+iByte+", '"+datosCode.nombreOpCode()+"'" + (bytesPosteriores!=0?" + " + bytesPosteriores + " (" + datosBytesPosteriores + descParametro+ ")" : "") +".");
			//assert datosCode!=null;
			//assert datosCode.bytesPosteriores()>=0;
			iByte += 1 + bytesPosteriores;
			assert iByte<=bytesCodigo;
		}
	}

	
	public Atributo atributoSegunNombre(int indNombre){
		if(atributos!=null){
			int i;
			for(i=0; i<atributos.length; i++){
				if(atributos[i].indiceNomAtributo()==indNombre) return atributos[i];
			}
		}
		return null;
	}
	
	public int indiceSecuenciaDeCodigo(byte[] codigoBuscar){
		if(codigoBuscar==null || codigo==null) return -1; 
		if(codigoBuscar.length==0 || codigo.length==0) return -1;
		int i, cantConcuerdan = 0;
		for(i=0; i<codigo.length; i++){
			if(codigoBuscar[cantConcuerdan]==codigo[i]){
				cantConcuerdan++; if(cantConcuerdan==codigoBuscar.length) return (i - (cantConcuerdan - 1));
			} else {
				cantConcuerdan = 0;
			}
		}
		return -1;
	}
	
	@Override
	public int indiceNomAtributo(){
		return _indiceNomAtributo;
	}
	
	@Override
	public int cantBytesDatos(){
		int i, cantBytes = 4; //4 info de pila,
		//Codigo
		cantBytes += 4; //4 de tamano
		if(codigo!=null){
			cantBytes += codigo.length;
		}
		//Excepciones
		cantBytes += 2; //2 de tamano
		if(tablaExcepciones!=null){
			for(i=0; i<tablaExcepciones.length; i++) cantBytes += tablaExcepciones[i].cantBytesDatos();
		}
		//Atributos
		cantBytes += 2; //2 de tamano
		if(atributos!=null){
			for(i=0; i<atributos.length; i++) cantBytes += 2 + 4 + atributos[i].cantBytesDatos(); //2 de tipoAttrib y 4 de bytesDatos
		}
		//
		return cantBytes; 
	}
	
	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			int i;
			flujo.writeShort(_indiceNomAtributo);
			flujo.writeInt(cantBytesDatos());
			//
			flujo.writeShort(_maxPila);
			flujo.writeShort(_maxLocales);
			//
			if(codigo==null){
				flujo.writeInt(0);
			} else {
				flujo.writeInt(codigo.length);
				flujo.write(codigo);
			}
			//
			if(tablaExcepciones==null){
				flujo.writeShort(0);
			} else {
				flujo.writeShort(tablaExcepciones.length);
				for(i=0; i<tablaExcepciones.length; i++) tablaExcepciones[i].guardarDatosEnFlujo(flujo);
			}
			//
			if(atributos==null){
				flujo.writeShort(0);
			} else {
				flujo.writeShort(atributos.length);
				for(i=0; i<atributos.length; i++) atributos[i].guardarDatosEnFlujo(flujo);
			}
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}
	
}
