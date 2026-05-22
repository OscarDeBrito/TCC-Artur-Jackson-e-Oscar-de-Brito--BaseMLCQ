public class Snippet__7287653 {

    public static void ensure(IProject project, IProgressMonitor mon, String... reqNatures) throws CoreException {
    		IProjectDescription desc = project.getDescription();
    		String[] oldNaturesArr = desc.getNatureIds();
    		Set<String> natures = new LinkedHashSet<String>();
    		for (String n : reqNatures) {
    			natures.add(n);
    		}
    		for (String n : oldNaturesArr) {
    			natures.add(n);
    		}
    		if (natures.size()>oldNaturesArr.length) {
    			//Some natures got added
    			desc.setNatureIds(natures.toArray(new String[natures.size()]));
    			project.setDescription(desc, mon);
    		} else {
    			//No new natures added, but need to set it to force desired ordering
    			desc.setNatureIds(natures.toArray(new String[natures.size()]));
    			project.setDescription(desc, IResource.AVOID_NATURE_CONFIG, mon);
    		}
    	}

}
