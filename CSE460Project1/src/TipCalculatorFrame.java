import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class TipCalculatorFrame extends JFrame{
	
	private static final String HOME_SCREEN = "HOMESCREEN";
	private static final String CONFIG_SCREEN = "CONFIGURATIONSCREEN";
	private static final String TAILOR_SCREEN = "TIPTAILORINGSCREEN";
	
	private JButton tipTailorBtn;
	private JButton configBtn;
	private JButton tipTailorHomeBtn;
	private JButton configHomeBtn;
	private JPanel contentPane;
	private HomeScreen hs;
	private ConfigurationScreen cs;
	private TipTailoringScreen ttS;
	
	public TipCalculatorFrame() //creates the Frame and addes all the screens to the cardlayouts
	{
		this.setSize(450, 450);
		setHomeScreen();
		setConfigScreen();
		setTipTailorScreen();
		
		contentPane=new JPanel();
		contentPane.setLayout(new CardLayout());
		contentPane.add(hs,HOME_SCREEN);
		contentPane.add(cs,CONFIG_SCREEN);
		contentPane.add(ttS,TAILOR_SCREEN);
		this.add(contentPane);

	}
	//setter and getters for the screens to be accessed from the main
	private void setHomeScreen()
	{
		this.hs=new HomeScreen();
	}
	private void setConfigScreen()
	{
		this.cs=new ConfigurationScreen();
	}
	private void setTipTailorScreen()
	{
		this.ttS=new TipTailoringScreen();
	}
	public HomeScreen getHome()
	{
		return this.hs;
	}
	public ConfigurationScreen getConfigScreen()
	{
		return this.cs;
	}
	public TipTailoringScreen getTipTailorScreen()
	{
		return this.ttS;
	}
	
	//swicth the screens requires switchign cards
	public void switchToTipTailorScreen()
	{
		CardLayout cards= (CardLayout) contentPane.getLayout();
		this.setSize(420, 400);
		cards.show(contentPane,TAILOR_SCREEN);
	}
	public void switchToConfigScreen()
	{
		CardLayout cards= (CardLayout) contentPane.getLayout();
		this.setSize(350, 400);
		cards.show(contentPane,CONFIG_SCREEN);
	}
	public void switchToHomeScreen()
	{
		CardLayout cards= (CardLayout) contentPane.getLayout();
		this.setSize(450, 450);
		cards.show(contentPane,HOME_SCREEN);
	}
	
}
