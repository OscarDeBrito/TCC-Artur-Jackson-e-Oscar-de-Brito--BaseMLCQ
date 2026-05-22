public class Snippet__5793952 {

    private String getMetadataFullAffiliations(List<Author> authorList) {
            List<Affiliation> unique = new ArrayList<Affiliation>();
            StringBuilder metAffils = new StringBuilder();

            for (Author a : authorList) {
                for (Affiliation af : a.getAffiliations()) {
                    if (!unique.contains(af)) {
                        unique.add(af);
                    }
                }
            }
            metAffils.append("[");
            for (Affiliation af : unique) {
                metAffils.append(af.toString());
                metAffils.append(",");
            }
            metAffils.append(metAffils.deleteCharAt(metAffils.length() - 1));
            metAffils.append("]");
            return metAffils.toString();
        }

}
