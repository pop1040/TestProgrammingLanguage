package net.pop1040.ProgrammingLanguage.Types;

import net.pop1040.ProgrammingLanguage.ExecutionEngine;

public class PArray extends PObject {
	
	public static PClass pClass = new PClass("[", 1);
	static{
		pClass.fieldNames.add("length");
		ExecutionEngine.baseClasses.add(pClass);
	}
	
	public PGeneric[] value;
	
	public PArray(PClass pClass, int length) {
		super(pClass, pClass);
		value = new PGeneric[length];
		fields.add(new PInteger(value.length));
		fieldMap.put("length", fields.get(0));
	}
	
	

}
