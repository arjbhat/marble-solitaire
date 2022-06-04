package cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw04.AbstractSolitaireModel;

/**
 * Represents the model for an English Solitaire Model. All common methods/features that it shares
 * with the European Solitaire Model and Triangle Model have been moved to the Abstract Solitaire
 * Model.
 */
public class EnglishSolitaireModel extends AbstractSolitaireModel {

  /**
   * Initializes an English Solitaire game board with an arm thickness of 3,
   * and with the empty slot in the centre.
   */
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Initializes the English game board with an arm thickness of 3 and with an empty slot.
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the empty slot is out of bounds/is invalid
   */
  public EnglishSolitaireModel(int rowEmpty, int columnEmpty) throws IllegalArgumentException {
    this(3, rowEmpty, columnEmpty);
  }

  /**
   * Initializes the English game board with a scale and with an empty slot in the centre.
   * @param scale The arm thickness (a positive odd integer)
   * @throws IllegalArgumentException If the scale size is not a positive number, is not odd, or
   *                                  is 1 since the game would not be playable.
   */
  public EnglishSolitaireModel(int scale) throws IllegalArgumentException {
    this(scale, scale + scale / 2 - 1, scale + scale / 2 - 1);
  }

  /**
   * Initializes the English game board for an English Solitaire Game with a scale and an
   * empty slot.
   * @param scale The arm thickness (a positive odd integer)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the scale size is not a positive number, is not odd, or
   *                                  is 1 since the game would not be playable, or if the empty
   *                                  slot is out of bounds/is invalid
   */
  public EnglishSolitaireModel(int scale, int rowEmpty, int columnEmpty)
          throws IllegalArgumentException {
    super(scale, rowEmpty, columnEmpty);
  }

  // This board is constructed using strip logic. The empty slot is either in the vertical strip or
  // it is in the horizontal strip.
  protected boolean boardLogic(int scale, int toRow, int toCol) {
    return ((toCol > scale - 2) && (toCol < 2 * scale - 1))
            || ((toRow > scale - 2) && (toRow < 2 * scale - 1));
  }

}