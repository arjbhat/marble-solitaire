import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Class to test the methods in the Triangle Solitaire Model. (including their integration)
 */
public class TriangleSolitaireModelTest {

  private MarbleSolitaireModel triangleModel1;
  private MarbleSolitaireModel triangleModel2;
  private MarbleSolitaireModel triangleModel3;
  private MarbleSolitaireModel triangleModel4;
  private MarbleSolitaireModel triangleModel3b;
  private MarbleSolitaireModel triangleModel3c;

  /**
   * Initializing valid game states.
   */
  @Before
  public void init() {
    triangleModel1 = new TriangleSolitaireModel();
    triangleModel2 = new TriangleSolitaireModel(1, 1);
    triangleModel3 = new TriangleSolitaireModel(5); // arm thickness of 5
    triangleModel4 = new TriangleSolitaireModel(5, 4, 0);
    triangleModel3b = new TriangleSolitaireModel(7);
    triangleModel3c = new TriangleSolitaireModel(9);
    MarbleSolitaireModel triangleModel3d = new TriangleSolitaireModel(2);
    MarbleSolitaireModel triangleModel3e = new TriangleSolitaireModel(4);
  }

  /**
   * Testing the exceptions for the constructor that initializes a game
   * by taking in the position of the empty slot.
   */
  @Test
  public void testInvalidEmptyCellPositionForConstructor2() {

    triangleModel1 = null;

    this.checkConstructorException("Invalid empty cell position (0,1)", 0, 1);
    this.checkConstructorException("Invalid empty cell position (1,2)", 1, 2);
    this.checkConstructorException("Invalid empty cell position (2,3)", 2, 3);
    this.checkConstructorException("Invalid empty cell position (3,4)", 3, 4);
    this.checkConstructorException("Invalid empty cell position (100,3)", 100, 3);
    this.checkConstructorException("Invalid empty cell position (-100,3)", -100, 3);
    this.checkConstructorException("Invalid empty cell position (3,100)", 3, 100);
    this.checkConstructorException("Invalid empty cell position (3,-100)", 3, -100);
    this.checkConstructorException("Invalid empty cell position (50,50)", 50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,50)", -50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,-50)",
            -50, -50);

