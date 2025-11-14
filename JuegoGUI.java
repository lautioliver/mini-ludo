import javax.swing.*;
import java.awt.*;

public class JuegoGUI extends JFrame {

    private final Juego juego;
    private final JLabel posJ1, posJ2, turnoLabel, tableroLabel;
    private final JLabel dadoIcono;    // Número grande del dado
    private final JLabel dadoTitulo;   // Texto "Tirada"
    private final JPanel panelBoton;
    private final JButton botonTirar;

    public JuegoGUI() {
        super("Mini Ludo");
        juego = new Juego();

        setLayout(new BorderLayout());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //LOGO.
        try {
            // Carga la imagen desde la ubicación de tu archivo.
            // Si la imagen está dentro del mismo directorio de recursos que la clase, usa getClass().getResource().
            // Asegúrate de reemplazar "ludo_icon.png" con el nombre real de tu archivo.
            Image icon = new ImageIcon(getClass().getResource("mini_ludo.jpg")).getImage();

            // 2. Establecer el Icono
            // Llama al método setIconImage() de la ventana (JFrame).
            setIconImage(icon);

        } catch (Exception e) {
            // Es buena práctica manejar el error si la imagen no se encuentra.
            System.err.println("Error al cargar el icono de la ventana: " + e.getMessage());
            // Si no se carga, el icono por defecto se mantiene.
        }

        // ---------------------------------------
        // PANEL SUPERIOR (Posiciones)
        // ---------------------------------------
        JPanel panelPos = new JPanel(new GridLayout(1, 2));
        posJ1 = new JLabel("Jugador 1: posición 0", SwingConstants.CENTER);
        posJ2 = new JLabel("Jugador 2: posición 0", SwingConstants.CENTER);
        posJ1.setFont(new Font("Arial", Font.BOLD, 16));
        posJ2.setFont(new Font("Arial", Font.BOLD, 16));
        panelPos.add(posJ1);
        panelPos.add(posJ2);

        // ---------------------------------------
        // PANEL TABLERO
        // ---------------------------------------
        JPanel panelTablero = new JPanel(new BorderLayout());
        tableroLabel = new JLabel(generarTablero(0, 0));
        tableroLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
        panelTablero.add(tableroLabel, BorderLayout.NORTH);

        // ---------------------------------------
        // PANEL INFORMACIÓN (turno)
        // ---------------------------------------
        JPanel panelInfo = new JPanel(new GridLayout(1, 1));
        turnoLabel = new JLabel("Turno: Jugador 1", SwingConstants.CENTER);
        turnoLabel.setFont(new Font("Arial", Font.BOLD, 18));

        panelInfo.add(turnoLabel);

        // ---------------------------------------
        // PANEL DEL DADO (Tirada + número grande)
        // ---------------------------------------
        JPanel panelDado = new JPanel();

        panelDado.setLayout(new BoxLayout(panelDado, BoxLayout.Y_AXIS));

        dadoTitulo = new JLabel("Tirada", SwingConstants.CENTER);
        dadoTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        dadoTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        dadoIcono = new JLabel("🎲", SwingConstants.CENTER);
        dadoIcono.setFont(new Font("Arial", Font.BOLD, 70));
        dadoIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelDado.add(dadoIcono);

        JPanel panelDadoCompleto = new JPanel(new BorderLayout());
        panelDadoCompleto.add(dadoTitulo, BorderLayout.NORTH);
        panelDadoCompleto.add(panelDado, BorderLayout.CENTER);

        // ---------------------------------------
        // BOTÓN "TIRAR DADO"
        // ---------------------------------------
        botonTirar = new JButton("Tirar Dado");
        // 1. Aumentamos el tamaño de la fuente para que el texto sea más grande
        botonTirar.setFont(new Font("Arial", Font.BOLD, 18)); // Tamaño de fuente aumentado a 18
        botonTirar.setFocusPainted(false);

        panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(botonTirar);

        panelTablero.add(panelBoton, BorderLayout.CENTER);

        //TAMAÑO PERSONALIZADO DEL BOTON.
        botonTirar.setPreferredSize(new Dimension(200, 60));
        botonTirar.addActionListener(e -> ejecutarTurno());

        // ---------------------------------------
        // AÑADIR TODO A LA VENTANA
        // ---------------------------------------
        add(panelPos, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        add(panelInfo, BorderLayout.SOUTH);

        add(panelDadoCompleto, BorderLayout.WEST);

        setVisible(true);
    }

    // ---------------------------------------
    // LÓGICA POR TURNO
    // ---------------------------------------
    private void ejecutarTurno() {
        Jugador actual = juego.turnoActual();
        Jugador otro = juego.otroJugador();

        int tiro = juego.jugarTurno();

        // actualizar número grande del dado
        dadoIcono.setText(String.valueOf(tiro));

        // choque
        if (actual.getFicha().getPosicion() == otro.getFicha().getPosicion()) {
            JOptionPane.showMessageDialog(this,
                    actual.getNombre() + " chocó a " + otro.getNombre());
            otro.getFicha().reiniciar();
        }

        // actualizar posiciones
        posJ1.setText("Jugador 1: posición " + juego.getJ1().getFicha().getPosicion());
        posJ2.setText("Jugador 2: posición " + juego.getJ2().getFicha().getPosicion());

        // actualizar tablero visual
        tableroLabel.setText(generarTablero(
                juego.getJ1().getFicha().getPosicion(),
                juego.getJ2().getFicha().getPosicion()
        ));

        // victoria
        if (juego.getTablero().esMeta(actual.getFicha().getPosicion())) {
            JOptionPane.showMessageDialog(this,
                    actual.getNombre() + " llegó a la meta y GANÓ!");
            botonTirar.setEnabled(false);
            return;
        }

        // cambiar turno
        juego.cambiarTurno();
        turnoLabel.setText("Turno: " + juego.turnoActual().getNombre());
    }

    // ---------------------------------------
    // GENERAR TABLERO (-, 1, 2, X)
    // ---------------------------------------
    private String generarTablero(int pos1, int pos2) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 20; i++) {
            if (i == pos1 && i == pos2) sb.append("X");
            else if (i == pos1) sb.append("1");
            else if (i == pos2) sb.append("2");
            else sb.append("-");
        }
        sb.append("]");
        return sb.toString();
    }

    // ---------------------------------------
    // MAIN (arranca el juego)
    // ---------------------------------------
    public static void main(String[] args) {
        new JuegoGUI();
    }
}
