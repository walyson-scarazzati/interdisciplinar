package extras;

/**
 * Validação e normalização de CPF e RG.
 */
public class Documentos {

    public static String normalizarCPF(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.replaceAll("\\D", "");
    }

    public static String normalizarRG(String rg) {
        if (rg == null) {
            return "";
        }
        return rg.trim().toUpperCase();
    }

    public static boolean validarCPF(String cpf) {
        String numeros = normalizarCPF(cpf);
        if (numeros.length() != 11) {
            return false;
        }
        if (numeros.matches("(\\d)\\1{10}")) { // sequências repetidas: 111.111.111-11 etc.
            return false;
        }

        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = numeros.charAt(i) - '0';
        }

        int primeiroDV = calcularDigitoVerificador(digitos, 9);
        if (primeiroDV != digitos[9]) {
            return false;
        }

        int segundoDV = calcularDigitoVerificador(digitos, 10);
        return segundoDV == digitos[10];
    }

    private static int calcularDigitoVerificador(int[] digitos, int qtdDigitos) {
        int soma = 0;
        int peso = qtdDigitos + 1;
        for (int i = 0; i < qtdDigitos; i++) {
            soma += digitos[i] * peso;
            peso--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    public static boolean validarRG(String rg) {
        String valor = normalizarRG(rg);
        if (valor.isEmpty()) {
            return false;
        }
        return valor.matches("[0-9A-Z.-]{5,15}") && valor.replaceAll("\\D", "").length() >= 5;
    }
}
