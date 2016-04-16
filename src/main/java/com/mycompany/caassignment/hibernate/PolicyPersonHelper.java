/**
 * 
 */
package com.mycompany.caassignment.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Methods to manipulate the PolicyPerson table in the database
 * 
 * @author Marius
 */
public class PolicyPersonHelper {

	private Session currentSession = null;
	
	public PolicyPersonHelper(){
		currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	/**
	 * Used to return the current session or create a new one if null 
	 * 
	 * @return returns current session
	 */
	private Session getSession(){
		if (currentSession == null || !currentSession.isOpen()) {
			currentSession = HibernateUtil.getSessionFactory().getCurrentSession();
        }
        return currentSession;
	}
	
	/**
	 * Inserts PolicyPerson to database 
	 * 
	 * @param p1 PolicyPerson to be inserted to database
	 * @return true/false to confirm if the PolicyPerson was inserted
	 */
	public boolean insertPolicyPerson(Policyperson p1){
		Transaction tr = getSession().beginTransaction();
        try {
            getSession().save(p1);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
            	tr.rollback();
            }
            e.printStackTrace();
            return false;
        }
        return true;
	}
	
	/**
	 * Gets every PolicyPerson for a specific policy from the database
	 * 
	 * @param policyID the policyID for which every PolicyPerson should be retrieved
	 * @return every PolicyPerson for the specified policy as a List
	 */
	@SuppressWarnings("unchecked")
	public List<Policyperson> getPolicyPersonList(int policyID){
		List<Policyperson> policyPersonList = null;
        org.hibernate.Transaction tr = getSession().beginTransaction();
        Query qr = getSession().createQuery("from Policyperson where idpolicy='" + policyID + "'");
        if (qr != null) {
        	policyPersonList = qr.list();
        }
        tr.commit();
        return policyPersonList;
	}

	/**
	 * Gets a single PolicyPerson from the database
	 * 
	 * @param policyID the policyID for a specific PolicyPerson
	 * @param policyPersonID the policyPersonID of the person to retrieve
	 * @return single instance of PolicyPerson
	 */
	public Policyperson getPolicyPerson(int policyID, int policyPersonID){
		Policyperson p1 = null;
        org.hibernate.Transaction tr = getSession().beginTransaction();
        Query qr = getSession().createQuery("from Policyperson where idpolicy='" + policyID 
        									+ "' and idPolicyperson='" + policyPersonID + "'");
        if (qr != null) {
        	p1 = (Policyperson) qr.uniqueResult();
        }
        tr.commit();
        return p1;
	}
	
	/**
	 * Updates a current PolicyPerson in the database
	 * 
	 * @param p1 the PolicyPerson with updated details
	 * @return true/false to confirm if PolicyPerson was updated
	 */
	public boolean updatePolicyPerson(Policyperson p1){
        if (p1 != null) {
            Transaction tr = null;
            try {
                tr = getSession().beginTransaction();
                getSession().update(p1);
                tr.commit();
            } catch (Exception e) {
                if (tr != null) {
                    tr.rollback();
                }
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}