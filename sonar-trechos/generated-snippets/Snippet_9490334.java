public class Snippet__9490334 {

    private static WebAppContext createWebAppContext(Builder b,
          AccessControlList adminsAcl, final String appDir) {
        WebAppContext ctx = new WebAppContext();
        ctx.setDefaultsDescriptor(null);
        ServletHolder holder = new ServletHolder(new DefaultServlet());
        Map<String, String> params = ImmutableMap. <String, String> builder()
                .put("acceptRanges", "true")
                .put("dirAllowed", "false")
                .put("gzip", "true")
                .put("useFileMappedBuffer", "true")
                .build();
        holder.setInitParameters(params);
        ctx.setWelcomeFiles(new String[] {"index.html"});
        ctx.addServlet(holder, "/");
        ctx.setDisplayName(b.name);
        ctx.setContextPath("/");
        ctx.setWar(appDir + "/" + b.name);
        String tempDirectory = b.conf.get(HTTP_TEMP_DIR_KEY);
        if (tempDirectory != null && !tempDirectory.isEmpty()) {
          ctx.setTempDirectory(new File(tempDirectory));
          ctx.setAttribute("javax.servlet.context.tempdir", tempDirectory);
        }
        ctx.getServletContext().setAttribute(CONF_CONTEXT_ATTRIBUTE, b.conf);
        ctx.getServletContext().setAttribute(ADMINS_ACL, adminsAcl);
        addNoCacheFilter(ctx);
        return ctx;
      }

}
