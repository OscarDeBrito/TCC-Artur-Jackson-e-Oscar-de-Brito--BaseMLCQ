private static class SynchronossFilePart extends SynchronossPart implements FilePart {

		private static final OpenOption[] FILE_CHANNEL_OPTIONS =
				{StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE};

		private final String filename;

		SynchronossFilePart(HttpHeaders headers, String filename, StreamStorage storage, DataBufferFactory factory) {
			super(headers, storage, factory);
			this.filename = filename;
		}

		@Override
		public String filename() {
			return this.filename;
		}

		@Override
		public Mono<Void> transferTo(Path dest) {
			ReadableByteChannel input = null;
			FileChannel output = null;
			try {
				input = Channels.newChannel(getStorage().getInputStream());
				output = FileChannel.open(dest, FILE_CHANNEL_OPTIONS);
				long size = (input instanceof FileChannel ? ((FileChannel) input).size() : Long.MAX_VALUE);
				long totalWritten = 0;
				while (totalWritten < size) {
					long written = output.transferFrom(input, totalWritten, size - totalWritten);
					if (written <= 0) {
						break;
					}
					totalWritten += written;
				}
			}
			catch (IOException ex) {
				return Mono.error(ex);
			}
			finally {
				if (input != null) {
					try {
						input.close();
					}
					catch (IOException ignored) {
					}
				}
				if (output != null) {
					try {
						output.close();
					}
					catch (IOException ignored) {
					}
				}
			}
			return Mono.empty();
		}

		@Override
		public String toString() {
			return "Part '" + name() + "', filename='" + this.filename + "'";
		}
	}
