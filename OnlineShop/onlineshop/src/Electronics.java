

public class Electronics extends Category  {
    public Electronics() {
        super("Electronics");

        Category smartphones = new Category("Smartphones");
        Category laptops = new Category("Laptops");
        Category cameras = new Category("Cameras");

        addSubcategory(smartphones);
        addSubcategory(laptops);
        addSubcategory(cameras);
    }
}
