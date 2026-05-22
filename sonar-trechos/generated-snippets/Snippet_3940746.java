public class GreeterBlueprintClient {
    GreeterMessageService greeterMessageService;
    String clientID;
    
    public GreeterBlueprintClient(String clientID){
        this.clientID = clientID;
    }
    
    public void setGreeterMessageService(GreeterMessageService gms){
        this.greeterMessageService = gms;
    }
    
    public void doRequests(){
        System.out.println("Invoking injected service...");
        printResult(greeterMessageService.getGreetingMessage());
    }
    
    private void printResult(String response){
        System.out.println("**********************************");
        System.out.println("*"+clientID);
        System.out.println("*"+response);
        System.out.println("**********************************");
    }
}
