static class CachedReader {
		File file;
		ImageInputStream stream;
		final PHDImage parentImage;
		class ReaderPos {
			int where;
			HeapdumpReader reader;
			ReaderPos(PHDImage parentImage) throws IOException {
				if(stream == null) {
					reader = new HeapdumpReader(file, parentImage);
				} else {
					reader = new HeapdumpReader(stream, parentImage);
				}
				where = 0;
			}
		}
		List<ReaderPos> readers = new ArrayList<ReaderPos>();
		CachedReader(File f, PHDImage parentImage) {
			file = f;
			this.parentImage = parentImage;
		}
		CachedReader(ImageInputStream stream, PHDImage parentImage) {
			this.stream = stream;
			this.parentImage = parentImage;
		}
		ReaderPos getReader(int n) throws IOException {
			ReaderPos best = null;
			for (ReaderPos rp : readers) {
				if (rp.where <= n && (best == null || best.where < rp.where)) {
					best =rp;
				}
			}
			if (best == null) {	
				best = new ReaderPos(parentImage);
			} else {
				readers.remove(best);
			}
			return best;
		}
		void returnReader(ReaderPos rdr) {
			readers.add(rdr);
		}
	}
