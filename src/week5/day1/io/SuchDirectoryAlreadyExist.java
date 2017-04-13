package week5.day1.io;

import java.io.IOException;

/**
 * Created by ksyashka on 25.02.2017.
 */
public class SuchDirectoryAlreadyExist extends IOException {
    public SuchDirectoryAlreadyExist(String message) {
        super(message);
    }
}
