public class Snippet__4417149 {

    private void initWS()
        {
            // create new instance of the service implmentation
            EmployeeService impl = new EmployeeServiceImpl(db, conn);

            // construct/configure factory to create our specific service
            svrFactory = new JaxWsServerFactoryBean();
            //svrFactory.getInInterceptors().add(new LoggingInInterceptor());
            //svrFactory.getOutInterceptors().add(new LoggingOutInterceptor());
            // specify our implementation class
            svrFactory.setServiceClass(EmployeeService.class);
            // specify the address
            svrFactory.setAddress(serviceAddress);
            // set the instance to be used
            svrFactory.setServiceBean(impl);
        }

}
