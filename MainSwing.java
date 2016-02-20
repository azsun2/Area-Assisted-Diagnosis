import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;

public class MainSwing extends JFrame {
	private int count = 0;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblQuery;
	private JLabel lblQuery_1;
	private JPanel panel_1;
	private JButton btnBack;

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
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) getContentPane().getLayout()).next(getContentPane());
			}
		});
		GridBagConstraints gbc_btnCompare = new GridBagConstraints();
		gbc_btnCompare.insets = new Insets(0, 0, 0, 5);
		gbc_btnCompare.gridx = 2;
		gbc_btnCompare.gridy = 6;
		panel.add(btnCompare, gbc_btnCompare);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, "name_1455962808179984000");
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) getContentPane().getLayout()).previous(getContentPane());
			}
		});
		panel_1.add(btnBack);
	}

}
