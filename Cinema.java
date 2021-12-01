package cinema;


import java.util.Locale;
import java.util.Scanner;

public class Cinema {
    public static final int NUMBER_OF_SEATS_TRESHOLD = 60;
    public static final int FIRST_PRICE = 10;
    public static final int SECOND_PRICE = 8;
    public int[][] seats;
    int numberOfRows;
    int numberOfSeatsInRow;
    Scanner scanner;
    int numberOfPurchasedTickets, currentIncome, totalIncome;
    float percentage;

    public Cinema() {
        this.scanner = new Scanner(System.in);
        this.numberOfPurchasedTickets = 0;
        this.percentage = 0.00f;
        this.currentIncome = 0;
        this.totalIncome = 0;

    }

    public static void main(String[] args) {
        // Write your code here
        String menuString = "1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit\n";

        Cinema cinema = new Cinema();
        cinema.numberOfRows = cinema.getInput("Enter the number of rows:");
        cinema.numberOfSeatsInRow = cinema.getInput("Enter the number of seats in each row:");
        cinema.fillTheSeats();
        cinema.showSeats();

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
                case 3:
                    cinema.showStatistics();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Illegal choice");
            }


        } while (choise != 0);
    }

    public void showStatistics() {
        String statiticsInfo = "Number of purchased tickets: %d%n" +
                "Percentage: %.2f%%%n" +
                "Current income: $%d%n" +
                "Total income: $%d%n";
        System.out.printf(Locale.US, statiticsInfo, numberOfPurchasedTickets, percentage, currentIncome, totalIncome);
        System.out.println();
    }

    public void showSeats() {
        System.out.println("Cinema:");
        StringBuilder firstRow = new StringBuilder();
        for (int j = 1; j < numberOfSeatsInRow + 1; j++) {
            firstRow.append(j);
            firstRow.append(" ");
        }

        System.out.println(firstRow);
        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                System.out.print(seats[i][j] == 0 ? "S " : "B ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void fillTheSeats() {
        seats = new int[numberOfRows][numberOfSeatsInRow];
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeatsInRow; j++) {
                seats[i][j] = 0;
            }
        }
        totalIncome = getTotalIncome();
    }

    public void buyATicket() {

        int rowNumber;
        int seatNumber;
        boolean error;

        do {
            error = false;
            rowNumber = getInput("Enter a row number:");

            seatNumber = getInput("Enter a seat number in that row:");

            if (seatNumber < 0 || seatNumber > numberOfSeatsInRow || rowNumber < 0 || rowNumber > numberOfRows) {
                System.out.println("Wrong input!");
                error = true;
                continue;
            }

            if (seats[rowNumber - 1][seatNumber - 1] > 0) {
                System.out.println("That ticket has already been purchased!");
                error = true;
            }

        } while (error);

        seats[rowNumber - 1][seatNumber - 1] = 1;
        int price = getTicketPrice(rowNumber);
        currentIncome += price;
        numberOfPurchasedTickets++;
        percentage = 100 * (float) numberOfPurchasedTickets / (float) (numberOfSeatsInRow * numberOfSeatsInRow);
        System.out.println("percentage " + percentage);
        System.out.println("Ticket price: $" + price);
    }

    int getTicketPrice(int rowNumber) {

        int totalSeats = numberOfRows * numberOfSeatsInRow;

        if (totalSeats <= NUMBER_OF_SEATS_TRESHOLD) {
            return FIRST_PRICE;
        } else {
            int firstHalf = numberOfRows / 2;
            int secondHalf = numberOfRows - firstHalf;
            return (rowNumber <= firstHalf) ? FIRST_PRICE : SECOND_PRICE;
        }
    }

    public int getInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }

    public int getTotalIncome() {
        int totalSeats = numberOfRows * numberOfSeatsInRow;

        if (totalSeats <= NUMBER_OF_SEATS_TRESHOLD) {
            return FIRST_PRICE * totalSeats;

        } else {
            int firstHalf = numberOfRows / 2;
            int secondHalf = numberOfRows - firstHalf;
            return FIRST_PRICE * firstHalf * numberOfSeatsInRow + SECOND_PRICE * secondHalf * numberOfSeatsInRow;

        }
    }
}