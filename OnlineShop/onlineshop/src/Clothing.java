

public class Clothing extends Category  {
    public Clothing() {
        super("Clothing");

        Category menClothing = new Category("Men's Clothing");
        Category womenClothing = new Category("Women's Clothing");
        Category kidsClothing = new Category("Kids' Clothing");

        addSubcategory(menClothing);
        addSubcategory(womenClothing);
        addSubcategory(kidsClothing);
    }
}
