public class NearestRepeatedEntries {
  public static void main(String args[]) {
    String[] sentence = new String[]{
      "This",
      "is",
      "a",
      "sentence",
      "with",
      "is",
      "repeated",
      "then",
      "repeated"
    };

    System.out.println("Distance between closest repeated entries: " + distanceOfClosestRepeatedEntries(sentence));
  }

  private static int distanceOfClosestRepeatedEntries(String[] sentence) {
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
