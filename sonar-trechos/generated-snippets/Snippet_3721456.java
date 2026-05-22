public static class PackageMapDistributor extends AtlasVirtualFile.DistributorBase {
        private final PackageDistribution packageDistribution;
        private final ExecutorService executorService;

        PackageMapDistributor(ApplicationWriter writer, PackageDistribution packageDistribution, ExecutorService executorService) {
            super(writer);
            this.packageDistribution = packageDistribution;
            this.executorService = executorService;
        }

        public Map<Integer, AtlasVirtualFile> run() throws ExecutionException, IOException {
            assert this.nameToFileMap.size() == 1;

            assert this.nameToFileMap.containsKey(0);

            int maxReferencedIndex = this.packageDistribution.maxReferencedIndex();

            for(int index = 1; index <= maxReferencedIndex; ++index) {
                AtlasVirtualFile file = new AtlasVirtualFile(index, this.writer.namingLens);
                this.nameToFileMap.put(index, file);
            }

            this.fillForMainDexList(this.classes);
            this.classes = this.sortClassesByPackage(this.classes, this.originalNames);
            Set<String> usedPrefixes = this.fillForDistribution(this.classes, this.originalNames);
            Map newAssignments;
            if (this.classes.isEmpty()) {
                newAssignments = Collections.emptyMap();
            } else {
                newAssignments = (new AtlasVirtualFile.PackageSplitPopulator(this.nameToFileMap, this.classes, this.originalNames, usedPrefixes, this.application.dexItemFactory, AtlasVirtualFile.FillStrategy.LEAVE_SPACE_FOR_GROWTH, this.writer.namingLens)).call();
                if (!newAssignments.isEmpty() && this.nameToFileMap.size() > 1) {
                    System.err.println(" * The used package map is missing entries. The following default mappings have been used:");
                    this.writeAssignments(newAssignments, new OutputStreamWriter(System.err));
                    System.err.println(" * Consider updating the map.");
                }
            }

            Path newPackageMap = Paths.get("package.map");
            System.out.println(" - " + newPackageMap.toString());
            PackageDistribution.writePackageToFileMap(newPackageMap, newAssignments, this.packageDistribution);
            return this.nameToFileMap;
        }

        private Set<String> fillForDistribution(Set<DexProgramClass> classes, Map<DexProgramClass, String> originalNames) throws ExecutionException {
            Set<String> usedPrefixes = null;
            if (this.packageDistribution != null) {
                ArrayList<Future<List<DexProgramClass>>> futures = new ArrayList(this.nameToFileMap.size());
                usedPrefixes = this.packageDistribution.getFiles();
                Iterator var5 = this.nameToFileMap.values().iterator();

                while(var5.hasNext()) {
                    AtlasVirtualFile file = (AtlasVirtualFile)var5.next();
                    AtlasVirtualFile.PackageMapPopulator populator = new AtlasVirtualFile.PackageMapPopulator(file, classes, this.packageDistribution, originalNames);
                    futures.add(this.executorService.submit(populator));
                }

                ThreadUtils.awaitFutures(futures).forEach(classes::removeAll);
            }

            return usedPrefixes;
        }

        private void writeAssignments(Map<String, Integer> assignments, Writer output) throws IOException {
            Iterator var3 = assignments.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, Integer> entry = (Map.Entry)var3.next();
                output.write("    ");
                PackageDistribution.formatEntry(entry, output);
                output.write("\n");
            }

            output.flush();
        }
    }
