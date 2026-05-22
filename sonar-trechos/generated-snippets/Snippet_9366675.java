private static class CertStoreComparator implements Comparator<CertStore> {
        @Override
        public int compare(CertStore store1, CertStore store2) {
            if (store1.getType().equals("Collection") ||
                store1.getCertStoreParameters() instanceof
                CollectionCertStoreParameters) {
                return -1;
            } else {
                return 1;
            }
        }
    }
