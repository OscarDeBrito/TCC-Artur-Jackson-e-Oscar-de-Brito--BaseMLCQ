public class Snippet__3804285 {

    private Integer findRankOf(Rankable r) {
            Object tag = r.getObject();
            for (int rank = 0; rank < rankedItems.size(); rank++) {
                Object cur = rankedItems.get(rank).getObject();
                if (cur.equals(tag)) {
                    return rank;
                }
            }
            return null;
        }

}
