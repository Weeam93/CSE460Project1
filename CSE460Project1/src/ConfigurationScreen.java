import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class ConfigurationScreen extends JPanel {
	
	private JPanel headerPanel;
	private JPanel bodyPanel;
	private JButton homeScreenBtn;
	public ConfigurationScreen()
	{
		this.setSize(350, 400);
		this.setVisible(true);
		//this.setResizable(false);
		
		headerPanel=new JPanel();
		bodyPanel=new JPanel();
		setUpHeader();
		setUpBody();
		this.add(headerPanel,BorderLayout.NORTH);
		this.add(bodyPanel,BorderLayout.CENTER);
		
	}
	private void setUpHeader()
	{
		homeScreenBtn=new JButton("Home");
		headerPanel.setLayout(new GridLayout(1,3));
		headerPanel.add(homeScreenBtn);
		/*headerPanel.add(new JLabel(" "));
		headerPanel.add(new JLabel(" "));*/
		
		
	}
	private void setUpBody()
	{
		JPanel panel1=new JPanel();
		JTextArea infoArea1=new JTextArea();
		infoArea1.setText("The tip range is the percentage\nrange for selecting tips. Defaults\nvalues are provided but you can\nadjust the range if you wish");
		panel1.add(infoArea1);
		
	
		JLabel minTipLabel=new JLabel("Minimum Tip Percentage");
		JLabel maxTipLabel=new JLabel("Maximum Tip Percentage");
		JTextField minTextField=new JTextField(10);
		JTextField maxTextField=new JTextField(10);
		
		JPanel panel2=new JPanel(new GridLayout(1,2));
		JPanel leftPanel2=new JPanel();
		leftPanel2.add(minTipLabel);
		JPanel rightPanel2=new JPanel();
		rightPanel2.add(minTextField);
		panel2.add(leftPanel2);
		panel2.add(rightPanel2);
		
		JPanel panel3=new JPanel(new GridLayout(1,2));
		JPanel leftPanel3=new JPanel();
		leftPanel3.add(maxTipLabel);
		JPanel rightPanel3=new JPanel();
		rightPanel3.add(maxTextField);
		panel3.add(leftPanel3);
		panel3.add(rightPanel3);
		
		JPanel panel4 =new JPanel();
		JTextArea infoArea2 = new JTextArea();
		infoArea2.setText("The Tip Base is the items of the bill\nused to determine the Tip amount.\nThe Tip Base consists of: the Bill\nTotal, the Tax, and the Items Deducted\nfrom bill(discounts,refurnds...)");
		panel4.add(infoArea2);
		
		
		JLabel includeTaxLabel=new JLabel("Include Tax");
		JLabel includeDeductionsLabel=new JLabel("Include Deductions");
		JCheckBox includeTaxToggle=new JCheckBox();
		JCheckBox includeDeductionsToggle=new JCheckBox();
		
		JPanel panel5=new JPanel(new GridLayout(1,2));
		JPanel leftPanel5=new JPanel();
		leftPanel5.add(includeTaxLabel);
		JPanel rightPanel5=new JPanel();
		rightPanel5.add(includeTaxToggle);
		panel5.add(leftPanel5);
		panel5.add(rightPanel5);
		
		JPanel panel6=new JPanel(new GridLayout(1,2));
		JPanel leftPanel6=new JPanel();
		leftPanel6.add(includeDeductionsLabel);
		JPanel rightPanel6=new JPanel();
		rightPanel6.add(includeDeductionsToggle);
		panel6.add(leftPanel6);
		panel6.add(rightPanel6);
		
		//bodyPanel.setLayout(new GridLayout(6,1));
		bodyPanel.setLayout(new BoxLayout(bodyPanel,BoxLayout.Y_AXIS));
		bodyPanel.add(panel1);
		bodyPanel.add(panel2);
		bodyPanel.add(panel3);
		bodyPanel.add(panel4);
		bodyPanel.add(panel5);
		bodyPanel.add(panel6);
	}
	public JButton getHomeScreenBtn()
	{
		return homeScreenBtn;
	}

}
