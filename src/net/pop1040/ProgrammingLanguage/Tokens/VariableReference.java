package net.pop1040.ProgrammingLanguage.Tokens;

import net.pop1040.ProgrammingLanguage.Types.PClass;

public class VariableReference{
	
	public static enum Type{
		LOCAL,
		FIELD,
		ARRAY
	}
	
	public Type mode;
	public String name;
	public Evaluatable object;
	public Evaluatable index;
	public PClass type;
	/**
	 * Variable Reference for LOCAL
	 * @param name
	 * @param type
	 * @param object
	 */
	public VariableReference(String name, PClass type){
		this.mode=Type.LOCAL;
		this.name=name;
		this.type=type;
	}
	
	/**
	 * Variable Reference for FIELD
	 * @param name
	 * @param type
	 * @param object
	 */
	public VariableReference(String name, PClass type, Evaluatable object){
		this.mode=Type.FIELD;
		this.name=name;
		this.type=type;
		this.object=object;
	}
	
	/**
	 * Variable Reference for FIELD
	 * @param name
	 * @param type
	 * @param object
	 */
	public VariableReference(PClass type, Evaluatable object, Evaluatable index){
		this.mode=Type.ARRAY;
		this.type=type;
		this.object=object;
		this.index=index;
	}
	
	@Override
	public String toString() {
		return "VarRef[mode=" + mode.name() + (mode==Type.ARRAY?"":", name=" + name) + ", type=" + type + (mode==Type.FIELD || mode==Type.ARRAY?", object=" + object:"") + (mode==Type.ARRAY?", index=" + index:"") + "]";
	}
	
}
