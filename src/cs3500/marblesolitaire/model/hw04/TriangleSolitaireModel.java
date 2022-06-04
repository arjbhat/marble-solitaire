package cs3500.marblesolitaire.model.hw04;

/**
 * Represents the model for a Triangle Solitaire Model. All common methods/features that it shares
 * with the English Solitaire Model and European Model have been moved to the Abstract Solitaire
 * Model.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * Initializes a Triangle Solitaire game board with 5 rows,
   * and with the empty slot right at the top.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Initializes the Triangle game board with a scale and with a default empty slot at the top.
   * @param scale The bottom-most row size (a positive odd integer)
   * @throws IllegalArgumentException If the scale size is not a positive number, or
   *                                  is 1 since the game would not be playable.
   */
  public TriangleSolitaireModel(int scale) throws IllegalArgumentException {
    this(scale, 0, 0);
  }

  /**
   * Initializes the Triangle game board with bottom row of size 5 and with an empty slot as
   * specified.
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the empty slot is out of bounds/is invalid
   */
  public TriangleSolitaireModel(int rowEmpty, int columnEmpty) throws IllegalArgumentException {
    this(5, rowEmpty, columnEmpty);
  }

  /**
   * Initializes the Triangle game board with a scale and an empty
   * slot.
   * @param scale The arm thickness (a positive odd integer)
   * @param rowEmpty The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException If the scale size is not a positive number, or
   *                                  is 1 since the game would not be playable, or if the empty
   *                                  slot is out of bounds/is invalid
   */
  public TriangleSolitaireModel(int scale, int rowEmpty, int columnEmpty)
          throws IllegalArgumentException {
    super(scale, rowEmpty, columnEmpty);
  }

  @Override
  // Can we construct a Triangle Marble Solitaire game?
  protected void canConstruct(int scale, int rowEmpty, int columnEmpty) {
    if (scale <= 0) {
      throw new IllegalArgumentException("Scale is not a positive number");
    }
    if (scale == 1) {
      throw new IllegalArgumentException("Scale is not playable");
    }
  }

  @Override
  // set the board size according to the Triangle Solitaire model specifications
  protected SlotState[][] setBoardSize(int scale) {
    return new SlotState[scale][scale];
  }

  @Override
  // Any moves available? (top, right, left, bottom, top left, or bottom right)
  protected boolean anyMovesLeft(int row, int col) {
    return super.anyMovesLeft(row, col)
            || validMove(row, col, row - 2, col - 2)
            || validMove(row, col, row + 2, col + 2);
  }

  @Override
  // Is the piece two slots away? (It's either in vertical, horizontal, top left, or bottom right)
  protected boolean twoSlotsAway(int fromRow, int fromCol, int toRow, int toCol) {
    return super.twoSlotsAway(fromRow, fromCol, toRow, toCol)
            || (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 2
            && Math.abs((fromRow + fromCol) - (toRow + toCol)) == 4);
  }

  // This board is constructed using some basic logic of equivalence
  protected boolean boardLogic(int scale, int toRow, int toCol) {
    return toCol <= toRow;
  }
}
