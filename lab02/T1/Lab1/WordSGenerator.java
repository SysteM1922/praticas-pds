package Lab1;

import java.io.*;
import java.util.*;

public class WordSGenerator {

    public static ArrayList<String> readWords(File wordFile) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(wordFile);
        ArrayList<String> words = new ArrayList<>();

        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();

            String[] currentWords = currentLine.split("[,; ]");
            for (int i = 0; i < currentWords.length; i++){
                if(currentWords[i].length()<3 || currentWords[i].length()>60)
                    continue;

                boolean ignore=false;
                for(int testChar = 0 ; testChar < currentWords[i].length() ; testChar++)
                    if(!Character.isAlphabetic(currentWords[i].charAt(testChar))){
                        System.out.printf("Warning: Word contains non Alphabetic characters\n",currentWords[i]);
                        ignore=true;
                        break;
                    }

                if(!ignore){
                    words.add(currentWords[i]);
                }
            }
        }

        return words;
    }

    public static Character[][] createPuzzle(ArrayList<String> wordList, int size){
        String largestWord = wordList.get(0);
        Random randomGen = new Random();

        for(int i = 1 ; i<wordList.size() ; i++){
            if(largestWord.length()<wordList.get(i).length())
                largestWord = wordList.get(i);
        }


        int minSize =  largestWord.length()>Math.ceil(Math.sqrt(wordList.size())) ? largestWord.length() : (int) Math.ceil(Math.sqrt(wordList.size()));
        minSize = minSize>10 ? minSize : 10;
        int jumbleSize = size;

        Character[][] wordJumble = new Character[jumbleSize][jumbleSize];
        for(int i = 0 ; i<jumbleSize ; i++)
            for(int j = 0 ; j<jumbleSize ; j++)
                wordJumble[i][j] = (char)(randomGen.nextInt(91-65)+65);


        ArrayList<Integer[]> occupiedPositions = new ArrayList<>();

        for(String word : wordList){
            ArrayList<Integer[]> tempArray = getInsertPositions(word,wordJumble,occupiedPositions);
            for(int i = 0 ; i<tempArray.size() ; i++) {
                int posX = tempArray.get(i)[0];
                int posY = tempArray.get(i)[1];

                wordJumble[posX][posY] = word.toUpperCase().charAt(i);
                //System.out.println(posX+" "+posY);
            }

            occupiedPositions.addAll(tempArray);
        }

        return wordJumble;
    }
    
    public static int randomDirection() {
        Random randomDir = new Random();
        return randomDir.nextInt(8);
    }

    public static  ArrayList<Integer[]> getInsertPositions(String word,Character[][]wordPuzzle, ArrayList<Integer[]> occupiedPositions){
        int tryCounter=0;
        Random random1 = new Random();

        while(tryCounter < 500){
            boolean tryCounterIncremented = false;

            int currentDirection = randomDirection();
            int wordPuzzleSize = wordPuzzle.length;

            int x = random1.nextInt(wordPuzzleSize);
            int y = random1.nextInt(wordPuzzleSize);

            ArrayList<Integer[]> tempOccupiedPositions = new ArrayList<>();
            for(int i = 0 ; i<word.length() ; i++){
                Integer[] currentPosition = {x,y};

                char currentChar = word.charAt(i);
                if(currentPosition[0]>=wordPuzzleSize || currentPosition[1]>=wordPuzzleSize || currentPosition[0]<0 || currentPosition[1]<0) {
                    tryCounterIncremented = true;
                    break;
                }

                for(Integer[] pos : occupiedPositions)
                    if(pos[0]==currentPosition[0] && pos[1]==currentPosition[1])
                        if(currentChar!=wordPuzzle[currentPosition[0]][currentPosition[1]]){
                            tryCounterIncremented = true;
                            break;
                        }

                tempOccupiedPositions.add(currentPosition);

                switch (currentDirection){
                    case 0: //Up
                        y++;
                        break;
                    case 1: //Down
                        y--;
                        break;
                    case 2: //Left
                        x--;
                        break;
                    case 3: //Right
                        x++;
                        break;
                    case 4: //UpLeft
                        x--;
                        y--;
                        break;
                    case 5: //DownLeft
                        x++;
                        y--;
                        break;
                    case 6: //UpRight
                        x--;
                        y++;
                        break;
                    case 7: //DownRight
                        x++;
                        y++;
                        break;
                }

            }

            if(tryCounterIncremented){
                tryCounter++;
                continue;
            }

            return tempOccupiedPositions;
        }

        return null;
    }
    public static void main(String[] args){


        try {

            Scanner inputTaker = new Scanner(System.in);
            System.out.println("Nome do ficheiro com as palavras:\n");
            String fileName = inputTaker.nextLine();
            System.out.println("Tamanho do puzzle:\n");
            int sizePuzzle = inputTaker.nextInt();
            

            File wordFile = new File("C:\\Users\\Toshiba\\eclipse-workspace\\PDS_2022\\src\\Lab1\\"+fileName);
            ArrayList<String> wordList = readWords(wordFile);
            Character[][] wordPuzzle = createPuzzle(wordList,sizePuzzle);

            for(int i = 0 ; i<wordPuzzle.length ; i++) {
                for (int j = 0; j < wordPuzzle.length; j++)
                    System.out.print(wordPuzzle[i][j]);
                System.out.println();
            }

            inputTaker.close();

            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
