package net.pop1040.ProgrammingLanguage.Types;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;

public class PDouble extends PPrimitive {

	public static PClass pClass = new PClass("double");
	static{
		ExecutionEngine.baseClasses.add(pClass);
	}
	
	public double value;
	
	public PDouble(double value) {
		this.value=value;
	}

	@Override
	public PClass getPClass() {
		return pClass;
	}
	
	@Override
	public String toString() {
		return "Double[value=" + value + "]";
	}

	@Override
	public char getCharValue() {
		return (char)value;
	}

	@Override
	public boolean getBooleanValue() {
		return value!=0d;
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
		return (int)value;
	}

	@Override
	public long getLongValue() {
		return (long)value;
	}

	@Override
	public float getFloatValue() {
		return (float)value;
	}

	@Override
	public double getDoubleValue() {
		return value;
	}

}
