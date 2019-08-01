package utils;

import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.testng.Assert.*;

public class HashUtilTest {

    static String password;

    @Test(expectedExceptions = NullPointerException.class)
    public void testGenerateHashPassword()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = null;
        assertNull(HashUtil.generateHashPassword(password));
    }

    @Test
    public void testGenerateHashPassword1()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "123";
        assertNotEquals(null, HashUtil.generateHashPassword(password));
    }

    @Test
    public void testGenerateHashPassword2()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "3344";
        assertNotEquals("23333", HashUtil.generateHashPassword(password));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testValidatePassword()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = null;
        assertTrue(HashUtil.validatePassword(password, HashUtil.generateHashPassword(password)));
    }

    @Test
    public void testValidatePassword1()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "4455";
        String actual = HashUtil.generateHashPassword(password);
        assertNotEquals(actual, HashUtil.generateHashPassword(password));
    }

    @Test
    public void testValidatePassword2()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "4455";
        String storedPassword = HashUtil.generateHashPassword(password);
        assertTrue(HashUtil.validatePassword(password, storedPassword));
    }
}