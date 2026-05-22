public class Snippet__8240678 {

    public void initialize(final Attribute[] attributes, final ElementProcessor parent) throws IOException {
            super.initialize(attributes, parent);
            // default value (when <gmr:Cell> has no ValueType attribute)
            int cellType = CellType.CELL_TYPE_FORMULA;
            try {
                cellType = getCellType();
            } catch (NullPointerException ignored) {
            }
            _cell = getSheet().getRow(getRow()).createCell(getColumn(), cellType);
        }

}
