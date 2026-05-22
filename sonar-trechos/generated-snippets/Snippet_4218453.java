public class Snippet__4218453 {

    public Expression setUpper(Bound newUpper)
        {
            upper = newUpper == null ? null : new Bound(newUpper.value, newUpper.inclusive);
            return this;
        }

}
