package extras;

import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Hash e verificação de senha via PBKDF2WithHmacSHA1 (nativo do JDK, sem
 * dependência externa).
 */
public class SenhaUtil {

    private static final int ITERACOES = 65536;
    private static final int TAMANHO_CHAVE = 160; // bits
    private static final int TAMANHO_SALT = 16; // bytes
    private static final String ALGORITMO = "PBKDF2WithHmacSHA1";

    public static String gerarHash(String senha) {
        try {
            byte[] salt = new byte[TAMANHO_SALT];
            new SecureRandom().nextBytes(salt);
            byte[] hash = derivarChave(senha.toCharArray(), salt, ITERACOES);
            return ITERACOES + ":" + Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao gerar hash de senha", ex);
        }
    }

    public static boolean verificar(String senha, String hashArmazenado) {
        try {
            String[] partes = hashArmazenado.split(":");
            int iteracoes = Integer.parseInt(partes[0]);
            byte[] salt = Base64.getDecoder().decode(partes[1]);
            byte[] hashEsperado = Base64.getDecoder().decode(partes[2]);

            byte[] hashCalculado = derivarChave(senha.toCharArray(), salt, iteracoes);
            return constantTimeEquals(hashEsperado, hashCalculado);
        } catch (Exception ex) {
            return false;
        }
    }

    private static byte[] derivarChave(char[] senha, byte[] salt, int iteracoes) throws InvalidKeySpecException, java.security.NoSuchAlgorithmException {
        PBEKeySpec spec = new PBEKeySpec(senha, salt, iteracoes, TAMANHO_CHAVE);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITMO);
        return skf.generateSecret(spec).getEncoded();
    }

    private static boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a.length != b.length) {
            return false;
        }
        int resultado = 0;
        for (int i = 0; i < a.length; i++) {
            resultado |= a[i] ^ b[i];
        }
        return resultado == 0;
    }
}
