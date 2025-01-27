package com.employee.management.system;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class FetchByID extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea resultArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FetchByID frame = new FetchByID();
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
	public FetchByID() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter ID to Fetch");
		lblNewLabel.setBounds(36, 10, 87, 13);
		contentPane.add(lblNewLabel);
		
		JTextField idField = new JTextField();
		idField.setBounds(145, 7, 96, 19);
		contentPane.add(idField);
		idField.setColumns(10);

		JButton fetchButton = new JButton("Search");
		fetchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText().trim();
	            if (id.isEmpty()) {
	                JOptionPane.showMessageDialog(FetchByID.this, "Please enter an Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            fetchEmployeeDetails(id);
			}
		});
		fetchButton.setBackground(new Color(0, 255, 255));
		fetchButton.setBounds(277, 6, 76, 21);
		contentPane.add(fetchButton);
	
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					Home frame = new Home();
					frame.setVisible(true);
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
        	}
        });
        backButton.setBackground(new Color(240, 240, 240));
        backButton.setBounds(156, 232, 85, 21);
        contentPane.add(backButton);
        
        resultArea = new JTextArea();
        resultArea.setBounds(46, 36, 307, 151);
        contentPane.add(resultArea);

        setVisible(true);
	}
    
   
    private void fetchEmployeeDetails(String id) {
    	String dpath="com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/empployee_management"; // Replace with your database URL
        String username = "root"; // Replace with your database username
        String password = "nagavijaya"; // Replace with your database password

        String query = "SELECT * FROM employee_data WHERE id = ?"; // Replace 'employees' and 'id' with your table and column names

        try {
        	Class.forName(dpath);
        	Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(query); 
            System.out.println("Database connected successfully.");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
            	System.out.println("Data found for ID: " + id);
                StringBuilder result = new StringBuilder();
                result.append("Employee ID: ").append(resultSet.getString("ID")).append("\n");
                result.append("Name: ").append(resultSet.getString("Name")).append("\n");
                result.append("Salary: ").append(resultSet.getDouble("Salary")).append("\n");
                result.append("Email: ").append(resultSet.getString("Email")).append("\n");
                result.append("Age: ").append(resultSet.getString("Age")).append("\n");
                result.append("Address: ").append(resultSet.getString("Address")).append("\n");
                resultArea.setText(result.toString());
            } else {
            	System.out.println("No data found for ID: " + id);
                resultArea.setText("No employee found with ID: " + id);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(FetchByID.this, "Error fetching employee details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
