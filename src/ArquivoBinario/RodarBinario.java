package ArquivoBinario;

import Auxiliares.CoresNoConsole;
import Auxiliares.Tabela;

import java.io.File;
import java.io.IOException;

public class RodarBinario {
    public final int N = 1024;
    long tini, tfim, ttotalO;
    int compO, movO;
    int compOrd, compEqOrd, movOrd, movEqOrd;
    int compRev, compEqRev, movRev, movEqRev;
    int compRand, compEqRand, movRand, movEqRand;
    double tseg, e = 2.71828, g = 0.577216;
    long tempoOrd, tempoRev, tempoRand;
    Arquivo arqOrd = new Arquivo("Ordenado");
    Arquivo arqRev = new Arquivo("ReversamenteOrdenado");
    Arquivo arqRand = new Arquivo("Aleatorio");
    Arquivo arqCopRand = new Arquivo("CopiaRand");
    //...

    private void comecar(Arquivo arquivo){
        arquivo.initComp();
        arquivo.initMov();
        tini = System.currentTimeMillis();
    }

    private void finaliza(Arquivo arquivo) {
        tfim = System.currentTimeMillis();
        compO = arquivo.getComp();
        movO = arquivo.getMov();
        ttotalO = tfim - tini;
        tseg = (double) ttotalO / 1000.0; //converter os millisegundos para segundos
    }

    private void armazenarOrdenado() {
        compOrd = compO;
        movOrd = movO;
        tempoOrd = (long)tseg;
    }

    private void armazenarReverso() {
        compRev = compO;
        movRev = movO;
        tempoRev = (long)tseg;
    }

    private void armazenarAleatorio() {
        compRand = compO;
        movRand = movO;
        tempoRand = (long)tseg;
    }

    private void exibirInfo(Arquivo arquivo) throws IOException {
        arquivo.exibirArquivo();
        System.out.println();
        System.out.println("Segundos: " + CoresNoConsole.ROXO + tseg + CoresNoConsole.RESET);
        System.out.println("Comp.: "+ CoresNoConsole.ROXO + compO + CoresNoConsole.RESET);
        System.out.println("Mov: " + CoresNoConsole.ROXO + movO + CoresNoConsole.RESET);
    }

