package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab2.MethodTritemium;
import kpi.lab3.MethodGamma;
import sun.jvm.hotspot.debugger.cdbg.Sym;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by andrew_yashin on 4/5/17.
 */
public class Gamma {
    @Parameter(names = {"-k", "--key"}, required = true)
    private int key;

    @Parameter(names = {"-e", "--encrypt"})
    private boolean encrypt = false;

    @Parameter(names = {"-d", "--decrypt"})
    private boolean decrypt = false;

    @Parameter(names = {"-f", "--from"})
    private String from;

    @Parameter(names = {"-t", "--to"})
    private String to;

    @Parameter(names = {"-text"})
    private String text = "";

    public static void main(String[] args) {
        Gamma test = new Gamma();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e){
            System.err.println("Try again");
        }

    }

    public void action(){
        SymbolicAlgorithm.initAlphabet();

        if(encrypt){
            new MethodGamma().encrypt(from, to, key, text);
        } else if(decrypt){
            new MethodGamma().decrypt(from, to, key, text);
        } else {
            System.out.println("nothing to do");
        }
    }
}
