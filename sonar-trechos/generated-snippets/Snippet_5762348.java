public class Snippet__5762348 {

    @PreAuthorize("hasRole('" + StandardEntitlement.REPORT_TEMPLATE_DELETE + "')")
        public ReportTemplateTO delete(final String key) {
            ReportTemplate reportTemplate = reportTemplateDAO.find(key);
            if (reportTemplate == null) {
                LOG.error("Could not find report template '" + key + "'");

                throw new NotFoundException(key);
            }

            List<Report> reports = reportDAO.findByTemplate(reportTemplate);
            if (!reports.isEmpty()) {
                SyncopeClientException sce = SyncopeClientException.build(ClientExceptionType.InUse);
                sce.getElements().addAll(reports.stream().map(Entity::getKey).collect(Collectors.toList()));
                throw sce;
            }

            ReportTemplateTO deleted = getReportTemplateTO(key);
            reportTemplateDAO.delete(key);
            return deleted;
        }

}
