package Client.PL;

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


public class RegisterWindow extends JFrame implements KeyListener, ActionListener{

	private LoginHandler loginHandler;
	private JTextField user_field;
	private JTextField pass_field;
	private JTextField email_field;
	private JTextField phone_field;
	private JRadioButton owner;
	private JRadioButton consumer;
	private JButton register;
	private JLabel categoryLabel;
	private JLabel email;
	private JLabel phone;

	private JLabel allLabel;
	private JLabel sportsLabel;
	private JLabel restaurantsLabel;
	private JLabel movieLabel;
	private JLabel hotelLabel;
	private JLabel otherLabel;


	private JCheckBox allCheck;
	private JCheckBox sportsCheck;
	private JCheckBox restaurantsCheck;
	private JCheckBox movieCheck;
	private JCheckBox hotelCheck;
	private JCheckBox otherCheck;




	public RegisterWindow (LoginHandler loginHandler)
	{
		super("Coupons 4 U");//call the constructor with the title 
		this.loginHandler=loginHandler;
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridBagLayout());

		user_field = new JTextField("",10);
		pass_field = new JPasswordField("",10);
		email_field = new JTextField("",10);
		phone_field = new JTextField("",10);


		register=new JButton("Register");
		JLabel userLabel=new JLabel("Username:");
		JLabel passLabel=new JLabel("Password:");

		JLabel ownerLabel=new JLabel("Owner");
		JLabel consumerLabel=new JLabel("Consumer");

		JLabel intro=new JLabel("Please submit your registration information");
		categoryLabel=new JLabel("Favorite Categories:");
		email=new JLabel("Email:");
		phone=new JLabel("Phone:");

		owner=new JRadioButton();
		owner.setMnemonic(KeyEvent.VK_N);
		owner.setActionCommand("owner");
		owner.setSelected(false);

		owner.setOpaque(false);
		owner.setContentAreaFilled(false);
		owner.setBorderPainted(false);


		consumer=new JRadioButton();
		consumer.setMnemonic(KeyEvent.VK_A);
		consumer.setActionCommand("consumer");

		consumer.setOpaque(false);
		consumer.setContentAreaFilled(false);
		consumer.setBorderPainted(false);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();

		group.add(owner);
		group.add(consumer);




		//		  allLabel=new JLabel("All");
		//		  sportsLabel=new JLabel("Sports");
		//		  restaurantsLabel=new JLabel("Restaurants & bars");
		//		  movieLabel=new JLabel("Movies & Cinema");
		//		  hotelLabel=new JLabel("Hotel & Spas");
		//		  otherLabel=new JLabel("Other");

		categoryLabel=new JLabel("Categories:");
		allCheck=new JCheckBox("All");
		allCheck.setOpaque(false);
		allCheck.setContentAreaFilled(false);
		allCheck.setBorderPainted(false);

		sportsCheck=new JCheckBox("Sports");
		sportsCheck.setOpaque(false);
		sportsCheck.setContentAreaFilled(false);
		sportsCheck.setBorderPainted(false);

		restaurantsCheck=new JCheckBox("Restaurants & Bars");
		restaurantsCheck.setOpaque(false);
		restaurantsCheck.setContentAreaFilled(false);
		restaurantsCheck.setBorderPainted(false);

		movieCheck=new JCheckBox("Movies & Cinema");
		movieCheck.setOpaque(false);
		movieCheck.setContentAreaFilled(false);
		movieCheck.setBorderPainted(false);

		hotelCheck=new JCheckBox("Hotel & Spas");
		hotelCheck.setOpaque(false);
		hotelCheck.setContentAreaFilled(false);
		hotelCheck.setBorderPainted(false);


		otherCheck=new JCheckBox("Other");
		otherCheck.setOpaque(false);
		otherCheck.setContentAreaFilled(false);
		otherCheck.setBorderPainted(false);


		// Register an action listener for the radio buttons.
		owner.addActionListener(this);
		consumer.addActionListener(this);


