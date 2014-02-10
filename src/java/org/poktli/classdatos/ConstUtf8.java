package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstUtf8 implements ConstBase {

	String cadena = null;
	
	public ConstUtf8(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}
	
	public ConstUtf8(String str){
		cadena = new String(str);
	}
	
	@Override
	public int etiqueta() {
		return 1; //CONSTANT_Utf8
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			int longitud = flujo.readUnsignedShort();
			byte[] bytesCadena = new byte[longitud]; flujo.read(bytesCadena);
			cadena = new String(bytesCadena, "UTF-8");
		} catch(Exception exc){
			Log.error("Excepcion al leer ConstUtf8: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			if(cadena==null){
				flujo.writeShort(0);
			} else {
				byte[] bytesCadena = cadena.getBytes("UTF-8");
				flujo.writeShort(bytesCadena.length);
				flujo.write(bytesCadena);
			}
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstUtf8: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		return true;
	}
	
	public String valorCadena(){
		return cadena;
	}
	
	public void establecerValorCadena(String str){
		cadena = new String(str);
	}
	
	public String toString(){
		return "CONSTANT_Utf8::'" + cadena + "'";
	}

}
