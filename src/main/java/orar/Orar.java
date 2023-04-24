package orar;

import model.Client;
import model.Coada;

import java.util.ArrayList;

public class Orar {
    private ArrayList<Coada> cozi;
    private int nrCozi;
//depinde de numarul de cozi si lista acestora
    //numarul de cozi este egal cu numarul de thread-uri
    //se stabileste la care coada merge clientul
    //prima varianta este daca aceasta este inchisa, iar in caz contrar este aleasa in functie de timpul minim de asteptare
    public Orar(int nrCozi) {
        cozi = new ArrayList<>();
        this.nrCozi = nrCozi;
        for (int i = 0; i < nrCozi; i++) {
            Coada newcoada = new Coada();
            Thread thread = new Thread(newcoada);
            thread.start();
            cozi.add(newcoada);
        }
    }

    public void atribuire(Client client) {
        int i = nrCozi + 1;
        int min = cozi.get(0).getwait().intValue();
        for (Coada c : cozi) {
            if (!c.getStatus()) {
                c.setStatus(true);
                i = cozi.indexOf(c);
                break;
            }
        }
        if (i < nrCozi) {
            cozi.get(i).adaugareClient(client);
            cozi.get(i).run();
        } else if (i == nrCozi + 1)
            for (Coada q : cozi) {
                if (q.getwait().intValue() < min && q.getStatus()) {
                    min = q.getwait().intValue();
                    i = cozi.indexOf(q);
                }
            }
    }

    public ArrayList<Coada> getCozi() {
        return cozi;
    }

    public int getNrCozi() {
        return nrCozi;
    }
}
