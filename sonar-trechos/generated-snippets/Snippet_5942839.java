public class Snippet__5942839 {

    public byte[] element() throws NoSuchElementException, KeeperException, InterruptedException {
            Map<Long,String> orderedChildren;

            // element, take, and remove follow the same pattern.
            // We want to return the child node with the smallest sequence number.
            // Since other clients are remove()ing and take()ing nodes concurrently, 
            // the child with the smallest sequence number in orderedChildren might be gone by the time we check.
            // We don't call getChildren again until we have tried the rest of the nodes in sequence order.
            while(true){
                try{
                    orderedChildren = orderedChildren(null);
                }catch(KeeperException.NoNodeException e){
                    throw new NoSuchElementException();
                }
                if(orderedChildren.size() == 0 ) throw new NoSuchElementException();

                for(String headNode : orderedChildren.values()){
                    if(headNode != null){
                        try{
                            return zookeeper.getData(dir+"/"+headNode, false, null);
                        }catch(KeeperException.NoNodeException e){
                            //Another client removed the node first, try next
                        }
                    }
                }

            }
        }

}
