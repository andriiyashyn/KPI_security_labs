package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import kpi.lab1.MehtodCezar;

public class Cezar {
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
        Cezar cezar = new Cezar();
        new JCommander(cezar, args);
        cezar.action();
    }
    
    public void action(){
        if(encrypt){
            new MehtodCezar().encrypt(from, to, key, null);
            System.out.println("work is done (e)");
        } else if(decrypt){
            new MehtodCezar().decrypt(from, to, key, null);
            System.out.println("work is done (d)");
        } else{
        	System.out.println("nothing to do");
        }
    }
}
