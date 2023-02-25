package com.manokel.tinysensor.javamysql;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.manokel.tinysensor.javamysql.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InsertUserForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLastname;
	private JTextField txtFirstname;
	private JTextField txtEmail;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtCountry;
	private PreparedStatement p;

	
	public InsertUserForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				txtLastname.setText("");
				txtFirstname.setText("");
				txtEmail.setText("");
				txtStreet.setText("");
				txtCity.setText("");
				txtCountry.setText("");
			}
		});
		setTitle("Insert User Details");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 476, 421);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 30, 33));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = "INSERT INTO USERS (LASTNAME, FIRSTNAME, EMAIL, STREET, CITY, COUNTRY) VALUES (?,?,?,?,?,?)";
				String inputLastname;
				String inputFirstname;
				String inputEmail;
				String inputStreet;
				String inputCity;
				String inputCountry;
				int n; 					// count how many entries have been affected
				
				try {
					Connection conn = DBUtil.getConnection();
					p = conn.prepareStatement(sql);
					
					inputLastname = txtLastname.getText().trim();
					inputFirstname = txtFirstname.getText().trim();
					inputEmail = txtEmail.getText().trim();
					inputStreet = txtStreet.getText().trim();
					inputCity = txtCity.getText().trim();
					inputCountry = txtCountry.getText().trim();
					
					// validations
					if (inputLastname.equals("") || inputFirstname.equals("") || inputEmail.equals(""))
//							|| inputStreet.equals("") || inputCity.equals("") || inputCountry.equals("")) 
					{
						return;
					}
					
					p.setString(1, inputLastname);
					p.setString(2, inputFirstname);
					p.setString(3, inputEmail);
					p.setString(4, inputStreet);
					p.setString(5, inputCity);
					p.setString(6, inputCountry);
					
					n = p.executeUpdate();
					JOptionPane.showMessageDialog(null, n + " records inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);				
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (p != null) p.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnInsert.setBackground(new Color(43, 44, 47));
		btnInsert.setForeground(new Color(213, 213, 213));
		btnInsert.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnInsert.setBounds(241, 333, 90, 30);
		contentPane.add(btnInsert);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getInsertUserForm().setVisible(false);
				Main.getSearchUserForm().setEnabled(true);
			}
		});
		btnClose.setBackground(new Color(43, 44, 47));
		btnClose.setForeground(new Color(213, 213, 213));
		btnClose.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnClose.setBounds(348, 333, 90, 30);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(43, 44, 47));
		panel.setBounds(42, 33, 388, 275);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtFirstname = new JTextField();
		txtFirstname.setBounds(124, 62, 241, 25);
		panel.add(txtFirstname);
		txtFirstname.setColumns(50);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setBounds(6, 61, 95, 26);
		panel.add(lblFirstname);
		lblFirstname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFirstname.setForeground(new Color(213, 213, 213));
		lblFirstname.setFont(new Font("Helvetica", Font.PLAIN, 14));
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setBounds(6, 19, 95, 26);
		panel.add(lblLastname);
		lblLastname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLastname.setForeground(new Color(213, 213, 213));
		lblLastname.setFont(new Font("Helvetica", Font.PLAIN, 14));
		
		txtLastname = new JTextField();
		txtLastname.setBounds(124, 20, 241, 25);
		panel.add(txtLastname);
		txtLastname.setColumns(50);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(6, 103, 95, 26);
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setForeground(new Color(213, 213, 213));
		lblEmail.setFont(new Font("Helvetica", Font.PLAIN, 14));
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(124, 104, 241, 25);
		txtEmail.setColumns(50);
		panel.add(txtEmail);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setBounds(6, 145, 95, 26);
		lblStreet.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStreet.setForeground(new Color(213, 213, 213));
		lblStreet.setFont(new Font("Helvetica", Font.PLAIN, 14));
		panel.add(lblStreet);
		
		txtStreet = new JTextField();
		txtStreet.setBounds(124, 146, 241, 25);
		txtStreet.setColumns(50);
		panel.add(txtStreet);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(6, 187, 95, 26);
		lblCity.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCity.setForeground(new Color(213, 213, 213));
		lblCity.setFont(new Font("Helvetica", Font.PLAIN, 14));
		panel.add(lblCity);
		
		txtCity = new JTextField();
		txtCity.setBounds(124, 188, 241, 25);
		txtCity.setColumns(50);
		panel.add(txtCity);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(6, 229, 95, 26);
		lblCountry.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCountry.setForeground(new Color(213, 213, 213));
		lblCountry.setFont(new Font("Helvetica", Font.PLAIN, 14));
		panel.add(lblCountry);
		
		txtCountry = new JTextField();
		txtCountry.setBounds(124, 230, 241, 25);
		txtCountry.setColumns(50);
		panel.add(txtCountry);
	}
//	
//	public static void main(String[] args) {
//		
//		try {
//		    UIManager.setLookAndFeel( new FlatLightLaf() );
//		} catch( Exception ex ) {
//		    System.err.println( "Failed to initialize LaF" );
//		}
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					InsertForm frame = new InsertForm();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//	}
}

