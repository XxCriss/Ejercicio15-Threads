package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame {
    private JPanel panel1;
    private JTextArea textArea1;
    private JButton ejecutarHilosButton;
    private JButton detenerHilosButton;
    private JButton iniciarBarrasButton;
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private MiHilo miHilo1, miHilo2, miHilo3;
    private Thread hiloAnonimo, hiloAnonimoBarras, thread;
    private Runnable miHiloRunnable1, miHiloRunnable2, miHiloRunnable3;

    public Ventana() {
        super("Threads");
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ejecutarHilosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Hilos
                textArea1.setText("");
                miHilo1 = new MiHilo(textArea1, 1);
                System.out.println("Estado miHilo1: " + miHilo1.getState());
                miHilo1.start();
                miHilo2 = new MiHilo(textArea1, 2);
                miHilo2.start();
                miHilo3 = new MiHilo(textArea1, 3);
                miHilo3.start();

                // Hilos anónimos
                hiloAnonimo = new Thread() {
                    public void run() {
                        System.out.println("Estado hilo anónimo 1: " + hiloAnonimo.getState());
                        textArea1.append("Hilo anónimo 1 ejecutándose...\n");
                        System.out.println("Estado hilo anónimo 1: " + hiloAnonimo.getState());
                    }
                };
                hiloAnonimo.start();
                hiloAnonimo = new Thread() {
                    public void run() {
                        textArea1.append("Hilo anónimo 2 ejecutándose...\n");
                    }
                };
                hiloAnonimo.start();
                hiloAnonimo = new Thread() {
                    public void run() {
                        textArea1.append("Hilo anónimo 3 ejecutándose...\n");
                    }
                };
                hiloAnonimo.start();

                // Hilos con la interfaz Runnable
                miHiloRunnable1 = new MiHiloRunnable(textArea1, 4);
                thread = new Thread(miHiloRunnable1);
                thread.start();
                miHiloRunnable2 = new MiHiloRunnable(textArea1, 5);
                thread = new Thread(miHiloRunnable2);
                thread.start();
                miHiloRunnable3 = new MiHiloRunnable(textArea1, 6);
                thread = new Thread(miHiloRunnable3);
                thread.start();

                // Hilos anónimos con la interfaz Runnable
                thread = new Thread(new Runnable() {
                    public void run() {
                        textArea1.append("Hilo anónimo 4 ejecutándose...\n");
                    }
                });
                thread.start();
                thread = new Thread(new Runnable() {
                    public void run() {
                        textArea1.append("Hilo anónimo 5 ejecutándose...\n");
                    }
                });
                thread.start();
                thread = new Thread(new Runnable() {
                    public void run() {
                        textArea1.append("Hilo anónimo 6 ejecutándose...\n");
                    }
                });
                thread.start();
            }
        });

        detenerHilosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(miHilo1.isAlive()) {
                    miHilo1.interrupt();
                }
                else if(miHilo2.isAlive()) {
                    miHilo2.interrupt();
                }
                if(miHilo3.isAlive()) {
                    miHilo3.interrupt();
                }
            }
        });


        iniciarBarrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hiloAnonimoBarras = new Thread() {
                    public void run() {
                        for(int i=0; i <= 100; i++) {
                            try {
                                progressBar1.setString(i + " %");
                                progressBar1.setValue(i);
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                hiloAnonimoBarras.start();
                hiloAnonimoBarras = new Thread() {
                    public void run() {
                        for(int i=0; i <= 100; i++) {
                            try {
                                progressBar2.setString(i + " %");
                                progressBar2.setValue(i);
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                hiloAnonimoBarras.start();
                hiloAnonimoBarras = new Thread() {
                    public void run() {
                        for(int i=0; i <= 100; i++) {
                            try {
                                progressBar3.setString(i + " %");
                                progressBar3.setValue(i);
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                hiloAnonimoBarras.start();
                iniciarBarrasButton.setEnabled(false);
                thread = new Thread(new Runnable() {
                    public void run() {
                        while(true) {
                            System.out.println("Estado hilo anónimo barras: " + hiloAnonimoBarras.getState());
                            if (!hiloAnonimoBarras.isAlive()) {
                                iniciarBarrasButton.setEnabled(true);
                                System.out.println("Estado hilo anónimo barras: " + hiloAnonimoBarras.getState());
                                break;
                            }
                        }
                    }
                });
                thread.start();
            }
        });
    }
}
