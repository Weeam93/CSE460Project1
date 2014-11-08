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
		Vector<JSlider> tipSliders=tipTailorPanel.getSliders();
		final Vector<JLabel> tipLabels=tipTailorPanel.getLabels();
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
