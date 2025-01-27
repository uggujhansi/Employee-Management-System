package com.employee.management.system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	
	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton fetching = new JButton("Fetch all Employees");
		fetching.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Fetch_employee_details fetch = new Fetch_employee_details();
					fetch.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		fetching.setBounds(64, 75, 134, 34);
		contentPane.add(fetching);
		
		JButton fetch_by_id = new JButton("Fetch Employee \r\n\tBy ID");
		fetch_by_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FetchByID fetch1 = new FetchByID();
					fetch1.setVisible(true);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		fetch_by_id.setBounds(236, 75, 144, 34);
		contentPane.add(fetch_by_id);
		
		JButton delete = new JButton("Delete Employee");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Delete_Employee fetch2 = new Delete_Employee();
					fetch2.setVisible(true);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		delete.setBounds(64, 141, 134, 34);
		contentPane.add(delete);
		
		JButton update = new JButton("Update Employee Details");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Update_Employee fetch3 = new Update_Employee();
					fetch3.setVisible(true);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		update.setBounds(236, 141, 144, 34);
		contentPane.add(update);
		
		JButton onboard = new JButton("Onboard New Employee");
		onboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Onboard_newEmployee fetch2 = new Onboard_newEmployee();
					fetch2.setVisible(true);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		onboard.setBounds(138, 202, 144, 34);
		contentPane.add(onboard);
		
		JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
		heading.setBounds(93, 21, 287, 13);
		heading.setFont(new Font("Serif", Font.BOLD, 16));
		heading.setBackground(new Color(240, 240, 240));
		heading.setForeground(new Color(255, 0, 128));
		contentPane.add(heading);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\HP\\ECLIPS~2\\Projects\\src\\com\\employee\\MANAGE~1\\system\\1920-H~1.JPG"));
		lblNewLabel.setBounds(0, 0, 633, 413);
		contentPane.add(lblNewLabel);
	}
}
