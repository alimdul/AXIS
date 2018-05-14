package ludapivovarevich.view;

import ludapivovarevich.controller.Controller;
import ludapivovarevich.view.plant.DialogChangePlant;
import ludapivovarevich.view.plant.DialogCreatePlant;
import ludapivovarevich.view.plant.DialogDeletePlant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class PanelInTab {

    private ModelTable model;
    private JTable table;
    private JScrollPane jsp;
    private JLabel labelMonths;
    private int index;
    private Controller controller;
    private MyFrame frame;
    private Map mapTableModel = new HashMap();

    public PanelInTab(Controller controller, MyFrame frame){
        this.controller = controller;
        this.frame = frame;
    }

    public JPanel getPanel(int i) throws RemoteException {
        index = i;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        model = new ModelTable();
        table = new JTable(model);
        jsp = new JScrollPane(table);

        mapTableModel.put(index-1, model);

        JPanel panel1 = new JPanel();

        JLabel labelTime = new JLabel("Время цветения(месяц): ");
        labelMonths = new JLabel(controller.getListFamilyPlants().get(index-1).getFloweringTime()
                .getFirstMonthFlowering()+" - "+
                controller.getListFamilyPlants().get(index-1).getFloweringTime()
                        .getLastMonthFlowering());
        JToolBar toolBar = new JToolBar();
        JButton buttonAdd = new JButton("Добавить цветок");
        JButton buttonDelete = new JButton("Удалить цветок");
        JButton buttonChange = new JButton("Изменить описание цветка");
        JButton buttonShow = new JButton("Посмотреть таблицу");

        toolBar.add(buttonAdd);
        toolBar.add(buttonDelete);
        toolBar.add(buttonChange);
        toolBar.add(buttonShow);
        toolBar.add(labelTime);
        toolBar.add(labelMonths);

        panel1.add(toolBar);

        panel.add(panel1, BorderLayout.NORTH);

        buttonAdd.addActionListener(new addActionListener());
        buttonDelete.addActionListener(new deleteActionListener());
        buttonChange.addActionListener(new changeActionListener());
        buttonShow.addActionListener(new showActionListener());

        jsp.setPreferredSize(new Dimension(700, 500));
        panel.add(jsp, BorderLayout.CENTER);

        return panel;
    }

    public class showActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ModelTable modelTable = (ModelTable) mapTableModel.get(frame.getSelectedIndexTab());
                modelTable.addNotation(controller.getListPlants(frame.getSelectedIndexTab()));
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        }
    }

    public class addActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreatePlant dialogCreatePlant = new DialogCreatePlant(controller);
            dialogCreatePlant.create(frame.getSelectedIndexTab());
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogDeletePlant dialogDeletePlant = new DialogDeletePlant(controller);
            dialogDeletePlant.delete(frame.getSelectedIndexTab());
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangePlant dialogChangePlant = new DialogChangePlant(controller);
            dialogChangePlant.change(frame.getSelectedIndexTab());
        }
    }
}
