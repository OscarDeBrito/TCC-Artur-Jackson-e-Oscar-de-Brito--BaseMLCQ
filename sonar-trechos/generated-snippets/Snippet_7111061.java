public class Snippet__7111061 {

    private void addSqlite() {

            ClassVisitorFactory classVisitorFactory = new ClassVisitorFactory() {
                @Override
                public ClassVisitor create(ClassInstrumentationData classInstrumentationData, ClassWriter classWriter) {
                    HashSet<String> ctorSignatures = new HashSet<String>();
                    ctorSignatures.add("(Lorg/sqlite/SQLiteConnection;Ljava/lang/String;)V");
                    final PreparedStatementMetaData metaData1 = new PreparedStatementMetaData(ctorSignatures);
                    metaData1.sqlStringInCtor = 2;
                    return new PreparedStatementClassVisitor(classInstrumentationData, classWriter, metaData1);
                }
            };

            String className = "org/sqlite/core/CorePreparedStatement";
            ClassInstrumentationData data =
                    new ClassInstrumentationData(className, InstrumentedClassType.SQL, classVisitorFactory)
                            .setReportCaughtExceptions(false)
                            .setReportExecutionTime(true);
            data.addMethod("executeBatch", "()[I", false, true, 0, new MethodVisitorFactory() {
                @Override
                public MethodVisitor create(MethodInstrumentationDecision decision, int access, String desc, String owner, String methodName, MethodVisitor methodVisitor, ClassToMethodTransformationData additionalData) {
                    return new PreparedStatementMethodForExecuteBatchVisitor(access, desc, owner, methodName, methodVisitor, null);
                }
            });
            classesToInstrument.put(className, data);

            doAdd(null, "org/sqlite/jdbc3/JDBC3PreparedStatement");
        }

}
