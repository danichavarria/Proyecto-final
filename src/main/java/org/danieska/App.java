package org.danieska;
import org.danieska.entity.Cliente;
import org.danieska.services.GenericServiceImpl;
import org.danieska.services.IGenericService;
import org.danieska.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class App {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;


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


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());


                if (username.equals("usuario") && password.equals("contrasena")) {
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

    private static void guardarUser(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }




        public static void main (String[]args){
            new App();
        }

    }


