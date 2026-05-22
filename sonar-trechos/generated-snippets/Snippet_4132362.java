public class Snippet__4132362 {

    public int compareTypePrecedence(RelDataType type1, RelDataType type2) {
        assert containsType(type1) : type1;
        assert containsType(type2) : type2;

        int p1 =
            getListPosition(
                type1.getSqlTypeName(),
                typeNames);
        int p2 =
            getListPosition(
                type2.getSqlTypeName(),
                typeNames);
        return p2 - p1;
      }

}
