import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * To test the MarbleSolitaireTextView with different constructors and to see if it works when
 * you play valid moves.
 */
public class MarbleSolitaireTextViewTest {

  private MarbleSolitaireTextView view1;
  private MarbleSolitaireTextView view2;
  private MarbleSolitaireTextView view3;
  private MarbleSolitaireTextView view4;
  private MarbleSolitaireTextView view5;
  private MarbleSolitaireTextView view1b;
  private MarbleSolitaireTextView view2b;
  private MarbleSolitaireTextView view3b;
  private MarbleSolitaireTextView view4b;
  private MarbleSolitaireTextView view5b;
  private MarbleSolitaireTextView newView1;
  private MarbleSolitaireTextView newView2;
  private MarbleSolitaireTextView newView3;
  private MarbleSolitaireTextView newView4;
  private MarbleSolitaireTextView newView5;
  private MarbleSolitaireModel englishModel1;
  private MarbleSolitaireModel englishModel3;
  private MarbleSolitaireModel europeanModel2;
  private MarbleSolitaireModel europeanModel3;
  private MarbleSolitaireModel europeanModel4;
  private MarbleSolitaireModel europeanModel5;
  private StringBuilder print1;
  private StringBuilder print2;
  private StringBuilder print3;
  private StringBuilder print4;
  private StringBuilder print5;


  /**
   * Initializing valid game states.
   * Test the view constructors (both 1 argument and 2 argument)
   */
  @Before
  public void init() {
    englishModel1 = new EnglishSolitaireModel();
    // empty in middle top
    MarbleSolitaireModel englishModel2 = new EnglishSolitaireModel(0, 3);
    englishModel3 = new EnglishSolitaireModel(5); // arm thickness of 5
    MarbleSolitaireModel englishModel4 = new EnglishSolitaireModel(5, 6, 7);
    MarbleSolitaireModel englishModel5 = new EnglishSolitaireModel(7);

    MarbleSolitaireModel europeanModel1 = new EuropeanSolitaireModel();
    // empty in middle top
    MarbleSolitaireModel europeanModel2 = new EuropeanSolitaireModel(0, 3);
    europeanModel3 = new EuropeanSolitaireModel(5); // arm thickness of 5
    MarbleSolitaireModel europeanModel4 = new EuropeanSolitaireModel(5, 6, 7);
    MarbleSolitaireModel europeanModel5 = new EuropeanSolitaireModel(7);

    // The outputs of each
    print1 = new StringBuilder();
    print2 = new StringBuilder();
    print3 = new StringBuilder();
    print4 = new StringBuilder();
    print5 = new StringBuilder();

    view1 = new MarbleSolitaireTextView(englishModel1);
    view2 = new MarbleSolitaireTextView(englishModel2);
    view3 = new MarbleSolitaireTextView(englishModel3);
    view4 = new MarbleSolitaireTextView(englishModel4);
    view5 = new MarbleSolitaireTextView(englishModel5);

    view1b = new MarbleSolitaireTextView(europeanModel1);
    view2b = new MarbleSolitaireTextView(europeanModel2);
    view3b = new MarbleSolitaireTextView(europeanModel3);
    view4b = new MarbleSolitaireTextView(europeanModel4);
    view5b = new MarbleSolitaireTextView(europeanModel5);

    newView1 = new MarbleSolitaireTextView(englishModel1, print1);
    newView2 = new MarbleSolitaireTextView(englishModel2, print2);
    newView3 = new MarbleSolitaireTextView(englishModel3, print3);
    newView4 = new MarbleSolitaireTextView(englishModel4, print4);
    newView5 = new MarbleSolitaireTextView(englishModel5, print5);
  }

  @Test
  public void testTextViewException() {
    try {
      view1 = new MarbleSolitaireTextView(null);
      fail("Reason: We don't fail to construct - even when we should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid model of type null", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view1.toString());

    assertEquals("    O O O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view1b.toString());

    assertEquals("    O _ O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", view2.toString());

    assertEquals("    O _ O\n" +
            "  O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "  O O O O O\n" +
            "    O O O", view2b.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view3.toString());

    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view3b.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view4.toString());

    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view4b.toString());

    assertEquals("            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O", view5.toString());

    assertEquals("            O O O O O O O\n" +
            "          O O O O O O O O O\n" +
            "        O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O O O O O O O\n" +
            "    O O O O O O O O O O O O O O O\n" +
            "      O O O O O O O O O O O O O\n" +
            "        O O O O O O O O O O O\n" +
            "          O O O O O O O O O\n" +
            "            O O O O O O O", view5b.toString());

    assertEquals("    O O O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O _ O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", newView1.toString());

    assertEquals("    O _ O\n" +
            "    O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "O O O O O O O\n" +
            "    O O O\n" +
            "    O O O", newView2.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O _ O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", newView3.toString());

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", newView4.toString());

    assertEquals("            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O _ O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O\n" +
            "            O O O O O O O", newView5.toString());

    englishModel3.move(6, 8, 6, 6);

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view3.toString());

    englishModel3.move(4, 7, 6, 7);

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view3.toString());

    englishModel3.move(2, 7, 4, 7);

    assertEquals("        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O _ O\n" +
            "        O O O _ O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view3.toString());

    englishModel3.move(0, 7, 2, 7);
    assertEquals("        O O O _ O\n" +
            "        O O O _ O\n" +
            "        O O O O O\n" +
            "        O O O _ O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view3.toString());

    englishModel3.move(0, 5, 0, 7);
    assertEquals("        O _ _ O O\n" +
            "        O O O _ O\n" +
            "        O O O O O\n" +
            "        O O O _ O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view3.toString());

    // Testing if it works with a _ near the far right side.

    englishModel3.move(0, 8, 0, 6);
    assertEquals("        O _ O _ _\n" +
            "        O O O _ O\n" +
            "        O O O O O\n" +
            "        O O O _ O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O\n" +
            "        O O O O O", view3.toString());

    // Testing if we can move and display for a European Model

    europeanModel3.move(6, 8, 6, 6);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view3b.toString());

    europeanModel3.move(4, 8, 6, 8);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view3b.toString());

    europeanModel3.move(8, 7, 6, 7);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view3b.toString());

