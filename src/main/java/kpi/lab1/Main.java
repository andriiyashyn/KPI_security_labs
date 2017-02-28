package kpi.lab1;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Main {
    @Parameter(names = {"--key", "-k"}, required = true)
    private int key;

    @Parameter(names = {"--encrypt", "-e"})
    private boolean encrypt = false;

    @Parameter(names = {"--decrypt", "-d"})
    private boolean decrypt = false;

    @Parameter(names = {"--from", "-f"}, required = true)
    private String fromFileName;


    @Parameter(names = {"--to", "-t"}, required = true)
    private String toFileName;

    public static void main(String[] args) {
        Main main = new Main();
        new JCommander(main, args);
        main.action();
    }
    
    public void action(){
        if(encrypt){
            new MehtodSezar().encrypt(fromFileName, toFileName, key);
        }
        
        if(decrypt){
            new MehtodSezar().decrypt(fromFileName, toFileName, key);
        }
    }
}
