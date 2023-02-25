package com.manokel.tinysensor.javamysql;

import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.manokel.tinysensor.javamysql.util.DBUtil;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * @author manokel
 *
 */

public class UpdateDeleteUserForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtLastname;
	private JTextField txtFirstname;
	private PreparedStatement preparedLastname;
	private PreparedStatement preparedEmail;
	private Connection conn;
	private ResultSet rs;
	private JTextField txtEmail;
	private JTextField txtStreet;
	private JTextField txtCity;
	private JTextField txtCountry;

	
	public UpdateDeleteUserForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				String sqlSearchLastname = "SELECT ID, LASTNAME, FIRSTNAME, EMAIL, STREET, CITY, COUNTRY WHERE LASTNAME LIKE ?";
				String sqlSearchEmail = "SELECT ID, LASTNAME, FIRSTNAME, EMAIL, STREET, CITY, COUNTRY WHERE EMAIL LIKE ?";
				try {
					conn =DBUtil.getConnection();
					// this is because of the previous-next buttons:
					// because sql drivers for results sets are forward-only use 'ResultSet.TYPE_SCROLL_SENSITIVE'
					// to update the table immediately after the query us 'ResultSet.CONCUR_UPDATABLE'

					preparedLastname = conn.prepareStatement(sqlSearchLastname, ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
					preparedEmail = conn.prepareStatement(sqlSearchEmail);
					
					preparedLastname.setString(1, Main.getSearchUserForm().getInputLastname() + '%');
					preparedEmail.setString(1, Main.getSearchUserForm().getInputEmail() + '%');
					
					rs = preparedLastname.executeQuery();	// populates the result set
					
					// --> Better to store results in an ArrayList!!
					// while (rs.next()) {
					// user.setId(rs.getString("ID");
					// user.setLastname(rs.getString("Lastname");
					// user.setFirstname(rs.getString("Firstname");
					// etc.
					if (rs.next()) {  // checks if there is a result
						txtID.setText(Integer.toString(rs.getInt("ID")));
						txtLastname.setText(rs.getString("Lastname"));
						txtFirstname.setText(rs.getString("Firstname"));
						txtEmail.setText(rs.getString("Email"));
						txtStreet.setText(rs.getString("Street"));
						txtCity.setText(rs.getString("City"));
						txtCountry.setText(rs.getString("Country"));
					} else {
						return;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					if (rs != null) rs.close();
					if (preparedLastname != null) preparedLastname.close();
					if (preparedEmail != null) preparedEmail.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		setTitle("Search Form");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 475, 639);
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
		btnClose.setBounds(321, 546, 90, 25);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(43, 44, 47));
		panel.setBounds(42, 33, 388, 414);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setBounds(6, 30, 95, 26);
		panel.add(lblID);
		lblID.setHorizontalAlignment(SwingConstants.TRAILING);
		lblID.setForeground(new Color(213, 213, 213));
		lblID.setFont(new Font("Helvetica", Font.PLAIN, 14));
		
		txtID = new JTextField();
		txtID.setBounds(125, 29, 65, 25);
		panel.add(txtID);
		txtID.setColumns(50);
		
		JLabel lblLastname = new JLabel("Laastname");
		lblLastname.setBounds(6, 84, 95, 26);
		lblLastname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLastname.setForeground(new Color(213, 213, 213));
		lblLastname.setFont(new Font("Helvetica", Font.PLAIN, 14));
		panel.add(lblLastname);
		
		txtLastname = new JTextField();
		txtLastname.setBounds(126, 83, 241, 25);
		txtLastname.setColumns(50);
		panel.add(txtLastname);
		
		txtFirstname = new JTextField();
		txtFirstname.setColumns(50);
		txtFirstname.setBounds(127, 137, 241, 25);
		panel.add(txtFirstname);
		
		JLabel lblFirstname = new JLabel("Firstname");
		lblFirstname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFirstname.setForeground(new Color(213, 213, 213));
		lblFirstname.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblFirstname.setBounds(6, 138, 95, 26);
		panel.add(lblFirstname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setForeground(new Color(213, 213, 213));
		lblEmail.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblEmail.setBounds(6, 192, 95, 26);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(50);
		txtEmail.setBounds(125, 191, 241, 25);
		panel.add(txtEmail);
		
		txtStreet = new JTextField();
		txtStreet.setColumns(50);
		txtStreet.setBounds(126, 245, 241, 25);
		panel.add(txtStreet);
		
		txtCity = new JTextField();
		txtCity.setColumns(50);
		txtCity.setBounds(126, 299, 241, 25);
		panel.add(txtCity);
		
		txtCountry = new JTextField();
		txtCountry.setColumns(50);
		txtCountry.setBounds(126, 353, 241, 25);
		panel.add(txtCountry);
		
		JLabel lblStreet = new JLabel("Street");
		lblStreet.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStreet.setForeground(new Color(213, 213, 213));
		lblStreet.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblStreet.setBounds(6, 246, 95, 26);
		panel.add(lblStreet);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCity.setForeground(new Color(213, 213, 213));
		lblCity.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblCity.setBounds(6, 299, 95, 26);
		panel.add(lblCity);
		
		JLabel lblCountry = new JLabel("City");
		lblCountry.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCountry.setForeground(new Color(213, 213, 213));
		lblCountry.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblCountry.setBounds(6, 352, 95, 26);
		panel.add(lblCountry);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setForeground(new Color(213, 213, 213));
		btnUpdate.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(43, 44, 47));
		btnUpdate.setBounds(201, 546, 90, 25);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 147, 0));
		btnDelete.setFont(new Font("Helvetica", Font.PLAIN, 14));
		btnDelete.setBackground(new Color(43, 44, 47));
		btnDelete.setBounds(82, 546, 90, 25);
		contentPane.add(btnDelete);
		
		JButton btnFirst = new JButton("|<");
		btnFirst.setBounds(42, 465, 90, 25);
		contentPane.add(btnFirst);
		btnFirst.setBackground(new Color(43, 44, 47));
		btnFirst.setForeground(new Color(213, 213, 213));
		
		JButton btnPrevious = new JButton("<");
		btnPrevious.setBounds(144, 465, 90, 25);
		contentPane.add(btnPrevious);
		btnPrevious.setBackground(new Color(43, 44, 47));
		btnPrevious.setForeground(new Color(213, 213, 213));
		
		JButton btnLast = new JButton(">|");
		btnLast.setBounds(352, 465, 90, 25);
		contentPane.add(btnLast);
		btnLast.setBackground(new Color(43, 44, 47));
		btnLast.setForeground(new Color(213, 213, 213));
		
		JButton btnNext = new JButton(">");
		btnNext.setBounds(250, 465, 90, 25);
		contentPane.add(btnNext);
		btnNext.setBackground(new Color(43, 44, 47));
		btnNext.setForeground(new Color(213, 213, 213));
	}
}