    europeanModel3.move(4, 10, 4, 8);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O _ _ O O\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view3b.toString());

    europeanModel3.move(4, 12, 4, 10);
    assertEquals("        O O O O O\n" +
            "      O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "O O O O O O O O O _ O _ _\n" +
            "O O O O O O O O _ O O O O\n" +
            "O O O O O O O O O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "O O O O O O O _ O O O O O\n" +
            "  O O O O O O O O O O O\n" +
            "    O O O O O O O O O\n" +
            "      O O O O O O O\n" +
            "        O O O O O", view3b.toString());
  }

  // Test the exceptions both of them throw if either argument is null (model or appendable)
  // for the constructor

  @Test
  public void testTextViewExceptionNewConstructorNullModel() {
    try {
      newView1 = new MarbleSolitaireTextView(null, this.print1);
      fail("Reason: We don't fail to construct - even when we should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid model of type null", e.getMessage());
    }
  }

  @Test
  public void testTextViewExceptionNewConstructorNullAppendable() {
    try {
      newView1 = new MarbleSolitaireTextView(englishModel3, null);
      fail("Reason: We don't fail to construct - even when we should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid appendable of type null", e.getMessage());
    }
  }

  // Do we correctly transmit to the appendable? (renderMessage)
  // Try on different kinds of messages.
  @Test
  public void testAppendableMessageTransmission() {
    assertEquals("", print1.toString());
    assertEquals("", print2.toString());
    assertEquals("", print3.toString());
    assertEquals("", print4.toString());
    assertEquals("", print5.toString());

    tryRenderMessage(newView1, print1, "Hello! ", "Hello! ");
    tryRenderMessage(newView2, print2, "My ", "My ");
    tryRenderMessage(newView3, print3, "name ", "name ");
    tryRenderMessage(newView4, print4, "is ", "is ");
    tryRenderMessage(newView5, print5, "Arj.", "Arj.");

    tryRenderMessage(newView1, print1, print2.toString(), "Hello! My ");
    tryRenderMessage(newView1, print1, print3.toString(), "Hello! My name ");
    tryRenderMessage(newView1, print1, print4.toString(), "Hello! My name is ");
    tryRenderMessage(newView1, print1, print5.toString(), "Hello! My name is Arj.");

    assertEquals("Hello! My name is Arj.", print1.toString());
  }

  // Do we correctly transmit to the appendable? (renderBoard)
  // Try on different kinds of boards and with different messages. (renderBoard correctly
  // appends the toString exactly as before)
  @Test
  public void testAppendableBoardTransmission() {

    tryRenderBoard(newView1);
    assertEquals(newView1.toString(), print1.toString());
    // Expected: Same as toString Actual: Appendable output

    String toStringCopy = newView1.toString(); // Saves a copy since Strings don't mutate
    // Expected: Same as toString Actual: Appendable output

    englishModel1.move(1, 3, 3, 3);

    tryRenderBoard(newView1);
    assertEquals(toStringCopy + newView1.toString(), print1.toString());
    // Expected: Same as Old toString + New toString Actual: Appendable output gets both

    tryRenderBoard(newView2);
    assertEquals(newView2.toString(), print2.toString());
    // Expected: Same as toString Actual: Appendable output

    tryRenderBoard(newView3);
    assertEquals(newView3.toString(), print3.toString());
    // Expected: Same as toString Actual: Appendable output

    tryRenderBoard(newView4);
    assertEquals(newView4.toString(), print4.toString());
    // Expected: Same as toString Actual: Appendable output

    tryRenderBoard(newView5);
    assertEquals(newView5.toString(), print5.toString());
    // Expected: Same as toString Actual: Appendable output
  }

  // To abstract renderBoard
  private void tryRenderBoard(MarbleSolitaireView view) {
    try {
      view.renderBoard();
    } catch (IOException e) {
      // Do nothing.
    }
  }

  // To abstract renderMessage - the view / the destination / the message / expected message
  private void tryRenderMessage(MarbleSolitaireView view, StringBuilder print, String msg,
                                String expected) {
    try {
      view.renderMessage(msg);
      assertEquals(expected, print.toString());
    } catch (IOException e) {
      // Do nothing.
    }
  }

  // Testing if they both throw an IOException if transmission to provided destination fails
  @Test
  public void testIOException() {
    Appendable brokenAppendable = new BrokenAppendable();
    this.newView1 = new MarbleSolitaireTextView(englishModel1, brokenAppendable);

    try {
      this.newView1.renderMessage("Hello");
      fail("We shouldn't be able to render a message " +
              "because appendable throws IOException");
    } catch (IOException e) {
      assertEquals("java.io.IOException", e.toString());
    }

    try {
      this.newView1.renderBoard();
      fail("We shouldn't be able to render a board " +
              "because appendable throws IOException");
    } catch (IOException e) {
      assertEquals("java.io.IOException", e.toString());
    }
  }

  // We don’t throw an exception for a null String (renderMessage) - it is appended to the message
  @Test
  public void testNullString() {
    try {
      this.newView1.renderMessage(null);
      assertEquals(print1.toString(), "null");
    } catch (IOException e) {
      throw new IllegalStateException("Should not throw an exception according to " +
              "Appendable interface");
    }
  }
}