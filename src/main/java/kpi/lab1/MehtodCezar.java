package kpi.lab1;


import kpi.abstr.SymbolicAlgorithm;

import java.io.*;

/**
 * Created by andrew_yashin on 2/23/17.
 */
public class MehtodCezar extends SymbolicAlgorithm {


    @Override
    public void encrypt(String from, String to, int key, String text) {
        BufferedReader in = null;
        BufferedWriter out = null;

        try{
            in = new BufferedReader(new FileReader(from));
            out = new BufferedWriter(new FileWriter(to));

            String string;
            String newString;
            while ((string = in.readLine()) != null){

                char[] symbols = string.toCharArray();

                StringBuilder sb = new StringBuilder();

                for(Character symbol: symbols){
                    char newChar = getCharFromAlhabet(symbol, key, true);
                    sb.append(newChar);
                }

                out.write(sb.toString() + "\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        BufferedReader in = null;
        BufferedWriter out = null;

        try{
            in = new BufferedReader(new FileReader(from));
            out = new BufferedWriter(new FileWriter(to));

            String string;
            String newString;
            while ((string = in.readLine()) != null){

                char[] symbols = string.toCharArray();

                StringBuilder sb = new StringBuilder();

                for(Character symbol: symbols){
                    char newChar = getCharFromAlhabet(symbol, key, false);
                    sb.append(newChar);
                }

                out.write(sb.toString() + "\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
