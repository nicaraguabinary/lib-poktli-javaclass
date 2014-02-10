package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstClass implements ConstBase {

	int indiceNom = 0;
	
	public ConstClass(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}
	
	public ConstClass(int iConstNom, ConstBase[] bibConsts){
		indiceNom = iConstNom;
		assert verificarValores(bibConsts);
	}
	
	@Override
	public int etiqueta() {
		return 7; //CONSTANT_Class
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			indiceNom = flujo.readUnsignedShort();
		} catch(Exception exc){
			Log.error("Excepcion al leer ConstClass: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(indiceNom);
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstClass: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		assert (indiceNom>0 && indiceNom<=bibConstantes.length);
		assert (bibConstantes[indiceNom-1] instanceof ConstUtf8);
		if(!(indiceNom>0 && indiceNom<=bibConstantes.length)) return false;
		if(!(bibConstantes[indiceNom-1] instanceof ConstUtf8)) return false;
		return true;
	}
	
	public int indiceConstNombre(){
		assert (indiceNom>=0);
		return indiceNom;
	}
	
	public String toString(){
		return "CONSTANT_Class::" + indiceNom;
	}

}
