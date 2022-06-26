
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AnandBind implements ActionListener{

JFrame frame;
JButton operationButton[];


String operationButtonName[]={"add","search","delete","list"};


public AnandBind(){

frame=new JFrame("Smart Dictionary");


JPanel mainPanel=new JPanel();
mainPanel.setBackground(Color.PINK);

JLabel lebel2=new JLabel("Smart Dictionary");
lebel2.setBounds(60,10,370,40);
lebel2.setForeground(new Color(0, 24, 245));
lebel2.setFont(new Font("Forte", Font.BOLD, 30));		
mainPanel.add(lebel2);

JPanel buttonPanel=new JPanel();
buttonPanel.setBackground(new Color(150, 216, 224));
buttonPanel.setBounds(10,60,370,40);

operationButton=new JButton[operationButtonName.length];
for(int i=0;i<operationButton.length;i++){
operationButton[i]=new JButton(operationButtonName[i]);
operationButton[i].addActionListener(this);
buttonPanel.add(operationButton[i]);
}

JLabel lebel1=new JLabel("Developed by Anand Bind");
lebel1.setBounds(20,115,370,40);
lebel1.setForeground(new Color(0,0,0));
lebel1.setFont(new Font("Lucida Handwriting", Font.BOLD, 20));		
mainPanel.add(lebel1);

mainPanel.add(buttonPanel);
mainPanel.setLayout(null);
frame.setVisible(true);
frame.add(mainPanel);
frame.setSize(400,200);
frame.setLocation(0,0);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     }

public void actionPerformed(ActionEvent evt){

if(evt.getSource()==operationButton[0]){
new Operation().addWord();
}else if(evt.getSource()==operationButton[1]){
new Operation().searchWord();
}
else if(evt.getSource()==operationButton[2]){
new Operation().deleteWord();
}
else if(evt.getSource()==operationButton[3]){
new Operation().list();
}

}

	public static void main(String[] args) {
		new AnandBind();
	}
}


