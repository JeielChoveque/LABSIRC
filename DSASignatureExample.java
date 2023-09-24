import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DSASignatureExample {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        int keySizes = 2048;


        System.out.println("Tamanho da Chave: " + keySizes + " bits");

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "BC");
        keyGen.initialize(keySizes);
        KeyPair keyPair = keyGen.generateKeyPair();

        Signature signature = Signature.getInstance("SHA1withDSA", "BC");
        signature.initSign(keyPair.getPrivate());

        byte[] message = new byte[]{(byte) 'a', (byte) 'b', (byte) 'c', (byte) 125};

        signature.update(message);

        byte[] sigBytes = signature.sign();

        signature.initVerify(keyPair.getPublic());
        signature.update(message);

        if (signature.verify(sigBytes)) {
            System.out.println("Assinatura válida - reconhecida");
        } else {
            System.out.println("Assinatura não reconhecida");
        }


    }
}
