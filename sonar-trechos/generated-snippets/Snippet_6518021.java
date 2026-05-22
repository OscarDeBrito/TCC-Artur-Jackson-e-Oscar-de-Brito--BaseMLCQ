protected class MappingRuleHandler extends AbstractHandler {

            private final String[] required = new String[] { MAPPING_RULE_FILTER_ATTRIBUTE,
                    MAPPING_RULE_OUTPUT_ATTRIBUTE };

            public MappingRuleHandler(AbstractHandler parentHandler, Attributes attributes, List mappingRules) {
                super(parentHandler, MAPPING_RULE_ELEMENT);
                mappingRules.add(parseRequiredAttributes(attributes, required));
            }

            @Override
            public void startElement(String name, Attributes attributes) {
                invalidElement(name, attributes);
            }
        }
