import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;


public class TipTailoringScreen extends JPanel{
	
	private JPanel headerPanel;
	private JPanel bodyPanel;
	private int guestCount;
	private JButton homeScreenBtn;

	public TipTailoringScreen()
	{
		guestCount=4;
		this.setSize(350, 400);
		this.setVisible(true);
		
		
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
	}
	private void setUpBody()
	{
		bodyPanel.setLayout(new BoxLayout(bodyPanel,BoxLayout.Y_AXIS));
		JPanel[] panelList=new JPanel[guestCount];
		JTextField[] textFieldList=new JTextField[guestCount];
		JSlider[] sliderList=new JSlider[guestCount];
		JLabel[] labelList=new JLabel[guestCount];
		for(int i=0;i<guestCount;i++)
		{
			panelList[i]=new JPanel();
			panelList[i].setLayout(new BoxLayout(panelList[i],BoxLayout.X_AXIS));
			textFieldList[i]=new JTextField(10);
			textFieldList[i].setMaximumSize(new Dimension(Integer.MAX_VALUE,textFieldList[i].getMinimumSize().height));
			sliderList[i]=new JSlider(JSlider.HORIZONTAL,0,10,5);
			JLabel dollarLabel=new JLabel("$");
			labelList[i]=new JLabel(Integer.toString(sliderList[i].getValue()));
			panelList[i].add(textFieldList[i]);
			panelList[i].add(sliderList[i]);
			panelList[i].add(dollarLabel);
			panelList[i].add(labelList[i]);
			bodyPanel.add(panelList[i]);
		}
	}
	public JButton getHomeScreenBtn()
	{
		return homeScreenBtn;
	}

}
