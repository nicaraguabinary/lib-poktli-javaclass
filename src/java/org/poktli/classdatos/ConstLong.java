package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstLong implements ConstBase {

	int valorAlto = 0;
	int valorBajo = 0;
	
	public ConstLong(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}
	
	@Override
	public int etiqueta() {
		return 5; //CONSTANT_Long
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			valorAlto = flujo.readInt();
			valorBajo = flujo.readInt();
		} catch(Exception exc){
			Log.error("Excepcion al leer ConstLong: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeInt(valorAlto);
			flujo.writeInt(valorBajo);
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstLong: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		return true;
	}
	
	public String toString(){
		return "CONSTANT_Long::" + valorAlto + "::" + valorBajo;
	}

}
