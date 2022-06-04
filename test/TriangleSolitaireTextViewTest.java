import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * To test the TriangleSolitaireTextView with different constructors and to see if it works when
 * you play valid moves.
 */
public class TriangleSolitaireTextViewTest {

  private TriangleSolitaireTextView view1;
  private TriangleSolitaireTextView view2;
  private TriangleSolitaireTextView view3;
  private TriangleSolitaireTextView view4;
  private TriangleSolitaireTextView view5;
  private TriangleSolitaireTextView newView1;
  private TriangleSolitaireTextView newView2;
  private TriangleSolitaireTextView newView3;
  private TriangleSolitaireTextView newView4;
  private TriangleSolitaireTextView newView5;
  private TriangleSolitaireModel triangleModel1;
  private TriangleSolitaireModel triangleModel3;
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
    triangleModel1 = new TriangleSolitaireModel();
    // empty in middle top
    TriangleSolitaireModel triangleModel2 = new TriangleSolitaireModel(4, 4);
    triangleModel3 = new TriangleSolitaireModel(2);
    TriangleSolitaireModel triangleModel4 = new TriangleSolitaireModel(5, 4, 3);
    TriangleSolitaireModel triangleModel5 = new TriangleSolitaireModel(7);

    // The outputs of each
    print1 = new StringBuilder();
    print2 = new StringBuilder();
    print3 = new StringBuilder();
    print4 = new StringBuilder();
    print5 = new StringBuilder();

    view1 = new TriangleSolitaireTextView(triangleModel1);
    view2 = new TriangleSolitaireTextView(triangleModel2);
    view3 = new TriangleSolitaireTextView(triangleModel3);
    view4 = new TriangleSolitaireTextView(triangleModel4);
    view5 = new TriangleSolitaireTextView(triangleModel5);

    newView1 = new TriangleSolitaireTextView(triangleModel1, print1);
    newView2 = new TriangleSolitaireTextView(triangleModel2, print2);
    newView3 = new TriangleSolitaireTextView(triangleModel3, print3);
    newView4 = new TriangleSolitaireTextView(triangleModel4, print4);
    newView5 = new TriangleSolitaireTextView(triangleModel5, print5);
  }

  @Test
  public void testTextViewException() {
    try {
      view1 = new TriangleSolitaireTextView(null);
      fail("Reason: We don't fail to construct - even when we should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid model of type null", e.getMessage());
    }
  }

  @Test
  public void testToString() {
    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", view1.toString());

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O _", view2.toString());

    assertEquals(" _\n" +
            "O O", view3.toString());

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O _ O", view4.toString());

    assertEquals("      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", view5.toString());

    assertEquals("    _\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O O", newView1.toString());

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O O _", newView2.toString());

    assertEquals(" _\n" +
            "O O", newView3.toString());

    assertEquals("    O\n" +
            "   O O\n" +
            "  O O O\n" +
            " O O O O\n" +
            "O O O _ O", newView4.toString());

    assertEquals("      _\n" +
            "     O O\n" +
            "    O O O\n" +
            "   O O O O\n" +
            "  O O O O O\n" +
            " O O O O O O\n" +
            "O O O O O O O", newView5.toString());

    triangleModel1.move(2, 0, 0, 0);

    assertEquals("    O\n" +
            "   _ O\n" +
            "  _ O O\n" +
            " O O O O\n" +
            "O O O O O", view1.toString());
  }

  // Test the exceptions both of them throw if either argument is null (model or appendable)
  // for the constructor

  @Test
  public void testTextViewExceptionNewConstructorNullModel() {
    try {
      newView1 = new TriangleSolitaireTextView(null, this.print1);
      fail("Reason: We don't fail to construct - even when we should");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid model of type null", e.getMessage());
    }
  }

  @Test
  public void testTextViewExceptionNewConstructorNullAppendable() {
    try {
      newView1 = new TriangleSolitaireTextView(triangleModel3, null);
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

    triangleModel1.move(2, 0, 0, 0);

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
    this.newView1 = new TriangleSolitaireTextView(triangleModel1, brokenAppendable);

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