public class RemoveCAS {

  public static void main(String[] args) {
    
    if (args.length != 2) {
      System.out.println("RemoveCAS corpusAddress casId");
      System.exit(-1);
    }
    
    Client c = Client.create();

    WebResource r = c.resource(args[0]);
    
    ClientResponse response = r
        .path(args[1])
        .delete(ClientResponse.class);
    
    System.out.println("Result: " + response.getStatus());
  }
  
}
