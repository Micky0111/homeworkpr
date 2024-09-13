package controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class StartFrame extends JFrame {
   public StartFrame() {
       setTitle("選擇專區");
       setSize(800, 600); 
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(new GridBagLayout()); 
       GridBagConstraints gbc = new GridBagConstraints();
       // 設定背景顏色
       getContentPane().setBackground(new Color(198, 227, 236)); 
       // 標題
       JLabel titleLabel = new JLabel("信用卡兌換哩程平台系統", JLabel.CENTER);
       titleLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 28)); 
       titleLabel.setForeground(new Color(0, 102, 102)); 
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.gridwidth = 3;
       gbc.insets = new Insets(30, 0, 30, 0); 
       gbc.anchor = GridBagConstraints.CENTER;
       add(titleLabel, gbc);
       // 新卡友按鈕
       JButton newCardMemberButton = new JButton("申辦卡片");
       newCardMemberButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
       newCardMemberButton.setBackground(new Color(255, 255, 255));
       newCardMemberButton.setPreferredSize(new Dimension(300, 80)); 
       gbc.gridy = 1;
       gbc.gridwidth = 1;
       gbc.insets = new Insets(10, 0, 20, 0);
       add(newCardMemberButton, gbc);
       // 客戶專區按鈕
       JButton customerButton = new JButton("我已經是卡友");
       customerButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18)); 
       customerButton.setBackground(new Color(255, 255, 255));
       customerButton.setPreferredSize(new Dimension(300, 80)); 
       gbc.gridy = 2;
       add(customerButton, gbc);
       // 加入美觀的區隔線
       JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
       separator.setPreferredSize(new Dimension(300, 5)); 
       separator.setForeground(Color.BLACK); 
       gbc.gridy = 3;
       gbc.insets = new Insets(30, 0, 30, 0); 
       add(separator, gbc);
       // 員工專區按鈕
       JButton employeeButton = new JButton("員工專區");
       employeeButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));
       employeeButton.setBackground(new Color(255, 255, 255));
       employeeButton.setPreferredSize(new Dimension(300, 80)); 
       gbc.gridy = 4;
       gbc.insets = new Insets(10, 0, 0, 0); 
       add(employeeButton, gbc);
       // 客戶登入頁面
       customerButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new LoginFrame("customer").setVisible(true); // 客戶登入
               dispose();
           }
       });
       // 點擊後進入員工登入頁面
       employeeButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new LoginFrame("employee").setVisible(true); // 員工登入
               dispose();
           }
       });
       // 跳轉到註冊頁面
       newCardMemberButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new RegisterFrame(null).setVisible(true);  // 註冊頁面
               dispose();
           }
       });
       setVisible(true);
   }
   public static void main(String[] args) {
       new StartFrame();
   }
}
