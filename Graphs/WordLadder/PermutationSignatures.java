/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int distance(String beginWord, String endWord, List<String> wordList) {
    /*
      Maps each uniquely identified "jump identifier" to all of the words in wordList
      that are reachable from that idendifier
    */
    HashMap<String, List<String>> identifierToReachableWords = initializeTransformationsMapping(wordList);

    // Set up the breadth first search
    Queue<Node> queue = new LinkedList<>();
    queue.add(new Node(beginWord, 1));

    // Track "nodes" that we have already visited
    Set<String> visited = new HashSet<>();
    visited.add(beginWord);

    while (!queue.isEmpty()) {
      Node node = queue.remove();
      int distance = node.distance;

      String word = node.word;
      int wordLength = word.length();

      // Try each possible transformation
      for (int i = 0; i < wordLength; i++) {
        // A "permutationIdentifier" to represent all "distance 1" transformations from "word"
        String permutationIdentifier = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);

        // Get all of the reachable words, were we to replace character at index 'i' with anything
        List<String> reachableWords = identifierToReachableWords.getOrDefault(permutationIdentifier, new ArrayList<>());

        // Process each word that can be reached via this transformation
        for (String reachableWord: reachableWords) {
          // If the word that can be reached return +1 our current distance to not actually do the hop
          if (reachableWord.equals(endWord)) {
            return distance + 1;
          }

          /*
            "reachableWord" doesn't match the "endWord". Mark it as visited &
            add it to the queue to be eventually searched.
          */
          if (!visited.contains(reachableWord)) {
            visited.add(reachableWord);
            queue.add(new Node(reachableWord, distance + 1));
          }
        }
      }
    }

    return 0; // No path found
  }

  private HashMap<String, List<String>> initializeTransformationsMapping(List<String> wordList) {
    HashMap<String, List<String>> identifierToReachableWords = new HashMap<>();

    /*
      Examples:

      startWord - "hit"
      endWord - "cog"
      wordList - [ "hot", "dot", "dog", "lot", "log", "cog" ]

      Table Output:
      {
        *og=[dog, log, cog],
        d*g=[dog],
        do*=[dot, dog],
        d*t=[dot],
        c*g=[cog],
        co*=[cog],
        h*t=[hot],
        ho*=[hot],
        l*t=[lot],
        lo*=[lot, log],
        *ot=[hot, dot, lot],
        l*g=[log]
      }

      Each * abstracts the idea of character replacement away.
      
      So if we have "dog" and "log", if we have the ability to change the first character both "dog"
      and "log" will be able to reach any word that has any first character, then "og".

      We represent that "any character" with a wildcard "*" and end with what cannot change, the "og",
      yielding a signature "*og".

      If we know all of the words that can be reached from that "*og" state, we know all of the "nodes"
      (words) we can reach from "dog" or "log" by removing the first character without doing the actual
      simulation itself.
    */
    for (String word: wordList) {
      int wordLength = word.length();

      // Generate all permutation id's from the "word" (pulled from "wordList")
      for (int i = 0; i < wordLength; i++) {

        // Generate the identifier - examples for "dog" are "*og" - "d*g" - "do*"
        String permutationIdentifier = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);

        // Get all transformations that can come off the "node" that that identifier represents
        List<String> reachableWords = identifierToReachableWords.getOrDefault(permutationIdentifier, new ArrayList<>());

        /*
          Add the word to that identifier's list (since 'word' can be reached from that state)
          & update the list that the identifier maps to in the hashtable
        */
        reachableWords.add(word);
        identifierToReachableWords.put(permutationIdentifier, reachableWords);
      }
    }

    return identifierToReachableWords;
  }

  private class Node {
    String word;
    int distance;

    public Node(String word, int distance) {
      this.word = word;
      this.distance = distance;
    }
  }
}
