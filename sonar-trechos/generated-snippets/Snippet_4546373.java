public class Snippet__4546373 {

    @ApiModel(value = "PutRolesRoleIdResponse")
        public static final class PutRolesRoleIdResponse {
            private PutRolesRoleIdResponse() {

            }
            final class PutRolesRoleIdResponseChanges {
                private PutRolesRoleIdResponseChanges(){}
                @ApiModelProperty(example = "some description(changed)")
                public String description;
            }
            @ApiModelProperty(example = "1")
            public Long resourceId;
            public PutRolesRoleIdResponseChanges changes;

        }

}
