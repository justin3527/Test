package translate;

import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrameClass extends JFrame implements ActionListener {

	
	
	private  JRadioButton cnTokr;
	private  JRadioButton allTokr ;
	private  JRadioButton allToen ;
	private  JRadioButton allTojp ;
	private  JTextArea input ;
	private  JTextArea output ;
	private  JButton trans ;
	private  JButton exit ;	
	private ButtonGroup btnG;
	private translateFunc transFunc;
	private JScrollPane scroll1;
	private JScrollPane scroll2;
	public FrameClass() {
		
		transFunc = new translateFunc();
		this.setTitle("Translate");
		this.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panel pan = new Panel();
		Container con = this.getContentPane();
		
		pan.setLayout(null);
		pan.setBounds(0, 0, 500, 500);
		
		cnTokr = new JRadioButton("cn->kr");
		cnTokr.setBounds(0, 0, 120, 50);
		
		allToen = new JRadioButton("**->en");
		allToen.setBounds(125, 0, 120, 50);
		
		allTokr = new JRadioButton("**->kr");
		allTokr.setBounds(250, 0, 120, 50);
		
		allTojp = new JRadioButton("**->jp");
		allTojp.setBounds(375, 0, 120, 50);
		
		btnG = new ButtonGroup();
		
		btnG.add(cnTokr);
		btnG.add(allToen);
		btnG.add(allTojp);
		btnG.add(allTokr);
		
		pan.add(cnTokr);
		pan.add(allToen);
		pan.add(allTojp);
		pan.add(allTokr);
		
		input = new JTextArea();
		scroll1 = new JScrollPane(input);
		scroll1.setBounds(40,50,400,150);
		pan.add(scroll1);
		
		output = new JTextArea();
		scroll2 = new JScrollPane(output);
		scroll2.setBounds(40,220,400,150);
		pan.add(scroll2);
		
		trans = new JButton("번역");
		trans.setBounds(100, 380, 100, 50);
		pan.add(this.trans);
		
		exit = new JButton("종료");
		exit.setBounds(270, 380, 100, 50);
		pan.add(this.exit);
		
		trans.addActionListener(this);
		exit.addActionListener(this);
		
		con.add(pan);
		this.setSize(500, 500);
		this.setVisible(true);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(exit))
		{
			System.exit(0);
		}
		else if(e.getSource().equals(trans))
		{
			if(input.getText().equals(""))
			{
				output.setText("내용을 입력하세요");
				return;
			}
			
			System.out.println("input : "+input.getText());
			
			int select = findSelectedRadio();
			
			if(select == -1)
			{
				output.setText("번역 방법을 선택하시오");
				return;
			}
				
			transFunc.init();
			
			switch(select)
			{
			case 0:
				output.setText(transFunc.cn_To_kr(input.getText().toString()));
				break;
			case 1:
				output.setText(transFunc.To_en(input.getText()));
				break;
			case 2:
				output.setText(transFunc.To_kr(input.getText()));
				break;
			case 3:
				output.setText(transFunc.To_jp(input.getText()));
				break;
				
			}
			
			transFunc.clearBrowser();
		}
	}
	
	public int findSelectedRadio()
	{
		if(cnTokr.isSelected())
			return 0;
		else if(allToen.isSelected())
			return 1;
		else if(allTokr.isSelected())
			return 2;
		else if(allTojp.isSelected())
			return 3;
		else
			return -1;
	}
	
	public static void main(String []args)
	{
		FrameClass frame = new FrameClass();
	}
	
	


}
