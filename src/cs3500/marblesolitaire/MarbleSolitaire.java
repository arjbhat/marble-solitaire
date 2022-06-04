package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.factory.MarbleSolitaireAbstractFactory;
import cs3500.marblesolitaire.factory.MarbleSolitaireFactoryBuilder;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Class used to run the different Configurations of the game via command line.
 */
public final class MarbleSolitaire {

  /**
   * Main method to run and play different kinds of Marble Solitaire Game configurations.
   *
   *    @param args input the type of Marble Solitaire (english, triangular, european)
   *                along with (optionally) the size of the board and the empty hole location
   */
  public static void main(String[] args) {
    MarbleSolitaireAbstractFactory factory;

    if (args.length > 0) {
      MarbleSolitaireFactoryBuilder builder = new MarbleSolitaireFactoryBuilder(args[0]);
      for (int i = 0; i < args.length; i += 1) {
        switch (args[i]) {
          case "-size":
            builder.scale(Integer.parseInt(args[i + 1]));
            break;
          case "-hole":
            builder.rowEmpty(Integer.parseInt(args[i + 1]))
                   .columnEmpty(Integer.parseInt(args[i + 2]));
            break;
          default:
            break;
        }
      }
      factory = builder.build();
    } else {
      throw new IllegalArgumentException("Invalid configuration for game");
    }

    MarbleSolitaireModel model = factory.createModel();
    MarbleSolitaireView view = factory.createView();
    Readable rd = new InputStreamReader(System.in);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, rd);
    controller.playGame();
  }
}