@Converter(autoApply = true)
	public static class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

		@Override
		public Date convertToDatabaseColumn(LocalDateTime date) {
			return LocalDateTimeToDateConverter.INSTANCE.convert(date);
		}

		@Override
		public LocalDateTime convertToEntityAttribute(Date date) {
			return DateToLocalDateTimeConverter.INSTANCE.convert(date);
		}
	}
