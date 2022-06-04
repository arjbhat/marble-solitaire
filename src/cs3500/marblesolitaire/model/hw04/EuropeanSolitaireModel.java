package cs3500.marblesolitaire.model.hw04;

/**
 * Represents the model for a European Solitaire Model. All common methods/features that it shares
 * with the English Solitaire Model and Triangle Model have been moved to the Abstract Solitaire
 * Model.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * Initializes a European Solitaire game board with an arm thickness of 3,
   * and with the empty slot in the centre.
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Initializes the European game board with a scale and with an empty slot in the centre.
   * @param scale The arm thickness (a positive odd integer)
   * @throws IllegalArgumentException If the scale size is not a positive number, is not odd, or
   *                                  is 1 since the game would not be playable.
   */
  public EuropeanSolitaireModel(int scale) throws IllegalArgumentException {
    this(scale, scale + scale / 2 - 1, scale + scale / 2 - 1);
  }

  /**
   * Initializes the European game board with an arm thickness of 3 and with an empty slot.
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the empty slot is out of bounds/is invalid
   */
  public EuropeanSolitaireModel(int rowEmpty, int columnEmpty) throws IllegalArgumentException {
    this(3, rowEmpty, columnEmpty);
  }

  /**
   * Initializes the European game board for an English Solitaire Game with a scale and an empty
   * slot.
   * @param scale The arm thickness (a positive odd integer)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the scale size is not a positive number, is not odd, or
   *                                  is 1 since the game would not be playable, or if the empty
   *                                  slot is out of bounds/is invalid
   */
  public EuropeanSolitaireModel(int scale, int rowEmpty, int columnEmpty)
          throws IllegalArgumentException {
    super(scale, rowEmpty, columnEmpty);
  }

  // This board is constructed using manhattan distance logic. If it's far enough then it's not
  // a part of the game.
  protected boolean boardLogic(int scale, int toRow, int toCol) {
    int centre = scale + scale / 2 - 1;
    return (this.manhattanDistance(centre, centre, toRow, toCol) < (2 * scale - 1));
  }

  // We use New Yorker logic for European and Triangle model because all the valid squares
  // are a certain number of blocks away...
  private int manhattanDistance(int fromRow, int fromCol, int toRow, int toCol) {
    return Math.abs(fromRow - toRow) + Math.abs(fromCol - toCol);
  }
}
