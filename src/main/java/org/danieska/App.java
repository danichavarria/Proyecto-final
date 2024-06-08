package org.danieska;
import org.danieska.entity.Cliente01;
import org.danieska.entity.User;
import org.danieska.services.GenericServiceImpl;
import org.danieska.services.IGenericService;
import org.danieska.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;


public class App {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    private List<User>usuarios;
    public App() {
        JFrame frame = new JFrame();

        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Enviar");
        subirUser();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());


                if (username.equals("") && password.equals("")) {
                    JOptionPane.showMessageDialog(null, "¡Envio exitoso!");

                    new MainApp();
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }



        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Espacio en blanco para mantener la cuadrícula
        panel.add(loginButton);


        panel.setBackground(Color.CYAN);

        frame.add(panel);
        frame.setVisible(true);


    }

    public void subirUser(){

        User usuario= new User("Admin","1234");
        guardarUser(usuario);
        usuarios=getUser();
    }

    private List<User> getUser(){
        IGenericService<User> usuario= new GenericServiceImpl<>(User.class,HibernateUtil.getSessionFactory());
        return usuario.getAll();
    }
    public void guardarUser(User user){
        IGenericService<User> usuario = new GenericServiceImpl<>(User.class,HibernateUtil.getSessionFactory());
        usuario.save(user);
    }

    public static void main (String[]args){
            new App();
        }

    }


