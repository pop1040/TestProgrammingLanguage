package net.pop1040.ProgrammingLanguage.Types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PObject extends PGeneric {
	
	private PClass pClass;
	public ArrayList<PClass> generics = new ArrayList<PClass>();
	
	public PObject(PClass pClass, PClass ... generics) {
		this.pClass=pClass;
		if(pClass.hasGenerics()){
			if(generics == null)throw new IllegalArgumentException("class " + pClass.typeName + " requires generics");
			if(generics.length == 0)throw new IllegalArgumentException("class " + pClass.typeName + " requires generics");
			if(generics.length != pClass.generics.size())throw new IllegalArgumentException("class " + pClass.typeName + " requires " + pClass.generics.size() + " generic class" + (pClass.generics.size()>1?"es":"") + " but " + generics.length + " were supplied");
			this.generics.addAll(Arrays.asList(generics));
		}else if(generics != null && generics.length > 0)throw new IllegalArgumentException("class " + pClass.typeName + " does not use generics");
	}
	
	protected PObject(){
		this.pClass = PClass.pClass;
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
	public String toString() {
		StringBuilder buf = new StringBuilder("Object[");
		for(String s:pClass.fieldNames)buf.append(s + " = " + String.valueOf(fieldMap.get(s)) + ", ");
		if(!pClass.fieldNames.isEmpty())buf.setLength(buf.length() - 2);
		buf.append("]");
		return buf.toString();
	}
	
}
