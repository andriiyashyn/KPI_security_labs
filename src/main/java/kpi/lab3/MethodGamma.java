package kpi.lab3;

import kpi.abstr.SymbolicAlgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by andrew_yashin on 3/29/17.
 */
public class MethodGamma extends SymbolicAlgorithm {

    private Random rand;

    @Override
    public void encrypt(String from, String to, int key, String text) {
        rand = new Random(Math.abs(key));
        if(text == null)
            processFile(from, to, true);
        else {
            StringBuilder sb = new StringBuilder();
            processText(sb, text, true);
            System.out.println(sb.toString());
        }
    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        rand = new Random(Math.abs(key));
        if(text == null)
            processFile(from, to, false);
        else {
            StringBuilder sb = new StringBuilder();
            processText(sb, text, false);
            System.out.println(sb.toString());
        }
    }

    private void processText(StringBuilder sb, String text, boolean encrypt){
        int[] array = new int[text.length()];
        for(int i = 0; i < array.length; i++){
            array[i] = rand.nextInt();
        }

        char[] symbols = text.toCharArray();

        for(int i = 0; i < symbols.length; i++){
            if(encrypt) {
                int index = alphabet.indexOf(symbols[i]) + array[i];

                while (index < 0){
                    index += alphabet.size()-1;
                }
                sb.append(alphabet.get(index % (alphabet.size() - 1)));
            } else {
                int index = alphabet.indexOf(symbols[i]) - array[i];

                while (index < 0){
                    index += alphabet.size()-1;
                }
                sb.append(alphabet.get(index % (alphabet.size() - 1)));
            }
        }
    }

    private void processFile(String from, String to, boolean encrypt){
        StringBuilder sb = new StringBuilder();

        try(BufferedReader out = new BufferedReader(new FileReader(from))){

            String string;
            while ((string = out.readLine()) != null){
                processText(sb, string, encrypt);
                sb.append("\n");
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        writeToFile(sb.toString(), to);
    }


}
