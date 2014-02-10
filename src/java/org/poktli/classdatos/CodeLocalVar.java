package org.poktli.classdatos;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import org.poktli.Log;

public class CodeLocalVar {

	private int _pcInicio = 0; //u2
	private int _pcLongitud = 0; //u2
	private int _indNombre = 0; //u2
	private int _indDescriptor = 0; //u2
	private int _indice = 0; //u2
	
	public CodeLocalVar(int pcInicio, int pcLongitud, int indNombre, int indDescriptor, int indice){
		_pcInicio = pcInicio;
		_pcLongitud = pcLongitud;
		_indNombre = indNombre;
		_indDescriptor = indDescriptor;
		_indice = indice;
	}
	
	public CodeLocalVar(ByteBuffer wrapped, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		_pcInicio = wrapped.getShort(); //u2
		_pcLongitud = wrapped.getShort(); //u2
		_indNombre = wrapped.getShort(); //u2
		_indDescriptor = wrapped.getShort(); //u2
		_indice = wrapped.getShort(); //u2
		assert (_indNombre>0 && _indNombre<=bibConstantes.length); 
		assert (_indDescriptor>0 && _indDescriptor<=bibConstantes.length);
		assert (bibConstantes[_indNombre-1] instanceof ConstUtf8);
		assert (bibConstantes[_indDescriptor-1] instanceof ConstUtf8);
		String strNom = ((ConstUtf8)bibConstantes[_indNombre-1]).valorCadena();
		String strDesc = ((ConstUtf8)bibConstantes[_indDescriptor-1]).valorCadena();
		Log.acumulativo(espaciosAntesDeTexto + "   VarLocal '"+strNom+"' '"+strDesc+"' -> ini("+_pcInicio+") long("+_pcLongitud+") indice("+_indice+").");
	}
	
	public int cantBytesDatos(){
		return 10;  
	}
	
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_pcInicio);
			flujo.writeShort(_pcLongitud);
			flujo.writeShort(_indNombre);
			flujo.writeShort(_indDescriptor);
			flujo.writeShort(_indice);
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}

	public int pcInicio(){
		return _pcInicio;
	}
	
	public int pcLongitud(){
		return _pcLongitud;
	}
	
	public int indNombre(){
		return _indNombre;
	}
	
	public int indDescriptor(){
		return _indDescriptor;
	}
	
	public int indice(){
		return _indice;
	}
	
}
