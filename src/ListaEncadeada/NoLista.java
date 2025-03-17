package ListaEncadeada;

public class NoLista {
    private int info;
    private NoLista prox;
    private NoLista ant;

    public NoLista(int info, NoLista prox, NoLista ant) {
        this.info = info;
        this.prox = prox;
        this.ant = ant;
    }
    public NoLista(int info) {
        this(info, null, null);
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NoLista getProx() {
        return prox;
    }

    public void setProx(NoLista prox) {
        this.prox = prox;
    }

    public NoLista getAnt() {
        return ant;
    }

    public void setAnt(NoLista ant) {
        this.ant = ant;
    }
}
