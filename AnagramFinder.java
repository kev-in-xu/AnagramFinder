import java.io.*;
import java.util.Iterator;

/**
 * Class that uses BST, AVLTree, and HashMap to find anagrams of a given word
 * @author Kevin Xu
 * @version 1.0.1 Dec 17, 2023
 *
 * Sources:
 * https://reintech.io/blog/java-file-handling-reading-writing-text-binary-files
 * * for usage of FileReader and BufferedReader
 *
 * https://www.freecodecamp.org/news/string-to-array-in-java-how-to-convert-a-string-to-an-array-in-java
 * * for converting from String to char array
 */

public class AnagramFinder {
    public static void main(String[] args) {
        if (args.length != 3) { // checks for 3 args
            System.err.println("Usage: java AnagramFinder <word> <dictionary file> <bst|avl|hash>");
            System.exit(1);
        }
        File file = new File(args[1]);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file)); // tries reading from file
        } catch (FileNotFoundException e) {
            System.err.println("Error: Cannot open file '" + args[1] + "' for input.");
            System.exit(1);
        }

        int type = -1;
        if (args[2].equals("bst")) {
            type = 0;
        } else if (args[2].equals("avl")) {
            type = 1;
        } else if (args[2].equals("hash")) {
            type = 2;
        }

        // creates data structure based on command-line argument parsed above
        MyMap<String, MyList<String>> map = null;
        switch (type) {
            case 0:
                map = new BSTMap<>();
                break;
            case 1:
                map = new AVLTreeMap<>();
                break;
            case 2:
                map = new MyHashMap<>();
                break;
            default:
                System.err.println("Error: Invalid data structure '" + args[2] + "' received.");
                System.exit(1);
        }

        // converts input word to a sorted, lowercase key String through a char array intermediate
        String searchKey = insertionSort(args[0].toLowerCase().toCharArray());

        try {
            String line, key;
            while ((line = br.readLine()) != null) { // adds all words into the array

                key = insertionSort(line.toLowerCase().toCharArray());
                MyList<String> temp = map.get(key);

                // if no list at given key, creates an empty list before adding new String
                if (temp == null) {
                    temp = new MyLinkedList<>();
                }
                temp.add(line);
                map.put(key,temp);
            }
        } catch (IOException e){
            System.err.println("Error: An I/O error occurred reading '" + args[1] + "'.");
            System.exit(1);
        }

        MyList<String> results = map.get(searchKey);

        if (results == null) {
            results = new MyLinkedList<>();
        } else {
            results = insertionSort(results); // sorts list of anagrams lexicographically
        }

        boolean printed = false;
        Iterator<String> itr = results.iterator();
        String s;
        while (itr.hasNext()) {
            s = itr.next();
            if (!s.equals(args[0])) { // does not print if same as input word
                System.out.println(s);
                printed = true;
            }
        }
        if (!printed) { // if list was null or if only item in list was the input word
            System.out.println("No anagrams found.");
        }
    }

    /**
     * --taken from class--
     * Sorts a given array of characters lexicographically using
     * insertion sort. Based on insertionSort method provided in
     * class in ElementarySorting.java
     * @param word the word (in the form of an array of chars) to be sorted
     * @return a String containing the sorted list of characters
     */
    public static String insertionSort(char[] word) {
        for (int i=1, len = word.length; i<len; i++) {
            char current = word[i];
            int k;
            for (k = i - 1; k >= 0 && word[k] > current; k--) {
                word[k+1] = word[k];
            }
            word[k+1] = current;
        }
        return new String(word);
    }

    /**
     * --taken from class--
     * Sorts a given list of Strings lexicographically (i.e. a list of words
     * that all are anagrams of the same set of characters.
     * @param words a List of Strings to be sorted
     * @return a list of sorted Strings
     */
    public static MyList<String> insertionSort(MyList<String> words) {
        for (int i=1, len = words.size(); i<len; i++) {
            String current = words.get(i);
            int k;
            for (k = i - 1; k >= 0 && (words.get(k).compareTo(current) > 0); k--) {
                words.set(k+1,words.get(k));
            }
            words.set(k+1,current);
        }
        return words;
    }
}