private static class RangerSealedObject extends SealedObject {

        /**
         *
         */
        private static final long serialVersionUID = -7551578543434362070L;

        protected RangerSealedObject(SealedObject so) {
            super(so);
        }

        protected RangerSealedObject(Serializable object, Cipher cipher) throws IllegalBlockSizeException, IOException {
            super(object, cipher);
        }

        public AlgorithmParameters getParameters() throws NoSuchAlgorithmException, IOException {
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("PBEWithMD5AndTripleDES");
            algorithmParameters.init(super.encodedParams);
            return algorithmParameters;
        }

    }
