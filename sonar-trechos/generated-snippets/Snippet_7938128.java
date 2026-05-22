public class TreePartFactory implements EditPartFactory {

	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof LED)
			return new LogicTreeEditPart(model);
		if (model instanceof LogicDiagram)
			return new LogicContainerTreeEditPart(model);
		return new LogicTreeEditPart(model);
	}

}
