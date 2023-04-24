package view;

import orar.Simulare;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Interfata i;

    public Controller(Interfata i) {
        this.i = i;
        i.exitActionListener(new exitActionListener());
        i.startActionListener(new startActionListener());}

    class startActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Afisare fm=new Afisare("Fisier.txt");
            if(i.getTimeService2()<1||i.getTimeService1()<1||i.getTimeArrive2()<1||i.getTimeArrive1()<1||i.getSimulare()<1||i.getCozi()<1||i.getClienti()<1){
                JOptionPane.showMessageDialog(null, "Date incorecte");
            }
            else{
                Simulare sim=new Simulare(i,fm);
                Thread t=new Thread(sim);
                t.start();

            }
        }
    }

    class exitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            i.close();
        }
    }

}
