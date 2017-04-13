package week5.day1.io;

import java.io.IOException;

/**
 * Created by ksyashka on 21.02.2017.
 */
public class NoSuchDirectoryException extends RuntimeException {
    public NoSuchDirectoryException(String s) {
        super (s);
    }
}
