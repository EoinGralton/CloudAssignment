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
public class MemberHelperTest {

	public MemberHelperTest() {
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
	 * Test of auth method, of class MemberHelper.
	 */
	@Test
	public void testAuth() throws Exception {
		System.out.println("auth");
		String email = "test@test.com";
		String password = "secret";
		MemberHelper instance = new MemberHelper();
		Member result = instance.auth(email, password);
		if (result != null) {
			assertEquals(email, result.getEmail());
		} else {
			fail("no member found");
		}
		email = "test1@test.com";
		result = instance.auth(email, password);
		if (result != null) {
			fail("member found");
		} else {
			assertNull(result);
		}
	}

	/**
	 * Test of updatePassword method, of class MemberHelper.
	 */
	@Test
	public void testUpdatePassword() throws Exception {
		System.out.println("updatePassword");
		String email = "test@test.com";
		String password = "secret";
		MemberHelper instance = new MemberHelper();
		int expResult = 0;
		Member result = instance.updatePassword(email, password);
		assertNotNull(result);
	}

	/**
	 * Test of getUserFromEmail method, of class MemberHelper.
	 */
	@Test
	public void testGetMemberFromEmail() {
		System.out.println("getMemberFromEmail");
		String email = "test@test.com";
		MemberHelper instance = new MemberHelper();
		Member result = instance.getMemberFromEmail(email);
		if (result != null) {
			assertEquals(email, result.getEmail());
		} else {
			fail("no member found");
		}
	}

	/**
	 * Test of hashToString method, of class MemberHelper.
	 */
	@Test
	public void testHashToString() throws NoSuchAlgorithmException {
		System.out.println("hashToString");
		byte[] hash = MessageDigest.getInstance("SHA1").digest(new String("secret").getBytes());
		String expResult = "E5E9FA1BA31ECD1AE84F75CAAA474F3A663F05F4";
		String result = MemberHelper.hashToString(hash);
		assertEquals(expResult, result);
	}

	/**
	 * Test of insert method, of class MemberHelper.
	 */
	@Test
	public void testInsert() throws NoSuchAlgorithmException {
		System.out.println("insert");
		MemberHelper helper = new MemberHelper();
		boolean expResult = true;
		boolean result = helper.insert("testx@test.com", "secret");
		assertEquals(expResult, result);
		Member member = helper.getMemberFromEmail("testx@test.com");
		helper.delete("testx@test.com");
		expResult = true;
		Person person = new Person(member, member.getId(), false, "Mr", "testx1", "testx2", null, null);
		result = helper.insert("testx1@test.com", "secret", person);
		assertEquals(expResult, result);
		helper.delete("testx1@test.com");
	}

	/**
	 * Test of delete method, of class MemberHelper.
	 */
	@Test
	public void testDelete() throws NoSuchAlgorithmException {
		System.out.println("delete");
		MemberHelper helper = new MemberHelper();
                boolean insert = helper.insert("testx@test.com", "secret");
		boolean result = helper.delete("testx@test.com");
		boolean expResult = true;
		assertEquals(expResult, result);
	}

}
