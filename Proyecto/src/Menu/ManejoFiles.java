package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class ManejoFiles extends javax.swing.JInternalFrame {

    JPopupMenu popupMenu = new JPopupMenu();
    public static String nombre;
    public static String tipo;
    int refresh = 0;
    File selectedFile;
    private FileSystemModel fileSystemModel;
    private NameSortedFileSystemModel Name;
    private DateFileSystemModel Date;
    private TypeFileSystemModel Type;
    private SizeFileSystemModel Size;
    String sort = "Default";

    public ManejoFiles() {
        initComponents();
        File rootDirectory = new File("/");
        fileSystemModel = new FileSystemModel(rootDirectory);
        Name = new NameSortedFileSystemModel(rootDirectory);
        Date = new DateFileSystemModel(rootDirectory);
        Type = new TypeFileSystemModel(rootDirectory);
        Size = new SizeFileSystemModel(rootDirectory);

        JMenuItem copyItem = new JMenuItem("Copiar");
        JMenuItem cutItem = new JMenuItem("Cortar");
        JMenuItem pasteItem = new JMenuItem("Pegar");
        JMenuItem rename = new JMenuItem("Renombrar");
        JMenuItem refreshyep = new JMenuItem("Refrescar");
        JMenuItem createfile = new JMenuItem("Nuevo Archivo");
        JMenuItem createfolder = new JMenuItem("Nuevo Folder");
        JMenuItem organizar = new JMenuItem("Organizar");
        JMenuItem sortName = new JMenuItem("Ver por Nombre");
        JMenuItem sortDate = new JMenuItem("Ver por Fecha");
        JMenuItem sortType = new JMenuItem("Ver por Tipo");
        JMenuItem sortSize = new JMenuItem("Ver por Tamaño");
        popupMenu.add(copyItem);
        popupMenu.add(cutItem);
        popupMenu.add(pasteItem);
        popupMenu.add(rename);
        popupMenu.add(refreshyep);
        popupMenu.add(createfile);
        popupMenu.add(createfolder);
        popupMenu.add(organizar);
        popupMenu.add(sortName);
        popupMenu.add(sortDate);
        popupMenu.add(sortType);
        popupMenu.add(sortSize);

        jTree1.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        copyItem.setAction(new AbstractAction("Copy") {
            public void actionPerformed(ActionEvent e) {
                selectedFile = (File) jTree1.getLastSelectedPathComponent();
                copyFile(selectedFile);
            }
        });

        cutItem.setAction(new AbstractAction("Cut") {
            public void actionPerformed(ActionEvent e) {
                selectedFile = (File) jTree1.getLastSelectedPathComponent();
                cutFile(selectedFile);
            }
        });

        pasteItem.setAction(new AbstractAction("Paste") {
            public void actionPerformed(ActionEvent e) {
                File LastFile = (File) jTree1.getLastSelectedPathComponent();
                pasteFile(LastFile);
            }
        });
        refreshyep.setAction(new AbstractAction("Refresh") {
            public void actionPerformed(ActionEvent e) {
                refreshing();
            }
        });
        createfile.setAction(new AbstractAction("New File") {
            public void actionPerformed(ActionEvent e) {
                selectedFile = (File) jTree1.getLastSelectedPathComponent();
                try {
                    newFile(selectedFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR (usualmente es por no seleccionar un folder)", "ERROR", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ManejoFiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        createfolder.setAction(new AbstractAction("New Folder") {
            public void actionPerformed(ActionEvent e) {
                selectedFile = (File) jTree1.getLastSelectedPathComponent();
                try {
                    newFolder(selectedFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR (usualmente es por no seleccionar un folder)", "ERROR", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ManejoFiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        rename.setAction(new AbstractAction("Renombrar") {
            public void actionPerformed(ActionEvent e) {
                selectedFile = (File) jTree1.getLastSelectedPathComponent();
                String renombre = JOptionPane.showInputDialog(null, "renombrar", null);
                try {
                    System.out.println("Hola");
                    rename(selectedFile, renombre);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ManejoFiles.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        organizar.setAction(new AbstractAction("Organizar") {
            public void actionPerformed(ActionEvent e) {
                selectedFile = (File) jTree1.getLastSelectedPathComponent();
                if (selectedFile.isDirectory()) {
                    organizar(selectedFile);
                } else {
                    JOptionPane.showMessageDialog(null, "por favor seleccione un folder", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        sortName.setAction(new AbstractAction("Ver por Nombre") {
            public void actionPerformed(ActionEvent e) {
                sortbyName();
            }
        });
        sortDate.setAction(new AbstractAction("Ver por Fecha") {
            public void actionPerformed(ActionEvent e) {
                sortbyDate();
            }
        });
        sortType.setAction(new AbstractAction("Ver por Tipo") {
            public void actionPerformed(ActionEvent e) {
                SortbyType();
            }
        });
        sortSize.setAction(new AbstractAction("Ver por Tamaño") {
            public void actionPerformed(ActionEvent e) {
                SortbySize();
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Mis Archivos");

        jTree1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTree1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTree1FocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTree1FocusGained
        if (refresh == 0) {
            refresh++;
            if (tipo.equals("Administrador")) {
                jTree1.setModel(new FileSystemModel(new File("Z")));
            } else {
                jTree1.setModel(new FileSystemModel(new File("Z/" + nombre)));
            }
        }
    }//GEN-LAST:event_jTree1FocusGained
    private void copyFile(File file) {
        switch (sort) {
            case "name":
                Name.copyFile(file);
                break;
            case "date":
                Date.copyFile(file);
                break;
            case "type":
                Type.copyFile(file);
                break;
            case "size":
                Size.copyFile(file);
            default:
                fileSystemModel.copyFile(file);
                break;
        }

    }

    private void cutFile(File file) {
        switch (sort) {
            case "name":
                Name.cutFile(file);
                break;
            case "date":
                Date.cutFile(file);
                break;
            case "type":
                Type.cutFile(file);
                break;
            case "size":
                Size.cutFile(file);
                break;
            default:
                fileSystemModel.cutFile(file);
                break;
        }

    }

    private void pasteFile(File destinationFolder) {
        try {
            switch (sort) {
                case "name":
                    Name.pasteFile(destinationFolder);
                    refreshing();
                    break;
                case "date":
                    Date.pasteFile(destinationFolder);
                    refreshing();
                    break;
                case "type":
                    Type.pasteFile(destinationFolder);
                    refreshing();
                    break;
                case "size":
                    Size.pasteFile(destinationFolder);
                    refreshing();
                    break;
                default:
                    fileSystemModel.pasteFile(destinationFolder);
                    refreshing();
                    break;
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR (usualmente es por no seleccionar un folder)", "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void refreshing() {
        switch (sort) {
            case "name":
                if (tipo.equals("Administrador")) {
                    NameSortedFileSystemModel model = new NameSortedFileSystemModel(new File("Z"));
                    model.refresh();
                    jTree1.setModel(model);
                } else {
                    NameSortedFileSystemModel model = new NameSortedFileSystemModel(new File("Z/" + nombre));
                    model.refresh();
                    jTree1.setModel(model);
                }
                break;
            case "date":
                if (tipo.equals("Administrador")) {
                    DateFileSystemModel model = new DateFileSystemModel(new File("Z"));
                    model.refresh();
                    jTree1.setModel(model);
                } else {
                    DateFileSystemModel model = new DateFileSystemModel(new File("Z/" + nombre));
                    model.refresh();
                    jTree1.setModel(model);
                }
                break;
            case "type":
                if (tipo.equals("Administrador")) {
                    TypeFileSystemModel model = new TypeFileSystemModel(new File("Z"));
                    model.refresh();
                    jTree1.setModel(model);
                } else {
                    TypeFileSystemModel model = new TypeFileSystemModel(new File("Z/" + nombre));
                    model.refresh();
                    jTree1.setModel(model);
                }
                break;
            case "size":
                if (tipo.equals("Administrador")) {
                    SizeFileSystemModel model = new SizeFileSystemModel(new File("Z"));
                    model.refresh();
                    jTree1.setModel(model);
                } else {
                    SizeFileSystemModel model = new SizeFileSystemModel(new File("Z/" + nombre));
                    model.refresh();
                    jTree1.setModel(model);
                }
                break;
            default:
                if (tipo.equals("Administrador")) {
                    FileSystemModel model = new FileSystemModel(new File("Z"));
                    model.refresh();
                    jTree1.setModel(model);
                } else {
                    FileSystemModel model = new FileSystemModel(new File("Z/" + nombre));
                    model.refresh();
                    jTree1.setModel(model);
                }
                break;
        }
    }

    private void sortbyName() {
        sort = "name";
        if (tipo.equals("Administrador")) {
            jTree1.setModel(new NameSortedFileSystemModel(new File("Z")));
        } else {
            jTree1.setModel(new NameSortedFileSystemModel(new File("Z/" + nombre)));
        }

    }

    private void sortbyDate() {
        sort = "date";
        if (tipo.equals("Administrador")) {
            jTree1.setModel(new DateFileSystemModel(new File("Z")));
        } else {
            jTree1.setModel(new DateFileSystemModel(new File("Z/" + nombre)));
        }
    }

    private void SortbyType() {
        sort = "type";
        if (tipo.equals("Administrador")) {
            jTree1.setModel(new TypeFileSystemModel(new File("Z")));
        } else {
            jTree1.setModel(new TypeFileSystemModel(new File("Z/" + nombre)));
        }
    }

    private void SortbySize() {
        sort = "size";
        if (tipo.equals("Administrador")) {
            jTree1.setModel(new SizeFileSystemModel(new File("Z")));
        } else {
            jTree1.setModel(new SizeFileSystemModel(new File("Z/" + nombre)));
        }
    }

    private void newFile(File file) throws IOException {
        switch (sort) {
            case "name":
                Name.createFile(file, "New File");
                refreshing();
                break;
            case "date":
                Date.createFile(file, "New File");
                refreshing();
                break;
            case "type":
                Type.createFile(file, "New File");
                refreshing();
                break;
            case "size":
                Size.createFile(file, "New File");
                refreshing();
                break;
            default:
                fileSystemModel.createFile(file, "New File");
                refreshing();
                break;
        }

    }

    private void newFolder(File file) throws IOException {
        switch (sort) {
            case "name":
                Name.createFolder(file, "New File");
                refreshing();
                break;
            case "date":
                Date.createFolder(file, "New File");
                refreshing();
                break;
            case "type":
                Type.createFolder(file, "New File");
                refreshing();
                break;
            case "size":
                Size.createFolder(file, "New File");
                refreshing();
                break;
            default:
                fileSystemModel.createFolder(file, "New File");
                refreshing();
                break;
        }
    }

    private void rename(File file, String string) throws IOException {
        System.out.println("Adios");
        switch (sort) {
            case "name":
                Name.rename(file, string);
                refreshing();
                break;
            case "date":
                Date.rename(file, string);
                refreshing();
                break;
            case "type":
                Type.rename(file, string);
                refreshing();
                break;
            case "size":
                Size.rename(file, string);
                refreshing();
                break;
            default:
                fileSystemModel.rename(file, string);
                refreshing();
                break;
        }

    }

    private void organizar(File file) {
        switch (sort) {
            case "name":
                Name.sortFiles(file);
                refreshing();
                break;
            case "date":
                Date.sortFiles(file);
                refreshing();
                break;
            case "type":
                Type.sortFiles(file);
                refreshing();
                break;
            case "size":
                Size.sortFiles(file);
                refreshing();
                break;
            default:
                fileSystemModel.sortFiles(file);
                refreshing();
                break;
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
