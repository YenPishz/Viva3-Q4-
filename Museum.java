package museum;

import java.io.*;
import java.util.*;

public class Museum {
    private List<Exhibit> exhibits;
    private Scanner scanner = new Scanner(System.in);

    public Museum() {
        exhibits = new ArrayList<>();
    }

    // Load exhibits from the file and populate the list
    public void loadExhibits(String filename) {
        exhibits.clear();  // Clear previous data to prevent duplication on reload

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(";", 5);
                if (details.length == 5) {
                    try {
                        String title = details[0].trim();
                        String artist = details[1].trim();
                        int year = Integer.parseInt(details[2].trim());
                        String type = details[3].trim();
                        String description = details[4].trim();

                        Exhibit exhibit = new Exhibit(title, artist, year, type, description);
                        exhibits.add(exhibit);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping invalid year format in line: " + line);
                    }
                } else {
                    System.out.println("Skipping invalid data format in line: " + line);
                }
            }
            System.out.println("Exhibits loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Display all exhibits
    public void viewAllExhibits() {
        if (exhibits.isEmpty()) {
            System.out.println("No exhibits available.");
        } else {
            for (Exhibit exhibit : exhibits) {
                System.out.println(exhibit.getDetails());
            }
        }
    }

    // Interactive search menu for users
    public void searchExhibits() {
        boolean running = true;

        while (running) {
            System.out.println("\n====== Exhibit Search Menu ======");
            System.out.println("1. Search by Title");
            System.out.println("2. Search by Artist");
            System.out.println("3. Search by Year");
            System.out.println("4. Search by Type");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("\nEnter title: ");
                    String title = scanner.nextLine();
                    searchExhibitsByTitle(title);
                    break;
                case 2:
                    System.out.print("\nEnter artist: ");
                    String artist = scanner.nextLine();
                    searchExhibitsByArtist(artist);
                    break;
                case 3:
                    System.out.print("\nEnter year: ");
                    int year = scanner.nextInt();
                    searchExhibitsByYear(year);
                    break;
                case 4:
                    System.out.print("\nEnter type (e.g., Painting, Sculpture): ");
                    String type = scanner.nextLine();
                    searchExhibitsByType(type);
                    break;
                case 5:
                    System.out.println("Returning to main menu...\n");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Search by Title
    public void searchExhibitsByTitle(String title) {
        List<Exhibit> results = new ArrayList<>();
        for (Exhibit exhibit : exhibits) {
            if (exhibit.getDetails().toLowerCase().contains(title.toLowerCase())) {
                results.add(exhibit);
            }
        }
        displaySearchResults(results, "Title", title);
    }

    // Search by Artist
    public void searchExhibitsByArtist(String artist) {
        List<Exhibit> results = new ArrayList<>();
        for (Exhibit exhibit : exhibits) {
            if (exhibit.getArtist().equalsIgnoreCase(artist)) {
                results.add(exhibit);
            }
        }
        displaySearchResults(results, "Artist", artist);
    }

    // Search by Year
    public void searchExhibitsByYear(int year) {
        List<Exhibit> results = new ArrayList<>();
        for (Exhibit exhibit : exhibits) {
            if (exhibit.getYear() == year) {
                results.add(exhibit);
            }
        }
        displaySearchResults(results, "Year", String.valueOf(year));
    }

    // Search by Type
    public void searchExhibitsByType(String type) {
        List<Exhibit> results = new ArrayList<>();
        for (Exhibit exhibit : exhibits) {
            if (exhibit.getType().equalsIgnoreCase(type)) {
                results.add(exhibit);
            }
        }
        displaySearchResults(results, "Type", type);
    }

    // Display formatted search results or "not found" message
    private void displaySearchResults(List<Exhibit> results, String criteria, String value) {
        if (results.isEmpty()) {
            System.out.println("No exhibits found for " + criteria + ": " + value);
        } else {
            System.out.println("Exhibits found for " + criteria + ": " + value);
            for (Exhibit exhibit : results) {
                System.out.println(exhibit.getDetails());
            }
        }
    }
}
