/**
 * <h1>Max Heap Class</h1>
 * MaxHeap implements a Max Heap class with an Array based representation (Default size: 100)
 * <p></p>
 * This Heap Can:
 * <ul>
 * <li>Insert And Remove Values</li>
 * <li>Display All Values in Heap</li>
 * <li>Display Number of Swaps Used During Insertion</li>
 * </ul>
 * <p></p>
 * <b>Note:</b> There are 2 methods of insertion: Sequential and Optimal
 *
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: May 13, 2017
 */
public class MaxHeap {
    /**integer array containing heap data*/
    private int[] heapArray;
    /**current maximum size of the heap*/
    private int maxSize;
    /**current number of items in the heap array*/
    private int currentSize;
    /**number of swaps performed when inserting or removing during lifespan of this MaxHeap*/
    private int numberOfSwaps;

    /**
     * Default Constructor. Assigns maxSize to 100, current siz, and number of swaps to 0, and initializes heapArray
     */
    public MaxHeap() {
        maxSize = 100;
        currentSize = 0;
        numberOfSwaps = 0;
        heapArray = new int[maxSize];
    }

    /**
     * Inserts a value into the max heap
     *
     * @param value to be inserted
     * @return true if inserted properly, false if insert failed
     */
    public boolean insert(int value) {
        if (currentSize == maxSize) {
            return false;
        }
        heapArray[currentSize] = value;
        trickleUp(currentSize++);
        return true;
    }

    /**
     * Trickles up value at given index
     *
     * @param index to be trickled up
     */
    private void trickleUp(int index) {
        int parent = (index - 1) / 2;
        int bottom = heapArray[index];

        while (index > 0 && heapArray[parent] < bottom) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
            numberOfSwaps++;
        }
        heapArray[index] = bottom;
    }

    /**
     * Removes a value from the top of the heap
     *
     * @return value that was removed from heap
     */
    public int remove() {
        int root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    /**
     * Inserts a value into the heap, if the max heap is filled, it will sort values optimally
     *
     * @param value to be inserted
     * @param filledSize max size before sorting
     * @return true if value is inserted properly, false if insert failed
     */
    public boolean insertOptimal(int value, int filledSize) {
        if (currentSize == maxSize) {
            return false;
        }

        heapArray[currentSize++] = value;
        if (currentSize == filledSize) {
            int parent = (currentSize / 2) - 1;

            while (parent > 0) {
                trickleDown(parent);
                parent--;
            }
            trickleDown(0);
        }

        return true;
    }

    /**
     * Trickles down value at given idex
     *
     * @param index to be trickled down
     */
    private void trickleDown(int index) {
        int largerChild;
        int top = heapArray[index];

        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && heapArray[leftChild] < heapArray[rightChild]) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (top >= heapArray[largerChild]) {
                break;
            }

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
            numberOfSwaps++;
        }

        heapArray[index] = top;
    }

    /**
     * Displays first 10 values in the heap
     */
    public void displayHeap() {
        for (int i = 0; i < 10; i++) {
            System.out.print(heapArray[i] + ", ");
        }
        System.out.println("...");
    }

    /**
     * Gets the total number of swaps that have occurred over all swaps during the lifetime of the heap
     *
     * @return the total number of swaps
     */
    public int getNumberOfSwaps() {
        return numberOfSwaps;
    }
}
