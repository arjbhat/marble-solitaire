package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * The View of the Triangle Marble Solitaire Game. - Only difference being the toString method
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {
  /**
   * Constructor for our Triangle Marble Solitaire Game View.
   *
   * @param model the model that we are trying to print
   * @throws IllegalArgumentException If the model is of type null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model)
          throws IllegalArgumentException {
    super(model);
  }

  /**
   * Second Constructor for our Triangle Marble Solitaire Game View that uses an appendable object
   * for its destination.
   *
   * @param model       the model that we are trying to print
   * @param destination the appendable object that this view uses as its destination
   * @throws IllegalArgumentException If the model is of type null or appendable is of type null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable destination)
          throws IllegalArgumentException {
    super(model, destination);
  }

  // toString that prepends some n number of spaces before each row such that the triangle model is
  // displayed as an equilateral triangle rather than just a right angle triangle.
  @Override
  public String toString() {
    // local variable to hold information to be accumulated
    StringBuilder textView = new StringBuilder();
    StringBuilder[] sbList = this.imageCreator();

    for (int i = 0; i < sbList.length; i += 1) {
      textView.append(" ".repeat(sbList.length - 1 - i)).append(sbList[i].toString());
    }

    // convert to string and strip trailing whitespace
    return textView.toString().stripTrailing();
  }
}
