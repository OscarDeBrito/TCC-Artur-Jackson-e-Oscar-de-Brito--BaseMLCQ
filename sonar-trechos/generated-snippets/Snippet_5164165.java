public static class KeyHook implements Serializable {
        private static final long serialVersionUID = 2400159460862757991L;

        private String[] chunkskey;
        private byte[] values;

        /**
         * For de-serialization
         */
        public KeyHook() {
        }

        public KeyHook(String[] chunkskey, byte[] values) {
            super();
            this.chunkskey = chunkskey;
            this.values = values;
        }

        public String[] getChunkskey() {
            return chunkskey;
        }

        public void setChunkskey(String[] chunkskey) {
            this.chunkskey = chunkskey;
        }

        public byte[] getValues() {
            return values;
        }

        public void setValues(byte[] values) {
            this.values = values;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + Arrays.hashCode(chunkskey);
            result = prime * result + Arrays.hashCode(values);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            KeyHook other = (KeyHook) obj;
            if (!Arrays.equals(chunkskey, other.chunkskey))
                return false;
            if (!Arrays.equals(values, other.values))
                return false;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (chunkskey != null) {
                builder.append("chunkskey_length:" + chunkskey.length);
            } else {
                builder.append("chunkskey_is_null");
            }
            builder.append("|");
            if (values != null) {
                builder.append("value_length:" + values.length);
            } else {
                builder.append("value_is_null");
            }
            return builder.toString();
        }

        //        @Override
        //        public void writeExternal(ObjectOutput out) throws IOException {
        //            if(chunkskey == null){
        //                out.writeInt(0);
        //            }else{
        //                out.writeInt(chunkskey.length);
        //                for (String chunkKey : chunkskey) {
        //                    out.writeUTF(chunkKey);
        //                }
        //            }
        //            if(values != null){
        //                out.write(values);
        //            }
        //        }
        //        
        //        @Override
        //        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //            int keySize = in.readInt();
        //            if(keySize > 0){
        //                chunkskey = new String[keySize];
        //                for (int i = 0; i < keySize; i++){
        //                    chunkskey[i] = in.readUTF();
        //                }
        //            }
        //            int available = in.available();
        //            if(available > 0){
        //                values = new byte[available];
        //                in.read(values);
        //            }
        //        }
    }
