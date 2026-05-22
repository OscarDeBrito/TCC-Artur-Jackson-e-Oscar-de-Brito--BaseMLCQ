final class WordListRandomWords implements RandomWords {

    private static final Random RANDOM = new SecureRandom();

    @Override
    public String getAdjective() {
        return Adjectives.WORDS.get(RANDOM.nextInt(Adjectives.WORDS.size()));
    }

    @Override
    public String getNoun() {
        return Nouns.WORDS.get(RANDOM.nextInt(Nouns.WORDS.size()));
    }

    private static BufferedReader getReader(String resourceName) {
        InputStream inputStream = WordListRandomWords.class.getClassLoader().getResourceAsStream(resourceName);
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private static List<String> getWordList(String resourceName) {
        try (Stream<String> stream = getReader(resourceName).lines()) {
            return stream
                .map(String::trim)
                .collect(Collectors.toList());
        }
    }

    private static final class Adjectives {

        private static List<String> WORDS = getWordList("adjectives.txt");

    }

    private static final class Nouns {

        private static List<String> WORDS = getWordList("nouns.txt");

    }

}
