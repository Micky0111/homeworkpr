package controller;

import model.Customer;
import model.Employee;
import service.CustomerService;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;

public class EmployeeFrame extends JFrame {
    private Employee employee;
    private EmployeeService employeeService;
    private CustomerService customerService;
    private JLabel infoLabel;
    private JTextArea receiptArea;  // 收據顯示

    public EmployeeFrame(Employee employee, EmployeeService employeeService, CustomerService customerService) {
        this.employee = employee;
        this.employeeService = employeeService;
        this.customerService = customerService;

        setTitle("員工操作介面");
        setSize(700, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        infoLabel = new JLabel("員工: " + employee.getEmployeeName());
        infoLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        infoLabel.setBounds(50, 20, 300, 30);
        getContentPane().add(infoLabel);

        JLabel cardNumberLabel = new JLabel("輸入客戶信用卡號碼(16碼）:");
        cardNumberLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        cardNumberLabel.setBounds(50, 70, 200, 30);
        getContentPane().add(cardNumberLabel);

        JTextField cardNumberField = new JTextField();
        cardNumberField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        cardNumberField.setBounds(250, 70, 150, 30);
        getContentPane().add(cardNumberField);

        JButton searchButton = new JButton("查詢客戶里程");
        searchButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        searchButton.setBounds(420, 70, 150, 30);
        getContentPane().add(searchButton);

        JLabel customerNameLabel = new JLabel(); // 顯示客戶名稱
        customerNameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        customerNameLabel.setBounds(50, 120, 300, 30);
        getContentPane().add(customerNameLabel);

        JLabel milesLabel = new JLabel(); // 顯示客戶里程
        milesLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        milesLabel.setBounds(50, 170, 300, 30);
        getContentPane().add(milesLabel);

        String[] options = {"台北到上海 - 7500里程", "台北到東京 - 10000里程", "台北到曼谷 - 12000里程",
                            "台北到巴黎 - 28000里程", "台北到紐約 - 35000里程"};
        JComboBox<String> flightComboBox = new JComboBox<>(options);
        flightComboBox.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        flightComboBox.setBounds(50, 220, 300, 30);
        getContentPane().add(flightComboBox);

        JButton redeemButton = new JButton("兌換里程");
        redeemButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        redeemButton.setBounds(50, 270, 150, 30);
        redeemButton.setEnabled(false); // 兌換按鈕先不要
        getContentPane().add(redeemButton);

        // 新增收據
        receiptArea = new JTextArea();
        receiptArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        receiptArea.setBounds(50, 320, 600, 150);
        receiptArea.setEditable(false);  // 用戶不可編輯收據
        receiptArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(receiptArea);

        // 回上一頁
        JButton backButton = new JButton("回上一頁");
        backButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        backButton.setBounds(50, 500, 100, 30);
        getContentPane().add(backButton);

        // 離開
        JButton exitButton = new JButton("離開");
        exitButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        exitButton.setBounds(550, 500, 100, 30);
        getContentPane().add(exitButton);

        // 新增 列印
        JButton printButton = new JButton("列印");
        printButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        printButton.setBounds(300, 500, 100, 30);
        getContentPane().add(printButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cardNumber = cardNumberField.getText();
                Customer customer = employeeService.searchCustomerMiles(customerService, cardNumber);

                if (customer != null) {
                    customerNameLabel.setText("客戶姓名: " + customer.getName()); // 客戶姓名
                    milesLabel.setText("客戶目前飛行里程: " + customer.getMiles()); // 里程
                    redeemButton.setEnabled(true); // 找到客戶後，再啟用兌換按鈕
                } else {
                    customerNameLabel.setText("查無此客戶！");
                    milesLabel.setText("");
                    redeemButton.setEnabled(false); // 禁用兌換按鈕
                }
            }
        });

        redeemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedMiles = 0;
                String selectedFlight = (String) flightComboBox.getSelectedItem();

                if (selectedFlight.contains("7500")) {
                    selectedMiles = 7500;
                } else if (selectedFlight.contains("10000")) {
                    selectedMiles = 10000;
                } else if (selectedFlight.contains("12000")) {
                    selectedMiles = 12000;
                } else if (selectedFlight.contains("28000")) {
                    selectedMiles = 28000;
                } else if (selectedFlight.contains("35000")) {
                    selectedMiles = 35000;
                }

                String cardNumber = cardNumberField.getText();
                Customer customer = employeeService.searchCustomerMiles(customerService, cardNumber);

                if (customer != null && customerService.redeemMiles(customer, selectedMiles)) {
                    milesLabel.setText("兌換成功！剩餘里程: " + customer.getMiles());

                    // 顯示收據資訊
                    receiptArea.setText("=== 兌換收據 ===\n" +
                            "員工: " + employee.getEmployeeName() + "\n" +
                            "客戶姓名: " + customer.getName() + "\n" +
                            "卡號: " + customer.getCardNumber() + "\n" +
                            "原始里程: " + (customer.getMiles() + selectedMiles) + "\n" +
                            "兌換里程: " + selectedMiles + "\n" +
                            "剩餘里程: " + customer.getMiles() + "\n" +
                            "=================\n");
                } else {
                    receiptArea.setText("里程不足，兌換失敗！");
                }
            }
        });

        // 回上一頁
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // 關閉頁面
                new StartFrame().setVisible(true);  // 返回到 StartFrame
            }
        });

        // 離開按鈕的功能
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 離開
            }
        });

        // 列印
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        if (pageIndex > 0) {
                            return NO_SUCH_PAGE;
                        }

                        // 列印
                        g.drawString(receiptArea.getText(), 100, 100);
                        return PAGE_EXISTS;
                    }
                });

                // 列印框
                if (printerJob.printDialog()) {
                    try {
                        printerJob.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        setVisible(true);
    }
}

