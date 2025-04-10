import ArquivoBinario.RodarBinario;
import Auxiliares.CoresNoConsole;
import ListaEncadeada.RodarLista;
import VetorEstatico.RodarVetor;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static int menuPrincipal() {
        Scanner leitor = new Scanner(System.in);
        System.out.println(CoresNoConsole.CIANO+"$$\\      $$\\                               ");
        System.out.println("$$$\\    $$$ |                              ");
        System.out.println("$$$$\\  $$$$ | $$$$$$\\  $$$$$$$\\  $$\\   $$\\ ");
        System.out.println("$$\\$$\\$$ $$ |$$  __$$\\ $$  __$$\\ $$ |  $$ |");
        System.out.println("$$ \\$$$  $$ |$$$$$$$$ |$$ |  $$ |$$ |  $$ |");
        System.out.println("$$ |\\$  /$$ |$$   ____|$$ |  $$ |$$ |  $$ |");
        System.out.println("$$ | \\_/ $$ |\\$$$$$$$\\ $$ |  $$ |\\$$$$$$  |");
        System.out.println("\\__|     \\__| \\_______|\\__|  \\__| \\______/ "+CoresNoConsole.RESET);
        System.out.println("\n1 - LISTA ENCADEADA");
        System.out.println("2 - ARQUIVO BINÁRIO");
        System.out.println("3 - VETORES");
        System.out.println(CoresNoConsole.AMARELO+"\nEscolha a opção: "+CoresNoConsole.RESET);

        return leitor.nextInt();
    }

    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        int opc, opcao = 1;
        do {
            opc = menuPrincipal();
            switch (opc) {
                case 1: {
                    // Va para a execucao das listas encadeadas
                    RodarLista.rodar();
                    break;
                }
                case 2: {
                    // Va para a execucao do arquivo binario
                    try{
                        RodarBinario.rodar();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case 3:{
                    // Va para vetores
                    RodarVetor.rodar();
                    break;
                }
                default: {
                    System.out.println(CoresNoConsole.VERMELHO +"OPÇÃO INVÁLIDA!!"+CoresNoConsole.RESET);
                }
            }
            System.out.println(CoresNoConsole.AMARELO+"\nQuer continuar? [0]NAO [1]SIM"+CoresNoConsole.RESET);
            opcao = leitor.nextInt();
        } while (opcao==1); //continuar a execucao do programa
        System.out.println("\nAté mais!");
    }
}
