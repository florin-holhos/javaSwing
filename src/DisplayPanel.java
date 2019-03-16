import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class DisplayPanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MyTree t;
    
    private int xs;
    private int ys;
    
    
    private String placeholder;
    
    private static boolean keyFound = false;
    private static int sentinelKey;
    
    private JButton insertBtn;
    private JTextField insertField;
	
	private JButton removeBtn;
	private JTextField removeField;
	
	private JButton searchBtn;
	private JTextField searchField;
	
	private JButton makeEmptyBtn;
	
	/********************************** DISPLAY PANEL ***************************************/
    
	public DisplayPanel(MyTree t) {
        this.t = t; // allows display routines to access the tree
        
        setBackground(new Color(130, 255, 221)); // panel background
        setForeground(Color.black); // panel foreground
        
        
		//align to the right
		setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		Color grey = new Color(220, 220, 220);
		
		JPanel operations = new JPanel();
		operations.setLayout(new FlowLayout(FlowLayout.CENTER));
		operations.setPreferredSize(new Dimension(220, 175));
		operations.setBackground(grey);
		
		/****** operations border ******/
	    operations.setBorder(new TitledBorder(new LineBorder(Color.black, 2),
	        "Operations"));
	    
	    /************ Action Listener for buttons**************/
	    ActionListener action = new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				/*********** insertButton && insertField *************/
				if(e.getSource() == insertBtn) {
					String s = insertField.getText();
					if(!s.isEmpty() && isNum(s)) {
						
						add(Integer.parseInt(s));
						insertField.setText("");
						
						
					}else insertField.setText("");
				}
				else if (e.getSource() == insertField){
					String s = insertField.getText();
					if(!s.isEmpty() && isNum(s)) {
						
						insertBtn.doClick();
						
					}else insertField.setText("");
				}
				
				/*********** removeButton && removeField *************/
				else if(e.getSource() == removeBtn) {
					String s = removeField.getText();
					if(!s.isEmpty() && isNum(s)) {
						
						remove(Integer.parseInt(s));
						removeField.setText("");
						
					}else removeField.setText("");
				}
				else if(e.getSource() == removeField) {
					String s = removeField.getText();
					if(!s.isEmpty() && isNum(s)) {
						
						removeBtn.doClick();
						
					}else removeField.setText("");
				}
				
				/*********** searchButton && searchField *************/
				else if (e.getSource() == searchBtn){
					String s = searchField.getText();
					if(!s.isEmpty() && isNum(s)) {
						search(Integer.parseInt(s));
						searchField.setText("");
					}else searchField.setText("");
					
				}
				else if(e.getSource() == searchField) {
					String s = searchField.getText();
					if(!s.isEmpty() && isNum(s)) {
						
						searchBtn.doClick();
						
					}else searchField.setText("");
				}
				else if(e.getSource() == makeEmptyBtn) {
					
					t.setRoot(null);
					t.setTotalnodes(0);
					t.setInputString("");
					
					keyFound = false;
					
					revalidate();
					repaint();
				}
			}
	    };
	    
	    
	    /********************** Operations panel buttons***********************/
	    
	    /****** add buttons to operations panel ******/
	    
	    JPanel buttons = new JPanel();
	    buttons.setLayout(new GridLayout(3, 2));
	    buttons.setBackground(grey);
	    
	    /************ insert button ***************/
	    
	    
	    JPanel p = new JPanel();
	    p.setBackground(grey);
	    insertBtn = new JButton("insert");
	    insertBtn.addActionListener(action);
	    insertBtn.setPreferredSize(new Dimension(90, 25));
	    p.add(insertBtn);
	    buttons.add(p);
	    
	    
	    /****** insert Field ******/
	    p = new JPanel();
	    p.setBackground(grey);
	    insertField = new JTextField(5);
	    insertField.setBorder(new LineBorder(new Color(130, 255, 221)));
	    insertField.addActionListener(action);
	    insertField.setPreferredSize(new Dimension(50, 25));
	    p.add(insertField);
	    buttons.add(p);
	    
	    
	    
	    /****** remove Button ******/
	    p = new JPanel();
	    p.setBackground(grey);
	    removeBtn = new JButton("remove");
	    removeBtn.addActionListener(action);
	    removeBtn.setPreferredSize(new Dimension(90, 25));
	    p.add(removeBtn);
	    buttons.add(p);
	    
	    
	    /****** remove Field ******/
	    p = new JPanel();
	    p.setBackground(grey);
	    removeField = new JTextField(5);
	    removeField.setBorder(new LineBorder(new Color(130, 255, 221)));
	    removeField.addActionListener(action);
	    removeField.setPreferredSize(new Dimension(50, 25));
	    p.add(removeField);
	    buttons.add(p);
	    
		
	    
	    /****** search Button ******/
	    p = new JPanel();
	    p.setBackground(grey);
	    searchBtn = new JButton("search");
	    searchBtn.addActionListener(action);
	    searchBtn.setPreferredSize(new Dimension(90, 25));
	    p.add(searchBtn);
	    buttons.add(p);
	    
	    
	    /****** search Field ******/
	    p = new JPanel();
	    p.setBackground(grey);
	    searchField = new JTextField(5);
	    searchField.setBorder(new LineBorder(new Color(130, 255, 221)));
	    searchField.addActionListener(action);
	    searchField.setPreferredSize(new Dimension(50, 25));
	    p.add(searchField);
	    buttons.add(p);
	    
	    // add buttons to the operations panel
	    operations.add(buttons, BorderLayout.NORTH);
	    
	    /****** makeEmptyTree Button ******/
	    p = new JPanel();
	    p.setBackground(grey);
	    p.setLayout(new BorderLayout());
	    makeEmptyBtn = new JButton("Make Empty");
	    makeEmptyBtn.addActionListener(action);
	    makeEmptyBtn.setPreferredSize(new Dimension(140, 30));
	    p.add(makeEmptyBtn);
	    operations.add(p, BorderLayout.SOUTH);
	    
	    // add operations panel in the main frame
		add(operations);
		
    }
    
    
    /*################# Paint the JComponents #################*/

	
    public void paintComponent(Graphics g) {
    	
    	
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground()); 
        Font MyFont = new Font("SansSerif", Font.PLAIN, 11);
        g.setFont(MyFont);
        
        
        xs = 10;
        ys = 20;
        
        //antialiasing for drawing
       ((Graphics2D) g).setRenderingHint(
    		   RenderingHints.KEY_ANTIALIASING,
    		   RenderingHints.VALUE_ANTIALIAS_ON);
        
       	/* check for / by zero  (XSCALE, YSCALE) */
      	if(t.getTotalnodes() == 0 || t.getMaxheight() == 0) {
      		return;
      	}else {
      		
      		g.drawString("Nodes: ", xs, ys);
            ys = ys + 15;
            int start = 0;
            
            /****************** copy the original input to a placeholder ********************/
            if(t.getInputString().length() < 190) {
            	this.placeholder = t.getInputString();
            	// print inputString on panel, 150 chars per line
                // if string longer than 23 lines don't print
                if(placeholder.length()< 23 * 150){
                    while((placeholder.length() - start) > 200){
                        g.drawString((placeholder.substring(start, start + 200)), xs, ys);
                        start += 200;
                        ys += 15;     
                    }
                    g.drawString(placeholder.substring(start, placeholder.length()),xs,ys);
                }
             /************* if the inputString.length > 190 then draw the last stored placeholder ********/
            }else {
            	g.drawString(placeholder.concat("..."), xs, ys);
            }
                
            MyFont = new Font("SansSerif",Font.BOLD,20); //bigger font for tree
            g.setFont(MyFont);
            this.drawTree(g, t.getRoot()); // draw the tree
            revalidate(); //update the component panel
      		
      	}
       	
    }

    
    /*##################### Draw the current Tree #########################*/
   
    public void drawTree(Graphics g, Node root) {//actually draws the tree
        int dx, dy, dx2, dy2;
        final int SCREEN_WIDTH = 1300; //screen size for panel
        final int SCREEN_HEIGHT = 720;
        final int XSCALE, YSCALE;
       	
        XSCALE = SCREEN_WIDTH/(t.getTotalnodes()); //scale x by total nodes in tree
        YSCALE = (SCREEN_HEIGHT-(ys))/(t.getMaxheight() + 1); //scale y by tree height
        
        
        if (root != null) { // inorder traversal to draw each node
            
            drawTree(g, root.getLeft()); // do left side of inorder traversal
            dx = root.getXpos() * XSCALE + xs + 5; // get x,y coords., and scale them
            dy = root.getYpos() * YSCALE + ys - 5;
            String s = Integer.toString(root.getKey()); //get the word at this node
            
            //drawing antialiasing
           ((Graphics2D) g).setRenderingHint( 
        		   RenderingHints.KEY_ANTIALIASING, 
        		   RenderingHints.VALUE_ANTIALIAS_ON);
            
           	
           // check for searchBtn and make the node red!!!
           if(keyFound == true && Integer.parseInt(s) == sentinelKey) {
	       	g.setColor(Color.RED);
	       	
	       	if(s.length() == 1) {
	           	g.fillOval(dx-10, dy-ys+10, 35, 35);
	           }else if(s.length() == 2) {
	
	           	g.fillOval(dx-5, dy-ys+7, 40, 40);
	           }else if(s.length() == 3) {
	           	g.fillOval(dx-4, dy-ys+1, 52, 52);
	           }else {
	           	g.fillOval(dx-6, dy-ys-7, s.length()*16, 70);
	           	
	           	sentinelKey = -999;
	           	keyFound = false;
	           }
	       }else {
        	   //draw black node
              	// draws a circle around the node
              	g.setColor(Color.black);
              	
               if(s.length() == 1) {
               	g.fillOval(dx-10, dy-ys+10, 35, 35);
               }else if(s.length() == 2) {

               	g.fillOval(dx-5, dy-ys+7, 40, 40);
               }else if(s.length() == 3) {
               	g.fillOval(dx-4, dy-ys+1, 52, 52);
               }else {
               	g.fillOval(dx-6, dy-ys-7, s.length()*16, 70);
               }
           }
            
            //word data color
            g.setColor(Color.white);
            g.drawString(s, dx, dy); // draws the word
            
            //set the color again to black and draw the node
            g.setColor(Color.black);
            
             
            // this draws the lines from a node to its children, if any
            if(root.getLeft() != null){ //draws the line to left child if it exists
                
            	dx2 = root.getLeft().getXpos() * XSCALE + xs + 5;
                dy2 = root.getLeft().getYpos() * YSCALE + ys - 5;
                
                g.drawLine(dx,dy,dx2,dy2);
            }
            
            if(root.getRight() != null){ //draws the line to right child if it exists
                dx2 = root.getRight().getXpos() * XSCALE + xs + 5;//get right child x,y scaled position
                dy2 = root.getRight().getYpos() * YSCALE + ys - 5;
                
                g.setColor(getForeground());
                g.drawLine(dx,dy,dx2,dy2);
            }
            drawTree(g, root.getRight()); //now do right side of inorder traversal
        }
    }
    
    
    /*##################### Buttons functions ###################*/
    
    // insert button
    
    public void add(int key) {
    	String k = Integer.toString(key);
    	String keyAlias = " " + k + ", ";
    	
    	if(!t.getInputString().contains(keyAlias)) {
    		t.setRoot(t.insert(t.getRoot(), key));  //insert word into tree
    		t.setInputString(t.getInputString().concat(keyAlias)); // add word to input string
    		
    		int i = 0;
    		t.setTotalnodes(i--);
    		
    		
    		//first of all, compute the nodes positions and scale the tree
        	this.t.setMaxheight(t.height(t.getRoot())); //finds tree height for scaling y axis
            this.t.computeNodePositions(); //finds x,y positions of the tree nodes
            
            
            revalidate();
	    	repaint();
    	}
    	else return;
    }

    // remove button
  
    public void remove(int key) {
    	
    	String k = Integer.toString(key);
    	String keyAlias = " " + k + ", ";
    	
    	if(t.getInputString().contains(keyAlias)) {
    		t.setRoot(t.deleteNode(t.getRoot(), key));
    		t.setInputString(t.getInputString().replace(keyAlias, ""));
    		
    		
    		int i = 0;
    		t.setTotalnodes(i--);
    		
    		//first of all, compute the nodes positions and scale the tree
        	this.t.setMaxheight(t.height(t.getRoot())); //finds tree height for scaling y axis
            this.t.computeNodePositions(); //finds x,y positions of the tree nodes
            
            keyFound = false;
            
            revalidate();
	    	repaint();
    	}
    	else return;
	}
    
    // search button
  
    public void search(int key) {
    	String k = Integer.toString(key);	    	
    	String keyAlias = " " + k + ", ";
    	
    	if(t.getInputString().contains(keyAlias)) {
    		
    		sentinelKey = t.search(t.getRoot(), key);
    		keyFound = true;
    		
    		int i = 0;
    		t.setTotalnodes(i--);
    		
    		//first of all, compute the nodes positions and scale the tree
        	this.t.setMaxheight(t.height(t.getRoot())); //finds tree height for scaling y axis
            this.t.computeNodePositions(); //finds x,y positions of the tree nodes
          
            revalidate();
	    	repaint();
    	}
    	else return;
    }
    
    //check if the input is numeric
    
    public boolean isNum(String number) {
        boolean ret = true;
        try {

            Integer.parseInt(number);

        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }
    
}