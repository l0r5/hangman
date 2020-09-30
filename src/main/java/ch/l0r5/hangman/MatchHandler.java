package ch.l0r5.hangman;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class MatchHandler {

    public MatchHandler() {
        initMatch();
    }

    public static void startNewMatch() {
        new MatchHandler();
    }

    private void initMatch() {
        List<String> wordList = provideWordList();
        Match match = new Match(wordList);
        match.start();
    }

    private static List<String> provideWordList() {
        List<String> doc = new ArrayList<>();
        String filePath = "src/main/resources/wortliste.txt";
        try {
            doc = readAllLines(new File(filePath).toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File couldn't be read from path: " + filePath);
        }
        return doc;
    }
}
