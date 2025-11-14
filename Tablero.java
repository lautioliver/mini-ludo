public class Tablero {
    private int longitud;

    public Tablero(int longitud) {
        this.longitud = longitud;
    }

    public boolean esMeta(int posicion) {
        return posicion >= longitud - 1;
    }
}