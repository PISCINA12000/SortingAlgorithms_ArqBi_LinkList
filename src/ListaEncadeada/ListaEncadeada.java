package ListaEncadeada;

import java.util.Random;

public class ListaEncadeada {
    private NoLista inicio;
    private NoLista fim;

    public ListaEncadeada() {
        inicializar();
    }

    public void inicializar() {
        this.inicio = null;
        this.fim = null;
    }

    public int primeiroElemento() {
        return this.inicio.getInfo();
    }

    public int ultimoElemento() {
        return this.fim.getInfo();
    }

    public void criarElementosRand(int num) {
        inicializar();
        Random gerador = new Random();
        this.inicio = this.fim = new NoLista(1 + gerador.nextInt(num));
        NoLista aux = this.inicio;
        for (int i = 1; i < num; i++) {
            aux.setProx(new NoLista(1 + gerador.nextInt(num)));
            aux.getProx().setAnt(aux);
            aux = aux.getProx();
            this.fim = aux;
        }
    }/*criar elementos "aleatoriamente"*/

    public void printarValores() {
        NoLista aux = this.inicio;
        try{
            while (aux != null) {
                System.out.println(aux.getInfo());
                aux = aux.getProx();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }/*printar os valores do vetor*/

    /*ORDENAÇÕES*/
    public void insercaoDireta() {
        int aux;
        NoLista ppos, i = this.inicio.getProx();
        while (i != null) {
            aux = i.getInfo();
            ppos = i;
            while (ppos.getAnt() != null && ppos.getAnt().getInfo() > aux) {
                ppos.setInfo(ppos.getAnt().getInfo());
                ppos = ppos.getAnt();
            }
            ppos.setInfo(aux);
            i = i.getProx();
        }
    }/*inserção direta*/

    public void bubbleSort() {
        int aux;
        NoLista TL2 = this.fim, i;
        boolean flag = true;
        while (TL2.getAnt() != null && flag) {
            flag = false;
            i = this.inicio;
            while (i != this.fim) {
                if (i.getInfo() > i.getProx().getInfo()) {
                    aux = i.getInfo();
                    i.setInfo(i.getProx().getInfo());
                    i.getProx().setInfo(aux);
                    flag = true;
                }
                i = i.getProx();
            }
            TL2 = TL2.getAnt();
        }
    }/*bubbleSort*/

    public void shakesort() {
        int aux;
        NoLista inicio = this.inicio, fim = this.fim, i;
        boolean flag = true;
        while (inicio != fim && flag) {
            flag = false;
            i = inicio;
            while (i != fim) {
                if (i.getInfo() > i.getProx().getInfo()) {
                    aux = i.getInfo();
                    i.setInfo(i.getProx().getInfo());
                    i.getProx().setInfo(aux);
                    flag = true;
                }
                i = i.getProx();
            }
            fim = fim.getAnt();
            if (flag) {
                flag = false;
                i = fim;
                while (i != inicio) {
                    if (i.getInfo() < i.getAnt().getInfo()) {
                        aux = i.getInfo();
                        i.setInfo(i.getAnt().getInfo());
                        i.getAnt().setInfo(aux);
                        flag = true;
                    }
                    i = i.getAnt();
                }
                inicio = inicio.getProx();
            }
        }
    }/*shakeSort*/

    public void selecaoDireta() {
        int aux;
        NoLista pposMenor, i, j;
        i = this.inicio;
        while (i != this.fim) {
            pposMenor = i;
            j = i.getProx();
            while (j != null) {
                if (j.getInfo() < pposMenor.getInfo()) {
                    pposMenor = j;
                }
                j = j.getProx();
            } //while j
            aux = pposMenor.getInfo();
            pposMenor.setInfo(i.getInfo());
            i.setInfo(aux);

            i = i.getProx();
        } //while i
    }/*selecaoDireta*/

    //funções auxiliares
    private NoLista andarFrente(NoLista inicio, int n) {
        for (int i = 0; i < n; i++)
            inicio = inicio.getProx();

        return inicio;
    }

    private NoLista andarAtras(NoLista inicio, int n) {
        for (int i = 0; i < n; i++)
            inicio = inicio.getAnt();

        return inicio;
    }

    private int contaLista() {
        NoLista aux = this.inicio;
        int cont = 0;
        while (aux != null) {
            aux = aux.getProx();
            cont++;
        }
        return cont;
    }

    private int buscaBinaria(int chave, int tl) {
        int ini = 0, fim = tl - 1, meio = fim / 2;
        while (ini < fim && chave != andarFrente(this.inicio, meio).getInfo()) {
            if (chave > andarFrente(this.inicio, meio).getInfo()) {
                ini = meio + 1;
            } else {
                if (chave < andarFrente(this.inicio, meio).getInfo()) {
                    fim = meio - 1;
                }
            }
            meio = (ini + fim) / 2;
        }
        if (chave > andarFrente(this.inicio, meio).getInfo())
            return meio + 1;
        return meio;
    }
    /*fim metodos extras*/

    public void combSort() {
        int gap, ant, aux, TL = this.contaLista();
        gap = (int) (TL / 1.3);
        ant = gap;

        while (gap > 0) {
            for (int i = 0; i + gap < TL; i++) {
                if (andarFrente(this.inicio, i).getInfo() > andarFrente(this.inicio, i + gap).getInfo()) {
                    aux = andarFrente(this.inicio, i).getInfo();
                    andarFrente(this.inicio, i).setInfo(andarFrente(this.inicio, i + gap).getInfo());
                    andarFrente(this.inicio, i + gap).setInfo(aux);
                }
            }
            gap = (int) (ant / 1.3);
            ant = gap;
        }
    }/*combSort*/

    public void shellSort() {
        int dist = 1, aux, pos, TL = contaLista();
        NoLista auxPos;
        while (dist < TL)
            dist = dist * 2 + 1;
        dist /= 2;

        while (dist > 0) {
            for (int i = dist; i < TL; i++) {
                aux = andarFrente(this.inicio, i).getInfo();
                pos = i;
                while (pos >= dist && aux < andarFrente(this.inicio, pos - dist).getInfo()) {
                    auxPos = andarFrente(this.inicio, pos);
                    auxPos.setInfo(andarAtras(auxPos, dist).getInfo());
                    pos -= dist;
                }
                andarFrente(this.inicio, pos).setInfo(aux);
            }
            dist /= 2;
        }
    }/*shellSort*/

    public void insercaoBinaria() {
        int pos, aux, TL = contaLista();
        NoLista auxJ;
        for (int i = 1; i < TL; i++) {
            aux = andarFrente(this.inicio, i).getInfo();
            pos = buscaBinaria(aux, i);
            for (int j = i; j > pos; j--) {
                auxJ = andarFrente(this.inicio, j);
                auxJ.setInfo(andarAtras(auxJ, 1).getInfo());
            }
            andarFrente(this.inicio, pos).setInfo(aux);
        }
    }/*insercaoBinaria*/

    public void heapSort() {
        int tl=contaLista(), fe, fd, fMaior, aux;

        while(tl>1){
            for(int pai=tl/2-1; pai>=0; pai--){
               fe = pai*2+1;
               fd = fe+1;
               fMaior = fe;
               if(fd<tl && andarFrente(this.inicio, fd).getInfo() > andarFrente(this.inicio, fe).getInfo())
                   fMaior = fd;
               if(andarFrente(this.inicio, fMaior).getInfo() > andarFrente(this.inicio, pai).getInfo()){
                   aux = andarFrente(this.inicio, fMaior).getInfo();
                   andarFrente(this.inicio, fMaior).setInfo(andarFrente(this.inicio, pai).getInfo());
                   andarFrente(this.inicio, pai).setInfo(aux);
               }
            }
            aux = this.inicio.getInfo();
            this.inicio.setInfo(andarFrente(this.inicio, tl-1).getInfo());
            andarFrente(this.inicio, tl-1).setInfo(aux);
            tl--;
        }
    }/*heapSort*/

    public void quickSortSemPivo() {
        quickSORTSemPivo(this.inicio, this.fim);
    }/*quick sort sem pivo*/
    public void quickSORTSemPivo(NoLista ini, NoLista fim) {
        if (ini != null && fim != null && ini != fim) {
            int aux;
            NoLista noI = ini, noJ = fim;
            boolean flag = true;
            while (noI != noJ) {
                if (flag)
                    while (noI != noJ && noI.getInfo() <= noJ.getInfo())
                        noI = noI.getProx();
                else
                    while (noI != noJ && noI.getInfo() <= noJ.getInfo())
                        noJ = noJ.getAnt();
                if (noI != noJ) {
                    aux = noI.getInfo();
                    noI.setInfo(noJ.getInfo());
                    noJ.setInfo(aux);
                    flag = !flag;
                }
            }
            if (noI!=ini && ini != noI.getAnt())
                quickSORTSemPivo(ini, noI.getAnt());
            if (noJ!=fim && noJ.getProx() != fim)
                quickSORTSemPivo(noJ.getProx(), fim);
        }
    }/*quick sort de verdade sem pivo*/

    public void quickSortComPivo(){

    } /*quick sort com pivo*/
    private void quickSORTComPivo(NoLista ini, NoLista fim) {

    } /*quick sort de verdade com pivo*/
    private int particionaQuick(){
        return 0; //temporario apenas para nao aparecer o erro
    } /*metodo para auxiliar o quick sort*/
}
