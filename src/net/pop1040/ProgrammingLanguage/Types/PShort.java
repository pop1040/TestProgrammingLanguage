package net.pop1040.ProgrammingLanguage.Types;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;

public class PShort extends PPrimitive {

	public static PClass pClass = new PClass("short");
	static{
		ExecutionEngine.baseClasses.add(pClass);
	}
	
	public short value;
	
	public PShort(short value) {
		this.value=value;
	}
	
	@Override
	public PClass getPClass() {
		return pClass;
	}
	
	@Override
	public String toString() {
		return "Short[value=" + value + "]";
	}

	@Override
	public char getCharValue() {
		return (char) value;
	}

	@Override
	public boolean getBooleanValue() {
		return value!=0;
	}

	@Override
	public byte getByteValue() {
		return (byte)value;
	}

	@Override
	public short getShortValue() {
		return value;
	}

	@Override
	public int getIntValue() {
		return value;
	}

	@Override
	public long getLongValue() {
		return value;
	}

	@Override
	public float getFloatValue() {
		return value;
	}

	@Override
	public double getDoubleValue() {
		return value;
	}

}
