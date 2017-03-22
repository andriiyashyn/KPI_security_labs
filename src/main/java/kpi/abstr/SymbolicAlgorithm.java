package kpi.abstr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by andrew_yashin on 2/23/17.
 */
public abstract class SymbolicAlgorithm {
    public static LinkedList<Character> alphabet;

    static {
        alphabet = new LinkedList<>();

        for (char i = 32; i <= 127; i++){
            alphabet.add(i);
        }

        for (char i = 'А'; i <= 'я'; i++){
            alphabet.add(i);
        }
    }

    public abstract void encrypt(String from, String to, int key);
    public abstract void decrypt(String from, String to, int key);

    protected static char getCharFromAlhabet(char prevChar, int key, boolean add) {

        int position = 0;
        position = Math.abs(key) % alphabet.size();
        return alphabet.get(position);
    }

    protected static void writeToFile(String data, String nameFile){
        try(BufferedWriter out = new BufferedWriter(new FileWriter(nameFile))){
            out.write(data);
        } catch (IOException e){
            System.err.println("Something wrong with writeToFile() method");
            e.printStackTrace();
        }
    }
}
