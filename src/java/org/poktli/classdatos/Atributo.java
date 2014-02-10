package org.poktli.classdatos;

import java.io.DataOutputStream;

public interface Atributo {
	//Indice del nombre del atributo
	public int indiceNomAtributo();
	//Bytes de atributo
	public int cantBytesDatos();
	//Guardar en archivo
	public void guardarDatosEnFlujo(DataOutputStream flujo);
}
