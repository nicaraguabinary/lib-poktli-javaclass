package org.poktli.classdatos;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import org.poktli.Log;

public class AtributoLocalVars implements Atributo {

	private int _indiceNomAtributo 	= 0;
	CodeLocalVar[] localVars = null;
	
	public AtributoLocalVars(int iNom){
		_indiceNomAtributo = iNom;
		localVars = null;
	}
	
	public AtributoLocalVars(int iNom, byte[] datos, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		_indiceNomAtributo = iNom;
		//
		ByteBuffer wrapped = ByteBuffer.wrap(datos);
		int cantVars = wrapped.getShort(); Log.acumulativo(espaciosAntesDeTexto + "   " + cantVars +" variables locales.");
		if(cantVars>0){
			localVars = new CodeLocalVar[cantVars];
			int i;
			for(i=0; i<cantVars; i++){
				localVars[i] = new CodeLocalVar(wrapped, bibConstantes, espaciosAntesDeTexto);
				assert wrapped.arrayOffset() <= datos.length;
			}
		}
		assert wrapped.arrayOffset() <= datos.length;
	}
	
	public int registrarLocalVar(int pcInicio, int pcLongitud, int indNombre, int indDescriptor, int indice){
		if(localVars==null){
			localVars = new CodeLocalVar[1];
		} else {
			CodeLocalVar[] localVarsN = new CodeLocalVar[localVars.length + 1];
			int i; for(i=0; i<localVars.length; i++) localVarsN[i] = localVars[i];
			localVars = localVarsN;
		}
		localVars[localVars.length - 1] = new CodeLocalVar(pcInicio, pcLongitud, indNombre, indDescriptor, indice);
		return localVars.length - 1;
	}
	
	public int indiceLocarVarPorIndiceNombre(int indNombre){
		int i;
		for(i=0; i<localVars.length; i++){
			if(localVars[i].indNombre()==indNombre){
				return i;				
			}
		}
		return -1;
	}
	
	@Override
	public int indiceNomAtributo() {
		return _indiceNomAtributo;
	}
	
	@Override
	public int cantBytesDatos(){
		int cantBytes = 2; //cantVars
		if(localVars!=null){
			int i; for(i=0; i<localVars.length; i++) cantBytes += localVars[i].cantBytesDatos();
		}
		return cantBytes;
	}
	
	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_indiceNomAtributo);
			flujo.writeInt(cantBytesDatos());
			//
			if(localVars==null){
				flujo.writeShort(0);
			} else {
				flujo.writeShort(localVars.length);
				int i; for(i=0; i<localVars.length; i++) localVars[i].guardarDatosEnFlujo(flujo);
			}
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}

}
