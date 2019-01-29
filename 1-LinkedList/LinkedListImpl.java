package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	//this will be the entry point to your linked list (the head)
    Node sentinel; 
  
    public LinkedListImpl() { 
    	//this constructor is needed for testing purposes. Please don't modify!
        sentinel = new Node(0); 
        //Note that the root's data is not a true part of your data set!
    }

    //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!

    public Node getRoot() { 
    	//leave this method as is, used by the grader to grab your linkedList easily.
        return sentinel;
    }

    @Override
    public boolean insert(double elt, int index) {
        Node node_inserted = new Node(elt);
        if(index < 0 || index > this.size()) {
        	// out of boundary
        	return false;
        }else {
        	// valid index
        	if(this.size() == 0) {
        		// empty list
        		this.sentinel.next = this.sentinel.prev = node_inserted;
        		node_inserted.prev = node_inserted.next = this.sentinel;
        		return true;
        	}else {
        		// not an empty list: 1) at the end 2) at the start 3) in the middle
        		if (index == this.size()) {
        			// at the end: this O(1) will not iterate through the list until the end O(n)
        			this.sentinel.prev.next = node_inserted;
        			node_inserted.prev = this.sentinel.prev;
        			node_inserted.next = this.sentinel;
        			this.sentinel.prev = node_inserted;
        			return true;
        		}else {	
        			
            		int i = 0;
            		Node temp = this.sentinel.next;	// place to insert
            		while(i < index) {
            			temp = temp.next;
            			i++;
            		}
            		temp.prev.next = node_inserted;
            		node_inserted.prev = temp.prev;
            		temp.prev = node_inserted;
            		node_inserted.next = temp;    		
            		return true; 
        		}		
        	}       				
        }
    }

    @Override
    public boolean remove(int index) {
    	if(index < 0 || index >= this.size() || this.isEmpty()) {
        	// out of boundary, or no elements in the list
        	return false;
        }else {
        	// valid index and not an empty list: 1) at the end 2) at the start 3) in the middle
        	if(index == (this.size()-1)) {
        		// at the end: this O(1) will not iterate through the list until the end O(n)
        		Node node_removed = this.sentinel.prev;
        		node_removed.prev.next = this.sentinel;
        		this.sentinel.prev = node_removed.prev;  		
        		return true;
        	}else {
        		// at the beginning O(1) or in the middle O(n)
        		Node node_removed = this.sentinel.next;
            	int i = 0;
            	while(i < index) {
            		node_removed = node_removed.next;
            		i++;
            	}
            	node_removed.prev.next = node_removed.next;
            	node_removed.next.prev = node_removed.prev;    	
                return true;       
        	} 	 	
        }
    }

    @Override
    public double get(int index) {
    	if(index < 0 || index >= this.size() || this.isEmpty()) {
    		// out of boundary, or no elements in the list
    		return Double.NaN;
        }else {
        	// valid index and not an empty list
        	Node temp = this.sentinel.next;
        	int i = 0;
        	while(i < index) {
        		temp = temp.next;
        		i++;
        	}
        	return temp.data;
        }		
    }

    @Override
    public int size() {
        int size = 0;
        if(this.sentinel.next == null || this.sentinel.next.equals(this.sentinel)) {
        	// empty list: initial state or after clearing the list
        	return size;
        }else {
        	// not empty
            Node temp = this.sentinel.next;
        	while(! temp.equals(this.sentinel)) {
            	temp = temp.next;
            	size++;
            }
            return size;
        }       
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0); 
    }

    @Override
    public void clear() {
    	this.sentinel.next = this.sentinel.prev = this.sentinel;
    }
}