package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab6.MethodRSA;

public class DecryptRSA {
    @Parameter(names = {"-f"},required = true)
    private String from;

    @Parameter(names = {"-t"}, required = true)
    private String to;

    @Parameter(names = {"-priv"}, required = true)
    private String privateFile;

    public static void main(String[] args) {
        DecryptRSA test = new DecryptRSA();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e) {
            System.err.println("Try again");
        }

    }

    private void action() {
        SymbolicAlgorithm.initAlphabet();
        MethodRSA rsa = new MethodRSA(null, privateFile);

        rsa.decrypt(from, to);
        System.out.println("done d");
    }
}