package Client.PL;

import javax.imageio.ImageIO;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.border.EmptyBorder;

import Server.BL.Consumer;
import Server.BL.BusinessCategory;
import Server.BL.LoginHandler;
import Server.BL.SensorType;
import Server.BL.Coupon;

public class MainWindow extends JFrame implements KeyListener, ActionListener{

	private Consumer user;
	private Menu menu;
	private JComboBox coupon_category_box;
	private JComboBox coupon_sensor_box;
	private JTextField coupon_min_price_field;
	private JTextField coupon_max_price_field;
	private JButton search;
	private JLabel prevButton;
	private JLabel nextButton;
	private JLabel purchaseButton;
	private Vector<Coupon> currentCoupons;
	private int currentIndex;


	private JLabel couponNameLABEL=new JLabel("Name:");
	private JLabel couponDescriptionLABEL=new JLabel("Description:");
	private JLabel couponPriceLABEL=new JLabel("Price:");
	private JLabel couponPriceAfterDiscountLABEL=new JLabel("Discount Price:");
	private JLabel couponRatingLABEL=new JLabel("Rating:");
	private JLabel couponDeadlineLABEL=new JLabel("Expiration date:");
	private JLabel couponCodeLABEL=new JLabel("Code:");
	private JLabel couponCategoryLABEL=new JLabel("Category:");
	private JLabel couponName=new JLabel("");
	private JLabel couponDescription=new JLabel("");
	private JLabel couponPrice=new JLabel("");
	private JLabel couponPriceAfterDiscount=new JLabel("");
	private JLabel couponRating=new JLabel("");
	private JLabel couponDeadline=new JLabel("");
	private JLabel couponCode=new JLabel("");
	private JLabel couponCategory=new JLabel("");
	private JLabel backgroundLabel;
	private JPanel couponPanel;
	private Server.BL.SearchHandler searchHandler;

