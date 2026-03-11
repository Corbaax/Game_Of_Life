import Game_Of_Life.Campo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static Game_Of_Life.Status.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CampoTest {
    Campo c;
    @BeforeEach
    void bfEach(){
        c = new Campo(10, 10);
    }

    @Test
    void testCreation(){
        c.makeLife(0,0);
        assertThat(
                c.cellAt(0,0),
                is(equalTo(Alive)));

        assertThat(
                c.cellAt(0,1),
                is(equalTo(Dead))
        );
    }

    @Test
    void testTickRule0(){
        c.makeLife(0,0);
        c.makeLife(0,1);

        c.makeLife(5,5);

        assertThat(c.cellAt(0,0), is(equalTo(Alive)));
        c.tick();
        assertThat(c.cellAt(0,0), is(equalTo(Dead)));
        assertThat(c.cellAt(0,1), is(equalTo(Dead)));
        assertThat(c.cellAt(5,5), is(equalTo(Dead)));
    }

    @Test
    void testTickRule1(){
        c.makeLife(0,0);
        c.makeLife(0,1);
        c.makeLife(1,0);

        c.makeLife(5,5);
        c.makeLife(5,6);
        c.makeLife(6,5);
        c.makeLife(6,6);

        c.tick();
        assertThat(c.cellAt(0,0), is(equalTo(Alive)));
        assertThat(c.cellAt(0,1), is(equalTo(Alive)));
        assertThat(c.cellAt(1,0), is(equalTo(Alive)));
        assertThat(c.cellAt(5,5), is(equalTo(Alive)));
        assertThat(c.cellAt(5,6), is(equalTo(Alive)));
        assertThat(c.cellAt(6,5), is(equalTo(Alive)));
        assertThat(c.cellAt(6,6), is(equalTo(Alive)));
    }

    @Test
    void testTickRule2(){
        c.makeLife(0,0);
        c.makeLife(0,1);
        c.makeLife(1,0);
        c.makeLife(0,1);
        c.makeLife(2,1);

        c.tick();

        assertThat(c.cellAt(0,0), is(equalTo(Alive)));
        assertThat(c.cellAt(0,1), is(equalTo(Alive)));
        assertThat(c.cellAt(1,0), is(equalTo(Dead)));
        assertThat(c.cellAt(1,1), is(equalTo(Dead)));
        assertThat(c.cellAt(2,1), is(equalTo(Alive)));
    }

    @Test
    void testTickRule3(){
        c.makeLife(0,0);
        c.makeLife(0,1);
        c.makeLife(1,0);
        c.makeLife(2,0);

        c.makeLife(5,5);
        c.makeLife(5,6);
        c.makeLife(6,5);

        c.tick();

        assertThat(c.cellAt(1,1), is(equalTo(Dead)));
        assertThat(c.cellAt(6,6), is(equalTo(Alive)));
    }


}