    private void excluirArquivos() {
        File diretorio = new File("arquivos"); // Diretório onde estão os arquivos
        File[] arquivos = diretorio.listFiles();

        if (arquivos != null) {
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && !arquivo.getName().equals("Aleatorio")) {
                    arquivo.delete(); // Exclui o arquivo se não for "Aleatorio"
                }
            }
        }
    }

    private void geraArquivos() throws IOException {
        arqOrd.truncate(0);
        arqOrd.geraArquivoOrdenado(N);
        arqRev.truncate(0);
        arqRev.geraArquivoReverso(N);
        arqCopRand.copiaArquivo(arqRand.getArquivo());
    }

    private void geraArquivoAleatorio() throws IOException {
        arqRand.truncate(0);
        arqRand.geraArquivoRandomico(N);
    }

    public void geraTabela() throws IOException {
        this.geraArquivoAleatorio();
        Tabela tabela = new Tabela("Estatisticas");
        for(int i=1; i<=17; i++){
            compEqOrd=0;
            movEqOrd=0;
            compEqRev=0;
            movEqRev=0;
            compEqRand=0;
            movEqRand=0;
            // O switch eh para chamar metodos diferentes em cada iteracao
            switch(i){
                case 1:{
                    //insercao direta

                    System.out.println(CoresNoConsole.AZUL + "Inserção Direta" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.insercaoDireta();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp
                    compEqOrd = N-1;
                    movEqOrd = 3*(N-1);

                    this.comecar(arqRev);
                    arqRev.insercaoDireta();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();
                    compEqRev = (int)((Math.pow(N,2) + N - 4)/4);
                    movEqRev = (int)(Math.pow(N,2) + 3*N - 4)/2;

                    this.comecar(arqCopRand);
                    arqCopRand.insercaoDireta();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();
                    compEqRand = (int)(Math.pow(N,2) + N - 2)/4;
                    movEqRand = (int)(Math.pow(N,2) + 9*N - 10)/4;

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Insercao Direta",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*insercao Direta*/
                case 2:{
                    //insercao binaria

                    System.out.println(CoresNoConsole.AZUL + "Inserção Binária" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.insercaoBinaria();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp
                    compEqOrd = (int)(N * (Math.log(N) - Math.log(e) + 0.5));
                    movEqOrd = 3*(N-1);

                    this.comecar(arqRev);
                    arqRev.insercaoBinaria();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();
                    compEqRev = (int)(N * (Math.log(N) - Math.log(e) + 0.5));
                    movEqRev = (int)(Math.pow(N,2) + 9*N - 10)/4;

                    this.comecar(arqCopRand);
                    arqCopRand.insercaoBinaria();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();
                    compEqRand = (int)(N * (Math.log(N) - Math.log(e) + 0.5));
                    movEqRand = (int)(Math.pow(N,2) + 3*N - 4)/2;

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Insercao Binaria",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*insercao Binaria*/
                case 3:{
                    //selecao direta

                    System.out.println(CoresNoConsole.AZUL + "Seleção Direta" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.selecaoDireta();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp
                    compEqOrd = (int)(Math.pow(N,2) - N)/2;
                    movEqOrd = 3 * (N - 1);

                    this.comecar(arqRev);
                    arqRev.selecaoDireta();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();
                    compEqRev = compEqOrd;
                    movEqRev = (int)(Math.pow(N,2)/4 + 3 * (N - 1));

                    this.comecar(arqCopRand);
                    arqCopRand.selecaoDireta();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();
                    compEqRand = compEqOrd;
                    movEqRand = (int)(N * (Math.log(N) + g));

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Selecao Direta",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*selecao direta*/
                case 4:{
                    //bubble sort

                    System.out.println(CoresNoConsole.AZUL + "Bubble Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.bubbleSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp
                    compEqOrd = N-1;
                    movEqOrd = 0;

                    this.armazenarOrdenado();
                    this.comecar(arqRev);
                    arqRev.bubbleSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();
                    compEqRev = (int)((Math.pow(N,2) - N)/2);
                    movEqRev = (int)(3 * (Math.pow(N,2) - N)/2);

                    this.armazenarReverso();
                    this.comecar(arqCopRand);
                    arqCopRand.bubbleSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();
                    compEqRand = (int)((Math.pow(N,2) - N)/2);
                    movEqRand = (int)(3 * (Math.pow(N,2) - N)/4);

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Bubble Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*bubble Sort*/
                case 5:{
                    //shake sort

                    System.out.println(CoresNoConsole.AZUL + "Shake Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.shakesort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp
                    compEqOrd = N-1;
                    movEqOrd = 0;

                    this.comecar(arqRev);
                    arqRev.shakesort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();
                    compEqRev = (int)((Math.pow(N,2) - N)/2);
                    movEqRev = (int)(3 * (Math.pow(N,2) - N)/2);

                    this.comecar(arqCopRand);
                    arqCopRand.shakesort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();
                    compEqRand = (int)((Math.pow(N,2) - N)/2);
                    movEqRand = (int)(3 * (Math.pow(N,2) - N)/4);

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Shake Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*shake Sort*/
                case 6:{
                    //shell sort

                    System.out.println(CoresNoConsole.AZUL + "Shell Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.shellSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.shellSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.shellSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Shell Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*shell sort*/
                case 7:{
                    //heap sort

                    System.out.println(CoresNoConsole.AZUL + "Heap Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.heapSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.heapSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.heapSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Heap Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*heap sort*/
                case 8:{
                    //comb sort

                    System.out.println(CoresNoConsole.AZUL + "Comb Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.combSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.combSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.combSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Comp Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*comb sort*/
                case 9:{
                    //quick sort sem pivo

                    System.out.println(CoresNoConsole.AZUL + "Quick Sort SEM Pivo" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.quickSortSemPivo();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.quickSortSemPivo();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.quickSortSemPivo();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Quick SEM Pivo",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*quick sort sem pivo*/
                case 10:{
                    //quick sort com pivo

                    System.out.println(CoresNoConsole.AZUL + "Quick Sort COM Pivo" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.quickSortComPivo();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.quickSortComPivo();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.quickSortComPivo();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Quick COM Pivo",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*quick sort com pivo*/
                case 11:{
                    //count sort

                    System.out.println(CoresNoConsole.AZUL + "Count Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.countSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.countSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.countSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Count Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*count sort*/
                case 12:{
                    //radix sort

                    System.out.println(CoresNoConsole.AZUL + "Radix Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.radixSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.radixSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.radixSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Radix Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*radix sort*/
                case 13:{
                    //bucket sort

                    System.out.println(CoresNoConsole.AZUL + "Bucket Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.bucketSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.bucketSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.bucketSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Bucket Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*bucket sort*/
                case 14:{
                    //gnome sort

                    System.out.println(CoresNoConsole.AZUL + "Gnome Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.gnomeSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.gnomeSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.gnomeSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Gnome Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*gnome sort*/
                case 15:{
                    //merge sort primeira implementação

                    System.out.println(CoresNoConsole.AZUL + "Merge Sort PRIMEIRA Implementação" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.mergeSortPrimeiraImplement();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.mergeSortPrimeiraImplement();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.mergeSortPrimeiraImplement();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Merge MULTIPLOS 2",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*merge sort primeira implementação*/
                case 16:{
                    //merge sort segunda implementação

                    System.out.println(CoresNoConsole.AZUL + "Merge Sort SEGUNDA Implementação" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.mergeSortSegundaImplement();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.mergeSortSegundaImplement();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.mergeSortSegundaImplement();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Merge Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*merge sort segunda implementação*/
                case 17:{
                    //tim sort

                    System.out.println(CoresNoConsole.AZUL + "Tim Sort" +CoresNoConsole.RESET);
                    this.geraArquivos();

                    this.comecar(arqOrd);
                    arqOrd.timSort();
                    this.finaliza(arqOrd);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Ordenado: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqOrd);//fim
                    armazenarOrdenado(); //armazenar os valores das mov e comp

                    this.comecar(arqRev);
                    arqRev.timSort();
                    this.finaliza(arqRev);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Reverso: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqRev);//fim
                    armazenarReverso();

                    this.comecar(arqCopRand);
                    arqCopRand.timSort();
                    this.finaliza(arqCopRand);
                    System.out.print(CoresNoConsole.AMARELO+"Arquivo Aleatorio: "+CoresNoConsole.RESET);
                    this.exibirInfo(arqCopRand);//fim
                    armazenarAleatorio();

                    //gravar a linha na tabela
                    tabela.gravaLinha(
                            "Tim Sort",
                            compOrd, compEqOrd, movOrd, movEqOrd, tempoOrd,
                            compRev, compEqRev, movRev, movEqRev, tempoRev,
                            compRand, compEqRand, movRand, movEqRand, tempoRand
                    );

                    excluirArquivos();
                    break;
                } /*tim sort*/
            }
        }
        tabela.fechar();

//        //grava na tabela informacoes os dados extraídos das execucoes do metodo
//        //Insercao Direta
//        gravaLinhaTabela(compO,
//                calculaCompInsDir(filesize()),
//                movO,
//                calculaMovInsDir(filesize()),
//                ttotalO //tempo execução no arquivo Ordenado já convertido para segundos
//                //...
//        );
//        //... fim Insercao Direta
//        //e assim continua para os outros métodos de ordenacao!!!
    }

    public static void rodar() throws IOException {
        RodarBinario p = new RodarBinario();
        p.geraTabela();
    }
}
