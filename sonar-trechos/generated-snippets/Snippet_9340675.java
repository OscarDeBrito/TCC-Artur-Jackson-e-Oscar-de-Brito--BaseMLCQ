public class Snippet__9340675 {

    public ReferenceType referenceType() {
            if (type == null) {
                try {
                    JDWP.ObjectReference.ReferenceType rtinfo =
                        JDWP.ObjectReference.ReferenceType.process(vm, this);
                    type = vm.referenceType(rtinfo.typeID,
                                            rtinfo.refTypeTag);
                } catch (JDWPException exc) {
                    throw exc.toJDIException();
                }
            }
            return type;
        }

}
