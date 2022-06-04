import java.io.IOException;
import java.nio.CharBuffer;

/**
 * A broken readable whose only job is to throw IOExceptions. We need to ensure that these are
 * being handled properly by the controller.
 */
public class BrokenReadable implements Readable {
  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}
