public class Syrup extends CondimentDecorator {
    private Inventory inventory;

    public Syrup(Beverage beverage, Inventory inventory) {
        super(beverage);
        this.inventory = inventory;
    }

    @Override
    public void prepare() {
        if (inventory.useSyrup()) {
            beverage.prepare();
            System.out.println("Добавление сиропа");
        } else {
            System.out.println("Извините, сироп закончился!");
        }
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Сироп";
    }
}
