package kpi.lab6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Logger;


public class MethodRSA {
    private final String ID_RSA_PRIVATE;
    private final String ID_RSA_PUBLIC;

    protected final static Logger LOGGER = Logger.getLogger(MethodRSA.class.getName());

    public static final int KEY_SIZE = 1024;

    static {
        RSAEncryptUtil.init();
    }

    public MethodRSA(String publicFile, String privateFile){
        ID_RSA_PRIVATE = privateFile;
        ID_RSA_PUBLIC = publicFile;
    }

    public void encrypt(String from, String to){
        try {
            String publicKeyString = getKeyString(ID_RSA_PUBLIC);
            PublicKey publicKey = RSAEncryptUtil.getPublicKeyFromString(publicKeyString);
            RSAEncryptUtil.encryptFile(from, to, publicKey);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void decrypt(String from, String to){
        try {
            String privateKeyString = getKeyString(ID_RSA_PRIVATE);
            PrivateKey privateKey = RSAEncryptUtil.getPrivateKeyFromString(privateKeyString);
            RSAEncryptUtil.decryptFile(from, to, privateKey);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getKeyString(String filename) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(filename));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null){
            builder.append(line);
        }

        in.close();

        return builder.toString();
    }

    public void generateKeys(){
        try {
            KeyPair pair = RSAEncryptUtil.generateKey();
            PrivateKey privateKey = pair.getPrivate();
            PublicKey publicKey = pair.getPublic();

            String privateKeyString = RSAEncryptUtil.getKeyAsString(privateKey);
            String publicKeyString = RSAEncryptUtil.getKeyAsString(publicKey);

            BufferedWriter publicKeyFile = new BufferedWriter(new FileWriter(ID_RSA_PUBLIC));
            BufferedWriter privateKeyFile = new BufferedWriter(new FileWriter(ID_RSA_PRIVATE));

            publicKeyFile.write(publicKeyString);
            privateKeyFile.write(privateKeyString);

            publicKeyFile.close();
            privateKeyFile.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

