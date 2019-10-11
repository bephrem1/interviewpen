import java.util.*;

public class IntervalScheduling {
  public static void main(String args[]) {
    List<Interval> intervals = new ArrayList<>();
    intervals.add(new Interval("a", 0, 3));
    intervals.add(new Interval("b", 0, 6));
    intervals.add(new Interval("c", 0, 17));
    intervals.add(new Interval("d", 5, 10));
    intervals.add(new Interval("e", 8, 11));
    intervals.add(new Interval("f", 11, 15));
    intervals.add(new Interval("g", 12, 18));
    intervals.add(new Interval("h", 20, 22));
    intervals.add(new Interval("i", 19, 23));

    List<Interval> optimalSchedule = constructOptimalSchedule(intervals);
    for (Interval scheduledInterval: optimalSchedule) {
      System.out.println(scheduledInterval.toString());
    }
  }

  private static List<Interval> constructOptimalSchedule(List<Interval> intervals) {
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

  private static class Interval implements Comparable<Interval> {
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
