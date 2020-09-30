package ch.l0r5.hangman;

import java.util.*;

public class Match {

    private final List<String> wordList;
    List<Character> collectedGuesses;
    int hangmanCount;
    boolean isGameOver;
    String currentGuessState;

    public Match(List<String> wordList) {
        this.wordList = wordList;
        this.collectedGuesses = new ArrayList<>();
        this.hangmanCount = 0;
        this.isGameOver = false;
    }

    public void start() {
        System.out.println("Welcome to Hangman!");
        Difficulty difficulty = promptDifficulty();
        String solution = getRandomWord(this.wordList, difficulty);
        this.currentGuessState = maskSolution(solution);
        System.out.println("Word to guess: " + this.currentGuessState);

        while (!isGameOver) {
            char guessChar = promptGuessChar();
            checkGuess(guessChar, solution);
        }
    }

    private static Difficulty promptDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose difficulty:\nEASY(0)\nMEDIUM(1)\nHARD(2)\nEnter the number next to the difficulty: ");
        Difficulty difficulty = Difficulty.values()[scanner.nextInt()];
        System.out.println("Your choice: " + difficulty);
        return difficulty;
    }

    private static String maskSolution(String word) {
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            result = result.concat("*");
        }
        return result;
    }

    private static String getRandomWord(List<String> wordList, Difficulty difficulty) {
        Random rand = new Random();
        String randomWord = wordList.get(rand.nextInt(wordList.size()));
        switch (difficulty) {
            case EASY:
                if (randomWord.length() >= 2 && randomWord.length() <= 8) {
                    return randomWord;
                }
                break;
            case MEDIUM:
                if (randomWord.length() >= 9 && randomWord.length() <= 12) {
                    return randomWord;
                }
                break;
            case HARD:
                if (randomWord.length() > 12) {
                    return randomWord;
                }
                break;
        }
        return getRandomWord(wordList, difficulty);
    }

    private void checkGuess(char guessChar, String solution) {
        char[] solutionChars = solution.toLowerCase().toCharArray();
        char[] currentStateArr = this.currentGuessState.toCharArray();
        if (solution.toLowerCase().contains(String.valueOf(guessChar))) {
            System.out.println("Yayyy congraz, you guessed a correct letter!");
            for (int i = 0; i < solutionChars.length; i++) {
                if (solutionChars[i] == guessChar) {
                    currentStateArr[i] = guessChar;
                }
            }
        } else {
            System.out.println("Ohh nooo, your guess is wrong.");
            System.out.println("The solution does not contain char '" + guessChar + "' .");
            drawHangman(solution);
        }
        this.currentGuessState = String.valueOf(currentStateArr);
        if (this.currentGuessState.contains("*")) {
            System.out.println("\n--------------------------------------");
            System.out.println("Already used chars: " + this.collectedGuesses);
            System.out.println("Current Guess: " + this.currentGuessState);
        } else {
            System.out.println("\nCongratulation!!!\n" +
                    "\n" +
                    "\n******        Y O U   W O N         *****\n\n" +
                    "    _-~~     ~~-_ \n" +
                    "   /~             ~\\ \n" +
                    "  |              _  |_\n" +
                    " |         _--~~~ )~~ )___\n" +
                    "\\|        /   ___   _-~   ~-_\n" +
                    "\\          _-~   ~-_         \\\n" +
                    "|         /         \\         |\n" +
                    "|        |           |     (O  |\n" +
                    " |      |             |        |\n" +
                    " |      |   O)        |       |\n" +
                    " /|      |           |       /\n" +
                    " / \\ _--_ \\         /-_   _-~)\n" +
                    "   /~    \\ ~-_   _-~   ~~~__/\n" +
                    "  |   |\\  ~-_ ~~~ _-~~---~  \\\n" +
                    "  |   | |    ~--~~  / \\      ~-_\n" +
                    "   |   \\ |                      ~-_\n" +
                    "    \\   ~-|                        ~~--__ _-~~-,\n" +
                    "     ~-_   |                             /     |\n" +
                    "        ~~--|                                 /\n" +
                    "          |  |                               /\n" +
                    "          |   |              _            _-~\n" +
                    "          |  /~~--_   __---~~          _-~\n" +
                    "          |  \\                   __--~~\n" +
                    "          |  |~~--__     ___---~~\n" +
                    "          |  |      ~~~~~\n" +
                    "          |  |\n");
            this.isGameOver = true;
        }
    }

    private void drawHangman(String solution) {
        if (this.hangmanCount < 5) {
            switch (this.hangmanCount) {
                case 0:
                    System.out.println("" +
                            "____" +
                            "\n|/" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n _____");
                    break;
                case 1:
                    System.out.println("" +
                            "_______________" +
                            "\n|/" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n _____");
                    break;
                case 2:
                    System.out.println("" +
                            "_______________" +
                            "\n|/           |" +
                            "\n|          (*_*)" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n _____");
                    break;
                case 3:
                    System.out.println("" +
                            "_______________" +
                            "\n|/           |" +
                            "\n|          (*_*)" +
                            "\n|           \\|/" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n|" +
                            "\n _____");
                    break;
                case 4:
                    System.out.println("" +
                            "_______________" +
                            "\n|/           |" +
                            "\n|          (*_*)" +
                            "\n|           \\|/" +
                            "\n|            |" +
                            "\n|           / \\" +
                            "\n|" +
                            "\n|" +
                            "\n _____" +
                            "\n" +
                            "\n******        G A M E    O V E R         *****" +
                            "\n" +
                            "     .... NO! ...                  ... MNO! ...\n" +
                            "   ..... MNO!! ...................... MNNOO! ...\n" +
                            " ..... MMNO! ......................... MNNOO!! .\n" +
                            ".... MNOONNOO!   MMMMMMMMMMPPPOII!   MNNO!!!! .\n" +
                            " ... !O! NNO! MMMMMMMMMMMMMPPPOOOII!! NO! ....\n" +
                            "    ...... ! MMMMMMMMMMMMMPPPPOOOOIII! ! ...\n" +
                            "   ........ MMMMMMMMMMMMPPPPPOOOOOOII!! .....\n" +
                            "   ........ MMMMMOOOOOOPPPPPPPPOOOOMII! ...  \n" +
                            "    ....... MMMMM..    OPPMMP    .,OMI! ....\n" +
                            "     ...... MMMM::   o.,OPMP,.o   ::I!! ...\n" +
                            "         .... NNM:::.,,OOPM!P,.::::!! ....\n" +
                            "          .. MMNNNNNOOOOPMO!!IIPPO!!O! .....\n" +
                            "         ... MMMMMNNNNOO:!!:!!IPPPPOO! ....\n" +
                            "           .. MMMMMNNOOMMNNIIIPPPOO!! ......\n" +
                            "          ...... MMMONNMMNNNIIIOO!..........\n" +
                            "       ....... MN MOMMMNNNIIIIIO! OO ..........\n" +
                            "    ......... MNO! IiiiiiiiiiiiI OOOO ...........\n" +
                            "  ...... NNN.MNO! . O!!!!!!!!!O . OONO NO! ........\n" +
                            "   .... MNNNNNO! ...OOOOOOOOOOO .  MMNNON!........\n" +
                            "   ...... MNNNNO! .. PPPPPPPPP .. MMNON!........\n" +
                            "      ...... OO! ................. ON! .......\n" +
                            "         ................................\n" +
                            "\n" +
                            "\n" +
                            "The Solution was: " + solution);
                    break;
            }
            this.hangmanCount++;
        } else {
            this.isGameOver = true;
        }
    }

    private char promptGuessChar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a character to guess: ");
        String guess = scanner.next();
        char guessChar = guess.toLowerCase().charAt(0);
        if (guess.length() > 1) {
            System.out.println("Invalid input. Only 1 char is allowed.");
            System.out.println("Please try again...");
            return promptGuessChar();
        }
        if (!Character.isLetter(guessChar)) {
            System.out.println("Invalid input. The char needs to be a letter.");
            System.out.println("Please try again...");
            return promptGuessChar();
        }
        if (!this.collectedGuesses.contains(guessChar)) {
            this.collectedGuesses.add(guessChar);
            return guessChar;
        } else {
            System.out.println("Invalid input. The char was already guessed.");
            System.out.println("Please try again...");
            return promptGuessChar();
        }
    }
}
