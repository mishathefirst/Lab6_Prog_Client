package com.lab6.client;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;

public class UserInteraction {

    private final CollectionManagement collectionManagement = new CollectionManagement();
    private final Scanner in = new Scanner(System.in);

    public void start() {

        Queue<String> historyQueue = new ArrayDeque<>(11);


        //TODO: errors processing
        //Process null response from the server


        System.out.println("Type in the command:");
        String command = in.nextLine();
        while(!command.equals("exit")) {
            executeCommand(command, historyQueue);
            System.out.println("Type in the command:");
            command = in.nextLine();
        }
        in.close();

    }


    private void executeCommand(String command, Queue<String> historyQueue) {
        switch (command) {
            case "info":
                historyUpdate(historyQueue, "info");
                printCollectionInfo(collectionManagement.info());
                break;
            case "show":
                historyUpdate(historyQueue, "show");
                System.out.println(collectionManagement.show());
                break;
            case "help":
                historyUpdate(historyQueue, "help");
                printHelpCommand();
                break;
            case "add":
                historyUpdate(historyQueue, "add");
                printAddCommand();
                break;
            case "clear":
                historyUpdate(historyQueue, "clear");
                clearCollection();
                break;
            case "execute_script":
                historyUpdate(historyQueue, "execute_script");
                System.out.println("Type in the name of the file with the script:");
                String scriptFileName = in.nextLine();
                executeScript(scriptFileName, historyQueue);
                break;
            case "history":
                historyUpdate(historyQueue, "history");
                for (int i = 0; i < historyQueue.toArray().length; i++) {
                    System.out.println(historyQueue.toArray()[i]);
                }
                break;
            default:
                System.out.println("Command not found. Type \"help\" to get information on an interaction with the program");
                break;
        }
    }

    private void printHelpCommand() {
        System.out.println("\"help\" - show info on the interaction with the program");
        System.out.println("\"show\" - show all elements of the collection");
        System.out.println("\"info\" - show info on the collection");
        System.out.println("\"add\" - add an element to the collection");
        System.out.println("\"update_id {element's number}\" - update an element of the collection by its id");
        System.out.println("\"remove_by_id {element's number}\" - remove an element from the collection by its id");
        System.out.println("\"clear\" - clear the collection");
        System.out.println("\"save\" - save changes into the file");
        System.out.println("\"execute_script\" - execute a script written in the same format as commands to the prgram");
        System.out.println("\"exit\" - leave the program without saving changes");
        System.out.println("\"history\" - show last 11 commands");
        System.out.println("\"add_if_min\" - add an element if it is the smallest in the collection");
        System.out.println("\"remove_lower\" - delete all elements from the collection that are lower than the current one.");
    }

    private void historyUpdate(Queue<String> historyQueue, String command) {
        historyQueue.add(command);
        if (historyQueue.size() > 11) {
            historyQueue.poll();
        }
    }

}
