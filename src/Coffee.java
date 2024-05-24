public class Coffee extends Beverage {
    private Inventory inventory;

    public Coffee(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String getDescription() {
        return "Кофе";
    }

    @Override
    public void prepare() {
        if (inventory.useCoffee()) {
            System.out.println("Приготовление кофе");
        } else {
            System.out.println("Извините, кофе закончился!");
        }
    }
}
