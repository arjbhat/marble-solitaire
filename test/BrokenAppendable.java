import java.io.IOException;

/**
 * A broken appendable whose only job is to throw IOExceptions. We need to ensure that these are
 * being handled properly by the view and the controller.
 */
public class BrokenAppendable implements Appendable {
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}