package HDT6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Map implementation: 1) HashMap, 2) TreeMap, 3) LinkedHashMap");
        int choice = scanner.nextInt();
        MapFactory factory;

        switch (choice) {
            case 1:
                factory = new HashMapFactory();
                break;
            case 2:
                factory = new TreeMapFactory();
                break;
            case 3:
                factory = new LinkedHashMapFactory();
                break;
            default:
                System.out.println("Invalid choice, defaulting to HashMap.");
                factory = new HashMapFactory();
        }

        PokemonProgram program = new PokemonProgram(factory);
        while (true) {
            System.out.println("\n1) Add Pokémon to collection");
            System.out.println("2) Show Pokémon data");
            System.out.println("3) Show user's Pokémon by Type1");
            System.out.println("4) Show all Pokémon by Type1");
            System.out.println("5) Show Pokémon by ability");
            System.out.println("6) Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (option == 6) break;

            switch (option) {
                case 1:
                    System.out.print("Enter Pokémon name to add: ");
                    String nameToAdd = scanner.nextLine();
                    program.addPokemon(nameToAdd);
                    break;
                case 2:
                    System.out.print("Enter Pokémon name to show: ");
                    String nameToShow = scanner.nextLine();
                    program.showPokemonData(nameToShow);
                    break;
                case 3:
                    program.showUserPokemonByType();
                    break;
                case 4:
                    program.showAllPokemonByType();
                    break;
                case 5:
                    System.out.print("Enter ability to search: ");
                    String ability = scanner.nextLine();
                    program.showPokemonByAbility(ability);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}