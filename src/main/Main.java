import analiz.Analizador;

public class Main {
    public static void main(String[] args) {
        Analizador analizador = new Analizador();
        int[] valores = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        double[] probs = { 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9 };
        int[] resultado = analizador.acha_sequencia(probs, valores);
        for (int i = 0; i < resultado.length; i++) {
            System.out.println(resultado[i]);
        }
    }

}
