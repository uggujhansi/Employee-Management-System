package com.employee.management.system;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Onboard_newEmployee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField, salaryField, emailField, ageField, addressField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Onboard_newEmployee frame = new Onboard_newEmployee();
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
	public Onboard_newEmployee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Enter Employee Details");
        lblNewLabel.setBounds(150, 10, 200, 13);
        contentPane.add(lblNewLabel);

        // Input fields for new employee details
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(53, 43, 87, 13);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 40, 96, 19);
        contentPane.add(nameField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(53, 73, 87, 13);
        contentPane.add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(150, 70, 96, 19);
        contentPane.add(salaryField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(53, 103, 87, 13);
        contentPane.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 100, 96, 19);
        contentPane.add(emailField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(53, 133, 87, 13);
        contentPane.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(150, 130, 96, 19);
        contentPane.add(ageField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(53, 163, 87, 13);
        contentPane.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 160, 96, 19);
        contentPane.add(addressField);

        // Insert Button
        JButton insertButton = new JButton("INSERT");
        insertButton.setBounds(156, 200, 85, 21);
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String salary = salaryField.getText().trim();
                String email = emailField.getText().trim();
                String age = ageField.getText().trim();
                String address = addressField.getText().trim();

                if (name.isEmpty() || salary.isEmpty() || email.isEmpty() || age.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(Onboard_newEmployee.this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                insertEmployee(name, salary, email, age, address);
            }
        });
        contentPane.add(insertButton);
        
        JButton btnNewButton = new JButton("BACK");
        btnNewButton.addActionListener(new ActionListener() {
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
        btnNewButton.setBounds(166, 231, 69, 22);
        contentPane.add(btnNewButton);

        setVisible(true);
    }

    private void insertEmployee(String name, String salary, String email, String age, String address) {
        String dpath = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/empployee_management"; // Replace with your database URL
        String username = "root"; // Replace with your database username
        String password = "nagavijaya"; // Replace with your database password

        String query = "INSERT INTO employee_data (Name, Salary, Email, Age, Address) VALUES (?, ?, ?, ?, ?)"; // Insert query

        try {
            Class.forName(dpath);
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, salary);
            statement.setString(3, email);
            statement.setString(4, age);
            statement.setString(5, address);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(Onboard_newEmployee.this, "Employee added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(Onboard_newEmployee.this, "Failed to add employee.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(Onboard_newEmployee.this, "Error inserting employee details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
