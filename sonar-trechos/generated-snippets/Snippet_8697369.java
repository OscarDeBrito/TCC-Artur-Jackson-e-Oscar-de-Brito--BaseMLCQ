public class Snippet__8697369 {

    public Snippet__8697369(ModelMenuItem modelMenuItem, Element conditionElement) {
            this.passStyleExdr = FlexibleStringExpander.getInstance(conditionElement.getAttribute("pass-style"));
            this.failStyleExdr = FlexibleStringExpander.getInstance(conditionElement.getAttribute("disabled-style"));
            this.condition = AbstractModelCondition.DEFAULT_CONDITION_FACTORY.newInstance(modelMenuItem, conditionElement);
        }

}
