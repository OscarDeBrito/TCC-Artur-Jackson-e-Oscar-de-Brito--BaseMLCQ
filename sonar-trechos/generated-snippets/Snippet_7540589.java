public static class RewriteResult implements Constants {

		public final byte[] bytes;

		// These bits describe which kinds of reflective things were done in the 
		// type - and so which fields (of the __sl variety) need filling in.  For example,
		// if the JLC_GETDECLAREDFIELDS bit is set, the field __sljlcgdfs must be set
		public final int bits;

		public RewriteResult(byte[] bytes, int bits) {
			this.bytes = bytes;
			this.bits = bits;
		}

		public String summarize() {
			StringBuilder s = new StringBuilder();
			s.append((bits & JLC_GETDECLAREDCONSTRUCTORS) != 0 ? "Class.getDeclaredConstructors()" : "");
			s.append((bits & JLC_GETDECLAREDCONSTRUCTOR) != 0 ? "Class.getDeclaredConstructor()" : "");
			s.append((bits & JLC_GETCONSTRUCTOR) != 0 ? "Class.getConstructor()" : "");
			s.append((bits & JLC_GETMODIFIERS) != 0 ? "Class.getModifiers()" : "");
			s.append((bits & JLC_GETDECLAREDFIELDS) != 0 ? "Class.getDeclaredFields() " : "");
			s.append((bits & JLC_GETDECLAREDFIELD) != 0 ? "Class.getDeclaredField() " : "");
			s.append((bits & JLC_GETFIELD) != 0 ? "Class.getField() " : "");
			s.append((bits & JLC_GETDECLAREDMETHODS) != 0 ? "Class.getDeclaredMethods() " : "");
			s.append((bits & JLC_GETDECLAREDMETHOD) != 0 ? "Class.getDeclaredMethod() " : "");
			s.append((bits & JLC_GETMETHOD) != 0 ? "Class.getMethod() " : "");
			s.append((bits & JLC_GETMETHODS) != 0 ? "Class.getMethods() " : "");
			s.append((bits & JLRM_INVOKE) != 0 ? "Method.invoke() " : "");
			s.append((bits & JLRF_GET) != 0 ? "Field.get() " : "");
			s.append((bits & JLRF_GETLONG) != 0 ? "Field.getLong() " : "");
			s.append((bits & JLOS_HASSTATICINITIALIZER) != 0 ? "jlObjectStream.hasStaticInitializer() " : "");
			return s.toString().trim();
		}
	}
