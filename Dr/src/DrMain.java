import javax.swing.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class DrMain {
	//making new colours
	public static final Color DARK_GREEN = new Color (0,200,0);
	public static final Color DARK_RED = new Color (200,0,0);
	public static final Color DARK_BLUE = new Color (0,0,200);
	public static final Color DARK_CYAN = new Color (0,200,200);


	//a frame for login
	JFrame loginFrame;
	//a frame for the admin
	JFrame adminFrame;
	// used to keep track of how many frames are open. So I can quit the entire program once the last frame is closed
	static int frameCounter=0;
	//creating an array to hold all the objects
	/**static DrMain objArray[ ]= new DrMain[10];*/
	//creating an arraylist to hold all the objects to make it easier to add and remove objects.
	static ArrayList<DrMain> objArray = new ArrayList<DrMain>();
	
	public static void main ( String args[]) {
		System.out.println("Free memory before array "+Runtime.getRuntime().freeMemory());
		
		
		DrMain.createObjects();
		
		/**objArray.add(new DrMain());
		objArray.get(0).createLoginFrame();
		objArray.get(0).createLoginContents(objArray.get(0).loginFrame,0);
		
		objArray.add(new DrMain());
		objArray.get(1).createLoginFrame();
		objArray.get(1).createLoginContents(objArray.get(1).loginFrame,1);*/
	}
	
	//method for creating an object and put it in objArray
	public static void createObjects() {
		//I am setting this variable before I add an object because if I set if after I will have to do -1 from the size
		int length=objArray.size();
		
		//adding an object to the array
		objArray.add(new DrMain());
		//creating login frame
		objArray.get(length).createLoginFrame(length);
		//creating the contents of that frame
		objArray.get(length).createLoginContents(objArray.get(length).loginFrame,length);
		System.out.println("Free memory after array of length"+length+"is "+Runtime.getRuntime().freeMemory());
		
	}
	
	//method to delete objects and remove then from objArray
	public static void destoryObjects(int objPosition) {
		//checking if the adminframe of that object is active or not
		try {
		if(objArray.get(objPosition).adminFrame.isActive()) {
			//making it invisible
			objArray.get(objPosition).adminFrame.setVisible(false);
			//deleting adminFrame
			objArray.get(objPosition).adminFrame.dispose();
		}
		}
		catch(Exception e) {			
			System.out.println(e);
			System.out.println("admin frame had not been created");
		}
		
		
		//checking if the login frame of that object is active or not
		if(objArray.get(objPosition).loginFrame.isActive()) {
			//making it invisible
			objArray.get(objPosition).loginFrame.setVisible(false);
			//deleting loginFrame
			objArray.get(objPosition).loginFrame.dispose();
		}
		
		objArray.remove(objPosition);
		System.out.println("Free memory before gc "+Runtime.getRuntime().freeMemory());
		System.gc();
		System.out.println("Free memory after gc "+Runtime.getRuntime().freeMemory());
		
	}
	
	//method to create contents of the login frame
	public void createLoginContents(JFrame frame, int objPosition) {
		//creating the admin button
		JButton adminButton = new JButton("Admin");
		adminButton.setBounds(100, 100, 200, 100);
		adminButton.setBackground(Color.GREEN);
		adminButton.setOpaque(true);
	    adminButton.setBorderPainted(false);	
	    
	    //adding a mouse listener to the button so that I can keep the colour changed for the duration of the button being pressed.
		adminButton.addMouseListener(new MouseAdapter (){			
			//changing colour to dark green when the button is selected
		    public void mousePressed(MouseEvent e) {
		    	adminButton.setBackground(DARK_GREEN);
		    }
			
		    //changing the colour back to green when the button is not selected any more
			public void mouseReleased(MouseEvent e ) {
				adminButton.setBackground(Color.GREEN);
				Toolkit.getDefaultToolkit().beep();
				//printing the object that calls this function
				System.out.println(this.hashCode()+" "+this.getClass());
				
				//I am not using frame here because it won't work, that is why I have to use objArray.get
				objArray.get(objPosition).createAdminFrame(objPosition);
				objArray.get(objPosition).createAdminContents(objArray.get(objPosition).adminFrame, objPosition);
				
			}

		});
		//printing the object that called createLoginContents
		System.out.println(this.hashCode());
		//adding adminbutton to the frame of the object
		frame.add(adminButton);
		
		
		
		//creating the employee button
		JButton employeeButton = new JButton ("Employee");
		employeeButton.setBounds(300,100,200,100);
		employeeButton.setBackground(Color.RED);
		employeeButton.setOpaque(true);
		employeeButton.setBorderPainted(false);
		
		//if something isn't commentted go to where I added a mouse listener to the admin button
		employeeButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				employeeButton.setBackground(DARK_RED);
				
			}
			
			public void mouseReleased(MouseEvent e) {
				employeeButton.setBackground(Color.RED);
				Toolkit.getDefaultToolkit().beep();
				
			}
			
		});
		
		//adding employeebutton the frame of the object
		frame.add(employeeButton);
		
		//creating button to create a new login frame
		JButton newLoginFrameButton = new JButton ("New login window");
		newLoginFrameButton.setBounds(500,100,200,100);
		newLoginFrameButton.setBackground(Color.CYAN);
		newLoginFrameButton.setOpaque(true);
		newLoginFrameButton.setBorderPainted(false);
		
		//if something isn't commentted go to where I added a mouse listener to the admin button
		newLoginFrameButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				newLoginFrameButton.setBackground(DARK_CYAN);
				
			}
			
			public void mouseReleased(MouseEvent e) {
				newLoginFrameButton.setBackground(Color.CYAN);
				Toolkit.getDefaultToolkit().beep();
				//calling the function which creates new objects
				DrMain.createObjects();
				
			}
			
		});
		
		//adding employeebutton the frame of the object
		frame.add(newLoginFrameButton);
		
		
		
	}
	
	
	
	//method to create the login frame
	public void createLoginFrame(int objPosition) {
		this.loginFrame= new JFrame("Login Window");
		//adding 1 to frame counter 
		frameCounter++;
		System.out.println(frameCounter);
		//this sets the width to half the width of the screen and the height to half the height of the screen
		this.loginFrame.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2));
		//this sets the location to the centre of the screen
		this.loginFrame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4),(int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4) );
		this.loginFrame.setLayout(null); 		
		this.loginFrame.setVisible(true);
		// adding a window listener
		this.loginFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        // if the last window is closed then the whole program terminates, else framecounter is decreased by 1	
	          if(frameCounter==1) {
	        	  System.out.println(frameCounter);
	        	  System.exit(0);		          
	          }
	          else {
	        	 frameCounter--;
	        	 //calling a function to delete the object
	        	 DrMain.destoryObjects(objPosition);
	        	 System.out.println(frameCounter);
	          }
	        
	        }
	    });
	}
	
	
	
	//method to create admin frame
	public void createAdminFrame(int objPosition) {
		//making the previous frame invisible
		objArray.get(objPosition).loginFrame.setVisible(false);
		//decreasing frame counter by 1 because the login frame is now invisible
		frameCounter--;
		
		this.adminFrame = new JFrame("Admin Frame");
		//adding 1 to frame counter
		frameCounter++;
		System.out.println(frameCounter);
		//setting the size of the frame
		this.adminFrame.setSize(500, 500);
		//setting the location
		this.adminFrame.setLocation(100,100);
		this.adminFrame.setLayout(null); 		
		this.adminFrame.setVisible(true);
		this.adminFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	        // if the last window is closed then the whole program terminates, else framecounter is decreased by 1	
	          if(frameCounter==1) {
	        	  System.out.println(frameCounter);
	        	  System.exit(0);		          
	          }
	          else {
	        	 frameCounter--;
	        	 System.out.println(frameCounter);
	          }
	        
	        }
	    });
	}
	
	
	
	public void createAdminContents(JFrame frame, int objPosition) {
		//creating button to go to the previous frame
		JButton previousFrameButton = new JButton("Go to login window");
		previousFrameButton.setBounds(00,0,200,100);
		previousFrameButton.setBackground(Color.RED);
		previousFrameButton.setOpaque(true);
		previousFrameButton.setBorderPainted(false);
		
		previousFrameButton.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				previousFrameButton.setBackground(DARK_RED);
				
			}
			
			public void mouseReleased(MouseEvent e) {
				previousFrameButton.setBackground(Color.RED);
				Toolkit.getDefaultToolkit().beep();
				objArray.get(objPosition).loginFrame.setVisible(true);
				frame.dispose();

			}
			
		});
		
		frame.add(previousFrameButton);
		
		System.out.println("admin frame contents created");
	}
	

}
