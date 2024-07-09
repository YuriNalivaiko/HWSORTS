import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class AviaSoulsTest {

    private AviaSouls aviasouls;

    @BeforeEach
    public void setup() {
        aviasouls = new AviaSouls();
        aviasouls.add(new Ticket("MOW", "NYC", 1000, 800, 1200));
        aviasouls.add(new Ticket("MOW", "NYC", 1500, 900, 1300));
        aviasouls.add(new Ticket("MOW", "PAR", 2000, 700, 1100));
        aviasouls.add(new Ticket("MOW", "NYC", 800, 600, 1000));
    }

    @Test
    public void testSearchAndSortByPrice() {
        Ticket[] tickets = aviasouls.search("MOW", "NYC");
        Arrays.sort(tickets, (t1, t2) -> Double.compare(t1.getPrice(), t2.getPrice()));

        Assertions.assertEquals(3, tickets.length);
        Assertions.assertArrayEquals(new Ticket[]{
                new Ticket("MOW", "NYC", 800, 600, 1000),
                new Ticket("MOW", "NYC", 1000, 800, 1200),
                new Ticket("MOW", "NYC", 1500, 900, 1300)
        }, tickets);
    }

    @Test
    public void testSearchAndSortByTime() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] tickets = aviasouls.searchAndSortBy("MOW", "NYC", comparator);

        Assertions.assertEquals(3, tickets.length);
        Arrays.sort(tickets, comparator);
        Assertions.assertArrayEquals(new Ticket[]{
                new Ticket("MOW", "NYC", 800, 600, 1000),
                new Ticket("MOW", "NYC", 1000, 800, 1200),
                new Ticket("MOW", "NYC", 1500, 900, 1300)
        }, tickets);
    }
}
