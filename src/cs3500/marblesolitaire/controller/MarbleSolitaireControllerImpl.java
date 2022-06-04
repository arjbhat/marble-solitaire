package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * The Controller of the Marble Solitaire game. It is used to play the game and communicates with
 * the model and the view.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable read;

  /**
   * Initializes the controller with a non-null model, view, and readable input.
   *
   * @param model The model that we're operating on
   * @param view The view that we're displaying to
   * @param read The readable that we're getting inputs from
   * @throws IllegalArgumentException if the model, view, or readable are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model,
                                       MarbleSolitaireView view,
                                       Readable read) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Invalid model of type null");
    }
    if (view == null) {
      throw new IllegalArgumentException("Invalid view of type null");
    }
    if (read == null) {
      throw new IllegalArgumentException("Invalid readable of type null");
    }

    this.model = model;
    this.view = view;
    this.read = read;

  }

  /**
   * The playGame method that the controller uses to communicate with the model and the view, using
   * input from the readable. User can input values (positive integers) to play the game or q/Q to
   * quit. If any individual value is unexpected (not a positive integer), it asks the user to
   * enter a different value. If they pass in an invalid move then it displays
   * "Invalid move. Play again." and keeps waiting for a proper move.
   *
   * @throws IllegalStateException only if the controller is unable to successfully transmit output
   *                               to the appendable.
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner sc = new Scanner(read);
    // move stored as an array: [fromRow][fromCol][toRow][toCol]
    int[] move = new int[4];
    // game starts off with the user not quitting
    boolean isGameQuit = false;

    // while the game is not over and the user has not quit keep looping
    while (!model.isGameOver() && !isGameQuit) {
      // render the board
      this.printBoard();

      // iterate through the inputs filling up the move array while the game is not quit
      int i = 0;
      while (i < 4 && !isGameQuit) {
        if (!sc.hasNext()) {
          throw new IllegalStateException("Unable to successfully receive input");
        }

        // We get a string input
        String strInput = sc.next();

        // If it is q then quit the game
        if (strInput.equalsIgnoreCase("q")) {
          isGameQuit = true;
          // If it is a positive int then add it to our move
        } else if (posInt(strInput)) {
          move[i] = Integer.parseInt(strInput) - 1;

          i += 1;

          // if the array is full, but you can't move then set it to 0 and try again.
          // else you make the move. You shouldn't render the board again.
          if (i == 4 && !canMove(move[0], move[1], move[2], move[3])) {
            i = 0;
          }

        } else {
          this.printMessage("Please only enter a positive integer or quit game. Not: "
                  + strInput);
        }
      }
    }

    // If game is not quit then the game is over!
    if (isGameQuit) {
      this.printMessage("Game quit!");
      this.printMessage("State of game when quit:");
    } else {
      this.printMessage("Game over!");
    }
    this.printBoard();

  }

  // Try making a move, and if you can then return true.
  private boolean canMove(int fromRow, int fromCol, int toRow, int toCol) {
    try {
      this.model.move(fromRow, fromCol, toRow, toCol);
      return true;
    } catch (IllegalArgumentException exe) {
      this.printMessage("Invalid move. Play again.");
      return false;
    }
  }

  // Is this a positive integer? 0 is not a positive integer.
  private static boolean posInt(String s) {
    if (s == null) {
      return false;
    }
    try {
      int i = Integer.parseInt(s);
      return i > 0;
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  // Print the message you want to send
  private void printMessage(String s) {
    try {
      this.view.renderMessage(s + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to successfully transmit output");
    }
  }

  // Print the game board with the score
  private void printBoard() {
    try {
      this.view.renderBoard();
      this.printMessage(String.format("\n" + "Score: %d", model.getScore()));
    } catch (IOException e) {
      throw new IllegalStateException("Unable to successfully transmit output");
    }
  }
}