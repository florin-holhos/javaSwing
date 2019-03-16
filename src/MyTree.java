

public class MyTree {
    private String inputString = new String();
    private Node root;
    private int totalnodes = 0; //keeps track of the inorder number for horiz. scaling
    private int maxheight = 0;//keeps track of the depth of the tree for vert. scaling

    // constructor
    public MyTree() {
        root = null;
    }

    //get the height of a node
    public int height(Node n){
        if(n == null) return 0;
        else return n.getHeight();
    }
    
    //find max
    public int max(int a, int b){
        return (a > b) ? a:b;
    }

    //compute positions of the nodes
    public void computeNodePositions() {
        int depth = 1;
        inorder_traversal(root, depth);
    }

    public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public int getTotalnodes() {
		return totalnodes;
	}

	public void setTotalnodes(int totalnodes) {
		this.totalnodes = totalnodes;
	}

	public int getMaxheight() {
		return maxheight;
	}

	public void setMaxheight(int maxheight) {
		this.maxheight = maxheight;
	}

	//traverses tree and computes x,y position of each node, stores it in the node
    public void inorder_traversal(Node t, int depth) {
        if (t != null) {
            inorder_traversal(t.getLeft(), depth + 1); //add 1 to depth (y coordinate)
            t.setXpos(totalnodes++); //x coord is node number in inorder traversal
            t.setYpos(depth);; // mark y coord as depth
            inorder_traversal(t.getRight(), depth + 1);
        }
    }
    
    
    /*############  INSERT  ################*/
    public Node insert(Node node, int key) {
    	
    	/* 1.  Perform the normal BST insertion */
        if (node == null) {
        	return (new Node(key, null, null));
        }
        else {
        	if (key == node.getKey()) {
                return node;  /* duplicate key  found - do nothing */
            }
            else if (key < node.getKey()){
                node.setLeft(insert(node.getLeft(), key));
            }
            else {
                node.setRight(insert(node.getRight(), key));
            }
  
	        /* 2. Update height of this ancestor node */
	        node.setHeight(1 + max(height(node.getLeft()),
				  	  height(node.getRight()))); 
	  
	        /* 3. Get the balance factor of this ancestor 
	              node to check whether this node became 
	              unbalanced */
	        int balance = getBalance(node); 
	  
	        // If this node becomes unbalanced, then there 
	        // are 4 cases Left Left Case 
	        if (balance > 1 && (key < node.getLeft().getKey()))
	            return rightRotate(node); 
	  
	        // Right Right Case 
	        if (balance < -1 && (key > node.getRight().getKey())) 
	            return leftRotate(node); 
	  
	        // Left Right Case 
	        if (balance > 1 && (key > node.getLeft().getKey())) { 
	            node.setLeft(leftRotate(node.getLeft())); 
	            return rightRotate(node); 
	        } 
	  
	        // Right Left Case 
	        if (balance < -1 && (key < node.getRight().getKey())) { 
	            node.setRight(rightRotate(node.getRight())); 
	            return leftRotate(node); 
	        } 
	  
	        /* return the (unchanged) node */
	        return node; 
        }
    }
    
    
	
    //utility function to right rotate subtree with root y
    public Node rightRotate(Node y) {
    	Node x = y.getLeft();
    	Node T2 = x.getRight();
    	
    	//rotation
    	x.setRight(y);
    	y.setLeft(T2);
    	
    	//update heights
    	y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
    	x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
    	
    	//return new root
    	return x;
    }
    
    // Performs left rotate around x
   public Node leftRotate(Node x) { 
        Node y = x.getRight(); 
        Node T2 = y.getLeft(); 
        
        // Perform rotation 
        y.setLeft(x); 
        x.setRight(T2); 
  
        //  Update heights 
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1); 
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1); 
  
        // Return new root 
        return y; 
    } 
    
    // check to see which side of the node is deeper
    int getBalance(Node n) {
    	if(n == null)
    		return 0;
    	return height(n.getLeft()) - height(n.getRight());
    }
    
    
    //########### search #############
    int search(Node node, int key) {
    	if(node == null) {
    		return 0; // missing from tree
    	}else if(key < node.getKey()) {
    		return search(node.getLeft(), key);
    	}else if(key > node.getKey()) {
    		return search(node.getRight(), key);
    	}else {
    		return node.getKey(); // found it
    	}
    }
    
    Node minValueNode(Node node)  
    {  
        Node current = node;  
  
        /* loop down to find the leftmost leaf */
        while (current.getLeft() != null)  
        current = current.getLeft();  
  
        return current;
    }  

    
    //################ delete ###############
    public Node deleteNode(Node root, int key)  
    {  
        if (root == null)  
            return root;  
  
        if (key < root.getKey())  
            root.setLeft(deleteNode(root.getLeft(), key));  
        
        else if (key > root.getKey())  
            root.setRight(deleteNode(root.getRight(), key));  
    
        else
        {  
  
            // node with only one child or no child  
            if ((root.getLeft() == null) || (root.getRight() == null))  
            {  
                Node temp = null;  
                if (temp == root.getLeft())  
                    temp = root.getRight();  
                else
                    temp = root.getLeft();  
  
                // No child case  
                if (temp == null)  
                {  
                    temp = root;  
                    root = null;  
                }  
                else // One child case  
                    root = temp; // Copy the contents of  
                                // the non-empty child
            }  
            else
            {  
  
                // node with two children: Get the inorder  
                // successor (smallest in the right subtree)  
                Node temp = minValueNode(root.getRight());
  
                // Copy the inorder successor's data to this node  
                root.setKey(temp.getKey());
  
                // Delete the inorder successor  
                root.setRight(deleteNode(root.getRight(), temp.getKey()));
            }  
        }  
  
        // If the tree had only one node then return  
        if (root == null)  
            return root;  
  
        root.setHeight(max(height(root.getLeft()), height(root.getRight())) + 1);  
      
        int balance = getBalance(root);  
  
        // If this node becomes unbalanced, then there are 4 cases  
        // Left Left Case  
        if (balance > 1 && getBalance(root.getLeft()) >= 0)  
            return rightRotate(root);  
  
        // Left Right Case  
        if (balance > 1 && getBalance(root.getLeft()) < 0)  
        {  
            root.setLeft(leftRotate(root.getLeft()));  
            return rightRotate(root);  
        }  
  
        // Right Right Case  
        if (balance < -1 && getBalance(root.getRight()) <= 0)  
            return leftRotate(root);  
  
        // Right Left Case  
        if (balance < -1 && getBalance(root.getRight()) > 0)  
        {  
            root.setRight(rightRotate(root.getRight()));  
            return leftRotate(root);  
        }  
  
        return root;  
    }     
}