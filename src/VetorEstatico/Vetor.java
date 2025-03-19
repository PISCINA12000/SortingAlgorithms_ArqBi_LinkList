package VetorEstatico;

import java.util.Random;

public class Vetor {
    private int TL = 0; //usarei o TL ao invés de vetor.length
    private int[] vetor; //variavel que podera ser declarada com o tamanho desejado
    private int TF; //sera a "CONSTANTE" que poderá ser definida pelo usuário

    public Vetor() {
        TF = 100;
        vetor = new int[TF + 1]; //declarar 1 a mais para permitir o uso de sentinela
    }

    public Vetor(int tamanho) {
        TF = tamanho;
        vetor = new int[TF + 1]; //declarar 1 a mais para permitir o uso de sentinela
    }

    //gets e sets
    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }

    public int[] getVetor() {
        return vetor;
    }

    public void setVetor(int[] vetor) {
        this.vetor = vetor;
    }

    public int getTF() {
        return TF;
    }

    public void setTF(int TF) {
        this.TF = TF;
    }

    public int getPosVet(int pos) {
        return vetor[pos];
    }

    public void setPosVet(int pos, int chave) {
        vetor[pos] = chave;
    }

    //metodo para alimentar vetor
    public void alimentar(int num) {
        for (int i = 0; i < num; i++) {
            this.vetor[i] = i + 1;
            this.TL++;
        }
    } /*introduzir valores para o vetor*/

    //metodo de exibicao
    public void exibirVetor() {
        for (int i = 0; i < TL; i++) {
            System.out.println(vetor[i]);
        }
    } /*exibicao do vetor*/

    //Metodo para adicionar no vetor
    public boolean pushVetor(int chave) {
        if (TL < TF) {
            vetor[TL] = chave;
            TL++;
            return true;
        } else
            return false;
    } /*adicionar um elemento ao final*/

    //Metodo de embaralhamento
    public void embaralhar() {
        Random rand = new Random();
        for (int i = this.TL - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Troca vetor[i] com vetor[j]
            int temp = this.vetor[i];
            this.vetor[i] = this.vetor[j];
            this.vetor[j] = temp;
        }
    } /*embaralhar o vetor*/

    //Metodo de remanejamento
    public void remanejar(int pos) {
        for (int i = pos; i < TL - 1; i++) {
            this.vetor[pos] = this.vetor[i + 1];
        }
        this.TL--;
    } /*remanejar vetor*/

    //Métodos de busca
    public int buscaExaustiva(int chave) {
        int pos = 0;
        while (pos < TL && chave != vetor[pos]) {
            pos++;
        }
        if (pos < TL) {
            return pos;
        }
        return -1;
    } /*exaustiva*/

    public int buscaExasSentinela(int chave) {
        int pos = 0;
        vetor[TL] = chave;
        while (chave != vetor[pos]) {
            pos++;
        }
        if (pos < TL) {
            return pos;
        }
        return -1;
    } /*exaustiva com sentinela*/

    public int buscaSequencial(int chave) {
        int pos = 0;
        while (pos < TL && chave > vetor[pos]) {
            pos++;
        }
        if (pos < TL && chave == vetor[pos]) {
            return pos;
        }
        return -1;
    } /*sequencial*/

    public int buscaSeqSentinela(int chave) {
        int pos = 0;
        vetor[TL] = chave;
        while (chave > vetor[pos]) {
            pos++;
        }
        if (pos < TL && chave == vetor[pos]) {
            return pos;
        }
        return -1;
    } /*sequencial com sentinela*/

    public int buscaBinaria(int chave) {
        int ini = 0, fim = this.TL - 1, meio = fim / 2;
        while (ini < fim && chave != this.vetor[meio]) {
            if (chave > this.vetor[meio]) {
                ini = meio + 1;
            } else if (chave < this.vetor[meio]) {
                fim = meio - 1;
            }
            meio = (ini + fim) / 2;
        }
        if (chave == this.vetor[meio]) {
            return meio;
        }
        return -1;
    } /*binaria*/

    public int buscaBinaria(int chave, int tl) {
        int ini = 0, fim = tl - 1, meio = fim / 2;
        while (ini < fim && chave != this.vetor[meio]) {
            if (chave > this.vetor[meio]) {
                ini = meio + 1;
            } else if (chave < this.vetor[meio]) {
                fim = meio - 1;
            }
            meio = (ini + fim) / 2;
        }
        if (chave > this.vetor[meio])
            return meio + 1;
        return meio;
    } /*binaria com indice*/

    //Métodos de ordenação
    public void insercaoDireta() {
        int aux, pos;
        for (int i = 1; i < TL; i++) {
            aux = vetor[i];
            pos = i;
            while (pos > 0 && vetor[pos - 1] > aux) {
                vetor[pos] = vetor[pos - 1];
                pos--;
            }
            vetor[pos] = aux;
        }
    } /*insercao direta*/

    public void bubbleSort() {
        int aux, TL2 = this.TL;
        boolean flag = true;
        while (TL2 > 0 && flag) {
            flag = false;
            for (int i = 0; i < TL - 1; i++)
                if (vetor[i] > vetor[i + 1]) {
                    aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                    flag = true;
                }
            TL2--;
        }
    } /*bubble sort*/

    public void shakesort() {
        int inicio = 0, fim = this.TL - 1, aux;
        boolean flag = true;
        while (inicio < fim && flag) {
            flag = false;
            for (int i = inicio; i < fim; i++) {
                if (vetor[i] > vetor[i + 1]) {
                    aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                    flag = true;
                }
            }
            fim--;
            if (flag) { //houve permutacoes
                flag = false;
                for (int i = fim; i > inicio; i--) {
                    if (vetor[i] < vetor[i - 1]) {
                        aux = vetor[i];
                        vetor[i] = vetor[i - 1];
                        vetor[i - 1] = aux;
                        flag = true;
                    }
                }
                inicio++;
            }
        }
    } /*shake sort*/

    public void selecaoDireta() {
        int posMenor, aux;
        for (int i = 0; i < TL - 1; i++) {
            posMenor = i;
            for (int j = i + 1; j < TL; j++) {
                if (this.vetor[j] < this.vetor[posMenor]) {
                    posMenor = j;
                }
            }
            aux = this.vetor[posMenor];
            this.vetor[posMenor] = this.vetor[i];
            this.vetor[i] = aux;
        }
    } /*selecao direta*/

    public void combSort() {
        int gap, ant, aux;
        gap = (int) (this.TL / 1.3);
        ant = gap;
        while (gap > 0) {
            for (int i = 0; i + gap < this.TL; i++) {
                if (this.vetor[i] > this.vetor[i + gap]) {
                    aux = this.vetor[i];
                    this.vetor[i] = this.vetor[i + gap];
                    this.vetor[i + gap] = aux;
                }
            }
            gap = (int) (ant / 1.3);
            ant = gap;
        }
    } /*comb sort*/

    public void shellSort() {
        int dist = 1, aux, pos;
        while (dist < this.TL)
            dist = dist * 2 + 1;
        dist = dist / 2;

        while (dist > 0) {
            for (int i = dist; i < this.TL; i++) {
                aux = this.vetor[i];
                pos = i;
                while (pos >= dist && aux < this.vetor[pos - dist]) {
                    this.vetor[pos] = vetor[pos - dist];
                    pos = pos - dist;
                }
                this.vetor[pos] = aux;
            }
            dist /= 2;
        }
    } /*shell sort*/

    public void insercaoBinaria() {
        int pos, aux;
        for (int i = 1; i < TL; i++) {
            aux = this.vetor[i];
            pos = buscaBinaria(aux, i);
            for (int j = i; j > pos; j--)
                this.vetor[j] = vetor[j - 1];
            this.vetor[pos] = aux;
        }
    } /*insercao binaria*/

    public void heapSort() {
        int TL2=this.TL, FE, FD, maiorF, aux;

        while(TL2>1){
            for(int pai=TL2/2-1; pai>=0; pai--){
                FE = pai*2+1;
                FD = FE+1;
                maiorF = FE;
                if(FD<TL2 && this.vetor[FD]>this.vetor[FE])
                    maiorF = FD;
                if(this.vetor[maiorF]>this.vetor[pai]){
                    aux = this.vetor[maiorF];
                    this.vetor[maiorF] = this.vetor[pai];
                    this.vetor[pai] = aux;
                }
            }
            aux = this.vetor[0];
            this.vetor[0] = this.vetor[TL2-1];
            this.vetor[TL2-1] = aux;
            TL2--;
        }
    } /*heap sort*/

    public void quickSortSemPivo(){
        quickSORTSemPivo(0, this.TL - 1);
    } /*quick sort sem pivo*/
    private void quickSORTSemPivo(int ini, int fim) {
        int i = ini, j = fim, aux;
        boolean flag = true;
        while (i < j) {
            if (flag)
                while (i < j && this.vetor[i] <= this.vetor[j])
                    i++;
            else
                while (i < j && this.vetor[i] <= this.vetor[j])
                    j--;
            aux = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = aux;
            flag = !flag;
        }
        if(ini < i-1)
            quickSORTSemPivo(ini, i-1);
        if(j+1 < fim)
            quickSORTSemPivo(j+1, fim);
    } /*quick sort de verdade sem pivo*/

    public void quickSortComPivo(){
        quickSORTComPivo(0, this.TL-1);
    } /*quick sort com pivo*/
    private void quickSORTComPivo(int ini, int fim) {
        if(ini<fim){
            int pos = particionaQuick(ini, fim);
            quickSORTComPivo(ini, pos-1);
            quickSORTComPivo(pos, fim);
        }
    } /*quick sort com pivo de verdade*/
    private int particionaQuick(int ini, int fim) {
        int pivo = this.vetor[fim], aux;
        while(ini<fim){
            while(ini<fim && this.vetor[ini]<=pivo)
                ini++;
            while(ini<fim && this.vetor[fim]>pivo)
                fim--;
            aux = this.vetor[ini];
            this.vetor[ini] = this.vetor[fim];
            this.vetor[fim] = aux;
        }
        return ini;
    } /*metodo para o quick com pivo*/

    public void quickComPivoProfessor(){
        quickCPIVO(0,this.TL-1);
    } /*quick com pivo do professor*/
    private void quickCPIVO(int ini, int fim) {
        int aux, pivo = this.vetor[(ini+fim)/2], i=ini, j=fim;

        while(i<j){
            while(this.vetor[i]<pivo)
                i++;
            while(this.vetor[j]>pivo)
                j--;
            if(i<=j){
                aux = this.vetor[i];
                this.vetor[i] = this.vetor[j];
                this.vetor[j] = aux;
                i++;
                j--;
            }
        }
        if(ini<j)
            quickCPIVO(ini, j);
        if(i<fim)
            quickCPIVO(i, fim);
    } /*quick sort com pivo feito pelo professor*/

    public void countSort(){
        int maior=0;
        for(int i=0; i<this.TL; i++){
            if(this.vetor[i]>maior)
                maior = this.vetor[i];
        }
        //achei o maior numero do meu vetor
        Vetor countVet = new Vetor(maior+1);
        countVet.setTL(maior+1); //deixa todas as posicoes com zeros
        for(int i=0; i<this.TL; i++){
            countVet.vetor[this.vetor[i]]++;
        }
        //contei as ocorrencias dos numeros de vetor em countVet
        for(int i=1; i<countVet.getTL(); i++)
            countVet.vetor[i] = countVet.vetor[i] + countVet.vetor[i-1];
        //realizei a soma cumulativa
        Vetor finalVet = new Vetor(this.TL);
        finalVet.setTL(this.TL);
        for(int i=this.TL-1; i>=0; i--){
            finalVet.vetor[countVet.vetor[vetor[i]] - 1] = vetor[i]; // Ajuste do índice
            countVet.vetor[vetor[i]]--;
        }
        //coloco os elementos ordenados no vetor original
        for(int i=0; i<this.TL; i++)
            vetor[i] = finalVet.vetor[i];
        //copiei para o vetor original
    } /*count sort*/

    public void radixSort() {
        int d=0;
        for(int i=0; i<this.TL; i++){
            //System.out.println("Qtde de Digitos do numero "+vetor[i]+": "+contaDigitos(vetor[i]));
            if(contaDigitos(this.vetor[i]) > d)
                d = contaDigitos(this.vetor[i]);
        } // tenho a quantidade maxima de digitos possiveis nos elementos do meu vetor

        for(int i=1; i<=d; i++)
            countSort(i); //chamar algum metodo que ira ordenar pelo digito
    } /*radix sort*/
    public void countSort(int d){
        int maior=0;
        for(int i=0; i<this.TL; i++){
            if(obterDigito(this.vetor[i], d) > maior)
                maior = this.vetor[i];
        }
        //achei o maior numero do meu vetor
        Vetor countVet = new Vetor(maior+1);
        countVet.setTL(maior+1); //deixa todas as posicoes com zeros
        for(int i=0; i<this.TL; i++){
            countVet.vetor[obterDigito(this.vetor[i], d)]++;
        }
        //contei as ocorrencias dos numeros de vetor em countVet
        for(int i=1; i<countVet.getTL(); i++)
            countVet.vetor[i] = countVet.vetor[i] + countVet.vetor[i-1];
        //realizei a soma cumulativa
        Vetor finalVet = new Vetor(this.TL);
        finalVet.setTL(this.TL);
        for(int i=this.TL-1; i>=0; i--){
            finalVet.vetor[countVet.vetor[obterDigito(vetor[i],d)] - 1] = vetor[i]; // Ajuste do índice
            countVet.vetor[obterDigito(vetor[i], d)]--;
        }
        //coloco os elementos ordenados no vetor original
        for(int i=0; i<this.TL; i++)
            vetor[i] = finalVet.vetor[i];
        //copiei para o vetor original
    } /*count sort para auxiliar o radix sort*/
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

    public void bucketSort(){
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, range, baldes=5, pos, k;
        Vetor[] buckets = new Vetor[baldes];
        //System.out.println(buckets.length); // verificar o tamanho do vetor de vetores

        for (int i = 0; i < this.TL; i++) {
            if(this.vetor[i]>max)
                max = this.vetor[i];
            if(this.vetor[i]<min)
                min = this.vetor[i];
        } //aqui tenho achado o maior e o menor valor do meu Array

        range = (max-min+1)/5;

        for (int i = 0; i < baldes; i++) {
            buckets[i] = new Vetor();
        } // criei os vetores e/ou buckets

        for (int i = 0; i < this.TL; i++) {
            if(range==0){
                buckets[0].pushVetor(this.vetor[i]);
            }
            else{
                pos = (this.vetor[i]-min)/range;
                if(pos==baldes)
                    pos--;
                buckets[pos].pushVetor(this.vetor[i]);
            }
        } // elementos do vetor original colocados nos respectivos baldes

        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i].getTL()!=0){
                buckets[i].insercaoDireta();
            }
        } // baldes ordenados

        // aqui preciso colocar os elementos ordenados dos baldes de volta no vetor
        k = 0;
        for (int i = 0; i < baldes; i++) {
            for (int j = 0; j < buckets[i].getTL(); j++) {
                this.vetor[k++] = buckets[i].getVetor()[j];
            }
        }
    } /*bucket sort*/

    public void gnomeSort(){
        int pos=1, aux;
        while(pos<this.TL){
            if(pos==0 || this.vetor[pos]>=this.vetor[pos-1])
                pos++;
            else{
                aux = this.vetor[pos];
                this.vetor[pos] = this.vetor[pos-1];
                this.vetor[pos-1] = aux;
                pos--;
            }
        }
    } /*gnome sort*/
}
