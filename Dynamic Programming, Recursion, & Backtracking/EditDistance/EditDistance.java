/*
  Edit Distance - LeetCode: https://leetcode.com/problems/edit-distance
  This code passes all Leetcode test cases as of Oct. 27 2019
*/

public static int minDistance(String s1, String s2) {
  int[][] opt = new int[s1.length()][s2.length()];
  for (int[] row: opt) {
    Arrays.fill(row, -1);
  }

  return levenshteinDistance(s1, s1.length() - 1, s2, s2.length() - 1, opt);
}

private static int levenshteinDistance(String s1, int s1Index , String s2, int s2Index,
                                                  int[][] opt) {
  if (s1Index == 0) {
    return s2Index + 1; // If s1 is "", it is all insertions to get s1 to s2
  } else if (s2Index == 0) {
    return s1Index + 1; // If s2 is "", it is all deletions to get s1 to s2
  }

  if (opt[s1Index][s2Index] != -1) {
    return opt[s1Index][s2Index];
  }

  if (s1.charAt(s1Index) == s2.charAt(s2Index)) {
    // Characters match - no repair needs to take place, no addition to distance
    opt[s1Index][s2Index] = levenshteinDistance(s1, s1Index - 1, s2, s2Index - 1, opt);
  } else {
    /*
      We have a character mismatch. Remember we want to transform s1 into s2 and
      we hold the i'th character of s1 and the j'th character of s2:

      Deletion:
        Find levenshteinDistance() of s1[0...(i-1)] => s2[0...j]
        i'th character of s1 is deleted

      Insertion:
        Find levenshteinDistance() of s1[0...i] => s2[0...(j-1)]
        We then insert s2[j] into s2 to regain s2[0...j]
      
      Substitution:
        Find levenshteinDistance() of s1[0...(i-1)] => s2[0...(j-1)]
        We then insert s2[j] as i'th character of s1 effectively substituting it
    */
    int delete = levenshteinDistance(s1, s1Index - 1, s2, s2Index, opt);
    int insert = levenshteinDistance(s1, s1Index, s2, s2Index - 1, opt);
    int substitute = levenshteinDistance(s1, s1Index - 1, s2, s2Index - 1, opt);

    /*
      We want to take the minimum of these 3 options to fix the problem (we add
      1 to the min cost action to symbolize performing the operation)
    */
    opt[s1Index][s2Index] = 1 + Math.min(delete , Math.min(insert, substitute));
  }

  return opt[s1Index][s2Index];
}
