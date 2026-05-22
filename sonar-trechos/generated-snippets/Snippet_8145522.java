public class Snippet__8145522 {

    public boolean needsTychoBuild() {
        return (this.needsMavenBuild() && this.runtimeProject.isEclipsePluginProject());
      }

}
