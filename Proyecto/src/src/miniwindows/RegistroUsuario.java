package miniwindows;

import java.util.ArrayList;
import java.util.List;

public class RegistroUsuario {
    public static List<Usuario> usuarios = new ArrayList<>();
    
    public static class Usuario {
        private String usuario;
        private String contraseña;
        private int puntos;

        public Usuario(String usuario, String contraseña, int puntos) {
            this.usuario = usuario;
            this.contraseña = contraseña;
            this.puntos=puntos;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }
        
        public int getPuntos() {
            return puntos;
    }
    }
}
