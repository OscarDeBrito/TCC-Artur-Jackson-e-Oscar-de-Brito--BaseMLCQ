public class Snippet__5041287 {

    @Override
        public void endIterator(Iterator<?> iterator)
        {
            checkIfClosed() ; 
            transaction.removeIterator(iterator) ;
            // Don't pass down the beginIterator call - we track and manage here, not lower down.  
            //blockMgr.endIterator(iterator) ;
        }

}
