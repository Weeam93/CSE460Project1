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
		ButtonHandlers();
		textBoxHandlers();
	}
	private static void textBoxHandlers()
	{
		homePanel.getTaxTextField().getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				warn();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				//warn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				warn();
			}
			public void warn()
			{
				//System.out.println("yo");
				if(Double.parseDouble(homePanel.getGuestTextField().getText())<0)
				{
					JOptionPane.showMessageDialog(myFrame, "Error: Number of Guest must be greater than 0","Error",JOptionPane.ERROR_MESSAGE);
					//homePanel.getGuestTextField().setText("1");
				}
			}
		
		});
		}
	private static void ButtonHandlers()
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
	
}
