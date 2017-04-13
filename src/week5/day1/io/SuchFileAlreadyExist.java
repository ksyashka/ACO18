package week5.day1.io;

import java.io.IOException;

/**
 * Created by ksyashka on 21.02.2017.
 */
public class SuchFileAlreadyExist extends IOException{
    public SuchFileAlreadyExist(String message) {
        super(message);
    }
}
