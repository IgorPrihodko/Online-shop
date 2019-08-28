package utils;

import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;


public class HashUtilTest {

    private static String password;

    @Test(expectedExceptions = NullPointerException.class)
    public void generateHashPasswordTestOne()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = null;
        assertNull(HashUtil.generateHashPassword(password));
    }

    @Test
    public void generateHashPasswordTestTwo()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "123";
        assertNotEquals(null, HashUtil.generateHashPassword(password));
    }

    @Test
    public void generateHashPasswordTestThree()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "3344";
        assertNotEquals("23333", HashUtil.generateHashPassword(password));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void validatePasswordTestOne()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = null;
        assertTrue(HashUtil.validatePassword(password, HashUtil.generateHashPassword(password)));
    }

    @Test
    public void validatePasswordTestTwo()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "4455";
        String actual = HashUtil.generateHashPassword(password);
        String expected = HashUtil.generateHashPassword(password);
        assertNotEquals(actual, expected);
    }

    @Test
    public void validatePasswordTestThree()
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "4455";
        String storedPassword = HashUtil.generateHashPassword(password);
        assertTrue(HashUtil.validatePassword(password, storedPassword));
    }
}
