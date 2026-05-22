public class Snippet__5420605 {

    @Test
        public void testImmutableKeywordAndProperty() throws Exception {
            Properties props = PropertiesUtil.deepCopy(TEST_PROPERTIES);
            String immutableDataTableFullName = SchemaUtil.getTableName("", generateUniqueName());
            String mutableDataTableFullName = SchemaUtil.getTableName("", generateUniqueName());
            try (Connection conn = DriverManager.getConnection(getUrl(), props);) {
                Statement stmt = conn.createStatement();
                try {
                    // create immutable table with immutable table property set to true 
                    String ddl = "CREATE IMMUTABLE TABLE  " + immutableDataTableFullName +
                            "  (a_string varchar not null, col1 integer" +
                            "  CONSTRAINT pk PRIMARY KEY (a_string)) IMMUTABLE_ROWS=true";
                    stmt.execute(ddl);
                    fail();
                }
                catch (SQLException e) {
                    assertEquals(SQLExceptionCode.IMMUTABLE_TABLE_PROPERTY_INVALID.getErrorCode(), e.getErrorCode());
                }
            
                try {
                    // create immutable table with immutable table property set to false
                    String ddl = "CREATE IMMUTABLE TABLE  " + mutableDataTableFullName +
                            "  (a_string varchar not null, col1 integer" +
                            "  CONSTRAINT pk PRIMARY KEY (a_string))  IMMUTABLE_ROWS=false";
                    stmt.execute(ddl);
                    fail();
                }
                catch (SQLException e) {
                    assertEquals(SQLExceptionCode.IMMUTABLE_TABLE_PROPERTY_INVALID.getErrorCode(), e.getErrorCode());
                }
            
            } 
        }

}
