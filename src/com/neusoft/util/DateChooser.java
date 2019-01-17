package com.neusoft.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EventListener;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.EventListenerList;
 
/**
 * 日期选择器 采用触发事件的方式,这样的话,就可以将DateChooser添加到任何组件之上,
 * 任何组件都可以捕获日期选择的事件,然后进行相应处理
 * 
 * @author changwen
 * @date Aug 26, 2015
 */
 
@SuppressWarnings("all")
public class DateChooser extends JPanel {
 
    protected Color weekBackgroundColor = new Color(189, 235, 238);
    protected Color weekendBtnFontColor = new Color(240, 64, 64); // color
    protected Color selectedColor = weekBackgroundColor;
    protected Font labelFont = new Font("Arial", Font.PLAIN, 10);
    protected Color defaultBtnFontColor = Color.BLACK;
    protected Color defaultBtnBackgroundColor = Color.WHITE;
    private Calendar cal = null;
    private Calendar todayCal = null;
    private int year;
    private int month;
    private int day;
    private JPanel controllPanel = null;
    private JPanel dateContainerPanel = null;
    private JLabel todayLabel = null;
    protected DateButton[][] buttonDays = null;
    public JComboBox monthChoice;
    public JComboBox yearChoice;
    protected String[] weekTitle = { "星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    protected int[] months = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
    private EventListenerList actionListenerList = new EventListenerList();
    static String dateString = "";
    public DateChooser() {
        buttonDays = new DateButton[6][7];
        cal = Calendar.getInstance();
        todayCal = Calendar.getInstance();
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DATE);
        JPanel oprPanel = createControlPanel();
        this.setLayout(new BorderLayout(0, 0));
        dateContainerPanel = new JPanel();
        createDayPanel(cal);
        setActiveDay(day);
        this.add(oprPanel, BorderLayout.NORTH);
        this.add(dateContainerPanel, BorderLayout.CENTER);
 
    }
 
    @SuppressWarnings("all")
    public JPanel createControlPanel() {
        controllPanel = new JPanel();
        controllPanel.setBackground(Color.WHITE);
        Box hBox = Box.createHorizontalBox();
        String strToday = formatDate(todayCal);
        todayLabel = new JLabel(strToday);
        monthChoice = new JComboBox();
        for (int i = 0; i < months.length; i++) {
            monthChoice.addItem(months[i]);
        }
 
        monthChoice.setSelectedItem(months[month]);
        monthChoice.setPreferredSize(new Dimension(monthChoice.getPreferredSize().width,
                monthChoice.getPreferredSize().height));
 
        monthChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int i = monthChoice.getSelectedIndex();
                if (i >= 0) {
                    month = i;
                    Calendar cal = new GregorianCalendar(year, month, 1);
                    year = cal.get(Calendar.YEAR);
                    month = cal.get(Calendar.MONTH);
                    createDayPanel(cal);
                }
            }
        });
        int currentYear = todayCal.get(Calendar.YEAR);
        final int gapYears = 30;
        yearChoice = new JComboBox();
        for (int i = 1970; i < currentYear + gapYears; i++) {
            yearChoice.addItem(i);
        }
        yearChoice.setSelectedIndex(currentYear - 1970);
        yearChoice.setPreferredSize(new Dimension(yearChoice.getPreferredSize().width,
                yearChoice.getPreferredSize().height));
 
        yearChoice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
