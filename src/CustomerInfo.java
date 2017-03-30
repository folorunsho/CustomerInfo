import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JSlider;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.Printable;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class CustomerInfo {

	private JFrame frame;
	private JTextField textId;
	private JTextField textFname;
	private JTextField textLname;
	private JTextField textAddr;
	private JTextField textPhone;
	private JTextField textSearch;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInfo window = new CustomerInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CustomerInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 908, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
				
		final Customer customer = new Customer();
		JButton btnNewButton = new JButton("Add ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				
				
				String id = textId.getText();
				String firstName = textFname.getText();
				String lastName	= textLname.getText();
				String addr = textAddr.getText();
				int phoneNum = Integer.parseInt(textPhone.getText());
				if( !(id.isEmpty() && firstName.isEmpty() && lastName.isEmpty() && addr.isEmpty() && phoneNum==0) ){					
					
			
			customer.AddCustomer(id, firstName, lastName, addr, phoneNum);	
			
			
			
			JOptionPane.showMessageDialog(null, "Customer added!");
				}
				else {
					
					System.out.print(id);
				}
			}
		});
		
		btnNewButton.setBounds(69, 194, 85, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(20, 47, 46, 14);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblId);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(20, 72, 103, 14);
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(20, 97, 103, 14);
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(20, 122, 85, 14);
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblAddress);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(20, 147, 143, 14);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblCustomerInformationRegister = new JLabel("Customer Information Register");
		lblCustomerInformationRegister.setBounds(49, 0, 329, 25);
		lblCustomerInformationRegister.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblCustomerInformationRegister);
		
		textId = new JTextField();
		textId.setBounds(159, 47, 219, 20);
		frame.getContentPane().add(textId);
		textId.setColumns(10);
		
		textFname = new JTextField();
		textFname.setBounds(159, 72, 219, 20);
		textFname.setColumns(10);
		frame.getContentPane().add(textFname);
		
		textLname = new JTextField();
		textLname.setBounds(159, 97, 219, 20);
		textLname.setColumns(10);
		frame.getContentPane().add(textLname);
		
		textAddr = new JTextField();
		textAddr.setBounds(159, 122, 219, 20);
		textAddr.setColumns(10);
		frame.getContentPane().add(textAddr);
		
		textPhone = new JTextField();
		textPhone.setBounds(159, 147, 219, 20);
		textPhone.setColumns(10);
		frame.getContentPane().add(textPhone);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 239, 1, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblSearchCustomersBy = new JLabel("Search Customers By:");
		lblSearchCustomersBy.setBounds(49, 242, 329, 28);
		lblSearchCustomersBy.setFont(new Font("Tahoma", Font.BOLD, 20));
		frame.getContentPane().add(lblSearchCustomersBy);
		
		JLabel idSearch = new JLabel("ID:");
		idSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		idSearch.setBounds(20, 285, 103, 14);
		frame.getContentPane().add(idSearch);
		
		textSearch = new JTextField();
		textSearch.setColumns(10);
		textSearch.setBounds(143, 281, 219, 20);
		frame.getContentPane().add(textSearch);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String searchText = textSearch.getText();
				Customer customer1 = customer.searchCustomer(searchText);
				textId.setText(customer1.getId());
				textFname.setText(customer1.getFirstName());
				textLname.setText(customer1.getLastName());
				textAddr.setText(customer1.getAddr());
				textPhone.setText(Integer.toString(customer1.getPhoneNum()));
				
			}
		});
		btnSearch.setBounds(143, 326, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setBounds(164, 193, 76, 25);
		frame.getContentPane().add(btnEdit);
		
		JButton dltButton = new JButton("Delete");
		dltButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String deleteText = textSearch.getText();
				boolean delete_result = customer.deleteCustomer(deleteText);
				if(delete_result){
					System.out.print("delete successful:");
				}else{
					System.out.print("delete not successful:");
				}
			}
		});
		dltButton.setBounds(268, 194, 89, 23);
		frame.getContentPane().add(dltButton);
	}
}
