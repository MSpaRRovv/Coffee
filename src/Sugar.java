public class Sugar extends CondimentDecorator {
    private Inventory inventory;

    public Sugar(Beverage beverage, Inventory inventory) {
        super(beverage);
        this.inventory = inventory;
    }

    @Override
    public void prepare() {
        if (inventory.useSugar()) {
            beverage.prepare();
            System.out.println("Добавление сахара");
        } else {
            System.out.println("Извините, сахар закончился!");
        }
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Сахар";
    }
}
