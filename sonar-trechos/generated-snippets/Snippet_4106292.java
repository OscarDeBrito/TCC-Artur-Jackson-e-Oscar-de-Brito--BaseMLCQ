public class AndRule implements Rule
{
	List<Rule> rules;

	public AndRule(Rule rule1, Rule rule2, Rule ... rules)
	{
		this.rules = new LinkedList<Rule>(Arrays.asList(rules));
		this.rules.add(rule1);
		this.rules.add(rule2);
	}

	@Override
	public boolean ruleMatches(Product product) throws IllegalArgumentException
	{
		boolean matches = true;
		for(Rule rule : rules)
		{
			if(! rule.ruleMatches(product))
			{
				matches = false;
			}
		}

		return matches;
	}

}
