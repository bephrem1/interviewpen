/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public int distance(String beginWord, String endWord, List<String> wordList) {
    if (!wordList.contains(endWord)) {
      return 0;
    }

    // Initialize the breadth first search
    Queue<String> queue = new LinkedList<String>();
    queue.add(beginWord);
    
    // Mark start word as visited
    Set<String> visited = new HashSet<String>();
    visited.add(beginWord);
    
    // The current level of "hops" we have taken from the start word
    int currentLevel = 1;
    int itemsLeftToProcessInLevel = 1;

    while (!queue.isEmpty()) {
      String word = queue.poll();
      
      // Physically modify each character
      for (int i = 0; i < word.length(); i++) {

        // Convert the string to a char array so we can manipulate characters directly
        char[] chars = word.toCharArray();
        
        for (char character = 'a'; character <= 'z'; character++) {
          // Modify the character at 'i'
          chars[i] = character;
          
          // Rebuild the string, we now have a string 1 'transformation' away
          String transformedWord = new String(chars);
          
          // If this word is a match for the end then we have found a path
          if (transformedWord.equals(endWord)) {
            return currentLevel + 1;
          }
          
          /*
            If the word is in the 'wordList' and has not been visited, then
            add it to the queue to eventually be searched
          */
          if (wordList.contains(transformedWord) && !visited.contains(transformedWord)) {
            queue.add(transformedWord);
            visited.add(transformedWord);
          }
        }
      }
    
      // 1 less item to process in the current level
      itemsLeftToProcessInLevel--;

      /*
        If we have just processed all of the items in the current level, the
        items in the queue represent all of the items in the next level.

        Get the queue's size (which is the size of the next level), set our state,
        and increment the level.
      */
      if (itemsLeftToProcessInLevel == 0) {
        currentLevel++;
        itemsLeftToProcessInLevel = queue.size();
      }
    }

    return 0;
  }
}
