public class FieldPrefixPrefixTupleReference extends TypeAwareTupleReference {

    public FieldPrefixPrefixTupleReference(ITypeTraits[] typeTraits) {
        super(typeTraits);
    }

    // assumes tuple index refers to prefix tuples
    @Override
    public void resetByTupleIndex(ITreeIndexFrame frame, int tupleIndex) {
        BTreeFieldPrefixNSMLeafFrame concreteFrame = (BTreeFieldPrefixNSMLeafFrame) frame;
        IPrefixSlotManager slotManager = concreteFrame.getSlotManager();
        int prefixSlotOff = slotManager.getPrefixSlotOff(tupleIndex);
        int prefixSlot = concreteFrame.getBuffer().getInt(prefixSlotOff);
        setFieldCount(slotManager.decodeFirstSlotField(prefixSlot));
        tupleStartOff = slotManager.decodeSecondSlotField(prefixSlot);
        buf = concreteFrame.getBuffer().array();
        resetByTupleOffset(buf, tupleStartOff);
    }
}
