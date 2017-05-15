import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: May 14, 2017
 */
public class HeapDriver {
    public static void main(String[] args) {
        //set up variables
        Scanner input = new Scanner(System.in);
        int userInput, insertionSwaps = 0, optimalSwaps = 0;

        //Using wrapper class so that Collections.shuffle properly shuffles values
        Integer[] numbersArr = new Integer[100];
        for (int i = 0; i < numbersArr.length; i++) {
            numbersArr[i] = i + 1;
        }

        //display menu
        System.out.println("Please select how to test the program:");
        System.out.println("(1) 20 sets of 100 randomly generated integers");
        System.out.println("(2) Fixed integer values 1-100");
        System.out.print("Enter choice: ");

        //get user menu choice
        userInput = input.nextInt();

        //if user chooses 1, create and fill 20 sets of each series insertions, and optimal insertions
        //if user chooses 2, create and fill 2 heaps 1->100, one with series insertion, and one with optimal
        if (userInput == 1) {
            for (int i = 0; i < 20; i++) {
                MaxHeap insertionHeap = new MaxHeap();
                //shuffle array
                Collections.shuffle(Arrays.asList(numbersArr));
                //generate series insertion heaps and count swaps
                for (int j = 0; j < numbersArr.length; j++) {
                    insertionHeap.insert(numbersArr[j]);
                }
                insertionSwaps += insertionHeap.getNumberOfSwaps();

                //generate optimal insertion heaps and count swaps
                MaxHeap optimalHeap = new MaxHeap();
                for (int j = 0; j < numbersArr.length; j++) {
                    optimalHeap.insertOptimal(numbersArr[j], 100);
                }
                optimalSwaps += optimalHeap.getNumberOfSwaps();
            }

            System.out.println("\nAverage swaps for series of insertions: " + (insertionSwaps / 20));
            System.out.println("Average swaps for optimal method: " + (optimalSwaps / 20));
        } else if (userInput == 2) {
            //fill heap with series of insertions and display info
            MaxHeap insertionHeap = new MaxHeap();
            for (int i = 0; i < numbersArr.length; i++) {
                insertionHeap.insert(numbersArr[i]);
            }
            System.out.print("\nHeap built using series of insertions: ");
            insertionHeap.displayHeap();
            System.out.println("Number of swaps: " + insertionHeap.getNumberOfSwaps());
            for (int i = 0; i < 10; i++) {
                insertionHeap.remove();
            }
            System.out.print("Heap after 10 removals: ");
            insertionHeap.displayHeap();
            System.out.println();

            //fill heap with optimal insertions and display info
            MaxHeap optimalHeap = new MaxHeap();
            for (int i = 0; i < numbersArr.length; i++) {
                optimalHeap.insertOptimal(numbersArr[i], 100);

            }
            System.out.print("Heap built using optimal method: ");
            optimalHeap.displayHeap();
            System.out.println("Number of swaps: " + optimalHeap.getNumberOfSwaps());
            for (int i = 0; i < 10; i++) {
                optimalHeap.remove();
            }
            System.out.print("Heap after 10 removals: ");
            optimalHeap.displayHeap();
        } else {
            //display error if the user didn't choose 1 or 2
            System.out.println("Error: Invalid Input");
        }
    }
}
