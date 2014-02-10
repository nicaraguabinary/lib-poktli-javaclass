package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstInterfaceMethodRef implements ConstBase {

	int _indiceClase = 0;
	int _indiceNomYTipo = 0;
	
	public ConstInterfaceMethodRef(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}
	
	@Override
	public int etiqueta() {
		return 11; //CONSTANT_InterfaceMethodref
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			_indiceClase = flujo.readUnsignedShort();
			_indiceNomYTipo = flujo.readUnsignedShort();
		}catch(Exception exc){
			Log.error("Excepcion al leer ConstInterfaceMethodRef: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_indiceClase);
			flujo.writeShort(_indiceNomYTipo);
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstInterfaceMethodRef: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		assert (_indiceClase>0 && _indiceClase<bibConstantes.length);
		assert (bibConstantes[_indiceClase-1] instanceof ConstClass);
		if(!(_indiceClase>0 && _indiceClase<bibConstantes.length)) return false;
		if(!(bibConstantes[_indiceClase-1] instanceof ConstClass)) return false;
		//
		assert (_indiceNomYTipo>0 && _indiceNomYTipo<bibConstantes.length);
		assert (bibConstantes[_indiceNomYTipo-1] instanceof ConstNameAndType);
		if(!(_indiceNomYTipo>0 && _indiceNomYTipo<bibConstantes.length)) return false;
		if(!(bibConstantes[_indiceNomYTipo-1] instanceof ConstNameAndType)) return false;
		//
		return true;
	}
	
	public String toString(){
		return "CONSTANT_InterfaceMethodref::" + _indiceClase + "::" + _indiceNomYTipo;
	}

}
