package ludapivovarevich.view;

import com.doszhan.CustomServiceStub;
import ludapivovarevich.controller.Controller;
import ludapivovarevich.view.family_plants.DialogChangeFamilyPlants;
import ludapivovarevich.view.family_plants.DialogCreateFamilyPlants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class MyFrame {
    private String title;
    private Dimension d;
    private Controller controller;
    private JFrame frame = new JFrame();

    private JToolBar tb = new JToolBar();
    private JButton addButton = new JButton("Добавить семейство");
    private JButton showButton = new JButton("Обновить");
    private JButton changeButton = new JButton("Изменить описание семейства");
    private JButton deleteButton = new JButton("Удалить семейство");
    private JButton offButton = new JButton("Закрыть приложение");

    private JTabbedPane jtp = new JTabbedPane();
    private PanelInTab panelInTab;
    public MyFrame(String title, Dimension d, Controller controller) {
        this.title = title;
        this.d = d;
        this.controller = controller;
    }

    public void init(){
        frame.setTitle(title);
        frame.setSize(d);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        tb.add(addButton);
        tb.add(deleteButton);
        tb.add(changeButton);
        tb.add(showButton);
        tb.add(offButton);

        frame.getContentPane().add(tb, BorderLayout.NORTH);

        addButton.addActionListener(new createActionListener());
        changeButton.addActionListener(new changeActionListener());
        showButton.addActionListener(new showActionListener());
        deleteButton.addActionListener(new deleteActionListener());
        offButton.addActionListener(new offActionListener());

        frame.getContentPane().add(jtp);
        panelInTab = new PanelInTab(controller, MyFrame.this);

        frame.setVisible(true);
    }

    public class showActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jtp.removeAll();

            try {

                for(int i = 0; i < controller.getListFamilyPlants().size(); i++){
                    CustomServiceStub.FamilyPlants familyPlants = controller.getListFamilyPlants().get(i);
                    jtp.addTab(familyPlants.getName(), panelInTab.getPanel(familyPlants.getId()));
                }

            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreateFamilyPlants dialog = new DialogCreateFamilyPlants(controller);
            dialog.create();
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("delete "+getSelectedIndexTab());
                controller.deleteFamilyPlants(getSelectedIndexTab());
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangeFamilyPlants dialog = new DialogChangeFamilyPlants(controller);
            dialog.change(getSelectedIndexTab());
        }
    }

    public class offActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public int getSelectedIndexTab(){
        return jtp.getSelectedIndex();
    }
}
