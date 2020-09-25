/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Person;
import exceptions.MissingInputException;
import exceptions.PersonNotFoundException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rasmus
 */
public class PersonFacade implements IPersonFacade {
    
    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getPersonCount(){
        EntityManager em = getEntityManager();
        try {
            long personCount = (long) em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return personCount;
        } finally{  
            em.close();
        } 
    }
    
    

    @Override
    public PersonDTO addPerson(String fName, String lName, String phone) throws MissingInputException {
        if ((fName.length() == 0) || (lName.length() == 0)){
           throw new MissingInputException("First Name and/or Last Name is missing"); 
        }
        EntityManager em = getEntityManager();
        Date now = new Date();
        Person p = new Person(fName, lName, phone, now, new Date());
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(p);
    }

    @Override
    public PersonDTO deletePerson(int intId) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        
        Long id = Long.valueOf(intId);
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new PersonNotFoundException(String.format("Person with id: (%d) not found", id));
        } else {
        try {
            em.getTransaction().begin();
                em.remove(person);
            em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new PersonDTO(person);
        }
    }

    @Override
    public PersonDTO getPerson(int id) throws PersonNotFoundException {
        EntityManager em = getEntityManager();
        try {
            Person person = em.find(Person.class, id);
            if (person == null) {
                throw new PersonNotFoundException(String.format("No person with provided id found", id));
            } else {
                return new PersonDTO(person);
            }
        } finally {
           em.close();
        }
    }

    @Override
    public PersonsDTO getAllPersons() {
        EntityManager em = getEntityManager();
        try {
            return new PersonsDTO(em.createNamedQuery("Person.getAllRows").getResultList());
            
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) throws PersonNotFoundException, MissingInputException {
        if ((p.getfName().length() == 0) || (p.getlName().length() == 0)){
           throw new MissingInputException("First Name and/or Last Name is missing"); 
        } 
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, p.getId());
        if (person == null) {
                throw new PersonNotFoundException(String.format("No person with provided id found", p.getId()));
        } else {
        try {
            em.getTransaction().begin();
                    person.setFirstName(p.getfName());
                    person.setLastName(p.getlName());
                    person.setPhone(p.getPhone());
                    person.setLastEdited();

                em.getTransaction().commit();
                return new PersonDTO(person);
        
        } finally {  
          em.close();
        }
    }
    
    
    }
}
