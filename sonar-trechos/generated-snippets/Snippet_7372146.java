private static class Frame {

		private final BufferedImage image;

		private final int delayTime;

		Frame(BufferedImage image, int delayTime) {
			this.image = image;
			this.delayTime = delayTime;
		}

		public BufferedImage getImage() {
			return this.image;
		}

		public int getDelayTime() {
			return this.delayTime;
		}

	}
