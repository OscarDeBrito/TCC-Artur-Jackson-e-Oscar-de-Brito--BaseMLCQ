public class Snippet__4217192 {

    @Override
        protected Catalog<String, Object, ServletWebContext<String, Object>>
                getCatalog(ServletWebContext<String, Object> context) {
            /* If the object returned from the passed context is not a valid catalog
             * then we use the super class's catalog extraction logic to pull it
             * or to error gracefully.
             */
            Object testCatalog = context.get(getCatalogName());

            /* Assume that the underlying implementation is following convention and
             * returning a catalog with the current context.
             */
            @SuppressWarnings("unchecked")
            Catalog<String, Object, ServletWebContext<String, Object>> catalog =
                    testCatalog != null && testCatalog instanceof Catalog ?
                        (Catalog<String, Object, ServletWebContext<String, Object>>) testCatalog :
                        super.getCatalog(context);

            return catalog;
        }

}
