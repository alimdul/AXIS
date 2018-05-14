package ludapivovarevich.view.family_plants;

import com.doszhan.CustomServiceStub;
import ludapivovarevich.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class DialogChangeFamilyPlants {

    private JDialog dialog = new JDialog();
    private Controller controller;

    public DialogChangeFamilyPlants(Controller controller) {
        this.controller = controller;
    }

    public void change(int index){

        System.out.println("change"+index);

        dialog.setSize(400,500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        String[] numberMonth = {
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };
        JComboBox comboBoxFirstMonth = new JComboBox(numberMonth);
        JComboBox comboBoxLastMonth = new JComboBox(numberMonth);

        JLabel nameFamilyPlants = new JLabel("Название семейства цветов: ");
        JTextField nameFamilyPlantsText = new JTextField(10);

        dialog.add(nameFamilyPlants, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(nameFamilyPlantsText, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel firstMonth = new JLabel("Первый месяц цветения: ");

        dialog.add(firstMonth, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(comboBoxFirstMonth, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel lastMonth = new JLabel("Последний месяц цветения: ");

        dialog.add(lastMonth, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(comboBoxLastMonth, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton changeNotation = new JButton("Изменить запись");

        dialog.add(changeNotation, new GridBagConstraints(0, 6, 2, 1, 2, 2,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);


        changeNotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameFamilyPlantsText.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Одно или несколько полей не заполнены");
                    return;
                } else {
                    CustomServiceStub.FamilyPlants familyPlants = new CustomServiceStub.FamilyPlants();
                    familyPlants.setId(index);
                    familyPlants.setName(nameFamilyPlantsText.getText());

                    CustomServiceStub.FloweringTime floweringTime = new CustomServiceStub.FloweringTime();
                    floweringTime.setFirstMonthFlowering(comboBoxFirstMonth.getSelectedItem().toString());
                    floweringTime.setLastMonthFlowering(comboBoxLastMonth.getSelectedItem().toString());

                    familyPlants.setFloweringTime(floweringTime);

                    try {
                        controller.changeFamilyPlants(familyPlants);
                        dialog.dispose();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }
}
