package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leena
 */
public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hakuPalauttaaOikeanPelaajan() {
        Player pelaaja = stats.search("Kurri");
        assertEquals(pelaaja.getName(), "Kurri");
    }
    
    @Test
    public void hakuPalauttaaNullJosPelaajaaEiOle() {
        Player pelaaja = stats.search("Kalle");
        assertEquals(pelaaja, null);
    }
    
    @Test
    public void hakuPalauttaaOikeanMaaranPelaajia() {
        List pelaajat = stats.team("EDM");
        assertTrue(pelaajat.size() == (3));
    }
    
    @Test
    public void parhaatPelaajatPalauttaaParhaanPelaajan() {
        List paras = stats.topScorers(1);
        assertTrue(paras.get(0).toString().contains("Gretzky"));
    }
    
    @Test
    public void parhaatPelaajatPalauttaaOikeanMaaranParhaitaPelaajia() {
        List parhaat = stats.topScorers(1);
        assertTrue(parhaat.size() == 2);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
