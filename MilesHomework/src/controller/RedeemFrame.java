package controller;

import model.Customer;
import service.CustomerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RedeemFrame extends JFrame {
    private Customer customer;
    private CustomerService customerService;
    private JLabel milesLabel;
    private int selectedMiles;  // 儲存選擇的兌換里程
    private JTextArea receiptArea;  // 收據顯示
    private JButton printButton;  // 列印

    public RedeemFrame(Customer customer, CustomerService customerService) {
        this.customer = customer;
        this.customerService = customerService;

        setTitle("兌換里程");
        setSize(900, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        getContentPane().setBackground(new Color(230, 245, 255)); 

        // 顯示飛行里程
        milesLabel = new JLabel("目前飛行里程: " + customer.getMiles());
        milesLabel.setBounds(300, 50, 400, 30);
        milesLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 18));  // 更新字體
        milesLabel.setForeground(new Color(0, 102, 204));  
        getContentPane().add(milesLabel);

        // 建立航班清單區域
        JPanel flightPanel = new JPanel();
        flightPanel.setBounds(200, 100, 500, 200);
        flightPanel.setLayout(new GridLayout(5, 1, 10, 10));  
        getContentPane().add(flightPanel);

        // 每個航班選項
        JRadioButton flightOption1 = new JRadioButton("台北到上海 - 7500里程");
        JRadioButton flightOption2 = new JRadioButton("台北到東京 - 10000里程");
        JRadioButton flightOption3 = new JRadioButton("台北到曼谷 - 12000里程");
        JRadioButton flightOption4 = new JRadioButton("台北到巴黎 - 28000里程");
        JRadioButton flightOption5 = new JRadioButton("台北到紐約 - 35000里程");

        // 只能選擇一個
        ButtonGroup flightGroup = new ButtonGroup();
        flightGroup.add(flightOption1);
        flightGroup.add(flightOption2);
        flightGroup.add(flightOption3);
        flightGroup.add(flightOption4);
        flightGroup.add(flightOption5);

        flightPanel.add(flightOption1);
        flightPanel.add(flightOption2);
        flightPanel.add(flightOption3);
        flightPanel.add(flightOption4);
        flightPanel.add(flightOption5);

        // 兌換按鈕
        JButton redeemButton = new JButton("兌換里程");
        redeemButton.setBounds(400, 350, 150, 40);
        redeemButton.setBackground(new Color(0, 153, 76));  // 綠色按鈕
        redeemButton.setForeground(new Color(0, 0, 0));
        redeemButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));  // 更新字體
        getContentPane().add(redeemButton);

        // 新增收據區域
        receiptArea = new JTextArea();
        receiptArea.setBounds(200, 400, 500, 100);
        receiptArea.setEditable(false);  // 禁止用戶編輯收據內容
        receiptArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));  // 更新字體
        getContentPane().add(receiptArea);

        // 回上一頁
        JButton backButton = new JButton("回上一頁");
        backButton.setBounds(200, 520, 100, 30);
        getContentPane().add(backButton);

        // 離開
        JButton exitButton = new JButton("離開");
        exitButton.setBounds(600, 520, 100, 30);
        getContentPane().add(exitButton);

        // 列印默認禁用
        printButton = new JButton("列印");
        printButton.setBounds(400, 520, 100, 30);
        printButton.setEnabled(false);  // 禁用列印按鈕，直到兌換成功
        getContentPane().add(printButton);

        // 兌換里程
        redeemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flightOption1.isSelected()) {
                    selectedMiles = 7500;
                } else if (flightOption2.isSelected()) {
                    selectedMiles = 10000;
                } else if (flightOption3.isSelected()) {
                    selectedMiles = 12000;
                } else if (flightOption4.isSelected()) {
                    selectedMiles = 28000;
                } else if (flightOption5.isSelected()) {
                    selectedMiles = 35000;
                }

                if (customerService.redeemMiles(customer, selectedMiles)) {
                    milesLabel.setText("兌換成功！剩餘里程: " + customer.getMiles());
                    String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
                    // 顯示收據
                    receiptArea.setText("=== 兌換收據 ===\n" +
                            "客戶姓名: " + customer.getName() + "\n" +
                            "卡號: " + customer.getCardNumber() + "\n" +
                            "原始里程: " + (customer.getMiles() + selectedMiles) + "\n" +
                            "兌換里程: " + selectedMiles + "\n" +
                            "剩餘里程: " + customer.getMiles() + "\n" +
                            "兌換時間: " + currentTime + "\n" +
                            "=================\n");
                    printButton.setEnabled(true);  // 啟用列印按鈕
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

        // 離開
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        setVisible(true);
    }
}


