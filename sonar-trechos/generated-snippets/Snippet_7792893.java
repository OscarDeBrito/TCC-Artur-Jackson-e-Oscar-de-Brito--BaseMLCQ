private class ExampleResource extends ResourceStreamResource {
		
		private String content;
		
		private int count = 0;

		public ExampleResource(String content)
		{
			this.content = content;

			setFileName("File-from-IResource.txt");
			setCacheDuration(Duration.NONE);
		}
		
		@Override
		protected IResourceStream getResourceStream(Attributes attributes) {
			// simulate delay
			try
			{
				TimeUnit.MILLISECONDS.sleep(3000);
			}
			catch (InterruptedException e)
			{
			}
			
			count++;
			if (count == 3) {
				count = 0;
				throw new AbortWithHttpErrorCodeException(400);
			}

			return new StringResourceStream(content);
		};

	}
