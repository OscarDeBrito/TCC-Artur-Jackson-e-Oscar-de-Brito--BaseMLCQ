public class Snippet__9007287 {

    @Override
      public Object doWork(Object first, Object second) throws IOException{
        if(null == first){
          throw new IOException(String.format(Locale.ROOT,"Unable to %s(...) with a null numerator", constructingFactory.getFunctionName(getClass())));
        }
    
        if(null == second){
          throw new IOException(String.format(Locale.ROOT,"Unable to %s(...) with a null denominator", constructingFactory.getFunctionName(getClass())));
        }

        BigDecimal numerator = (BigDecimal)first;
        BigDecimal denominator = (BigDecimal)second;
        
        if(0 == denominator.compareTo(BigDecimal.ZERO)){
          throw new IOException(String.format(Locale.ROOT,"Unable to %s(...) with a 0 denominator", constructingFactory.getFunctionName(getClass())));
        }
    
        return numerator.divide(denominator, MathContext.DECIMAL64);
      }

}
