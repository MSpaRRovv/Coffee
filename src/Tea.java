public class Tea extends Beverage {
    private Inventory inventory;

    public Tea(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String getDescription() {
        return "Чай";
    }

    @Override
    public void prepare() {
        if (inventory.useTea()) {
            System.out.println("Заваривание чая");
        } else {
            System.out.println("Извините, чай закончился!");
        }
    }
}
