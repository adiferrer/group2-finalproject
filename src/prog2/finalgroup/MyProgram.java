package prog2.finalgroup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class will contain the main method and also the code
 * that generates the GUI (Graphical User Interface).
 */
public class MyProgram extends JFrame {
    private ArrayList<Citizen> list = MyProgramUtility.parseCSV("res/data.csv");
    private ArrayList<Citizen> citizenArrayList;

    private JPanel buttonPaneGlobal;
    private JPanel buttonPaneGlobalSortOperations;
    private JPanel buttonPaneGlobalShowOperations;
    private JPanel buttonPaneDistrict;
    private JPanel buttonPaneDistrictSortOperations;
    private JPanel buttonPaneDistrictShowOperations;

    private JPanel citizenPane;

    private JTable citizenTable;
    private JScrollPane scrollPane;
    private JTabbedPane globalOrDistrictSelector;

    private JFrame popUpWindow;
    private JPanel popUpPanel;

    public MyProgram() {
        buttonPaneGlobal = new JPanel();
        buttonPaneGlobalSortOperations = new JPanel();
        buttonPaneGlobalShowOperations = new JPanel();
        buttonPaneDistrict = new JPanel();
        buttonPaneDistrictSortOperations = new JPanel();
        buttonPaneDistrictShowOperations = new JPanel();
        globalOrDistrictSelector = new JTabbedPane();
        citizenPane = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane();
        citizenTable = new JTable();
        String[] columnHeader = {"Full Name", "E-mail", "Address", "Age", "Resident", "District", "Gender"};

        popUpWindow = new JFrame("Input Window");

        // Global Buttons and associated actions
        var sortAccordingToAgeGloballyButton = new JButton("Age");
        sortAccordingToAgeGloballyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sets title to current operation
                setTitle("My Program: Citizens Sorted According to Age (Ascending Order)");

                // removes previous scroll pane
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                citizenArrayList = MyProgramUtility.sortAccordingToAgeGlobal(list.stream());

                // code utilizing JTable
                DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                for (Citizen c : citizenArrayList) {
                    Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                            c.getAge(), resOrNonRes(c.isResident()),
                            c.getDistrict(), maleOrFemale(c.getGender())};
                    tableModel.addRow(data);
                }

