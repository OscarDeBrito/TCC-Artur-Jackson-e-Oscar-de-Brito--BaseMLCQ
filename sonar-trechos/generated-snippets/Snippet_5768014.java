public class Snippet__5768014 {

    @Override
            public Iterator<SAML2IdPTO> iterator(final long first, final long count) {
                List<SAML2IdPTO> list = restClient.list();
                Collections.sort(list, comparator);
                return list.subList((int) first, (int) first + (int) count).iterator();
            }

}
