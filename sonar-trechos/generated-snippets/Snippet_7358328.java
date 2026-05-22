public class Snippet__7358328 {

    public void test20() {
            final SparseTreeAdditionalData additionalData = new SparseTreeAdditionalData();
            final SparseTree<String> st = new SparseTree<String>('/', String.CASE_INSENSITIVE_ORDER);

            st.add("$", "Root"); //$NON-NLS-1$ //$NON-NLS-2$
            st.add("$/A", "A"); //$NON-NLS-1$ //$NON-NLS-2$
            st.add("$/A/Z", "AZ"); //$NON-NLS-1$ //$NON-NLS-2$

            final List<String> results = new ArrayList<String>();
            st.EnumParents("$/A/B/C/D/E", new NoChildrenBelowCallback(), EnumParentsOptions.NONE, additionalData, results); //$NON-NLS-1$

            final List<String> expectedResults = new ArrayList<String>();
            expectedResults.add("$/A/B"); //$NON-NLS-1$
            expectedResults.add(null);

            Assert.assertTrue(areStringListsEqual(expectedResults, results, String.CASE_INSENSITIVE_ORDER));
        }

}
