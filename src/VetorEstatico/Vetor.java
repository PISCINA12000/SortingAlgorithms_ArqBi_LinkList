package VetorEstatico;

import java.util.Random;

public class Vetor {
    private int TL = 0; //usarei o TL ao invés de vetor.length
    private int[] vetor; //variavel que podera ser declarada com o tamanho desejado
    private int TF; //sera a "CONSTANTE" que poderá ser definida pelo usuário

    public Vetor(int tamanho) {
        TF = tamanho;
        vetor = new int[TF + 1]; //declarar 1 a mais para permitir o uso de sentinela
    }

    public Vetor() {
        TF = 100;
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

    // metodo para alimentar vetor
    public void alimentar(int num) {
        //tenho que ter certeza que o meu "num" é menor que o meu TF
        for (int i = 0; i < num; i++) {
            this.vetor[i] = i + 1;
            this.TL++;
        }
    } /*introduzir valores para o vetor*/

    // metodo de exibicao
    public void exibirVetor() {
        for (int i = 0; i < TL; i++) {
            System.out.println(vetor[i]);
        }
    } /*exibicao do vetor*/

    // Metodo para adicionar no vetor
    public boolean pushVetor(int chave) {
        if (TL < TF) {
            vetor[TL] = chave;
            TL++;
            return true;
        } else
            return false;
    } /*adicionar um elemento ao final*/

    // Metodo de embaralhamento
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

    // Metodo de remanejamento
    public void remanejar(int pos) {
        for (int i = pos; i < TL - 1; i++) {
            this.vetor[pos] = this.vetor[i + 1];
        }
        this.TL--;
    } /*remanejar vetor*/

    // Obter o dígito no index passado
    private int obterDigito(int numero, int d) {
        int divisor = 1;

        // Calcula o divisor para alcançar a posição desejada
        for (int i = 1; i < d; i++) {
            divisor *= 10;
        }

        return (numero / divisor) % 10;
    }

    // Conta a quantidade de dígitos de um número
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

    // MÉTODOS DE BUSCA -------------------------------------------------------
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

    // MÉTODOS DE ORDENAÇÃO ---------------------------------------------------
    public void insercaoDireta() {
        insercaoDireta(0,this.TL-1);
    } /*insercao direta*/

    private void insercaoDireta(int left, int right) {
        int aux, pos;
        for (int i = left+1; i <= right; i++) {
            aux = vetor[i];
            pos = i;
            while (pos > left && vetor[pos - 1] > aux) {
                vetor[pos] = vetor[pos - 1];
                pos--;
            }
            vetor[pos] = aux;
        }
    } /*inserção direta com parâmetros*/

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
        int gap = TL, aux;
        boolean flag = true;

        while (gap > 1 || flag) {
            gap = (int)(gap/1.3);
            if(gap<1)
                gap = 1;
            flag = false;
            for (int i = 0; i + gap < TL; i++) {
                if (vetor[i] > vetor[i + gap]) {
                    aux = vetor[i];
                    vetor[i] = vetor[i + gap];
                    vetor[i + gap] = aux;
                    flag = true;
                }
            }
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

        //achar o maior numero do meu vetor
        for(int i=0; i<this.TL; i++){
            if(this.vetor[i]>maior)
                maior = this.vetor[i];
        }

        //contar as ocorrencias dos numeros de vetor em countVet
        Vetor countVet = new Vetor(maior+1);
        countVet.setTL(maior+1); //deixa todas as posicoes com zeros
        for(int i=0; i<this.TL; i++){
            countVet.vetor[this.vetor[i]]++;
        }

        //realizar a soma cumulativa
        for(int i=1; i<countVet.getTL(); i++)
            countVet.vetor[i] = countVet.vetor[i] + countVet.vetor[i-1];

        //coloco os elementos ordenados no vetor final
        Vetor finalVet = new Vetor(this.TL);
        finalVet.setTL(this.TL);
        for(int i=this.TL-1; i>=0; i--){
            finalVet.vetor[countVet.vetor[vetor[i]] - 1] = vetor[i]; // Ajuste do índice
            countVet.vetor[vetor[i]]--;
        }

        //copiei para o vetor original
        for(int i=0; i<this.TL; i++)
            vetor[i] = finalVet.vetor[i];
    } /*count sort*/

    public void radixSort() {
        int d=0;
        for(int i=0; i<this.TL; i++){
            if(contaDigitos(this.vetor[i]) > d)
                d = contaDigitos(this.vetor[i]);
        } // tenho a quantidade maxima de digitos possiveis nos elementos do meu vetor

        for(int i=1; i<=d; i++)
            countSort(i); //chamar algum metodo que ira ordenar pelo digito
    } /*radix sort*/
    private void countSort(int d){
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

    public void mergeSort(){
        Vetor vet1 = new Vetor((this.TL+1)/2);
        Vetor vet2 = new Vetor(this.TL/2);
        int seq = 1;

        while(seq<this.TL){
            vet1.setTL(0);
            vet2.setTL(0);
            particao(vet1, vet2);

            fusao(vet1, vet2, seq);
            seq *= 2;
        }
    } /*merge sort primeira implementação*/
    private void particao(Vetor vet1, Vetor vet2){
        for (int i = 0; i < (this.TL+1)/2; i++) {
            vet1.pushVetor(this.vetor[i]);
        }
        for (int i = (this.TL+1)/2; i < this.TL; i++) {
            vet2.pushVetor(this.vetor[i]);
        }
    }
    private void fusao(Vetor vet1, Vetor vet2, int seq) {
        int k, i, j, cont1, cont2;

        k = i = j = 0;
        while (i < vet1.getTL() && j < vet2.getTL()) {
            // andar pelos vetores que precisam realizar a fusao
            cont1 = cont2 = 0;
            while (cont1 < seq && cont2 < seq && i < vet1.getTL() && j < vet2.getTL()) {
                if (vet1.getVetor()[i] < vet2.getVetor()[j]) {
                    this.vetor[k++] = vet1.getVetor()[i++];
                    cont1++;
                    //inseri no vetor original o menor elemento comparado
                } else {
                    this.vetor[k++] = vet2.getVetor()[j++];
                    cont2++;
                    //inseri no vetor original o menor elemento comparado
                }
            }
            while (cont1 < seq && i < vet1.getTL()) {
                this.vetor[k++] = vet1.getVetor()[i++];
                cont1++;
            }

            while (cont2 < seq && j < vet2.getTL()) {
                this.vetor[k++] = vet2.getVetor()[j++];
                cont2++;
            }
        }
        while (i < vet1.getTL()) {
            this.vetor[k++] = vet1.getVetor()[i++];
        }
        while (j < vet2.getTL()) {
            this.vetor[k++] = vet2.getVetor()[j++];
        }
    }

    public void mergeSortSegundaImplement(){
        int aux[] = new int[this.TL];
        mergeSegundaImplement(0, this.TL-1, aux);
    } /*merge sort segunda implementação*/
    private void mergeSegundaImplement(int esq, int dir, int[] aux) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSegundaImplement(esq, meio, aux);
            mergeSegundaImplement(meio + 1, dir, aux);
            fusaoSegundaImplement(esq, meio, meio + 1, dir, aux);
        }
    }
    private void fusaoSegundaImplement(int ini1, int fim1, int ini2, int fim2, int aux[]){
        int k = 0, i = ini1, j = ini2;

        while (i <= fim1 && j <= fim2) {
            if (this.vetor[i] < this.vetor[j])
                aux[k++] = this.vetor[i++];
            else
                aux[k++] = this.vetor[j++];
        }
        if (i == ini2) // não preciso do if
        {
            // sobraram elementos em 'j'
            while (j <= fim2)
                aux[k++] = this.vetor[j++];
        }
        else
        {
            // sobraram elementos em 'i'
            while (i <= fim1)
                aux[k++] = this.vetor[i++];
        }

        k = 0;
        for (int pos = ini1; pos <= fim2; pos++)
            this.vetor[pos] = aux[k++];
    }

