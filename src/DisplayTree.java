
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

	public class DisplayTree extends JFrame {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private static MyTree t; // t is Binary tree we are displaying
	    private JScrollPane scrollpane;
	    private DisplayPanel panel;
	    
	   
	    /*################### MAIN #####################*/
	   
	    public static void main(String[] args) {
	        t =  new MyTree();
	        
	        DisplayTree dt = new DisplayTree(t);//get a display panel
	        dt.setTitle("AVL Tree Visualisation");
	        
	
	        // make the frame half the height and width
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        int height = screenSize.height;
	        int width = screenSize.width;
	        
	        dt.setSize(width/2, height/2);
	
	        dt.setLocationRelativeTo(null);
	
	        dt.setVisible(true); //show the window
	        
	    }

	    
	    /****************** Display Tree constructor *******************/
	   
	    public DisplayTree(MyTree t) {
	        panel = new DisplayPanel(t);
	        scrollpane = new JScrollPane(panel);
	        
	        //window border
	        scrollpane.setBorder(BorderFactory.createLineBorder(Color.black));
	        
	        getContentPane().add(scrollpane, BorderLayout.CENTER);
	        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.pack();  // cleans up the window panel
	        
	        
	    }
    
}
