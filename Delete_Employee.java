package com.employee.management.system;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Delete_Employee extends JFrame {

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
					Delete_Employee frame = new Delete_Employee();
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
	public Delete_Employee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter ID to Fetch");
		lblNewLabel.setBounds(53, 43, 87, 13);
		contentPane.add(lblNewLabel);
		
		JTextField idField = new JTextField();
		idField.setBounds(150, 40, 96, 19);
		contentPane.add(idField);
		idField.setColumns(10);

		JButton fetchButton = new JButton("Search");
		fetchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText().trim();
	            if (id.isEmpty()) {
	                JOptionPane.showMessageDialog(Delete_Employee.this, "Please enter an Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            fetchEmployeeDetails(id);
			}
		});
		fetchButton.setBackground(new Color(0, 255, 255));
		fetchButton.setBounds(256, 39, 76, 21);
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
        backButton.setBounds(156, 258, 85, 21);
        contentPane.add(backButton);
        
        resultArea = new JTextArea();
        resultArea.setBounds(52, 66, 292, 151);
        contentPane.add(resultArea);
        
        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String id = idField.getText().trim();
                 if (id.isEmpty()) {
                     JOptionPane.showMessageDialog(Delete_Employee.this, "Please enter an Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
                     return;
                 }
                 deleteEmployee(id);
        	}
        });
        deleteButton.setBounds(156, 227, 85, 21);
        contentPane.add(deleteButton);
        
        JLabel lblNewLabel_1 = new JLabel("Deleting the Enployee usind employee id\r\n");
        lblNewLabel_1.setForeground(new Color(128, 0, 0));
        lblNewLabel_1.setBounds(104, 10, 240, 13);
        contentPane.add(lblNewLabel_1);

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
            JOptionPane.showMessageDialog(Delete_Employee.this, "Error fetching employee details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteEmployee(String id) {
        String dpath = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/empployee_management"; // Replace with your database URL
        String username = "root"; // Replace with your database username
        String password = "nagavijaya"; // Replace with your database password

        String query = "DELETE FROM employee_data WHERE id = ?"; // Replace 'employee_data' and 'id' with your table and column names

        try {
            Class.forName(dpath);
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(Delete_Employee.this, "Employee deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                resultArea.setText("Employee with ID " + id + " deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(Delete_Employee.this, "No employee found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(Delete_Employee.this, "Error deleting employee: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
