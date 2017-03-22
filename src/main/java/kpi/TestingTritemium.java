package kpi;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import kpi.lab2.MethodTritemium;

/**
 * Created by andrew_yashin on 3/20/17.
 */
public class TestingTritemium {

    @Parameter(names = {"-e"})
    private boolean encrypt = false;

    @Parameter(names = {"-d"})
    private boolean decrypt = false;

    @Parameter(names = {"-f"}, required = true)
    private String from;

    @Parameter(names = {"-t"}, required = true)
    private String to;

    @Parameter(names = {"-c1"}, required = true)
    private int c1;

    @Parameter(names = {"-c0"}, required = true)
    private int c0;

    @Parameter(names = {"-c2"})
    private int c2 = 0;

    public static void main(String[] args) {
        TestingTritemium test = new TestingTritemium();

        try {
            new JCommander(test, args);
            test.action();
        } catch (ParameterException e){
            System.err.println("Input all required arguments \n" +
                    " -f - from File\n" +
                    " -t - to File\n" +
                    " -c0, -c1, ( -c2 - not required ) - arguments\n");
        }

    }

    public void action(){
        if(encrypt){
            new MethodTritemium(c2, c1, c0).encrypt(from, to, 0);
            System.out.println("work is done (e)");
        } else if(decrypt){
            new MethodTritemium(c2, c1, c0).decrypt(from, to, 0);
            System.out.println("work is done (d)");
        } else{
            System.out.println("nothing to do");
        }
    }
}
