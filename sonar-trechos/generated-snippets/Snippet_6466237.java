public class FormDataValidator implements Validator
{
    @Override
    public ValidationResult validate ( final Object target )
    {
        final SimpleValidationContext ctx = new SimpleValidationContext ();

        for ( final Method m : target.getClass ().getMethods () )
        {
            final DataValidator dv = m.getAnnotation ( DataValidator.class );
            if ( dv == null )
            {
                continue;
            }

            try
            {
                m.invoke ( target, ctx );
            }
            catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException e )
            {
                ctx.error ( null, new ExceptionError ( e ) );
            }
        }

        return ctx.getResult ();
    }
}
