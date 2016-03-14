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
package com.mycompany.caassignment;

import com.mycompany.caassignment.hibernate.Users;
import com.mycompany.caassignment.hibernate.UsersHelper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dietmar
 */
public class UsersHelperTest {
    
    public UsersHelperTest() {
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

    /**
     * Test of auth method, of class UsersHelper.
     */
    @Test
    public void testAuth() throws Exception {
        System.out.println("auth");
        String email = "test@test.com";
        String password = "secret";
        UsersHelper instance = new UsersHelper();
        Users result = instance.auth(email, password);
        assertEquals(email, result.getEmail());
        email = "test@test.com";
        password = "secret";
 
    }

    /**
     * Test of updatePassword method, of class UsersHelper.
     */
    @Test
    public void testUpdatePassword() throws Exception {
        System.out.println("updatePassword");
        String email = "test@test.com";
        String password = "secret";
        UsersHelper instance = new UsersHelper();
        int expResult = 0;
        int result = instance.updatePassword(email, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserFromEmail method, of class UsersHelper.
     */
    @Test
    public void testGetUserFromEmail() {
        System.out.println("getUserFromEmail");
        String email = "test@test.com";
        UsersHelper instance = new UsersHelper();
        Users result = instance.getUserFromEmail(email);
        assertEquals(email, result.getEmail());
    }

    /**
     * Test of hashToString method, of class UsersHelper.
     */
    @Test
    public void testHashToString() throws NoSuchAlgorithmException {
        System.out.println("hashToString");
        byte[] hash = MessageDigest.getInstance("SHA1").digest(new String("secret").getBytes());
        String expResult = "E5E9FA1BA31ECD1AE84F75CAAA474F3A663F05F4";
        String result = UsersHelper.hashToString(hash);
        assertEquals(expResult, result);
    }
    
}
