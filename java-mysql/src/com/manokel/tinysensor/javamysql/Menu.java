package com.manokel.tinysensor.javamysql;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.manokel.tinysensor.javamysql.util.DBUtil;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public Menu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					DBUtil.getConnection();
					System.out.println("Connected");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/resources/eduv2.png")));
		setTitle("Διαχείριση Εκπαιδευτικού Συστήματος");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 256);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 30, 33));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(40, 68, 345, 1);
		contentPane.add(separator);
		
		JButton btnUsers = new JButton("");
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getSearchUserForm().setVisible(true);
			}
		});
		btnUsers.setBackground(new Color(43, 44, 47));
		btnUsers.setBounds(163, 91, 32, 32);
		contentPane.add(btnUsers);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setForeground(new Color(213, 213, 213));
		lblUsers.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lblUsers.setBounds(207, 96, 64, 27);
		contentPane.add(lblUsers);
		
		JButton btnDevices = new JButton("");
		btnDevices.setBackground(new Color(43, 44, 47));
		btnDevices.setBounds(163, 154, 32, 32);
		contentPane.add(btnDevices);
		
		JLabel lblDevices = new JLabel("Devices");
		lblDevices.setForeground(new Color(213, 213, 213));
		lblDevices.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
		lblDevices.setBounds(207, 159, 88, 27);
		contentPane.add(lblDevices);
		
		JLabel lblHeader = new JLabel("Choose instance:");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(new Color(213, 213, 213));
		lblHeader.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		lblHeader.setBounds(91, 23, 244, 46);
		contentPane.add(lblHeader);
	}
	
	
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
//					Menu frame = new Menu();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//	}
}