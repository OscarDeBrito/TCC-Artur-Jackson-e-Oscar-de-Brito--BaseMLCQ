public class Butcher {

	@Transformer
	public Meat sell(Ingredient ingredient) {
		return new Meat(ingredient.getName(), ingredient.getAmount());
	}

    @Transformer
    public GroceryBag<Meat> sell(ShoppingList shoppingList) {
        GroceryBag<Meat> groceryBag = new GroceryBag<Meat>();
        for (Ingredient ingredient : shoppingList.getItems()) {
            groceryBag.put(sell(ingredient));
        }
        return groceryBag;
    }
}
