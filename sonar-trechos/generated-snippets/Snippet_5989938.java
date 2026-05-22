public class Snippet__5989938 {

    protected MethodSpec.Builder resumeMethodBuilder() {
            return MethodSpec.methodBuilder(RESUME_METHOD)
                             .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                             .addParameter(responseType(), LAST_SUCCESSFUL_PAGE_LITERAL)
                             .returns(className())
                             .addCode(CodeBlock.builder()
                                               .beginControlFlow("if ($L.$L($L))", NEXT_PAGE_FETCHER_MEMBER,
                                                                 HAS_NEXT_PAGE_METHOD, LAST_SUCCESSFUL_PAGE_LITERAL)
                                               .addStatement("return new $T($L, $L)", className(), CLIENT_MEMBER,
                                                             constructRequestFromLastPage(LAST_SUCCESSFUL_PAGE_LITERAL))
                                               .endControlFlow()
                                               .build())
                             .addJavadoc(CodeBlock.builder()
                                                  .add("<p>A helper method to resume the pages in case of unexpected failures. "
                                                       + "The method takes the last successful response page as input and returns an "
                                                       + "instance of {@link $T} that can be used to retrieve the consecutive pages "
                                                       + "that follows the input page.</p>", className())
                                                  .build());
        }

}
