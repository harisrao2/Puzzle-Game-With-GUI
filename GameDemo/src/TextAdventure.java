import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class TextAdventure extends JFrame{

	
	/*************************** INITIALIZING GUI VARIABLES ******************************/
	private JFrame frame;
	private JTextArea map;
	private JButton upB;
	private JButton leftB;
	private JButton rightB;
	private JButton downB;
	private JButton play;
	private JButton clear1;
	private JTextField textEnter;
	private JTextArea textA;
	private JTextArea PlanksOut;
	private JTextPane Inventorytxt;
	private JTextPane Plankstxt;
	private JButton restart; 
	private JScrollBar scrollBar;
	private JScrollPane scrollPane;
	/**************************** INITIALIZING MAP OBJECTS ********************************/
	
	Adventurer p1 = new Adventurer("bob",1,11);
	
	pin pin1 = new pin(false,2,10);
	
	Inventory inv = new Inventory(false,0);
	
	plank plank1 = new plank(1,1,24);
	plank plank2 = new plank (1,2,18);
	plank plank3= new plank (1,5,9);
	plank plank4= new plank (1,13,21);
	plank plank5= new plank (1,12,3);
	
	Door door1 = new Door(false,false,4,1);
	Door door2 = new Door(false,false,1,12);
	Door door3 = new Door(false,false,1,17);
	Door door4 = new Door(false,false,8,20);
	Door door5 = new Door(false,false,13,12);
	Door door6 = new Door(false,false,12,6);
	Door door7 = new Door(false,false,8,10);
	
	Door exit1= new Door(true,false,0,2);
	Door exit2= new Door(true,false,0,3);
	Door exit3= new Door(true,false,12,2);
	
	Guard guard1 = new Guard(true,1,2);
	Guard guard2 = new Guard(true,1,3);
	Guard guard3 = new Guard(true,1,6);
	Guard guard4 = new Guard(true,2,15);
	Guard guard5 = new Guard(true,4,13);
	Guard guard6 = new Guard(true,7,9);
	Guard guard7 = new Guard(true,7,11);
	Guard guard8 = new Guard(true,8,15);
	Guard guard9 = new Guard(true,11,1);
	Guard guard10 = new Guard(true,12,1);
	Guard guard11= new Guard(true,13,1);
	Guard guard12= new Guard(true,13,15);
	
	/**************************** INITIALIZING VARIABLES AND GRID **************************/
	int c = 26;
	int r = 15;
	int posx = p1.getX();
	int posy = p1.getY();
	int prevx =1;
	int prevy=11;
	block[][] grid = new block [r][c];
	private JTextArea PinOut;
	private JTextPane PinText;
	private JButton help;
	private JButton clear;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextAdventure window = new TextAdventure();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextAdventure() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
			
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		map = new JTextArea();
		map.setBounds(28, 59, 311, 241);
		frame.getContentPane().add(map);
		
		upB = new JButton("^");
		upB.setBounds(150, 330, 41, 29);
		frame.getContentPane().add(upB);
		upB.addActionListener(new Event());
		
		leftB = new JButton("<");
		leftB.setBounds(115, 348, 41, 29);
		frame.getContentPane().add(leftB);
		leftB.addActionListener(new Event());
		
		rightB = new JButton(">");
		rightB.setBounds(184, 348, 41, 29);
		frame.getContentPane().add(rightB);
		rightB.addActionListener(new Event());
		
		downB = new JButton("v");
		downB.setBounds(150, 366, 41, 29);
		frame.getContentPane().add(downB);
		downB.addActionListener(new Event());
		 
		play = new JButton("Play");
		play.setBounds(125, 415, 89, 29);
		frame.getContentPane().add(play);
		play.addActionListener(new Event());
		
		textA = new JTextArea();
		textA.setLineWrap(true);
		textA.setBounds(385, 59, 335, 390);
		textA.setWrapStyleWord(true);getContentPane();
		
		//scrollPane = new JScrollPane(textA);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.setPreferredSize(new Dimension(380, 100));
		
		frame.getContentPane().add(textA);
		//textA.setText("Hit the play button to Begin and see the guide");
		
		
		
		PlanksOut = new JTextArea();
		PlanksOut.setBounds(487, 531, 101, 16);
		frame.getContentPane().add(PlanksOut);
		
		Inventorytxt = new JTextPane();
		Inventorytxt.setText("Inventory");
		Inventorytxt.setBounds(415, 503, 69, 16);
		frame.getContentPane().add(Inventorytxt);
		
		Plankstxt = new JTextPane();
		Plankstxt.setText("Planks : ");
		Plankstxt.setBounds(415, 531, 53, 16);
		frame.getContentPane().add(Plankstxt);
		
		PinOut = new JTextArea();
		PinOut.setBounds(487, 559, 33, 16);
		frame.getContentPane().add(PinOut);
		
		PinText = new JTextPane();
		PinText.setText("Pin : ");
		PinText.setBounds(415, 559, 53, 16);
		frame.getContentPane().add(PinText);
		
		restart = new JButton("Restart");
		restart.setBounds(125, 461, 89, 29);
		frame.getContentPane().add(restart);
		
		help = new JButton("Help");
		help.setBounds(125, 507, 89, 29);
		frame.getContentPane().add(help);
		
		clear1 = new JButton("Clear Text");
		clear1.setBounds(498, 461, 117, 29);
		frame.getContentPane().add(clear1);
		
		
		clear1.addActionListener(new Event());
		restart.addActionListener(new Event());
		help.addActionListener(new Event());
		
			
	}
	
	private class Event implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == play){
			map.setText("");
			textA.setText("");
			AdventureModel(p1.getX(),p1.getY());
			printMap(grid,15,26);
		}
		if(e.getSource()==clear1) {
			textA.setText("");
			
		}
		if(e.getSource()==help) {
			textA.setText("Hit the 'Play' button to see the map.\nUse the 4 arrow keys under the map to move your character\n\n'P' resembles the prisoner(you).\n'-' resembles a path that you can walk on.\n'x' resembles the guards.\n'o' resembles the planks that can be used to take down the guards.\n'K' is the pin you will use to unlock the doors.\n'#' resembles the walls.\n'/' resembles the doors\n'e' are the exit doors you want to look for, be careful for any traps.\n\nTip: Use your planks wisely, GoodLuck. You may use the Restart button to restart the game\n");
		}
		if(e.getSource()==restart) {
			map.setText("");
			textA.setText("");
			PinOut.setText("");
			PlanksOut.setText("");
			
			p1.setX(1);
			p1.setY(11);
			
			pin1.setHave(false);
			pin1.setX(2);
			pin1.setY(10);
			
			inv.setPin(false);
			inv.setPlanks(0);
			
			plank1.setNum(1);
			plank1.setX(1);
			plank1.setY(24);
			plank2.setNum(1);
			plank2.setX(2);
			plank2.setY(18);
			plank3.setNum(1);
			plank3.setX(5);
			plank3.setY(9);
			plank4.setNum(1);
			plank4.setX(13);
			plank4.setY(21);
			plank5.setNum(1);
			plank5.setX(12);
			plank5.setY(3);
			
			door1.setExit(false);
			door1.setOpen(false);
			door1.setX(4);
			door1.setY(1);
			door2.setExit(false);
			door2.setOpen(false);
			door2.setX(1);
			door2.setY(12);
			door3.setExit(false);
			door3.setOpen(false);
			door3.setX(1);
			door3.setY(17);
			door4.setExit(false);
			door4.setOpen(false);
			door4.setX(8);
			door4.setY(20);
			door5.setExit(false);
			door5.setOpen(false);
			door5.setX(13);
			door5.setY(12);
			door6.setExit(false);
			door6.setOpen(false);
			door6.setX(12);
			door6.setY(6);
			door7.setExit(false);
			door7.setOpen(false);
			door7.setX(8);
			door7.setY(10);
			
			exit1.setExit(true);
			exit1.setOpen(false);
			exit1.setX(0);
			exit1.setY(2);
			exit2.setExit(true);
			exit2.setOpen(false);
			exit2.setX(0);
			exit2.setY(3);
			exit3.setExit(true);
			exit3.setOpen(false);
			exit3.setX(12);
			exit3.setY(2);
			
			guard1.setAlive(true);
			guard1.setX(1);
			guard1.setY(2);
			guard2.setAlive(true);
			guard2.setX(1);
			guard2.setY(3);
			guard3.setAlive(true);
			guard3.setX(1);
			guard3.setY(6);
			guard4.setAlive(true);
			guard4.setX(2);
			guard4.setY(15);
			guard5.setAlive(true);
			guard5.setX(4);
			guard5.setY(13);
			guard6.setAlive(true);
			guard6.setX(7);
			guard6.setY(9);
			guard7.setAlive(true);
			guard7.setX(7);
			guard7.setY(11);
			guard8.setAlive(true);
			guard8.setX(8);
			guard8.setY(15);
			guard9.setAlive(true);
			guard9.setX(11);
			guard9.setY(1);
			guard10.setAlive(true);
			guard10.setX(12);
			guard10.setY(1);
			guard11.setAlive(true);
			guard11.setX(13);
			guard11.setY(1);
			guard12.setAlive(true);
			guard12.setX(13);
			guard12.setY(15);
			
			AdventureModel(p1.getX(),p1.getY());
			printMap(grid,15,26);
		}
		
		if(e.getSource()==upB) { /**********************    MOVE UP    ***************************/
			map.setText("");
			prevx=p1.getX();
			AdventureModel(p1.getX(),p1.getY());
			
			if(grid[p1.getX()-1][p1.getY()] instanceof Wall) {
				textA.append("You are running into a Wall\n");
			}else if(grid[p1.getX()-1][p1.getY()] instanceof pin) {
				p1.setX(p1.getX()-1);
				pin1.setHave(true);
				inv.setPin(true);
				textA.append(grid[p1.getX()][p1.getY()].toString());
				PinOut.setText("");
				if(inv.getPin()==true) {
					PinOut.append("I ");
				}
				
			}else if(grid[p1.getX()-1][p1.getY()] instanceof plank) {
				p1.setX(p1.getX()-1);
				if(grid[p1.getX()][p1.getY()] == plank1) {
					plank1.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank2) {
					plank2.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank3) {
					plank3.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank4) {
					plank4.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank5) {
					plank5.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				inv.setPlanks(inv.getPlanks()+1);
				PlanksOut.setText("");
				for(int i=0;i<inv.getPlanks();i++) {
					PlanksOut.append("O");
				}
				
			}						//instance of plank end
			else if(grid[p1.getX()-1][p1.getY()] instanceof Door) {
				if(pin1.getHave()==true) {
					p1.setX(p1.getX()-1);
					if(grid[p1.getX()][p1.getY()] == door1) {
						door1.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door2) {
						door2.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door3) {
						door3.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door4) {
						door4.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door5) {
						door5.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door6) {
						door6.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door7) {
						door1.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == exit1) {
						exit1.setOpen(true);
						textA.setText("");
						textA.append("You successfully broke out of the prison, try not to \nend up back in here.\n");
						rightB.setEnabled(false);
						leftB.setEnabled(false);
						upB.setEnabled(false);
						downB.setEnabled(false);
		
					}
					if(grid[p1.getX()][p1.getY()] == exit2) {
						exit2.setOpen(true);
						textA.setText("");
						textA.append("You successfully broke out of the prison, try not to \nend up back in here.\n");
						rightB.setEnabled(false);
						leftB.setEnabled(false);
						upB.setEnabled(false);
						downB.setEnabled(false);
					}
					if(grid[p1.getX()][p1.getY()] == exit3) {
						exit3.setOpen(true);
						textA.append("You successfully unlocked the an exit door\n");
					}
				}else{
					textA.append("There is a door at South, You must find a pin to unlock the door first.\n");
				}
			}								//instance of DOOR end
			else if(grid[p1.getX()-1][p1.getY()] instanceof Guard) {   //Instance of Guard Start
				if(inv.getPlanks()<1) { //0 planks
					textA.append("There is a guard there, You must find atleast 1 plank to take down the Guard");
				}else {	//1 or more planks
					p1.setX(p1.getX()-1);
					if(grid[p1.getX()][p1.getY()] == guard1) {
						guard1.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard2) {
						guard2.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard3) {
						guard3.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard4) {
						guard4.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard5) {
						guard5.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard6) {
						guard6.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard7) {
						guard7.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard8) {
						guard8.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard9) {
						guard9.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard10) {
						guard10.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard11) {
						guard11.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard12) {
						guard12.setAlive(false);
					}
					inv.setPlanks(inv.getPlanks()-1);
					PlanksOut.setText("");
					for(int i=0;i<inv.getPlanks();i++) {
						PlanksOut.append("O");
					}
					//p1.setX(p1.getX()-1);
					textA.append("Guard Down, you may proceed carefully\n");
					
				}
			}							//instance of Guard END
			else {
				p1.setX(p1.getX()-1);
			}
			AdventureModel(p1.getX(),p1.getY());
			printMap(grid,15,26);
		}
		
		
		if(e.getSource()==downB) { /**********************    MOVE DOWN   ***************************/
			map.setText("");
			prevx = p1.getX();
			AdventureModel(p1.getX(),p1.getY());
			if(grid[p1.getX()+1][p1.getY()] instanceof Wall) {
				textA.append("You are running into a Wall\n");
			}else if(grid[p1.getX()+1][p1.getY()] instanceof pin) {
				p1.setX(p1.getX()+1);
				pin1.setHave(true);
				inv.setPin(true);
				textA.append(grid[p1.getX()][p1.getY()].toString());
				PinOut.setText("");
				if(inv.getPin()==true) {
					PinOut.append("I ");
				}
				
			}else if(grid[p1.getX()+1][p1.getY()] instanceof plank) {
				p1.setX(p1.getX()+1);
				if(grid[p1.getX()][p1.getY()] == plank1) {
					plank1.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank2) {
					plank2.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank3) {
					plank3.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank4) {
					plank4.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank5) {
					plank5.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				inv.setPlanks(inv.getPlanks()+1);
				PlanksOut.setText("");
				for(int i=0;i<inv.getPlanks();i++) {
					PlanksOut.append("O ");
				}
				
			}								//instance of plank END
			else if(grid[p1.getX()+1][p1.getY()] instanceof Door) {
				if(inv.getPin()==true) {
					p1.setX(p1.getX()+1);
					if(grid[p1.getX()][p1.getY()] == door1) {
						door1.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door2) {
						door2.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door3) {
						door3.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door4) {
						door4.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door5) {
						door5.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door6) {
						door6.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door7) {
						door1.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == exit1) {
						exit1.setOpen(true);
						textA.append("You successfully unlocked the an exit door\n");
					}
					if(grid[p1.getX()][p1.getY()] == exit2) {
						exit2.setOpen(true);
						textA.append("You successfully unlocked the an exit door\n");
					}
					if(grid[p1.getX()][p1.getY()] == exit3) {
						exit3.setOpen(true);
						textA.append("You successfully unlocked the an exit door\n");
					}
				}else{
					textA.append("There is a door at North, You must find a pin to unlock the door first.\n");
				}
			}								//instance of Door END
			else if(grid[p1.getX()+1][p1.getY()] instanceof Guard) {   //Instance of Guard Start
				if(inv.getPlanks()<1) { //0 planks
					textA.append("There is a guard there, You must find atleast 1 plank to take down the Guard\n");
				}else {	//1 or more planks
					p1.setX(p1.getX()+1);
					if(grid[p1.getX()][p1.getY()] == guard1) {
						guard1.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard2) {
						guard2.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard3) {
						guard3.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard4) {
						guard4.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard5) {
						guard5.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard6) {
						guard6.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard7) {
						guard7.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard8) {
						guard8.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard9) {
						guard9.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard10) {
						guard10.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard11) {
						guard11.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard12) {
						guard12.setAlive(false);
					}
					inv.setPlanks(inv.getPlanks()-1);
					PlanksOut.setText("");
					for(int i=0;i<inv.getPlanks();i++) {
						PlanksOut.append("O");
					}
					//p1.setX(p1.getX()+1);
					textA.append("Guard Down, you may proceed carefully\n");
					
				}
			}							//instance of Guard END
			else {
				p1.setX(p1.getX()+1);
			}
			AdventureModel(p1.getX(),p1.getY());
			printMap(grid,15,26);
		}
		if(e.getSource()==leftB) { /**********************    MOVE LEFT    ***************************/
			map.setText("");
			prevx = p1.getX();
			AdventureModel(p1.getX(),p1.getY());
			if(grid[p1.getX()][p1.getY()-1] instanceof Wall) {
				textA.append("You are running into a Wall\n");
			}else if(grid[p1.getX()][p1.getY()-1] instanceof pin) {
				p1.setY(p1.getY()-1);
				pin1.setHave(true);
				inv.setPin(true);
				textA.append(grid[p1.getX()][p1.getY()].toString());
				PinOut.setText("");
				if(inv.getPin()==true) {
					PinOut.append("I ");
				}
				
			}else if(grid[p1.getX()][p1.getY()-1] instanceof plank) {
				p1.setY(p1.getY()-1);
				if(grid[p1.getX()][p1.getY()] == plank1) {
					plank1.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank2) {
					plank2.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank3) {
					plank3.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank4) {
					plank4.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				if(grid[p1.getX()][p1.getY()] == plank5) {
					plank5.setNum(0);
					textA.append(grid[p1.getX()][p1.getY()].toString());
				}
				inv.setPlanks(inv.getPlanks()+1);
				PlanksOut.setText("");
				for(int i=0;i<inv.getPlanks();i++) {
					PlanksOut.append("O ");
				}
				
			}								//instance of plank END
			else if(grid[p1.getX()][p1.getY()-1] instanceof Door) {
				if(inv.getPin()==true) {
					p1.setY(p1.getY()-1);
					if(grid[p1.getX()][p1.getY()] == door1) {
						door1.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door2) {
						door2.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door3) {
						door3.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door4) {
						door4.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door5) {
						door5.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door6) {
						door6.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == door7) {
						door1.setOpen(true);
						textA.append("You successfully unlocked the door\n");
					}
					if(grid[p1.getX()][p1.getY()] == exit1) {
						exit1.setOpen(true);
						textA.append("You successfully unlocked the an exit door\n");
					}
					if(grid[p1.getX()][p1.getY()] == exit2) {
						exit2.setOpen(true);
						textA.append("You successfully unlocked the an exit door\n");
					}
					if(grid[p1.getX()][p1.getY()] == exit3) {
						exit3.setOpen(true);
						textA.append("You successfully unlocked the an exit door\n");
					}
				}else{
					textA.append("There is a door to your left, You must find a pin to unlock the door first.\n");
				}
			}									// instance of DOOR END
			else if(grid[p1.getX()][p1.getY()-1] instanceof Guard) {   //Instance of Guard Start
				if(inv.getPlanks()<1) { //0 planks
					textA.append("There is a guard there, You must find atleast 1 plank to take down the Guard\n");
				}else {	//1 or more planks
					p1.setY(p1.getY()-1);
					if(grid[p1.getX()][p1.getY()] == guard1) {
						guard1.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard2) {
						guard2.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard3) {
						guard3.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard4) {
						guard4.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard5) {
						guard5.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard6) {
						guard6.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard7) {
						guard7.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard8) {
						guard8.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard9) {
						guard9.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard10) {
						guard10.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard11) {
						guard11.setAlive(false);
					}
					if(grid[p1.getX()][p1.getY()] == guard12) {
						guard12.setAlive(false);
					}
					inv.setPlanks(inv.getPlanks()-1);
					PlanksOut.setText("");
					for(int i=0;i<inv.getPlanks();i++) {
						PlanksOut.append("O");
					}
					//p1.setY(p1.getY()-1);
					textA.append("Guard Down, you may proceed carefully\n");
					
				}
			}							//instance of Guard END
			else {
				p1.setY(p1.getY()-1);
			}
			AdventureModel(p1.getX(),p1.getY());
			printMap(grid,15,26);
		}
		if(e.getSource()==rightB) { /**********************    MOVE RIGHT    ***************************/
			map.setText("");
			prevx = p1.getX();
			AdventureModel(p1.getX(),p1.getY());
				if(grid[p1.getX()][p1.getY()+1] instanceof Wall) {
					textA.append("You are running into a Wall\n");
				}else if(grid[p1.getX()][p1.getY()+1] instanceof pin) {
					p1.setY(p1.getY()+1);
					pin1.setHave(true);
					inv.setPin(true);
					textA.append(grid[p1.getX()][p1.getY()].toString());
					PinOut.setText("");
					if(inv.getPin()==true) {
						PinOut.append("I ");
					}
				}else if(grid[p1.getX()][p1.getY()+1] instanceof plank) {
					p1.setY(p1.getY()+1);
					if(grid[p1.getX()][p1.getY()] == plank1) {
						plank1.setNum(0);
						textA.append(grid[p1.getX()][p1.getY()].toString());
					}
					if(grid[p1.getX()][p1.getY()] == plank2) {
						plank2.setNum(0);
						textA.append(grid[p1.getX()][p1.getY()].toString());
					}
					if(grid[p1.getX()][p1.getY()] == plank3) {
						plank3.setNum(0);
						textA.append(grid[p1.getX()][p1.getY()].toString());
					}
					if(grid[p1.getX()][p1.getY()] == plank4) {
						plank4.setNum(0);
						textA.append(grid[p1.getX()][p1.getY()].toString());
					}
					if(grid[p1.getX()][p1.getY()] == plank5) {
						plank5.setNum(0);
						textA.append(grid[p1.getX()][p1.getY()].toString());
					}
					inv.setPlanks(inv.getPlanks()+1);
					PlanksOut.setText("");
					for(int i=0;i<inv.getPlanks();i++) {
						PlanksOut.append("O ");
					}
					
				}                   //instance of plank END
				else if(grid[p1.getX()][p1.getY()+1] instanceof Door) {
					if(inv.getPin()==true) {
						p1.setY(p1.getY()+1);
							if(grid[p1.getX()][p1.getY()] == door1) {
								door1.setOpen(true);
								textA.append("You successfully unlocked the door\n");
							}
							if(grid[p1.getX()][p1.getY()] == door2) {
								door2.setOpen(true);
								textA.append("You successfully unlocked the door\n");
							}
							if(grid[p1.getX()][p1.getY()] == door3) {
								door3.setOpen(true);
								textA.append("You successfully unlocked the door\n");
							}
							if(grid[p1.getX()][p1.getY()] == door4) {
								door4.setOpen(true);
								textA.append("You successfully unlocked the door\n");
							}
							if(grid[p1.getX()][p1.getY()] == door5) {
								door5.setOpen(true);
								textA.append("You successfully unlocked the door\n");
							}
							if(grid[p1.getX()][p1.getY()] == door6) {
								door6.setOpen(true);
								textA.append("You successfully unlocked the door\n");
							}
							if(grid[p1.getX()][p1.getY()] == door7) {
								door1.setOpen(true);
								textA.append("You successfully unlocked the door\n");
							}
							if(grid[p1.getX()][p1.getY()] == exit1) {
								exit1.setOpen(true);
								textA.append("You successfully unlocked the an exit door\n");
							}
							if(grid[p1.getX()][p1.getY()] == exit2) {
								exit2.setOpen(true);
								textA.append("You successfully unlocked the an exit door\n");
							}
							if(grid[p1.getX()][p1.getY()] == exit3) {
								exit3.setOpen(true);
								textA.append("You successfully unlocked the an exit door\n");
							}
						
					}
					else{
						textA.append("There is a door to your right, You must find a pin to unlock the door first.\n");
					}
				}                               // instance of Door END
				else if(grid[p1.getX()][p1.getY()+1] instanceof Guard) {   //Instance of Guard Start
					if(inv.getPlanks()<1) { //0 planks
						textA.append("There is a guard there, You must find atleast 1 plank to take down the Guard\n");
					}else {	//1 or more planks
						p1.setY(p1.getY()+1);
						if(grid[p1.getX()][p1.getY()] == guard1) {
							guard1.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard2) {
							guard2.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard3) {
							guard3.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard4) {
							guard4.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard5) {
							guard5.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard6) {
							guard6.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard7) {
							guard7.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard8) {
							guard8.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard9) {
							guard9.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard10) {
							guard10.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard11) {
							guard11.setAlive(false);
						}
						if(grid[p1.getX()][p1.getY()] == guard12) {
							guard12.setAlive(false);
						}
						
						inv.setPlanks(inv.getPlanks()-1); 
						PlanksOut.setText("");
						for(int i=0;i<inv.getPlanks();i++) {
							PlanksOut.append("O");
						}
						//p1.setY(p1.getY()+1);
						textA.append("Guard Down, you may proceed carefully\n");
						
					}
				}							//instance of Guard END
				else {
					p1.setY(p1.getY()+1);
				}
				
			//textA.append(nearRight(p1.getX(),p1.getY()));
			//textA.append(nearLeft(p1.getX(),p1.getY()));
			//textA.append(nearUp(p1.getX(),p1.getY()));
			//textA.append(nearDown(p1.getX(),p1.getY()));
			
			AdventureModel(p1.getX(),p1.getY());
			printMap(grid,15,26);
		}
		
	}
	}
	private String nearRight (int x,int y) {
		return grid[x][y+1].toString();	
	}
	private String nearLeft (int x,int y) {
		return grid[x][y-1].toString();	
	}
	private String nearUp (int x,int y) {
		return grid[x-1][y].toString();	
	}
	private String nearDown (int x,int y) {
		return grid[x+1][y].toString();	
	}
	private void AdventureModel(int x,int y) {  /*************************MAP GENERATION***********************/
	//Adventure Model Class
		
		for(int i =0;i<r;i++) {
			for(int j =0;j<c;j++) {
				grid[i][j] = new path(i,j);
			}
		}
		
		
		for (int i=0;i<c;i++) {
			grid[0][i] = new Wall(true,0,i);     // Borders Top Bottom
			grid[r-1][i]= new Wall(true,r-1,i);
		}
		for(int i=0;i<r;i++) {
			grid[i][0]= new Wall(true,i,0);    // Borders Left Right
			grid[i][c-1]= new Wall(true,i,c-1);
		}
		for(int i=1;i<6;i++) {
			grid[4][i]= new Wall(true,4,i);   //Security Room Bottom
		}
		for(int i=1;i<5;i++) {
			grid[i][5]= new Wall(true,i,5);;	//Security Room Right
		}
		for(int i=6;i<9;i++) {
			for(int j=1;j<5;j++) {      //mid Left block
			grid[i][j]= new Wall(true,i,j);;
			}
		}
		for(int i=1;i<7;i++) {
			grid[10][i]= new Wall(true,10,i);;   //Trap room Top
		}
		for(int i=11;i<14;i++) {
			grid[i][2]= new Wall(true,i,2);;     //Trap room verticle walls
			grid[i][6]= new Wall(true,i,6);;
		}
		for(int i=3;i<12;i++){
			grid[i][8]= new Wall(true,i,8);;     //Mid Room with 2 guards
		}
		for(int i=1;i<14;i++){
			grid[i][12]= new Wall(true,i,12);; 
			grid[i][20]= new Wall(true,i,20);;		//2 Full verticle walls room verticle walls
		}
		
		for(int i=9;i<12;i++){
			grid[3][i]= new Wall(true,3,i);; 
			grid[11][i]= new Wall(true,11,i);;		//mid room with 2 guards
		}
		
		for(int i=5;i<13;i++) {
			for(int j=12;j<18;j++) { 
				if(i==8) {
				}
				else {
					grid[i][j]= new Wall(true,i,j);;
				}
			}
		}
		for(int i=13;i<25;i++) {
		if(i==15||i==21) {
		
			}else {
			grid[3][i]= new Wall(true,3,i);;
			}
		}
		
		//grid[1][9]= new Wall(true,1,9);;
		//grid[2][9]= new Wall(true,2,9);;
		grid[1][9]= new Wall(true,1,9);;
		grid[2][9]= new Wall(true,2,9);;
		grid[2][13]= new Wall(true,2,13);;
		grid[2][14]= new Wall(true,2,14);;
		grid[2][16]= new Wall(true,2,16);;
		grid[2][17]= new Wall(true,2,17);;
		grid[11][21]= new Wall(true,11,21);;
		grid[11][22]= new Wall(true,11,22);;
		grid[11][23]= new Wall(true,11,23);;
		grid[1][17]= new Wall(true,1,17);;
		
		
		//DOORS
		if(door1.getOpen()==false) {
			grid[4][1] = door1;
		}else {
			grid[4][1] = new path(4,1);
		}
		if(door2.getOpen()==false) {
			grid[1][12]= door2;
		}else {
			grid[1][12]= new path (1,12);
		}
		if(door3.getOpen()==false) {
			grid[1][17]= door3;
		}else {
			grid[1][17]= new path (1,17);
		}
		if(door4.getOpen()==false) {
			grid[8][20]= door4;
		}else {
			grid[8][20]= new path (8,20);
		}
		if(door5.getOpen()==false) {
			grid[13][12]= door5;
		}else {
			grid[13][12]= new path (13,12);
		}
		if(door6.getOpen()==false) {
			grid[12][6]= door6;
		}else {
			grid[12][6]= new path (12,6);
		}
		if(door7.getOpen()==false) {
			grid[10][8]= door7;
		}else {
			grid[10][8]= new path (10,8);
		}
		
		//EXIT DOORS
		if(exit1.getOpen()==false) {
			grid[0][2]= exit1;
		}else {
			grid[0][2]= new path (0,2);
		}
		if(exit2.getOpen()==false) {
			grid[0][3]= exit2;
		}else {
			grid[0][3]= new path (0,3);
		}
		if(exit3.getOpen()==false) {
			grid[12][2]= exit3;
		}else {
			grid[12][2]= new path (12,2);
		}
		

		
		
		
		//GUARDS
		if(guard1.getAlive()==false) {
			grid[1][2] = new path(1,2);
		}else {
			grid[1][2] = guard1;
		}
		if(guard2.getAlive()==false) {	
			grid[1][3]= new path(1,3);
		}else {
			grid[1][3]= guard2;
		}
		if(guard3.getAlive()==false) {
			grid[1][6]= new path(1,6);
		}else {
			grid[1][6]= guard3;
		}
		if(guard4.getAlive()==false) {
			grid[2][15]= new path(2,15);
		}else {
			grid[2][15]= guard4;
		}
		if(guard5.getAlive()==false) {
			grid[4][13]= new path(4,13);
		}else {
			grid[4][13]= guard5;
		}
		if(guard6.getAlive()==false) {
			grid[7][9]= new path(7,9);
		}else {
			grid[7][9]= guard6;
		}
		if(guard7.getAlive()==false) {
			grid[7][11]= new path(7,11);
		}else {
			grid[7][11]= guard7;
		}
		if(guard8.getAlive()==false) {
			grid[8][15]= new path(8,15);
		}else {
			grid[8][15]= guard8;
		}
		if(guard9.getAlive()==false) {
			grid[11][1]= new path(11,1);
		}else {
			grid[11][1]= guard9;
		}
		if(guard10.getAlive()==false) {
			grid[12][1]= new path(12,1);
		}else {
			grid[12][1]= guard10;
		}
		if(guard11.getAlive()==false) {
			grid[13][1]= new path(13,1);
		}else {
			grid[13][1]= guard11;
		}
		if(guard12.getAlive()==false) {
			grid[13][15]= new path (13,15);
		}else {
			grid[13][15]= guard12;
		}
		
		
		
		//PLANKS
		if(plank1.getNum()==0) {
			grid[1][24] = new path(1,24);
		}else {
			grid[1][24] = plank1;
		}
		if(plank2.getNum()==0) {
			grid[2][18]= new path(2,18);
		}else {
			grid[2][18]= plank2;
		}
		if(plank3.getNum()==0) {
			grid[5][9]= new path(5,9);
		}else {
			grid[5][9]= plank3;
		}
		if(plank4.getNum()==0) {
			grid[13][21]= new path(13,21);
		}else {
			grid[13][21]= plank4;
		}
		if(plank5.getNum()==0) {
			grid[12][3]= new path(12,3);
		}else {
			grid[12][3]= plank5;
		}
		
		
		
		//PIN
		if(pin1.getHave()==true) {
			//grid[2][10] = new pin(false,2,10);
		}else {
			grid[2][10] = new pin(true,2,10);
		}
		
		
		
		
		if((grid[x][y] instanceof Wall)) {
		//grid[x][y]= new Player("bob",x,y); //Player
			grid[x][y]= new Adventurer("bob",prevx,prevy);
		}else {
			grid[x][y]= new Adventurer("bob",x,y); //Player
		//posx=prevx;
		//posy=prevy;
		}
	}
		
//Making MAP end
	
	//Printing MAP
		private void printMap(block [][] grid, int r, int c) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				
					if(grid[i][j]instanceof Guard ) {
						
						map.append("X ");
						continue;
						
					}
					if((grid[i][j] instanceof plank)) {
						map.append("o ");
						continue;
					}
						
					if((grid[i][j]) instanceof Door) {
						if(((Door) grid[i][j]).getExit()==false) {
						map.append("// ");  //For MAC change to "/ "
						continue;
						}else{
						map.append("e ");
						continue;
					}
					}
					if(grid[i][j] instanceof Wall) {
					
						map.append("# ");
						continue;
					}
					if(grid[i][j] instanceof pin) {
						
						map.append("K ");
						continue;
					}
					if(grid[i][j] instanceof path) {
					map.append("-  ");    //For MAC change to "- "
					}
					if(grid[i][j] instanceof Adventurer) {
						map.append("P ");
						continue;
					}
				
				
			
			
		}
			map.append("\n");	
	}
		
		
	
		}
}
