package ru.vsu.cs.course1;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.util.Date;


public class FrameMain extends JFrame {
    List<PlanetData> mainClassData = new ArrayList<PlanetData>();
    private JPanel panelMain;
    private JTable mainTable;
    private JFrame frame;
    private JPanel table;
    private JButton adder;
    private JButton remover;
    private JButton updateVisualisation;
    private JButton deleteSystem;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;
    private DefaultTableModel tableModel;
    DrawPanel dp;
    Date now = new Date();
    private Object[] columnsHeader = new String[]{
            "Наименование", "Скорость", "Радиус вращения", "Диаметр"
    };
    private Object[][] data = new String[][]{};


    //JTable solar = new JTable(data, columnsHeader);
    //JScrollPane scrollPane = new JScrollPane(solar);

    public FrameMain() {

        Timer obRunTime = new Timer(1000, e -> {
            frame.repaint();
        });

        obRunTime.start();
        this.setTitle("Редактор солнечной системы");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        frame = new JFrame();
        frame.setTitle("Визуализация солнечной системы");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        dp = new DrawPanel();
        dp.setSize(500, 500);
        frame.add(dp);

        frame.setSize(500, 500);
        frame.setVisible(true);

        menuBarMain = new JMenuBar();
        setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        //САМА ТАБЛИЦА

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnsHeader);
        for (int i = 0; i < data.length; i++)
            tableModel.addRow(data[i]);

        // Создание таблицы на основании модели данных
        mainTable = new JTable(tableModel);
        tableModel.insertRow(0, new String[]{"Наименование", "Скорость(км/с)", "Радиус вращения(км)", "Диаметр(км)"});

        mainTable.setRowHeight(30);
        mainTable.setSize(200, 200);
        table.add(mainTable);
        SolarSystem solarSystem = new SolarSystem();
        dp.setSolarSystem(solarSystem);

        adder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = 0;
                if (mainTable.getSelectedRow() > 1) {
                    idx = mainTable.getSelectedRow();
                } else {
                    idx = 0;
                }
                int i = mainTable.getRowCount();
                tableModel.insertRow(idx + 1, new String[]{
                        "Планета №" + String.valueOf(i),
                        "0", "0", "0"});
                Date now = new Date();
                long nowTime = now.getTime();
                String[][] dataField = JTableUtils.readStringMatrixFromJTable(mainTable);
                SolarSystem.planets.add(new SolarSystem.Planet(dataField[i][0],
                        Integer.parseInt(dataField[i][1]),
                        Integer.parseInt(dataField[i][2]),
                        Integer.parseInt(dataField[i][3]),
                        nowTime / 1000));
            }
        });

        remover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                int idx = mainTable.getSelectedRow();
                String[][] dataField = JTableUtils.readStringMatrixFromJTable(mainTable);

                for (int i = 0; i < SolarSystem.planets.size(); i++) {
                    if (SolarSystem.planets.get(i).getName() == dataField[idx][0]) {
                        SolarSystem.planets.remove(i);
                    }
                }
                // Удаление выделенной строки
                tableModel.removeRow(idx);
            }
        });
        updateVisualisation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                long nowTime = now.getTime();
                String[][] dataField = JTableUtils.readStringMatrixFromJTable(mainTable);
                for (int i = 1; i < dataField.length; i++) {
                    long timePlanet = SolarSystem.planets.get(i - 1).getTimeOfCreate();
                    SolarSystem.planets.set(i - 1, new SolarSystem.Planet(dataField[i][0],
                            Integer.parseInt(dataField[i][1]),
                            Integer.parseInt(dataField[i][2]),
                            Integer.parseInt(dataField[i][3]),
                            timePlanet / 1000));
                }
                frame.repaint();
            }
        });

        deleteSystem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SolarSystem.planets.clear();
                while (1 < tableModel.getRowCount()) {
                    tableModel.removeRow(1);
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayoutManager(3, 6, new Insets(10, 10, 10, 10), 10, 10));
        table = new JPanel();
        table.setLayout(new BorderLayout(0, 0));
        panelMain.add(table, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(900, 500), null, 0, false));
        remover = new JButton();
        remover.setText("Удалить");
        panelMain.add(remover, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        adder = new JButton();
        adder.setText("Добавить планету");
        panelMain.add(adder, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panelMain.add(spacer1, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panelMain.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panelMain.add(spacer3, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        updateVisualisation = new JButton();
        updateVisualisation.setText("Обновить визуализацию");
        panelMain.add(updateVisualisation, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteSystem = new JButton();
        deleteSystem.setText("Удалить все планеты");
        panelMain.add(deleteSystem, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panelMain;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}