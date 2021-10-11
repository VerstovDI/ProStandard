package ru.Aidar.ParsingProffStandards;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        Thread thread3 = new Thread1();

        thread1.start();
        thread2.start();
        thread3.start();
    }


}
