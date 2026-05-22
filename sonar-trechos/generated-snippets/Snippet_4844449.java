private static class JCARepositoryFactory
            implements RepositoryFactory, Serializable {

		private static final long serialVersionUID = 5364039431121341634L;
		
		private final JCAManagedConnectionFactory mcf;

        public JCARepositoryFactory(JCAManagedConnectionFactory mcf) {
            this.mcf = mcf;
        }

        public Repository getRepository() throws RepositoryException {
            return mcf.getRepository();
        }

    }
