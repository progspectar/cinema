package cinema;


import java.util.Scanner;

public class Cinema {
    static final String strRow = "S S S S S S S S";
    int numberOfRows;
    int numberOfSeatsInRow;
    Scanner scanner;
    public int [] [] seats;

    public static void main(String[] args) {
        // Write your code here
        String menuString = "1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "0. Exit\n";

        Cinema cinema = new Cinema();
        cinema.numberOfRows = cinema.getInput("Enter the number of rows:");
        cinema.numberOfSeatsInRow = cinema.getInput("Enter the number of seats in each row:");
        cinema.fillTheSeats();


        int choise;

        do {
            choise = cinema.getInput(menuString);
            switch (choise) {
                case 1:
                    cinema.showSeats();
                    break;
                case 2:
                    cinema.buyATicket();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Illegal choice");
            }


        } while (choise != 0);

    }

    public Cinema() {
        this.scanner = new Scanner(System.in);
    }


    public void showSeats() {

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                System.out.print(seats[i][j] == 0 ? "S " : "B ");
            }
            System.out.println();

        }

    }

    public void fillTheSeats() {

        seats = new int[numberOfRows][numberOfSeatsInRow];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                seats[i] [j]  = 0;
            }


        }
    }

    public void buyATicket() {

    }


    public int getInput(String prompt) {
        System.out.println(prompt);
        int value = scanner.nextInt();
        return value;
    }

    public int getTotalIncome(int numberOfRows, int numberOfSeatsInRow) {
        int totalSeats = numberOfRows * numberOfSeatsInRow;

        if (totalSeats <= 60) {
            return 10 * totalSeats;

        } else {
            int firstHalf = numberOfRows / 2;
            int secondHalf = numberOfRows - firstHalf;
            return 10 * firstHalf * numberOfSeatsInRow + 8 * secondHalf * numberOfSeatsInRow;

        }


    }

}