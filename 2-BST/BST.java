package BST_A2;

public class BST implements BST_Interface {
    public BST_Node root;
    int size;

    public BST() {
        size = 0;
        root = null;
    }

    @Override
    //used for testing, please leave as is
    public BST_Node getRoot() {
    	if(this.empty() == true) {
    		return null;
    	}else {
    		return this.root;
    	} 
    }

    @Override
    public boolean insert(String s) {
        if(this.empty() == true) {
        	// insert the root node
        	BST_Node node = new BST_Node(s);
  		  	this.root = node;
  		  	this.size++;
  		  	return true;
        }else if (this.contains(s)) {
        	// invalid case
        	return false;
        }else {
        	// insert the node which is not root
        	this.size++;
            return this.root.insertNode(s);
        }
    }

    @Override
    public boolean remove(String s) {
    	if(this.empty() == true || this.contains(s) == false) {
    		// invalid case
    		return false;
    	}else if (s == this.root.data) {
    		// remove root node
    		if(root.left == null && root.right == null) {
    			// only the root node exists
    			root = null;
    		}else if(root.left == null && root.right != null) {
    			// only has the right side
  			  	root = root.right;
  		  	}else if(root.left != null && root.right == null) {
  		  		// only has the left side
  		  		root = root.left;
  		  	}else {
  		  		// root has both sides, the minimum node at the right side become the new root	  		 
  		  		BST_Node temp = root.right.findMin();
  		  		root.removeNode(temp.data);
  		  		root.data = temp.data;
  		  		temp = null;
  		  	}
    		this.size--;
    		return true;
    	}else {
    		// remove node which is not the root
    		this.size--;
    		return this.root.removeNode(s);
    	} 
    }

    @Override
    public String findMin() {
    	if(this.empty() == true) {
    		return null;
    	}else {
    		return this.root.findMin().getData();
    	}
    }

    @Override
    public String findMax() {
    	if(this.empty() == true) {
    		return null;
    	}else {
            return this.root.findMax().getData();
    	}
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(String s) {
    	if(this.empty() == true) {
    		return false;
    	}else {
    		return this.root.containsNode(s);
    	} 
    }

    @Override
    public int size() {
    	if(this.empty() == true) {
    		return 0;
    	}else {
    		return this.size;
    	} 
    }

    @Override
    public int height() {
    	if(this.empty() == true) {
    		return -1;
    	}else {
    		return this.root.getHeight();
    	}
    }
}