public class TransientValuesTestFileTypeFactory extends FileTypeFactory {

	@Override
	public void createFileTypes(@NotNull FileTypeConsumer consumer) {
		consumer.consume(org.eclipse.xtext.parsetree.transientvalues.idea.lang.TransientValuesTestFileType.INSTANCE, org.eclipse.xtext.parsetree.transientvalues.idea.lang.AbstractTransientValuesTestFileType.DEFAULT_EXTENSION);
	}

}
