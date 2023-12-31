import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class PagibigQueuingSystem1 {

    public static void main(String[] args) {

        QueueSystem centralizedQueue = new QueueSystem();
        HelpDeskStation helpDesk1 = new HelpDeskStation(centralizedQueue);
        HelpDeskStation helpDesk2 = new HelpDeskStation(centralizedQueue);
        HelpDeskStation helpDesk3 = new HelpDeskStation(centralizedQueue);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Gerlyn Gaoat | 3BSCS-2\n");
            System.out.println("Pagibig Centrallized Queuing System\n");
            System.out.println("MENU:");
            System.out.println("1. Enqueue customer");
            System.out.println("2. Serve next customer");
            System.out.println("3. Reset queue");
            System.out.println("4. Display current queue");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter customer queue number: ");
                    try {
                        int customerNumber = scanner.nextInt();
                        centralizedQueue.enqueue(customerNumber);
                        System.out.println("Customer added to the queue.");
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // consume the invalid input
                    }
                    break;

                case 2:
                    helpDesk1.serveNextCustomer();
                    helpDesk2.serveNextCustomer();
                    helpDesk3.serveNextCustomer();
                    break;

                case 3:
                    System.out.print("Enter new starting number for the queue: ");
                    try {
                        int newStartingNumber = scanner.nextInt();
                        helpDesk1.resetQueue(newStartingNumber);
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // consume the invalid input
                    }
                    break;

                case 4:
                    centralizedQueue.displayQueue();
                    break;

                case 5:
                    System.out.println("Exiting the queuing system. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");

                    scanner.close();
            }
        }
    }
}


class QueueSystem {
    private Queue<Integer> queue = new LinkedList<>();

    public void enqueue(int number) {
        queue.add(number);
    }

    public int dequeue() {
        if (!isEmpty()) {
            return queue.poll();
        } else {
            throw new IllegalStateException("The queue is empty.");
        }
    }

    public int peek() {
        if (!isEmpty()) {
            return queue.peek();
        } else {
            throw new IllegalStateException("The queue is empty.");
        }
    }

    public void resetQueue(int newStartingNumber) {
        queue.clear();
        enqueue(newStartingNumber);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void displayQueue() {
        if (!isEmpty()) {
            System.out.println("Current Queue: " + queue);
        } else {
            System.out.println("The queue is empty.");
        }
    }
}

class HelpDeskStation {
    private QueueSystem queueSystem;

    public HelpDeskStation(QueueSystem queueSystem) {
        this.queueSystem = queueSystem;
    }

    public void serveNextCustomer() {
        try {
            int nextNumber = queueSystem.dequeue();
            System.out.println("Serving customer with queue number: " + nextNumber);
        } catch (IllegalStateException e) {
            System.out.println("No customers in the queue.");
        }
    }

    public void resetQueue(int newStartingNumber) {
        queueSystem.resetQueue(newStartingNumber);
        System.out.println("Queue has been reset. Next customer will receive queue number: " + newStartingNumber);


    }
}
