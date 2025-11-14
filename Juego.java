public class Juego {

    private Jugador j1;
    private Jugador j2;
    private Tablero tablero;
    private Dado dado;

    private boolean turnoJ1 = true;
    private int ultimoTiro = -1;

    public Juego() {
        this.j1 = new Jugador("Jugador 1");
        this.j2 = new Jugador("Jugador 2");
        this.tablero = new Tablero(20);
        this.dado = new Dado();
    }

    public Jugador getJ1() { return j1; }
    public Jugador getJ2() { return j2; }
    public Dado getDado() { return dado; }
    public Tablero getTablero() { return tablero; }
    public int getUltimoTiro() { return ultimoTiro; }

    public Jugador turnoActual() {
        return turnoJ1 ? j1 : j2;
    }

    public Jugador otroJugador() {
        return turnoJ1 ? j2 : j1;
    }

    public void cambiarTurno() {
        turnoJ1 = !turnoJ1;
    }

    // Juega un turno y devuelve el tiro
    public int jugarTurno() {
        Jugador actual = turnoActual();
        ultimoTiro = actual.jugarTurno(dado);
        return ultimoTiro;
    }
}