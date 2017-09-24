package net.pop1040.ProgrammingLanguage.Types;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;

public class PChar extends PPrimitive {

	public static PClass pClass = new PClass("char");
	static{
		ExecutionEngine.baseClasses.add(pClass);
	}
	
	public char value;
	
	public PChar(char value) {
		this.value=value;
	}
	
	@Override
	public String getInstanceName() {
		return null;
	}

	@Override
	public PClass getPClass() {
		return pClass;
	}
	
	@Override
	public String toString() {
		return "Char[value=" + value + "]";
	}

	@Override
	public char getCharValue() {
		return value;
	}

	@Override
	public boolean getBooleanValue() {
		return value!=0;
	}

	@Override
	public byte getByteValue() {
		return (byte) value;
	}

	@Override
	public short getShortValue() {
		return (short)value;
	}

	@Override
	public int getIntValue() {
		return this.getShortValue();
	}

	@Override
	public long getLongValue() {
		return this.getShortValue();
	}

	@Override
	public float getFloatValue() {
		return this.getShortValue();
	}

	@Override
	public double getDoubleValue() {
		return this.getByteValue();
	}

}
