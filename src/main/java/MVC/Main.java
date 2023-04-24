package MVC;

import view.Afisare;
import view.Controller;
import view.Interfata;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Interfata i=new Interfata();
        Controller c=new Controller(i);

    }}
