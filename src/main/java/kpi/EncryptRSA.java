package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.abstr.SymbolicAlgorithm;
import kpi.lab6.MethodRSA;

public class EncryptRSA {
    @Parameter(names = {"-f"}, required = true)
    private String from;

    @Parameter(names = {"-t"}, required = true)
    private String to;

    @Parameter(names = {"-publ"}, required = true)
    private String publicFile;

    public static void main(String[] args) {
        EncryptRSA test = new EncryptRSA();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e) {
            System.err.println("Try again");
        }

    }

    private void action() {
        SymbolicAlgorithm.initAlphabet();
        MethodRSA rsa = new MethodRSA(publicFile, null);

        rsa.encrypt(from, to);
        System.out.println("done e");
    }
}
