package kpi.lab3;

import kpi.abstr.SymbolicAlgorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Created by andrew_yashin on 4/5/17.
 */
public class MethodGamma extends SymbolicAlgorithm {
    private Random random;


    @Override
    public void encrypt(String from, String to, int key, String text) {
        random = new Random(Math.abs(key));
        if(text.isEmpty()){
            workOnFile(from, to, true);
        } else {
            StringBuilder sb = new StringBuilder();
            workOnText(sb, text, true);
        }
    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        random = new Random(Math.abs(key));
        if(text.isEmpty()){
            workOnFile(from, to, false);
        } else {
            StringBuilder sb = new StringBuilder();
            workOnText(sb, text, false);
        }
    }

    private void workOnText(StringBuilder sb, String line, boolean encrypt){
        int[] array = new int[line.length()];
        for(int i = 0; i < line.length(); i++){
            array[i] = Math.abs(random.nextInt());
        }

        char[] chars = line.toCharArray();

        for(int i = 0; i < chars.length; i++){
            if(encrypt){
                int index = characters.indexOf(chars[i]) + array[i];

                while (index < 0)
                    index += characters.size();

                sb.append(characters.get(index%(characters.size())));
            } else {

                int index = characters.indexOf(chars[i]) - array[i];

                while (index < 0)
                    index += characters.size();

                sb.append(characters.get(index%(characters.size())));
            }
        }
    }

    private void workOnFile(String from, String to, boolean encrypt){
        StringBuilder sb = new StringBuilder();

        try(BufferedReader in = new BufferedReader(new FileReader(from))){

            String line;
            while ((line = in.readLine()) != null){
                workOnText(sb, line, encrypt);
                sb.append("\n");
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        writeToFile(sb.toString(), to);
    }
}
