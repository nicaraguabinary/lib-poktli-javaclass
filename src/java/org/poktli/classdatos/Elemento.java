package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import org.poktli.ArchivoClass;
import org.poktli.Log;

public class Elemento {

	private int _mascaraAcceso = 0;
	private int _indiceNombre = 0; 
	private int _indiceDescriptor = 0;
	private Atributo[] _atributos = null;
	
	public Elemento(){
		//
	}
	
	public Elemento(int iNombre, int iDesc, int iMascara, Atributo[] attrb, ConstBase[] bibConstantes){
		_mascaraAcceso = iMascara;
		_indiceNombre = iNombre;
		_indiceDescriptor = iDesc;
		_atributos = attrb;
		assert (iNombre>0 && iNombre<=bibConstantes.length);
		assert (iDesc>0 && iDesc<=bibConstantes.length);
		assert bibConstantes[iNombre-1] instanceof ConstUtf8;
		assert bibConstantes[iDesc-1] instanceof ConstUtf8;
	}
	
	public Atributo atributoSegunNombre(int indNombre){
		if(_atributos!=null){
			int i;
			for(i=0; i<_atributos.length; i++){
				if(_atributos[i].indiceNomAtributo()==indNombre) return _atributos[i];
			}
		}
		return null;
	}
	
	public void cargarDatosCampoDesdeFlujo(DataInputStream flujo, ConstBase[] bibConstantes, String espaciosAntesDeTexto) {
		try {
			_mascaraAcceso = flujo.readUnsignedShort();
			_indiceNombre = flujo.readUnsignedShort(); 
			_indiceDescriptor = flujo.readUnsignedShort();
			assert (_indiceNombre>0 && _indiceNombre<=bibConstantes.length); 
			assert (bibConstantes[_indiceNombre-1] instanceof ConstUtf8);
			assert (_indiceDescriptor>0 && _indiceDescriptor<=bibConstantes.length); 
			assert (bibConstantes[_indiceDescriptor-1] instanceof ConstUtf8);
			int conteoAtributos = flujo.readUnsignedShort(); Log.acumulativo(espaciosAntesDeTexto + "Campo, mascaraAcceso("+strMascaraAccesoCampo(_mascaraAcceso)+") nom -> '"+((ConstUtf8)bibConstantes[_indiceNombre-1]).valorCadena()+"' desc -> '"+((ConstUtf8)bibConstantes[_indiceDescriptor-1]).valorCadena()+"' conteoAtributos("+conteoAtributos+").");
	    	if(conteoAtributos>0){
	    		_atributos = new Atributo[conteoAtributos];
	    		int iAtrb;
	    		for(iAtrb=0; iAtrb<conteoAtributos; iAtrb++){
	    			int indNombreAtrrb = flujo.readUnsignedShort();
					int bytesDatosAtrrb = flujo.readInt();
					byte[] datosAttrb = null;
					if(bytesDatosAtrrb>0){
						datosAttrb = new byte[bytesDatosAtrrb];
						flujo.read(datosAttrb);
					}
					_atributos[iAtrb] = ArchivoClass.atributoSegunTipo(indNombreAtrrb, datosAttrb, bibConstantes, espaciosAntesDeTexto+"   ");
				}
	    	}
		} catch(Exception exc){
			Log.error("Excepcion al leer Elemento: " + exc);
			exc.printStackTrace();
		}
	}
	
