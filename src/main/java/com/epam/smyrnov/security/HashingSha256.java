package com.epam.smyrnov.security;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class HashingSha256 {

    public static String hash(String password) {
        return Hashing.sha256().hashString(password, Charsets.UTF_8).toString();
    }
}