    public void timSort() {
        int n = this.TL, min_merge = 32, end, size, mid, right;
        int minRun = calcMinRunTIM(n, min_merge);

        for (int start = 0; start < n; start += minRun) {
            if(start + minRun - 1 < n - 1) //pergunta se ainda possui minRun elementos para a ordenação
                end = start + minRun - 1; //se houver então vou ordenar o total de minRun elementos nesse momento
            else
                end = n - 1; //se não houver vou ordenar os elementos restantes no meu vetor
            //end = (start + minRun - 1 < n - 1) ? start + minRun - 1 : n - 1;
            insercaoDireta(start, end);
        }

        size = minRun;
        while (size < n) { //enquanto não fiz o merge no meu vetor inteiro
            // left anda em (2*size) porque cada iteração dois vetores de tamanho "size" são ordenados
            for (int left = 0; left < n; left += 2*size) {
                if(left + size - 1 < n - 1)
                    mid = left + size - 1; //se o meu meio não chega ao final do meu vetor, atribuo o valor "normal"
                else
                    mid = n - 1; //defino meu meio como "n-1" se o cálculo chega ou ultrapassa o final
                //mid = (left + size - 1 < n - 1) ? left + size - 1 : n - 1;

                if(left + 2 * size - 1 < n - 1) //se essa conta não chega a última posição do vetor original
                    right = left + 2*size - 1; //-1 ao final por se tratar de posição e não de tamanho
                else
                    right = n - 1; // se chegar e/ou passar, então eu vou ordenar até o final do vetor, evitando pegar posições que nunca podem ser acessadas
                //right = (left + 2 * size - 1 < n - 1) ? left + 2 * size - 1 : n - 1;

                /*chamo o metodo após achar o meio e o final correto*/
                if (mid < right) { //só chamo o merge se realmente possuo elementos para dois vetores, se não possuir, tenho certeza que essa última parte está ordenada pelo insertionSort
                    mergeTIM(left, mid, right);
                }
            }
            size *= 2;
        }
    } /*tim sort*/
    private void mergeTIM(int l, int m, int r) {
        int len1 = m - l + 1, len2 = r - m;
        int[] left = new int[len1];
        int[] right = new int[len2];

        /*aqui é o particionamento do vetor em dois*/
        for (int i = 0; i < len1; i++) {
            left[i] = this.vetor[l + i]; //começo a pegar os elementos a partir da posição "l"
        }
        for (int i = 0; i < len2; i++) {
            right[i] = this.vetor[m + 1 + i]; //começo a pegar os elementos a partir da posição "m+1"
        }

        /*colocar os elementos na posição correta do vetor original*/
        /*o 'K' começa de 'l' pois pode ser que ele esteja particionando apenas uma parte do vetor, e não ele inteiro*/
        int i = 0, j = 0, k = l;
        while (i < len1 && j < len2) { //enquanto o 'i' e o 'j' ainda não passaram seus limites no vetor
            if (left[i] <= right[j]) {
                this.vetor[k++] = left[i++];
            } else {
                this.vetor[k++] = right[j++];
            }
        }

        /*colocar no vetor original oq sobrou da primeira partição*/
        while (i < len1) {
            this.vetor[k++] = left[i++];
        }
        /*colocar no vetor original oq sobrou da segunda partição*/
        while (j < len2) {
            this.vetor[k++] = right[j++];
        }
    }
    private int calcMinRunTIM(int n, int min_merge) {
        int r = 0;
        while (n >= min_merge) {
            r = r + (n % 2);
            n = n/2;
        }
        return n + r;
    } /*cálculo da RUN mínima do TIM*/
}
