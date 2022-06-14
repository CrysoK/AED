package TP9;

public class NodoBT<T> {
    private T dato;
    private NodoBT<T> izq;
    private NodoBT<T> der;

    public NodoBT<T>(T dato, NodoBT<T> izq, NodoBT<T> der){
        this.izq = izq;
        this.der = der;
    }

    public NodoBT<T>(T dato){
        this(dato, null, null);
    }

    public T getDato(NodoBT<T>){
        return this.dato;
    }

    public NodoBT<T> getIzq(NodoBT<T>){
        return this.izq;
    }

    public NodoBT<T> getDer(NodoBT<T>){
        return this.der;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setIzq(NodoBT<T> izq){
        this.izq = izq;
    }

    public void setDer(NodoBT<T> der){
        this.der = der;
    }

    public String toString(){
        return this.dato.toString();
    }
}
