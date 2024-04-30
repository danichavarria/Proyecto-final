package com.danieska.app;

import java.awt.BorderLayout;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class PanelToolbarExample extends JFrame {
    public  PanelToolbarExample() {
        super(" Ejemplo de Toolbar");

        // Crear una barra de herramientas
        JToolBar toolBar = new JToolBar();
        getContentPane().add(toolBar, BorderLayout.PAGE_START);

        // Crear botones para Clientes, Proveedor y Productos
        JButton clientesButton = new JButton("Clientes");
        JButton proveedorButton = new JButton("Proveedor");
        JButton productosButton = new JButton("Productos");

        // Agregar los botones a la barra de herramientas
        toolBar.add(clientesButton);
        toolBar.add(proveedorButton);
        toolBar.add(productosButton);

        // Configuraciones del frame principal
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

   
}
    

    