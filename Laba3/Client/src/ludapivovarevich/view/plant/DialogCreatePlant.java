package ludapivovarevich.view.plant;

import com.doszhan.CustomServiceStub;
import ludapivovarevich.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class DialogCreatePlant {

    private JDialog dialog = new JDialog();
    private Controller controller;

    public DialogCreatePlant(Controller controller){
        this.controller = controller;
    }

    public void create(int index){

        dialog.setSize(400,500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel plantLabel = new JLabel("Название цветка: ");
        JTextField plantTextField = new JTextField(10);

        dialog.add(plantLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(plantTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel leafLabel = new JLabel("Размер листка: ");
        JTextField leafTextField = new JTextField(10);

        dialog.add(leafLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(leafTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel stemLabel = new JLabel("Размер стебля: ");
        JTextField stemTextField = new JTextField(10);

        dialog.add(stemLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(stemTextField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton addNotation = new JButton("Добавить запись");

        dialog.add(addNotation, new GridBagConstraints(0, 6, 2, 1, 2, 2,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);


        addNotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((plantTextField.getText()).trim().isEmpty() || (leafTextField.getText()).trim().isEmpty() ||
                        (stemTextField.getText()).trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Одно или несколько полей не заполнены");
                    return;
                } else {
                    try {

                        CustomServiceStub.Plant plant = new CustomServiceStub.Plant();
                        plant.setPlant(plantTextField.getText());
                        plant.setLeaf(leafTextField.getText());
                        plant.setStem(stemTextField.getText());


                        controller.addPlant(index, plant);
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }

                    dialog.dispose();
                }
            }
        });
    }
}
