
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.HashMap;

public class Operation extends JFrame implements ActionListener
{
  Properties pr;
  JTextField textF1,textF2;
  JLabel lebel1,lebel2;
  JButton operationButton[];
  String keys[];
  String values[];

  public Operation()
  {
    this.setLayout(null);
    lebel1=new JLabel();
    lebel2=new JLabel();
    textF1=new JTextField();
    textF2=new JTextField();	
    operationButton=new JButton[3];
    try
    {
      FileInputStream inp=new FileInputStream("LocalDatabase.properties");
      pr=new Properties();
      pr.load(inp);
    }
    catch(Exception e){ }   
  }

    public void addWord()
    {
    this.setBackground(Color.RED);
    this.setBounds(400,20,240,250);
    
    lebel1.setText("Enter The word");
    lebel1.setForeground(new Color(0, 230, 54));
    lebel1.setFont(new Font("Lucida Handwriting", Font.BOLD, 15));    
    lebel1.setBounds(20,20,170,20);
    this.add(lebel1);
    
    textF1.setBounds(20,50,170,20);
    textF1.setForeground(new Color(0, 50, 230));
    this.add(textF1);
    
    lebel2.setText("Enter the meaning");
    lebel2.setForeground(new Color(0, 230, 54));
    lebel2.setFont(new Font("Lucida Handwriting", Font.BOLD, 15));    
    lebel2.setBounds(20,80,170,20);
    this.add(lebel2);
    
    textF2.setBounds(20,110,170,20);
    textF2.setForeground(new Color(0, 50, 230));
    this.add(textF2);
    
    operationButton[0]=new JButton("Add");
    operationButton[0].setBounds(80,150,70,20);
    operationButton[0].addActionListener(this);
    this.add(operationButton[0]);
    this.setVisible(true);
  }
  public void searchWord()
  {
    this.setBounds(400,20,240,170);
    
    lebel1.setText("Enter word search");
    lebel1.setForeground(new Color(0, 230, 54));
    lebel1.setFont(new Font("Lucida Handwriting", Font.BOLD, 15));    
    lebel1.setBounds(20,20,170,20);
    
    textF1.setBounds(20,50,170,20);
    textF1.setForeground(new Color(0, 50, 230));
    
    operationButton[1]=new JButton("Search");
    textF1.setForeground(new Color(0, 50, 230));
    operationButton[1].setBounds(50,90,120,20);
    operationButton[1].addActionListener(this);
    
    this.add(lebel1);
    this.add(textF1);
    this.add(operationButton[1]);
    this.setVisible(true);
	}
	public void deleteWord()
  {

    this.setBounds(400,20,240,250);
    this.setBackground(new Color(0, 50, 230));
    
    lebel1.setText("Enter Word to delete");
    lebel1.setBounds(20,20,170,20);
    textF1.setBounds(20,50,170,20);
    
    operationButton[2]=new JButton("Delete");
    operationButton[2].setBounds(50,90,120,20);
    operationButton[2].addActionListener(this);
    
    this.add(lebel1);
    this.add(textF1);
    this.add(operationButton[2]);
    this.setVisible(true);

	}
  public void actionPerformed(ActionEvent evt)
  {
    if(evt.getSource()==operationButton[0]){
      add();
    }
    else if(evt.getSource()==operationButton[1]){
      search();
    }
    else if(evt.getSource()==operationButton[2]){
      remove();
    }
  }
	public void add()
  {
		try
    {
      String s1=textF1.getText().trim();
      String s2=textF2.getText().trim();
      
      if(s1.length()==0 || s2.length()==0 )
      {
        JOptionPane.showMessageDialog(this, " Enter Values please");
      }
      else
      {
        pr.setProperty(s1,s2);
        pr.store(new FileWriter("LocalDatabase.properties"),"Dictionary App");  
        JOptionPane.showMessageDialog(this, " Add Successfuly");
        textF1.setText("");
        textF2.setText("");
      }
    }catch(Exception e){ }
  }

  public void search()
  {
    String s1=textF1.getText().trim();
    if(s1.length()==0 )
    {
      JOptionPane.showMessageDialog(this, " Enter Values please");
    }
    else
    {
      try
      {
        if(pr.getProperty(s1)==null)
          JOptionPane.showMessageDialog(this,"No Meaning in Dictionary");
        else
        {
          JOptionPane.showMessageDialog(this,"(Word)  "+s1+" = "+"(Meaning)  "+pr.getProperty(s1));
          textF1.setText("");
        }
      }catch(Exception e){ }
  	}
  }

	public void remove()
  {
    String s1=textF1.getText().trim();
    if(s1.length()==0 ){
      JOptionPane.showMessageDialog(this, " Enter Values please");
    }
    else
    {
		  try
      {
        pr.remove(  (Object)s1  );
        pr.store(new FileWriter("LocalDatabase.properties"),"Dictionary App");  
        JOptionPane.showMessageDialog(this, " Remove Successfuly");
        textF1.setText("");
      }catch(Exception e){ }
	  }
  }

	public void list()
  {
    this.setBounds(400,20,480,480);
    String column[]={"Word","Meaing"};   
    String s[];
    String s1[];
    int count=0;
    int numofValue=0; 
    boolean check=true;
    try
    {
      FileInputStream fn=new FileInputStream("LocalDatabase.properties");
      Properties pr=new Properties();
      pr.load(fn);
      Enumeration en=pr.propertyNames();
      while(en.hasMoreElements())
      {
        String key=(String)en.nextElement();
        String value=pr.getProperty(key);
        numofValue++;
      }
    }
    catch(Exception e){ }
    
    s=new String[numofValue];
    s1=new String[numofValue];
    String [][] arr=new String[numofValue][2];
    try
    {
      FileInputStream fn=new FileInputStream("LocalDatabase.properties");
      Properties pr=new Properties();
      pr.load(fn);
      Enumeration en=pr.propertyNames();
      while(en.hasMoreElements())
      {
        String key=(String)en.nextElement();
        String value=pr.getProperty(key);
        s[count]=value;
        s1[count]=key;
        count++;
      }
    }
    catch(Exception e){ } 
    int u=0;
    for (int i =0;i<numofValue;i++)  
    {  
      for(int j=0;j<2;j++)  
      {  
        if(check)
        {
          arr[i][j]=s1[i];
          check=false;
        }
        else
        {
          arr[i][j]=s[i];
          check=true;
        }
      }  
    }  
    JTable jt=new JTable(arr,column);  
    JScrollPane sp=new JScrollPane(jt);  
    jt.setCellSelectionEnabled(false);
    jt.setFocusable(false);
    jt.setRowSelectionAllowed(false);
    this.add(sp);          
    this.setVisible(true);
    this.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
  }
}


