public class ArrayImageSource extends ImageSource
	{

		private int[] imageSource;

		public ArrayImageSource( int width, int height, int[] imageSource )
		{
			super( width, height );
			this.imageSource = imageSource;
		}

		public int getRGB( int x, int y )
		{
			return imageSource[y * width + x];
		}

		public int[] getData( )
		{
			return imageSource;
		}
	}
