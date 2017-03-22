package kpi.lab2;

import kpi.abstr.SymbolicAlgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by andrew_yashin on 3/20/17.
 */
public class MethodTritemium extends SymbolicAlgorithm {

    private int a, b, c;
    private String word;

    public MethodTritemium(int a, int b, int c, String word) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.word = word;
    }

    @Override
    public void encrypt(String from, String to, int key, String text) {
        if(word == null) {
            if (text == null) {
                workOnFile(from, to, true);
            } else {
                workOnSystemInput(text, true);
            }
        } else {
            if (text == null) {
                workOnFileWithWord(from, to, true);
            } else {
                workOnSystemInputWithWord(text, true);
            }
        }
    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        if(word == null) {
            if (text == null) {
                workOnFile(from, to, false);
            } else {
                workOnSystemInput(text, false);
            }
        } else {
            if (text == null) {
                workOnFileWithWord(from, to, false);
            } else {
                workOnSystemInputWithWord(text, false);
            }
        }
    }

    private void workOnFile(String from, String to, boolean encrypt){
        int totalCount = characters.size() - 1;

        try(BufferedReader in = new BufferedReader(new FileReader(from))){
            String string;
            String newString = "";
            int t = 0;
            while ((string = in.readLine()) != null){

                char[] symbols = string.toCharArray();
                StringBuilder sb = new StringBuilder();

                for(Character symbol: symbols) {
                    int keyValue = (a * t*t) + (b * t) + c;
                    int index = 0;

                    if(encrypt){
                        int indexOfEncrypt = characters.indexOf(symbol);
                        int indexOfDecrypt = (indexOfEncrypt+keyValue) % totalCount;
                        index = indexOfDecrypt;
                    }
                    else {
                        int indexOfDecrypt = characters.indexOf(symbol);
                        int temp = indexOfDecrypt-keyValue;
                        while (temp < 0){
                            temp += totalCount;
                        }
                        int indexOFEncrypt = (temp) % totalCount;
                        index = indexOFEncrypt;

                    }

                    sb.append(characters.get(Math.abs(index)));


                    ++t;
                }
                newString += sb.toString() + "\n";
            }
            writeToFile(newString, to);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void workOnSystemInput(String text, boolean encrypt){
        StringBuilder sb = new StringBuilder();
        char[] textInChar = text.toCharArray();
        int totalCount = characters.size() - 1;
        int t = 0;

        for(int i = 0; i < textInChar.length; i++){

            int index = 0;
            int keyValue = (a * t * t)
                    + (b * t)
                    + c;
            if(encrypt){
                int indexOfEncrypt = characters.indexOf(textInChar[i]);
                int indexOfDecrypt = (indexOfEncrypt+keyValue) % totalCount;

                index = indexOfDecrypt;
            } else{
                int indexOfDecrypt = characters.indexOf(textInChar[i]);
                int temp = indexOfDecrypt-keyValue;

                while (temp < 0){
                    temp += totalCount;
                }
                int indexOfEncrypt = (temp) % totalCount;
                index = indexOfEncrypt;
            }

            sb.append(characters.get(index));
            ++t;
        }

        System.out.println(sb.toString());

    }

    private void workOnFileWithWord(String from, String to, boolean encrypt){
        try(BufferedReader in = new BufferedReader(new FileReader(from))){
            StringBuilder sb = new StringBuilder();
            String string;
            int t = 0;
            char[] gasloArray = word.toCharArray();

            while ((string = in.readLine()) != null){

                char[] chars = string.toCharArray();
                getString(chars, encrypt, gasloArray, sb, t);
                sb.append("\n");
            }

            writeToFile(sb.toString(), to);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void workOnSystemInputWithWord(String text, boolean encrypt){
        char[] textArray = text.toCharArray();
        char[] gasloArray = word.toCharArray();
        int t = 0;
        StringBuilder sb = new StringBuilder();

        System.out.println(getString(textArray, encrypt, gasloArray, sb, t));
    }

    private String getString(char[] textArray, boolean encrypt, char[] gasloArray, StringBuilder sb, int t){
        for(Character symbol: textArray){
            int index = 0;

            if (encrypt){
                int temp = t % gasloArray.length;
                index = + characters.indexOf(symbol) + characters.indexOf(gasloArray[temp]);
            } else {
                int temp = t % gasloArray.length;
                index = characters.indexOf(symbol) - characters.indexOf(gasloArray[temp]);

                while (index < 0){
                    index += characters.size()-1;
                }
            }

            ++t;
            sb.append(characters.get(index%(characters.size()-1)));
        }

        return sb.toString();
    }
}