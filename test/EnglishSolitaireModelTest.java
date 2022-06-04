import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class to test the methods in the English Solitaire Model. (including their integration)
 */
public class EnglishSolitaireModelTest {

  private MarbleSolitaireModel englishModel1;
  private MarbleSolitaireModel englishModel2;
  private MarbleSolitaireModel englishModel3;
  private MarbleSolitaireModel englishModel4;
  private MarbleSolitaireModel englishModel3b;
  private MarbleSolitaireModel englishModel3c;

  /**
   * Initializing valid game states.
   */
  @Before
  public void init() {
    englishModel1 = new EnglishSolitaireModel();
    englishModel2 = new EnglishSolitaireModel(0, 3); // empty in middle top
    englishModel3 = new EnglishSolitaireModel(5); // arm thickness of 5
    englishModel4 = new EnglishSolitaireModel(5, 6, 7);
    englishModel3b = new EnglishSolitaireModel(7);
    englishModel3c = new EnglishSolitaireModel(9);
  }

  /**
   * Testing the exceptions for the constructor that initializes a game
   * by taking in the position of the empty slot.
   */
  @Test
  public void testInvalidEmptyCellPositionForConstructor2() {

    englishModel1 = null;

    this.checkConstructorException("Invalid empty cell position (0,0)", 0, 0);
    this.checkConstructorException("Invalid empty cell position (0,6)", 0, 6);
    this.checkConstructorException("Invalid empty cell position (6,0)", 6, 0);
    this.checkConstructorException("Invalid empty cell position (6,6)", 6, 6);
    this.checkConstructorException("Invalid empty cell position (1,1)", 1, 1);
    this.checkConstructorException("Invalid empty cell position (1,5)", 1, 5);
    this.checkConstructorException("Invalid empty cell position (5,1)", 5, 1);
    this.checkConstructorException("Invalid empty cell position (5,5)", 5, 5);
    this.checkConstructorException("Invalid empty cell position (100,3)", 100, 3);
    this.checkConstructorException("Invalid empty cell position (-100,3)", -100, 3);
    this.checkConstructorException("Invalid empty cell position (3,100)", 3, 100);
    this.checkConstructorException("Invalid empty cell position (3,-100)", 3, -100);
    this.checkConstructorException("Invalid empty cell position (50,50)", 50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,50)", -50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,-50)",
            -50, -50);

