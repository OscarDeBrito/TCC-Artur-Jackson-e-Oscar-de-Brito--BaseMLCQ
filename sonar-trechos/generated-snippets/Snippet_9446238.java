public class Snippet__9446238 {

    public byte[] getBytes(int columnIndex) throws SQLException {
            // sanity check.
            checkIndex(columnIndex);
            // make sure the cursor is on a valid row
            checkCursor();

            if (isBinary(RowSetMD.getColumnType(columnIndex)) == false) {
                throw new SQLException(resBundle.handleGetObject("cachedrowsetimpl.dtypemismt").toString());
            }

            return (byte[])(getCurrentRow().getColumnObject(columnIndex));
        }

}
