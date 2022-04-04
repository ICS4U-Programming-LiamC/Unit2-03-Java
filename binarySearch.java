import java.util.Arrays; 
import java.util.Random;
import java.util.Scanner;


/**
* 
* 
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
  
    System.out.println("Welcome! This program finds a number in a list\n");
    int[] arrayOfnums = new int[20];
    Random r = new Random();
    System.out.println("The array that we are searching through is: ");
    for (int i = 0; i < arrayOfnums.length; i++) {
      arrayOfnums[i] = (r.nextInt(20));
    }

    // https://www.softwaretestinghelp.com/sort-arrays-in-java/
    System.out.printf("Original Array : %s", Arrays.toString(arrayOfnums)); 
    Arrays.sort(arrayOfnums); 
    System.out.printf("\nSorted Array : %s \n", Arrays.toString(arrayOfnums)); 

    int numToLookFor = arrayOfnums[r.nextInt(arrayOfnums.length)];

    int index = binarySearcher(arrayOfnums, numToLookFor, 0, arrayOfnums.length);

    String rangeOnIndex = isElseWhere(arrayOfnums, index);
    
    System.out.println("The number " + numToLookFor + " appears at " + rangeOnIndex);

  }
}
