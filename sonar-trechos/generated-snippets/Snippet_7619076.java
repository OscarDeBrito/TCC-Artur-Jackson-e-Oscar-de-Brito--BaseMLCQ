@Configuration
	@Conditional(JwtKeyStoreCondition.class)
	protected class JwtKeyStoreConfiguration implements ApplicationContextAware {

		private final AuthorizationServerProperties authorization;

		private ApplicationContext context;

		@Autowired
		public JwtKeyStoreConfiguration(AuthorizationServerProperties authorization) {
			this.authorization = authorization;
		}

		@Override
		public void setApplicationContext(ApplicationContext context)
				throws BeansException {
			this.context = context;
		}

		@Bean
		@ConditionalOnMissingBean(AuthorizationServerTokenServices.class)
		public DefaultTokenServices jwtTokenServices(TokenStore jwtTokenStore) {
			DefaultTokenServices services = new DefaultTokenServices();
			services.setTokenStore(jwtTokenStore);
			return services;
		}

		@Bean
		@ConditionalOnMissingBean(TokenStore.class)
		public TokenStore tokenStore() {
			return new JwtTokenStore(accessTokenConverter());
		}

		@Bean
		public JwtAccessTokenConverter accessTokenConverter() {
			Assert.notNull(this.authorization.getJwt().getKeyStore(),
					"keyStore cannot be null");
			Assert.notNull(this.authorization.getJwt().getKeyStorePassword(),
					"keyStorePassword cannot be null");
			Assert.notNull(this.authorization.getJwt().getKeyAlias(),
					"keyAlias cannot be null");

			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

			Resource keyStore = this.context
					.getResource(this.authorization.getJwt().getKeyStore());
			char[] keyStorePassword = this.authorization.getJwt().getKeyStorePassword()
					.toCharArray();
			KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore,
					keyStorePassword);

			String keyAlias = this.authorization.getJwt().getKeyAlias();
			char[] keyPassword = Optional
					.ofNullable(this.authorization.getJwt().getKeyPassword())
					.map(String::toCharArray).orElse(keyStorePassword);
			converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword));

			return converter;
		}

	}
