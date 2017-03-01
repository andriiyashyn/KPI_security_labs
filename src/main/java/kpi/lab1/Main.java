package kpi.lab1;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
    @Parameter(names = {"-k"}, required = true)
    private int key;

    @Parameter(names = {"-e"})
    private boolean encrypt = false;

    @Parameter(names = {"-d"})
    private boolean decrypt = false;

    @Parameter(names = {"-f"}, required = true)
    private String from;


    @Parameter(names = {"-t"}, required = true)
    private String to;

    public static void main(String[] args) {
        Main main = new Main();
        new JCommander(main, args);
        main.action();
    }
    
    public void action(){
        if(encrypt){
            new MehtodSezar().encrypt(from, to, key);
            System.out.println("work is done (e)");
        } else if(decrypt){
            new MehtodSezar().decrypt(from, to, key);
            System.out.println("work is done (d)");
        } else{
        	System.out.println("nothing to do");
        }
    }
}
