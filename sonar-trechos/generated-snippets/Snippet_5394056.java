public class Snippet__5394056 {

    @Test
        public void testJoinWithLocalIndex() throws Exception {
            Properties props = PropertiesUtil.deepCopy(TEST_PROPERTIES);
            Connection conn = DriverManager.getConnection(getUrl(), props);
            try {            
                String query = "select phone, i.name from " + getTableName(conn, JOIN_SUPPLIER_TABLE_FULL_NAME) + " s join " + getTableName(conn, JOIN_ITEM_TABLE_FULL_NAME) + " i on s.\"supplier_id\" = i.\"supplier_id\" where s.name = 'S1' and i.name < 'T6'";
                System.out.println("1)\n" + query);
                PreparedStatement statement = conn.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                assertTrue (rs.next());
                assertEquals(rs.getString(1), "888-888-1111");
                assertTrue (rs.next());
                assertEquals(rs.getString(1), "888-888-1111");
                assertFalse(rs.next());
                rs = conn.createStatement().executeQuery("EXPLAIN " + query);
                assertPlansEqual(plans[24], QueryUtil.getExplainPlan(rs));
            
                query = "select phone, max(i.name) from " + getTableName(conn, JOIN_SUPPLIER_TABLE_FULL_NAME) + " s join " + getTableName(conn, JOIN_ITEM_TABLE_FULL_NAME) + " i on s.\"supplier_id\" = i.\"supplier_id\" where s.name = 'S1' and i.name < 'T6' group by phone";
                statement = conn.prepareStatement(query);
                rs = statement.executeQuery();
                assertTrue (rs.next());
                assertEquals(rs.getString(1), "888-888-1111");
                assertEquals(rs.getString(2), "T2");
                assertFalse(rs.next());
                rs = conn.createStatement().executeQuery("EXPLAIN " + query);
                assertPlansEqual(plans[25], QueryUtil.getExplainPlan(rs));
            
                query = "select max(phone), max(i.name) from " + getTableName(conn, JOIN_SUPPLIER_TABLE_FULL_NAME) + " s left join " + getTableName(conn, JOIN_ITEM_TABLE_FULL_NAME) + " i on s.\"supplier_id\" = i.\"supplier_id\" and i.name < 'T6' where s.name <= 'S3'";
                statement = conn.prepareStatement(query);
                rs = statement.executeQuery();
                assertTrue (rs.next());
                assertEquals(rs.getString(1), "888-888-3333");
                assertEquals(rs.getString(2), "T4");
                assertFalse(rs.next());
                rs = conn.createStatement().executeQuery("EXPLAIN " + query);
                assertPlansEqual(plans[26], QueryUtil.getExplainPlan(rs));
            } finally {
                conn.close();
            }
        }

}
