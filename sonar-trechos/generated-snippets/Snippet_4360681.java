public class Snippet__4360681 {

    public static Test clientExistingServerSuite(Class testClass, String hostName, int portNumber)
        {
                   // Will not start server and does not stop it when done!.
            return existingServerDecorator(bareClientServerSuite(testClass),
                    hostName, portNumber);
        }

}
