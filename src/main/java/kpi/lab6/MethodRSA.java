package kpi.lab6;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.logging.Logger;


public class MethodRSA {
    private static final String ID_RSA_PRIVATE = "id_rsa";
    private static final String ID_RSA_PUBLIC = "id_rsa.pub";

    protected final static Logger LOGGER = Logger.getLogger(MethodRSA.class.getName());

    public static final int KEY_SIZE = 1024;

    public void encrypt(String from, String to) {
        encryptOrDecrypt(from, to, Cipher.ENCRYPT_MODE);
    }

    public void decrypt(String from, String to) {
        encryptOrDecrypt(from, to, Cipher.DECRYPT_MODE);
    }

    private void encryptOrDecrypt(String from, String to, int mode) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(from);
            out = new FileOutputStream(to);

            KeyFactory factory = KeyFactory.getInstance("RSA");

            Key key = null;
            if (mode == Cipher.ENCRYPT_MODE) {
                key = generatePublicKey(factory, ID_RSA_PUBLIC);
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(mode, key);
                CipherInputStream cis = new CipherInputStream(in, cipher);
                doCopy(cis, out);
            }
            else {
                key = generatePrivateKey(factory, ID_RSA_PRIVATE);
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(mode, key);
                CipherOutputStream cos = new CipherOutputStream(out, cipher);
                doCopy(in, cos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[128];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }

    private PrivateKey generatePrivateKey(KeyFactory factory, String filename) throws InvalidKeySpecException, FileNotFoundException, IOException {
        PemFileForFile pemFile = new PemFileForFile(filename);
        byte[] content = pemFile.getPemObject().getContent();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
        return factory.generatePrivate(privKeySpec);
    }

    private PublicKey generatePublicKey(KeyFactory factory, String filename) throws InvalidKeySpecException, FileNotFoundException, IOException {
        PemFileForFile pemFile = new PemFileForFile(filename);
        byte[] content = pemFile.getPemObject().getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
        return factory.generatePublic(pubKeySpec);
    }

    public void generateKeyFiles() {
        Security.addProvider(new BouncyCastleProvider());
        LOGGER.info("BouncyCastle provider added.");

        try {
            KeyPair keyPair = generateRSAKeyPair();
            RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey pub = (RSAPublicKey) keyPair.getPublic();

            writePemFile(priv, "RSA PRIVATE KEY", ID_RSA_PRIVATE);
            writePemFile(pub, "RSA PUBLIC KEY", ID_RSA_PUBLIC);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
        generator.initialize(KEY_SIZE);

        KeyPair keyPair = generator.generateKeyPair();
        LOGGER.info("RSA key pair generated.");
        return keyPair;
    }

    private void writePemFile(Key key, String description, String filename)
            throws FileNotFoundException, IOException {
        PemFile pemFile = new PemFile(key, description);
        pemFile.write(filename);

        LOGGER.info(String.format("%s successfully writen in file %s.", description, filename));
    }

    private class PemFile {

        private PemObject pemObject;

        public PemFile(Key key, String description) {
            this.pemObject = new PemObject(description, key.getEncoded());
        }

        public void write(String filename) throws FileNotFoundException, IOException {
            PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            try {
                pemWriter.writeObject(this.pemObject);
            } finally {
                pemWriter.close();
            }
        }

        public PemObject read(String filename) throws IOException {
            PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
            try {
                return pemReader.readPemObject();
            } finally {
                pemReader.close();
            }
        }
    }


    private class PemFileForFile {

        private PemObject pemObject;

        public PemFileForFile(String filename) throws FileNotFoundException, IOException {
            PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
            try {
                this.pemObject = pemReader.readPemObject();
            } finally {
                pemReader.close();
            }
        }

        public PemObject getPemObject() {
            return pemObject;
        }
    }
}

