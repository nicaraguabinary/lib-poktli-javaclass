package org.poktli;

public class MainSnifSnif {
	
	public static void main(String[] args) {
		Log.establecerModo(Log.LOG_nivelTodo); //LOG_nivelSilencioso, LOG_nivelBasico, LOG_nivelTodo
		boolean modoVerboso			= false;
		boolean modoPruebaArchivo 	= false;
		String rutaArchivoClass		= "";
		//Analizar argumentos
		int iArg;     
		for(iArg=0; iArg<args.length; iArg++){ 
			String argumento = args[iArg];
			if(argumento.equals("-v")){
				modoVerboso = true;
			} else if(argumento.equals("-test")){
				modoPruebaArchivo = true;
			} else if(argumento.equals("-class")){
				if((iArg+1)<args.length) rutaArchivoClass = args[++iArg];
			}    
		}  
		//Ejecutar
		if(rutaArchivoClass.length()==0){
			Log.error("No se ha especificado un archivo class.");
			Log.error("Parametros: -v (modo verboso) -test (prueba guardado de clase) -class [archivo].");
		} else {
			ArchivoClass clase = new ArchivoClass();
			Log.vaciarAcumulativo(); 
			if(!clase.cargarClaseDesdeArchivo(rutaArchivoClass)){
				Log.msgObligatorio(Log.cadenaAcumulada());
				Log.error("ERROR interprentando archivo class '"+rutaArchivoClass+"'.");
			} else {
				if(modoVerboso){
					Log.informativo("------------------------");
					Log.informativo(Log.cadenaAcumulada());
					Log.informativo("------------------------");
				}
				Log.msgObligatorio("Archivo class '"+rutaArchivoClass+"' interpretado exitosamente.");
				//Probar guardado de archivo clase
				if(modoPruebaArchivo){
					String rutaArchivoPruebaGuardado = "./tmpPoktliPruebaGuardado.class";
					if(!clase.guardarClaseEnArchivo(rutaArchivoPruebaGuardado)){
						Log.error("ERROR guardando archivo class '"+rutaArchivoPruebaGuardado+"'.");
					} else {
						ArchivoClass clase2 = new ArchivoClass();
						if(!clase2.cargarClaseDesdeArchivo(rutaArchivoPruebaGuardado)){
							Log.msgObligatorio(Log.cadenaAcumulada());
							Log.error("ERROR en carga de archivo class guardado '"+rutaArchivoPruebaGuardado+"'.");
							Log.error("--- EL GUARDADO DE CLASE HA FALLADO. ---");
						} else {
							Log.msgObligatorio("--- Prueba de guardado de clase ha sido exitosa. ---");
							Log.msgObligatorio("(Archivo fue interpretado, se guardo una copia y finalmente se interpreto la copia para asegurar la integridad binaria)");
						}
					}
				}
			}
		}
		Log.msgObligatorio("Fin.");
	}
	
}