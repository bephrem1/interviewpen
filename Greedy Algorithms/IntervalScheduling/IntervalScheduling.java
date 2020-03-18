/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  public List<Interval> constructOptimalSchedule(List<Interval> intervals) {
    List<Interval> optimalSchedule = new ArrayList<>();
    Collections.sort(intervals); // sort by finish time

    int lastScheduledIntervalFinish = Integer.MIN_VALUE;
    for (Interval interval: intervals) {
      if (interval.start > lastScheduledIntervalFinish) {
        optimalSchedule.add(interval);
        lastScheduledIntervalFinish = interval.end;
      }
    }

    return optimalSchedule;
  }

  private class Interval implements Comparable<Interval> {
    String id;
    int start;
    int end;

    public Interval(String id, int start, int end) {
      this.id = id;
      this.start = start;
      this.end = end;
    }

    @Override
    public String toString() {
      return "#" + this.id + " [" + this.start + ", " + this.end + "]";
    }

    @Override
    public int compareTo(Interval that) {
      if (this.end < that.end) {
        return -1;
      } else {
        return this.end == that.end ? 0 : 1;
      }
    }
  }
}
