package utils;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class GifLoading {

	public static JLabel crearLabelGif(JLayeredPane pane) {
		
		int width = 1024;
		int height = 576;
        URL url = GifLoading.class.getResource("/images/loading.gif");
        
        if (url == null) {
            System.err.println("No se encontró el GIF");
            return new JLabel();
        }

        ImageIcon gifIcon = new ImageIcon(url);
        JLabel labelGif = new JLabel(gifIcon);

        // Centrado según tamaño del panel
        int x = (width - gifIcon.getIconWidth()) / 2;
        int y = (height - gifIcon.getIconHeight()) / 2;

        labelGif.setBounds(x, y, gifIcon.getIconWidth(), gifIcon.getIconHeight());
        labelGif.setVisible(false);

        // Agregar directamente al layeredPane 
        pane.add(labelGif, JLayeredPane.PALETTE_LAYER);

        return labelGif;
    }

}
