public class Snippet__9421769 {

    public JCNewClass SpeculativeNewClass(JCExpression encl,
                                 List<JCExpression> typeargs,
                                 JCExpression clazz,
                                 List<JCExpression> args,
                                 JCClassDecl def,
                                 boolean classDefRemoved)
        {
            JCNewClass tree = classDefRemoved ?
                    new JCNewClass(encl, typeargs, clazz, args, def) {
                        @Override
                        public boolean classDeclRemoved() {
                            return true;
                        }
                    } :
                    new JCNewClass(encl, typeargs, clazz, args, def);
            tree.pos = pos;
            return tree;
        }

}
