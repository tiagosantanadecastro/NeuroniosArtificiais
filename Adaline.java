package NeuroniosArtificiais;

public class Adaline {

    private double[] w = new double[3]; //Pesos
    private double soma = 0;//somatorio da função
    private final int maxIteracoes = 30;
    private int contador = 0;
    private double Epson=0.1;
    private double taxaAprendizado = 0.5;
    private double erroQuadraticoMedio=0;
    private double erroQuadraticoMedioAnterior=0;
    private int[][] matrizAprendizado = new int[4][3]; //4 linhas para as 4 possibilidades de entrada e 3 colunas: uma para entrada 1, outra para entrada 2 e outra para o valor esperado

    Adaline() {

        this.matrizAprendizado[0][0] = 0;
        this.matrizAprendizado[0][1] = 0;
        this.matrizAprendizado[0][2] = 0; // valor esperado

        this.matrizAprendizado[1][0] = 0;
        this.matrizAprendizado[1][1] = 1;
        this.matrizAprendizado[1][2] = 0; //valor esperado

        this.matrizAprendizado[2][0] = 1;
        this.matrizAprendizado[2][1] = 0;
        this.matrizAprendizado[2][2] = 0; //valor esperado

        this.matrizAprendizado[3][0] = 1;
        this.matrizAprendizado[3][1] = 1;
        this.matrizAprendizado[3][2] = 1;//valoresperado

        w[0] = 0;
        w[1] = 0;
        w[2] = 0;//bias

    }

    double calculo(int x1, int x2) {

        soma = (x1 * w[0]) + (x2 * w[1]) + (w[2]);

        //função de ativação
       return Math.tanh(soma);
    }

    public void treinar() {
  
        
        double saida;
        for (int i = 0; i < 4; i++) {
            saida = calculo(matrizAprendizado[i][0], matrizAprendizado[i][1]);
            calculaErroQuadraticoMedio(i,  saida);
            corrigirPeso(i,saida);  
        }
       
        
        if ((Math.abs(erroQuadraticoMedio-erroQuadraticoMedioAnterior)>Epson) && (this.contador < this.maxIteracoes)) {
           erroQuadraticoMedioAnterior=erroQuadraticoMedio;
           erroQuadraticoMedio=0;
           this.contador++;        
           treinar();
          
        }

    }
    double calculaErroQuadraticoMedio(int i,double saida){
       erroQuadraticoMedio=erroQuadraticoMedio+Math.pow(matrizAprendizado[i][2]-saida, 2);
       erroQuadraticoMedio=erroQuadraticoMedio/(i+1);
        return erroQuadraticoMedio;
    }

    void corrigirPeso(int i, double saida) {

        w[0] = w[0] + ((matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0] * taxaAprendizado);
        w[1] = w[1] + ((matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1] * taxaAprendizado);
        w[2] = w[2] + ((matrizAprendizado[i][2] - saida) * taxaAprendizado);
    }

    void print() {

        System.out.println(" Caso 01 para 0 e 0 " + calculo(0, 0));
        System.out.println(" Caso 02 para 0 e 1 " + calculo(0, 1));
        System.out.println(" Caso 03 para 1 e 0 " + calculo(1, 0));
        System.out.println(" Caso 04 para 1 e 1 " + calculo(1, 1));
        
       
    }

    public static void main(String[] arguments) {

        Adaline p = new Adaline();
        p.treinar();
        p.print();

    }
}
