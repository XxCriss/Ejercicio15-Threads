package org.example;

import javax.swing.*;

public class MiHilo extends Thread {
    private int numHilo;
    private JTextArea textArea;

    public MiHilo(JTextArea textArea, int n) {
        this.textArea = textArea;
        numHilo = n;
    }
    public void run() {
        if(numHilo == 1 || numHilo == 2) {
            textArea.append("Hilo " + numHilo + " ejecutándose...\n");
            textArea.append("Hilo " + numHilo + " detenido...\n");
        }
        else if(numHilo == 3) {
            int i = 1;
            while(true) {
                // Realizar alguna tarea
                textArea.append("Ejecutando tarea en segundo plano...\n");
                textArea.append("Hilo " + numHilo + " ejecutándose... " + i + " veces\n");

                try {
                    // Dormir el hilo por un tiempo
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Manejar la interrupción
                    textArea.append("Hilo " + numHilo + " interrumpido\n");
                    break;
                }
                i++;
            }

            // Liberar recursos y finalizar el hilo
            textArea.append("Hilo " + numHilo + " detenido\n");
        }
    }
}
