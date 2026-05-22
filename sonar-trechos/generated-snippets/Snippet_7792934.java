public static interface FileFilter
	{
		/**
		 * File filter that matches all files
		 */
		public static FileFilter ALL_FILES = new FileFilter()
		{
			@Override
			public boolean accept(final File file)
			{
				return true;
			}
		};

		/**
		 * @param file
		 *            The file to test
		 * @return True if the file should be accepted
		 */
		public boolean accept(File file);
	}
