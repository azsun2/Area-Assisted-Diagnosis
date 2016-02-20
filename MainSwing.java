import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.print.attribute.standard.NumberUpSupported;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class MainSwing extends JFrame {
	private String first;
	private String second;
	private int a;
	private int b;
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<Integer> number = new ArrayList<Integer>();
	private String zip;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblQuery;
	private JLabel lblQuery_1;
	private JPanel panel_1;
	private JButton btnBack;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textField_2;
	private JLabel lblZipCode;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainSwing frame = new MainSwing();
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
	public MainSwing() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		final JPanel panel = new JPanel();
		getContentPane().add(panel, "name_1455962462584217000");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblQuery = new JLabel("Query 1");
		GridBagConstraints gbc_lblQuery = new GridBagConstraints();
		gbc_lblQuery.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuery.gridx = 2;
		gbc_lblQuery.gridy = 1;
		panel.add(lblQuery, gbc_lblQuery);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		lblQuery_1 = new JLabel("Query 2");
		GridBagConstraints gbc_lblQuery_1 = new GridBagConstraints();
		gbc_lblQuery_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuery_1.gridx = 2;
		gbc_lblQuery_1.gridy = 3;
		panel.add(lblQuery_1, gbc_lblQuery_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		DefaultTableModel dft = new DefaultTableModel();
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first = textField_1.getText();
				second = textField.getText();
				zip = textField_2.getText();
				names.add(first);
				names.add(second);
				a = Main.getCount(first,zip);
				b = Main.getCount(second,zip);
				number.add(a);
				number.add(b);
				((DefaultTableModel) table.getModel()).setRowCount(names.size());
				((DefaultTableModel) table.getModel()).setColumnCount(2);
				for(int c = 0; c < names.size(); c++){
					table.getModel().setValueAt(names.get(c), c, 0);
					table.getModel().setValueAt(number.get(c), c, 1);
				}
				((CardLayout) getContentPane().getLayout()).next(getContentPane());
			}
		});
		
		lblZipCode = new JLabel("Zip Code");
		GridBagConstraints gbc_lblZipCode = new GridBagConstraints();
		gbc_lblZipCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblZipCode.gridx = 2;
		gbc_lblZipCode.gridy = 5;
		panel.add(lblZipCode, gbc_lblZipCode);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 6;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		GridBagConstraints gbc_btnCompare = new GridBagConstraints();
		gbc_btnCompare.insets = new Insets(0, 0, 0, 5);
		gbc_btnCompare.gridx = 2;
		gbc_btnCompare.gridy = 8;
		panel.add(btnCompare, gbc_btnCompare);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, "name_1455962808179984000");
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{75, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				names.clear();
				number.clear();
				((CardLayout) getContentPane().getLayout()).previous(getContentPane());
			}
		});
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		//table.setModel(new DefaultTableModel(names.size(),2));
		//table.setModel(new DefaultTableModel(3,3));
		scrollPane.setViewportView(table);
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 1;
		panel_1.add(btnBack, gbc_btnBack);
	}

}