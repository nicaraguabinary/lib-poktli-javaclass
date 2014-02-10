package org.poktli.classdatos;

import java.io.DataOutputStream;
import java.nio.ByteBuffer;
import org.poktli.Log;

public class AtributoLineasNum implements Atributo {

	private int _indiceNomAtributo 	= 0;
	CodeLine[] lineasFuente = null;
	
	public AtributoLineasNum(int iNom){
		_indiceNomAtributo = iNom;
		lineasFuente = null;
	}
			
	public AtributoLineasNum(int iNom, byte[] datos, ConstBase[] bibConstantes, String espaciosAntesDeTexto){
		_indiceNomAtributo = iNom;
		//
		ByteBuffer wrapped = ByteBuffer.wrap(datos);
		int cantLineas = wrapped.getShort(); Log.acumulativo(espaciosAntesDeTexto + "   " + cantLineas +" lineas de codigo fuente.");
		if(cantLineas>0){
			lineasFuente = new CodeLine[cantLineas];
			int i;
			for(i=0; i<cantLineas; i++){
				lineasFuente[i] = new CodeLine(wrapped, bibConstantes, espaciosAntesDeTexto);
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
		int cantBytes = 2; //cantLineas
		if(lineasFuente!=null){
			int i; for(i=0; i<lineasFuente.length; i++) cantBytes += lineasFuente[i].cantBytesDatos();
		}
		return cantBytes;
	}

	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(_indiceNomAtributo);
			flujo.writeInt(cantBytesDatos());
			//
			if(lineasFuente==null){
				flujo.writeShort(0);
			} else {
				flujo.writeShort(lineasFuente.length);
				int i; for(i=0; i<lineasFuente.length; i++) lineasFuente[i].guardarDatosEnFlujo(flujo);
			}
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}

}
