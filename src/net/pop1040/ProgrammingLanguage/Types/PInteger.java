package net.pop1040.ProgrammingLanguage.Types;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;

public class PInteger extends PPrimitive {

	public static PClass pClass = new PClass("int");
	static{
		ExecutionEngine.baseClasses.add(pClass);
	}
	
	public int value;
	
	public PInteger(int value) {
		this.value=value;
	}
	

	@Override
	public PClass getPClass() {
		return pClass;
	}
	
	@Override
	public String toString() {
		return "Integer[value=" + value + "]";
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
		return (short)value;
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
