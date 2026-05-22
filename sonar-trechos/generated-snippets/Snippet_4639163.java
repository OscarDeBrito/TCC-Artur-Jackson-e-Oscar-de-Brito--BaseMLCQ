public static class DecimalConverter extends IdentityConverter<Decimal> {

		private static final long serialVersionUID = 3825744951173809617L;

		private final int precision;
		private final int scale;

		public DecimalConverter(int precision, int scale) {
			this.precision = precision;
			this.scale = scale;
		}

		@Override
		Decimal toExternalImpl(BaseRow row, int column) {
			return row.getDecimal(column, precision, scale);
		}
	}
