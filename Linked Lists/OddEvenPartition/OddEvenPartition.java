class WithDummyHead {
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode oddDummy = new ListNode(-1);
    ListNode evenDummy = new ListNode(-1);

    ListNode oddTail = oddDummy;
    ListNode evenTail = evenDummy;

    int index = 0;
    ListNode curr = head;
    while (curr != null) {
      if (index % 2 == 0) {
        evenTail.next = curr;
        evenTail = curr;
      } else {
        oddTail.next = curr;
        oddTail = curr;
      }

      curr = curr.next;
      index++;
    }

    evenTail.next = oddDummy.next;
    oddTail.next = null;

    return evenDummy.next;
  }
}

class WithLessPointers {
  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode even = head;
    ListNode odd = head.next;

    ListNode oddHead = odd;

    while (odd != null && odd.next != null) {
      even.next = odd.next;
      even = odd.next;
      odd.next = even.next;
      odd = even.next;
    }
    even.next = oddHead;

    return head;
  }
}