	public MainWindow (Consumer cons)
	{
		super("Coupons 4 U");//call the constructor with the title 
		this.user=cons;
		this.searchHandler=new Server.BL.SearchHandler();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu = new Menu();

		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File("prev.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		prevButton = new JLabel(new ImageIcon(buttonIcon));
		prevButton.setBorder(new EmptyBorder(0,0,0,0));

		try {
			buttonIcon = ImageIO.read(new File("next.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		nextButton = new JLabel(new ImageIcon(buttonIcon));
		nextButton.setBorder(BorderFactory.createEmptyBorder());

		try {
			buttonIcon = ImageIO.read(new File("purchase.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		purchaseButton = new JLabel(new ImageIcon(buttonIcon));
		purchaseButton.setBorder(BorderFactory.createEmptyBorder());

		currentIndex=0;


		String[] categories=BusinessCategory.getStrings();
		coupon_category_box = new JComboBox(categories);
		coupon_category_box.setSelectedIndex(0);
		
		String[] sensors=SensorType.getStrings();
		coupon_sensor_box = new JComboBox(sensors);
		coupon_sensor_box.setSelectedIndex(0);


		coupon_min_price_field = new JTextField("0",5);
		coupon_max_price_field = new JTextField("",5);
		search=new JButton("Search");
		JLabel minLabel=new JLabel("Minimum price:");
		JLabel maxLabel=new JLabel("Maximum price:");
		JLabel intro=new JLabel("Hello, "+user.getUserName());
		JLabel searchLabel=new JLabel("Search for coupons:");
		JLabel categoryLabel=new JLabel("Category:");
		JLabel sensorLabel=new JLabel("Sensor:");

		couponNameLABEL=new JLabel("Name:");
		couponDescriptionLABEL=new JLabel("Description:");
		couponPriceLABEL=new JLabel("Price:");
		couponPriceAfterDiscountLABEL=new JLabel("Discount Price:");
		couponRatingLABEL=new JLabel("Rating:");
		couponDeadlineLABEL=new JLabel("Expiration date:");
		couponCodeLABEL=new JLabel("Code:");
		couponCategoryLABEL=new JLabel("Category:");


		GridBagConstraints c = new GridBagConstraints();


		couponPanel=new JPanel();
		couponPanel.setLayout(new GridBagLayout());
		couponPanel.setOpaque(true);
		couponPanel.setBackground(new Color(0,0,0,0)); 		

		backgroundLabel=new JLabel(new ImageIcon("background.jpg"));
		backgroundLabel.setLayout(new GridBagLayout());

		setContentPane(backgroundLabel);

		c = new GridBagConstraints();

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(0,50,0,0);
		c.anchor=GridBagConstraints.NORTHEAST;
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(menu,c);//add the menu to the upper part of the screen

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(10,20,0,0);
		c.anchor=GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(intro,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(60,40,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(searchLabel,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(93,20,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(minLabel,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(90,120,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(coupon_min_price_field,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(92,210,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(maxLabel,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(90,315,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(coupon_max_price_field,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(92,390,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(categoryLabel,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(87,455,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(coupon_category_box,c);
		
		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(92,630,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(sensorLabel,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(87,700,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(coupon_sensor_box,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(90,850,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(search,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(200,80,20,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(couponPanel,c);



		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(0,0,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponNameLABEL,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(30,0,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponPriceLABEL,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(60,0,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponPriceAfterDiscountLABEL,c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(90,0,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponRatingLABEL,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(120,0,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponDeadlineLABEL,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(12,0,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponCodeLABEL,c);
		

		c.ipady=3;
		c.ipadx=3;
		c.gridx=1;
		c.gridy=0;
		c.anchor=GridBagConstraints.NORTHWEST;
		c.insets=new Insets(0,0,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponDescriptionLABEL,c);
		
		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(90,240,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponCategoryLABEL,c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(0,110,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponName,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(30,110,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponPrice,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(60,110,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponPriceAfterDiscount,c);


		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(90,110,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponRating,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(120,110,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponDeadline,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(12,110,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponCode,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=0;
		c.gridy=1;
		c.insets=new Insets(-50,320,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponCategory,c);

		c.ipady=3;
		c.ipadx=3;
		c.gridx=1;
		c.gridy=0;
		c.insets=new Insets(30,10,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		couponPanel.add(couponDescription,c);

		this.menu.b2.setFont(new Font("Helvetica", Font.BOLD, 15));//set the fonts of the buttons in the menu
		this.menu.b1.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.menu.log_out_button.setFont(new Font("Helvetica", Font.BOLD, 15));
		this.menu.b1.setForeground(new Color(13,13,13));//set the font colors in the buttons in the menu
		this.menu.b2.setForeground(new Color(13,13,13));
		this.menu.log_out_button.setForeground(new Color(13,13,13));


		intro.setFont(new Font("Miriam", Font.BOLD, 20));
		intro.setForeground(new Color(0,0,128));

		searchLabel.setFont(new Font("Ravie", Font.PLAIN, 17));
		minLabel.setFont(new Font("Miriam", Font.PLAIN, 16));
		maxLabel.setFont(new Font("Miriam", Font.PLAIN, 16));
		coupon_min_price_field.setFont(new Font("Miriam", Font.PLAIN, 16));
		coupon_max_price_field.setFont(new Font("Miriam", Font.PLAIN, 16));
		categoryLabel.setFont(new Font("Miriam", Font.PLAIN, 16));
		sensorLabel.setFont(new Font("Miriam", Font.PLAIN, 16));
		coupon_category_box.setFont(new Font("Miriam", Font.PLAIN, 16));
		coupon_sensor_box.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponName.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponDescription.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponPrice.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponPriceAfterDiscount.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponRating.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponDeadline.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponCode.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponCategory.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponNameLABEL.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponDescriptionLABEL.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponPriceLABEL.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponPriceAfterDiscountLABEL.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponRatingLABEL.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponDeadlineLABEL.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponCodeLABEL.setFont(new Font("Miriam", Font.PLAIN, 16));
		couponCategoryLABEL.setFont(new Font("Miriam", Font.PLAIN, 16));

		coupon_category_box.setBackground(new Color(100,149,237));
		coupon_sensor_box.setBackground(new Color(100,149,237));

		coupon_category_box.addActionListener(this);	
		coupon_sensor_box.addActionListener(this);
		coupon_min_price_field.addActionListener(this);
		coupon_max_price_field.addActionListener(this);
		search.addActionListener(this);//add this JFrame as the listener for all button and keyboard clicks
		menu.b1.addActionListener(this);//add this JFrame as the listener for all button and keyboard clicks
		menu.b2.addActionListener(this);
		menu.log_out_button.addActionListener(this);
		this.addKeyListener(this);

		prevButton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(--currentIndex<0)
					currentIndex=currentCoupons.size()-1;
				displayCoupon(currentCoupons.elementAt(currentIndex));
			}
		});

		nextButton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if(++currentIndex==currentCoupons.size())
					currentIndex=0;
				displayCoupon(currentCoupons.elementAt(currentIndex));

			}
		});
		
		rootPane.setDefaultButton(search);
		
		this.pack();
		this.setVisible(true);
		this.setFocusable(true);


	}

	public void keyPressed(KeyEvent k) {

		if(k.getKeyCode()==KeyEvent.VK_ESCAPE)
			this.dispose();
	}



	public void actionPerformed(ActionEvent a) {
		if(a.getSource().equals(search))
		{
			this.currentIndex=0;
			try {
				currentCoupons=this.searchHandler.getCoupons(coupon_min_price_field.getText(),
						coupon_max_price_field.getText(),coupon_category_box.getSelectedItem().
						toString());

				if(currentCoupons==null) 
					return;

				displayCoupon(currentCoupons.elementAt(currentIndex));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (a.getSource().equals(this.menu.log_out_button))
		{
			this.dispose();
			new LoginScreen();
		}



	}





	public void displayCoupon(Coupon coupon){	
		
		
		couponPanel.setOpaque(true);
		couponPanel.setBackground(new Color(0,0,0,0)); 		
		backgroundLabel.setIcon(new ImageIcon("background.jpg"));
		
		couponName.setText(coupon.getName());
		couponDescription.setText(coupon.getDescription());
		couponPrice.setText(coupon.getPrice());
		couponPriceAfterDiscount.setText(coupon.getDiscountPrice());
		couponRating.setText(coupon.getRating());
		couponDeadline.setText(coupon.getDeadLine());
		couponCode.setText(coupon.getCode());
		couponCategory.setText(coupon.getCategory());
		couponNameLABEL.setText("Name:");
		couponDescriptionLABEL.setText("Description:");
		couponPriceLABEL.setText("Price:");
		couponPriceAfterDiscountLABEL.setText("Discount Price:");
		couponRatingLABEL.setText("Rating:");
		couponDeadlineLABEL.setText("Expiration date:");
		couponCodeLABEL.setText("Code:");
		couponCategoryLABEL.setText("Category:");
		GridBagConstraints c = new GridBagConstraints();

		c.ipady=0;
		c.ipadx=0;
		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(350,-750,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(prevButton,c);


		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(350,-650,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(nextButton,c);

		c.gridx=0;
		c.gridy=0;
		c.insets=new Insets(350,-200,0,0);
		c.weightx = 1;
		c.weighty = 1;		
		backgroundLabel.add(purchaseButton,c);


	}




	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}


}
