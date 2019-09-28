/*
  Selection Sort
*/
import java.util.*;

public class SelectionSort {
  public static void main(String args[]) {
    int[] numbers = new int[]{ 1, -40, 4, 5, 8, 3, 4, 1, 4, 5, 100, -12 };
    selectionSort(numbers);
    System.out.println(Arrays.toString(numbers));
  }

  public static void selectionSort(int[] arr) {
    int lastIndex = arr.length - 1;

    for (int i = lastIndex; i >= 1; i--) {
      int indexOfItemToPlace = 0;

      for (int j = 1; j <= i; j++) {
        if (arr[j] > arr[indexOfItemToPlace]) {
          indexOfItemToPlace = j;
        }
      }

      swap(arr, indexOfItemToPlace, i);
    }
  }

  private static void swap(int[] arr, int first, int second) {
    int temp = arr[first];
    arr[first] = arr[second];
    arr[second] = temp;
  }
}
