public class Ficha {
    private int posicion = 0;

    public int getPosicion() {
        return posicion;
    }

    public void mover(int pasos) {
        posicion += pasos;
    }

    public void reiniciar() {
        posicion = 0;
    }
}