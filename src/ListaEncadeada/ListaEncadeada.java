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

    //métodos extras
    public int primeiroElemento() {
        return this.inicio.getInfo();
    }

    public int ultimoElemento() {
        return this.fim.getInfo();
    }

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

    private int contaIntervalo(NoLista inicio, NoLista fim) {
        int cont = 0;
        while(inicio!=fim){
            cont++;
            inicio = inicio.getProx();
        }
        return cont;
    }

    private int achaPos(NoLista no){
        int cont = 0;
        NoLista aux = this.inicio;

        while(aux!=no){
            cont++;
            aux = aux.getProx();
        }
        return cont;
    }

    private void criarListaZerada(int n) {
        this.fim = this.inicio = new NoLista(0);
        for (int i = 0; i < n - 1; i++) {
            this.fim.setProx(new NoLista(0));
            this.fim.getProx().setAnt(this.fim);
            this.fim = this.fim.getProx();
        }
    }

    private int obterDigito(int numero, int d) {
        int divisor = 1;

        // Calcula o divisor para alcançar a posição desejada
        for (int i = 1; i < d; i++) {
            divisor *= 10;
        }

        return (numero / divisor) % 10;
    }

    private int contaDigitos(int numero){
        int contador = 0;
        if(numero!=0){
            while(numero!=0){
                numero = numero/10;
                contador++;
            }
            return contador;
        }
        //se o numero recebido for 0, ele retorna que possui apenas 1 digito
        return 1;
    }

    private void addFinal(int valor) {
        NoLista novoNo = new NoLista(valor);

        // Caso a lista esteja vazia
        if (this.inicio == null) {
            this.inicio = this.fim = novoNo;
        } else {
            this.fim.setProx(novoNo); // O último nó atual aponta para o novo nó
            novoNo.setAnt(this.fim);  // O novo nó aponta de volta para o último nó atual
            this.fim = novoNo;        // Atualiza o fim da lista
        }
    }

    /*fim metodos extras*/

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
        quickSORTComPivo(this.inicio, this.fim);
    } /*quick sort com pivo*/
    private void quickSORTComPivo(NoLista ini, NoLista fim) {
        int aux, pivo, posi = achaPos(ini), posj = achaPos(fim);
        NoLista i=ini, j=fim;

        if(i!=null && j!=null && ini!=fim && fim.getProx()!=ini) {
            pivo = andarFrente(ini, contaIntervalo(ini, fim) / 2).getInfo();

            while(posi<posj){
                while(i.getInfo()<pivo){
                    posi++;
                    i = i.getProx();
                }
                while(j.getInfo()>pivo){
                    posj--;
                    j = j.getAnt();
                }
                if(posi<=posj){
                    aux = i.getInfo();
                    i.setInfo(j.getInfo());
                    j.setInfo(aux);
                    posi++;
                    posj--;
                    i = i.getProx();
                    j = j.getAnt();
                }
            }
            if(ini!=j || j.getProx()!=ini)
                quickSORTComPivo(ini, j);
            if(i!=fim || fim.getProx()!=i)
                quickSORTComPivo(i, fim);
        }
    } /*quick sort de verdade com pivo*/

    public void countSort(){
        int maior=Integer.MIN_VALUE;
        NoLista aux = this.inicio, auxFinal, auxCount;

        //achar o maior elemento da minha lista
        while(aux!=null){
            if(aux.getInfo()>maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }

        //criar uma outra lista e contar as ocorrencias dos numeros
        ListaEncadeada count = new ListaEncadeada();
        count.criarListaZerada(maior+1);
        aux = this.inicio;
        while(aux!=null){
            auxCount = count.andarFrente(count.inicio,aux.getInfo());
            auxCount.setInfo(auxCount.getInfo()+1);

            aux = aux.getProx();
        }

        //realizar a soma cumulativa
        auxCount = count.inicio.getProx();
        while(auxCount!=null){
            auxCount.setInfo(auxCount.getInfo()+auxCount.getAnt().getInfo());
            auxCount = auxCount.getProx();
        }

        //colocar os elementos na listaFinal
        ListaEncadeada finalList = new ListaEncadeada();
        finalList.criarListaZerada(contaLista());
        aux = this.fim;
        while(aux!=null){
            auxFinal = andarFrente(count.inicio, aux.getInfo());
            auxFinal = andarFrente(finalList.inicio, auxFinal.getInfo()-1);
            auxFinal.setInfo(aux.getInfo());

            auxCount = andarFrente(count.inicio, aux.getInfo());
            auxCount.setInfo(auxCount.getInfo()-1);

            aux = aux.getAnt();
        }

        //coloco os elementos ordenados no vetor original
        auxFinal = finalList.inicio;
        aux = this.inicio;
        while(auxFinal!=null){
            aux.setInfo(auxFinal.getInfo());
            auxFinal = auxFinal.getProx();
            aux = aux.getProx();
        }
    } /*countSort*/

    public void radixSort(){
        int d=0;
        NoLista aux = this.inicio;

        //achar a quantidade máxima de digitos
        while(aux!=null){
            if(contaDigitos(aux.getInfo())>d)
                d = contaDigitos(aux.getInfo());
            aux = aux.getProx();
        }

        for(int i=1; i<=d; i++)
            countSortRad(i);
    } /*radixSort*/
    private void countSortRad(int d){
        int maior=Integer.MIN_VALUE;
        NoLista aux = this.inicio, auxFinal, auxCount;

        //achar o maior elemento da minha lista
        while(aux!=null){
            if(obterDigito(aux.getInfo(), d) > maior)
                maior = aux.getInfo();
            aux = aux.getProx();
        }

        //criar uma outra lista e contar as ocorrencias dos numeros
        ListaEncadeada count = new ListaEncadeada();
        count.criarListaZerada(maior+1);
        aux = this.inicio;
        while(aux!=null){
            auxCount = count.andarFrente(count.inicio,obterDigito(aux.getInfo(),d));
            auxCount.setInfo(auxCount.getInfo()+1);

            aux = aux.getProx();
        }

        //realizar a soma cumulativa
        auxCount = count.inicio.getProx();
        while(auxCount!=null){
            auxCount.setInfo(auxCount.getInfo()+auxCount.getAnt().getInfo());
            auxCount = auxCount.getProx();
        }

        //colocar os elementos na listaFinal
        ListaEncadeada finalList = new ListaEncadeada();
        finalList.criarListaZerada(contaLista());
        aux = this.fim;
        while(aux!=null){
            auxFinal = andarFrente(count.inicio, obterDigito(aux.getInfo(),d));
            auxFinal = andarFrente(finalList.inicio, auxFinal.getInfo()-1);
            auxFinal.setInfo(aux.getInfo());

            auxCount = andarFrente(count.inicio, obterDigito(aux.getInfo(),d));
            auxCount.setInfo(auxCount.getInfo()-1);

            aux = aux.getAnt();
        }

        //coloco os elementos ordenados no vetor original
        auxFinal = finalList.inicio;
        aux = this.inicio;
        while(auxFinal!=null){
            aux.setInfo(auxFinal.getInfo());
            auxFinal = auxFinal.getProx();
            aux = aux.getProx();
        }
    } /*countSort auxiliar do RADIX*/

    public void bucketSort(){
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, range, baldes=5, pos;
        ListaEncadeada[] buckets = new ListaEncadeada[baldes];
        NoLista aux = this.inicio, auxBuckets;

        //identificar o maior e o menor elemento da minha lista
        while(aux!=null){
            if(aux.getInfo()>max)
                max = aux.getInfo();
            if(aux.getInfo()<min)
                min = aux.getInfo();
            aux = aux.getProx();
        }

        range = (max-min)/2;

        //criar os buckets
        for(int i=0; i<baldes; i++){
            buckets[i] = new ListaEncadeada();
        }

        //separar os elementos em seus respectivos baldes
        aux = this.inicio;
        while(aux!=null){
            if(range==0){
                buckets[0].addFinal(aux.getInfo());
            }
            else{
                pos = (aux.getInfo()-min)/range;
                if(pos==baldes)
                    pos--;
                buckets[pos].addFinal(aux.getInfo());
            }
            aux = aux.getProx();
        }

        //ordenar os buckets
        for(int i=0; i<buckets.length; i++){
            if(buckets[i].inicio!=null)
                buckets[i].insercaoDireta();
        }

        //realocar os elementos dos baldes para a lista original
        aux = this.inicio;
        for(int i=0; i< buckets.length; i++){
            auxBuckets = buckets[i].inicio;
            while(auxBuckets!=null){
                aux.setInfo(auxBuckets.getInfo());
                auxBuckets = auxBuckets.getProx();
                aux = aux.getProx();
            }
        }
    } /*bucketSort*/

    public void gnomeSort(){
        NoLista aux = this.inicio.getProx();
        int auxNum;

        while(aux!=null){
            if(aux==this.inicio || aux.getInfo()>=aux.getAnt().getInfo())
                aux = aux.getProx();
            else{
                auxNum = aux.getInfo();
                aux.setInfo(aux.getAnt().getInfo());
                aux.getAnt().setInfo(auxNum);
                aux = aux.getAnt();
            }
        }
    } /*gnomeSort*/

    public void mergeSortPri(){
        ListaEncadeada list1 = new ListaEncadeada();
        ListaEncadeada list2 = new ListaEncadeada();
        int seq = 1, tl = contaLista();

        while(seq<tl){
            particaoMergePri(list1, list2);
            fusaoMergePri(list1, list2, seq);
            seq *= 2;
        }
    } /*mergeSort primeira implementação*/
    private void particaoMergePri(ListaEncadeada list1, ListaEncadeada list2){
        int tl = contaLista();
        NoLista aux;

        aux = this.inicio;
        for(int i=0; i<(tl+1)/2; i++){
            list1.addFinal(aux.getInfo());
            aux = aux.getProx();
        }
        while(aux!=null){
            list2.addFinal(aux.getInfo());
            aux = aux.getProx();
        }
    }
    private void fusaoMergePri(ListaEncadeada list1, ListaEncadeada list2, int seq){
        NoLista auxi=list1.inicio, auxy=list2.inicio, auxk=this.inicio;
        int aux_seq = seq, i=0, j=0;

        while(auxi!=null && auxy!=null){
            while(i<seq && j<seq){
                if(auxi.getInfo() < auxy.getInfo()){
                    auxk.setInfo(auxi.getInfo());
                    auxk = auxk.getProx(); //PRÓXIMO DA LISTA PRINCIPAL
                    auxi = auxi.getProx(); //PRÓXIMO DA LISTA 1
                    i++;
                }
                else{
                    auxk.setInfo(auxy.getInfo());
                    auxk = auxk.getProx(); //PRÓXIMO DA LISTA PRINCIPAL
                    auxy = auxy.getProx(); //PRÓXIMO DA LISTA 2
                    j++;
                }
            }
            while(i<seq){
                auxk.setInfo(auxi.getInfo());
                auxk = auxk.getProx(); //PRÓXIMO DA LISTA PRINCIPAL
                auxi = auxi.getProx(); //PRÓXIMO DA LISTA 1
                i++;
            }
            while(j<seq){
                auxk.setInfo(auxy.getInfo());
                auxk = auxk.getProx(); //PRÓXIMO DA LISTA PRINCIPAL
                auxy = auxy.getProx(); //PRÓXIMO DA LISTA 2
                j++;
            }

            seq += aux_seq;
        }
    }
}
