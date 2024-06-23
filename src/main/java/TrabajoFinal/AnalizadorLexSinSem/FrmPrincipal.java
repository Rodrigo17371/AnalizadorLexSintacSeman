package TrabajoFinal.AnalizadorLexSinSem;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java_cup.runtime.Symbol;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;

import javax.swing.JScrollPane;

public class FrmPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    protected JTextArea txtAnalizar;
    protected JTextArea txtResultado_Lexico;
    protected JTextArea txtResultado_Sintactico;
    
    private final static String nuevaLinea= "\n";
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmPrincipal frame = new FrmPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 449, 604);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 43, 414, 165);
        contentPane.add(scrollPane);
        
        txtAnalizar = new JTextArea();
        scrollPane.setViewportView(txtAnalizar);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 253, 414, 216);
        contentPane.add(scrollPane_1);
        
        txtResultado_Lexico = new JTextArea();
        scrollPane_1.setViewportView(txtResultado_Lexico);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File archivo = chooser.getSelectedFile();
                
                try {
                    String cadena = new String(Files.readAllBytes(archivo.toPath()));
                    txtAnalizar.setText(cadena);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnBuscar.setBounds(10, 11, 414, 23);
        contentPane.add(btnBuscar);
        
        JButton btnAnalizarLexico = new JButton("Analizar-Lexico");
        btnAnalizarLexico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    
                try {
                    int contador = 1;
                    String expresion = txtAnalizar.getText();
                    Lexer lexer = new Lexer(new StringReader(expresion));
                    String resultado = "Linea " + contador + "\t\tSIMBOLO" + nuevaLinea;
                    while (true) {
                        Tokens token = lexer.yylex();
                        if (token == null) {
                            txtResultado_Lexico.setText(resultado);
                            return;
                        }
                        switch (token) {
                            case Linea: 
                                contador++;
                                resultado = resultado + "Linea " + contador + nuevaLinea;
                                break;
                            case Var:
                                resultado = resultado + "<Var>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Const:
                                resultado = resultado + "<Const>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Let:
                                resultado = resultado + "<Let>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case If:
                                resultado = resultado + "<If>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Else:
                                resultado = resultado + "<Else>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case While:
                                resultado = resultado + "<While>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case For:
                                resultado = resultado + "<For>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Function:
                                resultado = resultado + "<Function>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Return:
                                resultado = resultado + "<Return>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Igual:
                                resultado = resultado + "<Igual>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Suma:
                                resultado = resultado + "<Suma>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Resta:
                                resultado = resultado + "<Resta>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Multiplicacion:
                                resultado = resultado + "<Multiplicacion>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Division:
                                resultado = resultado + "<Division>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Parentesis_a:
                                resultado = resultado + "<Parentesis de apertura>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Parentesis_c:
                                resultado = resultado + "<Parentesis de cierre>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Llave_a:
                                resultado = resultado + "<Llave de apertura>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Llave_c:
                                resultado = resultado + "<Llave de cierre>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Identificador:
                                resultado = resultado + "<Identificador>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case P_coma:
                                resultado = resultado + "<Punto y coma>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case Numero:
                                resultado = resultado + "<Numero>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            case ERROR:
                                resultado = resultado + "<Simbolo no definido>\t\t" + lexer.lexeme + nuevaLinea;
                                break;
                            default:
                                resultado = resultado + "<" + lexer.lexeme + ">" + nuevaLinea;
                                break;
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnAnalizarLexico.setBounds(10, 219, 414, 23);
        contentPane.add(btnAnalizarLexico);
        
        txtResultado_Sintactico = new JTextArea();
        txtResultado_Sintactico.setBounds(12, 512, 412, 35);
        contentPane.add(txtResultado_Sintactico);
        
        JButton btnAnalizarSintactico = new JButton("Analizar - Sintactico");
        btnAnalizarSintactico.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ST = txtAnalizar.getText();
                Sintax s = new Sintax(new TrabajoFinal.AnalizadorLexSinSem.LexerCup(new StringReader(ST)));
                
                try {
                    s.parse();
                    txtResultado_Sintactico.setText("Análisis realizado correctamente");
                    txtResultado_Sintactico.setForeground(new Color(25, 111, 61));
                } catch (Exception ex) {
                    Symbol sym = s.getS();
                    txtResultado_Sintactico.setText("Error de sintaxis. Línea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
                    txtResultado_Sintactico.setForeground(Color.red);
                }
            }
        });
        btnAnalizarSintactico.setBounds(10, 479, 414, 23);
        contentPane.add(btnAnalizarSintactico);        
    }
}