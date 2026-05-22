public class Snippet__4447157 {

    public void mergeFrom(Input input, RunQuery message) throws IOException
        {
            for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
            {
                switch(number)
                {
                    case 0:
                        return;
                    case 1:
                        message.resultsMode = QueryResultsMode.valueOf(input.readEnum());
                        break;
                    case 2:
                        message.type = QueryType.valueOf(input.readEnum());
                        break;
                    case 3:
                        message.plan = input.readString();
                        break;
                    case 4:
                        if(message.fragments == null)
                            message.fragments = new ArrayList<PlanFragment>();
                        message.fragments.add(input.mergeObject(null, PlanFragment.getSchema()));
                        break;

                    case 5:
                        message.preparedStatementHandle = input.mergeObject(message.preparedStatementHandle, PreparedStatementHandle.getSchema());
                        break;

                    default:
                        input.handleUnknownField(number, this);
                }   
            }
        }

}
