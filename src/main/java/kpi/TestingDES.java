package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab3.MethodGamma;
import kpi.lab5.MethodDES;

public class TestingDES {
    @Parameter(names = {"-e"})
    private boolean encrypt = false;

    @Parameter(names = {"-k"})
    private int key = 20;

    @Parameter(names = {"-d"})
    private boolean decrypt = false;

    @Parameter(names = {"-f"})
    private String from;

    @Parameter(names = {"-t"})
    private String to;

    @Parameter(names = {"-text"})
    private String text;


    public static void main(String[] args) {
        TestingDES test = new TestingDES();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e){
            System.err.println("Try again");
        }

    }

    private void action(){
        SymbolicAlgorithm.initAlphabet();
        if(encrypt){
            new MethodDES().encrypt(from, to, 0, text);
            System.out.println("work is done (e)");
        } else if(decrypt){
            new MethodDES().decrypt(from, to, 0, text);
            System.out.println("work is done (d)");
        } else{
            System.out.println("nothing to do");
        }
    }
}
