package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstFloat implements ConstBase {

	int valor = 0;
	
	public ConstFloat(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}

	@Override
	public int etiqueta() {
		return 4; //CONSTANT_Float
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			valor = flujo.readInt();
		} catch(Exception exc){
			Log.error("Excepcion al leer ConstFloat: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeInt(valor);
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstFloat: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		return true;
	}
	
	public String toString(){
		return "CONSTANT_Float::" + valor;
	}

}
