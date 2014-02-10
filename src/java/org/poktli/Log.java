package org.poktli;

public class Log {

	public static final int LOG_nivelSilencioso = 0;
	public static final int LOG_nivelBasico = 5;
	public static final int LOG_nivelTodo = 10;
	
	private static int _nivelLog = LOG_nivelTodo;
	private static String _logAcum = "";
	
	static public void establecerModo(int modo){
		_nivelLog = modo;
	}
	
	static public void msgObligatorio(String cadena){
		System.out.println("Poktli, "+cadena);
	}
	
	static public void error(String cadena){
		if(_nivelLog>LOG_nivelSilencioso) System.out.println("Poktli, "+cadena);
		_logAcum += cadena + "\n";
	}
	
	static public void advertencia(String cadena){
		if(_nivelLog>LOG_nivelSilencioso) System.out.println("Poktli, "+cadena);
		_logAcum += cadena + "\n";
	}
	
	static public void informativo(String cadena){ //Nota: Informativo no debe acumular en _logAcum
		if(_nivelLog>LOG_nivelBasico) System.out.println("Poktli, "+cadena);
	}
	
	static public void acumulativo(String cadena){
		_logAcum += cadena + "\n";
	}
	
	static public void vaciarAcumulativo(){
		_logAcum = "";
	}
	
	static public String cadenaAcumulada(){
		return _logAcum;
	}
	
}
