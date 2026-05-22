public static class BuilderSchema implements com.dyuproject.protostuff.Schema<org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.Builder>
        {
            public void mergeFrom(com.dyuproject.protostuff.Input input, org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.Builder builder) throws java.io.IOException
            {
                for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
                {
                    switch(number)
                    {
                        case 0:
                            return;
                        case 1:
                            builder.setExceptionClass(input.readString());
                            break;
                        case 2:
                            builder.setMessage(input.readString());
                            break;
                        case 3:
                            builder.addStackTrace(input.mergeObject(org.apache.drill.exec.proto.UserBitShared.StackTraceElementWrapper.newBuilder(), org.apache.drill.exec.proto.SchemaUserBitShared.StackTraceElementWrapper.MERGE));

                            break;
                        case 4:
                            builder.setCause(input.mergeObject(org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.newBuilder(), org.apache.drill.exec.proto.SchemaUserBitShared.ExceptionWrapper.MERGE));

                            break;
                        default:
                            input.handleUnknownField(number, this);
                    }
                }
            }
            public boolean isInitialized(org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.Builder builder)
            {
                return builder.isInitialized();
            }
            public org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.Builder newMessage()
            {
                return org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.newBuilder();
            }
            public java.lang.String getFieldName(int number)
            {
                return org.apache.drill.exec.proto.SchemaUserBitShared.ExceptionWrapper.getFieldName(number);
            }
            public int getFieldNumber(java.lang.String name)
            {
                return org.apache.drill.exec.proto.SchemaUserBitShared.ExceptionWrapper.getFieldNumber(name);
            }
            public java.lang.Class<org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.Builder> typeClass()
            {
                return org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.Builder.class;
            }
            public java.lang.String messageName()
            {
                return org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.class.getSimpleName();
            }
            public java.lang.String messageFullName()
            {
                return org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.class.getName();
            }
            //unused
            public void writeTo(com.dyuproject.protostuff.Output output, org.apache.drill.exec.proto.UserBitShared.ExceptionWrapper.Builder builder) throws java.io.IOException {}
        }
