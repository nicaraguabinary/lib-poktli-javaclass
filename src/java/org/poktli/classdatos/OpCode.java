package org.poktli.classdatos;

import org.poktli.Log;

public class OpCode {
	
	private String _nombreOpCode		= "";
	private int _valorOpCode			= 0;
	private String _descripcionOpCode	= "";
	private int _bytesPosteriores		= 0;
	
	public static final int OPCODE_aaload = 0x32;
	public static final int OPCODE_aastore = 0x53;
	public static final int OPCODE_aconst_null = 0x1;
	public static final int OPCODE_aload = 0x19;
	public static final int OPCODE_aload_0 = 0x2a;
	public static final int OPCODE_aload_1 = 0x2b;
	public static final int OPCODE_aload_2 = 0x2c;
	public static final int OPCODE_aload_3 = 0x2d;
	public static final int OPCODE_anewarray = 0xbd;
	public static final int OPCODE_areturn = 0xb0;
	public static final int OPCODE_arraylength = 0xbe;
	public static final int OPCODE_astore = 0x3a;
	public static final int OPCODE_astore_0 = 0x4b;
	public static final int OPCODE_astore_1 = 0x4c;
	public static final int OPCODE_astore_2 = 0x4d;
	public static final int OPCODE_astore_3 = 0x4e;
	public static final int OPCODE_athrow = 0xbf;
	public static final int OPCODE_baload = 0x33;
	public static final int OPCODE_bastore = 0x54;
	public static final int OPCODE_bipush = 0x10;
	public static final int OPCODE_caload = 0x34;
	public static final int OPCODE_castore = 0x55;
	public static final int OPCODE_checkcast = 0xc0;
	public static final int OPCODE_d2f = 0x90;
	public static final int OPCODE_d2i = 0x8e;
	public static final int OPCODE_d2l = 0x8f;
	public static final int OPCODE_dadd = 0x63;
	public static final int OPCODE_daload = 0x31;
	public static final int OPCODE_dastore = 0x52;
	public static final int OPCODE_dcmpg = 0x98;
	public static final int OPCODE_dcmpl = 0x97;
	public static final int OPCODE_dconst_0 = 0xe;
	public static final int OPCODE_dconst_1 = 0xf;
	public static final int OPCODE_ddiv = 0x6f;
	public static final int OPCODE_dload = 0x18;
	public static final int OPCODE_dload_0 = 0x26;
	public static final int OPCODE_dload_1 = 0x27;
	public static final int OPCODE_dload_2 = 0x28;
	public static final int OPCODE_dload_3 = 0x29;
	public static final int OPCODE_dmul = 0x6b;
	public static final int OPCODE_dneg = 0x77;
	public static final int OPCODE_drem = 0x73;
	public static final int OPCODE_dreturn = 0xaf;
	public static final int OPCODE_dstore = 0x39;
	public static final int OPCODE_dstore_0 = 0x47;
	public static final int OPCODE_dstore_1 = 0x48;
	public static final int OPCODE_dstore_2 = 0x49;
	public static final int OPCODE_dstore_3 = 0x4a;
	public static final int OPCODE_dsub = 0x67;
	public static final int OPCODE_dup = 0x59;
	public static final int OPCODE_dup_x1 = 0x5a;
	public static final int OPCODE_dup_x2 = 0x5b;
	public static final int OPCODE_dup2 = 0x5c;
	public static final int OPCODE_dup2_x1 = 0x5d;
	public static final int OPCODE_dup2_x2 = 0x5e;
	public static final int OPCODE_f2d = 0x8d;
	public static final int OPCODE_f2i = 0x8b;
	public static final int OPCODE_f2l = 0x8c;
	public static final int OPCODE_fadd = 0x62;
	public static final int OPCODE_faload = 0x30;
	public static final int OPCODE_fastore = 0x51;
	public static final int OPCODE_fcmpg = 0x96;
	public static final int OPCODE_fcmpl = 0x95;
	public static final int OPCODE_fconst_0 = 0xb;
	public static final int OPCODE_fconst_1 = 0xc;
	public static final int OPCODE_fconst_2 = 0xd;
	public static final int OPCODE_fdiv = 0x6e;
	public static final int OPCODE_fload = 0x17;
	public static final int OPCODE_fload_0 = 0x22;
	public static final int OPCODE_fload_1 = 0x23;
	public static final int OPCODE_fload_2 = 0x24;
	public static final int OPCODE_fload_3 = 0x25;
	public static final int OPCODE_fmul = 0x6a;
	public static final int OPCODE_fneg = 0x76;
	public static final int OPCODE_frem = 0x72;
	public static final int OPCODE_freturn = 0xae;
	public static final int OPCODE_fstore = 0x38;
	public static final int OPCODE_fstore_0 = 0x43;
	public static final int OPCODE_fstore_1 = 0x44;
	public static final int OPCODE_fstore_2 = 0x45;
	public static final int OPCODE_fstore_3 = 0x46;
	public static final int OPCODE_fsub = 0x66;
	public static final int OPCODE_getfield = 0xb4;
	public static final int OPCODE_getstatic = 0xb2;
	public static final int OPCODE_goto = 0xa7;
	public static final int OPCODE_goto_w = 0xc8;
	public static final int OPCODE_i2b = 0x91;
	public static final int OPCODE_i2c = 0x92;
	public static final int OPCODE_i2d = 0x87;
	public static final int OPCODE_i2f = 0x86;
	public static final int OPCODE_i2l = 0x85;
	public static final int OPCODE_i2s = 0x93;
	public static final int OPCODE_iadd = 0x60;
	public static final int OPCODE_iaload = 0x2e;
	public static final int OPCODE_iand = 0x7e;
	public static final int OPCODE_iastore = 0x4f;
	public static final int OPCODE_iconst_m1 = 0x2;
	public static final int OPCODE_iconst_0 = 0x3;
	public static final int OPCODE_iconst_1 = 0x4;
	public static final int OPCODE_iconst_2 = 0x5;
	public static final int OPCODE_iconst_3 = 0x6;
	public static final int OPCODE_iconst_4 = 0x7;
	public static final int OPCODE_iconst_5 = 0x8;
	public static final int OPCODE_idiv = 0x6c;
	public static final int OPCODE_if_acmpeq = 0xa5;
	public static final int OPCODE_if_acmpne = 0xa6;
	public static final int OPCODE_if_icmpeq = 0x9f;
	public static final int OPCODE_if_icmpne = 0xa0;
	public static final int OPCODE_if_icmplt = 0xa1;
	public static final int OPCODE_if_icmpge = 0xa2;
	public static final int OPCODE_if_icmpgt = 0xa3;
	public static final int OPCODE_if_icmple = 0xa4;
	public static final int OPCODE_ifeq = 0x99;
	public static final int OPCODE_ifne = 0x9a;
	public static final int OPCODE_iflt = 0x9b;
	public static final int OPCODE_ifge = 0x9c;
	public static final int OPCODE_ifgt = 0x9d;
	public static final int OPCODE_ifle = 0x9e;
	public static final int OPCODE_ifnonnull = 0xc7;
	public static final int OPCODE_ifnull = 0xc6;
	public static final int OPCODE_iinc = 0x84;
	public static final int OPCODE_iload = 0x15;
	public static final int OPCODE_iload_0 = 0x1a;
	public static final int OPCODE_iload_1 = 0x1b;
	public static final int OPCODE_iload_2 = 0x1c;
	public static final int OPCODE_iload_3 = 0x1d;
	public static final int OPCODE_imul = 0x68;
	public static final int OPCODE_ineg = 0x74;
	public static final int OPCODE_instanceof = 0xc1;
	public static final int OPCODE_invokeinterface = 0xb9;
	public static final int OPCODE_invokespecial = 0xb7;
	public static final int OPCODE_invokestatic = 0xb8;
	public static final int OPCODE_invokevirtual = 0xb6;
	public static final int OPCODE_ior = 0x80;
	public static final int OPCODE_irem = 0x70;
	public static final int OPCODE_ireturn = 0xac;
	public static final int OPCODE_ishl = 0x78;
	public static final int OPCODE_ishr = 0x7a;
	public static final int OPCODE_istore = 0x36;
	public static final int OPCODE_istore_0 = 0x3b;
	public static final int OPCODE_istore_1 = 0x3c;
	public static final int OPCODE_istore_2 = 0x3d;
	public static final int OPCODE_istore_3 = 0x3e;
	public static final int OPCODE_isub = 0x64;
	public static final int OPCODE_iushr = 0x7c;
	public static final int OPCODE_ixor = 0x82;
	public static final int OPCODE_jsr = 0xa8;
	public static final int OPCODE_jsr_w = 0xc9;
	public static final int OPCODE_l2d = 0x8a;
	public static final int OPCODE_l2f = 0x89;
	public static final int OPCODE_l2i = 0x88;
	public static final int OPCODE_ladd = 0x61;
	public static final int OPCODE_laload = 0x2f;
	public static final int OPCODE_land = 0x7f;
	public static final int OPCODE_lastore = 0x50;
	public static final int OPCODE_lcmp = 0x94;
	public static final int OPCODE_lconst_0 = 0x9;
	public static final int OPCODE_lconst_1 = 0xa;
	public static final int OPCODE_ldc = 0x12;
	public static final int OPCODE_ldc_w = 0x13;
	public static final int OPCODE_ldc2_w = 0x14;
	public static final int OPCODE_ldiv = 0x6d;
	public static final int OPCODE_lload = 0x16;
	public static final int OPCODE_lload_0 = 0x1e;
	public static final int OPCODE_lload_1 = 0x1f;
	public static final int OPCODE_lload_2 = 0x20;
	public static final int OPCODE_lload_3 = 0x21;
	public static final int OPCODE_lmul = 0x69;
	public static final int OPCODE_lneg = 0x75;
	public static final int OPCODE_lookupswitch = 0xab;
	public static final int OPCODE_lor = 0x81;
	public static final int OPCODE_lrem = 0x71;
	public static final int OPCODE_lreturn = 0xad;
	public static final int OPCODE_lshl = 0x79;
	public static final int OPCODE_lshr = 0x7b;
	public static final int OPCODE_lstore = 0x37;
	public static final int OPCODE_lstore_0 = 0x3f;
	public static final int OPCODE_lstore_1 = 0x40;
	public static final int OPCODE_lstore_2 = 0x41;
	public static final int OPCODE_lstore_3 = 0x42;
	public static final int OPCODE_lsub = 0x65;
	public static final int OPCODE_lushr = 0x7d;
	public static final int OPCODE_lxor = 0x83;
	public static final int OPCODE_monitorenter = 0xc2;
	public static final int OPCODE_monitorexit = 0xc3;
	public static final int OPCODE_multianewarray = 0xc5;
	public static final int OPCODE_new = 0xbb;
	public static final int OPCODE_newarray = 0xbc;
	public static final int OPCODE_nop = 0x0;
	public static final int OPCODE_pop = 0x57;
	public static final int OPCODE_pop2 = 0x58;
	public static final int OPCODE_putfield = 0xb5;
	public static final int OPCODE_putstatic = 0xb3;
	public static final int OPCODE_ret = 0xa9;
	public static final int OPCODE_return = 0xb1;
	public static final int OPCODE_saload = 0x35;
	public static final int OPCODE_sastore = 0x56;
	public static final int OPCODE_sipush = 0x11;
	public static final int OPCODE_swap = 0x5f;
	public static final int OPCODE_tableswitch = 0xaa;
	public static final int OPCODE_wide = 0xc4;

	
	private static boolean _bibInicializada	= false;
	private static int _usoBibOpCodes	= 0;
	private static OpCode[] _bibOpCodes = new OpCode[256]; //Actualmente son 200 opCodes
	
