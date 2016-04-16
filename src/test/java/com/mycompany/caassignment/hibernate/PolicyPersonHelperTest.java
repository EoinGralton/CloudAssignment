/**
 * 
 */
package com.mycompany.caassignment.hibernate;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Marius
 *
 */
public class PolicyPersonHelperTest {
	private static List<Policyperson> currentList;
	private static Policy pol1;

	/**
	 * @throws java.lang.Exception
	 */
	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MemberHelper mh = new MemberHelper();
		Member m1 = mh.getMemberFromEmail("test@test.com");
		Person pr1 = new Person(m1, m1.getId(), false, "Mr", "Joe", "Bloggs", new Date(90,4,2), new HashSet<Policy>());
		pol1 = new Policy(pr1, false, new Date(116,6,26), new Date(116,7,4), false, new HashSet<Policyperson>());
		pol1.setId(1);
		currentList = new ArrayList<Policyperson>();
		
		currentList.add(new Policyperson(pol1, "Mr", "Joe", "Bloggs", new Date(90,4,2)));
		currentList.get(0).setIdPolicyPerson(1);
		currentList.add(new Policyperson(pol1, "Miss", "Sarah", "Bloggs", new Date(88,1,23)));
		currentList.get(1).setIdPolicyPerson(2);
		
	}
	
	/**
	 * Check that a new Policyperson record is inserted
	 * Test method for {@link com.mycompany.caassignment.hibernate.PolicyPersonHelper#insertPolicyPerson(com.mycompany.caassignment.hibernate.Policyperson)}.
	 */
	@Test
	public void testInsertPolicyPerson() {
		PolicyPersonHelper helper = new PolicyPersonHelper();
		@SuppressWarnings("deprecation")
		Policyperson personToInsert = new Policyperson(pol1, "Mr", "Thomas", "Reed", new Date(75,6,14));
		boolean isInserted = helper.insertPolicyPerson(personToInsert);
		assertTrue(isInserted);
		
		Policyperson ppFromDB = helper.getPolicyPerson(1, personToInsert.getIdPolicyPerson());
		assertEquals(personToInsert.getFirstname(), ppFromDB.getFirstname());
		assertEquals(personToInsert.getDateofbirth(), ppFromDB.getDateofbirth());
	}

	/**
	 * Check that all the records from Policyperson table are returned
	 * Test method for {@link com.mycompany.caassignment.hibernate.PolicyPersonHelper#getPolicyPersonList(int)}.
	 */
	@Test
	public void testGetPolicyPersonList() {
		PolicyPersonHelper helper = new PolicyPersonHelper();
		List<Policyperson> listFromDB = helper.getPolicyPersonList(1);
		assertEquals(currentList.get(0).getIdPolicyPerson(), listFromDB.get(0).getIdPolicyPerson());
		assertEquals(currentList.get(1).getIdPolicyPerson(), listFromDB.get(1).getIdPolicyPerson());
	}

	/**
	 * Check that the correct Policyperson is succesfully retrieved
	 * Test method for {@link com.mycompany.caassignment.hibernate.PolicyPersonHelper#getPolicyPerson(int, int)}.
	 */
	@Test
	public void testGetPolicyPerson() {
		PolicyPersonHelper helper = new PolicyPersonHelper();
		Policyperson ppFromDB = helper.getPolicyPerson(1, 2);
		Policyperson ppCurrent = currentList.get(1);
		assertEquals(ppCurrent.getIdPolicyPerson(), ppFromDB.getIdPolicyPerson());
		assertEquals(ppCurrent.getDateofbirth(), ppFromDB.getDateofbirth());
		assertNotNull(ppFromDB.getPolicy().getId());
	}

	/**
	 * Check if Policyperson record is succesfully updated
	 * Test method for {@link com.mycompany.caassignment.hibernate.PolicyPersonHelper#updatePolicyPerson(com.mycompany.caassignment.hibernate.Policyperson)}.
	 */
	@Test
	public void testUpdatePolicyPerson() {
		PolicyPersonHelper helper = new PolicyPersonHelper();
		Policyperson ppFromDB = helper.getPolicyPerson(1, 3);
		String nameToSet = "Jonathan";
		ppFromDB.setFirstname(nameToSet);
		boolean isUpdated = helper.updatePolicyPerson(ppFromDB);
		assertTrue(isUpdated);
		
		ppFromDB = helper.getPolicyPerson(1, 3);
		assertEquals(nameToSet, ppFromDB.getFirstname());
	}
}