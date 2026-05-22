public class Snippet__5684516 {

    protected List<String> getSelectionsInContext(String label, Collection<String> allEntities, String context) {
            List<String> allEntityString = new ArrayList<String>();

            for (String selectedText : allEntities) {
                if (context.contains(selectedText) && selectedText.compareToIgnoreCase(label) != 0) {
                    allEntityString.add(selectedText);
                }

            }

            return allEntityString;
        }

}
