/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  class Trie {
    static final int ALPHABET_SIZE = 26;
    TrieNode root;

    public Trie() {
      root = new TrieNode('#'); // dummy root - we won't use this value
    }

    // Inserts a word into the trie
    public void insert(String word) {
      TrieNode curr = root;

      for (int level = 0; level < word.length(); level++) {
        char nextCharacter = word.charAt(level);
        int accessIndex = nextCharacter - 'a';

        if (curr.adjacents[accessIndex] == null) {
          curr.adjacents[accessIndex] = new TrieNode(nextCharacter);
        }

        curr = curr.adjacents[accessIndex]; // Move to the next character
      }

      // The last node we still have reference to ends the word
      curr.endsWord = true;
    }

    // Returns if the word is in the trie
    public boolean search(String word) {
      TrieNode curr = root;

      for (int level = 0; level < word.length(); level++) {
        char nextCharacter = word.charAt(level);
        int accessIndex = nextCharacter - 'a';

        // Dead-end/bad character path, return false
        if (curr.adjacents[accessIndex] == null) {
          return false;
        }

        curr = curr.adjacents[accessIndex];
      }

      /*
      * We could walk the whole word, but it may still be a prefix to some larger
      * word in the trie. Ensure that this is actually the terminating character of a
      * word previously inserted into the trie.
      */
      return curr.endsWord;
    }

    // Returns if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
      TrieNode curr = root;

      for (int level = 0; level < prefix.length(); level++) {
        char nextCharacter = prefix.charAt(level);
        int accessIndex = nextCharacter - 'a';

        if (curr.adjacents[accessIndex] == null) {
          return false;
        }

        curr = curr.adjacents[accessIndex];
      }

      // We could walk the whole prefix in the trie
      return true;
    }

    private class TrieNode {
      char character;
      TrieNode[] adjacents;
      boolean endsWord;

      public TrieNode(char character) {
        this.character = character;
        adjacents = new TrieNode[ALPHABET_SIZE];
        endsWord = false;
      }
    }
  }
}

/*
  Nodes are characters and edges indicate all possible characters
  that can come after a given character.

  Only has provision to handle letters a-z.
*/
