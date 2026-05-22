@ImplementedBy(SemanticNodeProvider.class)
public interface ISemanticNodeProvider {

	public interface ISemanticNode {
		INode getNode();

		ISemanticNode getFollower();

		AbstractElement getGrammarElement();
	}

	public interface INodesForEObjectProvider {
		ISemanticNode getSemanticNodeForMultiValue(EStructuralFeature feature, int indexInFeature, int indexInNonTransient, Object value);

		ISemanticNode getSemanticNodeForSingelValue(EStructuralFeature feature, Object value);

		ISemanticNode getFirstSemanticNode();

		INode getNodeForMultiValue(EStructuralFeature feature, int indexInFeature, int indexInNonTransient, Object value);

		INode getNodeForSingelValue(EStructuralFeature feature, Object value);
	}

	public class NullNodesForEObjectProvider implements INodesForEObjectProvider {
		@Override
		public INode getNodeForMultiValue(EStructuralFeature feature, int indexInFeature, int indexAmongNonTransient,
				Object value) {
			return null;
		}

		@Override
		public INode getNodeForSingelValue(EStructuralFeature feature, Object value) {
			return null;
		}

		@Override
		public ISemanticNode getSemanticNodeForMultiValue(EStructuralFeature feature, int indexInFeature,
				int indexInNonTransient, Object value) {
			return null;
		}

		@Override
		public ISemanticNode getSemanticNodeForSingelValue(EStructuralFeature feature, Object value) {
			return null;
		}

		@Override
		public ISemanticNode getFirstSemanticNode() {
			return null;
		}
	}

	public INodesForEObjectProvider NULL_NODES_PROVIDER = new NullNodesForEObjectProvider();

	INodesForEObjectProvider getNodesForSemanticObject(EObject semanticObject, ICompositeNode suggestedComposite);

}
