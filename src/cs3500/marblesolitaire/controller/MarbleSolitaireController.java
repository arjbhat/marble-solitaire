package cs3500.marblesolitaire.controller;

/**
 * This controller interface is used to interact with the whole program - the model and the view -
 * with some readable input. Its implementation is responsible for controlling the entire program.
 */
public interface MarbleSolitaireController {

  /**
   * The playGame method that the controller uses to communicate with the model and the view, using
   * input from the readable. User can input values (positive integers) to play the game or q/Q to
   * quit. If any individual value is unexpected (not a positive integer), it asks the user to
   * enter a different value. If they pass in an invalid move then it displays
   * "Invalid move. Play again." and keeps waiting for a proper move.
   *
   *
   * @throws IllegalStateException only if the controller is unable to successfully transmit output
   *                               to the appendable.
   */
  void playGame() throws IllegalStateException;

}