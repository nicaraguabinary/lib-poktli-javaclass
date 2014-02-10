package org.poktli.classdatos;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import org.poktli.Log;

public class CodeLine {

	private int _pcInicio;
	private int _numeroLineaFuente;
	
	public CodeLine(ByteBuffer wrapped, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		_pcInicio = wrapped.getShort(); //u2
		_numeroLineaFuente = wrapped.getShort(); //u2
		//Log.informarivo(espaciosAntesDeTexto + "   LineaFuente #" + (i+1)+" pcIni("+pcInicio+") numLineaFuente("+numLinea+").");
	}
	
	public int cantBytesDatos(){
		return 4;
	}
	
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_pcInicio);
			flujo.writeShort(_numeroLineaFuente);
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}
	
	public int pcInicio(){
		return _pcInicio;
	}
	
	public int numeroLineaFuente(){
		return _numeroLineaFuente;
	}
	
}
