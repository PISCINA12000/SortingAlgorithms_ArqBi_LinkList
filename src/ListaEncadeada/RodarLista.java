package ListaEncadeada;

import Auxiliares.CoresNoConsole;
import java.util.Scanner;

public class RodarLista {
    private static final int N = 32;

    private static int menuPrincipal() {
        int opc;
        Scanner input = new Scanner(System.in);
        System.out.println(CoresNoConsole.ROXO +"$$$$$$$$\\ $$\\   $$\\  $$$$$$\\   $$$$$$\\  $$$$$$$\\  $$$$$$$$\\  $$$$$$\\  $$$$$$$\\   $$$$$$\\  ");
        System.out.println("$$  _____|$$$\\  $$ |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$  _____|$$  __$$\\ $$  __$$\\ $$  __$$\\ ");
        System.out.println("$$ |      $$$$\\ $$ |$$ /  \\__|$$ /  $$ |$$ |  $$ |$$ |      $$ /  $$ |$$ |  $$ |$$ /  $$ |");
        System.out.println("$$$$$\\    $$ $$\\$$ |$$ |      $$$$$$$$ |$$ |  $$ |$$$$$\\    $$$$$$$$ |$$ |  $$ |$$$$$$$$ |");
        System.out.println("$$  __|   $$ \\$$$$ |$$ |      $$  __$$ |$$ |  $$ |$$  __|   $$  __$$ |$$ |  $$ |$$  __$$ |");
        System.out.println("$$ |      $$ |\\$$$ |$$ |  $$\\ $$ |  $$ |$$ |  $$ |$$ |      $$ |  $$ |$$ |  $$ |$$ |  $$ |");
        System.out.println("$$$$$$$$\\ $$ | \\$$ |\\$$$$$$  |$$ |  $$ |$$$$$$$  |$$$$$$$$\\ $$ |  $$ |$$$$$$$  |$$ |  $$ |");
        System.out.println("\\________|\\__|  \\__| \\______/ \\__|  \\__|\\_______/ \\________|\\__|  \\__|\\_______/ \\__|  \\__|"+CoresNoConsole.RESET);
        System.out.println("\n1 - Listar elemento(s)");
        System.out.println("2 - Ordenar lista");
        System.out.println("3 - Gerar valores aleatórios");
        System.out.print(CoresNoConsole.AMARELO+"INPUT: "+CoresNoConsole.RESET);
        opc = input.nextInt();
        return opc;
    }
    
    private static int menuOrdenacao() {
        int opc;
        Scanner input = new Scanner(System.in);
        System.out.println(CoresNoConsole.ROXO+"\n\n----- ORDENAR ELEMENTOS -----"+CoresNoConsole.RESET);
        System.out.println("1 - Inserção Direta");
        System.out.println("2 - Bubble Sort ");
        System.out.println("3 - Shakesort");
        System.out.println("4 - Seleção Direta");
        System.out.println("5 - Comb Sort");
        System.out.println("6 - Shell Sort");
        System.out.println("7 - Inserção Binária");
        System.out.println("8 - Heap Sort");
        System.out.println("9 - Quick Sort SEM pivo");
        System.out.println("10 - Quick Sort COM pivo");
        System.out.println("11 - Count Sort");
        System.out.println("12 - Radix Sort");
        System.out.println("13 - Bucket Sort");
        System.out.println("14 - Gnome Sort");
        System.out.println("15 - Merge Sort PRIMEIRA implementação");
        System.out.println("16 - Merge Sort SEGUNDA implementação");
        System.out.println("17 - Tim Sort");
        System.out.print(CoresNoConsole.AMARELO+"INPUT: "+CoresNoConsole.RESET);
        opc = input.nextInt();
        return opc;
    }/*ordenações*/

    private static void continuar(){
        System.out.println(CoresNoConsole.AMARELO+"Pressione QUALQUER tecla para continuar"+CoresNoConsole.RESET);
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println(CoresNoConsole.VERMELHO+"Erro na leitura! " + e.getMessage()+CoresNoConsole.RESET);
        }
    }

    public static void rodar() {
        int opc, opcInterno;
        ListaEncadeada lista = new ListaEncadeada();
        do{
            opc = menuPrincipal();
            switch(opc){
                case 1:{
                    System.out.println(CoresNoConsole.CIANO+"\n----- LISTAGEM DE ELEMENTOS -----"+CoresNoConsole.RESET);
                    lista.printarValores();
                    System.out.println("***** listagem concluída *****\n");
                    continuar();
                    break;
                }/*listagem dos elementos*/
                case 2:{
                    opcInterno = menuOrdenacao();
                    System.out.println(CoresNoConsole.VERMELHO+"\n\nLista Antes da ordenação: "+CoresNoConsole.RESET);
                    lista.printarValores();
                    switch(opcInterno){
                        case 1:
                            lista.insercaoDireta();
                            break;
                        case 2:
                            lista.bubbleSort();
                            break;
                        case 3:
                            lista.shakesort();
                            break;
                        case 4:
                            lista.selecaoDireta();
                            break;
                        case 5:
                            lista.combSort();
                            break;
                        case 6:
                            lista.shellSort();
                            break;
                        case 7:
                            lista.insercaoBinaria();
                            break;
                        case 8:
                            lista.heapSort();
                            break;
                        case 9:
                            //quick sort SEM pivo
                            lista.quickSortSemPivo();
                            break;
                        case 10:
                            //quick sort COM pivo
                            lista.quickSortComPivo();
                            break;
                        case 11:
                            lista.countSort();
                            break;
                        case 12:
                            lista.radixSort();
                            break;
                        case 13:
                            lista.bucketSort();
                            break;
                        case 14:
                            lista.gnomeSort();
                            break;
                        case 15:
                            //merge sort primeira implementação, múltiplos de 2
                            lista.mergeSortPri();
                            break;
                        case 16:
                            //merge sort segunda implementação, qualquer multiplicidade
                            lista.mergeSortSeg();
                            break;
                        case 17:
                            lista.timSort();
                            break;
                        default:
                            System.out.println(CoresNoConsole.VERMELHO+"Essa opção não é válida"+CoresNoConsole.RESET);
                    }
                    if(opcInterno>0 &&opcInterno<18){
                        System.out.println(CoresNoConsole.VERDE+"\nLista Depois da ordenação: "+CoresNoConsole.RESET);
                        lista.printarValores();
                        System.out.println();
                    }
                    continuar();
                    break;
                }/*ordenações*/
                case 3:{
                    lista.criarElementosRand(N);
                    System.out.println(CoresNoConsole.VERDE+"Elementos criados com sucesso!"+CoresNoConsole.RESET);
                    continuar();
                    break;
                }/*gerar valores aleatorios*/
                default:{
                    System.out.println("\n\nAté mais!");
                }
            }
        }while(opc>0 && opc<5);
    }
}
