public class Snippet__7725751 {

    public static boolean isTrustAliasMissing(ComputeState computeState) {
            URI hostUri = ContainerDescription.getDockerHostUri(computeState);
            return UriUtils.HTTPS_SCHEME.equalsIgnoreCase(hostUri.getScheme())
                    && (getTrustAlias(computeState) == null);
        }

}
