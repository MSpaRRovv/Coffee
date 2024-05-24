public class Milk extends CondimentDecorator {
    private Inventory inventory;

    public Milk(Beverage beverage, Inventory inventory) {
        super(beverage);
        this.inventory = inventory;
    }

    @Override
    public void prepare() {
        if (inventory.useMilk()) {
            beverage.prepare();
            System.out.println("Добавление молока");
        } else {
            System.out.println("Извините, молоко закончилось!");
        }
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Молоко";
    }
}
