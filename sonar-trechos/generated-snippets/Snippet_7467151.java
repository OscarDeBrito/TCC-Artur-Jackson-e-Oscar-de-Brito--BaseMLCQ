public class Snippet__7467151 {

    private static List<Parameter> parseParametersOptional(MethodSpec spec, List<? extends VariableElement> types) {
            List<Parameter> parsedParams = new ArrayList<>();

            int typeStartIndex = 0;
            List<ParameterSpec> specifications = spec.getOptional();
            outer: for (int specIndex = 0; specIndex < specifications.size(); specIndex++) {
                ParameterSpec specification = specifications.get(specIndex);
                for (int typeIndex = typeStartIndex; typeIndex < types.size(); typeIndex++) {
                    VariableElement variable = types.get(typeIndex);
                    Parameter optionalParam = matchParameter(specification, variable, -1, -1);
                    if (optionalParam != null) {
                        parsedParams.add(optionalParam);
                        typeStartIndex = typeIndex + 1;
                        continue outer;
                    }
                }
            }

            if (typeStartIndex < types.size()) {
                // not enough types found
                return null;
            }
            return parsedParams;
        }

}
