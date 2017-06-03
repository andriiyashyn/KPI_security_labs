package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab3.MethodGamma;
import kpi.lab6.MethodRSA;

public class TestingRSA {
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

    @Parameter(names = {"-generate"})
    private boolean generate = false;


    public static void main(String[] args) {
        TestingRSA test = new TestingRSA();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e){
            System.err.println("Try again");
        }

    }

    private void action(){
        SymbolicAlgorithm.initAlphabet();
        MethodRSA rsa = new MethodRSA();
        if(generate){
            rsa.generateKeyFiles();
        } else {

            if (encrypt) {
                rsa.encrypt(from, to);
                System.out.println("done e");
            } else {
                rsa.decrypt(from, to);
                System.out.println("done d");
            }
        }
    }
}
