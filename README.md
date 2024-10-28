# Java-Threaded-Word-Counter
- This repository houses the code used to compare a single-threaded word counter against a multithreaded word counter that I did as an assignment for my Principles of Operating Systems course.

## **Introduction**
- Work on creating word counters through two different implementations and compare their performance
    - Single Threaded Word Counter
    - Multithreaded Word Counter
- The dataset used for this comparison is a sample set of 14 different files containing csv and txt files, provided in the dataset directory
- Hash Maps are used in order to store the key value pairs of word, count
    - For the multithreaded approach I use the Collections.synchronizedMap method from the Collections package

### **1. Single-Threaded Word Counter**
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
- Created a new program that modified the single-threaded program to be multithreaded
- New program must be at least 10% faster than the original single-threaded program
- Implementation includes creating a thread for each file and that thread will be responsible for their own file and completing the word count

### **Example Output**
- The output to the console and the screenshot we must include must look similar to the below image:
![Example Output from Console](./..assets/example_output.png)