	public void cargarDatosMetodoDesdeFlujo(DataInputStream flujo, ConstBase[] bibConstantes, String espaciosAntesDeTexto) {
		try {
			_mascaraAcceso = flujo.readUnsignedShort();
			_indiceNombre = flujo.readUnsignedShort();
			_indiceDescriptor = flujo.readUnsignedShort();
			assert (_indiceNombre>0 && _indiceNombre<=bibConstantes.length); 
			assert (bibConstantes[_indiceNombre-1] instanceof ConstUtf8);
			assert (_indiceDescriptor>0 && _indiceDescriptor<=bibConstantes.length); 
			assert (bibConstantes[_indiceDescriptor-1] instanceof ConstUtf8);
			int conteoAtributos = flujo.readUnsignedShort(); Log.acumulativo(espaciosAntesDeTexto + "Metodo, mascaraAcceso("+strMascaraAccesoMetodo(_mascaraAcceso)+") nom -> '"+((ConstUtf8)bibConstantes[_indiceNombre-1]).valorCadena()+"' desc -> '"+((ConstUtf8)bibConstantes[_indiceDescriptor-1]).valorCadena()+"' conteoAtributos("+conteoAtributos+").");
	    	if(conteoAtributos>0){
	    		_atributos = new Atributo[conteoAtributos];
	    		int iAtrb;
	    		for(iAtrb=0; iAtrb<conteoAtributos; iAtrb++){
	    			int indNombreAtrrb = flujo.readUnsignedShort();
					int bytesDatosAtrrb = flujo.readInt();
					byte[] datosAttrb = null;
					if(bytesDatosAtrrb>0){
						datosAttrb = new byte[bytesDatosAtrrb];
						flujo.read(datosAttrb);
					}
					_atributos[iAtrb] = ArchivoClass.atributoSegunTipo(indNombreAtrrb, datosAttrb, bibConstantes, espaciosAntesDeTexto+"   ");
				}
	    	}
		} catch(Exception exc){
			Log.error("Excepcion al leer Elemento: " + exc);
			exc.printStackTrace();
		}
	}

	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_mascaraAcceso);
			flujo.writeShort(_indiceNombre);
			flujo.writeShort(_indiceDescriptor);
			if(_atributos==null){
				flujo.writeShort(0);
			} else {
				flujo.writeShort(_atributos.length);
				int iAtrb; for(iAtrb=0; iAtrb<_atributos.length; iAtrb++) _atributos[iAtrb].guardarDatosEnFlujo(flujo);
			}
		} catch(Exception exc){
			Log.error("Excepcion al escribir Elemento: " + exc);
		}
	}
	
	public int mascaraAcceso(){
		return _mascaraAcceso;
	}
	
	public int indiceNombre(){
		return _indiceNombre;
	}
	
	public int indiceDescriptor(){
		return _indiceDescriptor;
	}
	
	public static String strMascaraAccesoCampo(int mascaraAcceso){
		String str = new String();
		if((mascaraAcceso & 0x0001)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PUBLIC"; }
		if((mascaraAcceso & 0x0002)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PRIVATE"; }
		if((mascaraAcceso & 0x0004)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PROTECTED"; }
		if((mascaraAcceso & 0x0008)!=0){ if(str.length()!=0) str += ", "; str += "ACC_STATIC"; }
		if((mascaraAcceso & 0x0010)!=0){ if(str.length()!=0) str += ", "; str += "ACC_FINAL"; }
		if((mascaraAcceso & 0x0040)!=0){ if(str.length()!=0) str += ", "; str += "ACC_VOLATILE"; }
		if((mascaraAcceso & 0x0080)!=0){ if(str.length()!=0) str += ", "; str += "ACC_TRANSIENT"; }
		//assert mascaraAcceso!=0; //Cuando los permisos no son explicitos, es posible que mascara sea cero.
		//assert str.length()!=0;
		return str;
	} 
	
	public static String strMascaraAccesoMetodo(int mascaraAcceso){
		String str = new String();
		if((mascaraAcceso & 0x0001)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PUBLIC"; }
		if((mascaraAcceso & 0x0002)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PRIVATE"; }
		if((mascaraAcceso & 0x0004)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PROTECTED"; }
		if((mascaraAcceso & 0x0008)!=0){ if(str.length()!=0) str += ", "; str += "ACC_STATIC"; }
		if((mascaraAcceso & 0x0010)!=0){ if(str.length()!=0) str += ", "; str += "ACC_FINAL"; }
		if((mascaraAcceso & 0x0020)!=0){ if(str.length()!=0) str += ", "; str += "ACC_SYNCHRONIZED"; }
		if((mascaraAcceso & 0x0100)!=0){ if(str.length()!=0) str += ", "; str += "ACC_NATIVE"; }
		if((mascaraAcceso & 0x0400)!=0){ if(str.length()!=0) str += ", "; str += "ACC_ABSTRACT"; }
		if((mascaraAcceso & 0x0800)!=0){ if(str.length()!=0) str += ", "; str += "ACC_STRICT"; }
		//assert mascaraAcceso!=0; //Cuando los permisos no son explicitos, es posible que mascara sea cero.
		//assert str.length()!=0;
		return str;
	} 
}
