# Java-Threaded-Word-Counter
- This repository is housing the code that I used to compare a single-threaded word counter against a multithreaded word counter for an assignment in my Principles of Operating Systems course
## Threaded Word Counter Assignment

### **Introduction**
- Work on creating word counters using a single thread and multithreaded approach
- The word counters are using 14 files as the provided dataset for this assignment
- We will be using a HashMap to store the words and the counts for each file

### **1. Single-Threaded Word Count**
- Implement a single-threaded Java program that will find the most frequent word for each file in the 14-file dataset
- Important Notes:
    - Iterate through the 14 files a total of 3 times
        - 1<sup>st</sup> iteration: count the most frequent word with exactly 6 characters
        - 2<sup>nd</sup> iteration: count the most frequent word with exactly 7 characters
        - 3<sup>rd</sup> iteration: count the most frequent word with exactly 8 characters
    - Print to the console the file names and the most frequent word for each file
    - Make sure to remove case sensitivity and use a reqular expression to identify words
        - Code should ignore whitespace and punctuation
    - Time all iterations and print the total time to the console
- Deliverable:
    - Java source 
    - PDF of the screenshot of the word counts and the runtime printed to the console
### **2. Multithreaded Word Count**
- Create a new program that modifies the single-threaded program to be multithreaded
- New program must be at least 10% faster than the original single-threaded program
- Implement a thread for each file and the thread will perform the word count for that file
- Synchronization of the map uses the Collections.synchronizedMap method from the Collections package

### **Example Output**
- The output to the console and the screenshot we must include must look similar to the below image:

<img src="example_output.png">