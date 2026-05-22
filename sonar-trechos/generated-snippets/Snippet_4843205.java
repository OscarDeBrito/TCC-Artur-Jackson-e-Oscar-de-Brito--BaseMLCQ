public class Snippet__4843205 {

    public int from(MemberGroupLayout.ColumnSpans columnSpans) {
            if (this == LEFT)
                return columnSpans.getLeft();
            if (this == MIDDLE)
                return columnSpans.getMiddle();
            if (this == RIGHT)
                return columnSpans.getRight();
            throw new IllegalStateException();
        }

}
