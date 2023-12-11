package Usuarios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Users {

    RandomAccessFile users;
    static File archivos = null;

    public Users() {

        //Constructor de la clase
        try {
            //nombre del archivo
            //r = lectura
            //rw = lectura y escritura
            users = new RandomAccessFile("BDUsuarios/RegistrosUsers.usr", "rw");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

    }

    public long getFinal() throws IOException {

        if (users.length() != 0) {
            users.seek(0);
            while (users.getFilePointer() < users.length()) {
                //leer un string(nombre)
                users.readUTF();
                //leer un string(contra)
                users.readUTF();
                //leer un string(tipo)
                users.readUTF();
            }
        }
        return users.getFilePointer();

    }

    public static void setArchivos(String direccion) {
        archivos = new File(direccion);
    }

    public static boolean crearFolder() {
        return archivos.mkdir();
    }

    public boolean write(String usuario, String contraseña, String tipo) throws IOException {
        try {
            users.seek(users.length());
            users.writeUTF(usuario);
            users.writeUTF(contraseña);
            users.writeUTF(tipo);

            setArchivos("Z");
            archivos.mkdir();
            setArchivos("Z/" + usuario);

            if (crearFolder()) {
                setArchivos("Z/" + usuario + "/Mis Imagenes");
                archivos.mkdir();

                setArchivos("Z/" + usuario + "/Mis Documentos");
                archivos.mkdir();

                setArchivos("Z/" + usuario + "/Mi Musica");
                archivos.mkdir();
                return true;
            }

        } catch (IOException e) {
            return false;
        }
        return false;
    }

    public boolean findUser(String usuario, String contraseña) throws IOException {
        users.seek(0);
        String user;
        String password;
        String tipo;
        while (users.getFilePointer() < users.length()) {
            long pos = users.getFilePointer();
            user = users.readUTF();
            password = users.readUTF();
            tipo = users.readUTF();
            if (user.equals(usuario) && password.equals(contraseña)) {
                users.seek(pos);
                return true;
            }
        }
        return false;
    }

    public String encontrartipo(String usuario, String contra) throws IOException {

        users.seek(0);
        String user;
        String password;
        String tipo;
        while (users.getFilePointer() < users.length()) {
            user = users.readUTF();
            password = users.readUTF();
            tipo = users.readUTF();
            if (user.equals(usuario) && password.equals(contra)) {
                return tipo;
            }
        }
        return null;

    }

}
