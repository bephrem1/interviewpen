import java.util.*;

public class KSmallestElements {
  public static void main(String args[]) {
    int[] elements = new int[]{ 1000, 0, 999, 984, 984, 39 };
    int k = 3;

    List<Integer> kSmallestElements = kSmallestElements(elements, k);
    System.out.println(kSmallestElements.toString());
  }

  private static List<Integer> kSmallestElements(int[] elements, int k) {
    /*
      If we want the k smallest items, we don't care for large items. So max heap.
      If we want the k largest items, we don't care for small items. So min heap.
    */
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());

    for (int element: elements) {
      maxHeap.add(element);
      if (maxHeap.size() == k + 1) {
        maxHeap.poll(); // over capacity by 1, eject largest item
      }
    }

    // Format to list just so we aren't returning a raw prioirty queue
    List<Integer> kSmallestElements = new ArrayList<>(maxHeap);

    return kSmallestElements;
  }
}
