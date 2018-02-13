package Client.PL;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;

public class Menu extends JPanel {

	 JButton b1;//the buttons and labels are held as fields so they can be accessed by the Main window
	 JButton b2;
	 JButton log_out_button;
	
	  Menu()
	{
		super(new GridBagLayout());
		this.setBackground(new Color(100,100,100));//set background(default theme=0 so we pick that color)
		b1 = new JButton("b1");//allocate buttons
		b2 = new JButton("b2");
		log_out_button = new JButton("Log Out");


		GridBagConstraints c = new GridBagConstraints();
		c.ipady=1;//set inner padding
		c.ipadx=3;
		c.weightx = 1;

		c.insets=new Insets(2,10,2,3);
		c.anchor=GridBagConstraints.WEST;
		c.gridx=0;
		c.fill=GridBagConstraints.WEST;
		this.add(b1,c);
		
		c.insets=new Insets(2,10,2,0);//change outer padding
		c.anchor=GridBagConstraints.WEST;
		c.gridx=1;
		this.add(b2,c);
		
		c.weightx = 0;
		c.insets=new Insets(2,500,2,10);
		c.anchor=GridBagConstraints.WEST;
		c.gridx=2;
		this.add(log_out_button,c);
		
		

		this.setFocusable(false);
	}
	
}
