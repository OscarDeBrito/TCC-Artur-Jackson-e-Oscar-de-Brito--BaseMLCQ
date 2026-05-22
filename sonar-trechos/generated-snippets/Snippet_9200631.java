class WordOptionObserver extends AbstractConfigurableOptionObserver
	{

		@Override
		public IConfigurableOption[] getOptions( )
		{
			return options;
		}

		@Override
		public IRenderOption getPreferredRenderOption( )
		{
			RenderOption renderOption = new RenderOption( );

			renderOption.setEmitterID( getID( ) );
			renderOption.setOutputFormat( "odt" ); //$NON-NLS-1$

			if ( values != null && values.length > 0 )
			{
				for ( IOptionValue optionValue : values )
				{
					if ( optionValue != null )
					{
						renderOption.setOption(
								getRenderOptionName( optionValue.getName( ) ),
								optionValue.getValue( ) );
					}
				}
			}

			return renderOption;
		}
	}
