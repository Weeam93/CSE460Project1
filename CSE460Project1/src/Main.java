import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Main {
	private static TipCalculatorFrame myFrame;
	private static HomeScreen homePanel;
	private static ConfigurationScreen configPanel;
	private static TipTailoringScreen tipTailorPanel;

	public static void main(String[] args)
	{
		myFrame = new TipCalculatorFrame();
		//myFrame.pack();
		myFrame.setResizable(false);
		myFrame.setVisible(true);
		homePanel=myFrame.getHome();
		configPanel=myFrame.getConfigScreen();
		tipTailorPanel=myFrame.getTipTailorScreen();
		navigationButtonHandlers();
		eventHandlers();
	}
	private static void eventHandlers()
	{
		homePanel.getCalcBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				homePanel.updateVariables();
				homePanel.guestInputIsValid();
				homePanel.taxInputIsValid();
				homePanel.deductionInputIsValid();
				homePanel.billInputIsValid();
				calcTipRate();
				homePanel.calculate(configPanel.isCheckedDeductions(),configPanel.isCheckedTax());
				homePanel.updateLabels();
				
				tipTailorPanel.setGuestCount(homePanel.getGuestCount());
				tipTailorPanel.updatePanel(homePanel.getPersonalTip());
				

			}});
		
		configPanel.getSaveBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				configPanel.updateVariables();
				configPanel.minTipPercentIsValid();
				configPanel.maxTipPercentIsValid();
			}});


	}
	
	private static void navigationButtonHandlers()
	{
		homePanel.getConfigBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFrame.switchToConfigScreen();
			}});
		homePanel.getTipTailorBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFrame.switchToTipTailorScreen();
			}});
		configPanel.getHomeScreenBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFrame.switchToHomeScreen();
			}});
		tipTailorPanel.getHomeScreenBtn().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFrame.switchToHomeScreen();
			}});
	}
	private static void calcTipRate()
	{
		System.out.println("Calculating Tip Rate");
		int rating=homePanel.getQuality();
		if(rating < 1)
			homePanel.setTipRate(configPanel.getMinTip());
		else if(rating >=5)
		{
			homePanel.setTipRate(configPanel.getMaxTip());
		}
		else
		{
			double rate=((double)rating/5)*configPanel.getMaxTip();
			homePanel.setTipRate(rate);
		}
		
	}

}
