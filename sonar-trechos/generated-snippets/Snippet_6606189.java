public class Snippet__6606189 {

    public String myCacheKey() {
            if (getChromosome() == null || getChromosome().getId() == null ||
                    getStartIndex() == null || getEndIndex() == null || getId() == null) {
                throw new IllegalArgumentException(MessageHelper.getMessage("error.hash"));
            }

            StringBuilder sb = new StringBuilder();
            sb = sb.append(getId())
                    .append(HASH_DELIMITER)
                    .append(getChromosome().getId())
                    .append(HASH_DELIMITER)
                    .append(getStartIndex())
                    .append(HASH_DELIMITER)
                    .append(getEndIndex());
            return sb.toString();
        }

}
