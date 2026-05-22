public class Snippet__7699101 {

    @Override
        public boolean is2G(){
            LuaJavaNetworkState state = new LuaJavaNetworkState(mRapidID, mRapidView);

            return state.isNetworkActive();
        }

}
