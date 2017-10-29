package net.pop1040.ProgrammingLanguage.Types;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;

public class PArray extends PObject {
	
	public static PClass pClass = new PClass("[", 1);
	static{
		pClass.fieldNames.add("length");
		ExecutionEngine.baseClasses.add(pClass);
	}
	
	public PGeneric[] value;
	
	public PArray(PClass clazz, int length) {
		super(PArray.pClass, clazz);
		value = new PGeneric[length];
		fields.add(new PInteger(value.length));
		fieldMap.put("length", fields.get(0));
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder("Array[");
		for(String s:pClass.fieldNames)buf.append(s + " = " + String.valueOf(fieldMap.get(s)) + ", ");
		//if(!pClass.fieldNames.isEmpty())buf.setLength(buf.length() - 2);
		buf.append("values = {");
		for(PGeneric g : value)buf.append(g + ", ");
		if(value.length > 0)buf.setLength(buf.length() - 2);
		buf.append("}]");
		return buf.toString();
	}
	

}
