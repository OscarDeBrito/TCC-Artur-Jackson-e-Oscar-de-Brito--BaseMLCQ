public static class PrimitiveByteArrayConverter extends IdentityConverter<byte[]> {

		private static final long serialVersionUID = -2007960927801689921L;

		public static final PrimitiveByteArrayConverter INSTANCE = new PrimitiveByteArrayConverter();

		private PrimitiveByteArrayConverter() {}

		@Override
		byte[] toExternalImpl(BaseRow row, int column) {
			return row.getBinary(column);
		}
	}
