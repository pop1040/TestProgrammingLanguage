package net.pop1040.ProgrammingLanguage.Types;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;

public class PBoolean extends PPrimitive{
	
	public boolean value;
	public static PClass pClass = new PClass("boolean");
	static{
		ExecutionEngine.baseClasses.add(pClass);
	}
	
	public PBoolean(boolean value){
		this.value=value;
	}
	
	public PBoolean(PBoolean clone){
		this.value=clone.value;
	}

	@Override
	public PClass getPClass() {
		return pClass;
	}

	@Override
	public char getCharValue() {
		return value?'T':'F';
	}

	@Override
	public boolean getBooleanValue() {
		return value;
	}

	@Override
	public byte getByteValue() {
		return (byte) (value?1:0);
	}

	@Override
	public short getShortValue() {
		return this.getByteValue();
	}

	@Override
	public int getIntValue() {
		return this.getByteValue();
	}

	@Override
	public long getLongValue() {
		return this.getByteValue();
	}

	@Override
	public float getFloatValue() {
		return this.getByteValue();
	}

	@Override
	public double getDoubleValue() {
		return this.getByteValue();
	}
	
	@Override
	public String toString() {
		return "Boolean[value=" + value + "]";
	}

}
