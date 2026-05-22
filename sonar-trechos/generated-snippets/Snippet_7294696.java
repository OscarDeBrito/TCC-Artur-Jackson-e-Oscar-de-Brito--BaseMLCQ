public class Snippet__7294696 {

    @Override
    	public IVMInstall verifyVMInstall(ILaunchConfiguration conf) throws CoreException {
    		IVMInstall javaInstall = super.verifyVMInstall(conf);
    		IGrailsInstall grailsInstall = GrailsLaunchArgumentUtils.getGrailsInstall(conf);
    		grailsInstall.verifyJavaInstall(javaInstall);
    		return javaInstall;
    	}

}
