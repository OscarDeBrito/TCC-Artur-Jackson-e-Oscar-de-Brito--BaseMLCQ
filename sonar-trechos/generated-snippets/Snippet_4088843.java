public class Snippet__4088843 {

    @Override
        public void customize() {
            newScript(CUSTOMIZING)
                    .body.append(
                            format("mkdir -p %s", getRunDir()),
                            format("cp -R %s/dist/{conf,html,logs,sbin} %s", getExpandedInstallDir(), getRunDir()))
                    .execute();

            // Install static content archive, if specified
            String archiveUrl = entity.getConfig(NginxController.STATIC_CONTENT_ARCHIVE_URL);
            if (Strings.isNonBlank(archiveUrl)) {
                getEntity().deploy(archiveUrl);
            }

            customizationCompleted = true;
        }

}
