public class Snippet__6248023 {

    private Pattern getPatternFindAnyCharacter() {
            final String regexCharacters = characters.stream().map(RegexChar::getRegExp)
                    .collect(Collectors.joining());
            final String regularExpression = String.format(".*[%s]+.*", regexCharacters);
            return Pattern.compile(regularExpression);
        }

}
