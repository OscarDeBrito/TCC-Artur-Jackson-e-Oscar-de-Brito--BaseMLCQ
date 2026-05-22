public class Snippet__7694897 {

    abstract public class OneArgFunction extends LibFunction {

    	abstract public LuaValue call(LuaValue arg);
	
    	/** Default constructor */
    	public OneArgFunction() {
    	}
		
    	public final LuaValue call() {
    		return call(NIL);
    	}

    	public final LuaValue call(LuaValue arg1, LuaValue arg2) {
    		return call(arg1);
    	}

    	public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3) {
    		return call(arg1);
    	}

    	public Varargs invoke(Varargs varargs) {
    		return call(varargs.arg1());
    	}
    }

}
