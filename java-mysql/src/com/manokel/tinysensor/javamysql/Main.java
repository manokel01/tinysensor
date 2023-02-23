package com.manokel.tinysensor.javamysql;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.manokel.tinysensor.javamysql.InsertUserForm;
import com.manokel.tinysensor.javamysql.Menu;
import com.manokel.tinysensor.javamysql.SearchUserForm;
import com.manokel.tinysensor.javamysql.UpdateDeleteUserForm;

public class Main {
	
	private static Menu menu;
	private static SearchUserForm searchForm;
	private static InsertUserForm insertForm;
	private static UpdateDeleteUserForm updateDeleteForm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel( new FlatLightLaf() );
					
					menu = new Menu();
					menu.setLocationRelativeTo(null);
					menu.setVisible(true);
					
					searchForm = new SearchUserForm();
					searchForm.setLocationRelativeTo(null);
					searchForm.setVisible(false);
					
					insertForm = new InsertUserForm();
					insertForm.setLocationRelativeTo(null);
					insertForm.setVisible(false);
					
					updateDeleteForm = new UpdateDeleteUserForm();
					updateDeleteForm.setLocationRelativeTo(null);
					updateDeleteForm.setVisible(false);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Menu getMenu() {
		return menu;
	}

	public static SearchUserForm getSearchUserForm() {
		return searchForm;
	}

	public static InsertUserForm getInsertUserForm() {
		return insertForm;
	}

	public static UpdateDeleteUserForm getUpdateDeleteUserForm() {
		return updateDeleteForm;
	}
	
	
}
