package net.pop1040.ProgrammingLanguage.Types;

import java.util.ArrayList;
import java.util.HashMap;

public class PObject extends PGeneric {
	
	private PClass pClass;
	
	public PObject(PClass pClass) {
		this.pClass=pClass;
	}
	

	public ArrayList<PGeneric> fields = new ArrayList<PGeneric>();
	public HashMap<String, PGeneric> fieldMap = new HashMap<String, PGeneric>();

	@Override
	public boolean isPrimitive() {
		return false;
	}

	@Override
	public PClass getPClass() {
		return pClass;
	}
	
	public HashMap<String, PGeneric> getFieldMap(){
		return fieldMap;
	}
	
	public ArrayList<PGeneric> getFields(){
		return fields;
	}
	
	public PGeneric[] getFieldArray(){
		return getFields().toArray(new PGeneric[getFields().size()]);
	}

	@Override
	public String getInstanceName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder("Object[");
		for(String s:pClass.fieldNames)buf.append(s + " = " + String.valueOf(fieldMap.get(s)) + ", ");
		if(!pClass.fieldNames.isEmpty())buf.setLength(buf.length() - 2);
		buf.append("]");
		return buf.toString();
	}
	
}
