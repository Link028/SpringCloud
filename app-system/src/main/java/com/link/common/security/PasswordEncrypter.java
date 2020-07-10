package com.link.common.security;

import com.link.util.MD5Utils;

public class PasswordEncrypter
{
    public static boolean matches(String password , String userId  , String newPassword, String salt)
    {
        return password.equals(encryptPassword( userId, newPassword, salt ));
    }

    public static String encryptPassword(String userId, String password, String salt)
    {
        return MD5Utils.hash( userId + password + salt );
    }
}