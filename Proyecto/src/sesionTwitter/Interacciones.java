/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesionTwitter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Edgar
 */
public class Interacciones {
     public static List<String> obtenerMencionesPropias(String usuario) {
        List<String> menciones = new ArrayList<>();
        String archivoTwits = "ruta/tu_directorio/twits.twc"; // Reemplaza con la ruta correcta

        try (BufferedReader br = new BufferedReader(new FileReader(archivoTwits))) {
            String linea;
            Pattern patronMencion = Pattern.compile("@(\\w+)");
            
            while ((linea = br.readLine()) != null) {
                Matcher matcher = patronMencion.matcher(linea);
                while (matcher.find()) {
                    String usuarioMencionado = matcher.group(1);
                    if (usuarioMencionado.equals(usuario)) {
                        // Evitar duplicaciones
                        if (!menciones.contains(usuarioMencionado)) {
                            menciones.add(usuarioMencionado);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return menciones;
    }

    public static void mostrarTweetsMencionados(String usuarioMencionado) {
      
    }
}
