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
    System.out.println(words.size());
    String hiddenWord = getRandomWordFromList(words);
    //char letter = inputLetterbyUser();

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
    public static String getRandomWordFromList(ArrayList<String> listOfWords){
        if (listOfWords.size() == 0){
            return null;
        }
        int randomNum = 1 + (int) (Math.random()*listOfWords.size()-1); //интервал от 1 до listOfWords.size()-1
        return listOfWords.get(randomNum-1);//вычитаем 1 для возможности включения 0-го элемента
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
    public boolean isLetterAreInWishedWord(String wishedWord, char inputLetterbyUser){
        if(wishedWord.indexOf(inputLetterbyUser) != -1){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * отрисовка отгадываемого слова
     */
    public String[] hideWordviewer(String wishedWord){
        System.out.println("не реализован");
        return new String[wishedWord.length()];
    }


    //отрисовка отгаданных букв
    public void showGuessedLetterinHiddenWord(String[] hiddenletters, char inputLetter){
        //не реализован


    }






}


