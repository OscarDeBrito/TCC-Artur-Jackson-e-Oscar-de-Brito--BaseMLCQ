public class Snippet__4467093 {

    @Override
      public void cleanUp() {
        final DrillConnectionImpl connection1 = (DrillConnectionImpl) connection;
        connection1.openStatementsRegistry.removeStatement(this);
      }

}
