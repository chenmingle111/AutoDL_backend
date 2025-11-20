package com.autodl_backend.util;

import org.apache.commons.codec.digest.DigestUtils;
import java.security.SecureRandom;
import java.time.Instant;

/**
 * Token管理器
 */
public class TokenManager {

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 生成用户token
     */
    public static String generateToken(Integer uid, String salt) {
        long timestamp = Instant.now().getEpochSecond();
        byte[] randomBytes = new byte[16];
        RANDOM.nextBytes(randomBytes);
        String randomHex = bytesToHex(randomBytes);

        String rawString = String.format("%d:%d:%s:%s", uid, timestamp, salt, randomHex);
        return DigestUtils.sha256Hex(rawString);
    }

    public static String generateToken(Integer uid) {
        return generateToken(uid, "");
    }

    /**
     * 验证token格式
     */
    public static boolean validateToken(String token) {
        if (token == null || token.length() != 64) {
            return false;
        }
        return token.matches("[0-9a-f]{64}");
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}