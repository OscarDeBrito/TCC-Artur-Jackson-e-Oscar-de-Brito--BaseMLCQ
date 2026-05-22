public class ResequencerGraphicalEditPart extends BorderedIntegrationPart {

	public ResequencerGraphicalEditPart(ResequencerModelElement resequencer) {
		super(resequencer);
	}

	@Override
	protected IFigure createFigure() {
		Label l = (Label) super.createFigure();
		l.setIcon(IntegrationImages.getImageWithBadge(IntegrationImages.RESEQUENCER, IntegrationImages.BADGE_SI));
		return l;
	}

	@Override
	public ResequencerModelElement getModelElement() {
		return (ResequencerModelElement) getModel();
	}

}
