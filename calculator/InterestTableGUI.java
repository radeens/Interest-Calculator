package calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import utilities.Utilities;

/**
 * You will likely import more than a few Swing and other libraries above.
 * 
 * @author CS Department, UMD
 * 
 */
@SuppressWarnings("serial")
public class InterestTableGUI extends JPanel {

	private static final int NOY_MIN = 1;
	private static final int NOY_MAX = 30;
	private static final int NOY_INIT = 5;
	private JTextArea display;
	private JScrollPane scrollPane;
	private JPanel centralPanel;
	private JTextField principalTextField;
	private JTextField percentageTextField;
	private JSlider slider;
	private JPanel buttonPanel;
	private JButton simpleIntButton;
	private JTextField monthlyPay;

	public InterestTableGUI(int x, int y) {

		setPreferredSize(new Dimension(x, y));
		setLayout(new BorderLayout());

		display = new JTextArea(10, 10);
		display.setLineWrap(true);
		display.setEditable(false);

		scrollPane = new JScrollPane(display);

		add(scrollPane, BorderLayout.NORTH);

		centralPanel = new JPanel();
		
		centralPanel.add(new JLabel("Principal: "));	
		principalTextField = new JTextField(12);
		centralPanel.add(principalTextField);
		
		centralPanel.add(new JLabel("Monthly Payment: "));
		monthlyPay = new JTextField(12);
		centralPanel.add(monthlyPay);

		centralPanel.add(new JLabel("Rate(Percentage): "));

		percentageTextField = new JTextField(5);
		

		centralPanel.add(percentageTextField);

		JLabel sliderLabel = new JLabel("Number of Years: ", JLabel.CENTER);
		sliderLabel.setAlignmentX(CENTER_ALIGNMENT);
		slider = new JSlider(JSlider.HORIZONTAL, NOY_MIN, NOY_MAX, NOY_INIT);
		//UIManager.put("ToolTip.background", new ColorUIResource(255, 255, 255));
		

		slider.setMajorTickSpacing(4);
		slider.setMinorTickSpacing(1);

		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		centralPanel.add(sliderLabel);
		centralPanel.add(slider);

		add(centralPanel, BorderLayout.CENTER);

		buttonPanel = new JPanel();

		simpleIntButton = new JButton("SimpleInterest");
		
		JButton compIntButton = new JButton("CompoundInterest");
		JButton bothIntButton = new JButton("BothInterests");
		

		buttonPanel.add(simpleIntButton);
		buttonPanel.add(compIntButton);
		buttonPanel.add(bothIntButton);
		

		add(buttonPanel, BorderLayout.SOUTH);

		
		// Simple interest actionListener uses private inner class
		simpleIntButton.addActionListener(new ButtonListener());

		// Compound interest button action listener, uses anonymous inner class
		compIntButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double principal = getPrincipal();
				double rate = getRate();
				int years = getYears();

				display.setText(Utilities.compoundInterestTable(principal,
						rate, years));
			}

		});

		// Both interest button listener uses anonymous inner class.
		bothIntButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double principal = getPrincipal();
				double rate = getRate();
				int years = getYears();

				display.setText(Utilities.bothInterestsTable(principal, rate,
						years));
			}

			
		});
		
	}
	
	private double getPrincipal() {
		try {
			return Double.parseDouble(principalTextField.getText());
		}
		catch(NumberFormatException e){
			display.setText("Principal amounts are numbers.");
			return 0;
		}
	}
	private double getMonthly(){
		try{
			return Double.parseDouble(monthlyPay.getText());
		}catch(NumberFormatException ee){
			display.setText("Try putting numbers");
			return 0;
		}
	}

	private double getRate() {
		try{
		return Double.parseDouble(percentageTextField.getText());
		}
		catch(NumberFormatException ef){
			display.setText("Try putting numbers without %.");
			return 0;
		}
	}

	private int getYears() {
		return slider.getValue();
	}

	/**
	 * Non-anonymous inner class for simple interest actionListener
	 */

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			double principal = getPrincipal();
			double rate = getRate();
			int years = getYears();

			display.setText(Utilities.simpleInterestTable(principal, rate,
					years));
		}
	}

	/**
	 * Creates and shows GUI and called by main method.
	 */
	public static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Interest Table Calculator");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(100, 100);
		InterestTableGUI table = new InterestTableGUI(675, 300);
		frame.setContentPane(table);
		//frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Main method that runs the GUI.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI(); // actually creates GUI.
			}
		});
	}
}