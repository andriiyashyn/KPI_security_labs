package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab6.MethodRSA;

public class GenerateKeys {
    @Parameter(names = {"-priv"}, required = true)
    private String privateFile;

    @Parameter(names = {"-publ"}, required = true)
    private String publicFile;

    public static void main(String[] args) {
        GenerateKeys test = new GenerateKeys();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e) {
            System.err.println("Try again");
        }

    }

    private void action() {
        SymbolicAlgorithm.initAlphabet();
        MethodRSA rsa = new MethodRSA(publicFile, privateFile);
        System.out.println("done generate");
    }
}
