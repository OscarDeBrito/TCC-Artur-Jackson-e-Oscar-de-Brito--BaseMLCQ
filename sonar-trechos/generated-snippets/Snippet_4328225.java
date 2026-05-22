public  static  class   School  extends KeyedObject
    {
        private String      _schoolName;
        private boolean     _isGrammarSchool;

        public  School( String schoolName, boolean isGrammarSchool )
        {
            _schoolName = schoolName;
            _isGrammarSchool = isGrammarSchool;
        }

        public  String      getSchoolName() { return _schoolName; }
        public  boolean     isGrammarSchool() { return _isGrammarSchool; }

        protected   void                createMinion( Database database )
            throws SQLException
        {
            Connection          conn = database.getConnection();

            PreparedStatement   ps = Utils.prepare
                (
                 conn,
                 "insert into School( schoolName ) values ( ? )"
                 );

            ps.setString( 1, _schoolName );

            ps.execute();
            Utils.close( ps );
        }

        protected   PreparedStatement   getKeyFinder( Database database )
            throws SQLException
        {
            Connection          conn = database.getConnection();
            PreparedStatement   ps = Utils.prepare
                (
                 conn,
                 "select schoolID from School where schoolName = ?"
                 );

            ps.setString( 1, _schoolName );

            return ps;
        }

    }
