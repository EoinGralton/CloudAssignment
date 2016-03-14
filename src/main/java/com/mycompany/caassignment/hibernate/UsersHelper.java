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
public class UsersHelper {

    Session session = null;

    public UsersHelper() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public Users getUserFromEmail(String email) {
        Users user = null;
            org.hibernate.Transaction tx = session.beginTransaction();
            Query q = session.createQuery("from Users where email='" + email + "'");
            if (q != null) {
                user = (Users) q.uniqueResult();
            }
            System.out.println("User: " + user);
            tx.commit();
            return user;
    }

    public Users auth(String email, String password) throws NoSuchAlgorithmException {

        byte[] passwordHash = null;
        byte[] passwordHashDb = null;
        passwordHash = MessageDigest.getInstance("SHA1").digest(password.getBytes());
        System.out.println("SHA1 length: " + passwordHash.length);
        Users ret = null;
        try {
            ret = getUserFromEmail(email);
            if (ret != null) {
                passwordHashDb = ret.getPassword();
                System.out.println("Hash  : " + hashToString(passwordHash));
                System.out.println("HashDb: " + hashToString(passwordHashDb));
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

    public int updatePassword(String email, String password) throws NoSuchAlgorithmException {
        int ret = 3;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("from Users where email='" + email + "'");
            if (q.uniqueResult() == null) {
                ret = 1;
            } else {
                Users user = (Users) q.uniqueResult();
                byte[] passwordHash = MessageDigest.getInstance("SHA1").digest(password.getBytes());
                System.out.println("Hash: "+hashToString(passwordHash));
                user.setPassword(passwordHash);
                session.update(user);
                tx.commit();
                ret = 0;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            ret = 2;
            e.printStackTrace();
        }
        return ret;
    }
    
    public static String hashToString(byte[] hash) {
        return javax.xml.bind.DatatypeConverter.printHexBinary(hash);
    }

}
