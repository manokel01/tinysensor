package com.manokel.tinysensor.javamysql;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchUserForm extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLastname;
	private JTextField textEmail;
	private String inputLastname;
	private String inputEmail;
	
	public SearchUserForm() {
		setTitle("Search Database");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 338);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 30, 33));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUserForm().setVisible(false);
				Main.getMenu().setVisible(true);
			}
		});
		btnClose.setBackground(new Color(43, 44, 47));
		btnClose.setForeground(new Color(192, 192, 192));
		btnClose.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnClose.setBounds(313, 253, 90, 25);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(43, 44, 47));
		panel.setBounds(29, 19, 374, 193);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(120, 44, 234, 25);
		panel.add(txtLastname);
		txtLastname.setColumns(50);
		
		JLabel lblLastname = new JLabel("Lastname");
		lblLastname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastname.setBounds(28, 46, 80, 20);
		panel.add(lblLastname);
		lblLastname.setForeground(new Color(169, 169, 169));
		lblLastname.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLastname = txtLastname.getText().trim();
				inputEmail = txtLastname.getText().trim();
				Main.getSearchUserForm().setEnabled(false);
				Main.getUpdateDeleteUserForm().setVisible(true);
			}
		});
		btnSearch.setBackground(new Color(30, 29, 32));
		btnSearch.setBounds(264, 131, 90, 32);
		panel.add(btnSearch);
		btnSearch.setForeground(new Color(254, 255, 255));
		btnSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		
		textEmail = new JTextField();
		textEmail.setColumns(50);
		textEmail.setBounds(120, 86, 234, 25);
		panel.add(textEmail);
		
		JLabel txtEmail = new JLabel("Email");
		txtEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		txtEmail.setForeground(new Color(169, 169, 169));
		txtEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		txtEmail.setBounds(28, 88, 80, 20);
		panel.add(txtEmail);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(29, 249, 90, 32);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchUserForm().setEnabled(false);
				Main.getInsertUserForm().setVisible(true);
			}
		});
		btnInsert.setBackground(new Color(43, 44, 47));
		btnInsert.setForeground(new Color(213, 213, 213));
		btnInsert.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
	}
	
	public String getInputLastname() {
		return inputLastname;
	}
	
	public String getInputEmail() {
		return inputEmail;
	}
	
}