package net.pop1040.ProgrammingLanguage.Types;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;

public class PFloat extends PPrimitive {

	public static PClass pClass = new PClass("float");
	static{
		ExecutionEngine.baseClasses.add(pClass);
	}
	
	public float value;
	
	public PFloat(float value) {
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
		return "Float[value=" + value + "]";
	}

	@Override
	public char getCharValue() {
		return (char)value;
	}

	@Override
	public boolean getBooleanValue() {
		return value!=0f;
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
		return (long) value;
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
