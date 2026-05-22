private class SampleMap implements SimpleMap {

    private SimpleMap map;
    private SimpleMap sample;

    public SampleMap(SimpleMap map, SimpleMap sampleMap) {
      this.map = map;
      this.sample = sampleMap;
    }

    @Override
    public int size() {
      return map.size();
    }

    @Override
    public InterruptibleIterator skvIterator(SamplerConfigurationImpl samplerConfig) {
      if (samplerConfig == null)
        return map.skvIterator(null);
      else {
        Pair<SamplerConfigurationImpl,Sampler> samplerAndConf = samplerRef.get();
        if (samplerAndConf == null) {
          return EmptyIterator.EMPTY_ITERATOR;
        } else if (samplerAndConf.getFirst() != null
            && samplerAndConf.getFirst().equals(samplerConfig)) {
          return sample.skvIterator(null);
        } else {
          throw new SampleNotPresentException();
        }
      }
    }

    @Override
    public void delete() {
      map.delete();
      sample.delete();
    }

    @Override
    public long getMemoryUsed() {
      return map.getMemoryUsed() + sample.getMemoryUsed();
    }

    @Override
    public void mutate(List<Mutation> mutations, int kvCount) {
      map.mutate(mutations, kvCount);

      Sampler sampler = getOrCreateSampler();
      if (sampler != null) {
        List<Mutation> sampleMutations = null;

        for (Mutation m : mutations) {
          List<ColumnUpdate> colUpdates = m.getUpdates();
          List<ColumnUpdate> sampleColUpdates = null;
          for (ColumnUpdate cvp : colUpdates) {
            Key k = new Key(m.getRow(), cvp.getColumnFamily(), cvp.getColumnQualifier(),
                cvp.getColumnVisibility(), cvp.getTimestamp(), cvp.isDeleted(), false);
            if (sampler.accept(k)) {
              if (sampleColUpdates == null) {
                sampleColUpdates = new ArrayList<>();
              }
              sampleColUpdates.add(cvp);
            }
          }

          if (sampleColUpdates != null) {
            if (sampleMutations == null) {
              sampleMutations = new ArrayList<>();
            }

            sampleMutations
                .add(new LocalityGroupUtil.PartitionedMutation(m.getRow(), sampleColUpdates));
          }
        }

        if (sampleMutations != null) {
          sample.mutate(sampleMutations, kvCount);
        }
      }
    }
  }
