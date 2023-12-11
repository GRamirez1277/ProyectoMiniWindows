package Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class TypeFileSystemModel implements TreeModel {

    private File root;
    private Vector listeners = new Vector();
    private File copiedFile;
    private boolean isCut;

    public TypeFileSystemModel(File rootDirectory) {
        root = rootDirectory;
    }

    public Object getRoot() {
        return root;
    }

    public Object getChild(Object parent, int index) {
        File directory = (File) parent;
        File[] children = directory.listFiles();
        Arrays.sort(children, new Comparator<File>() {
            public int compare(File f1, File f2) {
                if (f1.isDirectory() && !f2.isDirectory()) {
                    return -1;
                } else if (!f1.isDirectory() && f2.isDirectory()) {
                    return 1;
                } else {
                    return f1.getName().compareToIgnoreCase(f2.getName());
                }
            }
        });
        return new TypeFileSystemModel.TreeFile(directory, children[index].getName());
    }

    public int getChildCount(Object parent) {
        File file = (File) parent;
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            if (fileList != null) {
                return fileList.length;
            }
        }
        return 0;
    }

    public boolean isLeaf(Object node) {
        File file = (File) node;
        return file.isFile();
    }

    public int getIndexOfChild(Object parent, Object child) {
        File directory = (File) parent;
        File file = new File(directory, child.toString());
        File[] children = directory.listFiles();
        Arrays.sort(children, new Comparator<File>() {
            public int compare(File f1, File f2) {
                if (f1.isDirectory() && !f2.isDirectory()) {
                    return -1;
                } else if (!f1.isDirectory() && f2.isDirectory()) {
                    return 1;
                } else {
                    return f1.getName().compareToIgnoreCase(f2.getName());
                }
            }
        });
        for (int i = 0; i < children.length; i++) {
            if (file.equals(children[i])) {
                return i;
            }
        }
        return -1;
    }

    public void valueForPathChanged(TreePath path, Object value) {
        File oldFile = (File) path.getLastPathComponent();
        String fileParentPath = oldFile.getParent();
        String newFileName = (String) value;
        File targetFile = new File(fileParentPath, newFileName);
        oldFile.renameTo(targetFile);
        File parent = new File(fileParentPath);
        int[] changedChildrenIndices = {getIndexOfChild(parent, targetFile)};
        Object[] changedChildren = {targetFile};
        fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);
    }

    private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);
        Iterator iterator = listeners.iterator();
        TreeModelListener listener = null;
        while (iterator.hasNext()) {
            listener = (TreeModelListener) iterator.next();
            listener.treeNodesChanged(event);
        }
    }

    public void addTreeModelListener(TreeModelListener listener) {
        listeners.add(listener);
    }

    public void removeTreeModelListener(TreeModelListener listener) {
        listeners.remove(listener);
    }

    private class TreeFile extends File {

        public TreeFile(File parent, String child) {
            super(parent, child);
        }

        public String toString() {
            return getName();
        }
    }

    public void copyFile(File file) {
        copiedFile = file;
        isCut = false;
    }

    public void cutFile(File file) {
        copiedFile = file;
        isCut = true;
    }

    public void pasteFile(File destinationFolder) throws IOException {
        if (copiedFile == null) {
            JOptionPane.showMessageDialog(null, "Error :(", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("PAIN AAA");
            return;
        }
        if (!destinationFolder.isDirectory()) {
            JOptionPane.showMessageDialog(null, "Destino no es folder", "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("");
        }
        if (!destinationFolder.exists()) {
            JOptionPane.showMessageDialog(null, "Destino no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new IOException("");
        }
        if (destinationFolder.equals(copiedFile.getParentFile())) {
            return;
        }
        File targetFile = new File(destinationFolder, copiedFile.getName());
        if (isCut) {
            Files.move(copiedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else {
            Files.copy(copiedFile.toPath(), targetFile.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
        }
        copiedFile = null;
    }
    public void refresh() {

        int[] indices = {0};
        Object[] children = {root};
        fireTreeNodesChanged(new TreePath(root), indices, children);
    }

    public boolean createFolder(File parent, String folderName) {
        File newFolder = new File(parent, folderName);
        return newFolder.mkdir();
    }

    public boolean createFile(File parent, String fileName) throws IOException {
        File newFile = new File(parent, fileName);
        return newFile.createNewFile();
    }

    public boolean rename(File file, String newName) throws IOException {
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "Archivo o folder no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new FileNotFoundException("");
        }

        File newFile = new File(file.getParent(), newName + getFileExtension(file));
        if (newFile.exists()) {
            JOptionPane.showMessageDialog(null, "Ese Nombre ya existe!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new IOException("");

        }

        boolean renamed = file.renameTo(newFile);
        if (renamed) {
            int[] indices = {getIndexOfChild(file.getParentFile(), newFile)};
            Object[] children = {new TypeFileSystemModel.TreeFile(file.getParentFile(), newName)};
            fireTreeNodesChanged(new TreePath(file.getParentFile()), indices, children);
        }
        return renamed;
    }

    private static String getFileExtension(File file) {
        String extension = "";
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = "." + fileName.substring(dotIndex + 1);
        }
        System.out.println("A:  " + extension);

        return extension;
    }

    public void sortFiles(File directorio) {
        File fileroot = new File("Z/" + ManejoFiles.nombre + "/");
        File imageDir = new File(fileroot, "Mis Imagenes");
        File docDir = new File(fileroot, "Mis Documentos");
        File musicDir = new File(fileroot, "Mi Musica");

        File[] files = fileroot.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String extension = getFileExtension2(file.getName());
                if (extension != null) {
                    extension = extension.toLowerCase();

                    switch (extension) {
                        case "jpg":
                        case "png":
                        case "gif":

                            moveFile(file, imageDir);

                            break;
                        case "doc":
                        case "docx":
                        case "pdf":
                        case "txt":
                            moveFile(file, docDir);
                            break;
                        case "mp3":
                        case "wav":
                        case "flac":
                            moveFile(file, musicDir);
                            break;
                        default:

                            break;
                    }
                }
            }
        }
    }

    private void moveFile(File file, File destDir) {
        File destFile = new File(destDir, file.getName());
        if (!destFile.exists()) {
            file.renameTo(destFile);
        }
    }

    private String getFileExtension2(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            System.out.println("YEP2");
            System.out.println(fileName.substring(dotIndex + 1));
            return fileName.substring(dotIndex + 1);
        } else {
            return null;
        }
    }
}
