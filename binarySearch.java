import java.util.Arrays; 
import java.util.Random;
import java.util.Scanner;


/**
* This program utilizes the binary search algorithm to
* find a number in an array quickly. 
* I also implimented a program to find out if that same number appears
* at other indecies around the number that the function found.
*
* @author  Liam Csiffary
* @version 1.0
* @since   2022-04-04
*/

public class binarySearch {

  // the function which calculates the factorial
  public static int binarySearcher(int[] array, int i, int left, int right) {
    int mid = (right - left) / 2 + left;
    if (array[mid] == i) {
      return mid;
    } else if (array[mid] > i) {
      return binarySearcher(array, i, left, mid - 1);
    } else {
      return binarySearcher(array, i, mid + 1, right);
    }
  }

  public static String isElseWhere(int[] array, int initIndex) {
    int numToLookFor = array[initIndex];
    int curNum = numToLookFor;
    int counter = 0;
    String range1 = "";
    String range2 = "";
    try {
      while (curNum == numToLookFor) {
        counter++;
        curNum = array[initIndex - counter];
      }
      range1 += (initIndex - counter + 1);
    } catch (Exception e) {
      range1 += "0";
    }

    try {
      curNum = numToLookFor;
      counter = 0;
      while (curNum == numToLookFor) {
        counter++;
        curNum = array[initIndex + counter];
      }
      range2 += (initIndex + counter - 1);
    } catch (Exception e) {
      range2 += array.length;
    }

    if (!range1.equals(range2)) {
      return "indecies " + range1 + "-" + range2;
    } else {
      return "index " + range1;
    }
  }

  // main function
  public static void main(String[] args) throws Exception {
    // define vars
    int lengthOfArray;
    int maxNum;
    Random r = new Random();
    Scanner sc = new Scanner(System.in);
  
    // greets the user
    System.out.println("Welcome! This program finds a number in a list\n");

    // gets the number of ints to put in the array
    System.out.println("How many numbers would you like to have in the array? ");
    
    // makes sure the user inputed an int
    while (true) {
      try {
        lengthOfArray = Integer.parseInt(sc.nextLine());
        break;
      } catch (NumberFormatException e) {
        System.out.println("Please input an int");
      }
    }
    // creates an array of that length
    int[] arrayOfnums = new int[lengthOfArray];

    // gets how big the numbers in the array should be
    System.out.println("Up to what number should the array be populated with 0-?: ");
    
    // makes sure the user inputed an int
    while (true) {
      try {
        maxNum = Integer.parseInt(sc.nextLine());
        break;
      } catch (NumberFormatException e) {
        System.out.println("Please input an int");
      }
    }

    // populates the array with numbers
    System.out.println("The array that we are searching through is: ");
    for (int i = 0; i < arrayOfnums.length; i++) {
      // + 1 so that its from 1 to the users input
      arrayOfnums[i] = (r.nextInt(maxNum) + 1);
    }

    // https://www.softwaretestinghelp.com/sort-arrays-in-java/
    System.out.printf("Original Array : %s", Arrays.toString(arrayOfnums)); 
    Arrays.sort(arrayOfnums); 
    System.out.printf("\nSorted Array : %s \n", Arrays.toString(arrayOfnums)); 

    // generates a random number for the program to search for
    int numToLookFor = arrayOfnums[r.nextInt(arrayOfnums.length)];

    // sends array and the numToLookFor to the searcher function
    int index = binarySearcher(arrayOfnums, numToLookFor, 0, arrayOfnums.length);

    // finds all indecies arround the returned value for which the number
    // at that index is the same
    String rangeOnIndex = isElseWhere(arrayOfnums, index);
    
    // prints the final answer to the user
    System.out.println("The number " + numToLookFor + " appears at " + rangeOnIndex);
    System.out.println("The length of the array was " + arrayOfnums.length);
  }
}
