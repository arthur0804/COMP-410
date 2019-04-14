package SPLT_A4;


public class BST_Node {
    String data;
    BST_Node left;
    BST_Node right;
    BST_Node parent; 
    //parent...not necessarily required, but can be useful in splay tree
    boolean justMade; 
    //could be helpful if you change some of the return types on your BST_Node insert.
    //I personally use it to indicate to my SPLT insert whether or not we increment size.

    BST_Node(String data) {
        this.data = data;
        this.justMade = true;
    }

    BST_Node(String data, BST_Node left, BST_Node right, BST_Node parent) { 
    	//feel free to modify this constructor to suit your needs
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.justMade = true;
    }

    // --- used for testing  ----------------------------------------------
    //
    // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

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


    // --- Some example methods that could be helpful ------------------------------------------
    //
    // add the meat of correct implementation logic to them if you wish

    // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
    // make them take more or different parameters
    // have them return different types
    //
    // you may use recursive or iterative implementations

    /*
    public BST_Node containsNode(String s){ return false; } 
    //note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
    public BST_Node insertNode(String s){ return false; } 
    //Really same logic as above note
    public boolean removeNode(String s){ return false; } 
    //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
    public BST_Node findMin(){ return left; } 
    public BST_Node findMax(){ return right; }
    public int getHeight(){ return 0; }

    private void splay(BST_Node toSplay) { return false; } 
    //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
    //I of course, will be checking with tests and by eye to make sure you are indeed splaying
    //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
    */

    // --- end example methods --------------------------------------

    // --------------------------------------------------------------------
    // you may add any other methods you want to get the job done
    // --------------------------------------------------------------------   
    
    /*
     * return the node if it is in the BST
     * return the last visited node if it is not in the BST
     */
    public BST_Node containsNode(String s) {
    	int result = s.compareTo(this.data);
    	if(result < 0) {
    		// smaller, go to the left side
    		if(this.left != null) {
    			return this.left.containsNode(s);
    		}else {
    			// last node visited
    			return this;
    		}
    	}else if(result > 0) {
    		// bigger, go to the right side
    		if(this.right != null) {
    			return this.right.containsNode(s);
    		}else {
    			// last node visited
    			return this;
    		}	
    	}else {
    		// equal means contains
    		return this;
    	} 
    }

    public BST_Node insertNode(String s) {
        if(s.compareTo(this.data) < 0) {
        	// smaller, go to the left side
        	if(this.left != null) {
        		return this.left.insertNode(s);
        	}else {
        		BST_Node newnode = new BST_Node(s, null, null, this);
        		this.left = newnode;
        		return newnode;
        	}
        }else {
        	// bigger, go to the right side
        	if(this.right != null) {
        		return this.right.insertNode(s);
        	}else {
        		BST_Node newnode = new BST_Node(s, null, null, this);
        		this.right = newnode;
        		return newnode;
        	}
        }
    }

    public boolean removeNode(String s) { 
        if (data == null) return false;
        if (data.equals(s)) {
            if (left != null) {
                data = left.findMax().data;
                left.removeNode(data);
                if (left.data == null) left = null;
            } else if (right != null) {
                data = right.findMin().data;
                right.removeNode(data);
                if (right.data == null) right = null;
            } else data = null;
            return true;
        } else if (data.compareTo(s) > 0) {
            if (left == null) return false;
            if (!left.removeNode(s)) return false;
            if (left.data == null) left = null;
            return true;
        } else if (data.compareTo(s) < 0) {
            if (right == null) return false;
            if (!right.removeNode(s)) return false;
            if (right.data == null) right = null;
            return true;
        }
        return false;
    }

    public BST_Node findMin() {
        if (left != null) {
        	return left.findMin();
        }else {
        	return this;
        }
    }

    public BST_Node findMax() {
        if (right != null) {
        	return right.findMax();
        }else {
        	return this;
        }
    }

    public int getHeight() {
        int l = 0;
        int r = 0;
        if (left != null) l += left.getHeight() + 1;
        if (right != null) r += right.getHeight() + 1;
        return Integer.max(l, r);
    }

}