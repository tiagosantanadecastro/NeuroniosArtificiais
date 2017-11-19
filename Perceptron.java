package NeuroniosArtificiais;
public class Perceptron {

  
    private double[] w = new double[3]; //Pesos
    private double soma = 0;//somatorio da função
    private final int maxIteracoes = 30;
    private int contador = 0;
    private double taxaAprendizado=0.5;
    private int[][] matrizAprendizado = new int[4][3]; //4 linhas para as 4 possibilidades de entrada e 3 colunas: uma para entrada 1, outra para entrada 2 e outra para o valor esperado

   
  Perceptron() {


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
    w[2]= 0;//bias

}
    int calculo(int x1, int x2) {

       
        soma = (x1 * w[0]) + (x2 * w[1]) + (w[2]);

        //função de ativação
        if (soma > 0) {
            return 1;
        }
        return 0;
    }


    public void treinar() {

        
        boolean treinou= true;
        int saida;

        for (int i = 0; i < 4; i++) {
            saida = calculo(matrizAprendizado[i][0], matrizAprendizado[i][1]);
            if (saida != matrizAprendizado[i][2]) { //Simulação do erro>0
                corrigirPeso(i, saida);
                treinou = false;
            }
        }
        this.contador++;

        if((treinou == false) && (this.contador < this.maxIteracoes)) {
            treinar();
        }

    }    
    void corrigirPeso(int i, int saida) {

        w[0] = w[0] + ((matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0]*taxaAprendizado);
        w[1] = w[1] + ( (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1]*taxaAprendizado);
        w[2] = w[2] + ( (matrizAprendizado[i][2] - saida)*taxaAprendizado);

    }

    void print() {


        System.out.println(" Caso 01 para 0 e 0 " + calculo(0, 0));
        System.out.println(" Caso 02 para 0 e 1 " + calculo(0, 1));
        System.out.println(" Caso 03 para 1 e 0 " + calculo(1, 0));
        System.out.println(" Caso 04 para 1 e 1 " + calculo(1, 1));
       

    }

    public static void main(String[] arguments) {

        Perceptron p = new Perceptron();
        p.treinar();
        p.print();

    }
}
