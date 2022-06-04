import java.io.IOException;
import java.util.Objects;

import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * A MockView to test the Controller.
 */
public class MockView implements MarbleSolitaireView {
  private final StringBuilder log;

  MockView(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void renderBoard() throws IOException {
    log.append("method: renderBoard\n");
  }

  @Override
  public void renderMessage(String message) throws IOException {
    String stripMessage = message.strip();
    log.append("method: renderMessage - message: ").append(stripMessage).append("\n");
  }
}
