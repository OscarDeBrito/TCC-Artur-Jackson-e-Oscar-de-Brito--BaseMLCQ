public class MovePackageChange extends PackageReorgChange {

	public MovePackageChange(IPackageFragment pack, IPackageFragmentRoot dest){
		super(pack, dest, null);
	}

	@Override
	protected Change doPerformReorg(IProgressMonitor pm) throws JavaModelException, OperationCanceledException {
		getPackage().move(getDestination(), null, getNewName(), true, pm);
		return null;
	}

	@Override
	public String getName() {
		String packageName= JavaElementLabels.getElementLabel(getPackage(), JavaElementLabels.ALL_DEFAULT);
		String destinationName= JavaElementLabels.getElementLabel(getDestination(), JavaElementLabels.ALL_DEFAULT);
		return Messages.format(RefactoringCoreMessages.MovePackageChange_move, new String[] {packageName, destinationName });
	}
}
