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
    private String gaslo;

    public MethodTritemium(int a, int b, int c, String gaslo) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.gaslo = gaslo;
    }

    @Override
    public void encrypt(String from, String to, int key, String text) {
        if(gaslo == null) {
            if (text == null) {
                processData(from, to, true);
            } else {
                processText(text, true);
            }
        } else {
            if (text == null) {
                processDataGaslo(from, to, true);
            } else {
                processTextGaslo(text, true);
            }
        }
    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        if(gaslo == null) {
            if (text == null) {
                processData(from, to, false);
            } else {
                processText(text, false);
            }
        } else {
            if (text == null) {
                processDataGaslo(from, to, false);
            } else {
                processTextGaslo(text, false);
            }
        }
    }

    private void processData(String from, String to,  boolean encrypt){
        int N = alphabet.size() - 1;

        try(BufferedReader in = new BufferedReader(new FileReader(from))){
            String string;
            String newString = "";
            int t = 0;
            while ((string = in.readLine()) != null){

                char[] symbols = string.toCharArray();
                StringBuilder sb = new StringBuilder();

                for(Character symbol: symbols) {

                    if(encrypt){
                        int m = alphabet.indexOf(symbol);
                        int k = (a * t*t) + (b * t) + c;
                        int L = (m+k) % N;

                        sb.append(alphabet.get(L));
                    } else {
                        int L = alphabet.indexOf(symbol);
                        int k = (a * t* t) + (b * t) + c;

                        int temp = L-k;

                        while (temp < 0){
                            temp += N;
                        }

                        int m = (temp) % N;

                        sb.append(alphabet.get(Math.abs(m)));
                    }

                    ++t;
                }
                newString += sb.toString() + "\n";
            }
            writeToFile(newString, to);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void processText(String text, boolean encrypt){
        StringBuilder sb = new StringBuilder();
        char[] textInChar = text.toCharArray();
        int N = alphabet.size() - 1;
        int t = 0;

        for(int i = 0; i < textInChar.length; i++){

            if(encrypt){
                int m = alphabet.indexOf(textInChar[i]);
                int k = (a * t * t)
                        + (b * t)
                        + c;
                int L = (m+k) % N;

                sb.append(alphabet.get(L));
            } else{
                int L = alphabet.indexOf(textInChar[i]);
                int k = (a * t * t)
                        + (b * t)
                        + c;

                int temp = L-k;

                while (temp < 0){
                    temp += N;
                }

                int m = (temp) % N;

                sb.append(alphabet.get(m));
            }

            ++t;
        }

        System.out.println(sb.toString());

    }

    private void processDataGaslo(String from, String to, boolean encrypt){
        try(BufferedReader in = new BufferedReader(new FileReader(from))){
            StringBuilder sb = new StringBuilder();
            String string;
            int t = 0;
            char[] gasloArray = gaslo.toCharArray();

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

    private void processTextGaslo(String text, boolean encrypt){
        char[] textArray = text.toCharArray();
        char[] gasloArray = gaslo.toCharArray();
        int t = 0;
        StringBuilder sb = new StringBuilder();

        System.out.println(getString(textArray, encrypt, gasloArray, sb, t));
    }

    private String getString(char[] textArray, boolean encrypt, char[] gasloArray, StringBuilder sb, int t){
        for(Character symbol: textArray){
            int index = 0;

            if (encrypt){
                int temp = t % gasloArray.length;
                index = + alphabet.indexOf(symbol) + alphabet.indexOf(gasloArray[temp]);
            } else {
                int temp = t % gasloArray.length;
                index = alphabet.indexOf(symbol) - alphabet.indexOf(gasloArray[temp]);

                while (index < 0){
                    index += alphabet.size()-1;
                }
            }

            ++t;
            sb.append(alphabet.get(index%(alphabet.size()-1)));
        }

        return sb.toString();
    }
}