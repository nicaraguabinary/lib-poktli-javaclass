package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ConstNoUsable implements ConstBase {

	//------------------------------------------
	//--- El documento de especificaciones de archivo CLASS,
	//--- establece que despues de una entrada CONST_long y CONST_double
	//--- debe considerarse la siguiente entrada como existente pero no-usable.
	//--- Es decir, es una casilla fantasma que ocupa un indice en la tabla,
	//--- pero no ocupe espacio en el archivo CLASS.
	//--- Nota: en el documento oficial colocan esta nota debajo de la explicacion:
	//--- "In retrospect, making 8-byte constants take two constant pool entries was a poor choice."
	//------------------------------------------
	
	public ConstNoUsable(){
		//nada
	}
	
	@Override
	public int etiqueta() {
		return 0;
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		//nada
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		//nada
	}

	@Override
	public boolean verificarValores(ConstBase[] bibConstantes) {
		return true; //nada
	}

	public String toString(){
		return "CONSTANT_NoUsablePosteriorLongDouble";
	}
}
