package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab2.MethodTritemium;

/**
 * Created by andrew_yashin on 3/20/17.
 */
public class TestingTritemium {

    @Parameter(names = {"-e"})
    private boolean encrypt = false;

    @Parameter(names = {"-d"})
    private boolean decrypt = false;

    @Parameter(names = {"-f"})
    private String from;

    @Parameter(names = {"-t"})
    private String to;

    @Parameter(names = {"-b"})
    private int b = 0;

    @Parameter(names = {"-c"})
    private int c = 0;

    @Parameter(names = {"-a"})
    private int a = 0;

    @Parameter(names = {"-text"})
    private String text;

    @Parameter(names = {"-g"})
    private String gaslo;


    @Parameter(names = {"-rus"})
    private boolean rus = false;

    public static void main(String[] args) {
        TestingTritemium test = new TestingTritemium();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e){
            System.err.println("Try again");
        }

    }

    public void action(){

        if(rus){
            SymbolicAlgorithm.initAlphabet(false);
        } else {
            SymbolicAlgorithm.initAlphabet(true);
        }

        if(encrypt){
            new MethodTritemium(a, b, c, gaslo).encrypt(from, to, 0, text);
            System.out.println("work is done (e)");
        } else if(decrypt){
            new MethodTritemium(a, b, c, gaslo).decrypt(from, to, 0, text);
            System.out.println("work is done (d)");
        } else{
            System.out.println("nothing to do");
        }
    }
}
