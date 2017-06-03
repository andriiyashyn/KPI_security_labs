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

    @Override
    public void encrypt(String from, String to, int key, String text) {
        try {
            InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to);
            encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, in, out);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void decrypt(String from, String to, int key, String text) {
        try {
            InputStream in = new FileInputStream(from);
            OutputStream out = new FileOutputStream(to);
            encryptOrDecrypt(key, Cipher.DECRYPT_MODE, in, out);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void encryptOrDecrypt(int key, int mode, InputStream is, OutputStream os) throws Exception {
        DESKeySpec dks = new DESKeySpec(ByteBuffer.allocate(8).putInt(key).array());
        SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
        SecretKey desKey = skf.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");

        if (mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, desKey);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }

    private void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }

}
