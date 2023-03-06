package com.lab6.client;

import com.google.gson.Gson;
import com.lab6.client.entities.CollectionData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class UserInteraction {

    private final CollectionManagement collectionManagement = new CollectionManagement();
    private final Scanner in = new Scanner(System.in);
    Gson gson = new Gson();

    public void start(BufferedWriter outputBuffer, BufferedReader inputBuffer) {

        Queue<String> historyQueue = new ArrayDeque<>(11);


        //TODO: errors processing
        //Process null response from the server


        System.out.println("Type in the command:");
        String command = in.nextLine();
        while(!command.equals("exit")) {
            executeCommand(command, historyQueue, outputBuffer, inputBuffer);
            System.out.println("Type in the command:");
            command = in.nextLine();
        }
        in.close();

    }


    private void executeCommand(String command, Queue<String> historyQueue, BufferedWriter outputBuffer,BufferedReader inputBuffer) {
        switch (command) {
            case "info":
                historyUpdate(historyQueue, "info");
                printCollectionInfo(outputBuffer, inputBuffer);
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
                clearCollection(outputBuffer, inputBuffer);
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


    private void printCollectionInfo(BufferedWriter outputBuffer, BufferedReader inputBuffer) {
        CollectionData collectionData;
        try {
            outputBuffer.write("info");
            outputBuffer.flush();
            collectionData = gson.fromJson(inputBuffer.readLine(), CollectionData.class);
            System.out.println("Collection type: " + collectionData.getType());
            System.out.println("Collection initialisation date: " + new Date(collectionData.getInitialisationDate()));
            System.out.println("Collection size: " + collectionData.getSize());
            System.out.println("Is collection empty: " + collectionData.isEmpty());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clearCollection(BufferedWriter outputBuffer, BufferedReader inputBuffer) {
        try {
            outputBuffer.write("clear");
            outputBuffer.flush();
            String serverResponse = inputBuffer.readLine();
            if (serverResponse.equals("success")) {
                System.out.println("Collection's been cleared successfully!");
            } else {
                System.out.println("Collection hasn't been cleared. Try again later.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void printAddCommand() {

        MusicGenre genre;

        System.out.println("Print the name of the band you would like to add: ");
        String bandName = in.nextLine();
        System.out.println("Print the the coordinates of the band in a format {xx,xx.xx}: ");
        String[] coordinatesString = in.nextLine().split(",");
        int bandCoordinateX = Integer.parseInt(coordinatesString[0]);
        double bandCoordinateY = Double.parseDouble(coordinatesString[1]);
        System.out.println("Type in the number of participants of the band: ");
        int numberOfParticipants = Integer.parseInt(in.nextLine());
        System.out.println("Choose the genre of the musical band within ROCK, JAZZ, PUNK-ROCK or skip the question if neither of the answers are appropriate:");
        String userGenre = in.nextLine();
        if (userGenre.equals("ROCK")) {
            genre = MusicGenre.ROCK;
        } else if (userGenre.equals("JAZZ")) {
            genre = MusicGenre.JAZZ;
        } else if (userGenre.equals("PUNK-ROCK")) {
            genre = MusicGenre.PUNK_ROCK;
        } else {
            genre = null;
        }
        System.out.println("Type in the name of the studio:");
        String studioName = in.nextLine();

        collectionManagement.add(new MusicBand(collectionManagement.getCollection().size() + 1, bandName,
                new Coordinates(bandCoordinateX, bandCoordinateY), LocalDate.now(),
                numberOfParticipants, genre, new Studio(studioName)));

        System.out.println("Element successfully added!");
    }





}
