public class Snippet__7372565 {

    private AnnotationMetadata getAnnotationMetadata() {
    			if (this.annotationMetadata == null) {
    				try {
    					MetadataReader metadataReader = this.metadataReaderFactory
    							.getMetadataReader(this.className);
    					this.annotationMetadata = metadataReader.getAnnotationMetadata();
    				}
    				catch (IOException ex) {
    					throw new IllegalStateException(
    							"Unable to read meta-data for class " + this.className, ex);
    				}
    			}
    			return this.annotationMetadata;
    		}

}
