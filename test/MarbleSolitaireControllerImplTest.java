import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Class to test the Solitaire Controller implementation, including the way it interacts with the
 * view and the controller.
 */
public class MarbleSolitaireControllerImplTest {
  private StringBuilder input1;
  private StringBuilder input2;
  private StringBuilder input3;
  private StringReader readInput1;
  private StringReader readInput2;
  private StringReader readInput3;
  private StringBuilder print1;
  private StringBuilder print2;
  private StringBuilder print3;
  private MarbleSolitaireController controller1;
  private MarbleSolitaireController controller2;
  private MarbleSolitaireController controller3;
  private MarbleSolitaireView newView1;
  private MarbleSolitaireView newView2;
  private MarbleSolitaireView newView3;
  private MarbleSolitaireModel englishModel1;
  private MarbleSolitaireModel europeanModel1;
  private MarbleSolitaireModel triangleModel1;

  /**
   * Initializing a valid controller.
   */
  @Before
  public void init() {
    input1 = new StringBuilder(); // Input for Controller 1
    input2 = new StringBuilder();
    input3 = new StringBuilder();
    readInput1 = new StringReader(input1.toString()); // Input1 as a Readable
    readInput2 = new StringReader(input2.toString());
    readInput3 = new StringReader(input3.toString());
    // destination 1
    print1 = new StringBuilder(); // Destination for View1
    print2 = new StringBuilder();
    print3 = new StringBuilder();

    englishModel1 = new EnglishSolitaireModel(); // Model for View1 and Controller1
    europeanModel1 = new EuropeanSolitaireModel();
    triangleModel1 = new TriangleSolitaireModel();

    newView1 = new MarbleSolitaireTextView(englishModel1, print1); // View for Controller1
    newView2 = new MarbleSolitaireTextView(europeanModel1, print2);
    newView3 = new TriangleSolitaireTextView(triangleModel1, print3);

    controller1 = new MarbleSolitaireControllerImpl(englishModel1, newView1, readInput1);
    controller2 = new MarbleSolitaireControllerImpl(europeanModel1, newView2, readInput2);
    controller3 = new MarbleSolitaireControllerImpl(triangleModel1, newView3, readInput3);
    // Controller for User
  }

  /**
   * Testing Constructor Exceptions for the Controller.
   */
  @Test
  public void testNullExceptionsForController() {
    try {
      controller1 = new MarbleSolitaireControllerImpl(null, newView1, readInput1);
      fail("Reason: Constructor didn't throw an exception - even when it should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid model of type null", e.getMessage());
    }

    try {
      controller1 = new MarbleSolitaireControllerImpl(englishModel1, null, readInput1);
      fail("Reason: Constructor didn't throw an exception - even when it should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid view of type null", e.getMessage());
    }

    try {
      controller1 = new MarbleSolitaireControllerImpl(englishModel1, newView1, null);
      fail("Reason: Constructor didn't throw an exception - even when it should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid readable of type null", e.getMessage());
    }
  }

