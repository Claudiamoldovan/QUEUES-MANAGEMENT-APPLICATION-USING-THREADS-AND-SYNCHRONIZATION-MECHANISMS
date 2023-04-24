package orar;

import model.Client;
import model.Coada;
import view.Afisare;
import view.Interfata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Simulare implements Runnable {

    private orar.Orar orar;
    private Interfata i;
    private ArrayList<Client> clienti = new ArrayList<>();//coada de asteptare
    static public ArrayList<String> simulationOutput = new ArrayList<>();
    static public float timpmediuservice = 0;
    public static int timpmediuasteptare = 0;

    private Afisare fm;

    public Simulare(Interfata i, Afisare fm) {
        this.i = i;
        orar = new orar.Orar(i.getCozi());
        generareC();
        this.fm = fm;
    }
//generam lista cu clienti avant timpurile intre intervalele date
    //pe aceasta lista o sortam in functie de timpul de sosire
    public void generareC() {
        Random r = new Random();
        int tarrive, tservice;
        for (int i = 1; i <= this.i.getClienti(); i++) {
            tarrive = r.nextInt(this.i.getTimeArrive2() - this.i.getTimeArrive1() + 1) + this.i.getTimeArrive1();
            tservice = r.nextInt(this.i.getTimeService2() - this.i.getTimeService1() + 1) + this.i.getTimeService1();
            timpmediuservice += tservice;
            clienti.add(new Client(0, tarrive, tservice));
        }
        timpmediuservice = timpmediuservice / i.getClienti();
        int i = 1;
        Collections.sort(clienti);
        for (Client client : clienti)
            client.setId(i++);
    }

    public int check() {
        int n = 0;
        if (clienti.isEmpty()) {
            for (Coada coada: orar.getCozi())
                if (!coada.getStatus())
                    n++;
        }
        return n;
    }
//timpul de simulare trebuie sa ajunga la timpul de simulare dat de utilizator
    //o data cu trecerea timpului cand timpul de simulare este egal cu timpul de simulare a unui client acesta va fi adaugat
    //la coada si va fi scos din lista de asteptare
    @Override
    public void run() {
        int simulationTime = 0, n;
        ArrayList<Client> clientsToRemove;
        while (simulationTime < i.getSimulare()) {
            clientsToRemove = new ArrayList<Client>();
            n = check();
            if (orar.getCozi().size() == n)
                break;
            for (int i = 0; i < this.i.getCozi(); i++)
                orar.getCozi().get(i).run();
            for (Client client : clienti) {
                if (client.gettimeArrival() == simulationTime) {
                    orar.atribuire(client);
                    clientsToRemove.add(client);
                }
            }
            for (Client client : clientsToRemove)
                clienti.remove(client);
            fm.transformString(simulationTime, clienti, orar.getCozi());
            simulationTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        timpmediuasteptare = timpmediuasteptare / i.getClienti();
        fm.transformString(simulationTime, clienti, orar.getCozi());
        fm.afisareFisier();

    }
}
