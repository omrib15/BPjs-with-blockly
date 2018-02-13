package Client.PL;

import Client.PL.Admin.AdminWindow;
import Client.PL.Owner.OwnerWindow;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.border.EmptyBorder;

import Server.BL.LoginHandler;
import Server.BL.Administrator;
import Server.BL.Owner;
import Server.BL.Consumer;
import Server.BL.User;
import Client.PL.Consumer.ConsumerWindow;


public class LoginScreen extends JFrame implements KeyListener, ActionListener{

	private LoginHandler loginHandler;
	private JTextField user_field;
	private JTextField pass_field;
	private JButton submit;
	private JButton register;
	
	public LoginScreen ()
	{
		super("Coupons 4 U");//call the constructor with the title 
		this.loginHandler=new LoginHandler();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridBagLayout());

                
		user_field = new JTextField("",10);
		pass_field = new JPasswordField("",10);
		submit=new JButton("Submit");
		register=new JButton("New user? Register");
		JLabel userLabel=new JLabel("Username:");
		JLabel passLabel=new JLabel("Password:");
		JLabel intro=new JLabel("Please submit your log in information");

		user_field.addActionListener(this);
		pass_field.addActionListener(this);
		submit.addActionListener(this);//add this JFrame as the listener for all button and keyboard clicks
		register.addActionListener(this);

		setVisible(true);
		this.addKeyListener(this);


		JLabel backgroundLabel=new JLabel(new ImageIcon("clouds.jpg"));
		backgroundLabel.setLayout(new GridBagLayout());

		setContentPane(backgroundLabel);

		GridBagConstraints c = new GridBagConstraints();


		

		

		
		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.gridwidth=GridBagConstraints.REMAINDER;
		c.insets=new Insets(10,3,3,3);
		c.anchor=GridBagConstraints.NORTH;
		c.weightx = 1;
		c.weighty = 1;
		this.add(intro, c);



		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(-100,50,0,0);
		c.anchor=GridBagConstraints.WEST;
		c.weightx = 1;
		c.weighty = 1;
		this.add(userLabel, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(-30,50,0,0);
		c.anchor=GridBagConstraints.WEST;
		c.weightx = 1;
		c.weighty = 1;
		this.add(passLabel, c);



		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(-100,150,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(user_field, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(-30,150,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(pass_field, c);



		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(60,10,10,0);
		c.anchor=GridBagConstraints.SOUTHWEST;
		c.weightx = 1;
		c.weighty = 1;
		this.add(register, c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(60,0,10,10);
		c.anchor=GridBagConstraints.SOUTHEAST;
		c.weightx = 1;
		c.weighty = 1;
		this.add(submit, c);
		
		rootPane.setDefaultButton(submit);//makes it so when "Enter" is pressed, the submit button action listener is called.


//		this.getContentPane().setBackground(new Color(139,115,85));//set the background


		this.submit.setBackground(new Color(210,180,140));
		this.submit.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.submit.setForeground(new Color(13,13,13));
		
		this.register.setBackground(new Color(210,180,140));
		this.register.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.register.setForeground(new Color(13,13,13));



		this.pack();
		this.setVisible(true);
		this.setFocusable(true);


	}




	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==register)
		{
			new RegisterWindow(loginHandler);
			return;
		}
		String userInput=user_field.getText();
		String passInput=pass_field.getText();

		try {
			User res=loginHandler.authenticateLogin(userInput, passInput);

			if(res==null)
				JOptionPane.showMessageDialog(new JFrame(), "Username or Password not found", "Login Error",
						JOptionPane.ERROR_MESSAGE);
			else{

				this.setVisible(false);

				if(res.getType().equals("consumer")){			
					//new MainWindow((Consumer)res);
                                    new ConsumerWindow((Consumer)res, this);
                                }
                                else if(res.getType().equals("owner")){			
					new OwnerWindow((Owner)res,this);
                                }
                                else if(res.getType().equals("admin")){			
					new AdminWindow((Administrator)res,this);
                                }
			}



		} catch (Exception e1) {
			e1.printStackTrace();
		}


	}





	public void keyPressed(KeyEvent k) {

		if(k.getKeyCode()==KeyEvent.VK_ESCAPE)
			this.dispose();
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


}
