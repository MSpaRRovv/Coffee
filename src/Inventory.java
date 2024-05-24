public class Inventory {
    private int milkCount = 10;
    private int sugarCount = 10;
    private int syrupCount = 10;
    private int coffeeCount = 10;
    private int teaCount = 10;

    public boolean useMilk() {
        if (milkCount > 0) {
            milkCount--;
            return true;
        }
        return false;
    }

    public boolean useSugar() {
        if (sugarCount > 0) {
            sugarCount--;
            return true;
        }
        return false;
    }

    public boolean useSyrup() {
        if (syrupCount > 0) {
            syrupCount--;
            return true;
        }
        return false;
    }

    public boolean useCoffee() {
        if (coffeeCount > 0) {
            coffeeCount--;
            return true;
        }
        return false;
    }

    public boolean useTea() {
        if (teaCount > 0) {
            teaCount--;
            return true;
        }
        return false;
    }

    public void refillMilk(int amount) {
        milkCount += amount;
    }

    public void refillSugar(int amount) {
        sugarCount += amount;
    }

    public void refillSyrup(int amount) {
        syrupCount += amount;
    }

    public void refillCoffee(int amount) {
        coffeeCount += amount;
    }

    public void refillTea(int amount) {
        teaCount += amount;
    }

    public int getMilkCount() {
        return milkCount;
    }

    public int getSugarCount() {
        return sugarCount;
    }

    public int getSyrupCount() {
        return syrupCount;
    }

    public int getCoffeeCount() {
        return coffeeCount;
    }

    public int getTeaCount() {
        return teaCount;
    }
}
