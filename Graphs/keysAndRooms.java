/*
  Keys And Rooms - LeetCode: https://leetcode.com/problems/keys-and-rooms/

  This code passes all Leetcode test cases as of Aug. 31 2019
  Runtime: 2 ms, faster than 53.28% of Java online submissions for Keys and Rooms.
*/

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
      }
    }
  }
  
  return seen.size() == rooms.size();
}
