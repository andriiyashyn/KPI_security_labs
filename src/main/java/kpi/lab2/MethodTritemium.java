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

    public MethodTritemium(int squareX, int x, int c){
        this.a = squareX;
        this.b = x;
        this.c = c;
    }
    @Override
    public void encrypt(String from, String to, int key) {
        processData(from, to, key, true);
    }

    @Override
    public void decrypt(String from, String to, int key) {
        processData(from, to, key, false);
    }

    private void processData(String from, String to, int key, boolean encrypt){
        int N = alphabet.size() - 1;

        try(BufferedReader in = new BufferedReader(new FileReader(from))){
            String string;
            String newString = "";
            while ((string = in.readLine()) != null){

                char[] symbols = string.toCharArray();

                StringBuilder sb = new StringBuilder();

                for(Character symbol: symbols) {

                    if(encrypt){
                        int m = alphabet.indexOf(symbol);
                        int k = (a * m*m) + (b * m) + c;
                        int L = (m+k) % N;

                        sb.append(alphabet.get(L));
                    } else {
                        int L = alphabet.indexOf(symbol);
                        int k = (a * L*L) + (b * L) + c;

                        int temp = L-k;

                        while (temp < 0){
                            temp += N;
                        }

                        int m = (temp) % N;

                        sb.append(alphabet.get(Math.abs(m)));
                    }
                }
                newString += sb.toString() + "\n";
            }
            writeToFile(newString, to);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
