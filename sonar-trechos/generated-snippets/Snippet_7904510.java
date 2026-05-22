public class Snippet__7904510 {

    public AndroidPackageableCollector addNativeLinkableAsset(NativeLinkable nativeLinkable) {
        if (nativeLinkablesAssetsToExclude.contains(nativeLinkable)) {
          return this;
        }
        APKModule module = apkModuleGraph.findModuleForTarget(nativeLinkable.getBuildTarget());
        collectionBuilder.putNativeLinkablesAssets(module, nativeLinkable);
        return this;
      }

}
