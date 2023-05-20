package org.example;

import javax.swing.*;

public class MiHiloRunnable implements Runnable {
    private int numHilo;
    private JTextArea textArea;

    public MiHiloRunnable(JTextArea textArea, int n) {
        this.textArea = textArea;
        numHilo = n;
    }
    public void run() {
        textArea.append("Hilo " + numHilo + " ejecutándose...\n");
        textArea.append("Hilo " + numHilo + " detenido...\n");
    }
}
