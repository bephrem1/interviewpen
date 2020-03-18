/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

public class Solution {
  public static void main(String args[]) {
    Set<String> access = new HashSet<>(Arrays.asList(
      "B",
      "D"
    ));

    List<String[]> folders = new ArrayList<>(Arrays.asList(
      new String[]{ "A", null },
      new String[]{ "B", "A" },
      new String[]{ "C", "A" },
      new String[]{ "F", "B" },
      new String[]{ "D", "C" },
      new String[]{ "E", "C" },
      new String[]{ "G", "F" },
      new String[]{ "H", "F" }
    ));

    /*
      A
      |
      -- B
         | -- F
         |    | -- G
         |    | -- H
         |
         |
         C
         | -- D
         | -- E
    */

    Map<String, String> nodeToParent = precomputeParentMapping(folders);

    String[] folderNames = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H" };

    for (String folder: folderNames) {
      System.out.println("Can we access folder " + folder + "? " + hasAccess(folder, access, folders, nodeToParent));
    }
  }

  private static boolean hasAccess(
    String folder,
    Set<String> access,
    List<String[]> folders,
    Map<String, String> nodeToParent
  ) {
    String curr = folder;

    while (curr != null) {
      if (access.contains(curr)) {
        return true;
      }

      curr = nodeToParent.get(curr);
    }

    return false;
  }

  private static Map<String, String> precomputeParentMapping(List<String[]> folders) {
    Map<String, String> nodeToParent = new HashMap<>();

    // In a tree every node can only have 1 parent, but multiple children
    for (String[] edge: folders) {
      nodeToParent.put(edge[0], edge[1]);
    }

    return nodeToParent;
  }
}
