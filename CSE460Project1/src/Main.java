import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Main {
	private static TipCalculatorFrame myFrame;
	private static HomeScreen homePanel;
	private static ConfigurationScreen configPanel;
	private static TipTailoringScreen tipTailorPanel;
	private static DecimalFormat df;
	private static Vector<JLabel> tipLabels;
	private static Vector<JSlider> tipSliders;
	private static boolean changed;
	
	public static void main(String[] args)
	{
		myFrame = new TipCalculatorFrame(); ///creats frame
		myFrame.setResizable(false);
		myFrame.setVisible(true);
		
		homePanel=myFrame.getHome(); //gets homeScreen
		configPanel=myFrame.getConfigScreen(); //gets the Configuration Screen
		tipTailorPanel=myFrame.getTipTailorScreen(); //gets the TipTailoringScreen
		
		df=new DecimalFormat("0.00");
		changed=false; //weather or not slider was changed
		navigationButtonHandlers(); //calls the navigation  handlers
		eventHandlers(); // calls even hanersl
	
	}
	private static void eventHandlers()
	{
		homePanel.getCalcBtn().addActionListener(new ActionListener(){ //when calc button is called

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				homePanel.updateVariables();
				homePanel.guestInputIsValid();//check for valid input
				homePanel.taxInputIsValid();//check for valid input
				homePanel.deductionInputIsValid();//check for valid input
				homePanel.billInputIsValid();//check for valid input
				calcTipRate(); //calcs the tip Rate
				homePanel.calculate(configPanel.isCheckedDeductions(),configPanel.isCheckedTax()); //cacluates the total 
				homePanel.updateLabels(); //updates thte labels
				
				homePanel.getTipTailorBtn().setEnabled(true); //can travrese to other page after clicking calculate
				tipTailorPanel.setGuestCount(homePanel.getGuestCount()); //sends the number of guests to the tip page
				tipTailorPanel.updatePanel(homePanel.getPersonalTip()); //sends the personaltip as the starting personal tip
				sliderHandler(); //checks if the sliders in the tip page are changed

			}});
		
		configPanel.getSaveBtn().addActionListener(new ActionListener(){ //when save button is called

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				configPanel.updateVariables(); //updates the min and max percent
				configPanel.minTipPercentIsValid(); //check for valid input
				configPanel.maxTipPercentIsValid(); //check for valid input
			}});
	}
	private static void sliderHandler()
	{
		tipSliders=tipTailorPanel.getSliders();
		tipLabels=tipTailorPanel.getLabels();
		for(int i=0;i<tipSliders.size();i++) //gets all the sliders
		{
			final int k=i; //current slider
			final double basePrice=homePanel.getPersonalTip(); //gets that person personal tip
			tipSliders.elementAt(i).addChangeListener(new ChangeListener(){
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					JSlider source=(JSlider)e.getSource();
					if(!source.getValueIsAdjusting())
					{
						if(k==0) //if first slider changed
							changed=true;
						double newPrice=((double)source.getValue()/100)*homePanel.getTotalTip(); //changes the slider price
						tipLabels.elementAt(k).setText(df.format(newPrice)); //changes the label for the price
					}
				}});
		}
	}
	private static void navigationButtonHandlers()
	{
		homePanel.getConfigBtn().addActionListener(new ActionListener(){//switches screen

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFrame.switchToConfigScreen(); 
			}});
		homePanel.getTipTailorBtn().addActionListener(new ActionListener(){//switches screen

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFrame.switchToTipTailorScreen();
			}});
		configPanel.getHomeScreenBtn().addActionListener(new ActionListener(){//switches screen

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFrame.switchToHomeScreen();
			}});
		tipTailorPanel.getHomeScreenBtn().addActionListener(new ActionListener(){//switches screen

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				double sum=0;
				for(int i=0;i<tipLabels.size();i++)
				{
					sum=sum+Double.parseDouble(tipLabels.elementAt(i).getText()); //updates the TipTotal
				}
				
			//updates the TipRate
				homePanel.getTipRateLabel().setText(df.format(homePanel.calcNewTipRate(sum,configPanel.isCheckedDeductions(),configPanel.isCheckedTax()))+" %");
				homePanel.setTotalTip(sum);
				if(changed==true)
				{
					//Sets the peronsal tip to tailored
				homePanel.getPeronsalTiplabel().setText(df.format(Double.parseDouble(tipLabels.firstElement().getText())));
				homePanel.getPersonalTipLabelLeft().setText("Tailored Per Person Tip");
				}
				if(sum==0)
				{
					homePanel.getTipRateLabel().setText(df.format(homePanel.getTipRate())+"%");
				}
				myFrame.switchToHomeScreen();
			}});
	}
	//calculates the TipRate baseed of min and max tip percentages and rateing
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
