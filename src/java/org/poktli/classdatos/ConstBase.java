package org.poktli.classdatos;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface ConstBase {
	//Valor de etiqueta segun las especificaciones de archivo ".class"
	public int etiqueta();
	//Cargar
	public void cargarDatosDesdeFlujo(DataInputStream flujo);
	//Guardar
	public void guardarDatosEnFlujo(DataOutputStream flujo);
	//Verificacion Debug
	public boolean verificarValores(ConstBase[] bibConstantes);
}
