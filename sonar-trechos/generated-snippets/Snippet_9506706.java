public class Snippet__9506706 {

    private void initReleases() {
        if (this.release != null) {
          return;
        }
        AllocateRequestProtoOrBuilder p = viaProto ? proto : builder;
        List<ContainerIdProto> list = p.getReleaseList();
        this.release = new ArrayList<ContainerId>();

        for (ContainerIdProto c : list) {
          this.release.add(convertFromProtoFormat(c));
        }
      }

}
