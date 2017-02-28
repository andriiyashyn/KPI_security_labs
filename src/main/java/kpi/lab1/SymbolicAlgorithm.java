package kpi.lab1;

import java.util.LinkedList;

/**
 * Created by andrew_yashin on 2/23/17.
 */
public abstract class SymbolicAlgorithm {
    public static LinkedList<Character> alphabet;

    static {
        alphabet = new LinkedList<>();

        for (char i = 'A'; i <= 'z'; i++){
            alphabet.add(i);
        }

        for (char i = 'А'; i <= 'я'; i++){
            alphabet.add(i);
        }

        for(char i = ' '; i <= '~'; i++){
            alphabet.add(i);
        }

        for (char i = '0'; i <= '9'; i++){
            alphabet.add(i);
        }
    }

    abstract boolean encrypt(String from, String to, int key);
    abstract boolean decrypt(String from, String to, int key);

    public static char getCharFromAlhabet(char prevChar, int key, boolean add) {

        int position = 0;
        if (add) {
            if ((position = alphabet.indexOf(prevChar) - key) < 0) {
                return alphabet.get(alphabet.size() - Math.abs(position));
            } else return alphabet.get(alphabet.indexOf(prevChar) - key);
        } else {
            if ((position = alphabet.indexOf(prevChar) + key) > alphabet.size()) {
                return alphabet.get(position - alphabet.size());
            } else return alphabet.get(alphabet.indexOf(prevChar) + key);
        }
    }
}
