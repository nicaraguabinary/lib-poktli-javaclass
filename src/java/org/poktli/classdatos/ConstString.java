package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import org.poktli.Log;

public class ConstString implements ConstBase {

	private int _indiceCadena = 0;
	
	public ConstString(int indiceCadena, ConstBase[] bibConstantes){
		_indiceCadena = indiceCadena;
		verificarValores(bibConstantes);
	}
	
	public ConstString(DataInputStream flujo){
		cargarDatosDesdeFlujo(flujo);
	}
	
	public int indiceCadena(){
		return _indiceCadena;
	}
	
	@Override
	public int etiqueta() {
		return 8; //CONSTANT_String
	}

	@Override
	public void cargarDatosDesdeFlujo(DataInputStream flujo) {
		try {
			_indiceCadena = flujo.readUnsignedShort();
		} catch(Exception exc){
			Log.error("Excepcion al leer ConstString: " + exc);
		}
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_indiceCadena);
		}catch(Exception exc){
			Log.error("Excepcion al escribir ConstString: " + exc);
		}
	}
	
	@Override
	public boolean verificarValores(ConstBase[] bibConstantes){
		assert (_indiceCadena>0 && _indiceCadena<=bibConstantes.length);
		assert (bibConstantes[_indiceCadena-1] instanceof ConstUtf8);
		if(!(_indiceCadena>0 && _indiceCadena<=bibConstantes.length)) return false;
		if(!(bibConstantes[_indiceCadena-1] instanceof ConstUtf8)) return false;
		return true;
	}
	
	public String toString(){
		return "CONSTANT_String::" + _indiceCadena;
	}

}