	OpCode(String nomOp, int valOp, String descOp, int bytesPostOp){
		_nombreOpCode = nomOp;
		_valorOpCode = valOp;
		_descripcionOpCode = descOp;
		_bytesPosteriores = bytesPostOp;
	}
	
	public String nombreOpCode(){
		return _nombreOpCode;
	}
	
	public int valorOpCode(){
		return _valorOpCode;
	}
	
	public String descripcionOpCode(){
		return _descripcionOpCode;
	}
	
	public int bytesPosteriores(){
		return _bytesPosteriores;
	}
	
	static public OpCode dameOpCode(byte[] codigo, int posicion){
		if(!_bibInicializada){
			inicializarBibliotecaOpCodes();
			_bibInicializada = true;
		}
		if(codigo!=null){
			if(posicion>=0 && posicion<codigo.length){
				int valOp = ((int)codigo[posicion] & 0xFF);
				assert (valOp>=0 && valOp<256);
				//Buscar OpCode
				int i;
				for(i=0; i<_usoBibOpCodes; i++){
					if(_bibOpCodes[i].valorOpCode()==valOp){
						OpCode opCode = _bibOpCodes[i];
						if(opCode.valorOpCode()==OPCODE_lookupswitch){
							//http://docs.oracle.com/javase/specs/jvms/se5.0/html/Instructions2.doc8.html
							//Log.informativo("OPCODE_lookupswitch");
							int posicionFinal = posicion + 1; while((posicionFinal % 4)!=0){ assert codigo[posicionFinal]==0; posicionFinal++; }//Hay de 0 a 3 bytes de padeo
							//Log.informativo("   "+(posicionFinal - (posicion + 1))+" bytes de padeo posIniciaSiguiente("+posicionFinal+")");
							posicionFinal += 4; //los 4 bytes de default
							int cantPares = (((int)codigo[posicionFinal] & 0xFF) << 24) | (((int)codigo[posicionFinal+1] & 0xFF) << 16) | (((int)codigo[posicionFinal+2] & 0xFF) << 8) | ((int)codigo[posicionFinal+3] & 0xFF);
							//Log.informativo("   Cantidad de pares: " + cantPares);
							assert cantPares>=0;
							posicionFinal += 4; //los 4 bytes de numberPairs
							posicionFinal += (cantPares * 8); //la cantidad de pares valor-salto
							//Log.informativo("   Bytes despues de opCode: " + (posicionFinal-posicion-1));
							return new OpCode(opCode.nombreOpCode(), opCode.valorOpCode(), opCode.descripcionOpCode(), posicionFinal-posicion-1);
						} else if(opCode.valorOpCode()==OPCODE_tableswitch){ 
							//http://docs.oracle.com/javase/specs/jvms/se5.0/html/Instructions2.doc14.html
							//Log.informativo("OPCODE_tableswitch");
							int posicionFinal = posicion + 1; while((posicionFinal % 4)!=0){ assert codigo[posicionFinal]==0; posicionFinal++; } //Hay de 0 a 3 bytes de padeo
							//Log.informativo((posicionFinal - (posicion + 1))+" bytes de padeo posIniciaSiguiente("+posicionFinal+")");
							posicionFinal += 4; //los 4 bytes de default
							int valorBajo = (((int)codigo[posicionFinal] & 0xFF) << 24) | (((int)codigo[posicionFinal+1] & 0xFF) << 16) | (((int)codigo[posicionFinal+2] & 0xFF) << 8) | ((int)codigo[posicionFinal+3] & 0xFF);
							//Log.informativo("   valorBajo: " + valorBajo);
							posicionFinal += 4; //los 4 bytes de valorBajo
							int valorAlto = (((int)codigo[posicionFinal] & 0xFF) << 24) | (((int)codigo[posicionFinal+1] & 0xFF) << 16) | (((int)codigo[posicionFinal+2] & 0xFF) << 8) | ((int)codigo[posicionFinal+3] & 0xFF);
							//Log.informativo("   valorAlto: " + valorAlto);
							assert valorBajo<=valorAlto;
							posicionFinal += 4; //los 4 bytes de valorAlto
							posicionFinal += ((valorAlto - valorBajo + 1) * 4); //la cantidad de valores de salto
							//Log.informativo("   Bytes despues de opCode: " + (posicionFinal-posicion-1));
							return new OpCode(opCode.nombreOpCode(), opCode.valorOpCode(), opCode.descripcionOpCode(), posicionFinal-posicion-1);
						} else if(opCode.valorOpCode()==OPCODE_wide){
							//http://docs.oracle.com/javase/specs/jvms/se5.0/html/Instructions2.doc15.html
							Log.informativo("OPCODE_wide");
							int valorSiguienteByte = ((int)codigo[posicion+1] & 0xFF);
							if(valorSiguienteByte==OPCODE_iload || valorSiguienteByte==OPCODE_fload || valorSiguienteByte==OPCODE_aload || valorSiguienteByte==OPCODE_lload || valorSiguienteByte==OPCODE_dload || valorSiguienteByte==OPCODE_istore || valorSiguienteByte==OPCODE_fstore || valorSiguienteByte==OPCODE_astore || valorSiguienteByte==OPCODE_lstore || valorSiguienteByte==OPCODE_dstore || valorSiguienteByte==OPCODE_ret){
								Log.informativo("   formato 1");
								return new OpCode(opCode.nombreOpCode(), opCode.valorOpCode(), opCode.descripcionOpCode(), 3);
							} else if(valorSiguienteByte==OPCODE_iinc){
								Log.informativo("   formato 2");
								return new OpCode(opCode.nombreOpCode(), opCode.valorOpCode(), opCode.descripcionOpCode(), 5);
							} else {
								Log.informativo("   ERROR formato desconocido");
								return null;
							}
						}
						return opCode;
					}
				}
			}
		}
		assert false; //OpCode no enconrtado, no deberia suceder
		return null;
	}
	
