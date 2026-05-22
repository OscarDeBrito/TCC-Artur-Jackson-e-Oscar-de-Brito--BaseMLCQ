public class Snippet__5731614 {

    void set(final String name, final String desc, final Handle bsm,
                final Object[] bsmArgs) {
            this.type = 'y';
            this.strVal1 = name;
            this.strVal2 = desc;
            this.objVal3 = bsm;
            this.objVals = bsmArgs;

            int hashCode = 'y' + name.hashCode() * desc.hashCode() * bsm.hashCode();
            for (Object bsmArg : bsmArgs) {
                hashCode *= bsmArg.hashCode();
            }
            this.hashCode = 0x7FFFFFFF & hashCode;
        }

}
