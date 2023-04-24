package view;

import orar.Simulare;
import model.Client;
import model.Coada;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Afisare {
    private String fileToWrite;
    public Afisare(String fileToWrite){
        this.fileToWrite = fileToWrite;
    }

//se afiseaza in fisier
    public void afisareFisier()
    {
        JFrame f = new JFrame(fileToWrite);
        JTextArea afisare=new JTextArea("");
        String s="";
        for(String string:Simulare.simulationOutput){
            s=s+string+"\n";
        }
        s=s+" Timp mediu de efectuare a serviciilor  "+Simulare.timpmediuservice+"\n";
        s=s+" Timp mediu de asteptare  "+Simulare.timpmediuasteptare+"\n";
        afisare.setBounds(10,10,5000,6000);
        afisare.append(s);
        f.add(afisare);
        JScrollPane sp = new JScrollPane(afisare);
        f.getContentPane().add(sp);
        f.setSize(5000,2000);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
//pentru a putea afisa in forma dorita la fiecare moment de timp
    //se afiseaza in consola
    public void transformString(int moment, ArrayList<Client> clienti, ArrayList<Coada> cozi){
        String string = "Momentul  ";
        string += String.valueOf(moment);
        string += "\n"+"  In asteptare: ";
        for(Client client: clienti){
            string += client.toString() ;
        }
        string += "\n";
        int i = 1;
        for(Coada coada: cozi){
            string += "  Coada " + i + ":";
            if(!coada.getStatus()){
                string += "   INCHIS   ";
            }
            if(coada.getStatus()){
                string += coada.toString();
            }
            string += "\n";
            i++;
        }
        System.out.println(string);
        Simulare.simulationOutput.add(string);
    }
}
