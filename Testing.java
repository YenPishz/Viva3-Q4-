/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package museum;

/**
 *
 * @author YenPishz
 */
public class Testing {
    public static void main(String[] args) {
        Museum museum = new Museum();
        museum.loadExhibits("src/Exhibits.txt");
        
        museum.searchExhibits(); 

        System.out.println("All exhibits:");
        museum.viewAllExhibits();

        System.out.println("\nSearch exhibits by artist 'Vincent van Gogh':");
        museum.searchExhibitsByArtist("Vincent van Gogh");

        System.out.println("\nSearch exhibits by type 'Painting':");
        museum.searchExhibitsByType("Painting");

        System.out.println("\nSearch exhibits by year '1504':");
        museum.searchExhibitsByYear(1504);
    }
}