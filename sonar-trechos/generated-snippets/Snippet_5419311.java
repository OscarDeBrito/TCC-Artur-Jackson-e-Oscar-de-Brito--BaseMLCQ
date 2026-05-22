public class Snippet__5419311 {

    private List<HRegionLocation> getRegionLocationsFromManifest(SnapshotManifest manifest) {
    		List<SnapshotRegionManifest> regionManifests = manifest.getRegionManifests();
    		Preconditions.checkNotNull(regionManifests);

    		List<RegionInfo> regionInfos = Lists.newArrayListWithCapacity(regionManifests.size());
    		List<HRegionLocation> hRegionLocations = Lists.newArrayListWithCapacity(regionManifests.size());

    		for (SnapshotRegionManifest regionManifest : regionManifests) {
    			RegionInfo regionInfo = ProtobufUtil.toRegionInfo(regionManifest.getRegionInfo());
    			if (isValidRegion(regionInfo)) {
    				regionInfos.add(regionInfo);
    			}
    		}

    		regionInfos.sort(RegionInfo.COMPARATOR);

    		for (RegionInfo regionInfo : regionInfos) {
    			hRegionLocations.add(new HRegionLocation(regionInfo, null));
    		}

    		return hRegionLocations;
    	}

}
