public class Snippet__8673459 {

    public void onRemove(){
            rmCnt.incrementAndGet();

            if (delegate != null)
                delegate.onRemove();
        }

}
