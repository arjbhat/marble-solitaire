package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * The abstract model for a marble solitaire game. This class represents common features/methods of
 * multiple solitaire implementations. This class is more oriented towards English and European
 * models because they take the majority - and are the more common implementations.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {
  private final int scale;
  private final int rowEmpty;
  private final int columnEmpty;
  private final SlotState[][] board;

  /**
   * Initializes the game board for any model that has a scale, empty row, and empty column.
   *
   * @param scale       The arm thickness (implementation depends on the type of model)
   * @param rowEmpty    The row of the empty slot
   * @param columnEmpty The column of the empty slot
   * @throws IllegalArgumentException depending on the implementation details of the type of model
   */
  public AbstractSolitaireModel(int scale, int rowEmpty, int columnEmpty)
          throws IllegalArgumentException {
    this.canConstruct(scale, rowEmpty, columnEmpty);

    this.scale = scale;
    this.rowEmpty = rowEmpty;
    this.columnEmpty = columnEmpty;
    board = this.setBoardSize(scale);
    this.makeBoard();
  }

  // set the board size according to specifications for English and European Solitaire
  // (will be different for a triangle)
  protected SlotState[][] setBoardSize(int scale) {
    return new SlotState[3 * scale - 2][3 * scale - 2];
  }

  // Can we construct an English Marble Solitaire game? If not then throw an error.
  protected void canConstruct(int scale, int rowEmpty, int columnEmpty) {
    if (scale <= 0) {
      throw new IllegalArgumentException("Scale is not a positive number");
    }
    if (scale == 1) {
      throw new IllegalArgumentException("Scale is not playable");
    }
    if (scale % 2 == 0) {
      throw new IllegalArgumentException("Scale is not an odd number");
    }
  }

  // Initializing the game board with Invalid, Marble, and Empty positions.
  private void makeBoard() throws IllegalArgumentException {
    // Enumerate a 2D array to represent the board
    for (int row = 0; row < this.getBoardSize(); row += 1) {
      for (int col = 0; col < this.getBoardSize(); col += 1) {
        // The col has restrictions in the vertical strip
        // The row has restrictions in the horizontal strip
        if (this.boardLogic(scale, row, col)) {
          // Set the slot to Marble if it passes the restrictions
          board[row][col] = SlotState.Marble;
        } else {
          // Else set it to Invalid
          board[row][col] = SlotState.Invalid;
        }
      }
    }

    // Throw an error if the empty piece is out of bounds or is Invalid.
    if (this.outOfBounds(rowEmpty, columnEmpty)
            || board[rowEmpty][columnEmpty] == SlotState.Invalid) {
      throw new IllegalArgumentException("Invalid empty cell position (" + rowEmpty + ","
              + columnEmpty + ")");
    }

    // Set that piece to Empty
    board[rowEmpty][columnEmpty] = SlotState.Empty;
  }

  // Board logic for each cell based on implementation
  // English Marble Solitaire: Strip logic
  // European Marble Solitaire: Manhattan distance from centre
  // Triangle Marble Solitaire: Manhattan distance from bottom left corner
  protected abstract boolean boardLogic(int scale, int row, int col);

  /**
   * Returns the size of the game. (number of rows or columns since)
   *
   * @return an int representing the size
   */
  @Override
  public int getBoardSize() {
    return board.length;
  }

  // Returns the slot state at the position given, else throws an exception.
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (this.outOfBounds(row, col)) {
      throw new IllegalArgumentException("Out of bounds");
    }
    return board[row][col];
  }

  // Is the position out of bounds? (Can't be negative or above board size)
  private boolean outOfBounds(int row, int col) {
    return row >= this.getBoardSize() || col >= this.getBoardSize() || row < 0 || col < 0;
  }

  // The score is the number of marbles currently on the board. (lower the better)
  @Override
  public int getScore() {
    int count = 0;
    // Go through and count all the marbles regardless of their position
    for (SlotState[] sArr : board) {
      for (SlotState s : sArr) {
        if (s == SlotState.Marble) {
          // Add to the count
          count += 1;
        }
      }
    }
    return count;
  }

  // Is the piece two slots away? (for English and European model)
  // (specifics depend on implementation)
  protected boolean twoSlotsAway(int fromRow, int fromCol, int toRow, int toCol) {
    return ((Math.abs(fromRow - toRow) == 2 && (fromCol - toCol == 0))
            ^ (Math.abs(fromCol - toCol) == 2 && (fromRow - toRow == 0)));
  }

  // Are any moves available? (for English and European model)
  // (need to check based on implementation)
  protected boolean anyMovesLeft(int row, int col) {
    return validMove(row, col, row - 2, col)
            || validMove(row, col, row, col + 2)
            || validMove(row, col, row + 2, col)
            || validMove(row, col, row, col - 2);
  }

  /**
   * The method for moving from one coordinate to another on the board.
   * A move is valid if all these conditions are true:
   *  - the "from" and "to" positions are valid (inside the board).
   *  - there is a marble at the specified "from" position.
   *  - the "to" position is empty.
   *  - the "to" and "from" positions are exactly two positions away - but more restrictions apply
   *  according to specification.
   *  - there is a marble in the slot between the "to" and "from" positions.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if any one of the conditions above are not true
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // Throws an exception if it's not a valid move
    if (!validMove(fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid Move");
    }

    // If it's a valid move then
    // The "from" slot will become empty because the marble has left it
    board[fromRow][fromCol] = SlotState.Empty;
    // The "middle" slot will become empty because we jumped over it
    board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = SlotState.Empty;
    // The "to" slot will get the marble
    board[toRow][toCol] = SlotState.Marble;
  }

  // Is this a valid move?
  protected boolean validMove(int fromRow, int fromCol, int toRow, int toCol)
          throws IllegalArgumentException {
    // To be valid, to and from must be inside the bounds
    return !outOfBounds(fromRow, fromCol) && !outOfBounds(toRow, toCol)
            // the "from" slot must be a marble
            && this.getSlotAt(fromRow, fromCol) == SlotState.Marble
            // the slot in the middle must be a marble
            && this.getSlotAt((fromRow + toRow) / 2, (fromCol + toCol) / 2)
            == SlotState.Marble
            // the "to" slot must be empty
            && this.getSlotAt(toRow, toCol) == SlotState.Empty
            // the move must be two slots away
            && this.twoSlotsAway(fromRow, fromCol, toRow, toCol);
  }

  /**
   * The game is over if there are no valid moves left for any marble on the board.
   * This method finds all marbles on the board,and if they have a valid move some spaces away
   * according to the game specification then the game is over.
   *
   * @return a boolean value that is true if the game is over and false if it is not.
   */
  @Override
  public boolean isGameOver() {
    for (int row = 0; row < this.getBoardSize(); row += 1) {
      for (int col = 0; col < this.getBoardSize(); col += 1) {
        // Find the marbles on the board
        if (board[row][col] == SlotState.Marble) {
          // and if they have a valid move some spaces away according to implementation
          if (this.anyMovesLeft(row, col)) {
            return false;
          }
        }
      }
    }
    // else the game is over
    return true;
  }
}