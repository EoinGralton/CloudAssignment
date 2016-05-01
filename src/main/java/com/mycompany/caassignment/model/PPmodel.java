/**
 * 
 */
package com.mycompany.caassignment.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mycompany.caassignment.hibernate.PolicyPersonHelper;
import com.mycompany.caassignment.hibernate.Policyperson;

/**
 * @author Marius
 *
 */
@ManagedBean
@ViewScoped
public class PPmodel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7374797451244656343L;
	private String title;
	private String fName;
	private String lName;
	private Date dateOfBirth;
	
	private PolicyPersonHelper helper = new PolicyPersonHelper();
	private Policyperson p1;
	private List<Policyperson> currentList;
	
	@PostConstruct
	public void init(){
		currentList = new ArrayList<Policyperson>();
		
		currentList.add(new Policyperson(null, "Mr", "Thomas", "Reed", new Date(75,6,14)));
	}
	/**
	 * @return the p1
	 */
	public Policyperson getP1() {
		return p1;
	}
	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(Policyperson p1) {
		this.p1 = p1;
	}
	/**
	 * @return the currentList
	 */
	public List<Policyperson> getCurrentList() {
		return currentList;
	}
	/**
	 * @param currentList the currentList to set
	 */
	public void setCurrentList(List<Policyperson> currentList) {
		this.currentList = currentList;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}
	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}
	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public void addPolicyPersonToDB(){
		p1 = new Policyperson();
		p1.setIdPolicyPerson(1); //FOR TESTING PURPOSES
		p1.setTitle(getTitle());
		p1.setFirstname(getfName());
		p1.setLastname(getlName());
		p1.setDateofbirth(getDateOfBirth());
		
		helper.insertPolicyPerson(p1);
		currentList.add(p1);
	}

}