//                System.out.println(yearChoice.getSelectedIndex());
                if (yearChoice.getSelectedIndex() != gapYears) {
                    Integer selYear = (Integer) yearChoice.getSelectedItem();
                    Calendar cal = new GregorianCalendar(year, month, 1);
                    cal.set(Calendar.YEAR, selYear);
                    year = cal.get(Calendar.YEAR);
                    month = cal.get(Calendar.MONTH);
                    createDayPanel(cal);
                }
            }
        });
 
        hBox.add(todayLabel);
        hBox.add(Box.createHorizontalStrut(5));
        hBox.add(monthChoice);
        hBox.add(Box.createHorizontalStrut(8));
        hBox.add(yearChoice);
        hBox.add(Box.createHorizontalStrut(8));
 
        controllPanel.add(hBox, BorderLayout.NORTH);
        return controllPanel;
 
    }
 
    /**
     * 创建日期组件
     * 
     * @param cal
     *            void
     */
    public void createDayPanel(Calendar cal) {
        dateContainerPanel.removeAll();
        dateContainerPanel.revalidate();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        int weeks = cal.get(Calendar.WEEK_OF_MONTH);
 
        GridLayout grid = new GridLayout(7, 7, 0, 0);
        dateContainerPanel.setLayout(grid);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int weekday = cal.get(Calendar.DAY_OF_WEEK);
//        System.out.println("weekday+" + weekday);
        cal.add(Calendar.DAY_OF_MONTH, 1 - weekday);
//        System.out.println("Calendar.DAY_OF_MONTH=" + cal.get(Calendar.DAY_OF_MONTH));
 
        for (int i = 0; i < 7; i++) {
            JLabel weekLabel = new JLabel(weekTitle[i], SwingConstants.CENTER);
//            weekLabel.setFont(labelFont);
            weekLabel.setOpaque(true);
            weekLabel.setBackground(weekBackgroundColor);
            dateContainerPanel.add(weekLabel);
        }
        DayButtonActionListener dayButtonActionListener = new DayButtonActionListener();
 
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                int curMonth = cal.get(Calendar.MONTH);
                DateButton button = null;
                if (curMonth != month) {
                    button = new DateButton(" ");
                    button.setEnabled(false);
                    button.setBackground(defaultBtnBackgroundColor);
                } else {
                    int currentDay = cal.get(Calendar.DAY_OF_MONTH);
                    button = new DateButton(currentDay + "");
                    button.setHorizontalTextPosition(SwingConstants.RIGHT);
                    button.setFont(labelFont);
                    button.setBackground(defaultBtnBackgroundColor);
                    button.setSelectedBackground(weekBackgroundColor);
                    if (currentDay == todayCal.get(Calendar.DAY_OF_MONTH) && month == todayCal.get(Calendar.MONTH)
                            && year == todayCal.get(Calendar.YEAR)) {
                        button.setBorder(BorderFactory.createLineBorder(weekendBtnFontColor));
                    }
                    if (cal.get(Calendar.MONTH) != month) {
                        button.setForeground(Color.BLUE);
                    }
                    if (j == 0 || j == 6) {
                        button.setForeground(weekendBtnFontColor);
                    } else {
                        button.setForeground(defaultBtnFontColor);
                    }
 
                }
                button.addActionListener(dayButtonActionListener);
                buttonDays[i][j] = button;
                dateContainerPanel.add(buttonDays[i][j]);
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
 
    }
 
    /**
     * 选中莫一天
     * 
     * @param selectedDay
     *            void
     */
    public void setActiveDay(int selectedDay) {
        clearAllActiveDay();
        if (selectedDay <= 0) {
            day = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
        } else {
            day = selectedDay;
        }
        int leadGap = new GregorianCalendar(year, month, 1).get(Calendar.DAY_OF_WEEK) - 1;
        JButton selectedButton = buttonDays[(leadGap + selectedDay - 1) / 7][(leadGap + selectedDay - 1) % 7];
        selectedButton.setBackground(weekBackgroundColor);
    }
 
    /**
     * 清除所有选择的日期
     * 
     */
    public void clearAllActiveDay() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JButton button = buttonDays[i][j];
                if (button.getText() != null && button.getText().trim().length() > 0) {
                    button.setBackground(defaultBtnBackgroundColor);
                    button.revalidate();
                }
            }
 
        }
    }
 
    /**
     * 获取选中的日期
     * 
     * @return String
     */
    public String getSelectedDate() {
        Calendar cal = new GregorianCalendar(year, month, day);
        return formatDate(cal);
    }
 
    private String formatDate(Calendar cal) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(cal.getTime());
    }
 
    class DayButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if (button.getText() != null && button.getText().trim().length() > 0) {
                day = Integer.parseInt(button.getText());
                setActiveDay(day);
                fireActionPerformed(e);// fire button click event
                /**
                 * 采用触发事件的方式,这样的话,就可以将DateChooser添加到任何组件之上,
                 * 任何组件都可以捕获日期选择的事件,然后进行相应处理
                 */
            }
        }
    }
 
    public void addActionListener(ActionListener actionListener) {
        actionListenerList.add(ActionListener.class, actionListener);
    }
 
    public void removeActionListener(ActionListener actionListener) {
        actionListenerList.remove(ActionListener.class, actionListener);
    }
 
    /**
     * 事件管理,触发事件
     * 
     * @param actionEvent
     *            void
     */
    protected void fireActionPerformed(ActionEvent actionEvent) {
        EventListener listenerList[] = actionListenerList.getListeners(ActionListener.class);
        for (int i = 0, n = listenerList.length; i < n; i++) {
            ((ActionListener) listenerList[i]).actionPerformed(actionEvent);
        }
    }
 
    public static String chooseDate() {
    	JFrame frame = new JFrame("日期");
    	frame.setSize(320, 230);
    	final DateChooser datePicker = new DateChooser();
    	
        datePicker.addActionListener(new ActionListener() {// 事件捕获
                    public void actionPerformed(ActionEvent e) {
                    	dateString = datePicker.getSelectedDate();
//                        System.out.println(datePicker.getSelectedDate());
                    }
                });
        frame.getContentPane().add(datePicker);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return dateString;
	}
    public static void main(String[] args) {
 
        
        
        
 
    }
 
}