                // Auto-resizing to cell content source code:
                // https://stackoverflow.com/questions/17858132/automatically-adjust-jtable-column-to-fit-content/25570812
                citizenTable = new JTable(tableModel) {
                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                        Component component = super.prepareRenderer(renderer, row, column);
                        int rendererWidth = component.getPreferredSize().width;
                        TableColumn tableColumn = getColumnModel().getColumn(column);
                        tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                // added 10 spaces so that the columns won't be too congested
                                getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                        return component;
                    }
                };
                citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                scrollPane = new JScrollPane(citizenTable);
                citizenPane.add(scrollPane);
                setMaximumSize(new Dimension(1400, 1000));
                setMinimumSize(new Dimension(1100, 550));
                pack();
                setLocationRelativeTo(null);
            }
        });
        buttonPaneGlobalSortOperations.add(sortAccordingToAgeGloballyButton);

        var sortAccordingToNameGloballyButton = new JButton("Name");
        sortAccordingToNameGloballyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle("My Program: Citizens Sorted According to Name (A-Z)");
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);
                citizenArrayList = MyProgramUtility.sortAccordingToLastNameGlobal(list.stream());

                // code utilizing JTable
                DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                for (Citizen c : citizenArrayList) {
                    Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                            c.getAge(), resOrNonRes(c.isResident()),
                            c.getDistrict(), maleOrFemale(c.getGender())};
                    tableModel.addRow(data);
                }

                citizenTable = new JTable(tableModel) {
                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                        Component component = super.prepareRenderer(renderer, row, column);
                        int rendererWidth = component.getPreferredSize().width;
                        TableColumn tableColumn = getColumnModel().getColumn(column);
                        tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                        return component;
                    }
                };
                citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                scrollPane = new JScrollPane(citizenTable);
                citizenPane.add(scrollPane);
                setMaximumSize(new Dimension(1400, 1000));
                setMinimumSize(new Dimension(1100, 550));
                pack();
                setLocationRelativeTo(null);
            }
        });
        buttonPaneGlobalSortOperations.add(sortAccordingToNameGloballyButton);

        var sortAccordingToDistrictGloballyButton = new JButton("District");
        sortAccordingToDistrictGloballyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle("My Program: Citizens Sorted According to District");
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);
                citizenArrayList = MyProgramUtility.sortAccordingToDistrictGlobal(list.stream());

                // code utilizing JTable
                DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                for (Citizen c : citizenArrayList) {
                    Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                            c.getAge(), resOrNonRes(c.isResident()),
                            c.getDistrict(), maleOrFemale(c.getGender())};
                    tableModel.addRow(data);
                }

                citizenTable = new JTable(tableModel) {
                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                        Component component = super.prepareRenderer(renderer, row, column);
                        int rendererWidth = component.getPreferredSize().width;
                        TableColumn tableColumn = getColumnModel().getColumn(column);
                        tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                        return component;
                    }
                };
                citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                scrollPane = new JScrollPane(citizenTable);
                citizenPane.add(scrollPane);
                setMaximumSize(new Dimension(1400, 1000));
                setMinimumSize(new Dimension(1100, 550));
                pack();
                setLocationRelativeTo(null);
            }
        });
        buttonPaneGlobalSortOperations.add(sortAccordingToDistrictGloballyButton);
        buttonPaneGlobalSortOperations.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Sort Operations"));

        var showResidentsGloballyButton = new JButton("Residents");
        showResidentsGloballyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle("My Program: Displaying List Of Resident Citizens");
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);
                citizenArrayList = MyProgramUtility.showResidentsGlobal(list.stream());

                // code utilizing JTable here
                DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                for (Citizen c : citizenArrayList) {
                    Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                            c.getAge(), resOrNonRes(c.isResident()),
                            c.getDistrict(), maleOrFemale(c.getGender())};
                    tableModel.addRow(data);
                }

                citizenTable = new JTable(tableModel) {
                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                        Component component = super.prepareRenderer(renderer, row, column);
                        int rendererWidth = component.getPreferredSize().width;
                        TableColumn tableColumn = getColumnModel().getColumn(column);
                        tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                        return component;
                    }
                };
                citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                scrollPane = new JScrollPane(citizenTable);
                citizenPane.add(scrollPane);
                setMaximumSize(new Dimension(1400, 1000));
                setMinimumSize(new Dimension(1100, 550));
                pack();
                setLocationRelativeTo(null);
            }
        });
        buttonPaneGlobalShowOperations.add(showResidentsGloballyButton);

        var showNonResidentsGloballyButton = new JButton("Non-residents");
        showNonResidentsGloballyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle("My Program: Displaying List Of Non-Resident Citizens");
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);
                citizenArrayList = MyProgramUtility.showNonResidentsGlobal(list.stream());

                // code utilizing JTable here
                DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                for (Citizen c : citizenArrayList) {
                    Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                            c.getAge(), resOrNonRes(c.isResident()),
                            c.getDistrict(), maleOrFemale(c.getGender())};
                    tableModel.addRow(data);
                }

                citizenTable = new JTable(tableModel) {
                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                        Component component = super.prepareRenderer(renderer, row, column);
                        int rendererWidth = component.getPreferredSize().width;
                        TableColumn tableColumn = getColumnModel().getColumn(column);
                        tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                        return component;
                    }
                };
                citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                scrollPane = new JScrollPane(citizenTable);
                citizenPane.add(scrollPane);
                setMaximumSize(new Dimension(1400, 1000));
                setMinimumSize(new Dimension(1100, 550));
                pack();
                setLocationRelativeTo(null);
            }
        });
        buttonPaneGlobalShowOperations.add(showNonResidentsGloballyButton);

        var showMalesGloballyButton = new JButton("Males");
        showMalesGloballyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle("My Program: Showing List Of Male Citizens");
                citizenArrayList = MyProgramUtility.showMalesGlobal(list.stream());
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                // code utilizing JTable here
                DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                for (Citizen c : citizenArrayList) {
                    Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                            c.getAge(), resOrNonRes(c.isResident()),
                            c.getDistrict(), maleOrFemale(c.getGender())};
                    tableModel.addRow(data);
                }

                citizenTable = new JTable(tableModel) {
                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                        Component component = super.prepareRenderer(renderer, row, column);
                        int rendererWidth = component.getPreferredSize().width;
                        TableColumn tableColumn = getColumnModel().getColumn(column);
                        tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                        return component;
                    }
                };
                citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                scrollPane = new JScrollPane(citizenTable);
                citizenPane.add(scrollPane);
                setResizable(false);
                /*setMaximumSize(new Dimension(1400, 1000));
                setMinimumSize(new Dimension(1100, 600));*/
                pack();
                setLocationRelativeTo(null);
            }
        });
        buttonPaneGlobalShowOperations.add(showMalesGloballyButton);

        var showFemalesGloballyButton = new JButton("Females");
        showFemalesGloballyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle("My Program: Showing List Of Female Citizens");
                citizenArrayList = MyProgramUtility.showFemalesGlobal(list.stream());
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                // code utilizing JTable here
                DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                for (Citizen c : citizenArrayList) {
                    Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                            c.getAge(), resOrNonRes(c.isResident()),
                            c.getDistrict(), maleOrFemale(c.getGender())};
                    tableModel.addRow(data);
                }

                citizenTable = new JTable(tableModel) {
                    @Override
                    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                        Component component = super.prepareRenderer(renderer, row, column);
                        int rendererWidth = component.getPreferredSize().width;
                        TableColumn tableColumn = getColumnModel().getColumn(column);
                        tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                        return component;
                    }
                };
                citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                scrollPane = new JScrollPane(citizenTable);
                citizenPane.add(scrollPane);
                setMaximumSize(new Dimension(1400, 1000));
                setMinimumSize(new Dimension(1100, 550));
                pack();
                setLocationRelativeTo(null);
            }
        });
        buttonPaneGlobalShowOperations.add(showFemalesGloballyButton);

        var showAgesWithinAGivenRangeGlobally = new JButton("Age Range");
        showAgesWithinAGivenRangeGlobally.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTitle("My Program: Showing Citizens Who Are Within A Given Age Range");
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                // utilize a popup window for accepting user input
                String age1, age2;
                int minAge = 0, maxAge = 0;

                do {
                    age1 = JOptionPane.showInputDialog(null, "Please enter the minimum age: ",
                            "Input Min. Age", JOptionPane.INFORMATION_MESSAGE);
                    if (age1 == null) {
                        setTitle("My Program");
                        break;
                    }

                    else if (age1.equalsIgnoreCase(""))
                        JOptionPane.showMessageDialog(null, "Please enter some data.",
                                "Blank Text Field", JOptionPane.ERROR_MESSAGE);
                    else {
                        try {
                            minAge = Integer.parseInt(age1);
                            if (minAge < 18 || minAge > 70)
                                JOptionPane.showMessageDialog(null,
                                        "Please enter a value between 18 and 70.", "Invalid Age",
                                        JOptionPane.ERROR_MESSAGE);
                            else if (minAge == 70) {
                                JOptionPane.showMessageDialog(null,
                                        "Maximum age entered. Click OK to proceed.", "Message",
                                        JOptionPane.INFORMATION_MESSAGE);
                                maxAge = 70;
                                break;
                            }
                        } catch (NumberFormatException numberFormatException) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number.",
                                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } while (minAge < 18 || minAge > 70 || age1.equalsIgnoreCase(""));

                if (age1 != null && minAge != 70) {
                    do {
                        age2 = JOptionPane.showInputDialog(null,
                                "Please enter the maximum age: ", "Input Max. Age",
                                JOptionPane.INFORMATION_MESSAGE);
                        if (age2 == null) {
                            minAge = 0;
                            setTitle("My Program");
                            break;
                        }
                        else if (age2.equalsIgnoreCase(""))
                            JOptionPane.showMessageDialog(null, "Please provide some data.",
                                    "Blank Text Field", JOptionPane.ERROR_MESSAGE);
                        else {
                            try {
                                maxAge = Integer.parseInt(age2);
                                if (maxAge < minAge || maxAge > 70)
                                    JOptionPane.showMessageDialog(null,
                                            "Please enter a value between " + minAge + " and 70.",
                                            "Invalid Age", JOptionPane.ERROR_MESSAGE);
                            } catch (NumberFormatException numberFormatException) {
                                JOptionPane.showMessageDialog(null,
                                        "Please enter a valid number.", "Invalid Input",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } while (maxAge > 70 || maxAge < minAge || age2.equalsIgnoreCase(""));
                }

                if ((minAge >= 18 && (maxAge >= minAge && maxAge <= 70)) || minAge == 70) {
                    if (minAge < maxAge)
                        setTitle("My Program: Showing Citizens Who Are Within " +
                                minAge + " And " + maxAge + " Years Old");
                    else setTitle("My Program: Showing Citizens Who Are " + minAge + " Years Old");
                    citizenArrayList = MyProgramUtility.showAgesWithinRangeGlobal(list.stream(), minAge, maxAge);

                    // code utilizing JTable here
                    DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                    for (Citizen c : citizenArrayList) {
                        Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                                c.getAge(), resOrNonRes(c.isResident()),
                                c.getDistrict(), maleOrFemale(c.getGender())};
                        tableModel.addRow(data);
                    }

                    citizenTable = new JTable(tableModel) {
                        @Override
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                            Component component = super.prepareRenderer(renderer, row, column);
                            int rendererWidth = component.getPreferredSize().width;
                            TableColumn tableColumn = getColumnModel().getColumn(column);
                            tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                    getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                            return component;
                        }
                    };
                    citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                    scrollPane = new JScrollPane(citizenTable);
                    citizenPane.add(scrollPane);
                    setMaximumSize(new Dimension(1400, 1000));
                    setMinimumSize(new Dimension(1100, 550));
                    pack();
                    setLocationRelativeTo(null);
                }
            }
        });
        buttonPaneGlobalShowOperations.add(showAgesWithinAGivenRangeGlobally);
        buttonPaneGlobalShowOperations.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Show Operations"));

        var sortAccordingToAgePerDistrictBtn = new JButton("Age");
        sortAccordingToAgePerDistrictBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);
                int district = getDistrict();

                if (district >= 1 && district <= 20) {
                    setTitle("My Program: Citizens From District " + district + " Sorted According to Age");
                    citizenArrayList = MyProgramUtility.sortAccordingToAgePerDistrict(list.stream(), district);
                    // code utilizing JTable here
                    DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                    for (Citizen c : citizenArrayList) {
                        Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                                c.getAge(), resOrNonRes(c.isResident()),
                                c.getDistrict(), maleOrFemale(c.getGender())};
                        tableModel.addRow(data);
                    }

                    citizenTable = new JTable(tableModel) {
                        @Override
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                            Component component = super.prepareRenderer(renderer, row, column);
                            int rendererWidth = component.getPreferredSize().width;
                            TableColumn tableColumn = getColumnModel().getColumn(column);
                            tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                    getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                            return component;
                        }
                    };
                    citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                    scrollPane = new JScrollPane(citizenTable);
                    citizenPane.add(scrollPane);
                    setMaximumSize(new Dimension(1400, 1000));
                    setMinimumSize(new Dimension(1100, 550));
                    pack();
                    setLocationRelativeTo(null);
                }
            }
        });
        buttonPaneDistrictSortOperations.add(sortAccordingToAgePerDistrictBtn);

        var sortAccordingToNamePerDistrictBtn = new JButton("Name");
        sortAccordingToNamePerDistrictBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int district = getDistrict();
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                if (district >= 1 && district <= 20) {
                    setTitle("My Program: Citizens From District " + district + " Sorted According to Full Name");
                    citizenArrayList = MyProgramUtility.sortAccordingToLastNamePerDistrict(list.stream(), district);
                    // code utilizing JTable here
                    DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                    for (Citizen c : citizenArrayList) {
                        Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                                c.getAge(), resOrNonRes(c.isResident()),
                                c.getDistrict(), maleOrFemale(c.getGender())};
                        tableModel.addRow(data);
                    }

                    citizenTable = new JTable(tableModel) {
                        @Override
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                            Component component = super.prepareRenderer(renderer, row, column);
                            int rendererWidth = component.getPreferredSize().width;
                            TableColumn tableColumn = getColumnModel().getColumn(column);
                            tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                    getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                            return component;
                        }
                    };
                    citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                    scrollPane = new JScrollPane(citizenTable);
                    citizenPane.add(scrollPane);
                    setMaximumSize(new Dimension(1400, 1000));
                    setMinimumSize(new Dimension(1100, 550));
                    pack();
                    setLocationRelativeTo(null);
                }
            }
        });
        buttonPaneDistrictSortOperations.add(sortAccordingToNamePerDistrictBtn);
        buttonPaneDistrictSortOperations.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Sort Operations"));

        var showResidentsPerDistrictBtn = new JButton("Residents");
        showResidentsPerDistrictBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int district = getDistrict();
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                if (district >= 1 && district <= 20) {
                    setTitle("My Program: Displaying List Of Resident Citizens From District " + district);
                    citizenArrayList = MyProgramUtility.showResidentsPerDistrict(list.stream(), district);
                    // code utilizing JTable here
                    DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                    for (Citizen c : citizenArrayList) {
                        Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                                c.getAge(), resOrNonRes(c.isResident()),
                                c.getDistrict(), maleOrFemale(c.getGender())};
                        tableModel.addRow(data);
                    }

                    citizenTable = new JTable(tableModel) {
                        @Override
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                            Component component = super.prepareRenderer(renderer, row, column);
                            int rendererWidth = component.getPreferredSize().width;
                            TableColumn tableColumn = getColumnModel().getColumn(column);
                            tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                    getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                            return component;
                        }
                    };
                    citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                    scrollPane = new JScrollPane(citizenTable);
                    citizenPane.add(scrollPane);
                    setMaximumSize(new Dimension(1400, 1000));
                    setMinimumSize(new Dimension(1100, 550));
                    pack();
                    setLocationRelativeTo(null);
                }

            }
        });
        buttonPaneDistrictShowOperations.add(showResidentsPerDistrictBtn);

        var showNonResidentsPerDistrictBtn = new JButton("Non-Residents");
        showNonResidentsPerDistrictBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int district = getDistrict();
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                if (district >= 1 && district <= 20) {
                    setTitle("My Program: Displaying List Of Non-Resident Citizens From District " + district);
                    citizenArrayList = MyProgramUtility.showNonResidentsPerDistrict(list.stream(), district);
                    // code utilizing JTable here
                    DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                    for (Citizen c : citizenArrayList) {
                        Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                                c.getAge(), resOrNonRes(c.isResident()),
                                c.getDistrict(), maleOrFemale(c.getGender())};
                        tableModel.addRow(data);
                    }

                    citizenTable = new JTable(tableModel) {
                        @Override
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                            Component component = super.prepareRenderer(renderer, row, column);
                            int rendererWidth = component.getPreferredSize().width;
                            TableColumn tableColumn = getColumnModel().getColumn(column);
                            tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                    getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                            return component;
                        }
                    };
                    citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                    scrollPane = new JScrollPane(citizenTable);
                    citizenPane.add(scrollPane);
                    setMaximumSize(new Dimension(1400, 1000));
                    setMinimumSize(new Dimension(1100, 550));
                    pack();
                    setLocationRelativeTo(null);
                }
            }
        });
        buttonPaneDistrictShowOperations.add(showNonResidentsPerDistrictBtn);

        var showMalesPerDistrictBtn = new JButton("Males");
        showMalesPerDistrictBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int district = getDistrict();
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                if (district >= 1 && district <= 20) {
                    setTitle("My Program: Displaying List Of Male Citizens From District " + district);
                    citizenArrayList = MyProgramUtility.showMalesPerDistrict(list.stream(), district);
                    // code utilizing JTable here
                    DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                    for (Citizen c : citizenArrayList) {
                        Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                                c.getAge(), resOrNonRes(c.isResident()),
                                c.getDistrict(), maleOrFemale(c.getGender())};
                        tableModel.addRow(data);
                    }

                    citizenTable = new JTable(tableModel) {
                        @Override
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                            Component component = super.prepareRenderer(renderer, row, column);
                            int rendererWidth = component.getPreferredSize().width;
                            TableColumn tableColumn = getColumnModel().getColumn(column);
                            tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                    getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                            return component;
                        }
                    };
                    citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                    scrollPane = new JScrollPane(citizenTable);
                    citizenPane.add(scrollPane);
                    setMaximumSize(new Dimension(1400, 1000));
                    setMinimumSize(new Dimension(1100, 550));
                    pack();
                    setLocationRelativeTo(null);
                }
            }
        });
        buttonPaneDistrictShowOperations.add(showMalesPerDistrictBtn);

        var showFemalesPerDistrictBtn = new JButton("Females");
        showFemalesPerDistrictBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int district = getDistrict();
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                if (district >= 1 && district <= 20) {
                    setTitle("My Program: Displaying List Of Female Citizens From District " + district);
                    citizenArrayList = MyProgramUtility.showFemalesPerDistrict(list.stream(), district);
                    // code utilizing JTable here
                    DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                    for (Citizen c : citizenArrayList) {
                        Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                                c.getAge(), resOrNonRes(c.isResident()),
                                c.getDistrict(), maleOrFemale(c.getGender())};
                        tableModel.addRow(data);
                    }

                    citizenTable = new JTable(tableModel) {
                        @Override
                        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                            Component component = super.prepareRenderer(renderer, row, column);
                            int rendererWidth = component.getPreferredSize().width;
                            TableColumn tableColumn = getColumnModel().getColumn(column);
                            tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                    getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                            return component;
                        }
                    };
                    citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                    scrollPane = new JScrollPane(citizenTable);
                    citizenPane.add(scrollPane);
                    setMaximumSize(new Dimension(1400, 1000));
                    setMinimumSize(new Dimension(1100, 550));
                    pack();
                    setLocationRelativeTo(null);
                }
            }
        });
        buttonPaneDistrictShowOperations.add(showFemalesPerDistrictBtn);

        var showAgesPerDistrictBtn = new JButton("Age Range");
        showAgesPerDistrictBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int district = getDistrict();
                if (scrollPane.isVisible())
                    scrollPane.setVisible(false);

                if (district >= 1 && district <= 20) {
                    String age1, age2;
                    int minAge = 0, maxAge = 0;

                    do {
                        age1 = JOptionPane.showInputDialog(null, "Please enter the minimum age: ",
                                "Input Min. Age", JOptionPane.INFORMATION_MESSAGE);
                        if (age1 == null) {
                            setTitle("My Program");
                            break;
                        }

                        else if (age1.equalsIgnoreCase(""))
                            JOptionPane.showMessageDialog(null, "Please enter some data.",
                                    "Blank Text Field", JOptionPane.ERROR_MESSAGE);
                        else {
                            try {
                                minAge = Integer.parseInt(age1);
                                if (minAge < 18 || minAge > 70)
                                    JOptionPane.showMessageDialog(null,
                                            "Please enter a value between 18 and 70.", "Invalid Age",
                                            JOptionPane.ERROR_MESSAGE);
                                else if (minAge == 70) {
                                    JOptionPane.showMessageDialog(null,
                                            "Maximum age entered. Click OK to proceed.", "Message",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    maxAge = 70;
                                    break;
                                }
                            } catch (NumberFormatException numberFormatException) {
                                JOptionPane.showMessageDialog(null, "Please enter a valid number.",
                                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } while (minAge < 18 || minAge > 70 || age1.equalsIgnoreCase(""));

                    if (age1 != null && minAge != 70) {
                        do {
                            age2 = JOptionPane.showInputDialog(null,
                                    "Please enter the maximum age: ", "Input Max. Age",
                                    JOptionPane.INFORMATION_MESSAGE);
                            if (age2 == null) {
                                minAge = 0;
                                setTitle("My Program");
                                break;
                            }
                            else if (age2.equalsIgnoreCase(""))
                                JOptionPane.showMessageDialog(null, "Please provide some data.",
                                        "Blank Text Field", JOptionPane.ERROR_MESSAGE);
                            else {
                                try {
                                    maxAge = Integer.parseInt(age2);
                                    if (maxAge < minAge || maxAge > 70)
                                        JOptionPane.showMessageDialog(null,
                                                "Please enter a value between " + minAge + " and 70.",
                                                "Invalid Age", JOptionPane.ERROR_MESSAGE);
                                } catch (NumberFormatException numberFormatException) {
                                    JOptionPane.showMessageDialog(null,
                                            "Please enter a valid number.", "Invalid Input",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } while (maxAge > 70 || maxAge < minAge || age2.equalsIgnoreCase(""));
                    }

                    if ((minAge >= 18 && (maxAge >= minAge && maxAge <= 70)) || minAge == 70) {
                        if (minAge < maxAge)
                            setTitle("My Program: Showing Citizens Who Are " +
                                    minAge + "-" + maxAge + " Years Old From District " + district);
                        else setTitle("My Program: Showing Citizens Who Are " + minAge + " Years Old From District " +
                                district);
                        citizenArrayList = MyProgramUtility.showAgesWithinRangePerDistrict(list.stream(), district,
                                minAge, maxAge);

                        // code utilizing JTable here
                        DefaultTableModel tableModel = new DefaultTableModel(columnHeader, 0);
                        for (Citizen c : citizenArrayList) {
                            Object[] data = {c.getFullName(), c.getEmail(), c.getAddress(),
                                    c.getAge(), resOrNonRes(c.isResident()),
                                    c.getDistrict(), maleOrFemale(c.getGender())};
                            tableModel.addRow(data);
                        }

                        citizenTable = new JTable(tableModel) {
                            @Override
                            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                                Component component = super.prepareRenderer(renderer, row, column);
                                int rendererWidth = component.getPreferredSize().width;
                                TableColumn tableColumn = getColumnModel().getColumn(column);
                                tableColumn.setPreferredWidth(Math.max(rendererWidth +
                                        getIntercellSpacing().width + 10, tableColumn.getPreferredWidth()));
                                return component;
                            }
                        };
                        citizenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                        scrollPane = new JScrollPane(citizenTable);
                        citizenPane.add(scrollPane);
                        setMaximumSize(new Dimension(1400, 1000));
                        setMinimumSize(new Dimension(1100, 550));
                        pack();
                        setLocationRelativeTo(null);
                    }
                }
            }
        });
        buttonPaneDistrictShowOperations.add(showAgesPerDistrictBtn);
        buttonPaneDistrictShowOperations.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Show Operations"));

        /* Configure the layouts of each panel
        buttonPaneGlobal.setLayout(new BoxLayout(buttonPaneGlobal, BoxLayout.PAGE_AXIS));
        buttonPaneGlobalSortOperations.setLayout(new BoxLayout(buttonPaneGlobalSortOperations, BoxLayout.PAGE_AXIS));
        buttonPaneGlobalShowOperations.setLayout(new BoxLayout(buttonPaneGlobalShowOperations, BoxLayout.PAGE_AXIS));
        buttonPaneDistrict.setLayout(new BoxLayout(buttonPaneDistrict, BoxLayout.PAGE_AXIS));
        buttonPaneDistrictSortOperations.setLayout(new BoxLayout(buttonPaneDistrictSortOperations, BoxLayout.PAGE_AXIS));
        buttonPaneDistrictShowOperations.setLayout(new BoxLayout(buttonPaneDistrictShowOperations, BoxLayout.PAGE_AXIS));
         */

        // Nest the JPanels
        buttonPaneGlobal.add(buttonPaneGlobalSortOperations);
        buttonPaneGlobal.add(buttonPaneGlobalShowOperations);
        buttonPaneDistrict.add(buttonPaneDistrictSortOperations);
        buttonPaneDistrict.add(buttonPaneDistrictShowOperations);


        // Configure the contents of JTab
        globalOrDistrictSelector.addTab("Global", buttonPaneGlobal);
        globalOrDistrictSelector.addTab("District", buttonPaneDistrict);

        add(citizenPane, BorderLayout.NORTH);
        add(globalOrDistrictSelector, BorderLayout.SOUTH);

        // Window options
        setTitle("My Program");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    protected static String resOrNonRes(boolean r) {
        if (r) return "Resident";
        return "Non-Resident";
    }

    protected static String maleOrFemale(char g) {
        if (g == 'F') return "Female";
        return "Male";
    }

    protected int getDistrict() {
        String input;
        int d = 0;
        do {
            input = JOptionPane.showInputDialog(null, "Please enter a district number:",
                    "Input for District", JOptionPane.INFORMATION_MESSAGE);
            if (input == null) {
                this.setTitle("My Program");
                return 0;
            } else if (input.equalsIgnoreCase(""))
                JOptionPane.showMessageDialog(null, "Please provide some data.",
                        "Blank Text Field", JOptionPane.ERROR_MESSAGE);
            else {
                try {
                    d = Integer.parseInt(input);
                    if (d < 1 || d > 20)
                        JOptionPane.showMessageDialog(null,
                                "Please enter a number between 1 and 20.", "Invalid District Number",
                                JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "Please enter a whole number.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (input.equalsIgnoreCase("") || d < 1 || d > 20);
        return d;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MyProgram());
    }
}
