package cs3500.marblesolitaire.factory;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Factory class to produce objects of the English Solitaire Model.
 */
public class EnglishSolitaireFactory extends MarbleSolitaireFactory {
  /**
   * Constructor that creates the required model for the English Solitaire Model.
   *
   * @param scale The arm thickness (a positive odd integer)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   */
  public EnglishSolitaireFactory(int scale, int rowEmpty, int columnEmpty) {
    super(new EnglishSolitaireModel(scale, rowEmpty, columnEmpty));
  }
}
