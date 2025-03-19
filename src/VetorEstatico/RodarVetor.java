package VetorEstatico;

import java.util.Scanner;

public class RodarVetor {
    private static int menuPrincipal() {
        int opc;
        Scanner input = new Scanner(System.in);
        System.out.println("----- MENU DE VETOR ESTÁTICO -----");
        System.out.println("1 - Adicionar elemento");
        System.out.println("2 - Remover elemento");
        System.out.println("3 - Buscar elemento");
        System.out.println("4 - Listar elemento(s)");
        System.out.println("5 - Ordenar vetor");
        System.out.println("6 - Embaralhar vetor");
        System.out.print("INPUT: ");
        opc = input.nextInt();
        return opc;
    }

    private static int menuBusca() {
        int opcInterno;
        Scanner input = new Scanner(System.in);
        System.out.println("-----  MENU DE BUSCAS -----");
        System.out.println("1 - Busca Exaustiva");
        System.out.println("2 - Busca Exaustiva com Sentinela");
        System.out.println("3 - Busca Sequencial");
        System.out.println("4 - Busca Sequencial com Sentinela");
        System.out.println("5 - Busca Binária");
        System.out.print("INPUT: ");
        opcInterno = input.nextInt();
        return opcInterno;
    }

    private static int menuOrdenacao() {
        int opcInterno;
        Scanner input = new Scanner(System.in);
        System.out.println("----- MENU DE ORDENAÇÃO -----");
        System.out.println("1 - Insercao Direta (feito)");
        System.out.println("2 - Bubble Sort (feito)");
        System.out.println("3 - Shakesort (feito)");
        System.out.println("4 - Selecao Direta (feito)");
        System.out.println("5 - Comb Sort (feito)");
        System.out.println("6 - Shell Sort (feito)");
        System.out.println("7 - Insercao Binaria (feito)");
        System.out.println("8 - Heap Sort (feito)");
        System.out.println("9 - Quick Sort SEM pivo (feito)");
        System.out.println("10 - Quick Sort COM pivo (feito)");
        System.out.println("11 - Count Sort (feito)");
        System.out.println("12 - Radix Sort (feito)");
        System.out.println("13 - Bucket Sort (feito)");
        System.out.println("14 - Gnome Sort (fazendo)");
        System.out.print("INPUT: ");
        opcInterno = input.nextInt();
        return opcInterno;
    }

    private static void continuar() {
        System.out.println("\nPressione QUALQUER tecla para continuar");
        try {
            System.in.read();
        } catch (Exception e) {
            System.out.println("Erro na leitura! " + e.getMessage());
        }
    }

