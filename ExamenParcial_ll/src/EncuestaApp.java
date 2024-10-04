import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EncuestaApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JComboBox<String> encuestaComboBox;
    private JLabel preguntaLabel;
    private JRadioButton siRadioButton;
    private JRadioButton noRadioButton;
    private JRadioButton otraRespuestaRadioButton;
    private JTextField otraRespuestaTextField;
    private JButton siguienteButton;
    private JButton saltarButton;
    private ArrayList<String> respuestas;

    // 10 preguntas para cada encuesta
    private String[][] preguntas = {
            {"¿Practicas deportes?", "¿Ves deportes en la TV?", "¿Te interesa aprender un deporte nuevo?", "¿Sigues equipos deportivos?",
                    "¿Te gustaría participar en un evento deportivo?", "¿Practicas deportes en equipo?", "¿Haces ejercicio regularmente?",
                    "¿Te interesan los deportes extremos?", "¿Sigues competiciones deportivas internacionales?", "¿Consideras que el deporte es importante?"},

            {"¿Te gusta la comida rápida?", "¿Prefieres comida casera?", "¿Tienes una dieta balanceada?", "¿Te gusta cocinar?",
                    "¿Pruebas comidas de diferentes culturas?", "¿Te gustan los postres?", "¿Sigues recetas de cocina?",
                    "¿Te interesa la comida saludable?", "¿Eres vegetariano?", "¿Te gusta probar nuevos restaurantes?"},

            {"¿Tienes un hobby?", "¿Dedicas tiempo a tu hobby?", "¿Te gustaría aprender uno nuevo?", "¿Haces manualidades?",
                    "¿Te gusta leer en tu tiempo libre?", "¿Participas en actividades al aire libre?", "¿Coleccionas algún tipo de objeto?",
                    "¿Practicas algún hobby con otras personas?", "¿Te gusta la jardinería?", "¿Te consideras creativo?"},

            {"¿Te gusta tu trabajo?", "¿Te sientes productivo?", "¿Te gustaría cambiar de carrera?", "¿Te interesan más capacitaciones?",
                    "¿Te consideras buen líder?", "¿Te gusta trabajar en equipo?", "¿Te interesa el emprendimiento?",
                    "¿Te gustaría trabajar en el extranjero?", "¿Estás satisfecho con tu ambiente laboral?", "¿Te gustaría aprender nuevas habilidades profesionales?"}
    };

    private int preguntaActual = 0;
    private String encuestaSeleccionada;

    public EncuestaApp() {
        setTitle("Encuesta");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        respuestas = new ArrayList<>();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Panel de selección de encuesta
        JPanel seleccionPanel = new JPanel(new GridLayout(3, 1));
        JLabel seleccionLabel = new JLabel("Selecciona una encuesta:");
        encuestaComboBox = new JComboBox<>(new String[]{"Deporte", "Comida", "Hoobie", "Profesionalidad"});
        JButton comenzarButton = new JButton("Comenzar");

        seleccionPanel.add(seleccionLabel);
        seleccionPanel.add(encuestaComboBox);
        seleccionPanel.add(comenzarButton);

        // Panel de preguntas
        JPanel preguntaPanel = new JPanel(new GridLayout(6, 1));
        preguntaLabel = new JLabel("Pregunta");
        siRadioButton = new JRadioButton("Sí");
        noRadioButton = new JRadioButton("No");
        otraRespuestaRadioButton = new JRadioButton("Otra respuesta");
        otraRespuestaTextField = new JTextField();
        siguienteButton = new JButton("Siguiente");
        saltarButton = new JButton("Saltar");

        ButtonGroup respuestaGroup = new ButtonGroup();
        respuestaGroup.add(siRadioButton);
        respuestaGroup.add(noRadioButton);
        respuestaGroup.add(otraRespuestaRadioButton);

        preguntaPanel.add(preguntaLabel);
        preguntaPanel.add(siRadioButton);
        preguntaPanel.add(noRadioButton);
        preguntaPanel.add(otraRespuestaRadioButton);
        preguntaPanel.add(otraRespuestaTextField);
        preguntaPanel.add(siguienteButton);
        preguntaPanel.add(saltarButton);

        // Añadir los paneles al cardPanel
        cardPanel.add(seleccionPanel, "Seleccion");
        cardPanel.add(preguntaPanel, "Preguntas");

        add(cardPanel);

        // Acción al presionar "Comenzar"
        comenzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encuestaSeleccionada = (String) encuestaComboBox.getSelectedItem();
                preguntaActual = 0;
                mostrarPregunta();
                cardLayout.show(cardPanel, "Preguntas");
            }
        });

        // Acción al presionar "Siguiente"
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarRespuesta();
                preguntaActual++;
                if (preguntaActual < 10) { // Actualizado a 10 preguntas por encuesta
                    mostrarPregunta();
                } else {
                    mostrarMensajeFinal();
                }
            }
        });

        // Acción al presionar "Saltar"
        saltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                respuestas.add("Saltada");
                preguntaActual++;
                if (preguntaActual < 10) {
                    mostrarPregunta();
                } else {
                    mostrarMensajeFinal();
                }
            }
        });
    }

    private void mostrarPregunta() {
        String[] preguntasEncuesta;
        switch (encuestaSeleccionada) {
            case "Deporte":
                preguntasEncuesta = preguntas[0];
                break;
            case "Comida":
                preguntasEncuesta = preguntas[1];
                break;
            case "Hoobie":
                preguntasEncuesta = preguntas[2];
                break;
            case "Profesionalidad":
                preguntasEncuesta = preguntas[3];
                break;
            default:
                preguntasEncuesta = preguntas[0];
                break;
        }
        preguntaLabel.setText(preguntasEncuesta[preguntaActual]);
        siRadioButton.setSelected(false);
        noRadioButton.setSelected(false);
        otraRespuestaRadioButton.setSelected(false);
        otraRespuestaTextField.setText("");
    }

    private void registrarRespuesta() {
        if (siRadioButton.isSelected()) {
            respuestas.add("Sí");
        } else if (noRadioButton.isSelected()) {
            respuestas.add("No");
        } else if (otraRespuestaRadioButton.isSelected() && !otraRespuestaTextField.getText().isEmpty()) {
            respuestas.add(otraRespuestaTextField.getText());
        } else {
            respuestas.add("Sin respuesta");
        }
    }

    private void mostrarMensajeFinal() {
        JOptionPane.showMessageDialog(this, "¡Gracias por completar la encuesta!");
        for (int i = 0; i < respuestas.size(); i++) {
            System.out.println("Pregunta " + (i + 1) + ": " + respuestas.get(i));
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EncuestaApp().setVisible(true);
            }
        });
    }
}