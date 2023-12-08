package sesionTwitter;

import java.util.ArrayList;
import java.util.List;
import sesionTwitter.RegistroUsuario.Usuario;


public class UsuariosRegistrados {
    private static List<RegistroUsuario.Usuario> usuariosRegistrados=new ArrayList<>();

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
