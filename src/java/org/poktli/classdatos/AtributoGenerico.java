package org.poktli.classdatos;

import java.io.DataOutputStream;
import org.poktli.Log;

public class AtributoGenerico implements Atributo {

	int indiceNomAtributo 	= 0;
	byte[] datosAtributo	= null;
		
	public AtributoGenerico(int iNom, byte[] datos, ConstBase[] bibConstantes){
		indiceNomAtributo = iNom;
		datosAtributo = datos;
		assert (iNom>0 && iNom<=bibConstantes.length);
		assert bibConstantes[iNom-1] instanceof ConstUtf8;
	}
	
	@Override
	public int indiceNomAtributo(){
		return indiceNomAtributo;
	}
	
	@Override
	public int cantBytesDatos(){
		if(datosAtributo==null) return 0;
		else  return datosAtributo.length;
	}
	
	@Override
	public void guardarDatosEnFlujo(DataOutputStream flujo) {
		try {
			flujo.writeShort(indiceNomAtributo);
			if(datosAtributo==null){
				flujo.writeInt(0);
			} else {
				flujo.writeInt(cantBytesDatos());
				flujo.write(datosAtributo);
			}
		} catch(Exception exc){
			Log.error("ERROR al escribir:" + exc); exc.printStackTrace();
		}
	}
	
}