    // Sanity check for handin
    assertNull(triangleModel1);

  }

  /**
   * Testing the exceptions for the constructor that initializes a game
   * by taking in the arm thickness.
   */
  @Test
  public void testInvalidScaleForConstructor3() {

    triangleModel1 = null;

    this.checkConstructorException("Scale is not a positive number", -3);
    this.checkConstructorException("Scale is not a positive number", -2);
    this.checkConstructorException("Scale is not a positive number", -1);
    this.checkConstructorException("Scale is not a positive number", 0);
    this.checkConstructorException("Scale is not playable", 1);
    this.checkConstructorException("Scale is not a positive number", -50);

    // Sanity check for handin
    assertNull(triangleModel1);
  }

  /**
   * Testing the exceptions for the constructor that initializes a game
   * by taking in the arm thickness and the position of the empty slot.
   */
  @Test
  public void testInvalidEmptyCellPositionForConstructor4() {

    triangleModel1 = null;

    // for thickness 3
    this.checkConstructorException("Invalid empty cell position (0,1)", 3, 0, 1);
    this.checkConstructorException("Invalid empty cell position (1,2)", 3, 1, 2);
    this.checkConstructorException("Invalid empty cell position (2,3)", 3, 2, 3);
    this.checkConstructorException("Invalid empty cell position (3,4)", 3, 3, 4);
    this.checkConstructorException("Invalid empty cell position (100,3)", 3, 100, 3);
    this.checkConstructorException("Invalid empty cell position (-100,3)", 3, -100, 3);
    this.checkConstructorException("Invalid empty cell position (3,100)", 3, 3, 100);
    this.checkConstructorException("Invalid empty cell position (3,-100)", 3, 3, -100);
    this.checkConstructorException("Invalid empty cell position (50,50)", 3, 50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,50)", 3, -50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,-50)",
            3, -50, -50);

    // for thickness 5
    this.checkConstructorException("Invalid empty cell position (0,1)", 5, 0, 1);
    this.checkConstructorException("Invalid empty cell position (1,2)", 5, 1, 2);
    this.checkConstructorException("Invalid empty cell position (2,3)", 5, 2, 3);
    this.checkConstructorException("Invalid empty cell position (3,4)", 5, 3, 4);
    this.checkConstructorException("Invalid empty cell position (100,3)", 5, 100, 3);
    this.checkConstructorException("Invalid empty cell position (-100,3)", 5, -100, 3);
    this.checkConstructorException("Invalid empty cell position (3,100)", 5, 3, 100);
    this.checkConstructorException("Invalid empty cell position (3,-100)", 5, 3, -100);
    this.checkConstructorException("Invalid empty cell position (50,50)", 5, 50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,50)", 5, -50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,-50)",
            5, -50, -50);

    // for thickness 7
    this.checkConstructorException("Invalid empty cell position (0,1)", 7, 0, 1);
    this.checkConstructorException("Invalid empty cell position (1,2)", 7, 1, 2);
    this.checkConstructorException("Invalid empty cell position (2,3)", 7, 2, 3);
    this.checkConstructorException("Invalid empty cell position (3,4)", 7, 3, 4);
    this.checkConstructorException("Invalid empty cell position (100,3)", 7, 100, 3);
    this.checkConstructorException("Invalid empty cell position (-100,3)", 7, -100, 3);
    this.checkConstructorException("Invalid empty cell position (3,100)", 7, 3, 100);
    this.checkConstructorException("Invalid empty cell position (3,-100)", 7, 3, -100);
    this.checkConstructorException("Invalid empty cell position (50,50)", 7, 50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,50)", 7, -50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,-50)",
            7, -50, -50);

    // for thickness 9
    this.checkConstructorException("Invalid empty cell position (0,1)", 9, 0, 1);
    this.checkConstructorException("Invalid empty cell position (1,2)", 9, 1, 2);
    this.checkConstructorException("Invalid empty cell position (2,3)", 9, 2, 3);
    this.checkConstructorException("Invalid empty cell position (3,4)", 9, 3, 4);
    this.checkConstructorException("Invalid empty cell position (100,3)", 9, 100, 3);
    this.checkConstructorException("Invalid empty cell position (-100,3)", 9, -100, 3);
    this.checkConstructorException("Invalid empty cell position (3,100)", 9, 3, 100);
    this.checkConstructorException("Invalid empty cell position (3,-100)", 9, 3, -100);
    this.checkConstructorException("Invalid empty cell position (50,50)", 9, 50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,50)", 9, -50, 50);
    this.checkConstructorException("Invalid empty cell position (-50,-50)",
            9, -50, -50);

    // Sanity check for handin
    assertNull(triangleModel1);
  }

  /**
   * Testing the exceptions for the constructor that initializes a game
   * by taking in the arm thickness and the position of the empty slot.
   */
  @Test
  public void testInvalidScaleForConstructor4() {

    triangleModel1 = null;

    this.checkConstructorException("Scale is not a positive number", -3, 0, 0);
    this.checkConstructorException("Scale is not a positive number", -2, 0, 0);
    this.checkConstructorException("Scale is not a positive number", -1, 0, 0);
    this.checkConstructorException("Scale is not a positive number", 0, 0, 0);
    this.checkConstructorException("Scale is not playable", 1, 0, 0);
    this.checkConstructorException("Scale is not a positive number", -50, 0, 0);
    this.checkConstructorException("Scale is not a positive number", -50, 50, -50);

    // Sanity check for handin
    assertNull(triangleModel1);
  }

  // Helper to test constructor exception
  private void checkConstructorException(String exe,
                                         int... parameters) {
    try {
      if (parameters.length == 1) {
        triangleModel1 = new TriangleSolitaireModel(parameters[0]);
      } else if (parameters.length == 2) {
        triangleModel1 = new TriangleSolitaireModel(parameters[0], parameters[1]);
      } else if (parameters.length == 3) {
        triangleModel1 = new TriangleSolitaireModel(parameters[0], parameters[1], parameters[2]);
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
    // For Constructor 1 (Thickness 3): Move top right
    checkLegalMove(triangleModel1, 2, 0, 0, 0);

    // For Constructor 1 (Thickness 3): Move left
    checkLegalMove(triangleModel1, 2, 2, 2, 0);

    // For Constructor 1 (Thickness 3): Move top left
    checkLegalMove(triangleModel1, 4, 3, 2, 1);

    // For Constructor 1 (Thickness 3): Move right
    checkLegalMove(triangleModel1, 2, 0, 2, 2);

    // For Constructor 1 (Thickness 3): Move top right
    checkLegalMove(triangleModel1, 4, 1, 2, 1);

    // For Constructor 1 (Thickness 3): Move bottom left
    checkLegalMove(triangleModel1, 1, 1, 3, 1);

    // For Constructor 1 (Thickness 3): Move top left
    checkLegalMove(triangleModel1, 3, 3, 1, 1);

    // For Constructor 1 (Thickness 3): Move bottom right
    checkLegalMove(triangleModel1, 0, 0, 2, 2);

    // sanity check for handin
    assertNotEquals(null, triangleModel1);
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
    this.checkModelIllegalArgumentException(triangleModel1, 50, 50, 52, 50);
    this.checkModelIllegalArgumentException(triangleModel1, -50, 50, 52, -50);

    triangleModel1 = new TriangleSolitaireModel(4, 3);
    // Outside the board to inside the board
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(4, 3));
    this.checkModelIllegalArgumentException(triangleModel1, 4, 5, 4, 3);

    triangleModel1 = new TriangleSolitaireModel(0, 0);
    // Inside the board to outside the board
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(4, 4));
    this.checkModelIllegalArgumentException(triangleModel1, 4, 3, 4, 5);

    triangleModel1.move(2, 0, 0, 0);
    triangleModel1.move(4, 0, 2, 0);
    // From empty slot
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(1, 0));
    this.checkModelIllegalArgumentException(triangleModel1, 3, 0, 1, 0);

    // From Invalid slot
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            triangleModel1.getSlotAt(1, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(1, 0));
    this.checkModelIllegalArgumentException(triangleModel1, 1, 2, 1, 0);

    // To slot with Marble
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(4, 4));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 2));
    this.checkModelIllegalArgumentException(triangleModel1, 4, 4, 2, 2);

    // To slot with Invalid
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Invalid,
            triangleModel1.getSlotAt(3, 4));
    this.checkModelIllegalArgumentException(triangleModel1, 3, 2, 3, 4);

    // Slot with Invalid in middle cannot be tested

    // Slot with Empty in the middle
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(4, 0));
    this.checkModelIllegalArgumentException(triangleModel1, 2, 0, 4, 0);

    triangleModel1 = new TriangleSolitaireModel(0, 0);

    // Move to slot 3 spaces away with 2 marbles in between
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(0, 0));
    this.checkModelIllegalArgumentException(triangleModel1, 3, 3, 0, 0);

    // Move to same position
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 3));
    this.checkModelIllegalArgumentException(triangleModel1, 3, 3, 3, 3);

    // Move to position 1 slot away
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(1, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(0, 0));
    this.checkModelIllegalArgumentException(triangleModel1, 1, 1, 0, 0);

    // All the illegal ways to move 2 slots away

    // Pos 1:
    triangleModel1 = new TriangleSolitaireModel(1, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(1, 0));
    this.checkModelIllegalArgumentException(triangleModel1, 2, 2, 1, 0);

    // Pos 2:
    triangleModel1 = new TriangleSolitaireModel(3, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(3, 0));
    this.checkModelIllegalArgumentException(triangleModel1, 2, 2, 3, 0);

    // Pos 3: Move to bottom left
    triangleModel1 = new TriangleSolitaireModel(4, 0);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(4, 0));
    this.checkModelIllegalArgumentException(triangleModel1, 2, 2, 4, 0);

    // Pos 4:
    triangleModel1 = new TriangleSolitaireModel(4, 1);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(4, 1));
    this.checkModelIllegalArgumentException(triangleModel1, 2, 2, 4, 1);

    // Pos 5:
    triangleModel1 = new TriangleSolitaireModel(4, 3);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(4, 3));
    this.checkModelIllegalArgumentException(triangleModel1, 2, 2, 4, 3);


    // Pos 6:
    triangleModel1 = new TriangleSolitaireModel(2, 2);
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(1, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(2, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(2, 2));
    this.checkModelIllegalArgumentException(triangleModel1, 1, 0, 2, 2);

    // Pos 7:
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(2, 2));
    this.checkModelIllegalArgumentException(triangleModel1, 3, 0, 2, 2);

    // Pos 8: Move to top right
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(4, 0));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(2, 2));
    this.checkModelIllegalArgumentException(triangleModel1, 4, 0, 2, 2);

    // Pos 9:
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(4, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 1));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(2, 2));
    this.checkModelIllegalArgumentException(triangleModel1, 4, 1, 2, 2);

    // Pos 10:
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(4, 3));
    assertEquals(MarbleSolitaireModelState.SlotState.Marble,
            triangleModel1.getSlotAt(3, 2));
    assertEquals(MarbleSolitaireModelState.SlotState.Empty,
            triangleModel1.getSlotAt(2, 2));
    this.checkModelIllegalArgumentException(triangleModel1, 4, 3, 2, 2);
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
            {2, 0, 0, 0},
            {4, 0, 2, 0},
            {4, 2, 4, 0},
            {4, 4, 4, 2},
            {2, 2, 4, 4},
            {0, 0, 2, 2},
            {3, 2, 1, 0},
            {2, 0, 0, 0},
            {4, 2, 2, 0}
    };
    playGameWithMoves(triangleModel1, movesToLoseModel1);
    assertTrue(triangleModel1.isGameOver());
    assertEquals(5, triangleModel1.getScore());

    this.init();

    // test if there are more than 1 pegs left, and you can make moves, the game is not over
    int [][] movesToLoseModel3 = new int[][]{
            {2, 0, 0, 0},
            {4, 0, 2, 0},
            {4, 2, 4, 0},
            {4, 4, 4, 2},
    };
    playGameWithMoves(triangleModel1, movesToLoseModel3);
    assertFalse(triangleModel1.isGameOver());
    assertEquals(10, triangleModel1.getScore());
  }

  // Helper to test if the score is changing as we play moves, and to check if we can play moves
  // when the game is not over
  private void playGameWithMoves(MarbleSolitaireModel model, int[][] arr) {
    int size = model.getBoardSize();
    int count = size * (size + 1) / 2 - 1;
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
    assertEquals(5, triangleModel1.getBoardSize()); // Constructor 1
    assertEquals(5, triangleModel2.getBoardSize()); // Constructor 2
    assertEquals(5, triangleModel3.getBoardSize()); // Constructor 3
    assertEquals(7, triangleModel3b.getBoardSize());
    assertEquals(9, triangleModel3c.getBoardSize());
    assertEquals(5, triangleModel4.getBoardSize()); // Constructor 4

    // We will now do fuzzy testing to test this on different odd boards
    Random r = new Random(100);
    for (int i = 0; i < 50; i += 1) {
      int armL = r.nextInt(1000) * 2 + 3;
      int boardSize = armL;
      triangleModel3 = new TriangleSolitaireModel(armL); // constructor 3
      triangleModel4 = new TriangleSolitaireModel(armL, 0,
              0); // constructor 4
      assertEquals(boardSize, triangleModel3.getBoardSize());
      assertEquals(boardSize, triangleModel4.getBoardSize());
    }
  }

  /**
   * Test the getSlotAt() method for different configurations of the board.
   */
  @Test
  public void getSlotAt() {
    triangleModel1 = new TriangleSolitaireModel();
    // Testing if it works for Constructor 1
    int[][] invalidForModel1 = new int[][]{
            {0, 1}, {0, 2}, {0, 3}, {0, 4},
            {1, 2}, {1, 3}, {1, 4},
            {2, 3}, {2, 4},
            {3, 4}};
    getAllSlots(triangleModel1, invalidForModel1, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForModel1 = new int[][]{
            {1, 0}, {1, 1},
            {2, 0}, {2, 1}, {2, 2},
            {3, 0}, {3, 1}, {3, 2}, {3, 3},
            {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}};
    getAllSlots(triangleModel1, marbleForModel1, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForModel1 = new int[][]{
            {0, 0}};
    getAllSlots(triangleModel1, emptyForModel1, MarbleSolitaireModelState.SlotState.Empty);

    triangleModel2 = new TriangleSolitaireModel(1, 1);
    // Testing if it works for Constructor 2
    int[][] invalidForModel2 = new int[][]{
            {0, 1}, {0, 2}, {0, 3}, {0, 4},
            {1, 2}, {1, 3}, {1, 4},
            {2, 3}, {2, 4},
            {3, 4}};
    getAllSlots(triangleModel2, invalidForModel2, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForModel2 = new int[][]{
            {0, 0},
            {1, 0},
            {2, 0}, {2, 1}, {2, 2},
            {3, 0}, {3, 1}, {3, 2}, {3, 3},
            {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}};
    getAllSlots(triangleModel2, marbleForModel2, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForModel2 = new int[][]{
            {1, 1}};
    getAllSlots(triangleModel2, emptyForModel2, MarbleSolitaireModelState.SlotState.Empty);

    triangleModel2 = new TriangleSolitaireModel(2);
    // Testing if it works for Constructor 3
    int[][] invalidForModel3 = new int[][]{
            {0, 1}};
    getAllSlots(triangleModel2, invalidForModel3, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForModel3 = new int[][]{
            {1, 0}, {1, 1}};
    getAllSlots(triangleModel2, marbleForModel3, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForModel3 = new int[][]{
            {0, 0}};
    getAllSlots(triangleModel2, emptyForModel3, MarbleSolitaireModelState.SlotState.Empty);

    triangleModel3 = new TriangleSolitaireModel(3);
    // Testing if it works for Constructor 3
    int[][] invalidForSize2 = new int[][]{
            {0, 1}, {0, 2}, {1, 2}};
    getAllSlots(triangleModel3, invalidForSize2, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForSize2 = new int[][]{
            {1, 0}, {1, 1}, {2, 0}, {2, 1}, {2, 2}};
    getAllSlots(triangleModel3, marbleForSize2, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForSize2 = new int[][]{
            {0, 0}};
    getAllSlots(triangleModel3, emptyForSize2, MarbleSolitaireModelState.SlotState.Empty);

    triangleModel4 = new TriangleSolitaireModel(3, 1, 0);
    // Testing if it works for all positions in an arm thickness 5 board (Constructor 4)
    int[][] invalidForModel4 = new int[][]{
            {0, 1}, {0, 2}, {1, 2}};
    getAllSlots(triangleModel4, invalidForModel4, MarbleSolitaireModelState.SlotState.Invalid);
    int[][] marbleForModel4 = new int[][]{
            {0, 0}, {1, 1}, {2, 0}, {2, 1}, {2, 2}};
    getAllSlots(triangleModel4, marbleForModel4, MarbleSolitaireModelState.SlotState.Marble);
    int[][] emptyForModel4 = new int[][]{
            {1, 0}};
    getAllSlots(triangleModel4, emptyForModel4, MarbleSolitaireModelState.SlotState.Empty);

    assertNotEquals(null, triangleModel1);
    assertNotEquals(null, triangleModel2);
    assertNotEquals(null, triangleModel3);
    assertNotEquals(null, triangleModel4);

    // Constructor 1 Exception
    getSlotAtException(triangleModel1, -1, 3);
    getSlotAtException(triangleModel1, 3, -1);
    getSlotAtException(triangleModel1, 3, 7);
    getSlotAtException(triangleModel1, 7, 3);

    // Constructor 2 Exception
    getSlotAtException(triangleModel2, -1, 3);
    getSlotAtException(triangleModel2, 3, -1);
    getSlotAtException(triangleModel2, 3, 7);
    getSlotAtException(triangleModel2, 7, 3);

    // Constructor 3 Exception
    getSlotAtException(triangleModel3, -1, 6);
    getSlotAtException(triangleModel3, 6, -1);
    getSlotAtException(triangleModel3, 6, 13);
    getSlotAtException(triangleModel3, 13, 6);

    // Constructor 4 Exception
    getSlotAtException(triangleModel4, -1, 6);
    getSlotAtException(triangleModel4, 6, -1);
    getSlotAtException(triangleModel4, 6, 13);
    getSlotAtException(triangleModel4, 13, 6);
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
    assertEquals(14, triangleModel1.getScore()); // constructor 1
    assertEquals(14, triangleModel2.getScore()); // constructor 2
    assertEquals(14, triangleModel3.getScore()); // constructor 3
    assertEquals(27, triangleModel3b.getScore());
    assertEquals(44, triangleModel3c.getScore());
    assertEquals(14, triangleModel4.getScore()); // constructor 4

    // We will now do fuzzy testing to test this on different odd boards
    // this works because we are counting marbles in a different way from the score function used
    Random r = new Random(100);
    for (int i = 0; i < 50; i += 1) {
      int armL = r.nextInt(1000) * 2 + 3;
      int score = armL * (armL + 1) / 2 - 1;
      triangleModel3 = new TriangleSolitaireModel(armL); // constructor 3
      triangleModel4 = new TriangleSolitaireModel(armL, 0, 0); // constructor 4
      assertEquals(score, triangleModel3.getScore());
      assertEquals(score, triangleModel4.getScore());
    }
  }
}