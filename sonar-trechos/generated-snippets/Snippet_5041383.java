public class JSONMaker implements JSONHandler
{
    public JSONMaker() {}
    
    private JsonValue value = null ;
    
    private Deque<JsonArray> arrays = new ArrayDeque<>();
    private Deque<JsonObject> objects = new ArrayDeque<>();

    // The depth of this stack is the object depth.
    private Deque<String> keys = new ArrayDeque<>();

    public JsonValue jsonValue()
    {
        return value ;
    }

    @Override
    public void startParse(long currLine, long currCol)
    {}

    @Override
    public void finishParse(long currLine, long currCol)
    {}
    
    @Override
    public void startObject(long currLine, long currCol)
    {
        objects.push(new JsonObject()) ; 
    }

    @Override
    public void finishObject(long currLine, long currCol)
    {
        value = objects.pop() ; 
    }

    @Override
    public void startArray(long currLine, long currCol)
    {
        arrays.push(new JsonArray()) ;
    }

    @Override
    public void element(long currLine, long currCol)
    {
        arrays.peek().add(value) ;
        value = null ;
    }

    @Override
    public void finishArray(long currLine, long currCol)
    {
        value = arrays.pop() ;
    }

    @Override
    public void startPair(long currLine, long currCol)
    { 
    }


    @Override
    public void keyPair(long currLine, long currCol)
    {
        keys.push(value.getAsString().value()) ;
    }

    @Override
    public void finishPair(long currLine, long currCol)
    {
        if ( value == null )
            throw new InternalErrorException("null for 'value' (bad finishPair() allignment)") ;
        
        String k = keys.pop();
        JsonObject obj = objects.peek() ;
        if ( obj.hasKey(k) )
            Log.warn("JSON", "Duplicate key '"+k+"' for object ["+currLine+","+currCol+"]") ;
        obj.put(k, value) ;
        value = null ;
    }

    @Override
    public void valueBoolean(boolean b, long currLine, long currCol)
    { 
        value = new JsonBoolean(b) ;
    }

    @Override
    public void valueDecimal(String image, long currLine, long currCol)
    {
        value = JsonNumber.valueDecimal(image) ;
    }

    @Override
    public void valueDouble(String image, long currLine, long currCol)
    {
        value = JsonNumber.valueDouble(image) ;
    }

    @Override
    public void valueInteger(String image, long currLine, long currCol)
    {
        value = JsonNumber.valueInteger(image) ;
    }

    @Override
    public void valueNull(long currLine, long currCol)
    {
        value = JsonNull.instance ;
    }

    @Override
    public void valueString(String image, long currLine, long currCol)
    {
        value = new JsonString(image) ;
    }
}
