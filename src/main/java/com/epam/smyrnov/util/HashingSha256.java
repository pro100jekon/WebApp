package com.epam.smyrnov.util;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class HashingSha256 {

    private HashingSha256() {
    }

    /**
     * SHA-256 hashing algorithm from com.google.common.hash.Hashing class
     * @param password
     * @return hash function of the password
     */
    public static String hash(String password) {
        return Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
    }
}
