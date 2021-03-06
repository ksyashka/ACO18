package week5.day1.io;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ksyashka on 18.02.2017.
 */
public class MyBash implements IBash {

    private File currentDir;

    public MyBash(String path) {
        File currentDir = new File(path);
        if (currentDir.exists() && currentDir.isDirectory()) {
            this.currentDir = currentDir;
        } else {
            throw new NoSuchDirectoryException("No such directory");
        }
    }

    @Override
    public String pwd() {
        return currentDir.getAbsolutePath();
    }

    @Override
    public String cd(String path) {
        File newPath = new File(path);

        if (newPath.isDirectory()) {
            currentDir = newPath;
            return pwd();
        }
        return null;
    }

    @Override
    public List<File> ls() {
        return Arrays.asList(currentDir.listFiles());
    }

    @Override
    public String cat(String path) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((line = reader.readLine()) != null)
                result.append(line);

        }  catch(IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    @Override
    public boolean writeInto(String path, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean append(String path, String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean touch(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) return file.createNewFile();
        else throw new SuchFileAlreadyExist("Such file already exist");
    }

    @Override
    public boolean mkdir(String dirPath) throws SuchDirectoryAlreadyExist {
        File file = new File(dirPath);
        if (!file.exists()) return file.mkdir();
        else throw new SuchDirectoryAlreadyExist("Such directory already exist");
    }

    @Override
    public List<File> find(String searchKey, String startPointPath) {
        return null;
    }
}
