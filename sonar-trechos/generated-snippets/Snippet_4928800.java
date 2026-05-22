public interface TreeState {

    /**
     * Restore tree expanded and selected state
     *
     * @param guiInstance GuiPackage to be used
     */
    void restore(GuiPackage guiInstance);

    static final TreeState NOTHING = (GuiPackage guiInstance) -> {};

    /**
     * Save tree expanded and selected state
     *
     * @param guiPackage {@link GuiPackage} to be used
     * @return {@link TreeState}
     */
    public static TreeState from(GuiPackage guiPackage) {
        if (guiPackage == null) {
            return NOTHING;
        }

        MainFrame mainframe = guiPackage.getMainFrame();
        if (mainframe != null) {
            final JTree tree = mainframe.getTree();
            int savedSelected = tree.getMinSelectionRow();
            ArrayList<Integer> savedExpanded = new ArrayList<>();

            for (int rowN = 0; rowN < tree.getRowCount(); rowN++) {
                if (tree.isExpanded(rowN)) {
                    savedExpanded.add(rowN);
                }
            }

            return new TreeStateImpl(savedSelected, savedExpanded);
        }

        return NOTHING;
    }

    static final class TreeStateImpl implements TreeState {

        // GUI tree expansion state
        private final List<Integer> savedExpanded;

        // GUI tree selected row
        private final int savedSelected;

        public TreeStateImpl(int savedSelected, List<Integer> savedExpanded) {
            this.savedSelected = savedSelected;
            this.savedExpanded = savedExpanded;
        }

        @Override
        public void restore(GuiPackage guiInstance) {
            MainFrame mainframe = guiInstance.getMainFrame();
            if (mainframe == null) {
                //log?
                return;
            }

            final JTree tree = mainframe.getTree();

            if (!savedExpanded.isEmpty()) {
                savedExpanded.forEach(tree::expandRow);
            } else {
                tree.expandRow(0);
            }
            tree.setSelectionRow(savedSelected);
        }
    }
}
