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

    //Metodos de ordenacao
    public void insercaoDireta() throws IOException {
        int pos, tl = (int) this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();
        for(int i=1; i<tl; i++){
            seekArq(i); registroAux.leDoArq(arquivo);
            pos = i;

            seekArq(pos-1); registro.leDoArq(arquivo);
            while(pos>0 && registro.getNumero()>registroAux.getNumero()){
                seekArq(pos-1); registro.leDoArq(arquivo);
                seekArq(pos); registro.gravaNoArq(arquivo);
                pos--;
                if(pos>0){
                    seekArq(pos-1); registro.leDoArq(arquivo);
                }
            }
            seekArq(pos); registroAux.gravaNoArq(arquivo);
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
        int ini=0, fim = tl-1, meio=fim/2;
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
        if(chave > registro.getNumero()){
            return meio+1;
        }
        return meio;
    }/*busca binaria*/

    public void insercaoBinaria() throws IOException {
        int pos, aux, tl = (int) this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();

        for(int i=1; i<tl; i++){
            this.seekArq(i); registro.leDoArq(this.arquivo);
            aux = registro.getNumero();
            pos = this.buscaBinaria(aux, i);
            for(int j=i; j>pos; j--){
                seekArq(j-1); registro.leDoArq(this.arquivo);
                seekArq(j); registro.gravaNoArq(this.arquivo);
            }
            registro.setNumero(aux);
            seekArq(pos); registro.gravaNoArq(arquivo);
        }
    }

    public void bubbleSort() throws IOException {
        int TL= (int) this.filesize();
        boolean flag = true;
        Registro registro = new Registro(), registroAux = new Registro();
        while(TL>0 && flag){
            flag = false;
            for (int i = 0; i < TL-1; i++) {
                this.seekArq(i); registro.leDoArq(this.arquivo);
                this.seekArq(i+1); registroAux.leDoArq(this.arquivo);
                if(registro.getNumero() > registroAux.getNumero()){
                    this.seekArq(i); registroAux.gravaNoArq(this.arquivo);
                    this.seekArq(i+1); registro.gravaNoArq(this.arquivo);
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

        while(inicio<fim && flag){
            flag = false;
            for (int i=inicio; i<fim; i++) {
                this.seekArq(i); registro.leDoArq(this.arquivo);
                this.seekArq(i+1); registroAux.leDoArq(this.arquivo);
                if(registro.getNumero()>registroAux.getNumero()){
                    this.seekArq(i); registroAux.gravaNoArq(this.arquivo);
                    this.seekArq(i+1); registro.gravaNoArq(this.arquivo);
                    flag = true;
                }
            }
            fim--;
            if(flag){ //houve permutacoes
                flag = false;
                for (int i=fim; i>inicio; i--) {
                    this.seekArq(i); registro.leDoArq(this.arquivo);
                    this.seekArq(i-1); registroAux.leDoArq(this.arquivo);
                    if(registro.getNumero()<registroAux.getNumero()){
                        this.seekArq(i); registroAux.gravaNoArq(this.arquivo);
                        this.seekArq(i-1); registro.gravaNoArq(this.arquivo);
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

        for (int i=0; i<tl-1; i++) {
            posMenor = i;
            for (int j=i; j<tl; j++) {
                this.seekArq(j); registro.leDoArq(this.arquivo);
                this.seekArq(posMenor); registroAux.leDoArq(this.arquivo);
                if(registro.getNumero()<registroAux.getNumero()){
                    posMenor = j;
                }
            }
            this.seekArq(posMenor); registro.leDoArq(this.arquivo);
            this.seekArq(i); registroAux.leDoArq(this.arquivo);
            this.seekArq(posMenor); registroAux.gravaNoArq(this.arquivo);
            this.seekArq(i); registro.gravaNoArq(this.arquivo);
        }
    } /*selecao Direta*/

    public void shellSort() throws IOException {
        int dist=1, pos, tl=(int)this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();
        while(dist<tl)
            dist = dist * 2 + 1;
        dist /= 2;

        while(dist>0){
            for (int i=dist; i<tl; i++) {
                this.seekArq(i); registroAux.leDoArq(this.arquivo);
                pos = i;
                if(pos>=dist){
                    this.seekArq(pos-dist); registro.leDoArq(this.arquivo);
                }
                while(pos>=dist && registroAux.getNumero()<registro.getNumero()){
                    this.seekArq(pos-dist); registro.leDoArq(this.arquivo);
                    this.seekArq(pos); registro.gravaNoArq(this.arquivo);
                    pos -= dist;
                    if(pos>=dist){
                        this.seekArq(pos-dist); registro.leDoArq(this.arquivo);
                    }
                }
                this.seekArq(pos); registroAux.gravaNoArq(this.arquivo);
            }
            dist /= 2;
        }
    } /*shell sort*/

    public void heapSort() throws IOException{
        int tl2=(int)this.filesize(), fe, fd, fMaior;
        Registro registro = new Registro(), registroAux = new Registro();

        while(tl2>1){
            for(int pai=tl2/2-1; pai>=0; pai--){
                fe = pai*2+1;
                fd = fe+1;
                fMaior = fe;

                if(fd<tl2){
                    this.seekArq(fd); registro.leDoArq(this.arquivo);
                    this.seekArq(fe); registroAux.leDoArq(this.arquivo);
                }
                if(fd<tl2 && registro.getNumero()>registroAux.getNumero())
                    fMaior = fd;
                this.seekArq(fMaior); registro.leDoArq(this.arquivo);
                this.seekArq(pai); registroAux.leDoArq(this.arquivo);
                if(registro.getNumero()>registroAux.getNumero()){
                    this.seekArq(fMaior); registroAux.gravaNoArq(this.arquivo); // grava o pai no filho maior
                    this.seekArq(pai); registro.gravaNoArq(this.arquivo);    // grava o filho maior no pai
                }
            }
            this.seekArq(0); registro.leDoArq(this.arquivo);
            this.seekArq(tl2-1); registroAux.leDoArq(this.arquivo);
            this.seekArq(0); registroAux.gravaNoArq(this.arquivo);
            this.seekArq(tl2-1); registro.gravaNoArq(this.arquivo);
            tl2--;
        }
    } /*heap sort*/

    public void combSort() throws IOException{
        int gap, ant, tl=(int)this.filesize();
        Registro registro = new Registro(), registroAux = new Registro();

        gap = (int) (tl/1.3);
        ant = gap;
        while(gap>0){
            for (int i=0; i+gap<tl; i++) {
                this.seekArq(i); registro.leDoArq(this.arquivo);
                this.seekArq(i+gap); registroAux.leDoArq(this.arquivo);
                if(registro.getNumero()>registroAux.getNumero()){
                    this.seekArq(i); registroAux.gravaNoArq(this.arquivo);
                    this.seekArq(i+gap); registro.gravaNoArq(this.arquivo);
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

        while(i<j){
            this.seekArq(j); regj.leDoArq(this.arquivo);
            this.seekArq(i); regi.leDoArq(this.arquivo);
            if(flag)
                while(i<j && regi.getNumero()<=regj.getNumero()){
                    regi.leDoArq(this.arquivo);
                    i++;
                }
            else
                while(i<j && regi.getNumero()<=regj.getNumero()){
                    j--;
                    this.seekArq(j); regj.leDoArq(this.arquivo);
                }
            if(i<j){
                this.seekArq(i); regj.gravaNoArq(this.arquivo);
                this.seekArq(j); regi.gravaNoArq(this.arquivo);
                flag = !flag;
            }
        }
        if(ini < i-1)
            quickSORTSemPivo(ini, i-1);
        if(j+1 < fim)
            quickSORTSemPivo(j+1, fim);
    } /*quick sort de verdade sem pivo*/

    public void quickSortComPivo() throws IOException{
        // um ou outro
        //quickSORTComPivo(0, (int)filesize()-1);
        quickSORTComPivoProfessor(0, (int)filesize()-1);
    } /*quick sort com pivo*/
    private void quickSORTComPivo(int ini, int fim) throws IOException{
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
        while(ini<fim){
            seekArq(ini); regi.leDoArq(this.arquivo);
            while(ini<fim && regi.getNumero()<=pivo){
                regi.leDoArq(this.arquivo);
                ini++;
            }
            seekArq(fim); regj.leDoArq(this.arquivo);
            while(ini<fim && regj.getNumero()>pivo){
                fim--;
                seekArq(fim);
                regj.leDoArq(this.arquivo);
            }
            seekArq(fim); regi.gravaNoArq(this.arquivo);
            seekArq(ini); regj.gravaNoArq(this.arquivo);
        }
        return ini;
    } /*metodo para auxiliar o quick com pivo*/
    public void quickSORTComPivoProfessor(int ini, int fim) throws IOException{
        int pivo, i=ini, j=fim;
        Registro regi = new Registro(), regj = new Registro();
        this.seekArq((ini+fim)/2); regi.leDoArq(this.arquivo);

        pivo = regi.getNumero();

        while(i<j){
            this.seekArq(i); regi.leDoArq(this.arquivo);
            while(regi.getNumero()<pivo){
                i++;
                this.seekArq(i); regi.leDoArq(this.arquivo);
            }
            this.seekArq(j); regj.leDoArq(this.arquivo);
            while(regj.getNumero()>pivo){
                j--;
                this.seekArq(j); regj.leDoArq(this.arquivo);
            }
            if(i<=j){
                this.seekArq(j); regi.gravaNoArq(this.arquivo);
                this.seekArq(i); regj.gravaNoArq(this.arquivo);
                i++;
                j--;
            }
        }
        if(ini<j)
            quickSORTComPivoProfessor(ini, j);
        if(i<fim)
            quickSORTComPivoProfessor(i, fim);
    } /*quick com pivo do professor*/

    public void countSort() throws IOException{
        // NÃO TERMINADO AINDA!!!!!!!!!!!!
        int maior=Integer.MIN_VALUE, i;
        Arquivo arquivoCont = new Arquivo("Contador");
        Arquivo arquivoFinal = new Arquivo("Final");
        Registro regi = new Registro(), regj = new Registro();

        seekArq(0); regi.leDoArq(this.arquivo);
        while(!eof()){
            if(regi.getNumero()>maior){
                maior = regi.getNumero();
            }
            regi.leDoArq(this.arquivo);
        } // depois daqui tenho o maior numero do meu arquivo
        if(regi.getNumero()>maior){
            maior = regi.getNumero();
        }
//        System.out.println("Maior elemento: " + maior);

        arquivoCont.truncate(0);
        arquivoCont.geraArquivoZerado(maior+1);
        this.seekArq(0); regi.leDoArq(this.arquivo);
        while(!eof()){
            arquivoCont.seekArq(regi.getNumero()); regj.leDoArq(arquivoCont.getArquivo());
            regj.setNumero(regj.getNumero()+1); // incremento
            arquivoCont.seekArq(regi.getNumero()); regj.gravaNoArq(arquivoCont.getArquivo());
            regi.leDoArq(this.arquivo);
        }
        arquivoCont.seekArq(regi.getNumero()); regj.leDoArq(arquivoCont.getArquivo());
        regj.setNumero(regj.getNumero()+1); // incremento
        arquivoCont.seekArq(regi.getNumero()); regj.gravaNoArq(arquivoCont.getArquivo());
        // ocorrencias contadas nas respectivas posicoes
//        System.out.println("Exibição das ocorrencias por posições:");
//        arquivoCont.exibirArquivo();

        i=1;
        arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
        while(!arquivoCont.eof()){
            arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
            arquivoCont.seekArq(i-1); regj.leDoArq(arquivoCont.getArquivo());
            regi.setNumero(regi.getNumero()+regj.getNumero());
            arquivoCont.seekArq(i); regi.gravaNoArq(arquivoCont.getArquivo());
            i++;
            arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
        }
        arquivoCont.seekArq(i); regi.leDoArq(arquivoCont.getArquivo());
        arquivoCont.seekArq(i-1); regj.leDoArq(arquivoCont.getArquivo());
        regi.setNumero(regi.getNumero()+regj.getNumero());
        arquivoCont.seekArq(i); regi.gravaNoArq(arquivoCont.getArquivo());
        // realizei a soma cumulativa
//        System.out.println("Exibição da soma cumulativa:");
//        arquivoCont.exibirArquivo();

        arquivoFinal.geraArquivoZerado((int)filesize());
        for(i=(int)filesize()-1; i>=0; i--){
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
            // countVet.vetor[vetor[i]]--
            seekArq(i); regi.leDoArq(this.arquivo);
            pos = regi.getNumero();
            arquivoCont.seekArq(pos); regi.leDoArq(arquivoCont.getArquivo());
            regi.setNumero(regi.getNumero()-1);
            arquivoCont.seekArq(pos); regi.gravaNoArq(arquivoCont.getArquivo());
        } // coloquei os elementos ordenados no arquivo final

        arquivoFinal.seekArq(0); regi.leDoArq(arquivoFinal.getArquivo());
        this.seekArq(0); regi.gravaNoArq(this.arquivo);
        while(!arquivoFinal.eof()){
            regi.leDoArq(arquivoFinal.getArquivo());
            regi.gravaNoArq(this.arquivo);
        } // copiei os dados ordenados para o arquivo original
    }

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
            System.out.println(registro.getNumero());

            /*
            Para exibir nao somente o numero como tambem o conteudo do 'lixo'
            System.out.print(registro.getNumero());
            System.out.println("  " + Arrays.toString(registro.getLixo()));
            */
        }
    }
}
