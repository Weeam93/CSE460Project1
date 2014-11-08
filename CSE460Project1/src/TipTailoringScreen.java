import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class TipTailoringScreen extends JPanel{
	
	private JPanel headerPanel;
	private JPanel bodyPanel;
	private int curGuestCount;
	private int newGuestCount;
	private JButton homeScreenBtn;
	private DecimalFormat df;
	private Vector<JPanel> panelList;
	private Vector<JTextField> textFieldList;
	private Vector<JSlider> sliderList;
	private Vector<JLabel> labelList;
	private JScrollPane scrollPane;

	public TipTailoringScreen()
	{
		curGuestCount=1;
		//this.setSize(375, 400);
		//this.setPreferredSize(new Dimension(400,400));
		this.setVisible(true);
		
		df=new DecimalFormat("0.00");
		
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	
		
		headerPanel=new JPanel();
		bodyPanel=new JPanel();
		
		bodyPanel.setLayout(new BoxLayout(bodyPanel,BoxLayout.Y_AXIS));
		panelList=new Vector<JPanel>();
	    textFieldList=new Vector<JTextField>();
		sliderList=new Vector<JSlider>();
		labelList=new Vector<JLabel>();
		
		
		setUpHeader();
		setUpBody(curGuestCount,0.00);
		JTextArea ta=new JTextArea();
		ta.setSize(300,300);
		scrollPane=new JScrollPane(bodyPanel);
		
		this.setLayout(new BorderLayout());
		this.add(headerPanel,BorderLayout.NORTH);
		this.add(scrollPane,BorderLayout.CENTER);
		
	textFieldList.firstElement().setText("ME");
	}
	private void setUpHeader()
	{
		homeScreenBtn=new JButton("Home");
		headerPanel.setLayout(new GridLayout(1,3));
		headerPanel.add(homeScreenBtn);
		headerPanel.add(new JLabel());
		headerPanel.add(new JLabel());
	}
	private void setUpBody(int guestCount,double personalTip)
	{
		
		for(int i=0;i<guestCount;i++)
		{
			panelList.add(new JPanel());
			panelList.lastElement().setLayout(new BoxLayout(panelList.lastElement(),BoxLayout.X_AXIS));
			textFieldList.add(new JTextField(10));
			textFieldList.lastElement().setMaximumSize(new Dimension(Integer.MAX_VALUE,textFieldList.lastElement().getMinimumSize().height));
			sliderList.add(new JSlider(JSlider.HORIZONTAL,0,100,5));
			sliderList.lastElement().setMajorTickSpacing(20);
			sliderList.lastElement().setMinorTickSpacing(10);
			//qualitySlider.setMinorTickSpacing();
			sliderList.lastElement().setPaintLabels(true);
			JLabel dollarLabel=new JLabel("$");
			labelList.add(new JLabel(df.format(personalTip)));
			panelList.lastElement().add(textFieldList.lastElement());
			panelList.lastElement().add(sliderList.lastElement());
			panelList.lastElement().add(dollarLabel);
			panelList.lastElement().add(labelList.lastElement());
			bodyPanel.add(panelList.lastElement());
			
			/*sliderList.lastElement().addChangeListener(new ChangeListener(){
			JLabel curLabel=labelList.lastElement();
				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					JSlider source=(JSlider)e.getSource();
					if(!source.getValueIsAdjusting())
					{
						double curPrice=Double.parseDouble(curLabel.getText());
						double newPrice=source.getValue()*
						curLabel.setText(df.format(source.getValue()));
					}
					
				}});*/
		}
		curGuestCount=guestCount;
	}
	public Vector<JSlider> getSliders()
	{
		return sliderList;
	}
	public Vector<JLabel> getLabels()
	{
		return labelList;
	}
	private void updateBody(double personalTip)
	{
		if(curGuestCount < newGuestCount)
		{
			int elementsToAdd=newGuestCount-curGuestCount;
			setUpBody(elementsToAdd,personalTip);
			curGuestCount=newGuestCount;
			
		}
		else if(curGuestCount > newGuestCount)
		{
			int elementsToRemove=curGuestCount-newGuestCount;
			for(elementsToRemove=elementsToRemove;elementsToRemove>0;elementsToRemove--)
			{
				bodyPanel.remove(panelList.elementAt(panelList.size()-1));
				panelList.remove(panelList.size()-1);
				textFieldList.remove(textFieldList.size()-1);
				sliderList.remove(sliderList.size()-1);
				labelList.remove(labelList.size()-1);
				curGuestCount--;		
			}	
		}
	}
	private void refreshLabels(double personalTip)
	{
		for(int i=0;i<labelList.size();i++)
		{
			labelList.elementAt(i).setText(df.format(personalTip));
		}
	}
	public void setGuestCount(int guest)
	{
		this.newGuestCount=guest;
	}
	public void updatePanel(double personalTip)
	{
		updateBody(personalTip);
		refreshLabels(personalTip);
		
	}
	public JButton getHomeScreenBtn()
	{
		return homeScreenBtn;
	}

}
