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
     * Default Constructor. Assigns maxSize to 100, current siz, and number of swaps to 0, and initializes heapArray.
     */
    public MaxHeap() {
        maxSize = 100;
        currentSize = 0;
        numberOfSwaps = 0;
        heapArray = new int[maxSize];
    }

    public boolean insert(int value) {
        if (currentSize == maxSize) {
            return false;
        }
        heapArray[currentSize] = value;
        trickleUp(currentSize++);
        return true;
    }

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

    public int remove() {
        int root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    public boolean insertOptimal(int value, int filledSize) {
        if (currentSize == maxSize) {
            return false;
        }

        heapArray[currentSize++] = value;
        if (currentSize == filledSize) {
            optimalReheapify();
        }
        return true;
    }

    private void optimalReheapify() {
        int largerChild;
        int parent = (currentSize / 2) - 1;

        while (parent > 0) {
            int leftChild = 2 * parent + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && heapArray[leftChild] < heapArray[rightChild]) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            if (heapArray[parent] < heapArray[largerChild]) {
                swap(parent, largerChild);
            }

            parent--;
        }

        trickleDown(0);
    }

    private void swap(int parent, int child) {
        int temp = heapArray[parent];
        heapArray[parent] = heapArray[child];
        heapArray[child] = temp;
        numberOfSwaps++;
    }

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

    public void displayHeap() {
        for (int i = 0; i < currentSize - 1; i++) {
            System.out.print(heapArray[i] + ", ");
        }
        System.out.print(heapArray[currentSize - 1]);
    }

    public int getNumberOfSwaps() {
        return numberOfSwaps;
    }

    //TODO: Add Javadoc comments
}
