

public class Home extends Category  {
    public Home() {
        super("Home");

        Category kitchenware = new Category("Kitchenware");
        Category furniture = new Category("Furniture");
        Category decor = new Category("Decor");

        addSubcategory(kitchenware);
        addSubcategory(furniture);
        addSubcategory(decor);
    }
}
