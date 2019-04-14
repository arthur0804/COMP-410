package SPLT_A4;


public class SPLT implements SPLT_Interface {
    private BST_Node root;
    private int size;

    public SPLT() {
        this.size = 0;
        this.root = null;
    }

    public BST_Node getRoot() {
        //please keep this in here! I need your root node to test your tree!
        return this.root;
    }
    
    // move the root until the top
    public void splay(BST_Node node_tosplay) {
    	while (node_tosplay != root) {
			BST_Node P = node_tosplay.parent;
			//zig
			if (P.equals(root)){
				if (node_tosplay.equals(P.left)) {
	        		rotateRight(P);
				}
				else if (node_tosplay.equals(P.right)){
					rotateLeft(P);
				}
			}
			else {
				BST_Node G = P.parent;
				// zig-zig 
				if(node_tosplay.equals(P.left) && P.equals(G.left)) {
					rotateRight(G);
					rotateRight(P);
				}
				// zag-zag
				if(node_tosplay.equals(P.right) && P.equals(G.right)) {
					rotateLeft(G);
					rotateLeft(P);
				}
				// zig-zag 
				if(node_tosplay.equals(P.right) && P.equals(G.left)) {
					rotateLeft(P);
					rotateRight(G);
				}
				// zag-zig
				if(node_tosplay.equals(P.left) && P.equals(G.right)) {
					rotateRight(P);
					rotateLeft(G);
				}
			}
    	}
    }
    
    public void rotateLeft(BST_Node node) {
    	BST_Node temp = node.right;
    	temp.parent = node.parent;
    	
    	node.right = temp.left;
    	if(node.right != null) {
    		node.right.parent = node;
    	}
    	
    	temp.left = node;
    	node.parent = temp;
    	
    	// temp took over node's place so now its parent should point to temp
        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
    }
    
    public void rotateRight(BST_Node node) {
    	BST_Node temp = node.left;
    	temp.parent = node.parent;
    	
    	node.left = temp.right;
    	if(node.left != null) {
    		node.left.parent = node;
    	}
    	
    	temp.right = node;
    	node.parent = temp;
    	
    	// temp took over node's place so now its parent should point to temp
        if (temp.parent != null) {
            if (node == temp.parent.left) {
                temp.parent.left = temp;
            } else {
                temp.parent.right = temp;
            }
        } else {
            root = temp;
        }
    }
   
    @Override
	public void insert(String s) {
		if (empty()) {
			root = new BST_Node(s);
			size += 1;
			return;
		}
		if (contains(s)) {
			// this step also splay the node
			return;
		} else {
			size += 1;
			BST_Node node_inseted = root.insertNode(s);
			splay(node_inseted);
			return;
		}
	}

	@Override
	public void remove(String s) {
		// this step also splay the node 
		boolean found_or_not = contains(s);
		if (!found_or_not) {
			return;
		} else {
			if (root.data.equals(s) && size == 1) {
				root = null;
				size -= 1;
				return;
			} else {
				size -= 1;
				root.removeNode(s);
				return;
			}
		}
	}

	@Override
	public String findMin() {
		if (empty()) {
			return null;
		} else {
			splay(root.findMin());
			return root.findMin().data;
		}
	}

	@Override
	public String findMax() {
		if (empty()) {
			return null;
		} else {
			splay(root.findMax());
			return root.findMax().data;
		}
	}

	@Override
	public boolean empty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		if (empty()) {
			return false;
		}
		// splay at the same time
		BST_Node last_node_visited = root.containsNode(s);
		splay(last_node_visited);
		if (last_node_visited.data.equals(s)){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int height() {
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

}