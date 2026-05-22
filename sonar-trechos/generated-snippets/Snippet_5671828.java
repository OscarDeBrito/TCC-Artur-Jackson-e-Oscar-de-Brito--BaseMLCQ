public class Snippet__5671828 {

    final public IObjectAtom variable() throws ParseException {
      Token t; String var;
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NOTEX:
          jj_consume_token(NOTEX);
          jj_consume_token(LPAR);
          t = jj_consume_token(VARIABLE);
          jj_consume_token(RPAR);
                                                          var=t.image; var=kb.getPrefixURI("var") + var.substring(1);
                                                                                                                    try{
                                                                                                                            {if (true) return new VariableAtom(new URI(var), true);}
                                                                                                                    } catch (URISyntaxException e) {
                                                                                                                            e.printStackTrace();
                                                                                                                            {if (true) return null;}
                                                                                                                    }
          break;
        case VARIABLE:
          t = jj_consume_token(VARIABLE);
              var=t.image; var=kb.getPrefixURI("var") + var.substring(1);
                                                                                                                    try{
                                                                                                                            {if (true) return new VariableAtom(new URI(var), false);}
                                                                                                                    } catch (URISyntaxException e) {
                                                                                                                            e.printStackTrace();
                                                                                                                            {if (true) return null;}
                                                                                                                    }
          break;
        case BNODE:
          t = jj_consume_token(BNODE);
              var=t.image;  {if (true) return new RuleBlankNode(var);}
          break;
        default:
          jj_la1[15] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        throw new Error("Missing return statement in function");
      }

}
