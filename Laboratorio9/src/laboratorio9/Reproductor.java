/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laboratorio9;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Reproductor extends JFrame {

    private ListaEnlazada listaEnlazada = new ListaEnlazada();
    private String ikon = null;
    private String ruta = null;
    private JFrame lista_C;

    private String currentAlbum;
    private int currentDuration;
    private int playbackPosition = 0;
    private boolean isSeeking = false;
    private Player currentPlayer;
    private boolean isPlaying = false;
    private boolean isPaused = false;
    private File currentSongFile;

    public Reproductor() {
        setTitle("REPRODUCTOR DE MUSICA");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("REPRODUCTOR DE MUSICA");
        headerLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(100, 149, 237));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addButton = createButton("AGREGAR CANCIÓN");
        JButton selectButton = createButton("SELECCIONAR CANCIÓN");
        JButton exitButton = createButton("SALIR");

        addButton.setAlignmentX(CENTER_ALIGNMENT);
        selectButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        menuPanel.add(addButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(selectButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(exitButton);

       
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
        setVisible(true);

        
        addButton.addActionListener(e -> {
            JFrame addFrame = new JFrame("AÑADIR CANCIONES");
            addFrame.setSize(400, 600);
            addFrame.setLocationRelativeTo(null);
            addFrame.getContentPane().setBackground(new Color(176, 224, 230));
            addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel addPanel = new JPanel();
            addPanel.setBackground(new Color(176, 224, 230));
            addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
            addPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel addTitle = new JLabel("AÑADIR CANCIONES");
            addTitle.setFont(new Font("Arial", Font.BOLD, 18));
            addTitle.setForeground(Color.DARK_GRAY);
            addTitle.setAlignmentX(CENTER_ALIGNMENT);
            addPanel.add(addTitle);
            addPanel.add(Box.createRigidArea(new Dimension(0, 15)));

          
            JTextField tfNombre = new JTextField(20);
            addPanel.add(createLabeledField("Nombre Cancion:", tfNombre));

            JTextField tfArtista = new JTextField(20);
            addPanel.add(createLabeledField("Artista:", tfArtista));

            JTextField tfGenero = new JTextField(20);
            addPanel.add(createLabeledField("Género:", tfGenero));

            
            JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            iconPanel.setBackground(new Color(176, 224, 230));
            JLabel lblIcon = new JLabel("Imagen:");
            lblIcon.setFont(new Font("Arial", Font.PLAIN, 14));
            JButton btnIcon = new JButton("Elegir Icono");
            btnIcon.addActionListener(ex -> {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes (PNG, JPG)", "png", "jpg");
                fc.setFileFilter(filter);
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    ikon = file.getAbsolutePath();
                    JOptionPane.showMessageDialog(null, "Imagen Seleccionada: " + file.getName());
                }
            });
            iconPanel.add(lblIcon);
            iconPanel.add(btnIcon);
            addPanel.add(iconPanel);

           
            JTextField tfDuracion = new JTextField(20);
            addPanel.add(createLabeledField("Duración (seg):", tfDuracion));

            
            JPanel mp3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            mp3Panel.setBackground(new Color(176, 224, 230));
            JLabel lblMp3 = new JLabel("Archivo MP3:");
            lblMp3.setFont(new Font("Arial", Font.PLAIN, 14));
            JButton btnMp3 = new JButton("Elegir Archivo MP3");
            btnMp3.addActionListener(ex -> {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("MP3 (*.mp3)", "mp3");
                fc.setFileFilter(filter);
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    ruta = file.getAbsolutePath();
                    JOptionPane.showMessageDialog(null, "Mp3 Seleccionado: " + file.getName());
                }
            });
            mp3Panel.add(lblMp3);
            mp3Panel.add(btnMp3);
            addPanel.add(mp3Panel);

            
            JButton btnAddSong = new JButton("AGREGAR CANCIÓN");
            btnAddSong.setAlignmentX(CENTER_ALIGNMENT);
            btnAddSong.addActionListener(actionEvent -> {
                String nombre = tfNombre.getText().trim();
                String artista = tfArtista.getText().trim();
                String genero = tfGenero.getText().trim();
                String duracionStr = tfDuracion.getText().trim();
                int duracion = 0;
                try {
                    duracion = Integer.parseInt(duracionStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "La duración debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nombre.isEmpty() || artista.isEmpty() || genero.isEmpty() || duracionStr.isEmpty() || ikon == null || ruta == null) {
                    JOptionPane.showMessageDialog(null, "Debe completar todos los campos y escoger imagen y mp3.");
                    return;
                }
                listaEnlazada.agregarCancion(nombre, artista, ikon, duracion, ruta, genero);
                JOptionPane.showMessageDialog(null, "Canción agregada: " + nombre + " por " + artista);
                addFrame.dispose();
            });
            addPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            addPanel.add(btnAddSong);

            addFrame.add(addPanel);
            addFrame.setVisible(true);
        });

        
        selectButton.addActionListener(e -> {
            lista_C = new JFrame("LISTA DE CANCIONES");
            lista_C.setSize(400, 500);
            lista_C.setLocationRelativeTo(null);
            lista_C.getContentPane().setBackground(new Color(224, 255, 255));
            lista_C.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel musicPanel = createMusicPanel();
            lista_C.add(musicPanel);
            lista_C.setVisible(true);
        });

        exitButton.addActionListener(e -> System.exit(0));
    }

    
    private JPanel createLabeledField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(176, 224, 230));
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    
    public JPanel createMusicPanel() {
        JPanel musicPanel = new JPanel(new BorderLayout());
        musicPanel.setBackground(Color.WHITE);

      
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);
        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        searchPanel.add(new JLabel("Buscar: "));
        searchPanel.add(searchField);

        
        DefaultListModel<String> songListModel = new DefaultListModel<>();
        JList<String> songList = new JList<>(songListModel);
        songList.setBackground(new Color(240, 255, 240));
        songList.setFont(new Font("Arial", Font.PLAIN, 16));
        songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        
        Cabecera current = listaEnlazada.cabeza;
        while (current != null) {
            Musica musica = current.musica;
            String song = musica.getTitulo() + " - " + musica.getArtista();
            songListModel.addElement(song);
            current = current.siguiente;
        }

        songList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedSong = songList.getSelectedValue();
                    if (selectedSong != null) {
                        String[] parts = selectedSong.split(" - ");
                        if (parts.length < 2) return;
                        String songTitle = parts[0];
                        String artistName = parts[1];
                        Cabecera temp = listaEnlazada.cabeza;
                        while (temp != null) {
                            Musica musica = temp.musica;
                            if (musica.getTitulo().equals(songTitle) && musica.getArtista().equals(artistName)) {
                                File songFile = new File(musica.getRuta());
                                showNowPlayingPanel(songTitle, artistName, musica.getCoverPath(), songFile);
                                break;
                            }
                            temp = temp.siguiente;
                        }
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(songList);
        scrollPane.setPreferredSize(new Dimension(380, 300));

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        JButton btnRegresar = new JButton("REGRESAR");
        btnRegresar.addActionListener(e -> lista_C.dispose());
        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.addActionListener(e -> {
            int index = songList.getSelectedIndex();
            if (index == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione una canción para eliminar.");
            } else {
                String selectedSong = songListModel.get(index);
                String[] parts = selectedSong.split(" - ");
                if (parts.length < 2) return;
                String songTitle = parts[0];
                if (currentAlbum != null && currentAlbum.equals(songTitle)) {
                    stopMusic();
                }
                listaEnlazada.eliminarCancion(index);
                songListModel.remove(index);
                JOptionPane.showMessageDialog(null, "Canción eliminada.");
            }
        });
        bottomPanel.add(btnRegresar);
        bottomPanel.add(btnEliminar);

        
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { filter(); }
            public void removeUpdate(DocumentEvent e) { filter(); }
            public void changedUpdate(DocumentEvent e) { filter(); }

            private void filter() {
                String query = searchField.getText().toLowerCase();
                songListModel.clear();
                Cabecera current = listaEnlazada.cabeza;
                while (current != null) {
                    Musica musica = current.musica;
                    String song = musica.getTitulo() + " - " + musica.getArtista();
                    if (song.toLowerCase().contains(query)) {
                        songListModel.addElement(song);
                    }
                    current = current.siguiente;
                }
            }
        });

        musicPanel.add(searchPanel, BorderLayout.NORTH);
        musicPanel.add(scrollPane, BorderLayout.CENTER);
        musicPanel.add(bottomPanel, BorderLayout.SOUTH);
        return musicPanel;
    }

    
    private void showNowPlayingPanel(String songTitle, String artistName, String albumArtPath, File file) {
        currentAlbum = songTitle;
        currentDuration = (int) file.length();

        if (currentPlayer != null && isPlaying) {
            stopMusic();
        }

        JFrame playingFrame = new JFrame("Reproduciendo");
        playingFrame.setSize(400, 500);
        playingFrame.setLocationRelativeTo(null);
        playingFrame.setResizable(true);
        playingFrame.getContentPane().setBackground(Color.DARK_GRAY);
        playingFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        playingFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                stopMusic();
                currentSongFile = null;
                playingFrame.dispose();
            }
        });
        playingFrame.setAlwaysOnTop(true);

       
        JLabel albumLabel = new JLabel();
        albumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        if (albumArtPath != null && !albumArtPath.isEmpty()) {
            ImageIcon icon = new ImageIcon(new ImageIcon(albumArtPath).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
            albumLabel.setIcon(icon);
        } else {
            albumLabel.setText("Sin Imagen");
            albumLabel.setForeground(Color.WHITE);
        }

        
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.DARK_GRAY);
        JLabel songLabel = new JLabel(songTitle, SwingConstants.CENTER);
        songLabel.setFont(new Font("Arial", Font.BOLD, 24));
        songLabel.setForeground(Color.WHITE);
        JLabel artistLabel = new JLabel(artistName, SwingConstants.CENTER);
        artistLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        artistLabel.setForeground(Color.LIGHT_GRAY);
        infoPanel.add(songLabel, BorderLayout.CENTER);
        infoPanel.add(artistLabel, BorderLayout.SOUTH);

        
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        controlPanel.setBackground(Color.LIGHT_GRAY);
        JButton btnPlayPause = new JButton();
        ImageIcon playIcon = new ImageIcon("DefaultIMAGE/play.png");
        btnPlayPause.setIcon(playIcon);
        btnPlayPause.addActionListener(e -> {
            if (isPlaying && !isPaused) {
                pauseMusic();
                btnPlayPause.setIcon(playIcon);
            } else if (isPaused) {
                resumeMusic();
                btnPlayPause.setIcon(new ImageIcon("DefaultIMAGE/pause.png"));
            } else {
                playMusic(file);
                btnPlayPause.setIcon(new ImageIcon("DefaultIMAGE/pause.png"));
            }
        });
        JButton btnStop = new JButton("Stop");
        btnStop.addActionListener(e -> {
            stopMusic();
            playbackPosition = 0;
            btnPlayPause.setIcon(playIcon);
        });
        controlPanel.add(btnPlayPause);
        controlPanel.add(btnStop);

        
        playingFrame.setLayout(new BorderLayout());
        playingFrame.add(albumLabel, BorderLayout.NORTH);
        playingFrame.add(infoPanel, BorderLayout.CENTER);
        playingFrame.add(controlPanel, BorderLayout.SOUTH);
        playingFrame.setVisible(true);
    }

    private void playMusic(File file) {
        try {
            currentSongFile = file;
            currentPlayer = new Player(new FileInputStream(file));
            isPlaying = true;
            isPaused = false;
            new Thread(() -> {
                try {
                    currentPlayer.play();
                    isPlaying = false;
                    playbackPosition = 0;
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (JavaLayerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void resumeMusic() {
        if (currentSongFile != null && isPaused) {
            try {
                FileInputStream fis = new FileInputStream(currentSongFile);
                currentPlayer = new Player(fis);
                isSeeking = true;
                new Thread(() -> {
                    try {
                        fis.skip(playbackPosition);
                        currentPlayer.play();
                        isSeeking = false;
                        isPlaying = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
                isPlaying = true;
                isPaused = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void pauseMusic() { 
        if (currentPlayer != null && isPlaying) {
            try {
                playbackPosition += currentPlayer.getPosition();
                stopMusic();
                isPaused = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void stopMusic() {
        if (currentPlayer != null) {
            currentPlayer.close();  
            isPlaying = false;
            isPaused = false;
            playbackPosition = 0;
        }
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(250, 40));
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    public static void main(String[] args) {
        new Reproductor();
    }
}
