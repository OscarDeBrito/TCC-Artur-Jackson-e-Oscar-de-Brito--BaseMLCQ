static class ConvertType extends TypeConversion<Type> {
    private boolean returnRawTypes;

    public ConvertType(boolean returnRawTypes) {
      this.returnRawTypes = returnRawTypes;
    }

    @Override
    protected Type convertArray(TypeDescriptor<?> type) {
      TypeDescriptor ret = createListType(type);
      return returnRawTypes ? ret.getRawType() : ret.getType();
    }

    @Override
    protected Type convertCollection(TypeDescriptor<?> type) {
      return Collection.class;
    }

    @Override
    protected Type convertMap(TypeDescriptor<?> type) {
      return Map.class;
    }

    @Override
    protected Type convertDateTime(TypeDescriptor<?> type) {
      return Instant.class;
    }

    @Override
    protected Type convertByteBuffer(TypeDescriptor<?> type) {
      return byte[].class;
    }

    @Override
    protected Type convertGenericFixed(TypeDescriptor<?> type) {
      return byte[].class;
    }

    @Override
    protected Type convertCharSequence(TypeDescriptor<?> type) {
      return String.class;
    }

    @Override
    protected Type convertPrimitive(TypeDescriptor<?> type) {
      return ClassUtils.primitiveToWrapper(type.getRawType());
    }

    @Override
    protected Type convertDefault(TypeDescriptor<?> type) {
      return returnRawTypes ? type.getRawType() : type.getType();
    }

    @SuppressWarnings("unchecked")
    private <ElementT> TypeDescriptor<List<ElementT>> createListType(TypeDescriptor<?> type) {
      TypeDescriptor componentType =
          TypeDescriptor.of(ClassUtils.primitiveToWrapper(type.getComponentType().getRawType()));
      return new TypeDescriptor<List<ElementT>>() {}.where(
          new TypeParameter<ElementT>() {}, componentType);
    }
  }
