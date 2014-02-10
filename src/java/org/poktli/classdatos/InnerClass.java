package org.poktli.classdatos;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;

import org.poktli.Log;

public class InnerClass {

	private int _indInnerClassInfo;
	private int _indOuterClassInfo;
	private int _indInnerName;
	private int _mascInnerClassAcess;
	
	public InnerClass(ByteBuffer wrapped, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		_indInnerClassInfo = wrapped.getShort(); //u2
		_indOuterClassInfo = wrapped.getShort(); //u2
		_indInnerName = wrapped.getShort(); //u2
		_mascInnerClassAcess = wrapped.getShort(); //u2
		String nomInnerClass = "";
		String nomOuterClass = "[not a member]";
		String nomInner = "[anonymous]";
		assert (_indInnerClassInfo>=0 && _indInnerClassInfo<=bibConstantes.length); //cero es permitido
		assert (_indOuterClassInfo>=0 && _indOuterClassInfo<=bibConstantes.length); //cero es permitido
		assert (_indInnerName>=0 && _indInnerName<=bibConstantes.length); //cero es permitido
		if(_indInnerClassInfo>0){
			assert (bibConstantes[_indInnerClassInfo-1] instanceof ConstClass);
			nomInnerClass = ((ConstUtf8)bibConstantes[((ConstClass)bibConstantes[_indInnerClassInfo-1]).indiceConstNombre() -1]).valorCadena();
		}
		if(_indOuterClassInfo>0){
			assert (bibConstantes[_indOuterClassInfo-1] instanceof ConstClass);
			nomOuterClass = ((ConstUtf8)bibConstantes[((ConstClass)bibConstantes[_indOuterClassInfo-1]).indiceConstNombre() -1]).valorCadena();
		}
		if(_indInnerName>0){
			assert (bibConstantes[_indInnerName-1] instanceof ConstUtf8);
			nomInner = ((ConstUtf8)bibConstantes[_indInnerName -1]).valorCadena();
		}
		Log.acumulativo(espaciosAntesDeTexto + "   InnerClass innerClass("+nomInnerClass+") outerClass("+nomOuterClass+") inner("+nomInner+") acceso("+strMascaraAccesoClaseInterna(_mascInnerClassAcess)+").");
	}
	
	public int cantBytesDatos(){
		return 8;
	}
	
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_indInnerClassInfo);
			flujo.writeShort(_indOuterClassInfo);
			flujo.writeShort(_indInnerName);
			flujo.writeShort(_mascInnerClassAcess);
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}
	
	public int indInnerClassInfo(){
		return _indInnerClassInfo;
	}
	
	public int indOuterClassInfo(){
		return _indOuterClassInfo;
	}
	
	public int indInnerName(){
		return _indInnerName;
	}
	
	public int mascInnerClassAcess(){
		return _mascInnerClassAcess;
	}
	
	public static String strMascaraAccesoClaseInterna(int mascaraAcceso){
		String str = new String();
		if((mascaraAcceso & 0x0001)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PUBLIC"; }
		if((mascaraAcceso & 0x0002)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PRIVATE"; }
		if((mascaraAcceso & 0x0004)!=0){ if(str.length()!=0) str += ", "; str += "ACC_PROTECTED"; }
		if((mascaraAcceso & 0x0008)!=0){ if(str.length()!=0) str += ", "; str += "ACC_STATIC"; }
		if((mascaraAcceso & 0x00010)!=0){ if(str.length()!=0) str += ", "; str += "ACC_FINAL"; }
		if((mascaraAcceso & 0x0200)!=0){ if(str.length()!=0) str += ", "; str += "ACC_INTERFACE"; }
		if((mascaraAcceso & 0x0400)!=0){ if(str.length()!=0) str += ", "; str += "ACC_ABSTRACT"; }
		//assert mascaraAcceso!=0; //Cuando los permisos no son explicitos, es posible que mascara sea cero.
		//assert str.length()!=0;
		return str;
	}
	
}
