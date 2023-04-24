package model;

import orar.Simulare;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Coada implements Runnable {
    private BlockingQueue<Client> clienti;
    private AtomicInteger wait;
    private boolean status;
    //statusul reprezinta daca este inchisa sau deschisa
    //prin timpul de asteptare ne referim daca o coada este libera sau nu
    //fiecarei cozi i se atribuie o lista de clienti

    public Coada() {
        this.clienti=new LinkedBlockingDeque<Client>();
        this.wait = new AtomicInteger();
    }

    public void adaugareClient(Client c) {
        this.clienti.add(c);
        this.wait.addAndGet(c.gettimeService());
        Simulare.timpmediuasteptare=+this.wait.intValue();
    }

    public void run() {
        //modul in care sunt impartiti clientii la coada
        //se foloseaste o stiva de unde sunt luati clientii
        //daca timpul de servire scade sub unu clientul este scos din stiva, iar statusul cozii se schimba
        if (!clienti.isEmpty()) {
            Client client = clienti.peek();
            if (client != null) {
                int serviceTime = client.gettimeService();
                if (serviceTime > 0) {
                    clienti.peek().settimeService(serviceTime-1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (serviceTime == 1) {
                    clienti.remove();
                    for (int i = 0; i < client.gettimeService(); i++) {
                        this.wait.getAndDecrement();
                    }
                }
                if (clienti.isEmpty()) {
                    this.status = false;
                }
            }
        }
    }

    public String toString() {
        String s = "";
        for (Client client : clienti) {
            s = s + client.toString();
        }
        return s;
    }
    //set-ere si get-ere
    public BlockingQueue<Client> getClienti() {
        return clienti;
    }

    public void setClienti(BlockingQueue<Client> clienti) {
        this.clienti = clienti;
    }

    public AtomicInteger getwait() {
        return wait;
    }

    public void setwait(AtomicInteger wait) {
        this.wait = wait;
    }

    public void setStatus(boolean x) {
        this.status = x;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void schimbareStatus() {
        if (this.status)
            this.status = false;
        else
            this.status = true;
    }
}
