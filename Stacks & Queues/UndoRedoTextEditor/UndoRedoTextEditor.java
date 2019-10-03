/*
  A rudimentary implementation for undo/redo using stacks to track undo & redo
  history during text editing.
*/
import java.util.*;

class Main {
  public static void main(String args[]) {
    Action[] actions = new Action[]{
      new Action('a', ActionType.INSERT), // "a"
      new Action('b', ActionType.INSERT), // "ab"
      new Action(ActionType.DELETE), // "a"
      new Action('d', ActionType.INSERT), // "ad"
      new Action(ActionType.UNDO), // "a"
      new Action(ActionType.REDO),  // "ad"
      new Action(ActionType.REDO), // "ad" nothing to redo
      new Action(ActionType.REDO), // "ad" nothing to redo
      new Action(ActionType.UNDO), // "a"
      new Action(ActionType.REDO)  // "ad"
    };

    System.out.println(performEditorActions(actions));
  }

  private static String performEditorActions(Action[] actions) {
    StringBuilder sb = new StringBuilder();

    Stack<Action> undo = new Stack<>(); // Track the most recent actions that we want to undo
    Stack<Action> redo = new Stack<>();

    for (Action a: actions) {
      switch (a.type) {
        case INSERT:
          performInsert(a, undo, sb);
          redo.clear();
          break;

        case DELETE:
          performDelete(a, undo, sb);
          redo.clear();
          break;

        case UNDO:
          Action undoAction = undo.pop();

          if (undoAction.type == ActionType.INSERT) {
            performInsert(undoAction, redo, sb);
          } else if (undoAction.type == ActionType.DELETE) {
            performDelete(undoAction, redo, sb);
          }
          break;

        case REDO:
          if (redo.size() != 0) {
            Action redoAction = redo.pop();

            if (redoAction.type == ActionType.INSERT) {
              performInsert(redoAction, undo, sb);
            } else if (redoAction.type == ActionType.DELETE) {
              performDelete(redoAction, undo, sb);
            }
          }
          break;
      }
    }

    return sb.toString();
  }

  private static void performInsert(Action a, Stack<Action> destinationStack, StringBuilder sb) {
    sb.append(a.character);

    Action oppositeAction = new Action(ActionType.DELETE);
    destinationStack.push(oppositeAction);
  }

  private static void performDelete(Action a, Stack<Action> destinationStack, StringBuilder sb) {
    int indexToRemove = sb.length() - 1;
    char character = sb.charAt(indexToRemove);

    sb.deleteCharAt(indexToRemove);

    Action oppositeAction = new Action(character, ActionType.INSERT);
    destinationStack.push(oppositeAction);
  }

  private static class Action {
    Character character;
    ActionType type;

    public Action(Character character, ActionType type) {
      this.character = character;
      this.type = type;
    }

    public Action(ActionType type) {
      this(null, type);
    }
  }

  private static enum ActionType {
    INSERT, // Insert the character
    DELETE, // Delete the most recently added character
    UNDO, // Undo the most recent action
    REDO // Redo the most recent undo
  }
}
