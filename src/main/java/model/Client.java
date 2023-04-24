package model;

public class Client implements Comparable<Client>{
    private int id;             //cream clientul care va fi reprezentat de id, timpul de ajungere si timpul de servire
    private int timeArrival;
    private int timeService;

    public Client(int id, int tArrival, int tService){
        this.id=id;
        this.timeArrival=tArrival;
        this.timeService=tService;
    }

    public String toString(){

        return "("+id+", "+timeArrival+", "+timeService+");";//modul de afisare
    }
//set-ere si get-ere
    @Override
    public int compareTo(Client client) {

        return -client.gettimeArrival()+this.gettimeArrival();
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int gettimeArrival() {

        return timeArrival;
    }

    public void settimeArrival(int tArrival) {

        this.timeArrival = tArrival;
    }

    public int gettimeService() {

        return timeService;
    }

    public void settimeService(int tService) {

        this.timeService = tService;
    }
}
