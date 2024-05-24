import javax.swing.*;
import java.awt.*;

public class AddonsDialog extends JDialog {
    private JCheckBox milkCheckBox;
    private JCheckBox sugarCheckBox;
    private JCheckBox syrupCheckBox;
    private boolean confirmed;

    public AddonsDialog(JFrame parent) {
        super(parent, "Выбор добавок", true);
        setLayout(new GridLayout(4, 1));
        milkCheckBox = new JCheckBox("Добавить молоко");
        sugarCheckBox = new JCheckBox("Добавить сахар");
        syrupCheckBox = new JCheckBox("Добавить сироп");

        add(milkCheckBox);
        add(sugarCheckBox);
        add(syrupCheckBox);

        JButton confirmButton = new JButton("Подтвердить");
        confirmButton.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });
        add(confirmButton);

        pack();
        setLocationRelativeTo(parent);
    }

    public boolean isMilkSelected() {
        return milkCheckBox.isSelected();
    }

    public boolean isSugarSelected() {
        return sugarCheckBox.isSelected();
    }

    public boolean isSyrupSelected() {
        return syrupCheckBox.isSelected();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
