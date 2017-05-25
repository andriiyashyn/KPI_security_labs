package kpi.lab5;

import kpi.abstr.SymbolicAlgorithm;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;

public class MethodDES extends SymbolicAlgorithm {
    private static Cipher encryptCipher;
    private static Cipher decryptCipher;

    @Override
    public void encrypt(String from, String to, int key, String text) {
        try {
            SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
            AlgorithmParameterSpec spec = new IvParameterSpec(ByteBuffer.allocate(8).putInt(key).array());

            encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);

            if(text == null){
                OutputStream os = new CipherOutputStream(new FileOutputStream(from), encryptCipher);
                writeData(new FileInputStream(to), os);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        try {
            SecretKey secretKey = KeyGenerator.getInstance("DES").generateKey();
            AlgorithmParameterSpec spec = new IvParameterSpec(ByteBuffer.allocate(8).putInt(key).array());

            decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

            if(text == null){
                InputStream is = new CipherInputStream(new FileInputStream(from), decryptCipher);
                writeData(is, new FileOutputStream(to));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private static void writeData(InputStream is, OutputStream os) throws IOException{
        byte[] buf = new byte[8];
        int numRead = 0;
        //read and write operation
        while ((numRead = is.read(buf)) != -1) {
            os.write(buf, 0, numRead);
        }
        os.close();
        is.close();
    }

}
