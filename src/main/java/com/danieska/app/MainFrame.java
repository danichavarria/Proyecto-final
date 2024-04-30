package com.danieska.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame 
{
    private JDesktopPane desktopPane;

    public MainFrame()
     {
        super("LIBRERIA MARITZA");

        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane, BorderLayout.CENTER);

        // Crear y agregar formularios internos
        ProductForm productForm = new ProductForm();
        ClientForm clientForm = new ClientForm();
        SupplierForm supplierForm = new SupplierForm();
        
        desktopPane.add(productForm);
        desktopPane.add(clientForm);
        desktopPane.add(supplierForm);
      

        // Configurar el frame principal
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
     {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

class ProductForm extends JInternalFrame {
    public ProductForm() {
        super(" Producto", true, true, true, true);
        inicializarFormularioProducto();
        getContentPane().setBackground(Color.BLUE);
        
    }
    
    private void inicializarFormularioProducto(){
        setSize(300,200);

        JMenuBar menuBar = new JMenuBar();

        JMenu opcionesMenu = new JMenu("Opciones");

        JMenuItem nombreItem = new JMenuItem("Nombre");

        JMenuItem precioItem = new JMenuItem("Precio");

        nombreItem.setBackground(Color.PINK);
        precioItem.setBackground(Color.PINK);

        nombreItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
            if (nombre != null){
                JOptionPane.showMessageDialog(null, "Nombre ingresado:" + nombre);
            }
            }
        });

        precioItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){

            String precio = JOptionPane.showInputDialog("Ingrese el precio:");
            if (precio != null){
                JOptionPane.showMessageDialog(null, "Precio ingresado:" + precio);
            }
            }
        });


        

        opcionesMenu.add((nombreItem) );
        opcionesMenu.add(precioItem);

        menuBar.add(opcionesMenu);

        setJMenuBar(menuBar);
        setVisible(true);
    }
}

class ClientForm extends JInternalFrame {
    public ClientForm() {
        super(" Cliente", true, true, true, true);
        inicializarFormularioCliente();  
        getContentPane().setBackground(Color.BLUE);     
    }
    private void inicializarFormularioCliente(){
        setSize(300,200);

        JMenuBar menuBar = new JMenuBar();

        JMenu datosMenu = new JMenu("Datos");


        //crear los elementos del menu
        JMenuItem nombreItem = new JMenuItem("Nombre");
        JMenuItem apellidoItem = new JMenuItem("Apellido");
        JMenuItem direccionItem = new JMenuItem("Direccion");
        JMenuItem celularItem = new JMenuItem("Celular");

        nombreItem.setBackground(Color.PINK);
        apellidoItem.setBackground(Color.PINK);
        direccionItem.setBackground(Color.PINK);
        celularItem.setBackground(Color.PINK);

        nombreItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
            if (nombre != null){
                JOptionPane.showMessageDialog(null, "Nombre ingresado:" + nombre);
            }
            }
        });

        apellidoItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){

            String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
            if (apellido != null){
                JOptionPane.showMessageDialog(null, "apellido ingresado:" + apellido);
            }
            }
        });

        direccionItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){

            String direccion = JOptionPane.showInputDialog("Ingrese la direccion:");
            if (direccion != null){
                JOptionPane.showMessageDialog(null, "direccion ingresado:" + direccion);
            }
            }
        });

        celularItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                String celular = JOptionPane.showInputDialog("Ingrese el numero de celular:");
                if(celular != null){
                    JOptionPane.showMessageDialog(null, "Numero de celular ingresado:" + celular);
                }
            }
        });

          datosMenu.add(nombreItem);
          datosMenu.add(apellidoItem);
          datosMenu.add(direccionItem);
          datosMenu.add(celularItem);   

          
          menuBar.add(datosMenu);

          setJMenuBar(menuBar);

          setVisible(true);
        
    }
}

class SupplierForm extends JInternalFrame {
    public SupplierForm() {
        super(" Proveedor", true, true, true, true);
       inicializarFormularioSupplier();
       getContentPane().setBackground(Color.BLUE);
   }
     private void inicializarFormularioSupplier(){
        setSize(300, 200);

        JMenuBar menuBar = new JMenuBar();

        JMenu datosMenu = new JMenu("Datos");

        JMenuItem nombreItem = new JMenuItem("Nombre");
        
        JMenuItem celularItem = new JMenuItem("Celular");
        JMenuItem direccionItem = new JMenuItem("Direccion");

        nombreItem.setBackground(Color.PINK);
        celularItem.setBackground(Color.PINK);
        direccionItem.setBackground(Color.PINK);
        
        nombreItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
            if (nombre != null){
                JOptionPane.showMessageDialog(null, "Nombre ingresado:" + nombre);
            }
            }
        });

        celularItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
                String celular = JOptionPane.showInputDialog("Ingrese el numero de celular:");
                if(celular != null){
                    JOptionPane.showMessageDialog(null, "Numero de celular ingresado:" + celular);
                }
            }
        });

        direccionItem.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e){
            String direccion = JOptionPane.showInputDialog("Ingrese la direccion:");
            if(direccion != null){
                JOptionPane.showMessageDialog(null, "Direccion ingresada:" + direccion);
            }
            }
        });

        datosMenu.add(nombreItem) ;
        datosMenu.add(celularItem);
        datosMenu.add(direccionItem);

        menuBar.add(datosMenu);
        setJMenuBar(menuBar);

        setVisible(true);

     }

}



