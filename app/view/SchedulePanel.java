package app.view;

import com.toedter.calendar.*;
import config.Config;
import gui.widgets.ColorTransitionButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SchedulePanel extends JPanel {

    ScheduleCalendar scheduleCalendar;
    private DefaultTableModel tableModel;
    public JTable listEmployee;
    private JTextField searchBar;
    public JButton searchBtn;
    public JRadioButton employeeNameRadio;
    public JRadioButton employeeIdRadio;

    public SchedulePanel()
    {
        super();
        setLayout(null);
        loadComponents();
    }

    private void loadComponents(){
        scheduleCalendar = new ScheduleCalendar();
        scheduleCalendar.setBounds(Config.resize(new Rectangle(330, 50, 580, 400)));
        add(scheduleCalendar);

        listEmployee = new JTable();
        listEmployee.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listEmployee.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listEmployee.setRowHeight(Config.resize(25));
        JScrollPane paneList = new JScrollPane(listEmployee);
        paneList.setBorder(BorderFactory.createTitledBorder(config.Config.EMPLOYEE_LIST));
        paneList.setBounds(Config.resize(new Rectangle(20, 180, 300, 320)));
        add(paneList);
        tableModel = new DefaultTableModel(new Object[][]{}, config.Config.EMPLOYEE_LIST_COLUMN) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        listEmployee.setModel(tableModel);
        listEmployee.getColumnModel().getColumn(0).setPreferredWidth(Config.resize(30));
        listEmployee.getColumnModel().getColumn(1).setPreferredWidth(Config.resize(100));
        listEmployee.getColumnModel().getColumn(2).setPreferredWidth(Config.resize(150));
        // search panel
        JPanel searchpnl = new JPanel(null);
        searchpnl.setBorder(BorderFactory.createTitledBorder(config.Config.EMPLOYEE_SEARCH));
        searchpnl.setBounds(Config.resize(new Rectangle(20, 40, 300, 120)));
        add(searchpnl);
        searchBar = new JTextField();
        searchBar.setBounds(Config.resize(new Rectangle(5, 30, 200, 30)));
        searchBtn = new ColorTransitionButton(config.Config.EMPLOYEE_SEARCH);

        searchBtn.setBounds(Config.resize(new Rectangle(205, 30, 90, 30)));
        employeeNameRadio = new JRadioButton(config.Config.EMPLOYEE_NAME);
        employeeIdRadio = new JRadioButton(config.Config.EMPLOYEE_ID);
        employeeNameRadio.setBounds(Config.resize(new Rectangle(25, 70, 120, 30)));
        employeeIdRadio.setBounds(Config.resize(new Rectangle(150, 70, 100, 30)));
        ButtonGroup radioGroup = new ButtonGroup();
        employeeNameRadio.setSelected(true);
        radioGroup.add(employeeIdRadio);
        radioGroup.add(employeeNameRadio);
        searchpnl.add(searchBar);
        searchpnl.add(searchBtn);
        searchpnl.add(employeeNameRadio);
        searchpnl.add(employeeIdRadio);
    }
}

class ScheduleCalendar extends JPanel{

    static final String[] months = { "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December" };

    static final String[] dow = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    static final int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */
            31, 30, 31, 31, /* may jun jul aug */
            30, 31, 30, 31 /* sep oct nov dec */
    };


    protected int mm;
    protected int yy;

    Calendar today;


    protected JMonthChooser month_choice = new JMonthChooser();
    protected JYearChooser year_choice = new JYearChooser();
    protected JButton days_buttons[];

    public ScheduleCalendar()
    {
        super();
        setLayout(new GridBagLayout());

        today = new GregorianCalendar();
        mm = today.get(Calendar.MONTH);
        yy = today.get(Calendar.YEAR);

        loadUI();
        updateContent();
    }

    private void loadUI()
    {
        month_choice.setMonth(mm);
        year_choice.setYear(yy);
        days_buttons = new JButton[42];

        JPanel days_panel = new JPanel();
        days_panel.setLayout(new GridLayout(7, 7));

        for(String d : dow){
            JLabel label = new JLabel(d);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            days_panel.add(label);
        }

        for (int i = 0; i < 42; i++)
        {
            days_panel.add(days_buttons[i] = new JButton(""));
            days_buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    System.out.println(new GregorianCalendar(yy, mm, Integer.parseInt(button.getText())).getTime());
                }
            });
        }

        month_choice.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                mm = month_choice.getMonth();
                updateContent();
            }
        });
        year_choice.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                yy = year_choice.getYear();
                updateContent();
            }
        });



        GridBagConstraints month_choice_constraint = new GridBagConstraints();
        month_choice_constraint.gridx = 0;
        month_choice_constraint.weightx = 1.0;
        month_choice_constraint.fill = GridBagConstraints.BOTH;
        add(month_choice, month_choice_constraint);

        GridBagConstraints year_choice_constraint = new GridBagConstraints();
        year_choice_constraint.gridx = 1;
        year_choice_constraint.weightx = 1.0;
        year_choice_constraint.fill = GridBagConstraints.BOTH;
        add(year_choice, year_choice_constraint);

        days_panel.setPreferredSize(Config.resize(new Dimension(600, 400)));
        GridBagConstraints days_panel_choice_constraint = new GridBagConstraints();
        days_panel_choice_constraint.gridx = 0;
        days_panel_choice_constraint.gridy = 1;
        days_panel_choice_constraint.weightx = 1.0;
        days_panel_choice_constraint.weighty = 1.0;
        days_panel_choice_constraint.gridwidth = 2;
        days_panel_choice_constraint.fill = GridBagConstraints.BOTH;
        add(days_panel, days_panel_choice_constraint);
    }


    private boolean isLeap(int year)
    {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }

    private int getDOM(int month, int year)
    {
        int days_of_month = dom[month];
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) ?  ++days_of_month : days_of_month;
    }

    private void updateContent()
    {
        int day_of_month = getDOM(mm, yy);
        today = Calendar.getInstance();
        GregorianCalendar last_month = new GregorianCalendar(yy, mm, 1);
//        last_month.add(Calendar.DAY_OF_WEEK, -1);
        int lead_gap = last_month.get(Calendar.DAY_OF_WEEK) - 1;
        last_month.add(Calendar.DATE, -lead_gap);
        int lead_day = last_month.get(Calendar.DAY_OF_MONTH);

        int foot_gap = 42 - lead_gap - day_of_month;

        for(int i = 0; i < lead_gap; ++i)
        {
            days_buttons[i].setText(String.valueOf(lead_day + i));
            days_buttons[i].setEnabled(false);
        }

        for(int i = 0; i < day_of_month; ++i)
        {
            days_buttons[lead_gap + i].setText(String.valueOf(i + 1));
            if(today.get(Calendar.DAY_OF_MONTH) == i + 1 && today.get(Calendar.MONTH) == mm && today.get(Calendar.YEAR) == yy)
                days_buttons[lead_gap + i].setForeground(Color.RED);
            else
                days_buttons[lead_gap + i].setForeground(Color.BLACK);

            days_buttons[lead_gap + i].setEnabled(true);
        }

        for(int i = 0; i < foot_gap; ++i)
        {
            days_buttons[lead_gap + day_of_month + i].setText(String.valueOf(i + 1));
            days_buttons[lead_gap + day_of_month + i].setEnabled(false);
        }

    }
}
