package ru.Aidar.ParsingProffStandards;

import java.nio.file.Paths;

import static ru.Aidar.ParsingProffStandards.Main.clearOldData;

public class App {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();
        Thread thread3 = new Thread3();

        thread1.start();
        thread2.start();
        thread3.start();
        String dirToSave = Paths.get("resources").toAbsolutePath().toString();
        clearOldData(dirToSave);


    }


}
