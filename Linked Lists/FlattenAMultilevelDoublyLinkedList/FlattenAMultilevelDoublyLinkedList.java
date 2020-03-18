/*
  This repository is no longer actively maintained. To find all solutions
  to this problem (and practice coding more problems) at:

  ~~~~~~~~~~~~~~~~~~~~~~~~~
  https://backtobackswe.com
  ~~~~~~~~~~~~~~~~~~~~~~~~~
*/

class Solution {
  public Node flatten(Node head) {
    if (head == null) {
      return head;
    }
    
    // We grab reference to the head of the list to do work with
    Node curr = head;

    /*
      Iterate over the "top level" list and perform flattenings.
      Take note that this is recursive in nature and the "top level"
      is relative to where we are in the recursion
    */
    while (curr != null) {
      // Stash reference to the node after 'curr', we will need it later
      Node nextNode = curr.next;

      /*
        If we have a child list we can flatten, then do so
        and begin rewiring things
      */
      if (curr.child != null) {

        // Point 'prev' of the child list's head to 'curr'
        curr.child.prev = curr;
        
        // Hey recursion, flatten curr's child list & get back to me with the head of the list
        Node flattenedListHead =  flatten(curr.child);
        
        // curr's 'next' value becomes the head of the flattened list
        curr.next = flattenedListHead;
        
        // Flattening is done, so nullify curr's child reference
        curr.child = null;
        
        /*
          We wired up the start of the flattened child list, now we
          need to fix up it's tail

          Update pointers between the last node in the child list and
          the 'nextNode' saved above (since 'nextNode' is where the
          original "top level" list continues)
        */
        if (nextNode != null) {
          Node lastNodeInFlattenedList = getLastNodeInList(flattenedListHead);

          nextNode.prev = lastNodeInFlattenedList;
          lastNodeInFlattenedList.next = nextNode;
        }
      }

      // Advance progress to curr's cached next node
      curr = nextNode;
    }

    return head;
  }

  /*
    Get reference to the last node in the linked list that 'node'
    sits in (we assume there are no cycles)
  */
  private Node getLastNodeInList(Node node) {
    if (node == null) {
      return null;
    }

    Node curr = node;
    while (curr.next != null) {
      curr = curr.next;
    }

    return curr;
  }
}
