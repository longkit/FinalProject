

public class Books extends Category  {
    public Books() {
        super("Books");

        Category fiction = new Category("Fiction");
        Category nonFiction = new Category("Non-Fiction");

        addSubcategory(fiction);
        addSubcategory(nonFiction);
    }
}
