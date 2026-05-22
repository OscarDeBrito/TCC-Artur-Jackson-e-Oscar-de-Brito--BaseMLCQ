public static class WorksetPlaceHolder<WT> extends Operator<WT> {

		private final DeltaIterationBase<?, WT> containingIteration;

		public WorksetPlaceHolder(DeltaIterationBase<?, WT> container, OperatorInformation<WT> operatorInfo) {
			super(operatorInfo, "Workset");
			this.containingIteration = container;
		}

		public DeltaIterationBase<?, WT> getContainingWorksetIteration() {
			return this.containingIteration;
		}

		@Override
		public void accept(Visitor<Operator<?>> visitor) {
			visitor.preVisit(this);
			visitor.postVisit(this);
		}

		@Override
		public UserCodeWrapper<?> getUserCodeWrapper() {
			return null;
		}
	}
