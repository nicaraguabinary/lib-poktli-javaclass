package org.poktli.classdatos;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import org.poktli.Log;

public class AtributoInnerClasses implements Atributo {

	private int _indiceNomAtributo 	= 0;
	InnerClass[] _clasesInternas = null;
	
	public AtributoInnerClasses(int iNom, byte[] datos, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		_indiceNomAtributo = iNom;
		//
		ByteBuffer wrapped = ByteBuffer.wrap(datos);
		int cantClasesInternas = wrapped.getShort(); Log.acumulativo(espaciosAntesDeTexto + "   " + cantClasesInternas +" clases internas.");
		if(cantClasesInternas>0){
			_clasesInternas = new InnerClass[cantClasesInternas];
			int i;
			for(i=0; i<cantClasesInternas; i++){
				_clasesInternas[i] = new InnerClass(wrapped, bibConstantes, espaciosAntesDeTexto);
				assert wrapped.arrayOffset() <= datos.length;
			}
		}
		assert wrapped.arrayOffset() <= datos.length;
	}
	
	@Override
	public int indiceNomAtributo() {
		return _indiceNomAtributo;
	}
	
	@Override
	public int cantBytesDatos(){
		int cantBytes = 2; //cantClasesInternas
		if(_clasesInternas!=null){
			int i; for(i=0; i<_clasesInternas.length; i++) cantBytes += _clasesInternas[i].cantBytesDatos();
		}
		return cantBytes;
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_indiceNomAtributo);
			flujo.writeInt(cantBytesDatos());
			//
			if(_clasesInternas==null){
				flujo.writeShort(0);
			} else {
				flujo.writeShort(_clasesInternas.length);
				int i; for(i=0; i<_clasesInternas.length; i++) _clasesInternas[i].guardarDatosEnFlujo(flujo);
			}
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}

}
