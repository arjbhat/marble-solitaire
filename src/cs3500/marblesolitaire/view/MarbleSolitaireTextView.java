package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents the View component of the Marble Solitaire Game.
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState model;
  private final Appendable destination;

  /**
   * Constructor for our Marble Solitaire Game View.
   *
   * @param model the model that we are trying to print
   * @throws IllegalArgumentException If the model is of type null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Invalid model of type null");
    }
    this.model = model;
    this.destination = System.out;
  }

  /**
   * Second Constructor for our Marble Solitaire Game View that uses an appendable object for its
   * destination.
   *
   * @param model the model that we are trying to print
   * @param destination the appendable object that this view uses as its destination
   * @throws IllegalArgumentException If the model is of type null or appendable is of type null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Invalid model of type null");
    }
    if (destination == null) {
      throw new IllegalArgumentException("Invalid appendable of type null");
    }
    this.model = model;
    this.destination = destination;
  }

  // Method to generate a text view of the game
  @Override
  public String toString() {
    // local variable to hold information to be accumulated
    StringBuilder textView = new StringBuilder();
    StringBuilder[] sbList = this.imageCreator();

    for (StringBuilder sb : sbList) {
      textView.append(sb.toString());
    }

    // convert to string and strip trailing whitespace
    return textView.toString().stripTrailing();
  }

  // Method that creates a list of StringBuilders that we can work with
  protected StringBuilder[] imageCreator() {
    StringBuilder[] sbList = new StringBuilder[model.getBoardSize()];

    for (int row = 0; row < model.getBoardSize(); row += 1) {
      // local variable to hold information to be accumulated every row
      StringBuilder textView = new StringBuilder();
      for (int col = 0; col < model.getBoardSize(); col += 1) {
        switch (model.getSlotAt(row, col)) {
          // appending space for Invalid
          case Invalid: textView.append(" ");
            break;
          // appending O for Marble
          case Marble: textView.append("O");
            break;
          // appending _ for Empty
          case Empty: textView.append("_");
            break;
          default:
            // Never reaches this because our toString does not support any other state
            throw new IllegalStateException("View does not support this state");
        }
        // append a space after every slot
        textView.append(" ");
      }
      // remove any trailing whitespace (invalid pieces)
      textView = new StringBuilder(textView.toString().stripTrailing());
      // append a new line
      textView.append("\n");
      sbList[row] = textView;
    }

    return sbList;
  }

  // Render board without appending a new-line at the end
  public void renderBoard() throws IOException {
    destination.append(this.toString());
  }

  public void renderMessage(String message) throws IOException {
    destination.append(message);
  }
}