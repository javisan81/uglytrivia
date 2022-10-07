package com.adaptionsoft.games.trivia.runner;


import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameRunnerGoldenMasterTest {

    private static List<String> readContent(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        return readStream(fileInputStream);
    }

    private static List<String> readStream(InputStream fileInputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
        List<String> result= new ArrayList<String>();
        while(reader.ready()) {
            result.add(reader.readLine());
        }
        return result;
    }

    @Test
    public void goldenMaster() throws IOException {
        ByteArrayOutputStream inMemoryStream = new ByteArrayOutputStream();

        PrintStream goldenMasterFile = new PrintStream(inMemoryStream);
        System.setOut(goldenMasterFile);
        for (int i = 0; i < 1000; i++) {
            Random random = new Random(i);
            GameRunner.runTheGame(random);
        }
        File file = new File("./goldenFile.txt");
        assertEquals(readStream(new ByteArrayInputStream(inMemoryStream.toByteArray())), readContent(file));
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream goldenMasterFile = new PrintStream(new FileOutputStream("./goldenFile.txt"));
        System.setOut(goldenMasterFile);
        for (int i = 0; i < 1000; i++) {
            Random random = new Random(i);
            GameRunner.runTheGame(random);
        }
    }
}