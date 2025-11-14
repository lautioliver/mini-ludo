public class Jugador {
    private String nombre;
    private Ficha ficha;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.ficha = new Ficha();
    }

    public String getNombre() {
        return nombre;
    }

    public Ficha getFicha() {
        return ficha;
    }

    // ✔ ESTE MÉTODO AHORA DEVUELVE EL TIRO
    public int jugarTurno(Dado dado) {
        int tiro = dado.tirar();
        ficha.mover(tiro);
        return tiro;  // ← IMPORTANTÍSIMO
    }
}