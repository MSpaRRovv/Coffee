import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VendingMachineGUI extends JFrame {
    private Inventory inventory = new Inventory();
    private JTextArea displayArea;
    private JComboBox<String> beverageComboBox;
    private JButton takeOrderButton;
    private Beverage currentBeverage;

    public VendingMachineGUI() {
        setTitle("Кофейный автомат");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 2));

        JLabel beverageLabel = new JLabel("Выберите напиток:");
        controlPanel.add(beverageLabel);

        beverageComboBox = new JComboBox<>(new String[]{"Кофе", "Чай"});
        controlPanel.add(beverageComboBox);

        JButton prepareButton = new JButton("Приготовить напиток");
        prepareButton.addActionListener(new PrepareButtonListener());
        controlPanel.add(prepareButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inventoryPanel = new JPanel();
        inventoryPanel.setLayout(new GridLayout(6, 1));

        JButton refillMilkButton = new JButton("Пополнить молоко");
        refillMilkButton.addActionListener(e -> {
            inventory.refillMilk(10);
            updateInventoryDisplay();
        });
        inventoryPanel.add(refillMilkButton);

        JButton refillSugarButton = new JButton("Пополнить сахар");
        refillSugarButton.addActionListener(e -> {
            inventory.refillSugar(10);
            updateInventoryDisplay();
        });
        inventoryPanel.add(refillSugarButton);

        JButton refillSyrupButton = new JButton("Пополнить сироп");
        refillSyrupButton.addActionListener(e -> {
            inventory.refillSyrup(10);
            updateInventoryDisplay();
        });
        inventoryPanel.add(refillSyrupButton);

        JButton refillCoffeeButton = new JButton("Пополнить кофе");
        refillCoffeeButton.addActionListener(e -> {
            inventory.refillCoffee(10);
            updateInventoryDisplay();
        });
        inventoryPanel.add(refillCoffeeButton);

        JButton refillTeaButton = new JButton("Пополнить чай");
        refillTeaButton.addActionListener(e -> {
            inventory.refillTea(10);
            updateInventoryDisplay();
        });
        inventoryPanel.add(refillTeaButton);

        takeOrderButton = new JButton("Забрать заказ");
        takeOrderButton.setEnabled(false);
        takeOrderButton.addActionListener(new TakeOrderButtonListener());
        inventoryPanel.add(takeOrderButton);

        add(inventoryPanel, BorderLayout.SOUTH);
        updateInventoryDisplay();
    }

    private void updateInventoryDisplay() {
        displayArea.setText("");
        displayArea.append("Осталось молока: " + inventory.getMilkCount() + "\n");
        displayArea.append("Осталось сахара: " + inventory.getSugarCount() + "\n");
        displayArea.append("Осталось сиропа: " + inventory.getSyrupCount() + "\n");
        displayArea.append("Осталось кофе: " + inventory.getCoffeeCount() + "\n");
        displayArea.append("Осталось чая: " + inventory.getTeaCount() + "\n");
    }

    private class PrepareButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Beverage beverage;
            String selectedBeverage = (String) beverageComboBox.getSelectedItem();
            if (selectedBeverage.equals("Кофе")) {
                beverage = new Coffee(inventory);
            } else {
                beverage = new Tea(inventory);
            }

            AddonsDialog addonsDialog = new AddonsDialog(VendingMachineGUI.this);
            addonsDialog.setVisible(true);

            if (addonsDialog.isConfirmed()) {
                if (addonsDialog.isMilkSelected()) {
                    beverage = new Milk(beverage, inventory);
                }
                if (addonsDialog.isSugarSelected()) {
                    beverage = new Sugar(beverage, inventory);
                }
                if (addonsDialog.isSyrupSelected()) {
                    beverage = new Syrup(beverage, inventory);
                }

                displayArea.append("Приготовление: " + beverage.getDescription() + "\n");
                new Thread(new BeveragePreparationTask(beverage)).start();
            }
        }
    }

    private class BeveragePreparationTask implements Runnable {
        private Beverage beverage;

        public BeveragePreparationTask(Beverage beverage) {
            this.beverage = beverage;
        }

        @Override
        public void run() {
            try {
                beverage.prepare(); // Уменьшаем количество ингредиентов при приготовлении напитка
                Thread.sleep(3000); // Симуляция времени приготовления
                SwingUtilities.invokeLater(() -> {
                    displayArea.append(beverage.getDescription() + " готов!\n");
                    takeOrderButton.setEnabled(true);
                    currentBeverage = beverage;
                    updateInventoryDisplay(); // Обновляем отображение количества ингредиентов
                });
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class TakeOrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            displayArea.append("Заказ забран: " + currentBeverage.getDescription() + "\n");
            takeOrderButton.setEnabled(false);
            currentBeverage = null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VendingMachineGUI().setVisible(true));
    }
}
