import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class ConfigurationScreen extends JPanel {
	
	private JPanel headerPanel;
	private JPanel bodyPanel;
	private JButton homeScreenBtn;
	private double minPercent;
	private double maxPercent;
	private DecimalFormat df;
	private JCheckBox includeDeductionsCheckBox;
	private JCheckBox includeTaxCheckBox;
	private JTextField minTextField;
	private JTextField maxTextField;
	private JPanel footerPanel;
	private JButton saveBtn;
	private double minDefault;
	private double maxDefault;
	public ConfigurationScreen()
	{
		this.setSize(350, 400);
		this.setVisible(true);
		

		df=new DecimalFormat("0.00");
	
		
		headerPanel=new JPanel();
		bodyPanel=new JPanel();
		footerPanel=new JPanel();
		setUpHeader();
		setUpBody();
		setUpFooter();
		this.setMaxTip(0.00);
		this.setMinTip(0.00);
		this.setMinDefault();
		this.setMaxDefault();
		this.add(headerPanel,BorderLayout.NORTH);
		this.add(bodyPanel,BorderLayout.CENTER);
		this.add(footerPanel,BorderLayout.SOUTH);
		
	}
	private void setUpHeader()
	{
		homeScreenBtn=new JButton("Home");
		headerPanel.setLayout(new GridLayout(1,3));
		headerPanel.add(homeScreenBtn);		
	}
	private void setUpFooter()
	{
		JPanel savePanel=new JPanel();
		saveBtn=new JButton("Save");
		savePanel.add(saveBtn);
		footerPanel.add(savePanel);
	}
	private void setUpBody()
	{
		JPanel panel1=new JPanel();
		JTextArea infoArea1=new JTextArea();
		infoArea1.setText("The tip range is the percentage\nrange for selecting tips. Defaults\nvalues are provided but you can\nadjust the range if you wish");
		panel1.add(infoArea1);
		
	
		JLabel minTipLabel=new JLabel("Minimum Tip Percentage");
		JLabel maxTipLabel=new JLabel("Maximum Tip Percentage");
		minTextField=new JTextField(10);
		maxTextField=new JTextField(10);
		
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
		includeTaxCheckBox=new JCheckBox();
	    includeDeductionsCheckBox=new JCheckBox();
		
		JPanel panel5=new JPanel(new GridLayout(1,2));
		JPanel leftPanel5=new JPanel();
		leftPanel5.add(includeTaxLabel);
		JPanel rightPanel5=new JPanel();
		rightPanel5.add(includeTaxCheckBox);
		panel5.add(leftPanel5);
		panel5.add(rightPanel5);
		
		JPanel panel6=new JPanel(new GridLayout(1,2));
		JPanel leftPanel6=new JPanel();
		leftPanel6.add(includeDeductionsLabel);
		JPanel rightPanel6=new JPanel();
		rightPanel6.add(includeDeductionsCheckBox);
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
	private void setMinDefault()
	{
		this.minDefault=0.00;
		this.minTextField.setText(df.format(minDefault));
	}
	private void setMaxDefault()
	{
		this.maxDefault=40.00;
		this.maxTextField.setText(df.format(maxDefault));
	}
	public void setMinTip(Double minT)
	{
		this.minPercent=minT;
		//this.minTextField.setText(df.format(minPercent));
	}
	public void setMaxTip(Double maxT)
	{
		this.maxPercent=maxT;
		//this.maxTextField.setText(df.format(maxPercent));
	}
	public JButton getHomeScreenBtn()
	{
		return homeScreenBtn;
	}
	public JButton getSaveBtn()
	{
		return saveBtn;
	}
	public boolean isCheckedDeductions()
	{
		return this.includeDeductionsCheckBox.isSelected();
	}
	public boolean isCheckedTax()
	{
		return this.includeTaxCheckBox.isSelected();
	}
	public double getMinTip()
	{
		if(Double.parseDouble(this.minTextField.getText())==minDefault)
			return this.minDefault;
		else
			return minPercent;
	}
	public double getMaxTip()
	{
		if(Double.parseDouble(this.maxTextField.getText())==maxDefault)
			return this.maxDefault;
		else
			return maxPercent;
	}
	public boolean minTipPercentIsValid()
	{
		if(minPercent < 0)
		{
			JOptionPane.showMessageDialog(this, "Error: Minimum Tip must be Greater than 0","Error",JOptionPane.WARNING_MESSAGE);
			this.setMinDefault();
			return false;
		}
		else if(minPercent > maxPercent )
	    {
			JOptionPane.showMessageDialog(this, "Warning: Minimum Tip must be Less than Max Tip","Error",JOptionPane.WARNING_MESSAGE);
			this.setMaxDefault();
			this.setMinDefault();
			return false;
		}
		return true;
	}
	public boolean maxTipPercentIsValid()
	{
		if(maxPercent < 0)
		{
			JOptionPane.showMessageDialog(this, "Warning: Maximum Tip must be Greater than 0","Error",JOptionPane.WARNING_MESSAGE);
			this.setMaxDefault();
			return false;
		}
		else if(maxPercent < minPercent )
	    {
			JOptionPane.showMessageDialog(this, "Error: Maximum Tip must be Greater than Max Tip","Error",JOptionPane.WARNING_MESSAGE);
			this.setMaxDefault();
			this.setMinDefault();
			return false;
		}
		return true;
	}
	public void updateVariables()
	{
		minPercent=Double.parseDouble(minTextField.getText());
		maxPercent=Double.parseDouble(maxTextField.getText());
	}

}
