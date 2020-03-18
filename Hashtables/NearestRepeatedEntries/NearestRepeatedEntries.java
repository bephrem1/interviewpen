/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int distanceOfClosestRepeatedEntries(String[] sentence) {
    Map<String, Integer> wordToIndexLastSeenAt = new HashMap<>();
    int nearestRepeatedEntryDistance = Integer.MAX_VALUE;

    for (int i = 0; i < sentence.length; i++) {
      String word = sentence[i];

      if (wordToIndexLastSeenAt.containsKey(word)) {
        int lastAppearanceIndex = wordToIndexLastSeenAt.get(word);
        int distanceToLastAppearance = i - lastAppearanceIndex;

        nearestRepeatedEntryDistance = Math.min(nearestRepeatedEntryDistance, distanceToLastAppearance);
      }

      wordToIndexLastSeenAt.put(word, i);
    }

    return nearestRepeatedEntryDistance == Integer.MAX_VALUE ? -1 : nearestRepeatedEntryDistance;
  }
}