		//		user_field.addActionListener(this);
		//		pass_field.addActionListener(this);
		register.addActionListener(this);//add this JFrame as the listener for all button and keyboard clicks

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
		c.insets=new Insets(10,0,0,0);
		c.anchor=GridBagConstraints.NORTH;
		c.weightx = 1;
		c.weighty = 1;
		this.add(intro, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(30,0,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(ownerLabel, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(30,100,0,0);
		c.weightx = 1;
		c.weighty = 1;
		backgroundLabel.add(owner, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(50,0,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(consumerLabel, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(50,100,0,0);
		c.weightx = 1;
		c.weighty = 1;
		backgroundLabel.add(consumer, c);


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
		c.insets=new Insets(-20,50,0,0);
		c.anchor=GridBagConstraints.WEST;
		c.weightx = 1;
		c.weighty = 1;
		this.add(passLabel, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(60,50,0,0);
		c.anchor=GridBagConstraints.WEST;
		c.weightx = 1;
		c.weighty = 1;
		this.add(email, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(140,50,0,0);
		c.anchor=GridBagConstraints.WEST;
		c.weightx = 1;
		c.weighty = 1;
		this.add(phone, c);



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
		c.insets=new Insets(-20,150,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(pass_field, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(60,150,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(email_field, c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(140,150,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(phone_field, c);



		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(-160,400,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(categoryLabel, c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(-80,400,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(allCheck, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(-30,400,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(sportsCheck, c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(20,400,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(restaurantsCheck, c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(70,400,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(movieCheck, c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(120,400,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(hotelCheck, c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(170,400,0,0);
		c.weightx = 1;
		c.weighty = 1;
		this.add(otherCheck, c);



		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(60,0,10,10);
		c.anchor=GridBagConstraints.SOUTHEAST;
		c.weightx = 1;
		c.weighty = 1;
		this.add(register, c);

		rootPane.setDefaultButton(register);//makes it so when "Enter" is pressed, the submit button action listener is called.


		//this.getContentPane().setBackground(new Color(139,115,85));//set the background




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
			try {
				String userInput=user_field.getText();
				String passInput=pass_field.getText();
				String emailInput=email_field.getText();
				String phoneInput=phone_field.getText();

				User res=loginHandler.authenticateLogin(userInput, "%");

				if(!(owner.isSelected()||consumer.isSelected())){
					JOptionPane.showMessageDialog(new JFrame(), "Please select a type of user.", "Registration Error",
							JOptionPane.ERROR_MESSAGE);
				}
				
				else if(res!=null)
					JOptionPane.showMessageDialog(new JFrame(), "Username already taken", "Registration Error",
							JOptionPane.ERROR_MESSAGE);
				else{

					this.setVisible(false);

					if(owner.isSelected()){
						loginHandler.addOwner(userInput, passInput);
						JOptionPane.showMessageDialog(new JFrame(), "User created.", "Registration Successful",
								JOptionPane.PLAIN_MESSAGE);

					}

					else if(consumer.isSelected())
					{
						if(emailInput.equals(""))
							JOptionPane.showMessageDialog(new JFrame(),"Please enter an email", "Registration Error",
									JOptionPane.ERROR_MESSAGE);
						else if(phoneInput.equals(""))
							JOptionPane.showMessageDialog(new JFrame(), "Please enter a phone", "Registration Error",
									JOptionPane.ERROR_MESSAGE);
						else{
							loginHandler.addConsumer(userInput,passInput,emailInput,phoneInput);

							if(allCheck.isSelected()){
								loginHandler.addCategoryToConsumer(userInput,"all");
							}
							else{
							if(sportsCheck.isSelected())
								loginHandler.addCategoryToConsumer(userInput,"sports");
							if(restaurantsCheck.isSelected())
								loginHandler.addCategoryToConsumer(userInput,"restaurants");
							if(movieCheck.isSelected())
								loginHandler.addCategoryToConsumer(userInput,"movies");
							if(hotelCheck.isSelected())
								loginHandler.addCategoryToConsumer(userInput,"hotels");
							if(otherCheck.isSelected())
								loginHandler.addCategoryToConsumer(userInput,"other");
							}
							JOptionPane.showMessageDialog(new JFrame(), "User created.", "Registration Successful",
									JOptionPane.PLAIN_MESSAGE);
						}


					}

				}}	catch (Exception e1) {
					e1.printStackTrace();
				}		
			}
			else if(e.getSource()==owner)
			{
				email.setVisible(false);
				phone.setVisible(false);
				email_field.setVisible(false);
				phone_field.setVisible(false);
				categoryLabel.setVisible(false);
				allCheck.setVisible(false);
				sportsCheck.setVisible(false);
				restaurantsCheck.setVisible(false);
				movieCheck.setVisible(false);
				hotelCheck.setVisible(false);
				otherCheck.setVisible(false);
			}

			else if(e.getSource()==consumer)
			{
				email.setVisible(true);
				phone.setVisible(true);
				email_field.setVisible(true);
				phone_field.setVisible(true);
				categoryLabel.setVisible(true);
				allCheck.setVisible(true);
				sportsCheck.setVisible(true);
				restaurantsCheck.setVisible(true);
				movieCheck.setVisible(true);
				hotelCheck.setVisible(true);
				otherCheck.setVisible(true);
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

