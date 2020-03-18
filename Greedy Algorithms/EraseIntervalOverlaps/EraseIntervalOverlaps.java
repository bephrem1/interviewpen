/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int eraseOverlapIntervals(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }

    Arrays.sort(intervals, new TwoDimArrayComparator());

    int endOfActiveInterval = intervals[0][1];
    int totalIntervalsInNonoverlappingSet = 1;

    for (int i = 1; i < intervals.length; i++) {
      int[] interval = intervals[i];

      int intervalStart = interval[0];
      int intervalEnd = interval[1];

      if (intervalStart >= endOfActiveInterval) {
        endOfActiveInterval = intervalEnd;
        totalIntervalsInNonoverlappingSet++;
      }
    }

    return intervals.length - totalIntervalsInNonoverlappingSet;
  }

  // Sorts intervals in increasing order by end time
  class TwoDimArrayComparator implements Comparator<int[]> {
    public int compare(int[] a, int[] b) {
      return a[1] - b[1];
    }
  }
}
