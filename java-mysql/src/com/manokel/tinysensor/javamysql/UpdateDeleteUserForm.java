package com.manokel.tinysensor.javamysql;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;

public class UpdateDeleteUserForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField textLastname;
	private JTextField textFirstname;

	
	public UpdateDeleteUserForm() {
		setTitle("Search Form");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 476, 359);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 30, 33));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClose.setBackground(new Color(43, 44, 47));
		btnClose.setForeground(new Color(213, 213, 213));
		btnClose.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnClose.setBounds(347, 278, 90, 25);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(43, 44, 47));
		panel.setBounds(42, 33, 388, 211);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(6, 19, 95, 26);
		panel.add(lblID);
		lblID.setHorizontalAlignment(SwingConstants.TRAILING);
		lblID.setForeground(new Color(213, 213, 213));
		lblID.setFont(new Font("Helvetica", Font.PLAIN, 14));
		
		txtID = new JTextField();
		txtID.setBounds(125, 19, 65, 25);
		panel.add(txtID);
		txtID.setColumns(50);
		
		JLabel lblLastname = new JLabel("Laastname");
		lblLastname.setBounds(6, 72, 95, 26);
		lblLastname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLastname.setForeground(new Color(213, 213, 213));
		lblLastname.setFont(new Font("Helvetica", Font.PLAIN, 14));
		panel.add(lblLastname);
		
		textLastname = new JTextField();
		textLastname.setBounds(126, 68, 241, 25);
		textLastname.setColumns(50);
		panel.add(textLastname);
		
		textFirstname = new JTextField();
		textFirstname.setColumns(50);
		textFirstname.setBounds(127, 119, 241, 25);
		panel.add(textFirstname);
		
		JButton btnLast = new JButton(">|");
		btnLast.setBackground(new Color(43, 44, 47));
		btnLast.setForeground(new Color(213, 213, 213));
		btnLast.setBounds(307, 162, 60, 25);
		panel.add(btnLast);
		
		JButton btnNext = new JButton(">");
		btnNext.setBackground(new Color(43, 44, 47));
		btnNext.setForeground(new Color(213, 213, 213));
		btnNext.setBounds(235, 162, 60, 25);
		panel.add(btnNext);
		
		JButton btnPrevious = new JButton("<");
		btnPrevious.setBackground(new Color(43, 44, 47));
		btnPrevious.setForeground(new Color(213, 213, 213));
		btnPrevious.setBounds(162, 162, 60, 25);
		panel.add(btnPrevious);
		
		JButton btnFirst = new JButton("|<");
		btnFirst.setBackground(new Color(43, 44, 47));
		btnFirst.setForeground(new Color(213, 213, 213));
		btnFirst.setBounds(89, 162, 60, 25);
		panel.add(btnFirst);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFirstname.setForeground(new Color(213, 213, 213));
		lblFirstname.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblFirstname.setBounds(6, 124, 95, 26);
		panel.add(lblFirstname);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(213, 213, 213));
		btnUpdate.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(43, 44, 47));
		btnUpdate.setBounds(240, 278, 90, 25);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 147, 0));
		btnDelete.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnDelete.setBackground(new Color(43, 44, 47));
		btnDelete.setBounds(138, 278, 90, 25);
		contentPane.add(btnDelete);
	}
}