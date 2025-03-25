package ArquivoBinario;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class Arquivo {
    private String nomeArquivo;
    private RandomAccessFile arquivo;
    private int comp, mov;

    // Construtores
    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
        this.arquivo = criaArquivo();
    }
    public Arquivo(){
        this("temp");
    }

    // Gets e Sets
    public RandomAccessFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(RandomAccessFile arquivo) {
        this.arquivo = arquivo;
    }

    public int getComp() {
        return this.comp;
    }

    private void setComp(int comp) {
        this.comp = comp;
    }

    public int getMov() {
        return this.mov;
    }

    private void setMov(int mov) {
        this.mov = mov;
    }

    public void initComp() {
        setComp(0);
    }

    public void initMov() {
        setMov(0);
    }

    // Demais metodos
    private RandomAccessFile criaArquivo() {
        String caminho = "arquivos\\" + nomeArquivo;
        RandomAccessFile arquivo;
        try {
            arquivo = new RandomAccessFile(caminho, "rw");
        } catch (IOException e) {
            return null;
        }
        return arquivo;
    }

    public void copiaArquivo(RandomAccessFile origem) throws IOException {
        Registro registro = new Registro();

        this.truncate(0);
        origem.seek(0);
        while(origem.getFilePointer() < origem.length()){
            registro.leDoArq(origem);
            registro.gravaNoArq(this.arquivo);
        }
    }

    public void truncate(long pos) throws IOException {
        arquivo.setLength(pos * Registro.length());
    }

    public boolean eof() throws IOException {
        if (arquivo.length() == arquivo.getFilePointer()) {
            //se entro aqui então o ponteiro já chegou ao final do arquivo
            return true;
        }
        return false;
    }

    public void seekArq(int pos) throws IOException {
        arquivo.seek(0); //levo o ponteiro para o inicio do arquivo
        arquivo.seek((long) pos * Registro.length());
    }

    public long filesize() throws IOException {
        return arquivo.length() / Registro.length();
    }

    public void insereNoFinal(Registro registro) throws IOException {
        this.seekArq((int) this.filesize());
        registro.gravaNoArq(this.arquivo);
    }

    // Métodos auxiliares que não mexem com arquivo binário
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

    private int obterDigito(int numero, int d) {
        int divisor = 1;

        // Calcula o divisor para alcançar a posição desejada
        for (int i = 1; i < d; i++) {
            divisor *= 10;
        }

        return (numero / divisor) % 10;
    }

    //Metodos de ordenacao
    public void insercaoDireta() throws IOException {
        int pos, tl = (int) this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();

        comp++;
        for(int i=1; i<tl; i++){
            comp++;
            seekArq(i); registroAux.leDoArq(arquivo);
            pos = i;

            seekArq(pos-1); registro.leDoArq(arquivo);
            comp++;
            while(pos>0 && registro.getNumero()>registroAux.getNumero()){
                comp++;
                seekArq(pos-1); registro.leDoArq(arquivo);
                seekArq(pos); registro.gravaNoArq(arquivo);
                mov++;
                pos--;
                comp++;
                if(pos>0){
                    seekArq(pos-1); registro.leDoArq(arquivo);
                }
            }
            seekArq(pos); registroAux.gravaNoArq(arquivo);
            mov++;
        }
    } /*insercao direta*/

    public int buscaBinaria(int chave) throws IOException {
        int ini=0, fim = (int) (this.filesize()-1), meio=fim/2;
        Registro registro = new Registro();

        this.seekArq(meio); registro.leDoArq(arquivo);
        while(ini<fim && chave != registro.getNumero()){
            if(chave > registro.getNumero()){
                ini = meio+1;
            }
            else{
                if(chave < registro.getNumero()){
                    fim = meio-1;
                }
            }
            meio = (ini+fim)/2;
            this.seekArq(meio); registro.leDoArq(arquivo);
        }
        if(chave == registro.getNumero()){
            return meio;
        }
        return -1;
    }/*busca binaria*/

    public int buscaBinaria(int chave, int tl) throws IOException {
        int ini = 0, fim = tl - 1, meio = fim / 2;
        Registro registro = new Registro();

        this.seekArq(meio);
        registro.leDoArq(arquivo);
        comp++;
        while (ini < fim && chave != registro.getNumero()) {
            comp++;
            comp++;
            if (chave > registro.getNumero()) {
                ini = meio + 1;
            } else {
                comp++;
                if (chave < registro.getNumero()) {
                    fim = meio - 1;
                }
            }
            meio = (ini + fim) / 2;
            this.seekArq(meio);
            registro.leDoArq(arquivo);
        }
        comp++;
        if (chave > registro.getNumero()) {

            return meio + 1;
        }
        return meio;
    }/*busca binaria*/

    public void insercaoBinaria() throws IOException {
        int pos, aux, tl = (int) this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();

        comp++;
        for(int i=1; i<tl; i++){
            comp++;
            this.seekArq(i); registro.leDoArq(this.arquivo);
            aux = registro.getNumero();
            pos = this.buscaBinaria(aux, i);
            comp++;
            for(int j=i; j>pos; j--){
                comp++;
                seekArq(j-1); registro.leDoArq(this.arquivo);
                seekArq(j); registro.gravaNoArq(this.arquivo);
                mov++;
            }
            registro.setNumero(aux);
            seekArq(pos); registro.gravaNoArq(arquivo);
            mov++;
        }
    }

    public void bubbleSort() throws IOException {
        int TL= (int) this.filesize();
        boolean flag = true;
        Registro registro = new Registro(), registroAux = new Registro();

        comp++;
        while(TL>0 && flag){
            comp++;
            flag = false;
            comp++;
            for (int i = 0; i < TL-1; i++) {
                comp++;
                this.seekArq(i); registro.leDoArq(this.arquivo);
                this.seekArq(i+1); registroAux.leDoArq(this.arquivo);
                comp++;
                if(registro.getNumero() > registroAux.getNumero()){
                    this.seekArq(i); registroAux.gravaNoArq(this.arquivo);
                    mov++;
                    this.seekArq(i+1); registro.gravaNoArq(this.arquivo);
                    mov++;
                    flag = true;
                }
            }
            TL--;
        }
    } /*bubble sort*/

    public void shakesort() throws IOException {
        int inicio=0, fim = (int) (this.filesize()-1);
        boolean flag = true;
        Registro registro = new Registro(), registroAux = new Registro();

        comp++;
        while(inicio<fim && flag){
            comp++;
            flag = false;
            comp++;
            for (int i=inicio; i<fim; i++) {
                comp++;
                this.seekArq(i); registro.leDoArq(this.arquivo);
                this.seekArq(i+1); registroAux.leDoArq(this.arquivo);
                comp++;
                if(registro.getNumero()>registroAux.getNumero()){
                    this.seekArq(i); registroAux.gravaNoArq(this.arquivo);
                    mov++;
                    this.seekArq(i+1); registro.gravaNoArq(this.arquivo);
                    mov++;
                    flag = true;
                }
            }
            fim--;
            comp++;
            if(flag){ //houve permutacoes
                flag = false;
                comp++;
                for (int i=fim; i>inicio; i--) {
                    comp++;
                    this.seekArq(i); registro.leDoArq(this.arquivo);
                    this.seekArq(i-1); registroAux.leDoArq(this.arquivo);
                    comp++;
                    if(registro.getNumero()<registroAux.getNumero()){
                        this.seekArq(i); registroAux.gravaNoArq(this.arquivo);
                        mov++;
                        this.seekArq(i-1); registro.gravaNoArq(this.arquivo);
                        mov++;
                        flag = true;
                    }
                }
                inicio++;
            }
        }
    } /*shake sort*/

    public void selecaoDireta() throws IOException {
        int posMenor, tl=(int)this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();

        comp++;
        for (int i=0; i<tl-1; i++) {
            comp++;
            posMenor = i;
            comp++;
            for (int j=i; j<tl; j++) {
                comp++;
                this.seekArq(j); registro.leDoArq(this.arquivo);
                this.seekArq(posMenor); registroAux.leDoArq(this.arquivo);
                comp++;
                if(registro.getNumero()<registroAux.getNumero()){
                    posMenor = j;
                }
            }
            this.seekArq(posMenor); registro.leDoArq(this.arquivo);
            this.seekArq(i); registroAux.leDoArq(this.arquivo);
            this.seekArq(posMenor); registroAux.gravaNoArq(this.arquivo);
            mov++;
            this.seekArq(i); registro.gravaNoArq(this.arquivo);
            mov++;
        }
    } /*selecao Direta*/

    public void shellSort() throws IOException {
        int dist=1, pos, tl=(int)this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();

        comp++;
        while(dist<tl) {
            comp++;
            dist = dist * 2 + 1;
        }
        dist /= 2;

        comp++;
        while(dist>0){
            comp++;
            comp++;
            for (int i=dist; i<tl; i++) {
                comp++;
                this.seekArq(i); registroAux.leDoArq(this.arquivo);
                pos = i;
                comp++;
                if(pos>=dist){
                    this.seekArq(pos-dist); registro.leDoArq(this.arquivo);
                }
                comp++;
                while(pos>=dist && registroAux.getNumero()<registro.getNumero()){
                    comp++;
                    this.seekArq(pos-dist); registro.leDoArq(this.arquivo);
                    this.seekArq(pos); registro.gravaNoArq(this.arquivo);
                    mov++;
                    pos -= dist;
                    comp++;
                    if(pos>=dist){
                        this.seekArq(pos-dist); registro.leDoArq(this.arquivo);
                    }
                }
                this.seekArq(pos); registroAux.gravaNoArq(this.arquivo);
                mov++;
            }
            dist /= 2;
        }
    } /*shell sort*/

    public void heapSort() throws IOException{
        int tl2=(int)this.filesize(), fe, fd, fMaior;
        Registro registro = new Registro(), registroAux = new Registro();

        comp++;
        while(tl2>1){
            comp++;
            comp++;
            for(int pai=tl2/2-1; pai>=0; pai--){
                comp++;
                fe = pai*2+1;
                fd = fe+1;
                fMaior = fe;

                comp++;
                if(fd<tl2){
                    this.seekArq(fd); registro.leDoArq(this.arquivo);
                    this.seekArq(fe); registroAux.leDoArq(this.arquivo);
                }
                comp++;
                if(fd<tl2 && registro.getNumero()>registroAux.getNumero()) {
                    fMaior = fd;
                }
                this.seekArq(fMaior); registro.leDoArq(this.arquivo);
                this.seekArq(pai); registroAux.leDoArq(this.arquivo);
                comp++;
                if(registro.getNumero()>registroAux.getNumero()){
                    this.seekArq(fMaior); registroAux.gravaNoArq(this.arquivo); // grava o pai no filho maior
                    mov++;
                    this.seekArq(pai); registro.gravaNoArq(this.arquivo);    // grava o filho maior no pai
                    mov++;
                }
            }
            this.seekArq(0); registro.leDoArq(this.arquivo);
            this.seekArq(tl2-1); registroAux.leDoArq(this.arquivo);
            this.seekArq(0); registroAux.gravaNoArq(this.arquivo);
            mov++;
            this.seekArq(tl2-1); registro.gravaNoArq(this.arquivo);
            mov++;
            tl2--;
        }
    } /*heap sort*/

    public void combSort() throws IOException{
        int gap, ant, tl=(int)this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();

        gap = (int) (tl/1.3);
        ant = gap;
        comp++;
        while(gap>0){
            comp++;
            comp++;
            for (int i=0; i+gap<tl; i++) {
                comp++;
                this.seekArq(i); registro.leDoArq(this.arquivo);
                this.seekArq(i+gap); registroAux.leDoArq(this.arquivo);
                comp++;
                if(registro.getNumero()>registroAux.getNumero()){
                    this.seekArq(i); registroAux.gravaNoArq(this.arquivo);
                    mov++;
                    this.seekArq(i+gap); registro.gravaNoArq(this.arquivo);
                    mov++;
                }
            }
            gap = (int) (ant/1.3);
            ant = gap;
        }
    } /*comb sort*/

    public void quickSortSemPivo() throws IOException{
        quickSORTSemPivo(0, (int)filesize()-1);
    } /*quick sort sem pivo*/
    private void quickSORTSemPivo(int ini, int fim) throws IOException{
        Registro regi = new Registro(), regj = new Registro();
        int i=ini, j=fim;
        boolean flag = true;

        comp++;
        while(i<j){
            comp++;
            this.seekArq(j); regj.leDoArq(this.arquivo);
            this.seekArq(i); regi.leDoArq(this.arquivo);
            comp++;
            if(flag) {
                comp++;
                while (i < j && regi.getNumero() <= regj.getNumero()) {
                    comp++;
                    regi.leDoArq(this.arquivo);
                    i++;
                }
            }
            else {
                comp++;
                while (i < j && regi.getNumero() <= regj.getNumero()) {
                    comp++;
                    j--;
                    this.seekArq(j);
                    regj.leDoArq(this.arquivo);
                }
            }
            comp++;
            if(i<j){
                this.seekArq(i); regj.gravaNoArq(this.arquivo);
                mov++;
                this.seekArq(j); regi.gravaNoArq(this.arquivo);
                mov++;
                flag = !flag;
            }
        }
        comp++;
        if(ini < i-1)
            quickSORTSemPivo(ini, i - 1);
        comp++;
        if(j+1 < fim)
            quickSORTSemPivo(j + 1, fim);
    } /*quick sort de verdade sem pivo*/

    public void quickSortComPivo() throws IOException{
        // um ou outro
        //quickSORTComPivo(0, (int)filesize()-1);
        quickSORTComPivoProfessor(0, (int)filesize()-1);
    } /*quick sort com pivo*/
    private void quickSORTComPivo(int ini, int fim) throws IOException{
        comp++;
        if(ini<fim){
            int pos = particionaQuick(ini, fim);
            quickSORTComPivo(ini, pos-1);
            quickSORTSemPivo(pos, fim);
        }
    } /*quick sort com pivo de verdade*/
    private int particionaQuick(int ini, int fim) throws IOException{
        Registro regi = new Registro(), regj = new Registro();
        int pivo;
        seekArq(fim); regi.leDoArq(this.arquivo);
        pivo = regi.getNumero();
        comp++;
        while(ini<fim){
            comp++;
            seekArq(ini); regi.leDoArq(this.arquivo);
            comp++;
            while(ini<fim && regi.getNumero()<=pivo){
                comp++;
                regi.leDoArq(this.arquivo);
                ini++;
            }
            seekArq(fim); regj.leDoArq(this.arquivo);
            comp++;
            while(ini<fim && regj.getNumero()>pivo){
                comp++;
                fim--;
                seekArq(fim);
                regj.leDoArq(this.arquivo);
            }
            seekArq(fim); regi.gravaNoArq(this.arquivo);
            mov++;
            seekArq(ini); regj.gravaNoArq(this.arquivo);
            mov++;
        }
        return ini;
    } /*metodo para auxiliar o quick com pivo*/
    public void quickSORTComPivoProfessor(int ini, int fim) throws IOException{
        int pivo, i=ini, j=fim;
        Registro regi = new Registro(), regj = new Registro();
        this.seekArq((ini+fim)/2); regi.leDoArq(this.arquivo);

        pivo = regi.getNumero();

        comp++;
        while(i<j){
            comp++;
            this.seekArq(i); regi.leDoArq(this.arquivo);
            comp++;
            while(regi.getNumero()<pivo){
                comp++;
                i++;
                this.seekArq(i); regi.leDoArq(this.arquivo);
            }
            this.seekArq(j); regj.leDoArq(this.arquivo);
            comp++;
            while(regj.getNumero()>pivo){
                comp++;
                j--;
                this.seekArq(j); regj.leDoArq(this.arquivo);
            }
            comp++;
            if(i<=j){
                this.seekArq(j); regi.gravaNoArq(this.arquivo);
                mov++;
                this.seekArq(i); regj.gravaNoArq(this.arquivo);
                mov++;
                i++;
                j--;
            }
        }
        comp++;
        if(ini<j)
            quickSORTComPivoProfessor(ini, j);
        comp++;
        if(i<fim)
            quickSORTComPivoProfessor(i, fim);
    } /*quick com pivo do professor*/

    public void countSort() throws IOException{
        int maior=Integer.MIN_VALUE, i;
        Arquivo arquivoCont = new Arquivo("Contador");
        Arquivo arquivoFinal = new Arquivo("Final");
        Registro regi = new Registro(), regj = new Registro();

        // achar o maior elemento do meu arquivo
        seekArq(0); regi.leDoArq(this.arquivo);
        comp++;
        while(!eof()){
            comp++;
            comp++;
            if(regi.getNumero()>maior){
                maior = regi.getNumero();
            }
            regi.leDoArq(this.arquivo);
        }
        comp++;
        if(regi.getNumero()>maior){
            maior = regi.getNumero();
        }

        // contar as ocorrências nas respectivas posicoes
        arquivoCont.truncate(0);
        arquivoCont.geraArquivoZerado(maior+1);
        this.seekArq(0); regi.leDoArq(this.arquivo);
        comp++;
        while(!eof()){
            comp++;
            arquivoCont.seekArq(regi.getNumero()); regj.leDoArq(arquivoCont.getArquivo());
            regj.setNumero(regj.getNumero()+1); // incremento
            arquivoCont.seekArq(regi.getNumero()); regj.gravaNoArq(arquivoCont.getArquivo());
            mov++;
            regi.leDoArq(this.arquivo);
        }
        arquivoCont.seekArq(regi.getNumero()); regj.leDoArq(arquivoCont.getArquivo());
        regj.setNumero(regj.getNumero()+1); // incremento
        arquivoCont.seekArq(regi.getNumero()); regj.gravaNoArq(arquivoCont.getArquivo());
        mov++;

        // realizar a soma cumulativa
        i=1;
        arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
        comp++;
        while(!arquivoCont.eof()){
            comp++;
            arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
            arquivoCont.seekArq(i-1); regj.leDoArq(arquivoCont.getArquivo());
            regi.setNumero(regi.getNumero()+regj.getNumero());
            arquivoCont.seekArq(i); regi.gravaNoArq(arquivoCont.getArquivo());
            mov++;
            i++;
            arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
        }
        arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
        arquivoCont.seekArq(i-1); regj.leDoArq(arquivoCont.getArquivo());
        regi.setNumero(regi.getNumero()+regj.getNumero());
        arquivoCont.seekArq(i); regi.gravaNoArq(arquivoCont.getArquivo());
        mov++;

        // colocar os elementos ordenados no arquivo final
        arquivoFinal.geraArquivoZerado((int)filesize());
        comp++;
        for(i=(int)filesize()-1; i>=0; i--){
            comp++;
            int pos;
            // vetor[i]
            seekArq(i); regi.leDoArq(this.arquivo);
            pos = regi.getNumero();
            // countVet.vetor[pos] - 1
            arquivoCont.seekArq(pos); regi.leDoArq(arquivoCont.getArquivo());
            pos = regi.getNumero()-1;
            // finalVet[pos] = vetor[i]
            seekArq(i); regj.leDoArq(this.arquivo);
            arquivoFinal.seekArq(pos); regj.gravaNoArq(arquivoFinal.getArquivo());
            mov++;
            // countVet.vetor[vetor[i]]--
            seekArq(i); regi.leDoArq(this.arquivo);
            pos = regi.getNumero();
            arquivoCont.seekArq(pos); regi.leDoArq(arquivoCont.getArquivo());
            regi.setNumero(regi.getNumero()-1);
            arquivoCont.seekArq(pos); regi.gravaNoArq(arquivoCont.getArquivo());
            mov++;
        }

        // copiar os dados ordenados para o arquivo original
        arquivoFinal.seekArq(0); regi.leDoArq(arquivoFinal.getArquivo());
        this.seekArq(0); regi.gravaNoArq(this.arquivo);
        mov++;
        comp++;
        while(!arquivoFinal.eof()){
            comp++;
            regi.leDoArq(arquivoFinal.getArquivo());
            regi.gravaNoArq(this.arquivo);
            mov++;
        }
    } /*count sort*/

    public void radixSort() throws IOException{
        int d=0, tl = (int)this.filesize();
        Registro registro = new Registro();

        //obter a quantidade máxima de digítos possíveis
        comp++;
        for(int i=0; i<tl; i++){
            comp++;
            this.seekArq(i); registro.leDoArq(this.arquivo);
            comp++;
            if(contaDigitos(registro.getNumero()) > d)
                d = contaDigitos(registro.getNumero());
        }
        comp++;
        for(int i=1; i<=d; i++) {
            comp++;
            countSort(i); //chamar o count pelos digítos
        }
    } /*radix sort*/
    private void countSort(int d) throws IOException {
        int maior=Integer.MIN_VALUE, i;
        Arquivo arquivoCont = new Arquivo("Contador");
        Arquivo arquivoFinal = new Arquivo("Final");
        Registro regi = new Registro(), regj = new Registro();

        // maior elemento do meu arquivo
        seekArq(0); regi.leDoArq(this.arquivo);
        comp++;
        while(!eof()){
            comp++;
            comp++;
            if(obterDigito(regi.getNumero(), d) > maior)
                maior = regi.getNumero();
            regi.leDoArq(this.arquivo);
        }
        comp++;
        if(obterDigito(regi.getNumero(), d) > maior)
            maior = regi.getNumero();

        // contar as ocorrências dos números
        arquivoCont.truncate(0);
        arquivoCont.geraArquivoZerado(maior+1);
        this.seekArq(0); regi.leDoArq(this.arquivo);
        comp++;
        while(!eof()){
            comp++;
            arquivoCont.seekArq(obterDigito(regi.getNumero(), d)); regj.leDoArq(arquivoCont.getArquivo());
            regj.setNumero(regj.getNumero()+1); // incremento
            arquivoCont.seekArq(obterDigito(regi.getNumero(), d)); regj.gravaNoArq(arquivoCont.getArquivo());
            mov++;
            regi.leDoArq(this.arquivo);
        }
        arquivoCont.seekArq(obterDigito(regi.getNumero(),d)); regj.leDoArq(arquivoCont.getArquivo());
        regj.setNumero(regj.getNumero()+1); // incremento
        arquivoCont.seekArq(obterDigito(regi.getNumero(),d)); regj.gravaNoArq(arquivoCont.getArquivo());
        mov++;

        // realizar a soma comulativa
        i=1;
        arquivoCont.seekArq(0);
        comp++;
        while(!arquivoCont.eof()){
            comp++;
            arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
            arquivoCont.seekArq(i-1); regj.leDoArq(arquivoCont.getArquivo());
            regi.setNumero(regi.getNumero()+regj.getNumero());
            arquivoCont.seekArq(i); regi.gravaNoArq(arquivoCont.getArquivo());
            mov++;
            i++;
            arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
        }
        arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
        arquivoCont.seekArq(i-1); regj.leDoArq(arquivoCont.getArquivo());
        regi.setNumero(regi.getNumero()+regj.getNumero());
        arquivoCont.seekArq(i); regi.gravaNoArq(arquivoCont.getArquivo());
        mov++;

        // colocar os elementos ordenados no arquivo final
        arquivoFinal.geraArquivoZerado((int)filesize());
        comp++;
        for(i=(int)filesize()-1; i>=0; i--){
            comp++;
            int pos;
            // vetor[i]
            seekArq(i); regi.leDoArq(this.arquivo);
            pos = obterDigito(regi.getNumero(),d);
            // countVet.vetor[pos] - 1
            arquivoCont.seekArq(pos); regi.leDoArq(arquivoCont.getArquivo());
            pos = regi.getNumero()-1;
            // finalVet[pos] = vetor[i]
            seekArq(i); regj.leDoArq(this.arquivo);
            arquivoFinal.seekArq(pos); regj.gravaNoArq(arquivoFinal.getArquivo());
            mov++;
            // countVet.vetor[vetor[i]]--
            seekArq(i); regi.leDoArq(this.arquivo);
            pos = obterDigito(regi.getNumero(),d);
            arquivoCont.seekArq(pos); regi.leDoArq(arquivoCont.getArquivo());
            regi.setNumero(regi.getNumero()-1);
            arquivoCont.seekArq(pos); regi.gravaNoArq(arquivoCont.getArquivo());
            mov++;
        }

        // copiei os dados ordenados para o arquivo original
        arquivoFinal.seekArq(0); regi.leDoArq(arquivoFinal.getArquivo());
        this.seekArq(0); regi.gravaNoArq(this.arquivo);
        mov++;
        comp++;
        while(!arquivoFinal.eof()){
            comp++;
            regi.leDoArq(arquivoFinal.getArquivo());
            regi.gravaNoArq(this.arquivo);
            mov++;
        }
    }

    public void bucketSort() throws IOException{
        int max=Integer.MIN_VALUE, min=Integer.MAX_VALUE, range, baldes=5, pos, k, tl=(int)filesize();
        Arquivo[] arquivos = new Arquivo[baldes];
        Registro registro = new Registro();

        //achei o maior e o menor elementos do meu arquivo
        comp++;
        for (int i = 0; i < tl; i++) {
            comp++;
            this.seekArq(i); registro.leDoArq(this.arquivo);
            comp++;
            if(registro.getNumero()>max)
                max = registro.getNumero();
            comp++;
            if(registro.getNumero()<min)
                min = registro.getNumero();
        }

        range = (max-min+1)/5;

        // criar os buckets
        comp++;
        for (int i = 0; i < baldes; i++) {
            comp++;
            String nome = "Arq" + i;
            arquivos[i] = new Arquivo(nome);
            arquivos[i].truncate(0);
        }

        //distribuir os elementos entre os baldes
        comp++;
        for (int i = 0; i < tl; i++) {
            comp++;
            comp++;
            if(range==0){
                //leio o elemento do arquivo original na pos i
                this.seekArq(i); registro.leDoArq(this.arquivo);
                //gravo ao final do bucket correto
                arquivos[0].seekArq((int)arquivos[0].filesize());
                registro.gravaNoArq(arquivos[0].getArquivo());
                mov++;
            }
            else{
                this.seekArq(i); registro.leDoArq(this.arquivo);
                pos = (registro.getNumero()-min)/range;
                comp++;
                if(pos==baldes)
                    pos--;
                arquivos[pos].seekArq((int)arquivos[pos].filesize());
                registro.gravaNoArq(arquivos[pos].getArquivo());
                mov++;
            }
        }

        // ordenar od baldes
        comp++;
        for (int i = 0; i < arquivos.length; i++) {
            comp++;
            comp++;
            if((int)arquivos[i].filesize()!=0)
                arquivos[i].insercaoDireta();
        }

        // aqui preciso colocar os elementos ordenados dos baldes de volta no vetor
        truncate(0);
        k = 0;
        comp++;
        for (int i = 0; i < baldes; i++) {
            comp++;
            comp++;
            for (int j = 0; j < (int)arquivos[i].filesize(); j++) {
                comp++;
                arquivos[i].seekArq(j); registro.leDoArq(arquivos[i].getArquivo());
                this.seekArq(k); registro.gravaNoArq(this.arquivo);
                mov++;
                k++;
                //this.vetor[k++] = buckets[i].getVetor()[j];
            }
        }
    } /*bucket sort*/

    public void gnomeSort() throws IOException{
        int pos=1;
        Registro regi = new Registro(), regj = new Registro();

        comp++;
        while(pos<(int)this.filesize()){
            comp++;
            comp++;
            if(pos==0)
                pos++;
            else{
                this.seekArq(pos); regi.leDoArq(this.arquivo);
                this.seekArq(pos-1); regj.leDoArq(this.arquivo);
                comp++;
                if(regi.getNumero()>=regj.getNumero())
                    pos++;
                else{
                    this.seekArq(pos); regj.gravaNoArq(this.arquivo);
                    mov++;
                    this.seekArq(pos-1); regi.gravaNoArq(this.arquivo);
                    mov++;
                    pos--;
                }
            }
        }
    } /*gnome sort*/

    public void mergeSortPrimeiraImplement() throws IOException{
        Arquivo arq1 = new Arquivo("Arq1"), arq2 = new Arquivo("Arq2");
        int seq = 1;

        comp++;
        while(seq<(int)this.filesize()){
            comp++;
            particao(arq1, arq2);
            fusaoPrimeiraImplement(arq1, arq2, seq);
            seq *= 2;
        }
    } /*merge sort multiplos de 2*/
    private void particao(Arquivo arq1, Arquivo arq2) throws IOException {
        Registro reg = new Registro();
        arq1.truncate(0);
        arq2.truncate(0);

        comp++;
        for(int i=0; i<(int)(this.filesize()+1)/2; i++){
            comp++;
            this.seekArq(i); reg.leDoArq(this.arquivo);
            arq1.insereNoFinal(reg);
            mov++;
        }
        comp++;
        for(int i=(int)(this.filesize()+1)/2; i<(int)this.filesize(); i++){
            comp++;
            this.seekArq(i); reg.leDoArq(this.arquivo);
            arq2.insereNoFinal(reg);
            mov++;
        }
    } /*particao para auxiliar merge de multiplos de 2*/
    private void fusaoPrimeiraImplement(Arquivo arq1, Arquivo arq2, int seq) throws IOException {
        int k=0, i=0, j=0, aux_seq=seq;
        Registro regi = new Registro(), regj = new Registro();

        comp++;
        while(k<(int)this.filesize()){
            comp++;
            comp++;
            while(i<seq && j<seq){
                comp++;
                arq1.seekArq(i); regi.leDoArq(arq1.getArquivo());
                arq2.seekArq(j); regj.leDoArq(arq2.getArquivo());
                comp++;
                if(regi.getNumero()<regj.getNumero()){
                    this.seekArq(k); regi.gravaNoArq(this.arquivo);
                    mov++;
                    k++;
                    i++;
                }
                else{
                    this.seekArq(k); regj.gravaNoArq(this.arquivo);
                    mov++;
                    k++;
                    j++;
                }
            }
            comp++;
            while(i<seq){
                comp++;
                arq1.seekArq(i); regi.leDoArq(arq1.getArquivo());
                this.seekArq(k); regi.gravaNoArq(this.arquivo);
                mov++;
                k++;
                i++;
            }
            comp++;
            while(j<seq){
                comp++;
                arq2.seekArq(j); regj.leDoArq(arq2.getArquivo());
                this.seekArq(k); regj.gravaNoArq(this.arquivo);
                mov++;
                k++;
                j++;
            }

            seq += aux_seq;
        }
    } /*fusao para auxiliar merge de multiplos de 2*/

    public void mergeSortSegundaImplement() throws IOException{
        Arquivo aux = new Arquivo();
        mergeSegundaImplement(0,(int)this.filesize()-1,aux);
    } /*merge sort qualquer multiplicidade*/
    private void mergeSegundaImplement(int esq, int dir, Arquivo aux) throws IOException{
        comp++;
        if(esq<dir){
            comp++;
            int meio = (esq+dir)/2;
            mergeSegundaImplement(esq, meio, aux);
            mergeSegundaImplement(meio+1, dir, aux);
            fusaoSegundaImplement(esq, meio, meio+1, dir, aux);
        }
    }
    private void fusaoSegundaImplement(int ini1, int fim1, int ini2, int fim2, Arquivo arqAux) throws IOException {
        int i = ini1, j = ini2;
        Registro regi = new Registro(), regj = new Registro();
        arqAux.truncate(0);

        comp++;
        while (i <= fim1 && j <= fim2) {
            comp++;
            this.seekArq(i);
            regi.leDoArq(this.arquivo);
            this.seekArq(j);
            regj.leDoArq(this.arquivo);
            comp++;
            if (regi.getNumero() < regj.getNumero()) {
                arqAux.insereNoFinal(regi);
                i++;
            } else {
                arqAux.insereNoFinal(regj);
                j++;
            }
            mov++;
        }
        comp++;
        while (j <= fim2) {
            comp++;
            this.seekArq(j);
            regj.leDoArq(this.arquivo);
            arqAux.insereNoFinal(regj);
            mov++;
            j++;
        }
        comp++;
        while (i <= fim1) {
            comp++;
            this.seekArq(i);
            regi.leDoArq(this.arquivo);
            arqAux.insereNoFinal(regi);
            mov++;
            i++;
        }

        arqAux.seekArq(0);
        comp++;
        for (int pos = ini1; pos <= fim2; pos++) {
            comp++;
            regi.leDoArq(arqAux.getArquivo());
            this.seekArq(pos);
            regi.gravaNoArq(this.arquivo);
            mov++;
        }
    }

    public void timSort(){

    } /*tim sort*/

    private int gerarAleatorio(int n) {
        Random sorteador = new Random();
        return (sorteador.nextInt(n)) + 1; //Valores de 1 até n
    }

    public void geraArquivoOrdenado(int n) {
        Registro registro = new Registro();
        for (int i = 0; i < n; i++) {
            registro.setNumero(i + 1);
            registro.gravaNoArq(this.arquivo);
        }
    }

    public void geraArquivoReverso(int n) {
        Registro registro = new Registro();
        for (int i = n; i > 0; i--) {
            registro.setNumero(i);
            registro.gravaNoArq(this.arquivo);
        }
    }

    public void geraArquivoRandomico(int n) {
        Registro registro = new Registro();
        for (int i = n; i > 0; i--) {
            registro.setNumero(gerarAleatorio(n));
            registro.gravaNoArq(this.arquivo);
        }
    }

    public void geraArquivoZerado(int n) {
        Registro registro = new Registro();
        for (int i = n; i > 0; i--) {
            registro.setNumero(0);
            registro.gravaNoArq(this.arquivo);
        }
    }

    //exibições
    public void exibirArquivo() throws IOException {
        Registro registro = new Registro();
        arquivo.seek(0);
        while (!eof()) {
            registro.leDoArq(arquivo);
            System.out.print(registro.getNumero()+" ");

            /*
            Para exibir nao somente o numero como tambem o conteudo do 'lixo'
            System.out.print(registro.getNumero());
            System.out.println("  " + Arrays.toString(registro.getLixo()));
            */
        }
        System.out.print("");
    }
}
