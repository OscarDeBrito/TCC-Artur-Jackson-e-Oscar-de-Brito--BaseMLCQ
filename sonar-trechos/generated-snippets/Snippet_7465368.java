public class Neo4jPersistentProperty extends AnnotationBasedPersistentProperty<Neo4jPersistentProperty> {

	private static final Logger logger = LoggerFactory.getLogger(Neo4jPersistentProperty.class);

	enum PropertyType {
		REGULAR_PROPERTY(false), INTERNAL_ID_PROPERTY(true), ID_PROPERTY(true);

		private final boolean idProperty;

		PropertyType(boolean idProperty) {
			this.idProperty = idProperty;
		}
	}

	private final PropertyType propertyType;

	/**
	 * Constructs a new {@link Neo4jPersistentProperty} based on the given arguments.
	 *
	 * @param owningClassInfo The {@link ClassInfo} of the object of which the property field is a member
	 * @param property The property
	 * @param owner The owning {@link PersistentEntity} that corresponds to the given {@code ClassInfo}
	 * @param simpleTypeHolder The {@link SimpleTypeHolder} that dictates whether the type of this property is considered
	 *          simple or not
	 */
	public Neo4jPersistentProperty(ClassInfo owningClassInfo, Property property,
			PersistentEntity<?, Neo4jPersistentProperty> owner, SimpleTypeHolder simpleTypeHolder) {
		super(property, owner, simpleTypeHolder);

		if (owningClassInfo == null) {
			logger.warn("Owning ClassInfo is null for property: {}", property);
		}

		if (owningClassInfo == null || owningClassIsSimple(owningClassInfo, simpleTypeHolder)
				|| owningClassDoesNotSupportIdProperties(owningClassInfo) || owningPropertyIsEnum(owner)) {
			this.propertyType = PropertyType.REGULAR_PROPERTY;
		} else if (isInternalIdentityField(owningClassInfo, property)) {
			this.propertyType = PropertyType.INTERNAL_ID_PROPERTY;
		} else if (isExplicitIdentityField(owningClassInfo, property)) {
			this.propertyType = PropertyType.ID_PROPERTY;
		} else {
			this.propertyType = PropertyType.REGULAR_PROPERTY;
		}
	}

	private static boolean owningPropertyIsEnum(PersistentEntity<?, Neo4jPersistentProperty> owner) {
		return owner.getType().isEnum();
	}

	private static boolean owningClassIsSimple(ClassInfo owningClassInfo, SimpleTypeHolder simpleTypeHolder) {

		return owningClassInfo.getUnderlyingClass() != null
				&& simpleTypeHolder.isSimpleType(owningClassInfo.getUnderlyingClass());
	}

	private static boolean owningClassDoesNotSupportIdProperties(ClassInfo owningClassInfo) {

		return owningClassInfo.isInterface() || owningClassInfo.annotationsInfo().get(QueryResult.class.getName()) != null
				|| owningClassInfo.isEnum();
	}

	private static boolean isInternalIdentityField(ClassInfo owningClassInfo, Property property) {

		Optional<Field> optionalInternalIdentityField = Optional.ofNullable(owningClassInfo.identityFieldOrNull())
				.map(FieldInfo::getField);
		return property.getField().equals(optionalInternalIdentityField);
	}

	private static boolean isExplicitIdentityField(ClassInfo owningClassInfo, Property property) {

		// Cannot use owningClassInfo.propertyField() as those are not initialized yet. They will
		// be initialized on the call, but that would change behaviour: SDN fails late when there
		// are invalid properties atm. Even if'ts better to fail early, it means at least changing
		// a dozen tests in SDN itself.
		return property.getField().map(field -> AnnotatedElementUtils.findMergedAnnotation(field, Id.class)).isPresent();
	}

	@Override
	public boolean isIdProperty() {
		return propertyType.idProperty;
	}

	PropertyType getPropertyType() {
		return propertyType;
	}

	@Override
	public boolean isVersionProperty() {
		return isAnnotationPresent(Version.class);
	}

	/**
	 * Overridden to force field access as opposed to getter method access for simplicity.
	 *
	 * @see org.springframework.data.mapping.model.AnnotationBasedPersistentProperty#usePropertyAccess()
	 */
	@Override
	public boolean usePropertyAccess() {
		logger.debug("[property].usePropertyAccess() returns false");
		return false;
	}

	/**
	 * Determines whether or not this property should be considered an association to another entity or whether it's just
	 * a simple property that should be shown as a value.
	 * <p>
	 * This implementation works by looking for non-transient members annotated with <code>@Relationship</code>.
	 * </p>
	 *
	 * @return <code>true</code> if this property is an association to another entity, <code>false</code> if not
	 */
	@Override
	public boolean isAssociation() {
		return !isTransient() && (isAnnotationPresent(Relationship.class) || isAnnotationPresent(StartNode.class)
				|| isAnnotationPresent(EndNode.class));
	}

	@Override
	protected Association<Neo4jPersistentProperty> createAssociation() {
		return new Association<Neo4jPersistentProperty>(this, null);
	}
}
