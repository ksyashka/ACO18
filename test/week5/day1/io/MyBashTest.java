package week5.day1.io;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by ksyashka on 18.02.2017.
 */
public class MyBashTest {
    private IBash iBash;

    @Before
    public void setUp() {
        iBash = new MyBash(".");
    }

    @Test
    public void pwdTest() throws Exception {
        String expected = "C:\\Users\\ksyashka\\IdeaProjects\\ACO18TeamProject\\KseniiaZhuravlova\\.";
        assertEquals(expected, iBash.pwd());

    }

    @Test
    public void cdTest() throws Exception {

    }

    @Test
    public void lsTest() throws Exception {

    }


    @Test
    public void writeIntoAndCatTest() throws Exception {
        String expected = "Hi there!";
        String path = "test.txt";
        iBash.writeInto(path, expected);
        assertEquals(expected, iBash.cat(path));
        File f = new File(path);
        f.delete();
    }

    @Test
    public void appendTest() throws Exception {
        String expected = "Hi there! ";
        String path = "test.txt";
        iBash.writeInto(path, expected);
        String add = "How are you?";
        iBash.append(path, add);
        assertEquals(expected + add, iBash.cat(path));
        File f = new File(path);
        f.delete();
    }

    @Test
    public void touchTest() throws Exception {
        assertTrue(iBash.touch("files/test.txt"));
        File f = new File("files/test.txt");
        assertTrue(f.exists());
        f.delete();
    }

    @Test(expected = SuchFileAlreadyExist.class)
    public void touchTestNegative() throws Exception {
        assertTrue(iBash.touch("files/test.txt"));
        assertTrue(iBash.touch("files/test.txt"));
        File f = new File("files/test.txt");
        f.delete();
    }


    @Test
    public void mkdirTest() throws Exception {
        assertTrue(iBash.mkdir("test/test"));
        File f = new File("test/test");
        assertTrue(f.exists());
        f.delete();
    }

    @Test(expected = SuchDirectoryAlreadyExist.class)
    public void mkdirTestNegative() throws Exception {
        assertTrue(iBash.mkdir("test"));
    }

    @Test
    public void findTest() throws Exception {

    }

}