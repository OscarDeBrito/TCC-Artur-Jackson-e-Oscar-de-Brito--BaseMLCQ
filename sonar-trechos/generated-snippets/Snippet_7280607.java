public static class Image extends AbstractResponse<Image> {
        public Image() {
        }

        public Image(JsonObject fields) throws SchemaViolationError {
            for (Map.Entry<String, JsonElement> field : fields.entrySet()) {
                String key = field.getKey();
                String fieldName = getFieldName(key);
                switch (fieldName) {
                    case "altText": {
                        String optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = jsonAsString(field.getValue(), key);
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "id": {
                        ID optional1 = null;
                        if (!field.getValue().isJsonNull()) {
                            optional1 = new ID(jsonAsString(field.getValue(), key));
                        }

                        responseData.put(key, optional1);

                        break;
                    }

                    case "originalSrc": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "src": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "transformedSrc": {
                        responseData.put(key, jsonAsString(field.getValue(), key));

                        break;
                    }

                    case "__typename": {
                        responseData.put(key, jsonAsString(field.getValue(), key));
                        break;
                    }
                    default: {
                        throw new SchemaViolationError(this, key, field.getValue());
                    }
                }
            }
        }

        public String getGraphQlTypeName() {
            return "Image";
        }

        /**
        * A word or phrase to share the nature or contents of an image.
        */

        public String getAltText() {
            return (String) get("altText");
        }

        public Image setAltText(String arg) {
            optimisticData.put(getKey("altText"), arg);
            return this;
        }

        /**
        * A unique identifier for the image.
        */

        public ID getId() {
            return (ID) get("id");
        }

        public Image setId(ID arg) {
            optimisticData.put(getKey("id"), arg);
            return this;
        }

        /**
        * The location of the original image as a URL.
        * If there are any existing transformations in the original source URL, they will remain and not be
        * stripped.
        */

        public String getOriginalSrc() {
            return (String) get("originalSrc");
        }

        public Image setOriginalSrc(String arg) {
            optimisticData.put(getKey("originalSrc"), arg);
            return this;
        }

        /**
        * The location of the image as a URL.
        *
        * @deprecated Previously an image had a single `src` field. This could either return the original image
        location or a URL that contained transformations such as sizing or scale.

        These transformations were specified by arguments on the parent field.

        Now an image has two distinct URL fields: `originalSrc` and `transformedSrc`.

        * `originalSrc` - the original unmodified image URL
        * `transformedSrc` - the image URL with the specified transformations included

        To migrate to the new fields, image transformations should be moved from the parent field to `transformedSrc`.

        Before:
        ```graphql
        {
            shop {
                productImages(maxWidth: 200, scale: 2) {
                    edges {
                        node {
                            src
                        }
                    }
                }
            }
        }
        ```

        After:
        ```graphql
        {
            shop {
                productImages {
                    edges {
                        node {
                            transformedSrc(maxWidth: 200, scale: 2)
                        }
                    }
                }
            }
        }
        ```

        */

        public String getSrc() {
            return (String) get("src");
        }

        public Image setSrc(String arg) {
            optimisticData.put(getKey("src"), arg);
            return this;
        }

        /**
        * The location of the transformed image as a URL.
        * All transformation arguments are considered "best-effort". If they can be applied to an image, they
        * will be.
        * Otherwise any transformations which an image type does not support will be ignored.
        */

        public String getTransformedSrc() {
            return (String) get("transformedSrc");
        }

        public Image setTransformedSrc(String arg) {
            optimisticData.put(getKey("transformedSrc"), arg);
            return this;
        }

        public boolean unwrapsToObject(String key) {
            switch (getFieldName(key)) {
                case "altText": return false;

                case "id": return false;

                case "originalSrc": return false;

                case "src": return false;

                case "transformedSrc": return false;

                default: return false;
            }
        }
    }
