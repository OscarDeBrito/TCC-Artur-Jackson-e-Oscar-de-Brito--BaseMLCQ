public class Snippet__5354961 {

    private Record transform(final Record record, final JoltTransform transform) {
            Map<String, Object> recordMap = (Map<String, Object>) DataTypeUtils.convertRecordFieldtoObject(record, RecordFieldType.RECORD.getRecordDataType(record.getSchema()));

            // JOLT expects arrays to be of type List where our Record code uses Object[].
            // Make another pass of the transformed objects to change Object[] to List.
            recordMap = (Map<String, Object>) normalizeJoltObjects(recordMap);
            final Object transformedObject = transform(transform, recordMap);

            // JOLT expects arrays to be of type List where our Record code uses Object[].
            // Make another pass of the transformed objects to change List to Object[].
            final Object normalizedRecordValues = normalizeRecordObjects(transformedObject);
            final Record updatedRecord = DataTypeUtils.toRecord(normalizedRecordValues, "r");
            return updatedRecord;
        }

}
