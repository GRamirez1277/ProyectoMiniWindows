package miniwindows;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import miniwindows.RegistroUsuario.Usuario;


public class UsuariosRegistrados {
    private static final String ARCHIVO_USUARIOS = "usuarios.dat";
    private static List<RegistroUsuario.Usuario> usuariosRegistrados = new ArrayList<>();

    static {
        // Cargar usuarios desde el archivo al inicio
        cargarUsuariosDesdeArchivo();
    }
    private static void cargarUsuariosDesdeArchivo() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_USUARIOS))) {
        usuariosRegistrados = (List<RegistroUsuario.Usuario>) ois.readObject();
    } catch (FileNotFoundException e) {
        // Si el archivo no existe, no hay usuarios cargados aún.
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
    public static List<RegistroUsuario.Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    public static void agregarUsuario(RegistroUsuario.Usuario usuario) {
    if(!usuariosRegistrados.contains(usuario)){
        usuariosRegistrados.add(usuario);
    }
}

    public static boolean verificarCredenciales(String usuario, String contraseña){
        for(RegistroUsuario.Usuario usuarios:usuariosRegistrados){
            if (usuarios.getUsuario().equalsIgnoreCase(usuario)&&usuarios.getContraseña().equals(contraseña)) {
                return true;
            }
        }
        return false;
    }
    
    public static void eliminarUsuario(Usuario usuario){
    if(usuario==null){
        return;
    }

    RegistroUsuario.Usuario usuarioPorEliminar=null;

    for(RegistroUsuario.Usuario usuarioRegistrado:usuariosRegistrados) {
        if(usuarioRegistrado.getUsuario().equals(usuario.getUsuario())){
            usuarioPorEliminar=usuarioRegistrado;
            break;
        }
    }

    if(usuarioPorEliminar!=null){
        usuariosRegistrados.remove(usuarioPorEliminar);
    }
}
    
    public static void actualizarUsuario(RegistroUsuario.Usuario usuarioActual){
    for(RegistroUsuario.Usuario usuario:usuariosRegistrados){
        if(usuario.equals(usuarioActual)){
            usuario.setUsuario(usuarioActual.getUsuario());
            usuario.setContraseña(usuarioActual.getContraseña());
            break;
        }
    }
}
}
