import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static final int MAX_COUNT_OF_MISTAKES = 8;
    public static int countOfMistakes;
    public static void main(String[] args) {
        ArrayList<String> words = writeWordsFromFileToList();
        char[] wishedWord = getRandomWordFromList(words);
        printWishedWord(wishedWord);
        char[] hiddenCopy = makeWishedWordHiddenCopy(wishedWord);
        printHiddenCopy(hiddenCopy);
        char supposedLetter = inputLetterByUser();
        if (isLetterAreInWishedWord(wishedWord, supposedLetter)){
            viewGreetingsOfRightLetterAppears();
            openGuessedLettersInHiddenWord(supposedLetter, hiddenCopy, wishedWord);
            printHiddenCopy(hiddenCopy);
        }
        else{
            viewWrongLetterAppears();
            printHiddenCopy(hiddenCopy);
            countOfMistakes++;
            printRemainedAttempts(countOfMistakes);
        }

    }


    /**
     * Запуск игры
     */
    public  void  startHangmanGame(){}

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
        System.out.print("введите букву: ");
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
        System.out.printf("осталось попыток: %d ", MAX_COUNT_OF_MISTAKES - countOfMistakes);
    }

    /**
     * Распечатка скрытой копии
     * @param hiddenCopy "спрятанная" копия загаданного слова
     */
    public static void printHiddenCopy(char[] hiddenCopy){
        System.out.print("\t\t\t\t");
        for (char c : hiddenCopy) {
            System.out.print(c);
        }
        System.out.println();
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
        System.out.println("данной буквы нет слове ");
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



}


