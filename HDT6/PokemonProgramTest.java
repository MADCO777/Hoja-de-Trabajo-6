package HDT6;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PokemonProgramTest {
    private PokemonProgram program;

    @Before
    public void setUp() {
        program = new PokemonProgram(new HashMapFactory());
    }

    @Test
    public void testAddPokemon() {
        program.addPokemon("Bulbasaur");
        program.addPokemon("Bulbasaur"); // Should indicate duplicate
        program.addPokemon("InvalidPokemon"); // Should indicate not exist
    }

    @Test
    public void testShowPokemonData() {
        program.showPokemonData("Pikachu");
        program.showPokemonData("InvalidPokemon"); // Should indicate not exist
    }
}