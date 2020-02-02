class Solution {
  public List<List<Integer>> threeSum(int[] A) {
    Arrays.sort(A); // Sorting array of integers can take O(n) time via Bucket Sort or Radix Sort

    Set<List<Integer>> allThreeSums = new HashSet<>();
    int secondToLastIndex = A.length - 2;

    for (int i = 0; i < secondToLastIndex; i++) {
      findTwoSum(i, A, allThreeSums);
    }

    return new ArrayList<>(allThreeSums);
  }

  private void findTwoSum(int rootIndex, int[] A, Set<List<Integer>> allThreeSums) {
    int left = rootIndex + 1;
    int right = A.length - 1;

    while (left < right) {
      int threeNumberSum = A[rootIndex] + A[left] + A[right];

      if (threeNumberSum == 0) {
        allThreeSums.add(Arrays.asList(A[rootIndex], A[left++], A[right--]));
      } else if (threeNumberSum < 0) {
        left++;
      } else {
        right--;
      }
    }
  }
}
