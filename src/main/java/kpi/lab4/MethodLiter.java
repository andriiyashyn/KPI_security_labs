package kpi.lab4;

import kpi.abstr.SymbolicAlgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class MethodLiter extends SymbolicAlgorithm{
    private String filenameDictionary;
    private Map<String, Character> dictionary;

    public MethodLiter(String filename){
        filenameDictionary = filename;
        dictionary = new HashMap<>();
    }

    @Override
    public void encrypt(String from, String to, int key, String text) {
        makeDictionary(filenameDictionary, dictionary);
        //Stream.of(dictionary).forEach(System.out::println);

        

    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        makeDictionary(filenameDictionary, dictionary);
    }

    private void processText(StringBuilder sb, String line, int key, boolean encrypt){

    }

    private void makeDictionary(String filename, Map<String, Character> dictionary){
        try(BufferedReader in = new BufferedReader(new FileReader(filename))){

            StringBuilder sb = new StringBuilder();
            String text;

            String line;
            while ((line = in.readLine()) != null){
                sb.append(line);
                sb.append("\n");
            }

            text = sb.toString();
            int g = (int)Math.sqrt(text.length()) + 1;

            for(int i = 0, c = 0; i < text.length(); i++){
                int key = (int)i/g*100 + i%g;
                dictionary.put(String.format("%04d", key), text.toCharArray()[i]);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
