public class Snippet__4911266 {

    @GET
      @Path("/create")
      public String createMetricConfig(@QueryParam("dataset") String dataset, @QueryParam("name") String name, @QueryParam("datatype") String metricType,
          @QueryParam("active") boolean active, @QueryParam("derived") boolean derived, @QueryParam("derivedFunctionType") String derivedFunctionType,
          @QueryParam("numerator") String numerator, @QueryParam("denominator") String denominator,
          @QueryParam("derivedMetricExpression") String derivedMetricExpression, @QueryParam("inverseMetric") boolean inverseMetric,
          @QueryParam("cellSizeExpression") String cellSizeExpression, @QueryParam("rollupThreshold") Double rollupThreshold) {
        try {
          MetricConfigDTO metricConfigDTO = new MetricConfigDTO();
          populateMetricConfig(metricConfigDTO, dataset, name, metricType, active, derived, derivedFunctionType, numerator, denominator, derivedMetricExpression,
              inverseMetric, cellSizeExpression, rollupThreshold);
          Long id = metricConfigDao.save(metricConfigDTO);

          metricConfigDTO.setId(id);
          return JsonResponseUtil.buildResponseJSON(metricConfigDTO).toString();
        } catch (Exception e) {
          LOG.warn("Failed to create metric:{}", name, e);
          return JsonResponseUtil.buildErrorResponseJSON("Failed to create metric:" + name + " Message:" + e.getMessage()).toString();
        }
      }

}