    // Sanity check for handin
    assertNull(englishModel1);

  }

  /**
   * Testing the exceptions for the constructor that initializes a game
   * by taking in the arm thickness.
   */
  @Test
  public void testInvalidScaleForConstructor3() {

    englishModel1 = null;

    this.checkConstructorException("Scale is not a positive number", -3);
    this.checkConstructorException("Scale is not a positive number", -2);
    this.checkConstructorException("Scale is not a positive number", -1);
    this.checkConstructorException("Scale is not a positive number", 0);
    this.checkConstructorException("Scale is not playable", 1);
    this.checkConstructorException("Scale is not an odd number", 2);
    this.checkConstructorException("Scale is not an odd number", 4);
    this.checkConstructorException("Scale is not an odd number", 50);
    this.checkConstructorException("Scale is not a positive number", -50);

    // Sanity check for handin
    assertNull(englishModel1);
  }

  /**
   * Testing the exceptions for the constructor that initializes a game
   * by taking in the arm thickness and the position of the empty slot.
   */
  @Test
  public void testInvalidEmptyCellPositionForConstructor4() {

    englishModel1 = null;

    // for thickness 3
    this.checkConstructorException("Invalid empty cell position (0,0)", 3, 0, 0);
    this.checkConstructorException("Invalid empty cell position (0,6)", 3, 0, 6);
    this.checkConstructorException("Invalid empty cell position (6,0)", 3, 6, 0);
    this.checkConstructorException("Invalid empty cell position (6,6)", 3, 6, 6);
    this.checkConstructorException("Invalid empty cell position (1,1)", 3, 1, 1);
    this.checkConstructorException("Invalid empty cell position (1,5)", 3, 1, 5);
    this.checkConstructorException("Invalid empty cell position (5,1)", 3, 5, 1);
    this.checkConstructorException("Invalid empty cell position (5,5)", 3, 5, 5);
    this.checkConstructorException("Invalid empty cell position (100,3)", 3, 100, 3);
    this.checkConstructorException("Invalid empty cell position (-100,3)",
            3, -100, 3);
    this.checkConstructorException("Invalid empty cell position (3,100)", 3, 3, 100);
    this.checkConstructorException("Invalid empty cell position (3,-100)",
            3, 3, -100);
    this.checkConstructorException("Invalid empty cell position (50,50)", 3, 50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,50)",
            3, -50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,-50)",
            3, -50, -50);

    // for thickness 5
    this.checkConstructorException("Invalid empty cell position (0,0)", 5, 0, 0);
    this.checkConstructorException("Invalid empty cell position (0,12)", 5, 0, 12);
    this.checkConstructorException("Invalid empty cell position (12,0)", 5, 12, 0);
    this.checkConstructorException("Invalid empty cell position (12,12)", 5, 12, 12);
    this.checkConstructorException("Invalid empty cell position (3,3)", 5, 3, 3);
    this.checkConstructorException("Invalid empty cell position (3,9)", 5, 3, 9);
    this.checkConstructorException("Invalid empty cell position (9,3)", 5, 9, 3);
    this.checkConstructorException("Invalid empty cell position (9,9)", 5, 9, 9);
    this.checkConstructorException("Invalid empty cell position (100,6)", 5, 100, 6);
    this.checkConstructorException("Invalid empty cell position (-100,6)",
            5, -100, 6);
    this.checkConstructorException("Invalid empty cell position (6,100)", 5, 6, 100);
    this.checkConstructorException("Invalid empty cell position (6,-100)",
            5, 6, -100);
    this.checkConstructorException("Invalid empty cell position (50,50)", 5, 50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,50)",
            5, -50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,-50)",
            5, -50, -50);

    // for thickness 7
    this.checkConstructorException("Invalid empty cell position (0,0)", 7, 0, 0);
    this.checkConstructorException("Invalid empty cell position (0,19)", 7, 0, 19);
    this.checkConstructorException("Invalid empty cell position (19,0)", 7, 19, 0);
    this.checkConstructorException("Invalid empty cell position (19,19)", 7, 19, 19);
    this.checkConstructorException("Invalid empty cell position (-50,0)", 7, -50, 0);
    this.checkConstructorException("Invalid empty cell position (0,50)", 7, 0, 50);

    // for thickness 9
    this.checkConstructorException("Invalid empty cell position (0,0)", 9, 0, 0);
    this.checkConstructorException("Invalid empty cell position (0,25)", 9, 0, 25);
    this.checkConstructorException("Invalid empty cell position (25,0)", 9, 25, 0);
    this.checkConstructorException("Invalid empty cell position (25,25)", 9, 25, 25);
    this.checkConstructorException("Invalid empty cell position (-50,0)", 9, -50, 0);
    this.checkConstructorException("Invalid empty cell position (0,50)", 9, 0, 50);

    // Sanity check for handin
    assertNull(englishModel1);
  }

  /**
   * Testing the exceptions for the constructor that initializes a game
   * by taking in the arm thickness and the position of the empty slot.
   */
  @Test
  public void testInvalidScaleForConstructor4() {

    englishModel1 = null;

    this.checkConstructorException("Scale is not a positive number", -3, 0, 0);
    this.checkConstructorException("Scale is not a positive number", -2, 0, 0);
    this.checkConstructorException("Scale is not a positive number", -1, 0, 0);
    this.checkConstructorException("Scale is not a positive number", 0, 0, 0);
    this.checkConstructorException("Scale is not playable", 1, 0, 0);
    this.checkConstructorException("Scale is not an odd number", 2, 0, 0);
    this.checkConstructorException("Scale is not an odd number", 4, 0, 0);
    this.checkConstructorException("Scale is not an odd number", 50, 0, 0);
    this.checkConstructorException("Scale is not a positive number", -50, 0, 0);
    this.checkConstructorException("Scale is not an odd number", 50, 50, 50);
    this.checkConstructorException("Scale is not a positive number", -50, 50, -50);

    // Sanity check for handin
    assertNull(englishModel1);
  }

  // Helper to test constructor exception
  private void checkConstructorException(String exe,
                                         int... parameters) {
    try {
      if (parameters.length == 1) {
        englishModel1 = new EnglishSolitaireModel(parameters[0]);
      } else if (parameters.length == 2) {
        englishModel1 = new EnglishSolitaireModel(parameters[0], parameters[1]);
      } else if (parameters.length == 3) {
        englishModel1 = new EnglishSolitaireModel(parameters[0], parameters[1], parameters[2]);
      } else {
        throw new IllegalArgumentException("Incorrect number of parameters inputted");
      }
      fail("Reason: Constructor didn't throw an exception - even when it should");
    } catch (IllegalArgumentException e) {
      assertEquals(exe, e.getMessage());
    }
  }

  /**
   * Testing a valid move on different constructors and thicknesses.
   */
  @Test
  public void move() {
    // For Constructor 1 (Thickness 3): Move up
    checkLegalMove(englishModel1, 5, 3, 3, 3);

    // For Constructor 1 (Thickness 3): Move down
    checkLegalMove(englishModel1, 2, 3, 4, 3);

    // For Constructor 1 (Thickness 3): Move right
    checkLegalMove(englishModel1, 3, 1, 3, 3);

    // For Constructor 1 (Thickness 3): Move left
    checkLegalMove(englishModel1, 3, 4, 3, 2);

    // For Constructor 4 (Thickness 5): Move right
    checkLegalMove(englishModel4, 6, 5, 6, 7);

    // For Constructor 4 (Thickness 5): Move left
    checkLegalMove(englishModel4, 6, 8, 6, 6);

    // For Constructor 4 (Thickness 5): Move up
    checkLegalMove(englishModel4, 8, 7, 6, 7);

    // For Constructor 4 (Thickness 5): Move down
    checkLegalMove(englishModel4, 5, 7, 7, 7);

    // For Constructor 3 (Thickness 7): Move left
    checkLegalMove(englishModel3b, 9, 11, 9, 9);

    // For Constructor 3 (Thickness 7): Move right
    checkLegalMove(englishModel3b, 9, 8, 9, 10);

    // For Constructor 3 (Thickness 7): Move up
    checkLegalMove(englishModel3b, 11, 9, 9, 9);

    // For Constructor 3 (Thickness 7): Move down
    checkLegalMove(englishModel3b, 7, 8, 9, 8);

    // sanity check for handin
    assertNotEquals(null, englishModel1);
    assertNotEquals(null, englishModel4);

  }

  // Helper to test if the move method actually works by checking states before and after the move
  private void checkLegalMove(MarbleSolitaireModel model,
                              int fromRow, int fromCol, int toRow, int toCol) {
    int middleRow = (fromRow + toRow) / 2;
    int middleCol = (fromCol + toCol) / 2;

    // The slot we want to move from has a marble
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(fromRow, fromCol));
    // The slot in between has a marble
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(middleRow, middleCol));
    // The slot we want to move to does not have a marble
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(toRow, toCol));
    // Now let's move
    model.move(fromRow, fromCol, toRow, toCol);
    // The slot we moved from should not have a marble
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(fromRow, fromCol));
    // The slot in between should not have a marble
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            model.getSlotAt(middleRow, middleCol));
    // The slot we want to move to has a marble
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            model.getSlotAt(toRow, toCol));
  }

  /**
   * Now we test invalid moves with one parameter that doesn't work and others that do
   * in order to thoroughly test. (for size 3)
   */
  @Test
  public void testInvalidMovesForSize3() {
    // Outside the board to outside the board
    this.checkModelIllegalArgumentException(englishModel1, 50, 50, 52, 50);
    this.checkModelIllegalArgumentException(englishModel1, -50, 50, 52, -50);

    // Allows us to test more
    englishModel1.move(3, 5, 3, 3);

    // Outside the board to inside the board
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(3, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 5));
    this.checkModelIllegalArgumentException(englishModel1, 3, 7, 3, 5);

    // Inside the board to outside the board
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(3, 6));
    this.checkModelIllegalArgumentException(englishModel1, 2, 5, 2, 7);

    // Allows us to test more
    englishModel1.move(3, 2, 3, 4);

    // From empty slot
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(3, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 5));
    this.checkModelIllegalArgumentException(englishModel1, 3, 3, 3, 5);

    // From Invalid slot
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            englishModel1.getSlotAt(1, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(2, 5));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 5));
    this.checkModelIllegalArgumentException(englishModel1, 1, 5, 3, 5);

    // To slot with Marble
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(3, 4));
    this.checkModelIllegalArgumentException(englishModel1, 5, 4, 3, 4);

    // To slot with Invalid
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(3, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(2, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            englishModel1.getSlotAt(1, 6));
    this.checkModelIllegalArgumentException(englishModel1, 3, 6, 1, 6);

    // Slot with Invalid in middle cannot be tested

    // Slot with Empty in the middle
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 3));
    this.checkModelIllegalArgumentException(englishModel1, 3, 1, 3, 3);

    // Move to slot 3 spaces away with 2 marbles in between
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(6, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(5, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 2));
    this.checkModelIllegalArgumentException(englishModel1, 6, 2, 3, 2);

    // Move to same position
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(6, 2));
    this.checkModelIllegalArgumentException(englishModel1, 6, 2, 6, 2);

    // Move to position 1 slot away
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(4, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 2));
    this.checkModelIllegalArgumentException(englishModel1, 4, 2, 3, 2);

    // Move diagonally
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(5, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel1.getSlotAt(3, 2));
    this.checkModelIllegalArgumentException(englishModel1, 5, 4, 3, 2);
  }

  /**
   * Now we test invalid moves with one parameter that doesn't work and others that do
   * in order to thoroughly test. (for size 5) We test a different size to see if the move is
   * scalable.
   */
  @Test
  public void testInvalidMovesForSize5() {
    // Outside the board to outside the board
    this.checkModelIllegalArgumentException(englishModel4, 50, 50, 52, 50);
    this.checkModelIllegalArgumentException(englishModel4, -50, 50, 52, -50);

    // Allows us to test more
    englishModel4.move(6, 9, 6, 7);
    englishModel4.move(6, 11, 6, 9);

    // Outside the board to inside the board
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(6, 12));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(6, 11));
    this.checkModelIllegalArgumentException(englishModel4, 6, 13, 6, 11);

    // Inside the board to outside the board
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(7, 11));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(7, 12));
    this.checkModelIllegalArgumentException(englishModel4, 7, 11, 7, 13);

    // From empty slot
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(6, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(6, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(6, 10));
    this.checkModelIllegalArgumentException(englishModel4, 6, 8, 6, 10);

    // Allows us to test more
    englishModel4.move(4, 10, 6, 10);
    englishModel4.move(4, 8, 4, 10);

    // From Invalid slot
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            englishModel4.getSlotAt(3, 10));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(4, 10));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(5, 10));
    this.checkModelIllegalArgumentException(englishModel4, 3, 10, 5, 10);

    // To slot with Marble
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(4, 12));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(4, 11));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(4, 10));
    this.checkModelIllegalArgumentException(englishModel4, 4, 12, 4, 10);

    // To slot with Invalid
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(5, 11));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(4, 11));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            englishModel4.getSlotAt(3, 11));
    this.checkModelIllegalArgumentException(englishModel4, 5, 11, 3, 11);

    // Slot with Invalid in middle cannot be tested

    // Slot with Empty in the middle
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(4, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(4, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(4, 9));
    this.checkModelIllegalArgumentException(englishModel4, 4, 7, 4, 9);

    // Move to slot 3 spaces away with 2 marbles in between
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(5, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(5, 8));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(5, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(5, 10));
    this.checkModelIllegalArgumentException(englishModel4, 5, 7, 5, 10);

    // Move to same position
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(6, 2));
    this.checkModelIllegalArgumentException(englishModel4, 6, 2, 6, 2);

    // Move to position 1 slot away
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(6, 9));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(6, 8));
    this.checkModelIllegalArgumentException(englishModel4, 6, 9, 6, 8);

    // Move diagonally
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(8, 6));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            englishModel4.getSlotAt(7, 7));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            englishModel4.getSlotAt(6, 8));
    this.checkModelIllegalArgumentException(englishModel4, 8, 6, 6, 8);
  }

  // Helper to test Invalid move exception
  private void checkModelIllegalArgumentException(MarbleSolitaireModel model,
                                                  int fromRow, int fromCol, int toRow, int toCol) {
    try {
      model.move(fromRow, fromCol, toRow, toCol);
      fail("Reason: We don't fail to construct - even when we should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid Move", e.getMessage());
    }
  }

  /**
   * Makes moves that will lead to different configurations of game over and tests if it works.
   * Keep a track of the score as you go. We will test this on all 4 constructors.
   */
  @Test
  public void playGames() {
    // test if there are more than 1 pegs left but game is over because no legal moves left
    // (game over because we lost) // Constructor 1
    int [][] movesToLoseModel1 = new int[][]{
            {5, 3, 3, 3},
            {2, 3, 4, 3},
            {0, 3, 2, 3},
            {3, 1, 3, 3},
            {3, 4, 3, 2},
            {3, 6, 3, 4}
    };
    playGameWithMoves(englishModel1, movesToLoseModel1);
    assertTrue(englishModel1.isGameOver());
    assertEquals(26, englishModel1.getScore());

    this.init();
    // test if there is 1 peg left (game over because we won) // Constructor 1
    int [][] movesToWinModel1 = new int[][]{
            {1, 3, 3, 3},
            {2, 5, 2, 3},
            {0, 4, 2, 4},
            {0, 2, 0, 4},
            {3, 4, 1, 4},
            {0, 4, 2, 4},
            {5, 4, 3, 4},
            {4, 6, 4, 4},
            {2, 6, 4, 6},
            {4, 3, 4, 5},
            {4, 6, 4, 4},
            {4, 1, 4, 3},
            {6, 2, 4, 2},
            {6, 4, 6, 2},
            {3, 2, 5, 2},
            {6, 2, 4, 2},
            {1, 2, 3, 2},
            {2, 0, 2, 2},
            {4, 0, 2, 0},
            {2, 3, 2, 1},
            {2, 0, 2, 2},
            {4, 3, 4, 5},
            {4, 5, 2, 5},
            {2, 5, 2, 3},
            {2, 3, 2, 1},
            {2, 1, 4, 1},
            {4, 1, 4, 3},
            {3, 3, 3, 1},
            {5, 3, 3, 3},
            {3, 4, 3, 2},
            {3, 1, 3, 3}
    };
    playGameWithMoves(englishModel1, movesToWinModel1);
    assertTrue(englishModel1.isGameOver());
    assertEquals(1, englishModel1.getScore());

    this.init();
    // test if there are more than 1 pegs left, and you can make moves, the game is not over
    // Constructor 3
    int [][] movesToLoseModel3 = new int[][]{
            {6, 8, 6, 6},
            {4, 7, 6, 7},
            {2, 7, 4, 7},
            {0, 7, 2, 7}
    };
    playGameWithMoves(englishModel3, movesToLoseModel3);
    assertFalse(englishModel4.isGameOver());
    assertEquals(32, englishModel1.getScore());
  }

  // Helper to test if the score is changing as we play moves, and to check if we can play moves
  // when the game is not over
  private void playGameWithMoves(MarbleSolitaireModel model, int[][] arr) {
    int size = (model.getBoardSize() + 2) / 3;
    int count = (5 * size * size) - (4 * size) - 1;
    for (int[] elem : arr) {
      if (elem.length != 4) {
        throw new IllegalArgumentException("Need exactly 2 parameters to play a move");
      }
      assertEquals(count, model.getScore());
      assertFalse(model.isGameOver());
      model.move(elem[0], elem[1], elem[2], elem[3]);
      count -= 1;
    }
  }

  /**
   * We want to check if we can get the correct size of the board.
   * The size is the height of the vertical strip.
   */
  @Test
  public void testGetBoardSize() {
    // The largest size will be 3*(arm length) - 2 because of the 1 block overlap
    assertEquals(7, englishModel1.getBoardSize()); // Constructor 1
    assertEquals(7, englishModel2.getBoardSize()); // Constructor 2
    assertEquals(13, englishModel3.getBoardSize()); // Constructor 3
    assertEquals(19, englishModel3b.getBoardSize());
    assertEquals(25, englishModel3c.getBoardSize());
    assertEquals(13, englishModel4.getBoardSize()); // Constructor 4

    // We will now do fuzzy testing to test this on different odd boards
    Random r = new Random(100);
    for (int i = 0; i < 50; i += 1) {
      int armL = r.nextInt(1000) * 2 + 3;
      int boardSize = 3 * (armL) - 2;
      englishModel3 = new EnglishSolitaireModel(armL); // constructor 3
      englishModel4 = new EnglishSolitaireModel(armL, armL + armL / 2 - 1,
              armL + armL / 2 - 1); // constructor 4
      assertEquals(boardSize, englishModel3.getBoardSize());
      assertEquals(boardSize, englishModel4.getBoardSize());
    }
  }

  /**
   * Test the getSlotAt() method for different configurations of the board.
   */
  @Test
  public void getSlotAt() {
    // Testing if it works for all positions in an arm thickness 3 board (Constructor 1)
    int[][] invalidForModel1 = new int[][]{
            {0, 0}, {0, 1}, {1, 0}, {1, 1}, // top left
            {0, 5}, {0, 6}, {1, 5}, {1, 6}, // top right
            {5, 0}, {5, 1}, {6, 0}, {6, 1}, // bottom left
            {5, 5}, {5, 6}, {6, 5}, {6, 6}}; // bottom right
    getAllSlots(englishModel1, invalidForModel1, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForModel1 = new int[][]{
            {0, 2}, {0, 3}, {0, 4}, // row 0
            {1, 2}, {1, 3}, {1, 4}, // row 1
            {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, // row 2
            {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5}, {3, 6}, // row 3
            {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}, {4, 6}, // row 4
            {5, 2}, {5, 3}, {5, 4}, // row 5
            {6, 2}, {6, 3}, {6, 4}}; // row 6
    getAllSlots(englishModel1, marbleForModel1, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForModel1 = new int[][]{
            {3, 3}};
    getAllSlots(englishModel1, emptyForModel1, MarbleSolitaireModelState.SlotState.Empty);

    // Testing if it works for all positions in an arm thickness 3 board (Constructor 2)
    int[][] invalidForModel2 = new int[][]{
            {0, 0}, {0, 1}, {1, 0}, {1, 1}, // top left
            {0, 5}, {0, 6}, {1, 5}, {1, 6}, // top right
            {5, 0}, {5, 1}, {6, 0}, {6, 1}, // bottom left
            {5, 5}, {5, 6}, {6, 5}, {6, 6}}; // bottom right
    getAllSlots(englishModel2, invalidForModel2, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForModel2 = new int[][]{
            {0, 2}, {0, 4}, // row 0
            {1, 2}, {1, 3}, {1, 4}, // row 1
            {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}, {2, 5}, {2, 6}, // row 2
            {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}, {3, 5}, {3, 6}, // row 3
            {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}, {4, 6}, // row 4
            {5, 2}, {5, 3}, {5, 4}, // row 5
            {6, 2}, {6, 3}, {6, 4}}; // row 6
    getAllSlots(englishModel2, marbleForModel2, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForModel2 = new int[][]{
            {0, 3}};
    getAllSlots(englishModel2, emptyForModel2, MarbleSolitaireModelState.SlotState.Empty);

    // Testing if it works for all positions in an arm thickness 5 board (Constructor 3)
    int[][] invalidForModel3 = new int[][]{
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 9}, {0, 10}, {0, 11}, {0, 12}, // row 0
            {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 9}, {1, 10}, {1, 11}, {1, 12}, // row 1
            {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 9}, {2, 10}, {2, 11}, {2, 12}, // row 2
            {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 9}, {3, 10}, {3, 11}, {3, 12}, // row 3
            {9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 9}, {9, 10}, {9, 11}, {9, 12}, // row 9
            {10, 0}, {10, 1}, {10, 2}, {10, 3}, {10, 9}, {10, 10}, {10, 11}, {10, 12}, // row 10
            {11, 0}, {11, 1}, {11, 2}, {11, 3}, {11, 9}, {11, 10}, {11, 11}, {11, 12}, // row 11
            {12, 0}, {12, 1}, {12, 2}, {12, 3}, {12, 9}, {12, 10}, {12, 11}, {12, 12}}; // row 12
    getAllSlots(englishModel3, invalidForModel3, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForModel3 = new int[][]{
            {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, // row 0
            {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8}, // row 1
            {2, 4}, {2, 5}, {2, 6}, {2, 7}, {2, 8}, // row 2
            {3, 4}, {3, 5}, {3, 6}, {3, 7}, {3, 8}, // row 3
            {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}, {4, 6}, {4, 7}, {4, 8}, {4, 9}, {4, 10},
            {4, 11}, {4, 12}, // row 4
            {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}, {5, 6}, {5, 7}, {5, 8}, {5, 9}, {5, 10},
            {5, 11}, {5, 12}, // row 5
            {6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 7}, {6, 8}, {6, 9}, {6, 10},
            {6, 11}, {6, 12}, // row 6
            {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}, {7, 10},
            {7, 11}, {7, 12}, // row 7
            {8, 0}, {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5}, {8, 6}, {8, 7}, {8, 8}, {8, 9}, {8, 10},
            {8, 11}, {8, 12}, // row 8
            {9, 4}, {9, 5}, {9, 6}, {9, 7}, {9, 8}, // row 9
            {10, 4}, {10, 5}, {10, 6}, {10, 7}, {10, 8}, // row 10
            {11, 4}, {11, 5}, {11, 6}, {11, 7}, {11, 8}, // row 11
            {12, 4}, {12, 5}, {12, 6}, {12, 7}, {12, 8}}; // row 12
    getAllSlots(englishModel3, marbleForModel3, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForModel3 = new int[][]{
            {6, 6}};
    getAllSlots(englishModel3, emptyForModel3, MarbleSolitaireModelState.SlotState.Empty);

    // Testing if it works for all positions in an arm thickness 5 board (Constructor 4)
    int[][] invalidForModel4 = new int[][]{
            {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 9}, {0, 10}, {0, 11}, {0, 12}, // row 0
            {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 9}, {1, 10}, {1, 11}, {1, 12}, // row 1
            {2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 9}, {2, 10}, {2, 11}, {2, 12}, // row 2
            {3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 9}, {3, 10}, {3, 11}, {3, 12}, // row 3
            {9, 0}, {9, 1}, {9, 2}, {9, 3}, {9, 9}, {9, 10}, {9, 11}, {9, 12}, // row 9
            {10, 0}, {10, 1}, {10, 2}, {10, 3}, {10, 9}, {10, 10}, {10, 11}, {10, 12}, // row 10
            {11, 0}, {11, 1}, {11, 2}, {11, 3}, {11, 9}, {11, 10}, {11, 11}, {11, 12}, // row 11
            {12, 0}, {12, 1}, {12, 2}, {12, 3}, {12, 9}, {12, 10}, {12, 11}, {12, 12}}; // row 12
    getAllSlots(englishModel4, invalidForModel4, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForModel4 = new int[][]{
            {0, 4}, {0, 5}, {0, 6}, {0, 7}, {0, 8}, // row 0
            {1, 4}, {1, 5}, {1, 6}, {1, 7}, {1, 8}, // row 1
            {2, 4}, {2, 5}, {2, 6}, {2, 7}, {2, 8}, // row 2
            {3, 4}, {3, 5}, {3, 6}, {3, 7}, {3, 8}, // row 3
            {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}, {4, 5}, {4, 6}, {4, 7}, {4, 8}, {4, 9}, {4, 10},
            {4, 11}, {4, 12}, // row 4
            {5, 0}, {5, 1}, {5, 2}, {5, 3}, {5, 4}, {5, 5}, {5, 6}, {5, 7}, {5, 8}, {5, 9}, {5, 10},
            {5, 11}, {5, 12}, // row 5
            {6, 0}, {6, 1}, {6, 2}, {6, 3}, {6, 4}, {6, 5}, {6, 6}, {6, 8}, {6, 9}, {6, 10},
            {6, 11}, {6, 12}, // row 6
            {7, 0}, {7, 1}, {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}, {7, 10},
            {7, 11}, {7, 12}, // row 7
            {8, 0}, {8, 1}, {8, 2}, {8, 3}, {8, 4}, {8, 5}, {8, 6}, {8, 7}, {8, 8}, {8, 9}, {8, 10},
            {8, 11}, {8, 12}, // row 8
            {9, 4}, {9, 5}, {9, 6}, {9, 7}, {9, 8}, // row 9
            {10, 4}, {10, 5}, {10, 6}, {10, 7}, {10, 8}, // row 10
            {11, 4}, {11, 5}, {11, 6}, {11, 7}, {11, 8}, // row 11
            {12, 4}, {12, 5}, {12, 6}, {12, 7}, {12, 8}}; // row 12
    getAllSlots(englishModel4, marbleForModel4, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForModel4 = new int[][]{
            {6, 7}};
    getAllSlots(englishModel4, emptyForModel4, MarbleSolitaireModelState.SlotState.Empty);

    assertNotEquals(null, englishModel1);
    assertNotEquals(null, englishModel2);
    assertNotEquals(null, englishModel3);
    assertNotEquals(null, englishModel4);

    // Constructor 1 Exception
    getSlotAtException(englishModel1, -1, 3);
    getSlotAtException(englishModel1, 3, -1);
    getSlotAtException(englishModel1, 3, 7);
    getSlotAtException(englishModel1, 7, 3);

    // Constructor 2 Exception
    getSlotAtException(englishModel2, -1, 3);
    getSlotAtException(englishModel2, 3, -1);
    getSlotAtException(englishModel2, 3, 7);
    getSlotAtException(englishModel2, 7, 3);

    // Constructor 3 Exception
    getSlotAtException(englishModel2, -1, 6);
    getSlotAtException(englishModel2, 6, -1);
    getSlotAtException(englishModel2, 6, 13);
    getSlotAtException(englishModel2, 13, 6);

    // Constructor 4 Exception
    getSlotAtException(englishModel2, -1, 6);
    getSlotAtException(englishModel2, 6, -1);
    getSlotAtException(englishModel2, 6, 13);
    getSlotAtException(englishModel2, 13, 6);
  }

  // Testing getSlotAt Exception Testing
  private void getSlotAtException(MarbleSolitaireModel model, int a, int b) {
    try {
      model.getSlotAt(a, b);
      fail("Reason: getSlotAt didn't throw an exception - even when it should");
    } catch (IllegalArgumentException e) {
      assertEquals("Out of bounds", e.getMessage());
    }
  }

  // Takes in a model, slot state, and a 2d array containing slot positions to check if all
  // slot positions have their correct states
  private void getAllSlots(MarbleSolitaireModel model,
                           int[][] arr, MarbleSolitaireModelState.SlotState slot) {
    for (int[] elem : arr) {
      if (elem.length != 2) {
        throw new IllegalArgumentException("Need exactly 2 parameters to get a slot");
      }
      assertEquals(model.getSlotAt(elem[0], elem[1]), slot);
    }
  }

  /**
   * We want to check if we can get the correct score is assigned at the start of the game for
   * different constructors.
   * We have already tested that the move method accurately deducts 1 score point every move.
   */
  @Test
  public void testGetScore() {
    assertEquals(32, englishModel1.getScore()); // constructor 1
    assertEquals(32, englishModel2.getScore()); // constructor 2
    assertEquals(104, englishModel3.getScore()); // constructor 3
    assertEquals(216, englishModel3b.getScore());
    assertEquals(368, englishModel3c.getScore());
    assertEquals(104, englishModel4.getScore()); // constructor 4

    // We will now do fuzzy testing to test this on different odd boards
    // this works because we are counting marbles in a different way from the score function used
    Random r = new Random(100);
    for (int i = 0; i < 50; i += 1) {
      int armL = r.nextInt(1000) * 2 + 3;
      int score = (5 * armL * armL) - (4 * armL) - 1;
      englishModel3 = new EnglishSolitaireModel(armL); // constructor 3
      englishModel4 = new EnglishSolitaireModel(armL, armL + armL / 2 - 1,
              armL + armL / 2 - 1); // constructor 4
      assertEquals(score, englishModel3.getScore());
      assertEquals(score, englishModel4.getScore());
    }
  }
}