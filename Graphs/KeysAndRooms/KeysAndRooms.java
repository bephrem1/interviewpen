/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> seen = new HashSet<>();
    
    queue.add(0);
    seen.add(0);
    
    while (!queue.isEmpty()) {
      int currentRoom = queue.poll();
      List<Integer> roomsUnlocked = rooms.get(currentRoom);
          
      for (int unlockedRoom: roomsUnlocked) {
        if (!seen.contains(unlockedRoom)) {
          seen.add(unlockedRoom);
          queue.add(unlockedRoom);

          // Short circuit search if all rooms opened
          if (rooms.size() == seen.size()) {
            return true;
          }
        }
      }
    }
    
    return seen.size() == rooms.size();
  }
}