	static private boolean registrarOpCode(String nomOp, int valOp, int bytesPostOp, String descOp){
		boolean existeConflicto = false;
		//Revisar si el opCode existe con ese nombre o valor numerico
		int i;
		for(i=0; i<_usoBibOpCodes; i++){
			if(_bibOpCodes[i].valorOpCode()==valOp){
				existeConflicto = true;
				Log.error("Conflicto bibOpCodes, el opCode valor "+valOp+" ya esta registrado!");
				assert false; //No deberia suceder
			}
			if(_bibOpCodes[i].nombreOpCode()==nomOp){
				existeConflicto = true;
				Log.error("Conflicto bibOpCodes, el opCode nombre '"+nomOp+"' ya esta registrado!");
				assert false; //No deberia suceder
			}
		}
		if(!existeConflicto){
			_bibOpCodes[_usoBibOpCodes] = new OpCode(nomOp, valOp, descOp, bytesPostOp);
			_usoBibOpCodes++;
			return true;
		}
		return false;
	}
	
	static private void inicializarBibliotecaOpCodes(){
		Log.informativo("Inicializando biblioteca de OpCodes...");
		registrarOpCode("aaload", OPCODE_aaload, 0, "LoadÊreferenceÊfrom array");
		registrarOpCode("aastore", OPCODE_aastore, 0, "Store intoÊreferenceÊarray");
		registrarOpCode("aconst_null", OPCODE_aconst_null, 0, "PushÊnull");
		registrarOpCode("aload", OPCODE_aload, 1, "LoadÊreferenceÊfrom local variable");
		registrarOpCode("aload_0", OPCODE_aload_0, 0, "LoadÊreferenceÊfrom local variable index 0");
		registrarOpCode("aload_1", OPCODE_aload_1, 0, "LoadÊreferenceÊfrom local variable index 1");
		registrarOpCode("aload_2", OPCODE_aload_2, 0, "LoadÊreferenceÊfrom local variable index 2");
		registrarOpCode("aload_3", OPCODE_aload_3, 0, "LoadÊreferenceÊfrom local variable index 3");
		registrarOpCode("anewarray", OPCODE_anewarray, 2, "Create new array ofÊreference");
		registrarOpCode("areturn", OPCODE_areturn, 0, "ReturnÊreferenceÊfrom method");
		registrarOpCode("arraylength", OPCODE_arraylength, 0, "Get length of array");
		registrarOpCode("astore", OPCODE_astore, 1, "StoreÊreferenceÊinto local variable");
		registrarOpCode("astore_0", OPCODE_astore_0, 0, "Store reference into local variable index 0");
		registrarOpCode("astore_1", OPCODE_astore_1, 0, "StoreÊreferenceÊinto local variable index 1");
		registrarOpCode("astore_2", OPCODE_astore_2, 0, "Store reference into local variable index 2");
		registrarOpCode("astore_3", OPCODE_astore_3, 0, "Store reference into local variable index 3");
		registrarOpCode("athrow", OPCODE_athrow, 0, "Throw exception or error");
		registrarOpCode("baload", OPCODE_baload, 0, "LoadÊbyteÊorÊbooleanÊfrom array");
		registrarOpCode("bastore", OPCODE_bastore, 0, "Store intoÊbyteÊorÊbooleanÊarray");
		registrarOpCode("bipush", OPCODE_bipush, 1, "PushÊbyte");
		registrarOpCode("caload", OPCODE_caload, 0, "LoadÊcharÊfrom array");
		registrarOpCode("castore", OPCODE_castore, 0, "Store intoÊcharÊarray");
		registrarOpCode("checkcast", OPCODE_checkcast, 2, "Check whether object is of given type");
		registrarOpCode("d2f", OPCODE_d2f, 0, "ConvertÊdoubleÊtoÊfloat");
		registrarOpCode("d2i", OPCODE_d2i, 0, "ConvertÊdoubleÊtoÊint");
		registrarOpCode("d2l", OPCODE_d2l, 0, "ConvertÊdoubleÊtoÊlong");
		registrarOpCode("dadd", OPCODE_dadd, 0, "AddÊdouble");
		registrarOpCode("daload", OPCODE_daload, 0, "LoadÊdoubleÊfrom array");
		registrarOpCode("dastore", OPCODE_dastore, 0, "Store intoÊdoubleÊarray");
		registrarOpCode("dcmpg", OPCODE_dcmpg, 0, "CompareÊdouble");
		registrarOpCode("dcmpl", OPCODE_dcmpl, 0, "CompareÊdouble");
		registrarOpCode("dconst_0", OPCODE_dconst_0, 0, "PushÊdouble 0.0");
		registrarOpCode("dconst_1", OPCODE_dconst_1, 0, "PushÊdouble 1.0");
		registrarOpCode("ddiv", OPCODE_ddiv, 0, "DivideÊdouble");
		registrarOpCode("dload", OPCODE_dload, 1, "LoadÊdoubleÊfrom local variable");
		registrarOpCode("dload_0", OPCODE_dload_0, 0, "LoadÊdoubleÊfrom local variable");
		registrarOpCode("dload_1", OPCODE_dload_1, 0, "LoadÊdoubleÊfrom local variable");
		registrarOpCode("dload_2", OPCODE_dload_2, 0, "LoadÊdoubleÊfrom local variable");
		registrarOpCode("dload_3", OPCODE_dload_3, 0, "LoadÊdoubleÊfrom local variable");
		registrarOpCode("dmul", OPCODE_dmul, 0, "MultiplyÊdouble");
		registrarOpCode("dneg", OPCODE_dneg, 0, "NegateÊdouble");
		registrarOpCode("drem", OPCODE_drem, 0, "RemainderÊdouble");
		registrarOpCode("dreturn", OPCODE_dreturn, 0, "ReturnÊdoubleÊfrom method");
		registrarOpCode("dstore", OPCODE_dstore, 1, "StoreÊdoubleÊinto local variable");
		registrarOpCode("dstore_0", OPCODE_dstore_0, 0, "StoreÊdoubleÊinto local variable");
		registrarOpCode("dstore_1", OPCODE_dstore_1, 0, "StoreÊdoubleÊinto local variable");
		registrarOpCode("dstore_2", OPCODE_dstore_2, 0, "StoreÊdoubleÊinto local variable");
		registrarOpCode("dstore_3", OPCODE_dstore_3, 0, "StoreÊdoubleÊinto local variable");
		registrarOpCode("dsub", OPCODE_dsub, 0, "SubtractÊdouble");
		registrarOpCode("dup", OPCODE_dup, 0, "Duplicate the top operand stack value");
		registrarOpCode("dup_x1", OPCODE_dup_x1, 0, "Duplicate the top operand stack value and insert two values down");
		registrarOpCode("dup_x2", OPCODE_dup_x2, 0, "Duplicate the top operand stack value and insert two or three values down");
		registrarOpCode("dup2", OPCODE_dup2, 0, "Duplicate the top one or two operand stack values");
		registrarOpCode("dup2_x1", OPCODE_dup2_x1, 0, "Duplicate the top one or two operand stack values and insert two or three values down");
		registrarOpCode("dup2_x2", OPCODE_dup2_x2, 0, "Duplicate the top one or two operand stack values and insert two, three, or four values down");
		registrarOpCode("f2d", OPCODE_f2d, 0, "ConvertÊfloatÊtoÊdouble");
		registrarOpCode("f2i", OPCODE_f2i, 0, "ConvertÊfloatÊtoÊint");
		registrarOpCode("f2l", OPCODE_f2l, 0, "ConvertÊfloatÊtoÊlong");
		registrarOpCode("fadd", OPCODE_fadd, 0, "AddÊfloat");
		registrarOpCode("faload", OPCODE_faload, 0, "LoadÊfloatÊfrom array");
		registrarOpCode("fastore", OPCODE_fastore, 0, "Store intoÊfloatÊarray");
		registrarOpCode("fcmpg", OPCODE_fcmpg, 0, "CompareÊfloat");
		registrarOpCode("fcmpl", OPCODE_fcmpl, 0, "CompareÊfloat");
		registrarOpCode("fconst_0", OPCODE_fconst_0, 0, "PushÊfloat");
		registrarOpCode("fconst_1", OPCODE_fconst_1, 0, "PushÊfloat");
		registrarOpCode("fconst_2", OPCODE_fconst_2, 0, "PushÊfloat");
		registrarOpCode("fdiv", OPCODE_fdiv, 0, "DivideÊfloat");
		registrarOpCode("fload", OPCODE_fload, 1, "LoadÊfloatÊfrom local variable");
		registrarOpCode("fload_0", OPCODE_fload_0, 0, "LoadÊfloatÊfrom local variable");
		registrarOpCode("fload_1", OPCODE_fload_1, 0, "LoadÊfloatÊfrom local variable");
		registrarOpCode("fload_2", OPCODE_fload_2, 0, "LoadÊfloatÊfrom local variable");
		registrarOpCode("fload_3", OPCODE_fload_3, 0, "LoadÊfloatÊfrom local variable");
		registrarOpCode("fmul", OPCODE_fmul, 0, "MultiplyÊfloat");
		registrarOpCode("fneg", OPCODE_fneg, 0, "NegateÊfloat");
		registrarOpCode("frem", OPCODE_frem, 0, "RemainderÊfloat");
		registrarOpCode("freturn", OPCODE_freturn, 0, "ReturnÊfloatÊfrom method");
		registrarOpCode("fstore", OPCODE_fstore, 1, "StoreÊfloatÊinto local variable");
		registrarOpCode("fstore_0", OPCODE_fstore_0, 0, "StoreÊfloatÊinto local variable");
		registrarOpCode("fstore_1", OPCODE_fstore_1, 0, "StoreÊfloatÊinto local variable");
		registrarOpCode("fstore_2", OPCODE_fstore_2, 0, "StoreÊfloatÊinto local variable");
		registrarOpCode("fstore_3", OPCODE_fstore_3, 0, "StoreÊfloatÊinto local variable");
		registrarOpCode("fsub", OPCODE_fsub, 0, "SubtractÊfloat");
		registrarOpCode("getfield", OPCODE_getfield, 2, "Fetch field from object");
		registrarOpCode("getstatic", OPCODE_getstatic, 2, "GetÊstaticÊfield from class");
		registrarOpCode("goto", OPCODE_goto, 2, "Branch always");
		registrarOpCode("goto_w", OPCODE_goto_w, 4, "Branch always (wide index)");
		registrarOpCode("i2b", OPCODE_i2b, 0, "ConvertÊintÊtoÊbyte");
		registrarOpCode("i2c", OPCODE_i2c, 0, "ConvertÊintÊtoÊchar");
		registrarOpCode("i2d", OPCODE_i2d, 0, "ConvertÊintÊtoÊdouble");
		registrarOpCode("i2f", OPCODE_i2f, 0, "ConvertÊintÊtoÊfloat");
		registrarOpCode("i2l", OPCODE_i2l, 0, "ConvertÊintÊtoÊlong");
		registrarOpCode("i2s", OPCODE_i2s, 0, "ConvertÊintÊtoÊshort");
		registrarOpCode("iadd", OPCODE_iadd, 0, "AddÊint");
		registrarOpCode("iaload", OPCODE_iaload, 0, "LoadÊintÊfrom array");
		registrarOpCode("iand", OPCODE_iand, 0, "Boolean ANDÊint");
		registrarOpCode("iastore", OPCODE_iastore, 0, "Store intoÊintÊarray");
		registrarOpCode("iconst_m1", OPCODE_iconst_m1, 0, "PushÊintÊconstant");
		registrarOpCode("iconst_0", OPCODE_iconst_0, 0, "PushÊintÊconstant");
		registrarOpCode("iconst_1", OPCODE_iconst_1, 0, "PushÊintÊconstant");
		registrarOpCode("iconst_2", OPCODE_iconst_2, 0, "PushÊintÊconstant");
		registrarOpCode("iconst_3", OPCODE_iconst_3, 0, "PushÊintÊconstant");
		registrarOpCode("iconst_4", OPCODE_iconst_4, 0, "PushÊintÊconstant");
		registrarOpCode("iconst_5", OPCODE_iconst_5, 0, "PushÊintÊconstant");
		registrarOpCode("idiv", OPCODE_idiv, 0, "DivideÊint");
		registrarOpCode("if_acmpeq", OPCODE_if_acmpeq, 2, "Branch ifÊreferenceÊcomparison succeeds");
		registrarOpCode("if_acmpne", OPCODE_if_acmpne, 2, "Branch ifÊreferenceÊcomparison succeeds");
		registrarOpCode("if_icmpeq", OPCODE_if_icmpeq, 2, "Branch ifÊintÊcomparison succeeds");
		registrarOpCode("if_icmpne", OPCODE_if_icmpne, 2, "Branch ifÊintÊcomparison succeeds");
		registrarOpCode("if_icmplt", OPCODE_if_icmplt, 2, "Branch ifÊintÊcomparison succeeds");
		registrarOpCode("if_icmpge", OPCODE_if_icmpge, 2, "Branch ifÊintÊcomparison succeeds");
		registrarOpCode("if_icmpgt", OPCODE_if_icmpgt, 2, "Branch ifÊintÊcomparison succeeds");
		registrarOpCode("if_icmple", OPCODE_if_icmple, 2, "Branch ifÊintÊcomparison succeeds");
		registrarOpCode("ifeq", OPCODE_ifeq, 2, "Branch ifÊintÊcomparison with zero succeeds");
		registrarOpCode("ifne", OPCODE_ifne, 2, "Branch ifÊintÊcomparison with zero succeeds");
		registrarOpCode("iflt", OPCODE_iflt, 2, "Branch ifÊintÊcomparison with zero succeeds");
		registrarOpCode("ifge", OPCODE_ifge, 2, "Branch ifÊintÊcomparison with zero succeeds");
		registrarOpCode("ifgt", OPCODE_ifgt, 2, "Branch ifÊintÊcomparison with zero succeeds");
		registrarOpCode("ifle", OPCODE_ifle, 2, "Branch ifÊintÊcomparison with zero succeeds");
		registrarOpCode("ifnonnull", OPCODE_ifnonnull, 2, "Branch ifÊreferenceÊnotÊnull");
		registrarOpCode("ifnull", OPCODE_ifnull, 2, "Branch ifÊreferenceÊisÊnull");
		registrarOpCode("iinc", OPCODE_iinc, 2, "Increment local variable by constant");
		registrarOpCode("iload", OPCODE_iload, 1, "LoadÊintÊfrom local variable");
		registrarOpCode("iload_0", OPCODE_iload_0, 0, "LoadÊintÊfrom local variable");
		registrarOpCode("iload_1", OPCODE_iload_1, 0, "LoadÊintÊfrom local variable");
		registrarOpCode("iload_2", OPCODE_iload_2, 0, "LoadÊintÊfrom local variable");
		registrarOpCode("iload_3", OPCODE_iload_3, 0, "LoadÊintÊfrom local variable");
		registrarOpCode("imul", OPCODE_imul, 0, "MultiplyÊint");
		registrarOpCode("ineg", OPCODE_ineg, 0, "NegateÊint");
		registrarOpCode("instanceof", OPCODE_instanceof, 0, "Determine if object is of given type");
		registrarOpCode("invokeinterface", OPCODE_invokeinterface, 4, "Invoke interface method");
		registrarOpCode("invokespecial", OPCODE_invokespecial, 2, "InvokeÊinstance method; special handling for superclass, private, and instance initialization method invocations");
		registrarOpCode("invokestatic", OPCODE_invokestatic, 2, "Invoke a class (static) method");
		registrarOpCode("invokevirtual", OPCODE_invokevirtual, 2, "Invoke instance method; dispatch based on class");
		registrarOpCode("ior", OPCODE_ior, 0, "Boolean ORÊint");
		registrarOpCode("irem", OPCODE_irem, 0, "RemainderÊint");
		registrarOpCode("ireturn", OPCODE_ireturn, 0, "ReturnÊintÊfrom method");
		registrarOpCode("ishl", OPCODE_ishl, 0, "Shift leftÊint");
		registrarOpCode("ishr", OPCODE_ishr, 0, "Arithmetic shift rightÊint");
		registrarOpCode("istore", OPCODE_istore, 1, "StoreÊintÊinto local variable");
		registrarOpCode("istore_0", OPCODE_istore_0, 0, "StoreÊintÊinto local variable");
		registrarOpCode("istore_1", OPCODE_istore_1, 0, "StoreÊintÊinto local variable");
		registrarOpCode("istore_2", OPCODE_istore_2, 0, "StoreÊintÊinto local variable");
		registrarOpCode("istore_3", OPCODE_istore_3, 0, "StoreÊintÊinto local variable");
		registrarOpCode("isub", OPCODE_isub, 0, "SubtractÊint");
		registrarOpCode("iushr", OPCODE_iushr, 0, "Logical shift rightÊint");
		registrarOpCode("ixor", OPCODE_ixor, 0, "Boolean XORÊint");
		registrarOpCode("jsr", OPCODE_jsr, 2, "Jump subroutine");
		registrarOpCode("jsr_w", OPCODE_jsr_w, 4, "Jump subroutine (wide index)");
		registrarOpCode("l2d", OPCODE_l2d, 0, "ConvertÊlongÊtoÊdouble");
		registrarOpCode("l2f", OPCODE_l2f, 0, "ConvertÊlongÊtoÊfloat");
		registrarOpCode("l2i", OPCODE_l2i, 0, "ConvertÊlongÊtoÊint");
		registrarOpCode("ladd", OPCODE_ladd, 0, "AddÊlong");
		registrarOpCode("laload", OPCODE_laload, 0, "LoadÊlongÊfrom array");
		registrarOpCode("land", OPCODE_land, 0, "Boolean ANDÊlong");
		registrarOpCode("lastore", OPCODE_lastore, 0, "Store intoÊlongÊarray");
		registrarOpCode("lcmp", OPCODE_lcmp, 0, "CompareÊlong");
		registrarOpCode("lconst_0", OPCODE_lconst_0, 0, "PushÊlongÊconstant");
		registrarOpCode("lconst_1", OPCODE_lconst_1, 0, "PushÊlongÊconstant");
		registrarOpCode("ldc", OPCODE_ldc, 1, "Push item from runtime constant pool");
		registrarOpCode("ldc_w", OPCODE_ldc_w, 2, "Push item from runtime constant pool (wide index)");
		registrarOpCode("ldc2_w", OPCODE_ldc2_w, 2, "PushÊlongÊorÊdoubleÊfrom runtime constant pool (wide index)");
		registrarOpCode("ldiv", OPCODE_ldiv, 0, "DivideÊlong");
		registrarOpCode("lload", OPCODE_lload, 1, "LoadÊlongÊfrom local variable");
		registrarOpCode("lload_0", OPCODE_lload_0, 0, "LoadÊlongÊfrom local variable");
		registrarOpCode("lload_1", OPCODE_lload_1, 0, "LoadÊlongÊfrom local variable");
		registrarOpCode("lload_2", OPCODE_lload_2, 0, "LoadÊlongÊfrom local variable");
		registrarOpCode("lload_3", OPCODE_lload_3, 0, "LoadÊlongÊfrom local variable");
		registrarOpCode("lmul", OPCODE_lmul, 0, "MultiplyÊlong");
		registrarOpCode("lneg", OPCODE_lneg, 0, "NegateÊlong");
		registrarOpCode("lookupswitch", OPCODE_lookupswitch, -1, "Access jump table by key match and jump"); //Variable
		registrarOpCode("lor", OPCODE_lor, 0, "Boolean ORÊlong");
		registrarOpCode("lrem", OPCODE_lrem, 0, "RemainderÊlong");
		registrarOpCode("lreturn", OPCODE_lreturn, 0, "ReturnÊlongÊfrom method");
		registrarOpCode("lshl", OPCODE_lshl, 0, "Shift left");
		registrarOpCode("lshr", OPCODE_lshr, 0, "Arithmetic shift rightÊlong");
		registrarOpCode("lstore", OPCODE_lstore, 1, "StoreÊlongÊinto local variable");
		registrarOpCode("lstore_0", OPCODE_lstore_0, 0, "StoreÊlongÊinto local variable");
		registrarOpCode("lstore_1", OPCODE_lstore_1, 0, "StoreÊlongÊinto local variable");
		registrarOpCode("lstore_2", OPCODE_lstore_2, 0, "StoreÊlongÊinto local variable");
		registrarOpCode("lstore_3", OPCODE_lstore_3, 0, "StoreÊlongÊinto local variable");
		registrarOpCode("lsub", OPCODE_lsub, 0, "SubtractÊlong");
		registrarOpCode("lushr", OPCODE_lushr, 0, "Logical shift rightÊlong");
		registrarOpCode("lxor", OPCODE_lxor, 0, "Boolean XORÊlong");
		registrarOpCode("monitorenter", OPCODE_monitorenter, 0, "Enter monitor for object");
		registrarOpCode("monitorexit", OPCODE_monitorexit, 0, "Exit monitor for object");
		registrarOpCode("multianewarray", OPCODE_multianewarray, 3, "Create new multidimensional array");
		registrarOpCode("new", OPCODE_new, 2, "Create new object");
		registrarOpCode("newarray", OPCODE_newarray, 1, "Create new array");
		registrarOpCode("nop", OPCODE_nop, 0, "Do nothing");
		registrarOpCode("pop", OPCODE_pop, 0, "Pop the top operand stack value");
		registrarOpCode("pop2", OPCODE_pop2, 0, "Pop the top one or two operand stack values");
		registrarOpCode("putfield", OPCODE_putfield, 2, "Set field in object");
		registrarOpCode("putstatic", OPCODE_putstatic, 2, "SetÊstaticÊfield in class");
		registrarOpCode("ret", OPCODE_ret, 1, "Return from subroutine");
		registrarOpCode("return", OPCODE_return, 0, "ReturnÊvoidÊfrom method");
		registrarOpCode("saload", OPCODE_saload, 0, "LoadÊshortÊfrom array");
		registrarOpCode("sastore", OPCODE_sastore, 0, "Store intoÊshortÊarray");
		registrarOpCode("sipush", OPCODE_sipush, 2, "PushÊshort");
		registrarOpCode("swap", OPCODE_swap, 0, "Swap the top two operand stack values");
		registrarOpCode("tableswitch", OPCODE_tableswitch, -1, "Access jump table by index and jump"); //Variable
		registrarOpCode("wide", OPCODE_wide, -1, "Extend local variable index by additional bytes"); //Variable
		//Por curiosidad, investigar los opCodes no utilizados
		int iCod, codIniHueco = -1;
		for(iCod=0; iCod<256; iCod++){ //Todos los posibles valores
			boolean encontrado = false;
			int i;
			for(i=0; i<_usoBibOpCodes; i++){
				if(_bibOpCodes[i].valorOpCode()==iCod){
					encontrado = true; break;
				}
			}
			if(encontrado){
				if(codIniHueco!=-1){
					int tamanoHueco = iCod - codIniHueco; assert tamanoHueco>0;
					if(tamanoHueco==1) Log.informativo("Opcode no ocupado: " + codIniHueco);
					else Log.informativo("Opcodes no ocupados " + codIniHueco + " -> " + (iCod - 1));
					codIniHueco = -1;
				}
			} else if(codIniHueco==-1){
				codIniHueco = iCod;
			}
		}
		//Hueco final
		if(codIniHueco!=-1){
			int tamanoHueco = iCod - codIniHueco; assert tamanoHueco>0;
			if(tamanoHueco==1) Log.informativo("Opcode no ocupado: " + codIniHueco);
			else Log.informativo("Opcodes no ocupados " + codIniHueco + " -> " + (iCod - 1));
		}
		Log.informativo("... biblioteca de OpCodes inicializada.");
	}
	
}
