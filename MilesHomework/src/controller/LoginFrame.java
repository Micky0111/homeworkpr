package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.CustomerDAO;
import dao.EmployeeDAO;
import dao.MySQLConnection;
import model.Customer;
import model.Employee;
import service.CustomerService;
import service.EmployeeService;

import java.sql.Connection;

public class LoginFrame extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private String userType;

    public LoginFrame(String userType) {
        this.userType = userType;
        setTitle(userType.equals("customer") ? "客戶登入" : "員工登入");
        setSize(600, 400);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());  
        GridBagConstraints gbc = new GridBagConstraints();

        // 設定背景顏色
        getContentPane().setBackground(new Color(198, 227, 236));  

        JLabel idLabel = new JLabel(userType.equals("customer") ? "信用卡號碼:" : "員工編號:");
        idLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));  // 更新字體
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(idLabel, gbc);

        idField = new JTextField(20);
        idField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));  // 更新字體
        gbc.gridx = 1;
        add(idField, gbc);

        JLabel passwordLabel = new JLabel("密碼:");
        passwordLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));  // 更新字體
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));  // 更新字體
        gbc.gridx = 1;
        add(passwordField, gbc);

        // 自動填入員工編號和密碼
        if (userType.equals("employee")) {
            idField.setText("1"); 
            passwordField.setText("1"); 
        }

        loginButton = new JButton("登入");
        loginButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));  // 更新字體
        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.insets = new Insets(20, 10, 20, 10);
        add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        setVisible(true);
    }

    private void handleLogin() {
        String id = idField.getText();
        String password = new String(passwordField.getPassword());

        Connection connection = MySQLConnection.getConnection();

        if (userType.equals("customer")) {
            CustomerDAO customerDAO = new CustomerDAO(connection);
            CustomerService customerService = new CustomerService(customerDAO);
            Customer customer = customerService.login(id, password);

            if (customer != null) {
                JOptionPane.showMessageDialog(this, "登入成功！你的飛行里程為: " + customer.getMiles());
                new RedeemFrame(customer, customerService).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "登入失敗，請檢查信用卡號碼或密碼！");
            }

        } else if (userType.equals("employee")) {
            EmployeeDAO employeeDAO = new EmployeeDAO(connection);
            EmployeeService employeeService = new EmployeeService(employeeDAO);
            Employee employee = employeeService.login(Integer.parseInt(id), password);

            if (employee != null) {
                JOptionPane.showMessageDialog(this, "員工登入成功！");
                new EmployeeFrame(employee, employeeService, new CustomerService(new CustomerDAO(connection))).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "登入失敗，請檢查員工編號或密碼！");
            }
        }
    }
}


