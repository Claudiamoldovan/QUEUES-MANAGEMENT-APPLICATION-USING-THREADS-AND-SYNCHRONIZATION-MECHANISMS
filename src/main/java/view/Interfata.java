package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Interfata {

    private JFrame f;

    private JTextField clienti;
    private JTextField cozi;
    private JTextField simulare;
    private JTextField timeArrive1;
    private JTextField timeArrive2;
    private JTextField timeService1;
    private JTextField timeService2;

    private Button start;
    private Button exit;

    public Interfata() {
        f = new JFrame();

        JLabel client = new JLabel("Numar clienti:");
        client.setBounds(10, 50, 140, 40);
        f.add(client);
        clienti = new JTextField("");
        clienti.setBounds(150, 50, 140, 40);
        f.add(clienti);

        JLabel nr_cozi = new JLabel("Numar cozi:");
        nr_cozi.setBounds(10, 100, 140, 40);
        f.add(nr_cozi);
        cozi = new JTextField("");
        cozi.setBounds(150, 100, 140, 40);
        f.add(cozi);

        JLabel simulare1 = new JLabel("Timp maxim simulare:");
        simulare1.setBounds(10, 150, 140, 40);
        f.add(simulare1);
        simulare = new JTextField("");
        simulare.setBounds(150, 150, 140, 40);
        f.add(simulare);

        JLabel sosire = new JLabel("Sosire:");
        sosire.setBounds(10, 200, 50, 40);
        f.add(sosire);
        timeArrive1 = new JTextField("");
        timeArrive1.setBounds(75, 200, 150, 40);
        f.add(timeArrive1);
        timeArrive2 = new JTextField("");
        timeArrive2.setBounds(240, 200, 150, 40);
        f.add(timeArrive2);

        JLabel serviciu = new JLabel("Serviciu:");
        serviciu.setBounds(10, 250, 50, 40);
        f.add(serviciu);
        timeService1 = new JTextField("");
        timeService1.setBounds(75, 250, 150, 40);
        f.add(timeService1);
        timeService2 = new JTextField("");
        timeService2.setBounds(240, 250, 150, 40);
        f.add(timeService2);

        start = new Button("Start");
        start.setBounds(100, 300, 50, 40);
        f.add(start);

        exit = new Button("Exit");
        exit.setBounds(250, 300, 50, 40);
        f.add(exit);

        f.setSize(420, 400);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
    }

    public int getClienti() {
        if (clienti.getText().matches("[0-9]*"))
            return Integer.parseInt(clienti.getText());
        else {
            JOptionPane.showMessageDialog(null, "Numar de clienti gresit");
            return 0;
        }
    }

    public int getCozi() {
        if (cozi.getText().matches("[0-9]*"))
            return Integer.parseInt(cozi.getText());
        else {
            JOptionPane.showMessageDialog(null, "Numar de cozi gresit");
            return 0;
        }
    }

    public int getSimulare() {
        if (simulare.getText().matches("[0-9]*"))
            return Integer.parseInt(simulare.getText());
        else {
            JOptionPane.showMessageDialog(null, "T simulare gresit");
            return 0;
        }
    }

    public int getTimeArrive1() {
        if (timeArrive1.getText().matches("[0-9]*"))
            return Integer.parseInt(timeArrive1.getText());
        else {
            JOptionPane.showMessageDialog(null, "T sosire min gresit");
            return 0;
        }
    }

    public int getTimeArrive2() {
        if (timeArrive2.getText().matches("[0-9]*"))
            return Integer.parseInt(timeArrive2.getText());
        else {
            JOptionPane.showMessageDialog(null, "T sosire max gresit");
            return 0;
        }
    }

    public int getTimeService1() {
        if (timeService1.getText().matches("[0-9]*"))
            return Integer.parseInt(timeService1.getText());
        else {
            JOptionPane.showMessageDialog(null, "T service min gresit");
            return 0;
        }
    }

    public int getTimeService2() {
        if (timeService2.getText().matches("[0-9]*"))
            return Integer.parseInt(timeService2.getText());
        else {
            JOptionPane.showMessageDialog(null, "T service max gresit");
            return 0;
        }
    }

    public void exitActionListener(ActionListener actionListener) {
        exit.addActionListener(actionListener);
    }

    public void startActionListener(ActionListener actionListener) {
        start.addActionListener(actionListener);
    }

    public void close() {
        f.dispose();
    }
}
