package analiz;

/**
 * Analizador
 */
public class Analizador {

    public int[] acha_sequencia(double[] probs, int[] valores) {
        if (valores.length < 1) {
            int[] aux = { 0 };
            return aux;
        }
        int[] sequencia = new int[valores.length];
        int promissor = 0;
        int valorPromissor = 0;
        double pesoPromissor = (1 - probs[0]) / valores[0];

        for (int i = 0; i < valores.length; i++) {
            double aux = (1 - probs[i]) / valores[i];
            if (aux < pesoPromissor) {
                promissor = i;
                valorPromissor = valores[i];
                pesoPromissor = aux;
            }
        }

        pesoPromissor = 1 - pesoPromissor;
        adicionarPromissorSequencia(sequencia, 0, promissor);
        probs[promissor] = -1;
        valores[promissor] = -1;
        int j = 1;
        for (int i = 0; i < valores.length; i++) {
            if (valores[i] >= 0) {

                int candidato = promissor(valores, probs, pesoPromissor, valorPromissor);
                pesoPromissor = calculaPesoPromissor(pesoPromissor, probs, candidato);
                valorPromissor = calculaValorPromissor(valores, candidato, valorPromissor);
                adicionarPromissorSequencia(sequencia, j, candidato);
                probs[candidato] = -1;
                valores[candidato] = -1;
                j++;
                i--;
            }
        }

        return sequencia;
    }

    private void adicionarPromissorSequencia(int[] sequencia, int i, int promissor) {
        sequencia[i] = promissor;
    }

    private int calculaValorPromissor(int[] valores, int promissor, int valorPromissorAnterior) {
        int valorPromissorAtual = valores[promissor];
        return valorPromissorAnterior + valorPromissorAtual;
    }

    private double calculaPesoPromissor(double pesoPromissorAnterior, double[] probs, int promissor) {

        return pesoPromissorAnterior * probs[promissor];

    }

    private int promissor(int[] valores, double[] probs, double pesoPromissorAnterior, double valorPromissorAnterior) {
        double pesoPromissor = 0;
        int promissor = 0;
        for (int i = 0; i <= valores.length; i++) {
            if (valores[i] >= 0) {
                pesoPromissor = (pesoPromissorAnterior * (1 - probs[i])) / (valorPromissorAnterior + valores[i]);
                promissor = i;
                break;
            }
        }

        for (int i = 0; i < valores.length; i++) {
            if (valores[i] >= 0) {
                double aux = (pesoPromissorAnterior * (1 - probs[i])) / (valorPromissorAnterior + valores[i]);
                if (aux < pesoPromissor) {
                    promissor = i;
                }
            }
        }
        return promissor;
    }
}