package controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.util.Random;
import dao.CustomerDAO;
import dao.MySQLConnection;
import model.Customer;
import java.sql.Connection;

public class RegisterFrame extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField idField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private String cardNumber; // 卡號
    private JButton copyButton; 

    public RegisterFrame(CustomerDAO customerDAO) {
        setTitle("申辦新卡片");
        setSize(800, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(224, 255, 255)); 

        
        JLabel nameLabel = new JLabel("您的名字:");
        nameLabel.setBounds(200, 50, 150, 30);
        nameLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(350, 50, 250, 30);
        getContentPane().add(nameField);

        // 手機號碼
        JLabel phoneLabel = new JLabel("手機號碼:");
        phoneLabel.setBounds(200, 100, 150, 30);
        phoneLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        getContentPane().add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(350, 100, 250, 30);
        getContentPane().add(phoneField);

        // 帳號
        JLabel idLabel = new JLabel("設定帳號:");
        idLabel.setBounds(200, 150, 150, 30);
        idLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(350, 150, 250, 30);
        getContentPane().add(idField);

        // 密碼
        JLabel passwordLabel = new JLabel("設定密碼:");
        passwordLabel.setBounds(200, 200, 150, 30);
        passwordLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16)); // 更新字體
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(350, 200, 250, 30);
        getContentPane().add(passwordField);

        // 註冊
        JButton registerButton = new JButton("Step1 : 註冊");
        registerButton.setBounds(200, 281, 211, 102);
        registerButton.setBackground(new Color(224, 255, 255));  // 綠色按鈕
        registerButton.setForeground(UIManager.getColor("Button.foreground"));
        registerButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20)); // 更新字體
        getContentPane().add(registerButton);

        // 回上一頁
        JButton backButton = new JButton("Step3 : 回上一頁 卡友登入");
        backButton.setBounds(200, 471, 400, 42);
        backButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20)); // 更新字體
        getContentPane().add(backButton);

        // 複製卡號按鈕
        copyButton = new JButton("Step2 : 請按我 複製卡號");
        copyButton.setBounds(200, 395, 400, 50);
        copyButton.setEnabled(false); // 卡號後才啟用
        copyButton.setBackground(new Color(255, 255, 255));  
        copyButton.setForeground(new Color(0, 0, 0));
        copyButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20)); // 更新字體
        getContentPane().add(copyButton);

        // 註冊完成
        messageLabel = new JLabel();
        messageLabel.setBounds(423, 285, 302, 92);
        messageLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14)); // 更新字體
        getContentPane().add(messageLabel);

        // 註冊
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        // 回上一頁
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartFrame().setVisible(true);  // 回 StartFrame
                dispose();  // 關閉
            }
        });

        // 複製卡號功能
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cardNumber != null) {
                    StringSelection stringSelection = new StringSelection(cardNumber);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                    JOptionPane.showMessageDialog(null, "卡號已複製到剪貼簿！");
                }
            }
        });

        setVisible(true);
    }

    // 註冊
    private void handleRegister() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String id = idField.getText();
        String password = new String(passwordField.getPassword());

        // 隨機16位數卡號
        cardNumber = generateCardNumber();

        // 給50000哩程
        int miles = 50000;

        // 連接資料庫並存取
        Connection connection = MySQLConnection.getConnection();
        CustomerDAO customerDAO = new CustomerDAO(connection);

        Customer customer = new Customer(0, name, phone, cardNumber, miles, password);
        customerDAO.registerCustomer(customer);

        // 註冊完成訊息
        messageLabel.setText("<html>恭喜您申辦成功！<br><br>信用卡號碼: " + cardNumber + "<br>贈送的哩程: 50000哩</html>");

        // 啟用複製
        copyButton.setEnabled(true);
    }

    // 隨機生成16位數卡號
    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }
}

