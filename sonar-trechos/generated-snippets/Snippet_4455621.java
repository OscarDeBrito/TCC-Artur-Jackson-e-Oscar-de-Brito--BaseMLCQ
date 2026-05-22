private static class MaterializeVisitor extends AbstractMaterializeVisitor {

    private final Map<VectorAccessible, BatchReference> batches;

    public MaterializeVisitor(Map<VectorAccessible, BatchReference> batches,
                              ErrorCollector errorCollector,
                              boolean allowComplexWriter,
                              boolean unionTypeEnabled) {
      super(errorCollector, allowComplexWriter, unionTypeEnabled);
      this.batches = batches;
    }

    @Override
    public LogicalExpression visitSchemaPath(final SchemaPath path, FunctionLookupContext functionLookupContext) {
      TypedFieldId tfId = null;
      BatchReference batchRef = null;
      for (Map.Entry<VectorAccessible, BatchReference> entry : batches.entrySet()) {
        tfId = entry.getKey().getValueVectorId(path);
        if (tfId != null) {
          batchRef = entry.getValue();
          break;
        }
      }

      if (tfId == null) {
        logger.warn("Unable to find value vector of path {}, returning null instance.", path);
        return NullExpression.INSTANCE;
      } else {
        return new ValueVectorReadExpression(tfId, batchRef);
      }
    }
  }
