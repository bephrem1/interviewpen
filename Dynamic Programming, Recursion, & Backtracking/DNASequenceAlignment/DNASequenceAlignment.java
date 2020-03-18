/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  private static final int GAP_COST = 1;
  private static final Map<String, Integer> alignmentCosts = new HashMap<>();

  private int alignmentMinCost(String s1, String s2) {
    int[][] opt = new int[s1.length() + 1][s2.length() + 1];

    // Base case #1 - all gaps (s1 is on the rows)
    for (int row = 0; row <= s1.length(); row++) {
      opt[row][0] = row * GAP_COST;
    }

    // Base case #2 - all gaps (s2 is on the columns)
    for (int col = 0; col <= s2.length(); col++) {
      opt[0][col] = col * GAP_COST;
    }

    for (int row = 1; row <= s1.length(); row++) {
      for (int col = 1; col <= s2.length(); col++) {
        Character s1Char = s1.charAt(row - 1);
        Character s2Char = s2.charAt(col - 1);

        String combined = Character.toString(s1Char) + Character.toString(s2Char);
        int alignmentCost = alignmentCosts.get(combined);

        int align = alignmentCost + opt[row - 1][col - 1]; // adds pair to conceptual matching set
        int dontMatchS1Char = GAP_COST + opt[row - 1][col]; // leaves a gap in s2, char in s1 gets no match
        int dontMatchS2Char = GAP_COST + opt[row][col - 1]; // leaves a gap in s1, char in s2 gets no match

        opt[row][col] = Math.min(align, Math.min(dontMatchS1Char, dontMatchS2Char));
      }
    }

    return opt[s1.length()][s2.length()];
  }

  private void initializeAlignmentCosts(Map<String, Integer> alignmentCosts) {
    alignmentCosts.put("AA", 0); // same symbol
    alignmentCosts.put("CC", 0); // same symbol
    alignmentCosts.put("GG", 0); // same symbol
    alignmentCosts.put("TT", 0); // same symbol

    /*
      Costs arbitrarily chosen. Symmetry shouldn't guarantee same alignment cost because
      character 1 is from s1 and character 2 is from s2. So "AC" could theoretically cost
      more (or less) to align than "CA".
    */
    alignmentCosts.put("AC", 1);
    alignmentCosts.put("CA", 1);

    alignmentCosts.put("AG", 2);
    alignmentCosts.put("GA", 2);

    alignmentCosts.put("AT", 4);
    alignmentCosts.put("TA", 4);

    alignmentCosts.put("CG", 3);
    alignmentCosts.put("GC", 3);

    alignmentCosts.put("CT", 5);
    alignmentCosts.put("TC", 5);

    alignmentCosts.put("GT", 1);
    alignmentCosts.put("TG", 1);
  }
}

/*
  Example:

  g = gap
  m = mismatch

  s1 => GA-CGTTA
  s2 => GAACGCTA
          ^  ^
          g  m
  
        0123456
  s1 => GACGTTA

        01234567
  s2 => GAACGCTA

  tuple of (i, j)
  matching = { (0, 0), (1, 1), (2, 3), (3, 4), (4, 5), (5, 6), (6, 7) }
                              ^                   ^
                    gap (s2's A unmatched)  mismatch (w/ real cost)
*/

/*
  Trace back the table:
      G A A C G C T A
    0 1 2 3 4 5 6 7 8
  G 1 0 1 2 3 4 5 6 7
  A 2 1 0 1 2 3 4 5 6
  C 3 2 1 1 1 2 3 4 5
  G 4 3 2 2 2 1 2 3 4
  T 5 4 3 3 3 2 3 2 3
  T 6 5 4 4 4 3 4 3 4
  A 7 6 5 4 5 4 4 4 3

  1.) Add (6, 7) to the matching
  2.) Don't match index 5 of s1 (gap in s2)
  3.) Add (4, 6) to the matching
  4.) Don't match index 5 of s2 (gap in s1)
  5.) Add (3, 4) to the matching
  6.) Add (2, 3) to the matching (we could have not matched index 3 of s2, leaving a gap in s1)
  7.) Don't match index 2 of s2 (gap in s1)
  8.) Add (1, 1) to the matching
  9.) Add (0, 0) to the matching

  matching = { (0, 0), (1, 1), (2, 3), (3, 4), (4, 6), (6, 7) }

  Finally looks like this:
  s1 => GA-CG-TTA
  s2 => GAACGCT-A

  Total Cost is 3 (we see that this is "gap heavy" to avoid mismatch costs)
*/
