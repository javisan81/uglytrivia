package com.adaptionsoft.games.trivia.runner;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameRunnerTest {

    @Test
    public void goldenMaster() throws IOException {
        ByteArrayOutputStream inMemoryStream = new ByteArrayOutputStream();
        PrintStream gamesResults= new PrintStream(inMemoryStream);
        System.setOut(gamesResults);
        for(int i=0;i<5000;i++){
            Random random = new Random(i);
            GameRunner.runTheGame(random);
        }
        File file = new File("./goldenMaster.txt");
        assertEquals(readStream(new ByteArrayInputStream(inMemoryStream.toByteArray())), readContent(file));
    }

    public static void main(String [] args) throws FileNotFoundException {
        PrintStream goldenMasterFile = new PrintStream(new FileOutputStream("./goldenMaster.txt"));
        System.setOut(goldenMasterFile);
        for(int i=0;i<5000;i++) {
            Random random = new Random(i);
            GameRunner.runTheGame(random);
        }
        goldenMasterFile.close();
    }

    private static List<String> readContent(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return readStream(fileInputStream);
    }

    private static List<String> readStream(InputStream byteArrayInputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        List<String> result= new ArrayList<>();
        while(reader.ready()) {
            result.add(reader.readLine());
        }
        return result;
    }
}
