/*
 * Copyright (C) 2016 Dietmar Steiner (open.source@d-steiner.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.mycompany.caassignment.hibernate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * This class contains all methods to manipulate the members table of the database
 * 
 * @author Dietmar
 */
public class MemberHelper {

	private Session session = null;

	/**
	 *
	 */
	public MemberHelper() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	private Session getSession() {
		if (session == null || !session.isOpen()) {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		return session;
	}

	/**
	 * Gets a Member Table class where the email equals the given email
         * 
	 * @param email the email to search for
	 * @return returns the member row or null if not found
	 */
	public Member getMemberFromEmail(String email) {
		Member member = null;
		org.hibernate.Transaction tx = getSession().beginTransaction();
		Query q = getSession().createQuery("from Member where email='" + email + "'");
		if (q != null) {
			member = (Member) q.uniqueResult();
		}
		tx.commit();
		return member;
	}

	/**
	 * Authenticates the given email and password against the entry in the database
         * 
	 * @param email the email
	 * @param password the password
	 * @return the member row or null if authentication failed
	 */
	public Member auth(String email, String password) {

		Member ret = null;
		byte[] passwordHash;
		try {
			passwordHash = MessageDigest.getInstance("SHA1").digest(password.getBytes());
			ret = getMemberFromEmail(email);
			if (ret != null) {
				byte[] passwordHashDb = ret.getPassword();
				for (int i = 0; i < passwordHash.length; i++) {
					if (passwordHash[i] != passwordHashDb[i]) {
						ret = null;
					}
				}
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * Updates the password in the Member Table in the database for the given
	 * email address
	 *
	 * @param email  email address of the entry to be updated
	 * @param password password to be set
	 * @return the Member Table class of the entry updated or null if the entry
	 *         was not found
	 */
	public Member updatePassword(String email, String password) {
		Member ret = null;
		Member member = this.getMemberFromEmail(email);
		if (member != null) {
			Transaction tx = null;
			try {
				tx = getSession().beginTransaction();
				byte[] passwordHash = MessageDigest.getInstance("SHA1").digest(password.getBytes());
				member.setPassword(passwordHash);
				getSession().update(member);
				tx.commit();
				ret = member;
			} catch (HibernateException | NoSuchAlgorithmException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			}
		}
		return ret;
	}

    /**
     *
     * @param email
     * @param password
     * @return
     */
    public boolean insert(String email, String password) {
		Transaction tx = getSession().beginTransaction();
		try {
			byte[] passwordHash = MessageDigest.getInstance("SHA1").digest(password.getBytes());
			Member member = new Member(email, passwordHash);
			getSession().save(member);
			tx.commit();
		} catch (NoSuchAlgorithmException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			return false;
		}
		return true;
	}

    /**
     *
     * @param email
     */
    public void delete(String email) {
		Member member = this.getMemberFromEmail(email);
		Transaction tx = getSession().beginTransaction();
		getSession().delete(member);
		tx.commit();
	}

	/**
	 *
	 * @param hash
	 * @return
	 */
	public static String hashToString(byte[] hash) {
		return javax.xml.bind.DatatypeConverter.printHexBinary(hash);
	}

}
