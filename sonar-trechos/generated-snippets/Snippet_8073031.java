protected static class SimpleNameDescription implements IEObjectDescription {

		private final EObject object;
		private final QualifiedName qualifiedName;
		private final QualifiedName simpleName;
		private final Map<String, String> userData;

		public SimpleNameDescription(QualifiedName qName, EObject resolvedObject, IEObjectDescription source) {
			this.simpleName = qName;
			this.object = resolvedObject;
			this.qualifiedName = source.getQualifiedName();
			Preconditions.checkArgument(!this.object.eIsProxy());
			Preconditions.checkNotNull(this.simpleName);
			Preconditions.checkNotNull(this.qualifiedName);
			Map<String, String> userData = null;
			for (final String key : source.getUserDataKeys()) {
				if (userData == null) {
					userData = Maps.newHashMapWithExpectedSize(2);
				}
				userData.put(key, source.getUserData(key));
			}
			this.userData = userData;
		}

		@Override
		public EClass getEClass() {
			return object.eClass();
		}

		@Override
		public EObject getEObjectOrProxy() {
			return object;
		}

		@Override
		public URI getEObjectURI() {
			throw new UnsupportedOperationException();
		}

		@Override
		public QualifiedName getName() {
			return simpleName;
		}

		@Override
		public QualifiedName getQualifiedName() {
			return qualifiedName;
		}

		@Override
		public String getUserData(String key) {
			if (userData == null) {
				return null;
			}
			return userData.get(key);
		}

		@Override
		public String[] getUserDataKeys() {
			if (userData == null) {
				return new String[0];
			}
			return userData.keySet().toArray(new String[userData.size()]);
		}

		@Override
		public String toString() {
			StringBuilder result = new StringBuilder();
			result.append(IEObjectDescription.class.getSimpleName() + " " + qualifiedName);
			if (simpleName != null && !simpleName.equals(qualifiedName)) {
				result.append(" name:" + simpleName);
			}
			if (userData != null && !userData.isEmpty()) {
				List<String> items = Lists.newArrayList();
				for (Entry<String, String> e : userData.entrySet()) {
					items.add(e.getKey() + ": " + e.getValue());
				}
				result.append(" userData=[" + Joiner.on(", ").join(items) + "]");
			}
			return result.toString();
		}

	}
