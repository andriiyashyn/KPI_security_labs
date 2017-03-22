package kpi.abstr;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by andrew_yashin on 2/23/17.
 */
public abstract class SymbolicAlgorithm {
    public static LinkedList<Character> characters;

    public static void initAlphabet(boolean eng){
        characters = new LinkedList<>();
        if(eng){
            for (int i = 32; i < 127; i++){
                characters.add((char)i);
            }
        } else {
            for (int i = 32; i < 65; i++) {
                characters.add((char)i);
            }

            for (int i = 91; i < 97; i++){
                characters.add((char)i);
            }

            for (int i = 123; i < 128; i++){
                characters.add((char)i);
            }

            for (char i = 'А'; i < 'я'; i++){
                characters.add(i);
            }

            characters.addAll(Arrays.asList('ї', 'Ї', 'ю', 'є', 'Ю', 'Є', 'і','І'));
        }
    }

    public abstract void encrypt(String from, String to, int key, String text);
    public abstract void decrypt(String from, String to, int key, String text);

    protected static char getCharFromAlhabet(char prevChar, int key, boolean add) {

        int position = 0;
        position = Math.abs(key) % characters.size();
        return characters.get(position);
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
