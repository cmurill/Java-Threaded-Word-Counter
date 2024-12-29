import java.io.*;
import java.util.*;
import java.util.regex.*;

public class SingleThreadWordCount {
    
    public static void main(String[] args) {

        long startTime = System.nanoTime();
        String dir = "../datasets/"; // relative path to the directory where the dataset is stored
        File dirPath = new File(dir); // createa file to passed into the Buffered Reader
        String [] fileNames = dirPath.list(); // scans the directory and and stores the names of the files as a string in an array

        for (int charCnt = 6; charCnt <=8; charCnt++) { // the iteration for the amount of character we want our word to have
            
            // Header for the Values we will be outputting
            System.out.println("***************************************");
            System.out.println(String.format("%-30s%-15s", "Key", "Value"));
            System.out.println("***************************************");

            // Iterate through each of the files
            for (String fileName : fileNames) {
                HashMap<String, Integer> wordCount = new HashMap<>(); // HashMap to store the words from the file as the key and the count as the value
                String mostCommonWordInFile;
                Pattern pattern = Pattern.compile("\\b[a-zA-Z]{" + charCnt + "}\\b"); // regular expression used to find the words in the file we are iterating through
                try {
                    if (fileName.equals(".DS_Store")) { // skip the .DS_Store file that Mac's make automatically
                        continue;
                    }
                    BufferedReader br = new BufferedReader(new FileReader(dir + fileName)); // buffered reader to read in the current file we are iterating through
                    String currentLine; // A string to hold the current line that the buffered reader has read
                    
                    try {
                        while((currentLine = br.readLine()) != null) { // while there are still lines that the buffered reader can read
                            Matcher matcher = pattern.matcher(currentLine); // use the regular expression to find any matches on the currentLine that the buffered reader is on
                            while (matcher.find()) { // while there is a word that matches the criteria of the regular expression
                                String acceptableWord = matcher.group().toLowerCase(); // temp String to hold the word that matched the regular expression and converts it to lowercase

                                if (checkUnique(acceptableWord, wordCount)) { // check if the string is not in the HashMap yet
                                    wordCount.put(acceptableWord, 1); // if it is not in the HashMap, add it to the HashMap with the default value of 1
                                } else {
                                    wordCount.put(acceptableWord, (wordCount.get(acceptableWord) + 1)); // if it is in the HashMap, make the value incremement by 1
                                }
                            }
                        }
                        mostCommonWordInFile = mostUsedWord(wordCount);
                        printData(fileName, mostCommonWordInFile);
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / (long) 1000000000.00;
        System.out.println("***************************************");
        System.out.println("Total Runtime: " + duration + " seconds");
        System.out.println("***************************************");
    }

    /*
     * A method that will check if the current word has already been entered into the Hash Map
     * If the word is already in the HashMap it will return boolean false
     * If the word is not in the HashMap it will return boolean true for unique
     */
    public static boolean checkUnique(String currentWord, HashMap<String, Integer> wordcnt) {
        if (wordcnt.containsKey(currentWord)) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * A method that will convert the Hash Map into an entry set and find the word that was most used and return it
     */
    public static String mostUsedWord(HashMap<String, Integer> wordcnt) {
        String mostUsedWord = null;
        int maxCount = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : wordcnt.entrySet()) {
            if(entry.getValue() > maxCount) {
                String temp = entry.getKey();
                maxCount = entry.getValue();
                mostUsedWord = temp;
            }
        }
        return mostUsedWord;
    }

    /*
     * A method that takes in the name of the file and the most frequently used word in the document and outputs it to the console
     */
    public static void printData(String fileName, String mostUsedWord) {
        System.out.println(String.format("%-30s%-15s", fileName, mostUsedWord));
    }   
}