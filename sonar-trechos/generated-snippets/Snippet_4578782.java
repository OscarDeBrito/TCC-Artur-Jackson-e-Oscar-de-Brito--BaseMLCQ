public class Snippet__4578782 {

    public long outputCrossReferenceObject(OutputStream stream,
                    TrailerDictionary trailerDictionary, int first, int last, int size) throws IOException {
                // Outputting the object streams should not have created new indirect objects
                assert objects.isEmpty();
                new CrossReferenceStream(PDFDocument.this, ++objectcount, trailerDictionary, position,
                        indirectObjectOffsets,
                        structureTreeObjectStreams.getCompressedObjectReferences())
                        .output(stream);
                return position;
            }

}
