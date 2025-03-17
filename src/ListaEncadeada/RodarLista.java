package ListaEncadeada;

import java.util.Scanner;

public class RodarLista {
    private static int menuPrincipal() {
        int opc;
        Scanner input = new Scanner(System.in);
        System.out.println("$$$$$$$$\\ $$\\   $$\\  $$$$$$\\   $$$$$$\\  $$$$$$$\\  $$$$$$$$\\  $$$$$$\\  $$$$$$$\\   $$$$$$\\  ");
        System.out.println("$$  _____|$$$\\  $$ |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$  _____|$$  __$$\\ $$  __$$\\ $$  __$$\\ ");
        System.out.println("$$ |      $$$$\\ $$ |$$ /  \\__|$$ /  $$ |$$ |  $$ |$$ |      $$ /  $$ |$$ |  $$ |$$ /  $$ |");
        System.out.println("$$$$$\\    $$ $$\\$$ |$$ |      $$$$$$$$ |$$ |  $$ |$$$$$\\    $$$$$$$$ |$$ |  $$ |$$$$$$$$ |");
        System.out.println("$$  __|   $$ \\$$$$ |$$ |      $$  __$$ |$$ |  $$ |$$  __|   $$  __$$ |$$ |  $$ |$$  __$$ |");
        System.out.println("$$ |      $$ |\\$$$ |$$ |  $$\\ $$ |  $$ |$$ |  $$ |$$ |      $$ |  $$ |$$ |  $$ |$$ |  $$ |");
        System.out.println("$$$$$$$$\\ $$ | \\$$ |\\$$$$$$  |$$ |  $$ |$$$$$$$  |$$$$$$$$\\ $$ |  $$ |$$$$$$$  |$$ |  $$ |");
        System.out.println("\\________|\\__|  \\__| \\______/ \\__|  \\__|\\_______/ \\________|\\__|  \\__|\\_______/ \\__|  \\__|");
        System.out.println("\n1 - Buscar elemento (nao será implementado)");
        System.out.println("2 - Listar elemento(s)");
        System.out.println("3 - Ordenar lista");
        System.out.println("4 - Gerar Valores Aleatórios");
        System.out.print("INPUT: ");
        opc = input.nextInt();
        return opc;
    }
    
    private static int menuOrdenacao() {
        int opc;
        Scanner input = new Scanner(System.in);
        System.out.println("----- ORDENAR ELEMENTOS -----");
        System.out.println("1 - Inserção Direta (feito)");
        System.out.println("2 - Bubble Sort (feito)");
        System.out.println("3 - Shakesort (feito)");
        System.out.println("4 - Seleção Direta (feito)");
        System.out.println("5 - Comb Sort (feito)");
        System.out.println("6 - Shell Sort (feito)");
        System.out.println("7 - Insercao Binaria (feito)");
        System.out.println("8 - Heap Sort (feito)");
        System.out.println("9 - Quick Sort (fazendo)");
        System.out.print("INPUT: ");
        opc = input.nextInt();
        return opc;
    }/*ordenações*/

    private static void continuar(){
        System.out.println("Pressione QUALQUER tecla para continuar");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Erro na leitura! " + e.getMessage());
        }
    }

    public static void rodar() {
        long timeIni, timeFim;
        int opc, opcInterno;
        ListaEncadeada lista = new ListaEncadeada();
        do{
            opc = menuPrincipal();
            switch(opc){
                case 1:{
                    System.out.println("Buscas ainda não implementadas!");
                    break;
                }/*buscas*/
                case 2:{
                    System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                    lista.printarValores();
                    System.out.println("***** listagem concluída *****");
                    continuar();
                    break;
                }/*listagem dos elementos*/
                case 3:{
                    opcInterno = menuOrdenacao();
                    switch(opcInterno){
                        case 1:{
                            lista.insercaoDireta();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*inserção direta*/
                        case 2:{
                            lista.bubbleSort();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*bubble sort*/
                        case 3:{
                            lista.shakesort();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*shake sort*/
                        case 4:{
                            lista.selecaoDireta();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*seleção direta*/
                        case 5:{
                            lista.combSort();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*comb sort*/
                        case 6:{
                            lista.shellSort();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*shell sort*/
                        case 7:{
                            lista.insercaoBinaria();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*insercao binaria*/
                        case 8:{
                            lista.heapSort();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*heap sort*/
                        case 9:{
                            lista.quickSortSemPivo();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*quick sort sem pivo*/
                        case 10:{
                            lista.quickSortComPivo();
                            System.out.println("Lista Ordenada com sucesso!");
                            System.out.println("----- LISTAGEM DE ELEMENTOS -----");
                            lista.printarValores();
                            System.out.println("***** listagem concluída *****");
                            continuar();
                            break;
                        }/*quick sort com pivo*/
                        default:
                            System.out.println("Essa opção não é válida");
                    }
                    break;
                }/*ordenações*/
                case 4:{
                    lista.criarElementosRand(10);
                    System.out.println("Elementos criados com sucesso!");
                    continuar();
                    break;
                }/*gerar valores aleatorios*/
                default:{
                    System.out.println("Até mais!!");
                }
            }
        }while(opc>0 && opc<5);
    }
}
