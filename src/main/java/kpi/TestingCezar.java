package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import kpi.lab1.MehtodSezar;

public class TestingCezar {
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
        TestingCezar testingCezar = new TestingCezar();
        new JCommander(testingCezar, args);
        testingCezar.action();
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
