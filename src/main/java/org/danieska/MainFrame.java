package org.danieska;

import org.danieska.entity.Cliente01;
import org.danieska.entity.Supplier01;
import org.danieska.services.GenericServiceImpl;
import org.danieska.services.IGenericService;
import org.danieska.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mariadb.jdbc.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private final JDesktopPane desktopPane;

    public MainFrame() {
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
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    // Métodos para establecer los valores
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}


class ProductForm extends JInternalFrame {
    private final ArrayList<Producto> productos;

    public ProductForm() {
        super(" Producto", true, true, true, true);
        productos = new ArrayList<>();
        inicializarFormularioProducto();
        getContentPane().setBackground(Color.BLUE);

    }


    private void inicializarFormularioProducto() {
        setSize(300, 200);

        JMenuBar menuBar = new JMenuBar();

        JMenu opcionesMenu = new JMenu("Opciones");

        JMenuItem nombreItem = new JMenuItem("Nombre");
        JMenuItem precioItem = new JMenuItem("Precio");
        JMenuItem mostrarItem = new JMenuItem("Mostrar Productos");

        nombreItem.setBackground(Color.PINK);
        precioItem.setBackground(Color.PINK);

        nombreItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                if (nombre != null) {
                    String precioStr = JOptionPane.showInputDialog("Ingrese el precio:");
                    if (precioStr != null) {
                        double precio = Double.parseDouble(precioStr);
                        Producto producto = new Producto(nombre, precio);
                        productos.add(producto);
                        JOptionPane.showMessageDialog(null, "Producto guardado: " + nombre + " - Precio: " + precio);
                    }
                }
            }

        });

        precioItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String precio = JOptionPane.showInputDialog("Ingrese el precio:");
                if (precio != null) {

                }
            }
        });
        mostrarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay productos guardados.", "Productos Guardados", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < productos.size(); i++) {
                        sb.append("Producto ").append(i + 1).append(": ").append(productos.get(i).getNombre()).append(" - Precio: ").append(productos.get(i).getPrecio()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString(), "Productos Guardados", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JMenuItem editarItem = new JMenuItem("Editar Productos");
        editarItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (productos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay productos guardados.", "Productos Guardados", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String inputIndex = JOptionPane.showInputDialog("Ingrese el número del producto que desea editar:");
                    if (inputIndex != null) {
                        try {
                            int index = Integer.parseInt(inputIndex) - 1;
                            if (index >= 0 && index < productos.size()) {
                                Producto producto = productos.get(index);
                                String nombre = JOptionPane.showInputDialog("editar el nombre:", producto.getNombre());
                                if (nombre != null) {
                                    String nuevoPrecioStr = JOptionPane.showInputDialog("Ingrese el nuevo precio:", producto.getPrecio());
                                    if (nuevoPrecioStr != null) {
                                        producto.setNombre(nombre);
                                        double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                                        producto.setPrecio(nuevoPrecio);
                                        JOptionPane.showMessageDialog(null, "Precio del producto '" + producto.getNombre() + "' editado correctamente.");
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Número de producto inválido.", "Editar Producto", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Editar Producto", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }
            }
        });
        JMenuItem eliminarItem = new JMenuItem("Eliminar Producto");
        eliminarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (productos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay productos guardados para eliminar.", "Eliminar Producto", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String inputIndex = JOptionPane.showInputDialog("Ingrese el numero del producto que desea eliminar:");
                    if (inputIndex != null) {
                        try {
                            int index = Integer.parseInt(inputIndex) - 1;
                            if (index >= 0 && index < productos.size()) {
                                productos.remove(index);
                                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Numero de Producto invalido.", "Eliminar Proucto", JOptionPane.WARNING_MESSAGE);
                            }

                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese un numero valido.", "Eliminar Producto", JOptionPane.WARNING_MESSAGE);
                        }

                    }

                }

            }

        });


        opcionesMenu.add((nombreItem));
        opcionesMenu.add(precioItem);
        opcionesMenu.addSeparator();
        opcionesMenu.add(mostrarItem);
        opcionesMenu.addSeparator();
        opcionesMenu.add(editarItem);
        opcionesMenu.add(eliminarItem);

        menuBar.add(opcionesMenu);

        setJMenuBar(menuBar);
        setVisible(true);
    }
    private static List<Producto> getProductos(){
        List<Producto> productos = new ArrayList<>();
        IGenericService<Producto> productoService = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
        productos = productoService.getAll();
        return productos;
    }
    private static void saveProducto(Producto producto){
        IGenericService<Producto> productoService = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
        productoService.save(producto);
    }
    private static void deleteProducto(Producto producto){
        IGenericService<Producto> productoService = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
        productoService.delete(producto);
    }
    private static void updateProducto(Producto producto){
        IGenericService<Producto> productoService = new GenericServiceImpl<>(Producto.class, HibernateUtil.getSessionFactory());
        productoService.update(producto);
    }
}

