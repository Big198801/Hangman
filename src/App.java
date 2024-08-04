import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static final int MAX_COUNT_OF_MISTAKES = 8;
    public static void main(String[] args) {
        String[][] hangman = getAndFillDefaultArray();
        printHangman(hangman);
        //startTheGames();
    }
    public static  void startTheGames() {
        int countOfMistakes = 0;
        while (true) {
            printStartGame();
            char startGame = inputLetterByUser();
            if (Character.compare(startGame, 'С') != 0) {
                printEndGame();
                return;
            } else {
                ArrayList<String> words = writeWordsFromFileToList();
                char[] wishedWord = getRandomWordFromList(words);
                assert wishedWord != null;
                printWishedWord(wishedWord);
                char[] hiddenCopy = makeWishedWordHiddenCopy(wishedWord);
                ArrayList<Character> earlierInputtedLetters = new ArrayList<Character>();
                while (true) {
                    printHiddenCopy(hiddenCopy);
                    char supposedLetter = inputLetterByUser();
                    if (!isLetterAlreadyChoosen(supposedLetter, earlierInputtedLetters)) {
                        if (isLetterAreInWishedWord(wishedWord, supposedLetter)) {
                            viewGreetingsOfRightLetterAppears();
                            openGuessedLettersInHiddenWord(supposedLetter, hiddenCopy, wishedWord);
                            printHiddenCopy(hiddenCopy);
                            if (isGameWin(wishedWord, hiddenCopy)) {
                                printPlayerWin();
                                startTheGames();
                                return;
                            } else {
                                earlierInputtedLetters.add(supposedLetter);
                                printEarlierInputtedLetters(earlierInputtedLetters);
                            }
                        } else {
                            viewWrongLetterAppears();
                            earlierInputtedLetters.add(supposedLetter);
                            printHiddenCopy(hiddenCopy);
                            printEarlierInputtedLetters(earlierInputtedLetters);
                            countOfMistakes++;
                            printRemainedAttempts(countOfMistakes);
                            if (countOfMistakes == MAX_COUNT_OF_MISTAKES) {
                                printPlayerLooseGame();
                                startTheGames();
                                return;

                            }
                        }
                    } else {
                        viewLettersWasChoosenEarlier();
                        earlierInputtedLetters.add(supposedLetter);
                    }
                }
            }
        }
    }


    public static void printPlayerWin(){
        System.out.println("УРРАААА Ты выиграл!!!\n\n");
    }
    public static void printPlayerLooseGame(){
        System.out.println("Ты проиграл!:))))\n\n");
    }

    /**
     * Запись слов из файла в список
     * @return ArrayList<String>
     */
    public static ArrayList<String> writeWordsFromFileToList()  {
        ArrayList<String> words = new ArrayList<>();
        try {
            FileReader fr = new FileReader("words.txt");
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                words.add(line.trim().toUpperCase());
                line = reader.readLine();
            }
            fr.close();
            reader.close();
        }
        catch (IOException e){
            e.getMessage();
        }
        return words;

    }

    /**
     * Получение случайного слова из списка
     * @param listOfWords принимает список ArrayList<String> слов
     * @return String
     */
    public static char[] getRandomWordFromList(ArrayList<String> listOfWords){
        if (listOfWords.size() == 0){
            return null;
        }
        int randomNum = 1 + (int) (Math.random()*listOfWords.size()-1); //интервал от 1 до listOfWords.size()-1
        return listOfWords.get(randomNum-1).toCharArray();//вычитаем 1 для возможности включения 0-го элемента
    }

    /**
     * Ввод буквы от пользователя
     * @return букву
     */
    public static char inputLetterByUser() {
        System.out.print("\nвведите букву: ");
        Scanner sc = new Scanner(System.in);
        return sc.next().toUpperCase().charAt(0);
    }

    /**
     * Проверка присутствия буквы в загаданном слове
     * @param wishedWord загаданное слово
     * @param inputLetterByUser введенная буква
     * @return true or false
     */
    public static boolean isLetterAreInWishedWord(char[] wishedWord, char inputLetterByUser) {
        boolean isContains = false;
        for (char ch : wishedWord) {
            if (ch == inputLetterByUser) {
                isContains = true;
                break;
            }
        }
        return isContains;
    }

    /**
     * Создание скрытого слова равного длине загаданного
     * @param wishedWord загаданное слово
     * @return массив символов
     */
    public static char[] makeWishedWordHiddenCopy(char[] wishedWord){
        char[] hiddenCopy = new char[wishedWord.length];
        Arrays.fill(hiddenCopy, '*');
        return hiddenCopy;
    }

    /**
     * Распечетка загаданного слова
     * @param wishedWord загаданное слово в формате char[]
     */
    public static void printWishedWord(char[] wishedWord){
        System.out.print("Загаданное слово: ");
        for (char c : wishedWord) {
            System.out.print(c);
        }
        System.out.println();
    }

    /**
     * Распечатка оставшихся попыток для отгадки слова
     * @param countOfMistakes количество ошибок
     */
    public static void printRemainedAttempts(int countOfMistakes){
        System.out.printf("осталось попыток: %d \n\n", MAX_COUNT_OF_MISTAKES - countOfMistakes);
    }

    /**
     * Распечатка скрытой копии
     * @param hiddenCopy "спрятанная" копия загаданного слова
     */
    public static void printHiddenCopy(char[] hiddenCopy){
        System.out.print("\n\t\t");
        for (char c : hiddenCopy) {
            System.out.print(c);
        }
        System.out.println();
    }

    /**
     * Старт игры
     */
    public static void printStartGame(){
        System.out.print("НАЖМИТЕ 'С' для старта или ЛЮБОЙ ДРУГОЙ символ для выхода их игры: ");
    }

    /**
     * Сообщение об остановки игры
     */
    public static void printEndGame(){
        System.out.println("ИГРА ЗАВЕРШЕНА");
    }

    /**
     * Сообщение об угаданной букве
     */
    public static void viewGreetingsOfRightLetterAppears(){
        System.out.println("верно, есть такая буква! ");
    }

    /**
     * Сообщение о неугаданной букве
     */
    public static void viewWrongLetterAppears(){
        System.out.println("!!!данной буквы нет в слове!!! ");
    }

    /**
     * Сообщение о том, что буква же была введена ранее
     */
    public static void viewLettersWasChoosenEarlier(){
        System.out.println("!!!буква была введена ранее, повторите ввод!!!");
    }

    /**
     * Проверка уже ранее введенных букв
     * @param supposedLetter вводимая буква
     * @param earlierInputtedLetter список уже введенных букв
     * @return True - если буква ранее вводилась, иначе - False
     */
    public static boolean isLetterAlreadyChoosen(char supposedLetter, ArrayList<Character> earlierInputtedLetter){
        if (earlierInputtedLetter.contains(supposedLetter)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Распечатка ранее введенных букв
     * @param earlierInputtedLetter список введенных букв
     */
    public static void printEarlierInputtedLetters(ArrayList<Character> earlierInputtedLetter){
        System.out.print("___________________\nвведенные ранее буквы\n");
        for (Character c: earlierInputtedLetter) {
            System.out.printf(" %s ", c);
        }
        System.out.println();
        //System.out.println("\n___________________");
    }

    /**
     * Открытие угаданных(ой) букв в скрытой копии
     * @param supposedLetter угаданная пользователем буква
     * @param hiddenSymbols массив символов спрятанного слова
     * @param wishedWord массив букв загаданного слова
     */
    public static void openGuessedLettersInHiddenWord(char supposedLetter, char[] hiddenSymbols, char[] wishedWord){
        for (int i = 0; i < wishedWord.length; i++) {
            if (wishedWord[i] == supposedLetter){
                hiddenSymbols[i] = supposedLetter;
            }
        }
    }

    /**
     * Проверка статуса выигрыша игры
     * @param wishedWord загаданное слово
     * @param hiddenCopy раскрываемая скрытая копия
     * @return да или нет
     */
    public static boolean  isGameWin(char[] wishedWord, char[] hiddenCopy ){
        boolean isEquals = true;
        for (int i = 0; i < wishedWord.length; i++) {
            if (wishedWord[i] != hiddenCopy[i]){
                isEquals = false;
            }
        } return isEquals;
    }

    /**
     * Создает изначальный вид виселицы
     * @return  заполненный массив String[][]
     */
    public static String[][] getAndFillDefaultArray(){
        return new String[][]{
                {" ", " _ ", "_ ", "_", " "},//default view
                {" ", "|", " ", " |", " "},
                {" ", "|", " ", " ", " "}, //1    [2][3] = " O";
                {" ", "|", " ", " ", " "}, //2    [3][3] = " -";
                {" ", "|", " ", " ", " "},//3-5  [4][2] = " /"; [4][3] = "|";  [4][4] = "\\";
                {" ", "|", " ", " ", " "}, //6    [5][3] = " |";
                {" ", "|", " ", " ", " "}, //7  [6][2] = " /"; [6][4] = "\\ ";
                {"_", "|", "_", " ", " "}
        };

    }


    /**
     * Распечатка виселицы
     * @param hangman Массив виселицы
     */
    public static void printHangman(String[][] hangman){
        for (int i = 0; i < hangman.length; i++) {
            System.out.println();
            for (int j = 0; j < hangman[i].length; j++) {
                System.out.print(hangman[i][j]);
            }

        }
    }

    /**
     * Обновление вида виселицы в зависимости от количества ошибок
     * @param hangman String[][] массив виселицы
     * @param currentCountOfMistakes текущее количество ошибок
     */
    public static void updateHangmanStatus(String[][] hangman, int currentCountOfMistakes){
        switch (currentCountOfMistakes){
            case 1: hangman[2][3] = " O";
            case 2: hangman[3][3] = " -";
            case 3: hangman[4][2] = " /";
            case 4: hangman[4][3] = "|";
            case 5: hangman[4][4] = "\\";
            case 6: hangman[5][3] = " |";
            case 7: hangman[6][2] = " /";
            case 8: hangman[6][4] = "\\ ";
        }
    }

}


