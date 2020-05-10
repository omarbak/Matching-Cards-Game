import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.*;
@SuppressWarnings("serial")
public class MainMenu extends JFrame{
		//static fields to be able to use them in the concentration part
		public static  int timer1;
		public static int scorePenalty;
		public static int countDown;
		public static String rows;
		public static  String columns;
		public static  String themes;
		public static String namePlayer;
		public static String namePlayer1;
		public static String namePlayer2;
		//PANELS
		private JPanel p;
		private JPanel p1;
		private JPanel p2;

		//LABELS
		private JLabel welcome;
		private JLabel difficulty;
		private JLabel theme;
		private JLabel RC;
		//BUTTONS
		private JButton start;
		private JButton instructions;
		private JButton scoreboard;
		private JButton exit;
		//JRButton
	    private JRadioButton easy;
	    private JRadioButton medium;
	    private JRadioButton hard;
	    private JRadioButton singleplayer;
	    private JRadioButton multiplayer;


	    //ButtonGroups
	    private ButtonGroup bg1 ;
	    private ButtonGroup bg2 ;

	    //ComboBox
	    @SuppressWarnings("rawtypes")
		private JComboBox row;
		@SuppressWarnings("rawtypes")
		private JComboBox column;
		@SuppressWarnings("rawtypes")
		private JComboBox theme0;
		
