import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This program utilizes the binary search algorithm to
 * find a number in an array quickly.
 * I also implemented a program to find out if that same number appears
 * at other indices around the number that the function found.
 *
 * @author Liam Csiffary
 * @version 1.0
 * @since 2022-04-04
 */

public class binarySearch {

  // the function which calculates the factorial
  public static int binarySearcher(int[] array, int i, int left, int right) {
    // defines the middle index of the remaining search range
    int mid = (right - left) / 2 + left;

    // determines whether i is correct, or if it is greater or less than the number
    // to look for
    if (array[mid] == i) {
      return mid;
    } else if (right == left) {
      return -1;
    } else if (array[mid] > i) {
      // sends the new left right to the function again changes right to be where the
      // middle was - 1
      return binarySearcher(array, i, left, mid - 1);
    } else {
      // does the same as the last one but with left and + 1
      return binarySearcher(array, i, mid + 1, right);
    }
  }

  // this function finds all other occurrences of the
  // number left or right of the number
  // that the binarySearcher found
  public static String isElseWhere(int[] array, int initIndex) {
    // gets the number the function should be looking for
    int numToLookFor = array[initIndex];
    int curNum = numToLookFor;
    int counter = 0;

    // these are the strings that we will pass back to the function
    String range1 = "";
    String range2 = "";

    // finds the numbers to the left of the initial index
    try {
      while (curNum == numToLookFor) {
        counter++;
        curNum = array[initIndex - counter];
      }
      range1 += (initIndex - counter + 1);
    } catch (Exception e) {
      // if it goes out of bounds then the range should be 0
      range1 += "0";
    }

    // finds all numbers o the right of the index
    try {
      curNum = numToLookFor;
      counter = 0;
      while (curNum == numToLookFor) {
        counter++;
        curNum = array[initIndex + counter];
      }
      range2 += (initIndex + counter - 1);
    } catch (Exception e) {
      // if it goes out of bounds to the right
      range2 += array.length;
    }

    // if range1 != range2 then return the new range of that number
    if (!range1.equals(range2)) {
      return "indices " + range1 + "-" + range2;
    } else {
      // otherwise return the initial index
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

    // makes sure the user inputed a positive integer
    while (true) {
      try {
        lengthOfArray = Integer.parseInt(sc.nextLine());
        if (lengthOfArray <= 0) {
          System.out.println("Please input an integer greater than 0");
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("Please input an int");
      }
    }

    // gets how big the numbers in the array should be
    System.out.println("Up to what number should the array be populated with 0-?: ");

    // makes sure the user inputed an int
    while (true) {
      try {
        maxNum = Integer.parseInt(sc.nextLine());
        if (maxNum <= 0) {
          System.out.println("Please input an integer greater than 0");
        } else {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("Please input an int");
      }
    }

    // creates an array of the length that the user inputed
    int[] arrayOfnums = new int[lengthOfArray];

    // populates the array with numbers
    System.out.println("The array that we are searching through is: ");
    for (int i = 0; i < arrayOfnums.length; i++) {
      // + 1 so that its from 1 to the users input
      arrayOfnums[i] = (r.nextInt(maxNum) + 1);
    }

    // https://www.softwaretestinghelp.com/sort-arrays-in-java/
    Arrays.sort(arrayOfnums);
    System.out.printf("\nSorted Array : %s \n", Arrays.toString(arrayOfnums));

    // generates a random number for the program to search for
    int numToLookFor = arrayOfnums[r.nextInt(arrayOfnums.length)];

    // sends array and the numToLookFor to the searcher function
    int index = binarySearcher(arrayOfnums, numToLookFor, 0, arrayOfnums.length);

    // if the number was not in the array
    if (index == -1) {
      System.out.println("The number " + numToLookFor + " does not appear in the array");
    } else {
      // finds all indices around the returned value for which the number
      // at that index is the same
      String rangeOnIndex = isElseWhere(arrayOfnums, index);

      // prints the final answer to the user
      System.out.println("The number " + numToLookFor + " appears at " + rangeOnIndex);
      System.out.println("The length of the array was " + arrayOfnums.length);
    }
  }
}
