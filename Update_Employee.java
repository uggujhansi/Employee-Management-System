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
import java.awt.Font;

public class Update_Employee extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea resultArea;
	private JTextField nameField, salaryField, emailField, ageField, addressField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Employee frame = new Update_Employee();
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
	public Update_Employee() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 396);
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
                    JOptionPane.showMessageDialog(Update_Employee.this, "Please enter an Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
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
        backButton.setBounds(161, 308, 85, 21);
        contentPane.add(backButton);

        resultArea = new JTextArea();
        resultArea.setBounds(63, 66, 273, 98);
        contentPane.add(resultArea);

        // Input fields for updating employee details
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(53, 203, 87, 13);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 200, 96, 19);
        contentPane.add(nameField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(53, 226, 87, 13);
        contentPane.add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(100, 223, 96, 19);
        contentPane.add(salaryField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(53, 252, 87, 13);
        contentPane.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 249, 96, 19);
        contentPane.add(emailField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(227, 203, 87, 13);
        contentPane.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(278, 200, 96, 19);
        contentPane.add(ageField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(227, 226, 87, 13);
        contentPane.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(278, 223, 96, 19);
        contentPane.add(addressField);

        // Update Button
        JButton updateButton = new JButton("UPDATE");
        updateButton.setBounds(161, 281, 85, 21);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String salary = salaryField.getText().trim();
                String email = emailField.getText().trim();
                String age = ageField.getText().trim();
                String address = addressField.getText().trim();

                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(Update_Employee.this, "Please enter an Employee ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                updateEmployee(id, name, salary, email, age, address);
            }
        });
        contentPane.add(updateButton);
        
        JLabel lblNewLabel_1 = new JLabel("Updating the employee Details \r\n");
        lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 13));
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBounds(85, 10, 207, 13);
        contentPane.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Here you can Update your details:");
        lblNewLabel_2.setFont(new Font("Serif", Font.BOLD, 12));
        lblNewLabel_2.setForeground(new Color(0, 64, 64));
        lblNewLabel_2.setBounds(53, 174, 178, 13);
        contentPane.add(lblNewLabel_2);

        setVisible(true);
    }

    private void fetchEmployeeDetails(String id) {
        String dpath = "com.mysql.cj.jdbc.Driver";
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

                // Populate the fields for updating
                nameField.setText(resultSet.getString("Name"));
                salaryField.setText(String.valueOf(resultSet.getDouble("Salary")));
                emailField.setText(resultSet.getString("Email"));
                ageField.setText(resultSet.getString("Age"));
                addressField.setText(resultSet.getString("Address"));
            } else {
                System.out.println("No data found for ID: " + id);
                resultArea.setText("No employee found with ID: " + id);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(Update_Employee.this, "Error fetching employee details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateEmployee(String id, String name, String salary, String email, String age, String address) {
        String dpath = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/empployee_management"; // Replace with your database URL
        String username = "root"; // Replace with your database username
        String password = "nagavijaya"; // Replace with your database password

        String query = "UPDATE employee_data SET Name = ?, Salary = ?, Email = ?, Age = ?, Address = ? WHERE ID = ?"; // Update query

        try {
            Class.forName(dpath);
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, salary);
            statement.setString(3, email);
            statement.setString(4, age);
            statement.setString(5, address);
            statement.setString(6, id);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(Update_Employee.this, "Employee details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(Update_Employee.this, "No employee found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(Update_Employee.this, "Error updating employee details: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
}
