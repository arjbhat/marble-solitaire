import java.util.Objects;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * A MockModel to test the Controller.
 */
public class MockModel implements MarbleSolitaireModel {
  private final StringBuilder log;

  MockModel(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    log.append(String.format("method: move - param: %d,%d,%d,%d\n",
              fromRow, fromCol, toRow, toCol));
  }

  @Override
  public boolean isGameOver() {
    log.append("method: isGameOver\n");
    return false;
  }

  @Override
  public int getBoardSize() {
    log.append("method: getBoardSize\n");
    return 0;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    log.append(String.format("method: move - param: %d,%d\n",
            row, col));
    return null;
  }

  @Override
  public int getScore() {
    log.append("method: getScore\n");
    return 0;
  }
}
