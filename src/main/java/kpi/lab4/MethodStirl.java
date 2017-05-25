package kpi.lab4;

import kpi.abstr.SymbolicAlgorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class MethodStirl extends SymbolicAlgorithm {
    private String inputKey;
    private HashMap<Character, ArrayList<String>> hashMap = new HashMap<>();

    public MethodStirl(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null){
                inputKey += line;
                inputKey += '\n';
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        setHashMap();
    }

    @Override
    public void encrypt(String from, String to, int key, String text) {
        if(text == null){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(from));
                StringBuilder builder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null){
                    builder.append(encrypt(line));
                    builder.append('\n');
                }

                writeToFile(builder.toString(), to);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println(encrypt(text));
        }
    }

    private String encrypt(String text){
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (char i: text.toCharArray()) {
            if (hashMap.containsKey(i)) {
                result.append(hashMap.get(i).get(random.nextInt(hashMap.get(i).size())) + " ");
            }
            else{
                result.append(i).toString();
            }

        }
        return result.toString();
    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        if(text == null){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(from));
                StringBuilder builder = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null){
                    builder.append(decrypt(line));
                    builder.append('\n');
                }

                writeToFile(builder.toString(), to);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println(decrypt(text));
        }
    }

    private String decrypt(String text){
        StringBuilder result = new StringBuilder();
        String[] array = text.split(" ");

        for(String number: array) {
            if(number.length() == 1){
                result.append(number);
            } else {
                for (Character character : hashMap.keySet()) {
                    if (hashMap.get(character).contains(number)) {
                        result.append(character);
                    }
                }
            }
        }
        return result.toString();
    }

    private void setHashMap() {
        char[] charKey = inputKey.toCharArray();

        String position = null;
        int CC = 0, SS = 0;
        for (char key : charKey) {
            if(key == '\n'){
                SS++;
                CC=0;
            }
            if (hashMap.containsKey(key)) {
                hashMap.get(key).add(setPosition(CC, SS));
            } else {
                position = setPosition(CC, SS);
                ArrayList<String> list = new ArrayList<>();
                list.add(position);
                hashMap.put(key, list);
            }
            CC++;
        }
    }

    private String setPosition(int x, int y) {
        return String.format("%04d%04d", x, y);
    }

}