  /**
   * Test what happens if the game is over, but we run out of inputs. Test if IllegalStateException
   * is thrown.
   */
  @Test
  public void testReadableRunningOut() {
    UserIO[] interactions = new UserIO[]{
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("4 2 4 4"),
            prints("method: move - param: 3,1,3,3"),
            prints("method: isGameOver"),
            prints("method: getScore"),
            // readable runs out if inputs because game is over but no more moves are provided
    };

    try {
      controllerToMockModel(interactions);
      fail("Reason: Game should fail because readable ran out of inputs");
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  /**
   * Test what happens if the game is over, but we run out of inputs. Test if IllegalStateException
   * is thrown.
   */
  @Test
  public void testReadableRunningOut3Values() {
    UserIO[] interactions = new UserIO[]{
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("4 2 4 ")
            // no moves are even played because we only have 3 values
    };

    try {
      controllerToMockModel(interactions);
      fail("Reason: Game should fail because readable ran out of inputs");
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  /**
   * Test if IllegalStateException thrown instead of an IOException when the appendable errors.
   * We make a broken appendable that always errors, provide it to the view and then try to transmit
   * to it.
   */
  @Test
  public void testAppendableFailing() {
    newView1 = new MarbleSolitaireTextView(englishModel1, new BrokenAppendable());
    controller1 = new MarbleSolitaireControllerImpl(englishModel1, newView1, readInput1);

    try {
      controller1.playGame();
      fail("Reason: Can't transmit output to a broken appendable");
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully transmit output", e.getMessage());
    }
  }

  /**
   * Test if IllegalStateException thrown instead of an IOException when the readable errors.
   */
  @Test
  public void testReadableFailing() {
    Readable read = new BrokenReadable();
    controller1 = new MarbleSolitaireControllerImpl(englishModel1, newView1, read);

    try {
      controller1.playGame();
      fail("Reason: Can't read readable");
    } catch (IllegalStateException e) {
      assertEquals("Unable to successfully receive input", e.getMessage());
    }
  }

  /**
   * Quit the game without any valid moves.
   */
  @Test
  public void testJustQuitGame() {
    assertEquals("", input1.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32"),
            inputs("q 2"), // value after quit does not matter
            prints("method: renderMessage - message: Game quit!"),
            prints("method: renderMessage - message: State of game when quit:"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32") // Score remains the same
    };

    controllerToMockView(englishModel1, interactions);
  }

  /**
   * Quit the game after 1 input.
   */
  @Test
  public void testQuitGame1Input() {
    assertEquals("", input1.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32"),
            inputs("1 q"),
            prints("method: renderMessage - message: Game quit!"),
            prints("method: renderMessage - message: State of game when quit:"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32") // Score remains the same
    };

    controllerToMockView(englishModel1, interactions);
  }

  /**
   * Quit the game after 2 input.
   */
  @Test
  public void testQuitGame2Input() {
    assertEquals("", input1.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32"),
            inputs("1 2 q"),
            prints("method: renderMessage - message: Game quit!"),
            prints("method: renderMessage - message: State of game when quit:"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32") // Score remains the same
    };

    controllerToMockView(englishModel1, interactions);
  }

  /**
   * Quit the game after 3 input.
   */
  @Test
  public void testQuitGame3Input() {
    assertEquals("", input1.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32"),
            inputs("1 2 3 q"),
            prints("method: renderMessage - message: Game quit!"),
            prints("method: renderMessage - message: State of game when quit:"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32") // Score remains the same
    };

    controllerToMockView(englishModel1, interactions);
  }

  /**
   * Play till the game is over. Ensure the right messages and scores displayed. (English)
   */
  @Test
  public void testInvalidMoveCharactersAndGameOverForEnglishSolitaireModel() {
    assertEquals("", input1.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32"),
            inputs("6 4 4 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 31"),
            inputs("3 4 5 5"),
            prints("method: renderMessage - message: Invalid move. Play again."),
            inputs("3 4 5 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 30"),
            inputs("1 4 X ∂"),
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: X"),
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: ∂"),
            inputs("3,"),
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: 3,"), // can't put comma after 3
            inputs("3 4"),
            prints("method: renderBoard"), // uses the inputs 1 4 3 4 since they were valid
            prints("method: renderMessage - message: Score: 29"),
            inputs("4 2 4 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 28"),
            inputs("4 5 4 3"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 27"),
            inputs("$"),
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: $"),
            inputs("0"), // 0 is not a positive integer
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: 0"),
            inputs("-1"), // -1 is not a positive integer
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: -1"),
            inputs("3 4 5 10"),
            prints("method: renderMessage - message: Invalid move. Play again."),
            inputs(") Z"),
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: )"),
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: Z"),
            inputs("4 10 & M"),
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: &"),
            prints("method: renderMessage - message: Please only enter a positive integer "
                    + "or quit game. Not: M"),
            inputs("4 5"),
            prints("method: renderMessage - message: Invalid move. Play again."),
            inputs("4 7 4 5"),
            prints("method: renderMessage - message: Game over!"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 26") // Score decreases :)
    };

    controllerToMockView(englishModel1, interactions);
  }

  /**
   * Play till the game is over. Ensure the right messages and scores displayed. (European)
   */
  @Test
  public void testInvalidMoveCharactersAndGameOverForEuropeanSolitaireModel() {
    assertEquals("", input2.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 36"),
            inputs("2 4 4 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 35"),
            inputs("2 4 4 4"),
            prints("method: renderMessage - message: Invalid move. Play again."),
            inputs("5 4 3 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 34"),
            inputs(") Z"),
            prints("method: renderMessage - message: Please only enter a positive integer " +
                    "or quit game. Not: )"),
            prints("method: renderMessage - message: Please only enter a positive integer " +
                    "or quit game. Not: Z"),
            inputs("7 4 5 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 33"),
            inputs("4 2 4 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32"),
            inputs("4 5 4 3"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 31"),
            inputs("4 7 4 5"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 30"),
            inputs("2 6 4 6"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 29"),
            inputs("5 6 3 6"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 28"),
            inputs("2 2 4 2"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 27"),
            inputs("5 2 3 2"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 26"),
            inputs("6 2 6 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 25"),
            inputs("6 5 6 3"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 24"),
            inputs("5 4 5 6"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 23"),
            inputs("5 7 5 5"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 22"),
            inputs("4 5 6 5"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 21"),
            inputs("7 5 5 5"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 20"),
            inputs("2 5 4 5"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 19"),
            inputs("5 5 3 5"),
            prints("method: renderMessage - message: Game over!"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 18")
    };

    controllerToMockView(europeanModel1, interactions);
  }

  /**
   * Play till the game is completely over. Ensure the right messages and scores displayed.
   * This is played using an actual model and a mock view. The score decreasing shows that it works.
   * (English)
   */
  @Test
  public void testCompleteGameOverEnglish() {
    assertEquals("", input1.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32"),
            inputs("2 4 4 4"), // {1, 3, 3, 3},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 31"),
            inputs("3 6 3 4"), // {2, 5, 2, 3},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 30"),
            inputs("1 5 3 5"), // {0, 4, 2, 4},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 29"),
            inputs("1 3 1 5"), // {0, 2, 0, 4},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 28"),
            inputs("4 5 2 5"), // {3, 4, 1, 4},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 27"),
            inputs("1 5 3 5"), // {0, 4, 2, 4},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 26"),
            inputs("6 5 4 5"), // {5, 4, 3, 4},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 25"),
            inputs("5 7 5 5"), // {4, 6, 4, 4},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 24"),
            inputs("3 7 5 7"), // {2, 6, 4, 6},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 23"),
            inputs("5 4 5 6"), // {4, 3, 4, 5},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 22"),
            inputs("5 7 5 5"), // {4, 6, 4, 4},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 21"),
            inputs("5 2 5 4"), // {4, 1, 4, 3},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 20"),
            inputs("7 3 5 3"), // {6, 2, 4, 2},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 19"),
            inputs("7 5 7 3"), // {6, 4, 6, 2},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 18"),
            inputs("4 3 6 3"), // {3, 2, 5, 2},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 17"),
            inputs("7 3 5 3"), // {6, 2, 4, 2},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 16"),
            inputs("2 3 4 3"), // {1, 2, 3, 2},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 15"),
            inputs("3 1 3 3"), // {2, 0, 2, 2},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 14"),
            inputs("5 1 3 1"), // {4, 0, 2, 0},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 13"),
            inputs("3 4 3 2"), // {2, 3, 2, 1},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 12"),
            inputs("3 1 3 3"), // {2, 0, 2, 2},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 11"),
            inputs("5 4 5 6"), // {4, 3, 4, 5},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 10"),
            inputs("5 6 3 6"), // {4, 5, 2, 5},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 9"),
            inputs("3 6 3 4"), // {2, 5, 2, 3},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 8"),
            inputs("3 4 3 2"), // {2, 3, 2, 1},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 7"),
            inputs("3 2 5 2"), // {2, 1, 4, 1},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 6"),
            inputs("5 2 5 4"), // {4, 1, 4, 3},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 5"),
            inputs("4 4 4 2"), // {3, 3, 3, 1},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 4"),
            inputs("6 4 4 4"), // {5, 3, 3, 3},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 3"),
            inputs("4 5 4 3"), // {3, 4, 3, 2},
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 2"),
            inputs("4 2 4 4"), // {3, 1, 3, 3}
            prints("method: renderMessage - message: Game over!"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 1") // Score decreases :)
    };

    controllerToMockView(englishModel1, interactions);
  }

  /**
   * Play till the game is completely over. Ensure the right messages and scores displayed.
   * This is played using an actual model and a mock view. The score decreasing shows that it works.
   * (Triangle)
   */
  @Test
  public void testCompleteGameOverTriangle() {
    assertEquals("", input1.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 14"),
            inputs("3 1 1 1"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 13"),
            inputs("3 3 3 1"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 12"),
            inputs("5 5 3 3"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 11"),
            inputs("2 2 4 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 10"),
            inputs("5 3 3 3"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 9"),
            inputs(") Z"),
            prints("method: renderMessage - message: Please only enter a positive integer " +
                    "or quit game. Not: )"),
            prints("method: renderMessage - message: Please only enter a positive integer " +
                    "or quit game. Not: Z"),
            inputs("5 1 5 3"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 8"),
            inputs("5 4 5 2"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 7"),
            inputs("2 4 4 4"),
            prints("method: renderMessage - message: Invalid move. Play again."),
            inputs("5 2 3 2"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 6"),
            inputs("4 4 2 2"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 5"),
            inputs("4 1 2 1"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 4"),
            inputs("1 1 3 1"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 3"),
            inputs("3 1 3 3"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 2"),
            inputs("3 3 1 1"),
            prints("method: renderMessage - message: Game over!"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 1")
    };

    controllerToMockView(triangleModel1, interactions);
  }

  /**
   * Test that message for invalid move is sent when invalid move is played - and whole game
   * not rendered. Test quit and then if same score shown.
   */
  @Test
  public void testInvalidMoveAndQuitGame() {
    assertEquals("", input1.toString()); // Sanity Check
    UserIO[] interactions = new UserIO[]{
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 32"),
            inputs("6 4 4 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 31"),
            inputs("3 4 5 5"),
            prints("method: renderMessage - message: Invalid move. Play again."),
            inputs("3 4 5 4"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 30"),
            inputs("q"),
            prints("method: renderMessage - message: Game quit!"),
            prints("method: renderMessage - message: State of game when quit:"),
            prints("method: renderBoard"),
            prints("method: renderMessage - message: Score: 30") // Score remains the same
    };

    controllerToMockView(englishModel1, interactions);
  }

  /**
   * Try pressing different keys in different orders along with invalids in order to test if
   * the keypress works as is intended.
   */
  @Test
  public void testKeypressOnMock() {
    assertEquals("", input1.toString()); // Sanity Check
    // Almost real simulation to GameOver in 6 moves on a mock model where all moves are valid
    UserIO[] interactions = new UserIO[]{
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("6 4 4 4"),
            prints("method: move - param: 5,3,3,3"), // same as 6 4 4 4
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("3 4 5 4"),
            prints("method: move - param: 2,3,4,3"), // same as 3 4 5 4
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("w"),
            inputs("E 1"),
            inputs("r T 4"),
            inputs("> % Y 3"),
            inputs("! ] ~ C 4"),
            prints("method: move - param: 0,3,2,3"), // same as 1 4 3 4
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("4 E"),
            inputs("2 r T"),
            inputs("4 > % Y"),
            inputs("4 ! ] ~ C"),
            prints("method: move - param: 3,1,3,3"), // same as 4 2 4 4
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("4 5 4 3"),
            prints("method: move - param: 3,4,3,2"),
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("4 7 4 5"),
            prints("method: move - param: 3,6,3,4"),
            prints("method: isGameOver"),
            prints("method: getScore"),
            inputs("Q") // We have to quit the loop ourselves because we operate on a mock
    };

    // A mock model where all moves are valid, so we need to quit
    controllerToMockModel(interactions);
  }

  private void controllerToMockModel(UserIO... interactions) {
    // Input is Input1
    StringBuilder expectedLog = new StringBuilder(); // What we expect log to look like
    StringBuilder actualLog = new StringBuilder(); // What is actually being logged
    MarbleSolitaireModel mockModel = new MockModel(actualLog);

    // Interactions went here...

    for (UserIO i : interactions) {
      i.apply(input1, expectedLog);
    }

    readInput1 = new StringReader(input1.toString());
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(mockModel, newView1, readInput1);

    controller1.playGame();

    String[] expectedLogArr = expectedLog.toString().split("\n");
    String[] actualLogArr = actualLog.toString().split("\n");

    int i = 0;
    for (String str : expectedLogArr) {
      assertEquals(str, actualLogArr[i]);
      i += 1;
    }
  }

  private void controllerToMockView(MarbleSolitaireModel model, UserIO... interactions) {
    // Input is Input1
    StringBuilder expectedLog = new StringBuilder(); // What we expect log to look like
    StringBuilder actualLog = new StringBuilder(); // What is actually being logged
    MarbleSolitaireView mockView = new MockView(actualLog);

    // Interactions went here...

    for (UserIO i : interactions) {
      i.apply(input1, expectedLog);
    }

    readInput1 = new StringReader(input1.toString());
    MarbleSolitaireController controller1 =
            new MarbleSolitaireControllerImpl(model, mockView, readInput1);

    controller1.playGame();

    // assertEquals(expectedLog.toString(), actualLog.toString()); To look at full StringBuilder

    String[] expectedLogArr = expectedLog.toString().split("\n");
    String[] actualLogArr = actualLog.toString().split("\n");

    int i = 0;
    for (String str : expectedLogArr) {
      assertEquals(str, actualLogArr[i]);
      i += 1;
    }
  }

  private static UserIO inputs(String in) {
    return (input, output) -> {
      input.append(in).append('\n');
    };
  }

  private static UserIO prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  /**
   * Test if the game display works as we expect it to for just game quit.
   */
  @Test
  public void testJustQuitGameIntegration() {
    input1 = new StringBuilder("q");
    readInput1 = new StringReader(input1.toString());
    controller1 = new MarbleSolitaireControllerImpl(englishModel1, newView1, readInput1);
    controller1.playGame();

    String games;
    games = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n";

    assertEquals(games, print1.toString());
  }

  /**
   * Test if the game display works as we expect it if you play move and quit. (English)
   */
  @Test
  public void testPlayMoveAndQuitGameIntegrationEnglish() {
    input1 = new StringBuilder("4 2 4 4 q");
    readInput1 = new StringReader(input1.toString());
    controller1 = new MarbleSolitaireControllerImpl(englishModel1, newView1, readInput1);
    controller1.playGame();

    String games;
    games = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 31\n";

    assertEquals(games, print1.toString());
  }

  /**
   * Test if the game display works as we expect it if you play move and quit. (European)
   */
  @Test
  public void testPlayMoveAndQuitGameIntegrationEuropean() {
    input2 = new StringBuilder("4 2 4 4 q");
    readInput2 = new StringReader(input2.toString());
    controller2 = new MarbleSolitaireControllerImpl(europeanModel1, newView2, readInput2);
    controller2.playGame();

    String games;
    games = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n";

    assertEquals(games, print2.toString());
  }

  /**
   * Test if the game display works as we expect it if you play move and quit. (Triangle)
   */
  @Test
  public void testPlayMoveAndQuitGameIntegrationTriangle() {
    input3 = new StringBuilder("3 1 1 1 q");
    readInput3 = new StringReader(input3.toString());
    controller3 = new MarbleSolitaireControllerImpl(triangleModel1, newView3, readInput3);
    controller3.playGame();

    String games;
    games = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n";

    assertEquals(games, print3.toString());
  }

  /**
   * Test if the game display works as we expect it for invalid moves, characters, and game over.
   * (English)
   */
  @Test
  public void testInvalidMoveCharactersAndGameOverIntegrationEnglish() {
    input1 = new StringBuilder("6 4 4 4 3 4 5 5 3 4 5 4 1 4 3 4 4 2 4 4 4 5 4 3 $ 0 -1 3 " +
            "4 5 10 ) Z 4 10 4 5 4 7 4 5\n");
    readInput1 = new StringReader(input1.toString());
    controller1 = new MarbleSolitaireControllerImpl(englishModel1, newView1, readInput1);
    controller1.playGame();

    String games;
    games = "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 31\n" +
            "Invalid move. Play again.\n" +
            "    O O O\n" +
            "    O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 30\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 29\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 28\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 27\n" +
            "Please only enter a positive integer or quit game. Not: $\n" +
            "Please only enter a positive integer or quit game. Not: 0\n" +
            "Please only enter a positive integer or quit game. Not: -1\n" +
            "Invalid move. Play again.\n" +
            "Please only enter a positive integer or quit game. Not: )\n" +
            "Please only enter a positive integer or quit game. Not: Z\n" +
            "Invalid move. Play again.\n" +
            "Game over!\n" +
            "    O _ O\n" +
            "    O _ O\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O O O\n" +
            "    O _ O\n" +
            "    O O O\n" +
            "Score: 26\n";

    assertEquals(games, print1.toString());
  }

  /**
   * Test if the game display works as we expect it for invalid moves, characters, and game over.
   * (European)
   */
  @Test
  public void testInvalidMoveCharactersAndGameOverIntegrationEuropean() {
    input2 = new StringBuilder("2 4 4 4\n" +
            "5 4 3 4\n" +
            "7 4 5 4\n" +
            "4 2 4 4\n" +
            "4 5 4 3\n" +
            "4 7 4 5\n" +
            "2 6 4 6\n" +
            "5 6 3 6\n" +
            "2 2 4 2\n" +
            "5 2 3 2\n" +
            "6 2 6 4\n" +
            "6 5 6 3\n" +
            "5 4 5 6\n" +
            "5 7 5 5\n" +
            "4 5 6 5\n" +
            "7 5 5 5\n" +
            "2 5 4 5\n" +
            "5 5 3 5");
    readInput2 = new StringReader(input2.toString());
    controller2 = new MarbleSolitaireControllerImpl(europeanModel1, newView2, readInput2);
    controller2.playGame();

    String games;
    games = "    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 36\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 35\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O _ O O O\n" +
            "  O O O O O\n" +
            "    O O O\n" +
            "Score: 34\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 33\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O _ _ O O O O\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 32\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O _ O _ _ O O\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 31\n" +
            "    O O O\n" +
            "  O O _ O O\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 30\n" +
            "    O O O\n" +
            "  O O _ O _\n" +
            "O O O O O _ O\n" +
            "O _ O _ O O _\n" +
            "O O O O O O O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 29\n" +
            "    O O O\n" +
            "  O O _ O _\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O O O O O _ O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 28\n" +
            "    O O O\n" +
            "  _ O _ O _\n" +
            "O _ O O O O O\n" +
            "O O O _ O _ _\n" +
            "O O O O O _ O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 27\n" +
            "    O O O\n" +
            "  _ O _ O _\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O _ O O O _ O\n" +
            "  O O _ O O\n" +
            "    O _ O\n" +
            "Score: 26\n" +
            "    O O O\n" +
            "  _ O _ O _\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O _ O O O _ O\n" +
            "  _ _ O O O\n" +
            "    O _ O\n" +
            "Score: 25\n" +
            "    O O O\n" +
            "  _ O _ O _\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O _ O O O _ O\n" +
            "  _ O _ _ O\n" +
            "    O _ O\n" +
            "Score: 24\n" +
            "    O O O\n" +
            "  _ O _ O _\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O _ O _ _ O O\n" +
            "  _ O _ _ O\n" +
            "    O _ O\n" +
            "Score: 23\n" +
            "    O O O\n" +
            "  _ O _ O _\n" +
            "O O O O O O O\n" +
            "O _ O _ O _ _\n" +
            "O _ O _ O _ _\n" +
            "  _ O _ _ O\n" +
            "    O _ O\n" +
            "Score: 22\n" +
            "    O O O\n" +
            "  _ O _ O _\n" +
            "O O O O O O O\n" +
            "O _ O _ _ _ _\n" +
            "O _ O _ _ _ _\n" +
            "  _ O _ O O\n" +
            "    O _ O\n" +
            "Score: 21\n" +
            "    O O O\n" +
            "  _ O _ O _\n" +
            "O O O O O O O\n" +
            "O _ O _ _ _ _\n" +
            "O _ O _ O _ _\n" +
            "  _ O _ _ O\n" +
            "    O _ _\n" +
            "Score: 20\n" +
            "    O O O\n" +
            "  _ O _ _ _\n" +
            "O O O O _ O O\n" +
            "O _ O _ O _ _\n" +
            "O _ O _ O _ _\n" +
            "  _ O _ _ O\n" +
            "    O _ _\n" +
            "Score: 19\n" +
            "Game over!\n" +
            "    O O O\n" +
            "  _ O _ _ _\n" +
            "O O O O O O O\n" +
            "O _ O _ _ _ _\n" +
            "O _ O _ _ _ _\n" +
            "  _ O _ _ O\n" +
            "    O _ _\n" +
            "Score: 18\n";

    assertEquals(games, print2.toString());
  }

  /**
   * Test if the game display works as we expect it for invalid moves, characters, and game over.
   * (Triangle)
   */
  @Test
  public void testInvalidMoveCharactersAndGameOverIntegrationTriangle() {
    input3 = new StringBuilder("3 1 1 1\n" +
            "3 3 3 1\n" +
            "5 5 3 3\n" +
            "2 2 4 4\n" +
            "5 3 3 3\n" +
            "5 1 5 3\n" +
            "5 4 5 2\n" +
            "5 2 3 2\n" +
            "4 4 2 2\n" +
            "4 1 2 1\n" +
            "1 1 3 1\n" +
            "3 1 3 3\n" +
            "3 3 1 1");
    readInput3 = new StringReader(input3.toString());
    controller3 = new MarbleSolitaireControllerImpl(triangleModel1, newView3, readInput3);
    controller3.playGame();

    String games;
    games = "    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 14\n" +
            "    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 13\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O O\n" +
            "Score: 12\n" +
            "    O\n" +
            "   _ O\n" +
            "  O _ O\n" +
            " O O O _\n" +
            "O O O O _\n" +
            "Score: 11\n" +
            "    O\n" +
            "   _ _\n" +
            "  O _ _\n" +
            " O O O O\n" +
            "O O O O _\n" +
            "Score: 10\n" +
            "    O\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O O _ O\n" +
            "O O _ O _\n" +
            "Score: 9\n" +
            "    O\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O O _ O\n" +
            "_ _ O O _\n" +
            "Score: 8\n" +
            "    O\n" +
            "   _ _\n" +
            "  O _ O\n" +
            " O O _ O\n" +
            "_ O _ _ _\n" +
            "Score: 7\n" +
            "    O\n" +
            "   _ _\n" +
            "  O O O\n" +
            " O _ _ O\n" +
            "_ _ _ _ _\n" +
            "Score: 6\n" +
            "    O\n" +
            "   _ O\n" +
            "  O O _\n" +
            " O _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 5\n" +
            "    O\n" +
            "   O O\n" +
            "  _ O _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 4\n" +
            "    _\n" +
            "   _ O\n" +
            "  O O _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 3\n" +
            "    _\n" +
            "   _ O\n" +
            "  _ _ O\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 2\n" +
            "Game over!\n" +
            "    O\n" +
            "   _ _\n" +
            "  _ _ _\n" +
            " _ _ _ _\n" +
            "_ _ _ _ _\n" +
            "Score: 1\n";

    assertEquals(games, print3.toString());
  }
}