class Client {
    private String nombre;
    private String apellido;
    private String direccion;
    private String celular;

    public Client(String nombre, String apellido, String direccion, String celular) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.celular = celular;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}



class ClientForm extends JInternalFrame {
    private ArrayList<Client> clientes;
    private JTextField nombreField, apellidoField, direccionField, celularField;
    private Connection connection;
    public ClientForm() {
        super(" Cliente", true, true, true, true);
        clientes = new ArrayList<>();
        inicializarFormularioCliente();

    }




    private void inicializarFormularioCliente() {
        setSize(300, 200);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(Color.BLUE);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoField = new JTextField();
        JLabel direccionLabel = new JLabel("direccion:");
        direccionField = new JTextField();
        JLabel celularLabel = new JLabel("Celular:");
        celularField = new JTextField();

        formPanel.add(nombreLabel);
        formPanel.add(nombreField);
        formPanel.add(apellidoLabel);
        formPanel.add(apellidoField);
        formPanel.add(direccionLabel);
        formPanel.add(direccionField);
        formPanel.add(celularLabel);
        formPanel.add(celularField);

        //crear botones y anadir acciones
        JButton guardarButton = new JButton("Guardar");


        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }

        });

        JButton mostrarButton = new JButton("Mostrar Clientes");
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarClientes();
            }
        });
        JButton editarButton = new JButton("Editar Cliente");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });
        JButton eliminarButton = new JButton("Eliminar Cliente");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarButton);
        buttonPanel.add(mostrarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void guardarCliente() {

        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String direccion = direccionField.getText();
        String celular = celularField.getText();

        System.out.println("Nombre:" + nombre);
        System.out.println("Apellido:" + apellido);
            System.out.println("Direccion:" + direccion);
            System.out.println("Celular:" + celular);


        if (!nombre.isEmpty() && !celular.isEmpty() && !direccion.isEmpty()) {
            Client cliente = new Client(nombre, apellido, direccion, celular);
            clientes.add(cliente);
            JOptionPane.showMessageDialog(this, "Cliente guardado:\nNombre: " + nombre + "\nApellido:" + apellido + "\nDirección: " + direccion + "\nCelular: " + celular);
            limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarClientes() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes guardados.", "Clientes Guardados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < clientes.size(); i++) {
                Client cliente = clientes.get(i);
                sb.append("Cliente ").append(i + 1).append(":\n");
                sb.append("Nombre: ").append(cliente.getNombre()).append("\n");
                sb.append("Apellido: ").append(cliente.getApellido()).append("\n");
                sb.append("Dirección: ").append(cliente.getDireccion()).append("\n");
                sb.append("Celular: ").append(cliente.getCelular()).append("\n\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Clientes Guardados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editarCliente() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes guardados para editar.", "Editar Cliente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String inputIndex = JOptionPane.showInputDialog("Ingrese el número del cliente que desea editar:");
            if (inputIndex != null) {
                try {
                    int index = Integer.parseInt(inputIndex) - 1;
                    if (index >= 0 && index < clientes.size()) {
                        Client cliente = clientes.get(index);
                        String nombre = JOptionPane.showInputDialog("Editar el nombre:", cliente.getNombre());
                        if (nombre != null) {
                            String apellido = JOptionPane.showInputDialog("Editar el apellido:", cliente.getApellido());
                            if (apellido != null) {
                                String direccion = JOptionPane.showInputDialog("Editar la dirección:", cliente.getDireccion());
                                if (direccion != null) {
                                    String celular = JOptionPane.showInputDialog("Editar el número de celular:", cliente.getCelular());
                                    if (celular != null) {
                                        cliente.setNombre(nombre);
                                        cliente.setApellido(apellido);
                                        cliente.setDireccion(direccion);
                                        cliente.setCelular(celular);
                                        JOptionPane.showMessageDialog(this, "Cliente editado correctamente.");
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Número de cliente inválido.", "Editar Cliente", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.", "Editar Cliente", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    private void eliminarCliente() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay clientes guardados para eliminar.", "Eliminar Cliente", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String inputIndex = JOptionPane.showInputDialog("Ingrese el número del cliente que desea eliminar:");
            if (inputIndex != null) {
                try {
                    int index = Integer.parseInt(inputIndex) - 1;
                    if (index >= 0 && index < clientes.size()) {
                        clientes.remove(index);
                        JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Número de cliente inválido.", "Eliminar Cliente", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.", "Eliminar Cliente", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    private void limpiarFormulario() {
        nombreField.setText("");
        apellidoField.setText("");
        direccionField.setText("");
        celularField.setText("");
    }
     private static List<Cliente01> getCliente01(){
         java.util.List<Cliente01> clientes = new ArrayList<>();
         IGenericService<Cliente01> clienteService = new GenericServiceImpl<>(Cliente01.class, HibernateUtil.getSessionFactory());
         clientes = clienteService.getAll();
         return clientes;

     }

     private static void saveCliente01(Cliente01 cliente){
        IGenericService<Cliente01> clienteService = new GenericServiceImpl<>(Cliente01.class, HibernateUtil.getSessionFactory());
        clienteService.save(cliente);
     }
     private static void deleteCliente01(Cliente01 cliente){
        IGenericService<Cliente01> clienteService = new GenericServiceImpl<>(Cliente01.class, HibernateUtil.getSessionFactory());
        clienteService.delete(cliente);
     }
     private static void updateCliente01(Cliente01 cliente){
        IGenericService<Cliente01> clienteService = new GenericServiceImpl<>(Cliente01.class, HibernateUtil.getSessionFactory());
        clienteService.update(cliente);
     }

}



class Supplier {
    private String nombre;
    private String celular;
    private String direccion;

    public Supplier(String nombre, String celular, String direccion) {
        this.nombre = nombre;
        this.celular = celular;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

class SupplierForm extends JInternalFrame {
    private  ArrayList<Supplier> proveedores;
    private JTextField nombreField, celularField, direccionField;

    public SupplierForm() {
        super("Proveedor", true, true, true, true);
        proveedores = new ArrayList<>();
        inicializarFormularioProveedor();



    }
    private void inicializarFormularioProveedor() {
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(Color.BLUE);


        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel celularLabel = new JLabel("Celular:");
        celularField = new JTextField();
        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField();

        formPanel.add(nombreLabel);
        formPanel.add(nombreField);
        formPanel.add(celularLabel);
        formPanel.add(celularField);
        formPanel.add(direccionLabel);
        formPanel.add(direccionField);

        JButton guardarButton = new JButton("Guardar");


        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProveedor();
            }
        });


        JButton mostrarButton = new JButton("Mostrar Proveedores");
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProveedores();
            }
        });

        JButton editarButton = new JButton("Editar Proveedor");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProveedor();
            }
        });
        JButton eliminarButton = new JButton("Eliminar proveedor");
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProveedor();

            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarButton);
        buttonPanel.add(mostrarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(eliminarButton);



        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void guardarProveedor() {
        String nombre = nombreField.getText();
        String celular = celularField.getText();
        String direccion = direccionField.getText();

        if (!nombre.isEmpty() && !celular.isEmpty() && !direccion.isEmpty()) {
            Supplier proveedor = new Supplier(nombre, celular, direccion);
            proveedores.add(proveedor);
            JOptionPane.showMessageDialog(this, "Proveedor guardado:\nNombre: " + nombre + "\nCelular: " + celular + "\nDirección: " + direccion);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


private void mostrarProveedores() {
        if (proveedores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay proveedores guardados.", "Proveedores Guardados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < proveedores.size(); i++) {
                Supplier proveedor = proveedores.get(i);
                sb.append("Proveedor ").append(i + 1).append(":\n");
                sb.append("Nombre: ").append(proveedor.getNombre()).append("\n");
                sb.append("Celular: ").append(proveedor.getCelular()).append("\n");
                sb.append("Dirección: ").append(proveedor.getDireccion()).append("\n\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "Proveedores Guardados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editarProveedor() {
        if (proveedores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay proveedores guardados para editar.", "Editar Proveedor", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String inputIndex = JOptionPane.showInputDialog("Ingrese el número del proveedor que desea editar:");
            if (inputIndex != null) {
                try {
                    int index = Integer.parseInt(inputIndex) - 1;
                    if (index >= 0 && index < proveedores.size()) {
                        Supplier proveedor = proveedores.get(index);
                        String nombre = JOptionPane.showInputDialog("Editar el nombre:", proveedor.getNombre());
                        if (nombre != null) {
                            String celular = JOptionPane.showInputDialog("Editar el número de celular:", proveedor.getCelular());
                            if (celular != null) {
                                String direccion = JOptionPane.showInputDialog("Editar la dirección:", proveedor.getDireccion());
                                if (direccion != null) {
                                    proveedor.setNombre(nombre);
                                    proveedor.setCelular(celular);
                                    proveedor.setDireccion(direccion);
                                    JOptionPane.showMessageDialog(this, "Proveedor editado correctamente.");
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Número de proveedor inválido.", "Editar Proveedor", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.", "Editar Proveedor", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    private  void eliminarProveedor(){
        if (proveedores.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay proveedor guardados para eliminar.", "Elminar Proveedor", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String inputIndex = JOptionPane.showInputDialog("Ingrese el numero del proveedor que desea eliminar:");
            if (inputIndex != null) {
                try {
                    int index = Integer.parseInt(inputIndex) - 1;
                    if (index >= 0 && index < proveedores.size()) {
                        proveedores.remove(index);
                        JOptionPane.showMessageDialog(null, "Proveedor eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Numero de proveedor invalido.", "Eliminar proveedor", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un numero valido.", "Eliminar proveedor", JOptionPane.WARNING_MESSAGE);

                }

            }
        }
    }



    private void limpiarFormulario() {
        nombreField.setText("");
        celularField.setText("");
        direccionField.setText("");
    }

    private  static List<Supplier> getSupplier(){
        List<Supplier> suppliers = new ArrayList<>();
        IGenericService<Supplier> supplierService = new GenericServiceImpl<>(Supplier.class, HibernateUtil.getSessionFactory());
        suppliers = supplierService.getAll();
        return suppliers;
    }
    private  static void saveSupplier01(Supplier supplier){
        IGenericService<Supplier> supplierService = new GenericServiceImpl<>(Supplier.class, HibernateUtil.getSessionFactory());
        supplierService.save(supplier);
    }
    private  static void deleteSupplier(Supplier supplier){
        IGenericService<Supplier> supplierService = new GenericServiceImpl<>(Supplier.class, HibernateUtil.getSessionFactory());
        supplierService.delete(supplier);
    }
    private static void updateSupplier(Supplier supplier){
        IGenericService<Supplier> supplierService = new GenericServiceImpl<>(Supplier.class, HibernateUtil.getSessionFactory());
        supplierService.update(supplier);
    }

}


