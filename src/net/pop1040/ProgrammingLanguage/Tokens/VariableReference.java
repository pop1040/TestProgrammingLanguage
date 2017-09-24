package net.pop1040.ProgrammingLanguage.Tokens;

import net.pop1040.ProgrammingLanguage.Types.PClass;

public class VariableReference{
	
	public static enum Type{
		LOCAL,
		FIELD
	}
	
	public Type mode;
	public String name;
	public Evaluatable object;
	public PClass type;
	
	public VariableReference(String name, PClass type){
		this.mode=Type.LOCAL;
		this.name=name;
		this.type=type;
	}
	
	public VariableReference(String name, PClass type, Evaluatable object){
		this.mode=Type.FIELD;
		this.name=name;
		this.type=type;
		this.object=object;
	}
	
	@Override
	public String toString() {
		return "VarRef[mode=" + mode.name() + ", name=" + name + ", type=" + type + (mode==Type.FIELD?", object=" + object:"") + "]";
	}
	
}
