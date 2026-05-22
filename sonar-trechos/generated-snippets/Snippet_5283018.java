public class Snippet__5283018 {

    @Override
        public Expression matches(Value v1, Value v2,
            String single, String multi, String esc) {
            return new MatchesExpression((Val) v1, (Val) v2, single, multi, esc,
                true);
        }

}
