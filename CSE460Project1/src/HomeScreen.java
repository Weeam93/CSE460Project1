import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;


public class HomeScreen extends JPanel{

	private JPanel guestPanel;
	private JPanel qualityPanel;
	private JPanel billTotalPanel;
	private JPanel billDeductionsPanel;
	private JPanel taxPanel;
	private JPanel tipRatePanel;
	private JPanel personTipPanel;
	private JPanel totalTipPanel;
	private JPanel totalPanel;
	private JLabel guestLabel;
	private JLabel qualityLabel;
	private JLabel billTotalLabel;
	private JLabel billDeductionsLabel;
	private JLabel taxLabel;
	private JLabel tipRateLabel;
	private JLabel totalTipLabel;
	private JLabel personTipLabel;
	private JLabel totalLabel;
	private JTextField billTotalTextField;
	private JTextField billDeductionsTextField;
	private JTextField taxTextField;
	private JTextField guestTextField;
	private JLabel tipRateOutput;
	private JLabel totalTipOutput;
	private JLabel  personTipOutput;
	private JLabel totalBillOutput;
	private JSlider qualitySlider;
	private JPanel bodyPanel;
	private JPanel footerPanel;
	private JPanel headerPanel;
	private JButton tipTailoringBtn;
	private JButton configureTipItemsBtn;
	private int guestCount;
	private double basebillTotal;
	private double deductions;
	private double tax;
	private double tipRate;
	private double totalTip;
	private double personalTip;
	private double totalBillCost;
	private DecimalFormat df;

	
	public HomeScreen()
	{
		this.setSize(350, 400);
		this.setVisible(true);
		setUpHeader();
		setUpBody();
		setUpFooter();
		//this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		df=new DecimalFormat("0.00");
		
		
		this.add(headerPanel,BorderLayout.NORTH);
		this.add(bodyPanel,BorderLayout.CENTER);
		this.add(footerPanel,BorderLayout.SOUTH);
		this.setGuestCount(1);
		this.setBillTotal(0.00);
		this.setBillDeductions(0.00);
		this.setTax(0.00);
		this.setTipRate(0.00);
		this.setTotalTip(0.00);
		this.setpersonalTip(0.00);
		this.setTotalBillCost(0.00);
	}
	private void setUpHeader()
	{
		headerPanel=new JPanel();
		JLabel headerLabel=new JLabel("Tip Splitting Calculator");
		headerPanel.add(headerLabel);
		
	}
	private void setUpFooter()
	{
		footerPanel=new JPanel(new GridLayout(1,2));
		tipTailoringBtn=new JButton("Tip Tailoring");
		configureTipItemsBtn=new JButton("Configuring Tip Items");
		footerPanel.add(tipTailoringBtn);
		footerPanel.add(configureTipItemsBtn);
	}
	private void setUpBody()
	{
		bodyPanel=new JPanel();
		bodyPanel.setLayout(new GridLayout(9,1));
		
		guestPanel=new JPanel(new GridLayout(1,2));
		qualityPanel=new JPanel(new GridLayout(1,2));
		billTotalPanel=new JPanel(new GridLayout(1,2));
		billDeductionsPanel=new JPanel(new GridLayout(1,2));
		taxPanel=new JPanel(new GridLayout(1,2));
		tipRatePanel=new JPanel(new GridLayout(1,2));
		totalTipPanel=new JPanel(new GridLayout(1,2));
		personTipPanel=new JPanel(new GridLayout(1,2));
		totalPanel=new JPanel(new GridLayout(1,2));

		guestLabel=new JLabel("Number of Guests");
		qualityLabel=new JLabel("Quality of Service");
		billTotalLabel=new JLabel("Bill Total");
		billDeductionsLabel=new JLabel("Bill Deductions");
		taxLabel=new JLabel("Tax");
		tipRateLabel=new JLabel("Tip Rate");
		totalTipLabel=new JLabel("Total Tip");
		personTipLabel=new JLabel("Per Person Tip");
		totalLabel=new JLabel("Total (Bill & Tip)");

		guestTextField=new JTextField(10);;
		billTotalTextField=new JTextField(10);
		billDeductionsTextField=new JTextField(10);
		taxTextField=new JTextField(10);
		tipRateOutput=new JLabel("0.0%");
		totalTipOutput=new JLabel("0.00");
		personTipOutput=new JLabel("0.00");
		totalBillOutput=new JLabel("0.00");

		guestPanel.add(guestLabel);
		JPanel rightGuestPanel=new JPanel();
		rightGuestPanel.add(new JLabel(""));
		rightGuestPanel.add(guestTextField);
		guestPanel.add(rightGuestPanel);
		
		qualityPanel.add(qualityLabel);
		qualitySlider=new JSlider(JSlider.HORIZONTAL,0,10,5);
		qualitySlider.setMajorTickSpacing(5);
		qualitySlider.setMinorTickSpacing(1);
		qualitySlider.setPaintLabels(true);
		qualityPanel.add(qualitySlider);

		billTotalPanel.add(billTotalLabel);
		JPanel rightBillTotalPanel=new JPanel();
		rightBillTotalPanel.add(new JLabel("$"));
		rightBillTotalPanel.add(billTotalTextField);
		billTotalPanel.add(rightBillTotalPanel);
		

		billDeductionsPanel.add(billDeductionsLabel);
		JPanel rightBillDeductionsPanel=new JPanel();
		rightBillDeductionsPanel.add(new JLabel("$"));
		rightBillDeductionsPanel.add(billDeductionsTextField);
		billDeductionsPanel.add(rightBillDeductionsPanel);

		taxPanel.add(taxLabel);
		JPanel rightTaxPanel=new JPanel();
		rightTaxPanel.add(new JLabel("$"));
		rightTaxPanel.add(taxTextField);
		taxPanel.add(rightTaxPanel);

		tipRatePanel.add(tipRateLabel);
		JPanel rightTipRatePanel=new JPanel();
		rightTipRatePanel.add(new JLabel(""));
		rightTipRatePanel.add(tipRateOutput);
		tipRatePanel.add(rightTipRatePanel);

		totalTipPanel.add(totalTipLabel);
		JPanel rightTotalTipPanel=new JPanel();
		rightTotalTipPanel.add(new JLabel("$"));
		rightTotalTipPanel.add(totalTipOutput);
		totalTipPanel.add(rightTotalTipPanel);

		personTipPanel.add(personTipLabel);
		JPanel rightPersonTipPanel=new JPanel();
		rightPersonTipPanel.add(new JLabel("$"));
		rightPersonTipPanel.add(personTipOutput);
		personTipPanel.add(rightPersonTipPanel);

		totalPanel.add(totalLabel);
		JPanel rightTotalPanel=new JPanel();
		rightTotalPanel.add(new JLabel("$"));
		rightTotalPanel.add(totalBillOutput);
		totalPanel.add(rightTotalPanel);
		
		bodyPanel.add(guestPanel);
		bodyPanel.add(qualityPanel);
		bodyPanel.add(billTotalPanel);
		bodyPanel.add(billDeductionsPanel);
		bodyPanel.add(taxPanel);
		bodyPanel.add(tipRatePanel);
		bodyPanel.add(totalTipPanel);
		bodyPanel.add(personTipPanel);
		bodyPanel.add(totalPanel);
	}
	public void setGuestCount(int numberOfGuest)
	{
		this.guestCount=numberOfGuest;
		guestTextField.setText(Integer.toString(guestCount));
	}
	public void setBillTotal(double billTotal)
	{	
		this.basebillTotal=billTotal;
		billTotalTextField.setText(df.format(basebillTotal));
	}
	public void setBillDeductions(double billDeductions)
	{
		this.deductions=billDeductions;
		billDeductionsTextField.setText(df.format(deductions));
	}
	public void setTax(double t)
	{
		this.tax=t;
		taxTextField.setText(df.format(tax));
	}
	public void setTipRate(double tipR)
	{
		this.tipRate=tipR;
		tipRateOutput.setText(df.format(tipRate)+"%");
	}
	public void setTotalTip(double totalT)
	{
		this.totalTip=totalT;
		totalTipOutput.setText(df.format(totalTip));
	}
	public void setpersonalTip(double personalT)
	{
		this.personalTip=personalT;
		personTipOutput.setText(df.format(personalTip));
	}
	public void setTotalBillCost(double totalBill)
	{
		this.totalBillCost=totalBill;
		totalBillOutput.setText(df.format(totalBillCost));
	}
	public int getGuestCount()
	{
		return this.guestCount;
	}
	public int getQuality()
	{
		return qualitySlider.getValue();
	}
	public double getTax()
	{
		return this.tax;
	}
	public double getTipRate()
	{
		return this.tipRate;
	}
	public JTextField getGuestTextField()
	{
		return this.guestTextField;
	}
	public JTextField getbillTotalTextField()
	{
		return this.billTotalTextField;
	}
	public JTextField getBillDeductionsTextField()
	{
		return this.billDeductionsTextField;
	}
	public JTextField getTaxTextField()
	{
		return this.taxTextField;
	}
	
	public JButton getConfigBtn()
	{
		return configureTipItemsBtn;
	}
	public JButton getTipTailorBtn()
	{
		return tipTailoringBtn;
	}

}