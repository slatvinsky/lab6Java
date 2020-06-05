package xyz.dragonnest.saesentsessis;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Main prog = new Main();
        prog.run();
    }

    private void run() {
        Train[] trains = createEmptyTrainArray(100);
        userInput(trains, false, false);
    }

    private void userInput(Train[] trains, boolean hasArray, boolean stop) {
        if (stop) return;
        Scanner in = new Scanner(System.in);
        String choise; boolean b = false, t = false;
        StringBuilder mmsg = new StringBuilder("What do you wan't to do?\n- randomly fill train array (r)\n- fill train array from file (f)\n- fill train array from binary file (b)");
        if (hasArray) mmsg.append("\n- skip this step and work with array you have (s)");
        System.out.println(mmsg);
        while(true) {
            String input = in.nextLine();
            if (input.charAt(0) == 'r') {
                choise = "rand";
                break;
            } else if (input.charAt(0) == 'f') {
                choise = "file"; b = false;
                break;
            } else if (input.charAt(0) == 'b') {
                choise = "file"; b = true;
                break;
            } else if (input.charAt(0) == 's' && hasArray) {
                choise = "skip";
                break;
            } else {
                System.out.println("Invalid input! Try to write r/f/b!");
            }
        }
        String fName;
        switch (choise) {
            case "file": {
                System.out.println("Enter filename without .txt");
                while (true) {
                    String input = in.nextLine();
                    if (input.endsWith(".txt")) {
                        System.out.println("Invalid name! Try to write filename without .txt!");
                    } else {
                        fName = input;
                        break;
                    }
                }
                if (b) readFromFileBinary(trains, fName);
                else readFromFile(trains, fName);
                break;
            }
            case "rand": {
                fillTrainsArrRandomly(trains);
                break;
            }
            case "skip": {
                break;
            }
        }
        System.out.println("Ok!\n\nWhat to do next?\n- print all trains (a)\n- print all trains, sorted by way point (w)\n- print all trains, sorted by way point and time (t)\n- write in file (f)\n- write in file binary (b)");
        while (true) {
            String input = in.nextLine();
            if (input.charAt(0) == 'a') {
                choise = "printall";
                break;
            } else if (input.charAt(0) == 'w') {
                choise = "printwp"; t = false;
                break;
            } else if (input.charAt(0) == 't') {
                choise = "printwp"; t = true;
                break;
            } else if (input.charAt(0) == 'f') {
                choise = "file"; b = false;
                break;
            } else if (input.charAt(0) == 'b') {
                choise = "file"; b = true;
                break;
            } else {
                System.out.println("Invalid input! Try to write a/w/t/f/b!");
            }
        }
        switch (choise) {
            case "printall": {
               printTrains(trains);
               break;
            }
            case "printwp": {
                StringBuilder msg = new StringBuilder("Enter one city from that list to sort by it:\n");
                String[] cities = {"Moscow", "Paris", "Mykolaiv", "Tokyo", "New York", "London", "Oslo"};
                for (int i = 0; i < cities.length; i++) {
                    msg.append(cities[i]);
                    if (i != cities.length-1) {
                        msg.append(',');
                    }
                }
                System.out.println(msg);
                String wayP = "";
                while(true) {
                    String input = in.nextLine();
                    b = false;
                    for (int i = 0; i < cities.length; i++) {
                        if (cities[i].equals(input)) {
                            wayP = input;
                            b = true;
                            break;
                        }
                    }
                    if (b) break;
                    else System.out.println("Invalid city! Try to enter correct one!");
                } // print with time
                if (t) {
                    System.out.println("Enter arrive time in format HH:MM");
                    int hours, mins;
                    while(true) {
                        String input = in.nextLine();
                        if (input.charAt(2) == ':') {
                            hours = Integer.parseInt(input.substring(0, 1));
                            mins = Integer.parseInt(input.substring(3, 4));
                            break;
                        } else {
                            System.out.println("Invalid input! Try to enter data in format HH:MM");
                        }
                    }
                    printWayPointTimeTrains(trains, wayP, hours, mins);
                } else {
                    printWayPointTrains(trains, wayP);
                }
                break;
            }
            case "file": {
                System.out.println("Enter filename to export, without .txt");
                while (true) {
                    String input = in.nextLine();
                    if (input.endsWith(".txt")) {
                        System.out.println("Invalid name! Try to write filename without .txt!");
                    } else {
                        fName = input;
                        break;
                    }
                }
                if (b) writeInFileBinary(trains, fName);
                else writeInFile(trains, fName);
                break;
            }
        }
        System.out.println("Do you want to continue work? y/n");
        String input = in.nextLine();
        while (true) {
            if (input.charAt(0) == 'y') {
                userInput(trains, true, false);
                return;
            } else if (input.charAt(0) == 'n') {
                return;
            } else {
                System.out.println("Incorrect input! Try to write y/n");
            }
        }
    }

    private void printTrains(Train[] t) {
        for(int i = 0; i < t.length; i++) {
            System.out.println(t[i]);
        }
    }

    private void printWayPointTrains(Train[] t, String wayPointSort) {
        for(int i = 0; i < t.length; i++) {
            if(t[i].getWayP().equals(wayPointSort)) {
                System.out.println(t[i]);
            }
        }
    }

    private void printWayPointTimeTrains(Train[] t, String wayPointSort, int hours, int minutes) {
        for(int i = 0; i < t.length; i++) {
            if(t[i].getWayP().equals(wayPointSort)) {
                if (t[i].getHours() < hours) {
                    System.out.println(t[i]);
                    if(t[i].getHours() == hours){
                        if (t[i].getMinutes() > minutes) {
                            System.out.println(t[i]);
                        }
                    }
                }
            }
        }
    }

    private Train[] fillTrainsArr() {
        return new Train[]{
                new Train("Moscow",1,12,0, 50, 50, 12),
                new Train("Paris", 2, 14,30,50,50,12),
                new Train("Ukraine", 3, 22,0,100,120,0)
        };
    }

    private void fillTrainsArrRandomly(Train[] arrToFill) {
        Random rnd = new Random();
        String[] cities = {"Moscow", "Paris", "Mykolaiv", "Tokyo", "New York", "London", "Oslo"};
        for (int i = 0; i < arrToFill.length; i++) {
            arrToFill[i] = new Train(cities[rnd.nextInt(cities.length)], rnd.nextInt(24), rnd.nextInt(59), i,60+rnd.nextInt(100), 10+rnd.nextInt(50), rnd.nextInt(40) );
        }
    }

    private Train[] createEmptyTrainArray(int amount) {
        return new Train[amount];
    }

    private void readFromFile(Train[] arrToFill, String fileName) {
        try (Scanner in = new Scanner(new File(fileName+".txt"))) {
            for (int i = 0; in.hasNext(); i++) {
                String wp = in.nextLine().split("\n", 1)[0];
                int tnum = in.nextInt(), timeH = in.nextInt(), timeM = in.nextInt(), pKoupe = in.nextInt(), pPlatz = in.nextInt(), pLux = in.nextInt();
                arrToFill[i] = new Train(wp, tnum, timeH, timeM, pKoupe, pPlatz, pLux);
            }
        } catch (IOException ex) {
            System.err.println("Error reading file!");
        }
    }

    private void readFromFileBinary(Train[] arrToFill, String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName+".txt"))) {
            for (int i = 0; i < arrToFill.length; i++) {
                String wp = (String)in.readObject();
                int tnum = in.readInt(), timeH = in.readInt(), timeM = in.readInt(), pKoupe = in.readInt(), pPlatz = in.readInt(), pLux = in.readInt();
                arrToFill[i] = new Train(wp, tnum, timeH, timeM, pKoupe, pPlatz, pLux);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeInFile(Train[] arrToWrite, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"));) {
            for (int i = 0; i < arrToWrite.length; i++) {
                if (arrToWrite[i] == null) break;
                String str = arrToWrite[i].getData();
                writer.write(str);
            }
            writer.close();
        } catch (IOException ex) {
            System.err.println("Error writing in file!");
        }
    }

    private void writeInFileBinary(Train[] arrToWrite, String fileName) {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName+".txt"))){
            for (int i = 0; i < arrToWrite.length; i++) {
                if (arrToWrite[i] == null) break;
                String str = arrToWrite[i].getWayP();
                writer.writeObject(str);
                int num = arrToWrite[i].getTrNum(); int h = arrToWrite[i].getHours(); int m = arrToWrite[i].getMinutes(); int pk = arrToWrite[i].getpKoupe(); int pp = arrToWrite[i].getpPlatz(); int pl = arrToWrite[i].getpLux();
                writer.writeInt(num); writer.writeInt(h); writer.writeInt(m); writer.writeInt(pk); writer.writeInt(pp); writer.writeInt(pl);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
