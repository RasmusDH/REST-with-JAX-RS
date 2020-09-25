package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import utils.EMF_Creator;
import entities.Address;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {
    
/*
    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person p1, p2;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = PersonFacade.getPersonFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Person("Rasmus", "Hansen", "12345678");
        p2 = new Person("Tumle", "dumle", "87654321");
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
                em.persist(p1);
                em.persist(p2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    
    

    @Test
    public void testGetPersonCount() {
        System.out.println("Tester getPersonCount");
        
        EntityManagerFactory _emf = null;
        PersonFacade pFac = PersonFacade.getPersonFacade(_emf);
        
        Long expResult = 2L;
        Long result = pFac.getPersonCount();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetAll() {
        System.out.println("Tester getAll");
        
        EntityManagerFactory _emf = null;
        PersonFacade pFac = PersonFacade.getPersonFacade(_emf);
        
        int expResult = 2;
        PersonsDTO result = pFac.getAllPersons();
        
        assertEquals(expResult, result.getAll().size());
    }
    
    @Test
    public void testGetAllContains() {
        System.out.println("Tester getAll contains");
        
        EntityManagerFactory _emf = null;
        PersonFacade pFac = PersonFacade.getPersonFacade(_emf);
        
        PersonsDTO result = pFac.getAllPersons();
        
        PersonDTO p1DTO = new PersonDTO(p1);
        PersonDTO p2DTO = new PersonDTO(p2);
        
        assertThat(result.getAll(), containsInAnyOrder(p1DTO, p2DTO));
    }
    
    

    @Test
    public void testDeletePerson() throws PersonNotFoundException {
        System.out.println("Tester deletePerson");
        
        long id = p2.getId();
        
        int p_id = (int) id;
        
        EntityManagerFactory _emf = null;
        PersonFacade pFac = PersonFacade.getPersonFacade(_emf);
        
        PersonDTO expResult = new PersonDTO(p2);
        PersonDTO result = pFac.deletePerson(p_id);
        
        assertEquals(expResult, result);
    }*/
}