/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public List<String> restoreIpAddresses(String rawIpString) {
    List<String> restoredIps = new ArrayList<>();
    restoreIps(0, 0, new int[4], rawIpString, restoredIps);
    return restoredIps;
  }

  private void restoreIps(
    int progressIndex,
    int currentSegment,
    int[] ipAddressSegments,
    String rawIpString,
    List<String> restoredIps
  ) {
    /*
      If we have filled 4 segments (0, 1, 2, 3) and we are on the 4th,
      we will only record an answer if the string was decomposed fully
    */
    if (currentSegment == 4 && progressIndex == rawIpString.length()) {
      restoredIps.add(buildIpStringFromSegments(ipAddressSegments));
    } else if (currentSegment == 4) {
      return;
    }

    /*
      Generate segments to try, a segment can be 1 to 3 digits long.
    */
    for (int segLength = 1; segLength <= 3 && progressIndex + segLength <= rawIpString.length(); segLength++) {

      // Calculate 1 index past where the segment ends index-wise in the original raw ip string
      int onePastSegmentEnd = progressIndex + segLength;

      // Extract int value from our snapshot from the raw ip string
      String segmentAsString = rawIpString.substring(progressIndex, onePastSegmentEnd);
      int segmentValue = Integer.parseInt(segmentAsString);

      // Check the "snapshot's" validity - if invalid break iteration
      if (segmentValue > 255 || segLength >= 2  && segmentAsString.charAt(0) == '0') {
        break;
      }

      // Add the extracted segment to the working segments
      ipAddressSegments[currentSegment] = segmentValue;

      // Recurse on the segment choice - when finished & we come back here, the next loop iteration will try another segment
      restoreIps(progressIndex + segLength, currentSegment + 1, ipAddressSegments, rawIpString, restoredIps);
    }
  }

  private String buildIpStringFromSegments(int[] ipAddressSegments) {
    return ipAddressSegments[0] + "." + ipAddressSegments[1] + "."+ ipAddressSegments[2] + "." + ipAddressSegments[3];
  }
}
