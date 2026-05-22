public class Snippet__7525975 {

    String toString(boolean appended) {

    		if (variables.isEmpty()) {
    			return "";
    		}

    		StringBuilder builder = new StringBuilder();
    		TemplateVariable previous = null;

    		for (TemplateVariable variable : variables) {

    			VariableType type = variable.getType();
    			type = appended && type.equals(REQUEST_PARAM) ? REQUEST_PARAM_CONTINUED : type;

    			if (previous == null) {
    				builder.append("{").append(type.toString());
    			} else if (!previous.isCombinable(variable)) {
    				builder.append("}{").append(type.toString());
    			} else {
    				builder.append(",");
    			}

    			previous = variable;
    			builder.append(variable.getName());
    		}

    		return builder.append("}").toString();
    	}

}
