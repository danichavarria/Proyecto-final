package org.danieska;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainApp extends JFrame {
    private JDesktopPane desktopPane;

    public MainApp(){
        super ("BIENVENIDO AL MENU");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        desktopPane = new JDesktopPane();

        getContentPane().add(desktopPane);

        desktopPane.setBackground(Color.LIGHT_GRAY);

        //crear menus
        JMenuBar menuBar = new JMenuBar();

        JMenu appMenu = new JMenu("App");
        JMenuItem salirItem = new JMenuItem("Salir");
        salirItem.setBackground(Color.WHITE);
        salirItem.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        appMenu.add(salirItem);
        menuBar.add(appMenu);

        JMenu entidadMenu = new JMenu("Entidades");
        JMenuItem abrirProductoItem = new JMenuItem("Abrir Formulario de Producto");
        abrirProductoItem.setBackground(Color.WHITE);
        abrirProductoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ProductForm productForm = new ProductForm();
                desktopPane.add(productForm);
                productForm.setVisible(true);
            }
        });
        JMenuItem abrirClienteItem = new JMenuItem("Abrir Formulario de cliente");
        abrirClienteItem.setBackground(Color.WHITE);
        abrirClienteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientForm clienteForm = new ClientForm();
                desktopPane.add(clienteForm);
                clienteForm.setVisible(true);
            }
        });
        JMenuItem abrirProveedorItem = new JMenuItem("Abrir Formulario de Proveedor");
        abrirProveedorItem.setBackground(Color.WHITE);
        abrirProveedorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierForm proveedorForm = new SupplierForm();
                desktopPane.add(proveedorForm);
                proveedorForm.setVisible(true);
            }
        });
        entidadMenu.add(abrirClienteItem);
        entidadMenu.add(abrirProveedorItem);
        entidadMenu.add(abrirProductoItem);
        menuBar.add(entidadMenu);

        // Menú para mostrar información sobre la aplicación
        JMenu infoMenu = new JMenu("Información");
        JMenuItem aboutItem = new JMenuItem("Acerca de");
        aboutItem.setBackground(Color.WHITE);
        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Mi Aplicación - Versión 1.0", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        infoMenu.add(aboutItem);
        menuBar.add(infoMenu);

        setJMenuBar(menuBar);
        setVisible(true);
    }
}