		//different values of the JCOMBOBOX
	    private static String[] rows1 = {"N/A","2","3","4","6"};
	    private static String[] columns1 = {"N/A","2","3","4","5","6"};
	    private static String[] themelist = {"N/A","CATS","CARS","RANDOM"};
	    
	    
		public MainMenu() {
			//initializing main menu frame
			guiframe();
			//message showing to select all buttons
		    JOptionPane.showMessageDialog(null,"Please Make sure to select all the option \n Otherwise the game won't function properly","NOTE!", JOptionPane.INFORMATION_MESSAGE);
		    
		}
		
		
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void guiframe() {
			//frame
			setVisible(true);
			setSize(1000, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
	
			/////////
			
			//Pannel  background
			p = new JPanel();
			//Panel2 for buttons
			
			p1 = new JPanel(new GridBagLayout());
			p2 = new JPanel(new GridBagLayout());
			
			// Button groups
			
			bg1 = new ButtonGroup();
			bg2 = new ButtonGroup();
			
			
			//buttons for different tasks
			start = new JButton("START GAME");
			instructions = new JButton("INSTRUCTIONS");
			scoreboard = new JButton("SCOREBOARD");
			exit = new JButton ("EXIT");
			// JRbuttons
		    easy = new JRadioButton("Easy");
		    medium = new JRadioButton("Medium");
		    hard = new JRadioButton("Hard");
		    singleplayer = new JRadioButton("Single Player");
		    multiplayer = new JRadioButton("Multiplayer");
		    
		    //regrouping buttons 
		    bg1.add(easy);
		    bg1.add(medium);
		    bg1.add(hard);
			bg2.add(singleplayer);
			bg2.add(multiplayer);
			
			//creation of JCOMBOBOX
			
			row = new JComboBox(rows1);
			column = new JComboBox(columns1);
			theme0 = new JComboBox(themelist);
			//selection of rows number
			row.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange()==ItemEvent.SELECTED) {
							rows = (String) row.getSelectedItem();										
						System.out.println("jboxclicked1");}}});
			// selection of columns number
			column.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange()==ItemEvent.SELECTED) {
						columns = (String) column.getSelectedItem();
						System.out.println("jboxclicked2");}}});
			//selection of the game theme
			theme0.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange()==ItemEvent.SELECTED) {
						themes = (String) theme0.getSelectedItem();
						System.out.println("jboxclicked3");}}});
			
			



		    //DIFFICULTY JRBUTTONS functions
		    easy.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					timer1 = 1000;
					countDown = 60;
					scorePenalty = 0;
					System.out.println("t1");
					
				}});
		    medium.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					timer1 = 500;
					countDown=30;
					scorePenalty = -2;
					System.out.println("t2");
		
				}});
			hard.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					timer1 = 250;
					countDown=10;
					scorePenalty = -3;

					System.out.println("t3");
				}});
			
			// game mode buttons functions 
			
			singleplayer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    namePlayer = JOptionPane.showInputDialog(null, "Please enter your name","N/A");
					System.out.println("t4");
				}});
			
			
			multiplayer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					namePlayer1 = JOptionPane.showInputDialog(null, "Player1 name","player1");
					namePlayer2 = JOptionPane.showInputDialog(null, "Player2 name","player2");
					System.out.println("t5");
				}});
			
		
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//redirects to a method that checks if all required buttons are selected
					checkSelectedbuttons();
				} 
						
					});

			scoreboard.addActionListener(new ActionListener() {
				//score board opens the file scoretxt to see whats in it
				public void actionPerformed(ActionEvent e) {
					JTextField readtext = new JTextField();
					try {
						FileReader reader = new FileReader("src/myscore.txt");
						BufferedReader br = new BufferedReader(reader);
						readtext.read(br,null);
						br.close();
						readtext.requestFocus();
						readtext.setEditable(false);
						JOptionPane.showMessageDialog(null, readtext, "SCOREBOARD", JOptionPane.INFORMATION_MESSAGE);

					}
					catch (Exception eh) {
						JOptionPane.showMessageDialog(null, eh);
					}
				} 
						
					});

			
			// adding actionListener INSTRUCTIONS
			instructions.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//set of rules of the game
					JOptionPane.showMessageDialog(null, 
							"To win the game, you'll have to find all the matching pairs of cards on this deck." +"\n"+
							"Start by opening a random card and try to find its match."+"\n"+
							"There will be a timer showing while you're playing, the faster you match all the cards the higher your Score (Timer) will be."+
							"\n"+ "Good luck!",
							"Rules Of The Game/How To", JOptionPane.INFORMATION_MESSAGE);}});
			
			exit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("ByeBye");
					System.exit(0); //the only system.exit, will terminate the whole program if EXIT is pressed
				}});
			
			// label or announcement
			welcome = new JLabel("Welcome To The Matching Cards Game.");
			difficulty = new JLabel("DIFFICULTY");
			RC = new JLabel ("CHOOSE YOUR ROWS AND COLUMNS");
			theme = new JLabel("GAME THEME");
			
			// creates spacing
			GridBagConstraints constraint1 = new GridBagConstraints();
			//Spacing between buttons
			constraint1.insets = new Insets(10,10,10,10);
			
			// adding all the buttons and labels to my panel 
			p2.add(difficulty,constraint1);
			p2.add(easy,constraint1);
			p2.add(medium,constraint1);
			p2.add(hard,constraint1);
			
			
			// adding theme buttons to my pannel
			p2.add(theme,constraint1);
			p2.add(theme0);
			
			
			p2.add(RC,constraint1);
			p2.add(row,constraint1);
			p2.add(column,constraint1);
			
			// adding Function Buttons to Panel 1
		
			p1.add(start,constraint1);
			p1.add(instructions,constraint1);
			p1.add(scoreboard,constraint1);
			p1.add(exit,constraint1);
			p.add(welcome);
			p.add(singleplayer,constraint1);
			p.add(multiplayer,constraint1);
			
			
			
			//adding panel to my frame
			add(p,BorderLayout.NORTH);
			add(p1,BorderLayout.SOUTH);
			add(p2,BorderLayout.CENTER);
			validate(); 
		}
		
		///method to check if all the required buttons are checked. 
		  private void checkSelectedbuttons() {
		    	try {
		    		if(easy.isSelected()==false && medium.isSelected()==false && hard.isSelected()==false) {
					    JOptionPane.showMessageDialog(null,"Please make sure to select difficulty","Error", JOptionPane.INFORMATION_MESSAGE);
		    		}
		    		else if(singleplayer.isSelected()==false&& multiplayer.isSelected()==false) {
					    JOptionPane.showMessageDialog(null,"Please make sure to select Player Mode","Error", JOptionPane.INFORMATION_MESSAGE);
		    		}
		    		else if(theme0.getSelectedItem()==null) {
					    JOptionPane.showMessageDialog(null,"Please make sure to select theme","Error", JOptionPane.INFORMATION_MESSAGE);
		    		}
		    		else if(theme0.getSelectedItem()=="N/A") {
					    JOptionPane.showMessageDialog(null,"Please make sure to select theme","Error", JOptionPane.INFORMATION_MESSAGE);}
		    		else if (Integer.parseInt(rows)*Integer.parseInt(columns)%2==1) {
				    JOptionPane.showMessageDialog(null,"You can't select both odd numbers in rows and columns","ODD NUMBER!", JOptionPane.INFORMATION_MESSAGE);
				    	}
		    		 else {
		    			 if(multiplayer.isSelected()) {
								new Concentration2();}
								if(singleplayer.isSelected()) {new Concentration();
								} 
				    		System.out.println("Your Game has Started");}	
		    			 
		    		 

		    	}catch (Exception e) {
		    		JOptionPane.showMessageDialog(null,"Please Make Sure that you selected the rows and columns","Error", JOptionPane.INFORMATION_MESSAGE);
		    	} }
		    	
		    	
		    	
		    
		
		
		
		public static void main(String[] args) {
				new MainMenu();
			
			
			
			
		}

	}

	
	

