import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
    ArrayList<String> words = writeWordsFromFileToList();
    //System.out.println(words.size());
    char[] wishedWord = getRandomWordFromList(words);
    //char letter = inputLetterbyUser();
    printWishedWord(wishedWord);
    //String[] hiddencopy = makeWishedWordHiddenCopy(wishedWord);
    //printHiddenCopy(hiddencopy);

    }

    /**
     * запуск игры
     */
    public  void  startHangmanGame(){}

    /**
     * запись слов из файла в список
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
     * получение случайного слова из списка
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
     * распечетка в консоль загаданного слова
     * @param wishedWord загаданное слово в формате char[]
     */
    public static void printWishedWord(char[] wishedWord){
        System.out.print("Загаданное слово: ");
        for (int i = 0; i < wishedWord.length; i++) {
            System.out.print(wishedWord[i]);
        }
    }

    /**
     * ввод буквы от пользователя
     * @return букву
     */
    public static char inputLetterbyUser() {
        System.out.println("введите букву: ");
        Scanner sc = new Scanner(System.in);
        char letter = sc.next().toUpperCase().charAt(0);
        return letter;
    }

    //проверка присутствия буквы в слове
    public boolean isLetterAreInWishedWord(char[] wishedWord, char inputLetterbyUser) {
        boolean isContains = false;
        for (char ch : wishedWord) {
            if (ch == inputLetterbyUser) {
                isContains = true;
            }
        }
        return isContains;
    }


    /**
     * создание скрытого слова равного длине загаданного
     * @param wishedWord загаданное слово
     * @return массив символов
     */
    public static String[] makeWishedWordHiddenCopy(String wishedWord){
        String[] hiddenCopy = new String[wishedWord.length()];
        for (int i = 0; i < hiddenCopy.length; i++) {
            hiddenCopy[i] = "*";
        }
        return hiddenCopy;
    }

    //распечатка скрытой копии
    public static void printHiddenCopy(String[] hiddenCopy){
        for (int i = 0; i < hiddenCopy.length; i++) {
            System.out.print(hiddenCopy[i]);
        }
    }


    //отрисовка отгаданных букв
//    public void showGuessedLetterinHiddenWord(String[] hiddenletters, char inputLetter, String wishedWord){
//       wishedWord.toCharArray();
//        for (int i = 0; i < wishedWord.length(); i++) {
//
//            if (wishedWord.
//        }
//
//
//    }






}


