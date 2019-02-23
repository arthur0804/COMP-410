package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
    private EntryPair[] array; //load this array
    private int size;
    private static final int arraySize = 10000;
    //Everything in the array will initially 
    //be null. This is ok! Just build out 
    //from array[1]

    public MinBinHeap() {
        this.array = new EntryPair[arraySize];
        array[0] = new EntryPair(null, -100000);
        //0th will be unused for simplicity 
        //of child/parent computations...
        //the book/animation page both do this.
    }

    //Please do not remove or modify this method! Used to test your entire Heap.
    @Override
    public EntryPair[] getHeap() {
        return this.array;
    }

    @Override
    public void insert(EntryPair entry) {
        if (size() == 0) {
            // insert into an empty array
            this.array[1] = entry;
        } else {
            // appended at the end
            int hole = size() + 1;
            array[hole] = entry;
            while ((hole / 2) > 0) {
                // compare to its parent
                if (array[hole].getPriority() < array[hole / 2].getPriority()) {
                    EntryPair temp = array[hole / 2];
                    array[hole / 2] = array[hole];
                    array[hole] = temp;
                    hole = hole / 2;
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public void delMin() {
        // remove the root and replace with the last item
        this.array[1] = this.array[size()];
        this.array[size()] = null;
        bubbleDown(1);
    }

    // helper function: bubble down an item
    public void bubbleDown(int pos) {
        while (pos * 2 <= size()) {
            // get the smaller child
            int mc = minChild(pos);
            if (this.array[pos].getPriority() > this.array[mc].getPriority()) {
                // if the parent is bigger than child, swap; and continue
                EntryPair temp = this.array[pos];
                this.array[pos] = this.array[mc];
                this.array[mc] = temp;
                pos = mc;
            } else {
                break;
            }
        }
    }

    // helper function: return the smaller child of a node
    public int minChild(int pos) {
        if (pos * 2 + 1 > size()) {
            return pos * 2;
        } else {
            if (this.array[pos * 2].getPriority() < this.array[pos * 2 + 1].getPriority()) {
                return pos * 2;
            } else {
                return pos * 2 + 1;
            }
        }
    }

    @Override
    public EntryPair getMin() {
        if (size() == 0) {
            return null;
        } else {
            return this.array[1];
        }
    }

    @Override
    public int size() {
        size = 0;
        int i = 1;
        while (array[i] != null) {
            size++;
            i++;
        }
        return size;
    }

    @Override
    public void build(EntryPair[] entries) {
        // copy into the array for the structure property
        for (int i = 0; i < entries.length; i++) {
            array[i + 1] = entries[i];
        }
        // start from the first non-leaf that has a child, bubble down
        int pos = size() / 2;
        while (pos > 0) {
            bubbleDown(pos);
            pos--;
        }
    }
}