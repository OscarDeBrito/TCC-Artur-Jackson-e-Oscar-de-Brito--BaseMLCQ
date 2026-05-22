public class Snippet__6500087 {

    @Override
    	public boolean appliesToPartially(GenerationSimpleRule rule, int i) {
    		if(rule instanceof ButtonRule && ((((ButtonRule)rule).getStyle() & SWT.CHECK) != 0)){
    			if(i == 0){
    				bRule = (ButtonRule)rule;
    			}
    			if(bRule.equals(rule)){
    				return true;
    			}
    			return false;
    		}
    		return false;
    	}

}