    public static void rodar() {
        Vetor vet = new Vetor();
        vet.alimentar(10);
        int opc, opcInterno, num;
        Scanner input = new Scanner(System.in);

        do {
            opc = menuPrincipal();
            switch (opc) {
                case 1: {
                    System.out.println("----- ADICIONAR -----");
                    System.out.print("INPUT: ");
                    num = input.nextInt();
                    if (vet.pushVetor(num)) {
                        System.out.println("Adicionado ao vetor com sucesso!");
                    } else {
                        System.out.println("Muitos elementos no vetor!!");
                    }
                    break;
                }/*adicionar elemento*/
                case 2: {
                    int pos;
                    System.out.println("----- REMOVER ELEMENTO -----");
                    System.out.print("INPUT: ");
                    num = input.nextInt();
                    pos = vet.buscaExaustiva(num);
                    vet.remanejar(pos);
                    break;
                }/*remover elemento*/
                case 3: {
                    int posBusca;
                    System.out.println("----- BUSCAR ELEMENTO -----");
                    System.out.println("Elemento a ser buscado: ");
                    num = input.nextInt();
                    opcInterno = menuBusca();
                    switch (opcInterno) {
                        case 1: {
                            posBusca = vet.buscaExaustiva(num);
                            if (posBusca > -1) { //achou o elemento
                                System.out.println("Elemento encontrado na posicao: " + posBusca + " Com valor: " + vet.getPosVet(posBusca));
                            } else {
                                System.out.println("Nada encontrado!!");
                            }
                            break;
                        }
                        case 2: {
                            posBusca = vet.buscaExasSentinela(num);
                            if (posBusca > -1) { //achou o elemento
                                System.out.println("Elemento encontrado na posicao: " + posBusca + " Com valor: " + vet.getPosVet(posBusca));
                            } else {
                                System.out.println("Nada encontrado!!");
                            }
                            break;
                        }
                        case 3: {
                            posBusca = vet.buscaSequencial(num);
                            if (posBusca > -1) { //achou o elemento
                                System.out.println("Elemento encontrado na posicao: " + posBusca + " Com valor: " + vet.getPosVet(posBusca));
                            } else {
                                System.out.println("Nada encontrado!!");
                            }
                            break;
                        }
                        case 4: {
                            posBusca = vet.buscaSeqSentinela(num);
                            if (posBusca > -1) { //achou o elemento
                                System.out.println("Elemento encontrado na posicao: " + posBusca + " Com valor: " + vet.getPosVet(posBusca));
                            } else {
                                System.out.println("Nada encontrado!!");
                            }
                            break;
                        }
                        case 5: {
                            posBusca = vet.buscaBinaria(num);
                            if (posBusca > -1) { //achou o elemento
                                System.out.println("Elemento encontrado na posicao: " + posBusca + " Com valor: " + vet.getPosVet(posBusca));
                            } else {
                                System.out.println("Nada encontrado!!");
                            }
                            break;
                        }
                        default:
                            System.out.println();
                    }
                    break;
                }/*buscar elemento*/
                case 4: {
                    System.out.println("----- LISTAGEM ELEMENTOS -----");
                    vet.exibirVetor();
                    System.out.println("--- Fim da listagem ---");
                    continuar();
                    break;
                }/*listar elementos*/
                case 5: {
                    opcInterno = menuOrdenacao();
                    switch (opcInterno) {
                        case 1: {
                            /*insercao direta*/
                            vet.insercaoDireta();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*insercao direta*/
                        case 2: {
                            /*bubble sort*/
                            vet.bubbleSort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*bubble sort*/
                        case 3: {
                            /*shake sort*/
                            vet.shakesort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*shake sort*/
                        case 4: {
                            /*selecao direta*/
                            vet.selecaoDireta();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*selecao direta*/
                        case 5: {
                            /*comb sort*/
                            vet.combSort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*comb sort*/
                        case 6: {
                            /*shell sort*/
                            vet.shellSort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*shell sort*/
                        case 7: {
                            /*insercao binaria*/
                            vet.insercaoBinaria();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*insercao binaria*/
                        case 8: {
                            /*heap sort*/
                            vet.heapSort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*heap sort*/
                        case 9: {
                            /*quick sort sem pivo*/
                            vet.quickSortSemPivo();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*quick sort sem pivo*/
                        case 10: {
                            /*quick sort com pivo professor*/
                            vet.quickComPivoProfessor();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*quick sort com pivo professor*/
                        case 11: {
                            /*count sort*/
                            vet.countSort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*count sort*/
                        case 12: {
                            /*radix sort*/
                            vet.radixSort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*radix sort*/
                        case 13:{
                            /*bucket sort*/
                            vet.bucketSort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*bucket sort*/
                        case 14:{
                            /*gnome sort*/
                            vet.gnomeSort();
                            System.out.println("Vetor ordenado com sucesso!");
                            continuar();
                            break;
                        } /*gnome sort*/
                        default:
                            System.out.println("Nenhuma opcao valida selecionada!");
                    }
                    break;
                }/*ordenar elementos*/
                case 6: {
                    vet.embaralhar();
                    System.out.println("Vetor embaralhado!!");
                    continuar();
                    break;
                }/*embaralhar elementos*/
                default:
                    System.out.println("Até mais!!");
            }
        } while (opc > 0);
    }
}
