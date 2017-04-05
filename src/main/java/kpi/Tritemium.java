package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab2.MethodTritemium;

/**
 * Created by andrew_yashin on 3/20/17.
 */
public class Tritemium {

    @Parameter(names = {"-e", "-encrypt"})
    private boolean encrypt = false;

    @Parameter(names = {"-d", "-decrypt"})
    private boolean decrypt = false;

    @Parameter(names = {"-f", "-from"})
    private String from;

    @Parameter(names = {"-t", "-to"})
    private String to;

    @Parameter(names = {"-b"})
    private int b = 0;

    @Parameter(names = {"-c"})
    private int c = 0;

    @Parameter(names = {"-a"})
    private int a = 0;

    @Parameter(names = {"-text"})
    private String text;

    @Parameter(names = {"-w", "-word"})
    private String word;


    @Parameter(names = {"-russian"})
    private boolean russian = false;

    public static void main(String[] args) {
        Tritemium test = new Tritemium();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e){
            System.err.println("Try again");
        }

    }

    public void action(){

        if(russian){
            SymbolicAlgorithm.initAlphabet();
        } else {
            SymbolicAlgorithm.initAlphabet();
        }

        if(encrypt){
            new MethodTritemium(a, b, c, word).encrypt(from, to, 0, text);
            System.out.println("work is done (e)");
        } else if(decrypt){
            new MethodTritemium(a, b, c, word).decrypt(from, to, 0, text);
            System.out.println("work is done (d)");
        } else{
            System.out.println("nothing to do");
        }
    }
}
