package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstMethodRef implements ConstBase {

	private int _indiceClase = 0;
	private int _indiceNomYTipo = 0;
	
	public ConstMethodRef(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}
	
	public ConstMethodRef(int iClase, int iNomTipo, ConstBase[] bibConstantes){
		_indiceClase = iClase;
		_indiceNomYTipo = iNomTipo;
		assert verificarValores(bibConstantes);
	}
	
	@Override
	public int etiqueta() {
		return 10; //CONSTANT_Methodref
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			_indiceClase = flujo.readUnsignedShort();
			_indiceNomYTipo = flujo.readUnsignedShort();
		}catch(Exception exc){
			Log.error("Excepcion al leer ConstMethodRef: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_indiceClase);
			flujo.writeShort(_indiceNomYTipo);
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstMethodRef: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		assert (_indiceClase>0 && _indiceClase<=bibConstantes.length);
		assert (bibConstantes[_indiceClase-1] instanceof ConstClass);
		if(!(_indiceClase>0 && _indiceClase<=bibConstantes.length)) return false;
		if(!(bibConstantes[_indiceClase-1] instanceof ConstClass)) return false;
		//
		assert (_indiceNomYTipo>0 && _indiceNomYTipo<=bibConstantes.length);
		assert (bibConstantes[_indiceNomYTipo-1] instanceof ConstNameAndType);
		if(!(_indiceNomYTipo>0 && _indiceNomYTipo<=bibConstantes.length)) return false;
		if(!(bibConstantes[_indiceNomYTipo-1] instanceof ConstNameAndType)) return false;
		//
		return true;
	}
	
	public int indiceClase(){
		return _indiceClase;
	}
	
	public int indiceNomYTipo(){
		return _indiceNomYTipo;
	}
	
	public String toString(){
		return "CONSTANT_Methodref::" + _indiceClase + "::" + _indiceNomYTipo;
	}

}
