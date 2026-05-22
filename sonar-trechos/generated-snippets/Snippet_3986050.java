public class Snippet__3986050 {

    @Override
        public Object invoke( Object proxy, Method method, Object[] args )
                throws Throwable
        {
            CircuitBreaker circuitBreaker = serviceCircuitBreaker.circuitBreaker();
            try
            {
                if( !circuitBreaker.isOn() )
                {
                    throw circuitBreaker.lastThrowable();
                }

                Object result = next.invoke( proxy, method, args );
                circuitBreaker.success();
                return result;
            
            } catch( Throwable throwable )
            {
                circuitBreaker.throwable( throwable );
                throw throwable;
            }
        }

}
