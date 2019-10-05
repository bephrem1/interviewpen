/*
  Keep track of the most visited pages given server log
  files notating pages accessed.

  This is an online algorithm so repeated calls with new log entries
  should be accounted for & querying the top k most visited entries
  should remain as fast as possible.
*/
import java.util.*;

class MostVisitedPages {
  public static void main(String args[]) {
    LogProcessor logProcessor = new LogProcessor();

    String[] pagesVisited = new String[]{
      "a", "b", "c", "d", "e", "f", "g", "h", "i",
      "a", "a", "a", "a", "b", "b", "b", "f", "f",
      "g", "h", "j"
    };

    for (String page: pagesVisited) {
      logProcessor.insert(page);
    }

    logProcessor.topKVisitedPages(3);
    logProcessor.topKVisitedPages(4);
    logProcessor.topKVisitedPages(6);
  }

  private static class LogProcessor {
    Map<String, Integer> pageIdToOccurrences;
    TreeNode root;

    public LogProcessor() {
      pageIdToOccurrences = new HashMap<>();
    }

    public List<String> topKVisitedPages(int k) {
      
    }

    public void insert(String visitedPageId) {

    }
  }

  private static TreeNode {
    String pageId;
    TreeNode left;
    TreeNode right;

    public TreeNode(String pageId) {
      this.pageId = pageId;
    }
  }
}
