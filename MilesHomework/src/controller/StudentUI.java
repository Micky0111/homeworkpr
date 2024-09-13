package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Student;
import service.impl.StudentServiceImpl;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name1;
	private JTextField chi1;
	private JTextField eng1;
	private JTextField id;
	private JTextField name2;
	private JTextField chi2;
	private JTextField eng2;
	private JTextField id2;
	
	private static StudentServiceImpl ssi=new StudentServiceImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentUI frame = new StudentUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(26, 27, 691, 57);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("學生成績系統");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("新細明體", Font.BOLD, 18));
		lblNewLabel.setBounds(278, 10, 119, 37);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(26, 94, 691, 107);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblVuau = new JLabel("姓名");
		lblVuau.setForeground(Color.WHITE);
		lblVuau.setFont(new Font("新細明體", Font.BOLD, 18));
		lblVuau.setBounds(10, 31, 52, 37);
		panel_1.add(lblVuau);
		
		JLabel lblVuau_1 = new JLabel("國文");
		lblVuau_1.setForeground(Color.WHITE);
		lblVuau_1.setFont(new Font("新細明體", Font.BOLD, 18));
		lblVuau_1.setBounds(176, 31, 52, 37);
		panel_1.add(lblVuau_1);
		
		JLabel lblVuau_2 = new JLabel("英文");
		lblVuau_2.setForeground(Color.WHITE);
		lblVuau_2.setFont(new Font("新細明體", Font.BOLD, 18));
		lblVuau_2.setBounds(334, 31, 52, 37);
		panel_1.add(lblVuau_2);
		
		name1 = new JTextField();
		name1.setBounds(62, 40, 96, 21);
		panel_1.add(name1);
		name1.setColumns(10);
		
		chi1 = new JTextField();
		chi1.setBounds(224, 40, 96, 21);
		panel_1.add(chi1);
		chi1.setColumns(10);
		
		eng1 = new JTextField();
		eng1.setBounds(396, 40, 96, 21);
		panel_1.add(eng1);
		eng1.setColumns(10);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(26, 211, 691, 196);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 671, 105);
		panel_2.add(scrollPane);
		
		JTextArea output = new JTextArea();
		scrollPane.setViewportView(output);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(128, 128, 128));
		panel_3.setBounds(26, 417, 691, 66);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblId = new JLabel("id");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("新細明體", Font.BOLD, 18));
		lblId.setBounds(20, 10, 36, 37);
		panel_3.add(lblId);
		
		JLabel lblVuau_3 = new JLabel("姓名");
		lblVuau_3.setForeground(Color.WHITE);
		lblVuau_3.setFont(new Font("新細明體", Font.BOLD, 18));
		lblVuau_3.setBounds(143, 10, 52, 37);
		panel_3.add(lblVuau_3);
		
		JLabel lblEjijp = new JLabel("國文");
		lblEjijp.setForeground(Color.WHITE);
		lblEjijp.setFont(new Font("新細明體", Font.BOLD, 18));
		lblEjijp.setBounds(293, 10, 52, 37);
		panel_3.add(lblEjijp);
		
		JLabel lblVuau_4 = new JLabel("英文");
		lblVuau_4.setForeground(Color.WHITE);
		lblVuau_4.setFont(new Font("新細明體", Font.BOLD, 18));
		lblVuau_4.setBounds(440, 10, 52, 37);
		panel_3.add(lblVuau_4);
		
		id = new JTextField();
		id.setBounds(39, 19, 96, 21);
		panel_3.add(id);
		id.setColumns(10);
		
		name2 = new JTextField();
		name2.setBounds(186, 19, 96, 21);
		panel_3.add(name2);
		name2.setColumns(10);
		
		chi2 = new JTextField();
		chi2.setBounds(334, 19, 96, 21);
		panel_3.add(chi2);
		chi2.setColumns(10);
		
		eng2 = new JTextField();
		eng2.setBounds(487, 19, 96, 21);
		panel_3.add(eng2);
		eng2.setColumns(10);
		
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(128, 128, 128));
		panel_4.setBounds(26, 493, 691, 76);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblId_1 = new JLabel("id");
		lblId_1.setForeground(Color.WHITE);
		lblId_1.setFont(new Font("新細明體", Font.BOLD, 18));
		lblId_1.setBounds(92, 10, 32, 37);
		panel_4.add(lblId_1);
		
		id2 = new JTextField();
		id2.setBounds(134, 19, 96, 21);
		panel_4.add(id2);
		id2.setColumns(10);
		
		
		
		
		
		
		JButton btnNewButton = new JButton("新增");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String Name=name1.getText();
				int CHI=Integer.parseInt(chi1.getText());
				int ENG=Integer.parseInt(eng1.getText());
				
				ssi.addStudent(new Student(Name,CHI,ENG));
				
				
			}
		});
		btnNewButton.setBounds(549, 39, 87, 23);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("查詢");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<Student> l=ssi.findAll();
				
				String show="";
				
				for(Student s:l)
				{
					show=show+"id:"+s.getId()+
							"\t名:"+s.getName()+
							"\t國文:"+s.getChi()+
							"\t英文:"+s.getEng()+
							"\t總分:"+s.getSum()+"\n";
				}
				
				output.setText(show);
				
			}
		});
		btnNewButton_1.setBounds(10, 21, 87, 23);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID=Integer.parseInt(id.getText());
				String Name=name2.getText();
				int Chi=Integer.parseInt(chi2.getText());
				int Eng=Integer.parseInt(eng2.getText());
				
				ssi.updateStudent(ID, Name, Chi, Eng);
				
			}
		});
		btnNewButton_2.setBounds(593, 18, 87, 23);
		panel_3.add(btnNewButton_2);
		
		
		JButton btnNewButton_3 = new JButton("刪除");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int  ID=Integer.parseInt(id2.getText());
				ssi.deleteStudent(ID);
				
			}
		});
		btnNewButton_3.setBounds(290, 18, 87, 23);
		panel_4.add(btnNewButton_3);
	}
}