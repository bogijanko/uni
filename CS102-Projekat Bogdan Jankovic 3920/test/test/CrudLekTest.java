
package test;

import crud.CrudLek;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojo.Lek;

public class CrudLekTest {

    public CrudLekTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreate() {
        Lek l = new Lek("Amoksiklav", "Hemofarm", 125, 600, 25);
        boolean result = CrudLek.create(l);
        assertTrue(result);
    }

    @Test
    public void testUpdate() {
        Lek l = new Lek("Antibiotik", "Hemofarm", 10, 300, 35);
        CrudLek.create(l);
        List<Lek> lista = CrudLek.readAll();
        l = lista.get(lista.size() - 1);
        int id = l.getId();
        l.setNaziv("Panklav");
        CrudLek.update(l);
        String result = CrudLek.readById(id).getNaziv();
        assertEquals("Panklav", result);
    }

    @Test
    public void testDelete() {
        Lek l = new Lek("Antibiotik", "Hemofarm", 10, 300, 10);
        boolean result = CrudLek.delete(l);
        assertTrue(result);

    }

    @Test
    public void testReadAll() {
        List<Lek> lista = CrudLek.readAll();
        int expResult = 0;
        int result = lista.size();
        assertNotEquals(expResult, result);
    }

    @Test
    public void testReadById() {
        int id = 2;
        Lek l = CrudLek.readById(id);
        int result = l.getId();
        assertEquals(result, id);
    }
}
