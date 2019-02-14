package BST_A2;

public class BST_Node {
    String data;
    BST_Node left;
    BST_Node right;

    BST_Node(String data) {
        this.data = data;
    }

    // --- used for testing  ----------------------------------------------
    //
    // leave these 3 methods in, as is

    public String getData() {
        return data;
    }
    public BST_Node getLeft() {
        return left;
    }
    public BST_Node getRight() {
        return right;
    }

    // --- end used for testing -------------------------------------------


    // --- fill in these methods ------------------------------------------
    //
    // at the moment, they are stubs returning false 
    // or some appropriate "fake" value
    //
    // you make them work properly
    // add the meat of correct implementation logic to them

    // you MAY change the signatures if you wish...
    // make the take more or different parameters
    // have them return different types
    //
    // you may use recursive or iterative implementations

    /*
    public boolean containsNode(String s){ return false; }
    public boolean insertNode(String s){ return false; }
    public boolean removeNode(String s){ return false; }
    public BST_Node findMin(){ return left; }
    public BST_Node findMax(){ return right; }
    public int getHeight(){ return 0; }
    */

    // --- end fill in these methods --------------------------------------


    // --------------------------------------------------------------------
    // you may add any other methods you want to get the job done
    // --------------------------------------------------------------------

    public String toString() {
        return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") +
            ",Right: " + ((this.right != null) ? right.data : "null");
    }

    public boolean containsNode(String s) {
    	int result = s.compareTo(this.data);
    	if(result < 0) {
    		// smaller, go to the left side
    		if(this.left != null) {
    			return this.left.containsNode(s);
    		}else {
    			return false;
    		}
    	}else if(result > 0) {
    		// bigger, go to the right side
    		if(this.right != null) {
    			return this.right.containsNode(s);
    		}else {
    			return false;
    		}	
    	}else {
    		// equal means contains
    		return true;
    	}   
    }
    
    public boolean insertNode(String s) {
        BST_Node newnode = new BST_Node(s);
        if(s.compareTo(this.data) < 0) {
        	// smaller, go to the left side
        	if(this.left != null) {
        		return this.left.insertNode(s);
        	}else {
        		this.left = newnode;
        		return true;
        	}
        }else {
        	// bigger, go to the right side
        	if(this.right != null) {
        		return this.right.insertNode(s);
        	}else {
        		this.right = newnode;
        		return true;
        	}
        }
    }
    
    public boolean removeNode(String s) {
        
    	// first find the node to be removed and its parent and side
    	BST_Node removednode = this;
    	BST_Node parentnode = this;
    	BST_Node newnode = null;
    	boolean isLeftChild = false;
    	
  	  	while (removednode.data != s) {  
  	  		if (s.compareTo(removednode.data) < 0) {  
  	  			isLeftChild = true;   
  	  			if(removednode.left != null){  
  	  				parentnode = removednode; 
  	  				removednode = removednode.left;  
  	  			} 
              
  	  		}else {  
  	  			isLeftChild = false;  
  	  			if(removednode.right != null){  
  	  				parentnode = removednode;  
  	  				removednode = removednode.right;  
  	  			}  
  	  		}  
  	  	}
    	    	
    	// 1) the node has no child (leaf)
    	if(removednode.left == null && removednode.right == null) {
    		if(isLeftChild) {
    			parentnode.left = null;
    		}else {
    			parentnode.right = null;
    		}
    	}
    	
    	// 2) the node has one right child
    	if(removednode.left == null && removednode.right != null){
    		if(isLeftChild) {
    			parentnode.left = removednode.right;
    		}else {
    			parentnode.right = removednode.right;
    		}
    	}
    	
    	// 2) the node has one left child
    	if(removednode.left != null && removednode.right == null) {
    		if(isLeftChild) {
    			parentnode.left = removednode.left;
    		}else {
    			parentnode.right = removednode.left;
    		}
    	}
    
    	// 3) the node has two children
    	if(removednode.left != null && removednode.right != null) {
    		newnode = removednode.right.findMin(); // find the minimum node at the right side
    		String temp = newnode.data;
    		this.removeNode(newnode.data); 		
    		removednode.data = temp;
    	}
    	return true;
    }
    
    public BST_Node findMin() {
    	// if the node has not left child, the node is the smallest
    	// else the "most left" node will be the smallest
        if(this.left == null) {
        	return this;
        }else {
        	BST_Node temp = this.left;
        	while (temp.left != null) {
        		temp = temp.left;
        	}
        	return temp;
        }
    }
    
    public BST_Node findMax() {    	
    	// if the node has not right child, the node is the biggest
    	// else the "most right" node will be the biggest
    	if(this.right == null) {
        	return this;
        }else {
        	BST_Node temp = this.right;
        	while (temp.right != null) {
        		temp = temp.right;
        	}
        	return temp;
        }
    }
    
    public int getHeight() {
        int height;
        if(this.left == null && this.right == null){
        	// the leaf
        	height = 0;
        }else if(this.left == null && this.right != null){
        	height = this.right.getHeight() + 1;
        }else if(this.left != null && this.right == null){
        	height = this.left.getHeight() + 1;
        }else {  
            int i = this.left.getHeight();
            int j = this.right.getHeight();
            height = (i<j) ? (j+1):(i+1);      	
        }
        return height;
    }


}