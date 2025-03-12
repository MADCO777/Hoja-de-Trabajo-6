package HDT6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PokemonProgram {
    private Map<String, Pokemon> pokemonMap;
    private Set<Pokemon> userCollection;

    public PokemonProgram(MapFactory factory) {
        this.pokemonMap = factory.createMap();
        this.userCollection = new HashSet<>();
        loadPokemonData("pokemon_data_pokeapi.csv");
    }

    private void loadPokemonData(String filename) {
        String line = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream("/HDT6/" + filename)))) {
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                // Use regex to split by comma, but ignore commas within quotes
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (data.length < 10) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                String name = data[0].trim();
                int pokedexNumber = Integer.parseInt(data[1].trim());
                String type1 = data[2].trim();
                String type2 = data[3].trim().equals("[null]") ? "None" : data[3].trim();
                String classification = data[4].trim();
                double height = Double.parseDouble(data[5].trim());
                double weight = Double.parseDouble(data[6].trim());
                List<String> abilities = Arrays.asList(data[7].replace("\"", "").split(";"));
                int generation = Integer.parseInt(data[8].trim());
                boolean legendary = data[9].trim().equals("true");

                Pokemon pokemon = new Pokemon(name, pokedexNumber, type1, type2, classification,
                        height, weight, abilities, generation, legendary);
                pokemonMap.put(name, pokemon);
                System.out.println("Loaded: " + name); // Debugging output
            }
            System.out.println("Total Pokémon loaded: " + pokemonMap.size());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format error: " + e.getMessage() + " in line: " + line);
        }
    }

    public void addPokemon(String name) {
        Pokemon pokemon = pokemonMap.get(name);
        if (pokemon == null) {
            System.out.println("Pokémon " + name + " does not exist.");
        } else if (userCollection.contains(pokemon)) {
            System.out.println("Pokémon " + name + " is already in your collection.");
        } else {
            userCollection.add(pokemon);
            System.out.println(name + " has been added to your collection.");
        }
    }

    public void showPokemonData(String name) {
        Pokemon pokemon = pokemonMap.get(name);
        if (pokemon == null) {
            System.out.println("Pokémon " + name + " does not exist.");
        } else {
            System.out.println(pokemon);
        }
    }

    public void showUserPokemonByType() {
        if (userCollection.isEmpty()) {
            System.out.println("Your collection is empty.");
            return;
        }
        List<Pokemon> sortedPokemon = new ArrayList<>(userCollection);
        Collections.sort(sortedPokemon, (p1, p2) -> p1.getType1().compareTo(p2.getType1()));
        for (Pokemon pokemon : sortedPokemon) {
            System.out.println("Name: " + pokemon.getName() + ", Type1: " + pokemon.getType1());
        }
    }

    public void showAllPokemonByType() {
        List<Pokemon> sortedPokemon = new ArrayList<>(pokemonMap.values());
        Collections.sort(sortedPokemon, (p1, p2) -> p1.getType1().compareTo(p2.getType1()));
        for (Pokemon pokemon : sortedPokemon) {
            System.out.println("Name: " + pokemon.getName() + ", Type1: " + pokemon.getType1());
        }
    }

    public void showPokemonByAbility(String ability) {
        List<Pokemon> matchingPokemon = new ArrayList<>();
        for (Pokemon pokemon : pokemonMap.values()) {
            if (pokemon.getAbilities().contains(ability)) {
                matchingPokemon.add(pokemon);
            }
        }
        if (matchingPokemon.isEmpty()) {
            System.out.println("No Pokémon found with ability: " + ability);
        } else {
            for (Pokemon pokemon : matchingPokemon) {
                System.out.println(pokemon.getName());
            }
        }
    }
}