public class Beauty extends Category  {
    public Beauty() {
        super("Beauty");

        Category MenSkincare = new Category("Men");
        Category WomenSkincare = new Category("Women");

        addSubcategory(MenSkincare);
        addSubcategory(WomenSkincare);
    }
}