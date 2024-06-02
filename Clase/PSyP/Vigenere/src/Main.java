public class Main {

    public static void main(String[] args) {
        String mensajeEncriptado = "KGEFAXMNQLJATRZZBNMSIPZZUTTSOU", mensajeDesencriptado = "ESTAMOSACABANDOUNESFUERZOFINAL";
        String clave = encontrarClave(mensajeEncriptado, mensajeDesencriptado);
        System.out.println(clave);
    }

    public static String encontrarClave(String mensajeEncriptado, String mensajeDesencriptado) {
        int longitudClave = mensajeDesencriptado.length();
        StringBuilder clave = new StringBuilder();
        for (int i = 0;i < longitudClave; i ++) {
            char caracterEncriptado = mensajeEncriptado.charAt(i);
            char caracterDesencriptado = mensajeDesencriptado.charAt(i);
            char caracterClave = (char)((((caracterEncriptado)- 'A') - (caracterDesencriptado - 'A') + 26) % 26 + 'A');
            clave.append(caracterClave);
        }
        return clave.toString();
    }

    public static String encriptar(String mensaje, String clave) {
        StringBuilder mensajeEncriptado = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++) {
            char caracterMensaje = mensaje.charAt(i);
            char caracterClave = clave.charAt(i % clave.length());
            char caracterEncriptado = (char)(((caracterMensaje - 'A') + (caracterClave - 'A')) % 26 + 'A');
            mensajeEncriptado.append(caracterEncriptado);
        }

        return mensajeEncriptado.toString();
    }

    public static String desencriptar(String mensajeEncriptado, String clave) {
        StringBuilder mensajeDesencriptado = new StringBuilder();

        for (int i = 0; i < mensajeEncriptado.length(); i++) {
            char encryptedChar = mensajeEncriptado.charAt(i);
            char keyChar = clave.charAt(i % clave.length());
            char decryptedChar = (char)(((encryptedChar - 'A') - (keyChar - 'A') + 26) % 26 + 'A');
            me.append(decryptedChar);
        }

        return decryptedMessage.toString();
    }
}