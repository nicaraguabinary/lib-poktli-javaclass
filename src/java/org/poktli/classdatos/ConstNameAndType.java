package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstNameAndType implements ConstBase {

	int _indiceNombre = 0;
	int _indiceDescripcion = 0;
	
	public ConstNameAndType(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}
	
	public ConstNameAndType(int iNombre, int iDesc, ConstBase[] bibConstantes){
		_indiceNombre = iNombre;
		_indiceDescripcion = iDesc;
		assert verificarValores(bibConstantes);
	}
	
	@Override
	public int etiqueta() {
		return 12; //CONSTANT_NameAndType
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			_indiceNombre = flujo.readUnsignedShort();
			_indiceDescripcion = flujo.readUnsignedShort();
		} catch(Exception exc){
			Log.error("Excepcion al leer ConstNameAndType: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_indiceNombre);
			flujo.writeShort(_indiceDescripcion);
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstNameAndType: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		assert (_indiceNombre>0 && _indiceNombre<=bibConstantes.length);
		assert (bibConstantes[_indiceNombre-1] instanceof ConstUtf8);
		if(!(_indiceNombre>0 && _indiceNombre<=bibConstantes.length)) return false;
		if(!(bibConstantes[_indiceNombre-1] instanceof ConstUtf8)) return false;
		//
		assert (_indiceDescripcion>0 && _indiceDescripcion<=bibConstantes.length);
		assert (bibConstantes[_indiceDescripcion-1] instanceof ConstUtf8);
		if(!(_indiceDescripcion>0 && _indiceDescripcion<=bibConstantes.length)) return false;
		if(!(bibConstantes[_indiceDescripcion-1] instanceof ConstUtf8)) return false;
		//
		return true;
	}
	
	public int indiceConstNombre(){
		return _indiceNombre;
	}
	
	public int indiceConstDescripcion(){
		return _indiceDescripcion;
	}
	
	public String toString(){
		return "CONSTANT_NameAndType::" + _indiceNombre + "::" + _indiceDescripcion;
	}

}
