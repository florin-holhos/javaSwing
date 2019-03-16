
public class Node { 
   
	private int key; 
	private Node left; 
    private Node right;
    
    private int height;
    
    private int xpos;
    private int ypos;
    
    public Node(int k, Node l, Node r){
    	this.left = l;
    	this.right = r;
    	this.key = k;
    	this.height = 1;
    }

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

}