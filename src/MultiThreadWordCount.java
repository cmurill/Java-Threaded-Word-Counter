import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.Collections;

public class MultiThreadWordCount implements Runnable {

    private final String filePath;
    private String fileName;
    private int charCnt;
    private static Map<Integer, List<String>> results = new HashMap<>(); // Use this to store results for each character count

    public MultiThreadWordCount(String filePath, String fileName, int charCnt) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.charCnt = charCnt;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String dir = "../datasets/"; 
        File dirPath = new File(dir);
        String[] fileNames = dirPath.list();

        for (int charCnt = 6; charCnt <= 8; charCnt++) {
            System.out.println("***************************************");
            System.out.println(String.format("%-30s%-15s", "Key", "Value"));
            System.out.println("***************************************");

            List<Thread> fileThreads = new ArrayList<>();

            for (String fileName : fileNames) {
                if (fileName.equals(".DS_Store")) {
                    continue;
                }
                Thread fileThread = new Thread(new MultiThreadWordCount(dir + fileName, fileName, charCnt));
                fileThreads.add(fileThread);
                fileThread.start(); 
            }

            // Wait for all threads to complete
            for (Thread fileThread : fileThreads) {
                try {
                    fileThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Print results for the current character that the threads are on
            for (String result : results.getOrDefault(charCnt, Collections.emptyList())) {
                System.out.println(result);
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        System.out.println("***************************************");
        System.out.println("Total Runtime: " + duration + " seconds");
        System.out.println("***************************************");
    }

    @Override
    public void run() {
        Map<String, Integer> wordCount = Collections.synchronizedMap(new HashMap<>());
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]{" + this.charCnt + "}\\b");
        String mostCommonWordInFile;

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(currentLine);
                while (matcher.find()) {
                    String acceptableWord = matcher.group().toLowerCase();
                    wordCount.put(acceptableWord, wordCount.getOrDefault(acceptableWord, 0) + 1);
                }
            }

            mostCommonWordInFile = mostUsedWord(wordCount);
            String result = String.format("%-30s%-15s", this.fileName, mostCommonWordInFile);

            if(!results.containsKey(this.charCnt)) {
                results.put(this.charCnt, new ArrayList<>());
            }
            results.get(this.charCnt).add(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String mostUsedWord(Map<String, Integer> wordcnt) {
        String mostUsedWord = null;
        int maxCount = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : wordcnt.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostUsedWord = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostUsedWord;
    }
}
