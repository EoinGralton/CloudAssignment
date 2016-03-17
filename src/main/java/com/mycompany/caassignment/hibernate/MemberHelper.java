/*
 * Copyright (C) 2016 Dietmar.
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
    	if (session == null || !session.isOpen()){
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    	}
    	return session;
    }

    /**
     *
     * @param email
     * @return
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
     *
     * @param email
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public Member auth(String email, String password) throws NoSuchAlgorithmException {

        byte[] passwordHash = null;
        byte[] passwordHashDb = null;
        passwordHash = MessageDigest.getInstance("SHA1").digest(password.getBytes());
        Member ret = null;
        try {
            ret = getMemberFromEmail(email);
            if (ret != null) {
                passwordHashDb = ret.getPassword();
                for (int i = 0; i < passwordHash.length; i++) {
                    if (passwordHash[i] != passwordHashDb[i]) {
                        ret = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     *
     * @param email
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    public Member updatePassword(String email, String password) throws NoSuchAlgorithmException {
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
            } catch (HibernateException e) {
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
     * @param hash
     * @return
     */
    public static String hashToString(byte[] hash) {
        return javax.xml.bind.DatatypeConverter.printHexBinary(hash);
    }

}
