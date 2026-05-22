@Singleton
public class EC2ImageSupplier implements Supplier<Set<? extends Image>> {
   @Resource
   @Named(ComputeServiceConstants.COMPUTE_LOGGER)
   protected Logger logger = Logger.NULL;

   private final Supplier<Set<String>> regions;
   private final DescribeImagesParallel describer;
   private final String[] amiOwners;
   private final EC2ImageParser parser;
   private final Supplier<LoadingCache<RegionAndName, ? extends Image>> cache;

   @Inject
   protected EC2ImageSupplier(@Region Supplier<Set<String>> regions, DescribeImagesParallel describer,
         @Named(PROPERTY_EC2_AMI_OWNERS) String[] amiOwners, Supplier<LoadingCache<RegionAndName, ? extends Image>> cache,
         EC2ImageParser parser) {
      this.regions = regions;
      this.describer = describer;
      this.amiOwners = amiOwners;
      this.cache = cache;
      this.parser = parser;
   }

   @SuppressWarnings({ "unchecked", "rawtypes" })
   @Override
   public Set<? extends Image> get() {
      if (amiOwners.length == 0) {
         logger.debug(">> no owners specified, skipping image parsing");
         return ImmutableSet.of();
      
      } else {
         logger.debug(">> providing images");

         Iterable<Entry<String, DescribeImagesOptions>> queries = getDescribeQueriesForOwnersInRegions(regions.get(),
                  amiOwners);

         Iterable<? extends Image> parsedImages = ImmutableSet.copyOf(filter(transform(describer.apply(queries), parser), Predicates
                  .notNull()));

         Map<RegionAndName, ? extends Image> imageMap = ImagesToRegionAndIdMap.imagesToMap(parsedImages);
         cache.get().invalidateAll();
         cache.get().asMap().putAll((Map)imageMap);
         logger.debug("<< images(%d)", imageMap.size());
         
         return Sets.newLinkedHashSet(imageMap.values());
      }
   }

   public Iterable<Entry<String, DescribeImagesOptions>> getDescribeQueriesForOwnersInRegions(Set<String> regions,
         String[] amiOwners) {
      DescribeImagesOptions options = getOptionsForOwners(amiOwners);
      Builder<String, DescribeImagesOptions> builder = ImmutableMap.builder();
      for (String region : regions)
         builder.put(region, options);
      return builder.build().entrySet();
   }

   public DescribeImagesOptions getOptionsForOwners(String... amiOwners) {
      DescribeImagesOptions options;
      if (amiOwners.length == 1 && amiOwners[0].equals("*"))
         options = new DescribeImagesOptions();
      else
         options = ownedBy(amiOwners);
      return options;
   }
}
