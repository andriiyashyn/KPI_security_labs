package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab3.MethodGamma;
import kpi.lab4.MethodStirl;

/**
 * Created by andrew_yashin on 4/13/17.
 */
public class TestingStirl {
    @Parameter(names = {"-e"})
    private boolean encrypt = false;

    @Parameter(names = {"-d"})
    private boolean decrypt = false;

    @Parameter(names = {"-f"})
    private String from;

    @Parameter(names = {"-t"})
    private String to;

    @Parameter(names = {"-text"})
    private String text;


    public static void main(String[] args) {
        TestingStirl test = new TestingStirl();

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
            new MethodStirl("lab4.txt").encrypt(from, to, 0, text);
            System.out.println("work is done (e)");
        } else if(decrypt){
            new MethodStirl("lab4.txt").decrypt(from, to, 0, text);
            System.out.println("work is done (d)");
        } else{
            System.out.println("nothing to do");
        }
    }

}
