package net.pop1040.ProgrammingLanguage.Types;

public abstract class PPrimitive extends PGeneric {
	
	@Override
	public boolean isPrimitive() {
		return true;
	}
	
	public abstract char getCharValue();
	
	public abstract boolean getBooleanValue();
	
	public abstract byte getByteValue();
	
	public abstract short getShortValue();
	
	public abstract int getIntValue();
	
	public abstract long getLongValue();
	
	public abstract float getFloatValue();
	
	public abstract double getDoubleValue();
}
