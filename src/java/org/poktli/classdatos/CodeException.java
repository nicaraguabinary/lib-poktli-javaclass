package org.poktli.classdatos;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import org.poktli.Log;

public class CodeException {

	private int _pcInicio		= 0;
	private int _pcFin			= 0;
	private int _pcGestor		= 0;
	private int _tipoCaptura	= 0;
	
	public CodeException(ByteBuffer wrapped, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		_pcInicio = wrapped.getShort();
		_pcFin = wrapped.getShort();
		_pcGestor = wrapped.getShort();
		_tipoCaptura = wrapped.getShort();
      	Log.acumulativo(espaciosAntesDeTexto + "   Excepcion ini("+_pcInicio+") fin("+_pcFin+") handler("+_pcGestor+") catchTipe("+_tipoCaptura+").");
	}
	
	public int cantBytesDatos(){
		return 8;
	}
	
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_pcInicio);
			flujo.writeShort(_pcFin);
			flujo.writeShort(_pcGestor);
			flujo.writeShort(_tipoCaptura);
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}	
	
	public int pcInicio(){
		return _pcInicio;
	}
	
	public int pcFin(){
		return _pcFin;
	}
	
	public int pcGestor(){
		return _pcGestor;
	}
	
	public int tipoCaptura(){
		return _tipoCaptura;
	}
	
}
