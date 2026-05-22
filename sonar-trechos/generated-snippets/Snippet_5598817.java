public final class TestCruddyExtractor extends TestCase {
	// Extractor primed on the test data
	private final QuickButCruddyTextExtractor te;
	// All the text to be found in the file
	String[] allTheText = new String[] {
		"This is a test title",
		"This is a test subtitle\nThis is on page 1",
		"Click to edit Master title style",
		"Click to edit Master text styles\nSecond level\nThird level\nFourth level\nFifth level",
		"*",
		"*",
		"*",
		"*",
		"*",
		"Click to edit Master text styles\nSecond level\nThird level\nFourth level\nFifth level",
		"*",
		"*",
		"These are the notes for page 1",
		"This is a test title",
		"This is a test subtitle\nThis is on page 1",
		"This is the title on page 2",
		"This is page two\nIt has several blocks of text\nNone of them have formattingT",
		"These are the notes on page two, again lacking formatting",
		"This is a test title",
		"This is a test subtitle\nThis is on page 1",
		"This is the title on page 2",
		"This is page two\nIt has several blocks of text\nNone of them have formatting",
	};

    public TestCruddyExtractor() throws Exception {
        POIDataSamples slTests = POIDataSamples.getSlideShowInstance();
		te = new QuickButCruddyTextExtractor(slTests.openResourceAsStream("basic_test_ppt_file.ppt"));
    }

    public void testReadAsVector() {
		// Extract the text from the file as a vector
		List<String> foundTextV = te.getTextAsVector();

		// Ensure they match
		assertEquals(allTheText.length,foundTextV.size());
		for(int i=0; i<allTheText.length; i++) {
			String foundText = foundTextV.get(i);
			assertEquals(allTheText[i],foundText);
		}
	}

	public void testReadAsString() {
		// Extract the text as a String
		String foundText = te.getTextAsString();

		// Turn the string array into a single string
		String expectText = StringUtil.join(allTheText, "\n") + "\n";

		// Ensure they match
		assertEquals(expectText,foundText);
	}
}
