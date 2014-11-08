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
		myFrame = new TipCalculatorFrame();
		//myFrame.pack();
		myFrame.setResizable(false);
		myFrame.setVisible(true);
		homePanel=myFrame.getHome();
		configPanel=myFrame.getConfigScreen();
		tipTailorPanel=myFrame.getTipTailorScreen();
		df=new DecimalFormat("0.00");
		changed=false;
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
				
				homePanel.getTipTailorBtn().setEnabled(true);
				tipTailorPanel.setGuestCount(homePanel.getGuestCount());
				tipTailorPanel.updatePanel(homePanel.getPersonalTip());
				sliderHandler();

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
	private static void sliderHandler()
	{
		tipSliders=tipTailorPanel.getSliders();
		tipLabels=tipTailorPanel.getLabels();
		for(int i=0;i<tipSliders.size();i++)
		{
			final int k=i;
			final double basePrice=homePanel.getPersonalTip();
			tipSliders.elementAt(i).addChangeListener(new ChangeListener(){
			
				

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					JSlider source=(JSlider)e.getSource();
					if(!source.getValueIsAdjusting())
					{
						if(k==0)
							changed=true;
						double newPrice=((double)source.getValue()/100)*homePanel.getTotalTip();
						tipLabels.elementAt(k).setText(df.format(newPrice));
					}
				}});
		}
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
				double sum=0;
				for(int i=0;i<tipLabels.size();i++)
				{
					sum=sum+Double.parseDouble(tipLabels.elementAt(i).getText());
				}
				
			
				homePanel.getTipRateLabel().setText(df.format(homePanel.calcNewTipRate(sum,configPanel.isCheckedDeductions(),configPanel.isCheckedTax()))+" %");
				homePanel.setTotalTip(sum);
				if(changed==true)
				{
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
