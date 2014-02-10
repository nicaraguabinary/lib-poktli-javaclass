package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstInteger implements ConstBase {

	int valor = 0;
	
	public ConstInteger(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}
	
	@Override
	public int etiqueta() {
		return 3; //CONSTANT_Integer
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			valor = flujo.readInt();
		} catch(Exception exc){
			Log.error("Excepcion al leer ConstInteger: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeInt(valor);
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstInteger: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		return true;
	}
	
	public String toString(){
		return "CONSTANT_Integer::" + valor;
	}

}
