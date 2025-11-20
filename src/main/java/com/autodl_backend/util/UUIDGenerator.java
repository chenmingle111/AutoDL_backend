package com.autodl_backend.util;

import java.security.SecureRandom;
import java.time.Instant;

/**
 * UUID生成器
 */
public class UUIDGenerator {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    /**
     * 生成部署UUID
     */
    public static String generateDeploymentUUID() {
        long timestamp = Instant.now().toEpochMilli();
        String timestampHex = Long.toHexString(timestamp);
        String randomPart = generateRandomHex(4);
        return (timestampHex + randomPart).substring(0, 15);
    }

    /**
     * 生成容器UUID
     */
    public static String generateContainerUUID(String deploymentUuid, String machineId) {
        String randomSuffix = generateRandomHex(4);
        return String.format("%s-%s-%s", deploymentUuid, machineId, randomSuffix);
    }

    /**
     * 生成镜像UUID
     */
    public static String generateImageUUID(String prefix) {
        String randomPart = generateRandomHex(5);
        return String.format("%s-%s", prefix, randomPart);
    }

    public static String generateImageUUID() {
        return generateImageUUID("image");
    }

    /**
     * 生成主机ID
     */
    public static String generateMachineId() {
        return generateRandomHex(5);
    }

    /**
     * 生成随机十六进制字符串
     */
    private static String generateRandomHex(int length) {
        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);
        StringBuilder sb = new StringBuilder(length * 2);
        for (byte b : bytes) {
            sb.append(HEX_CHARS[(b >> 4) & 0x0F]);
            sb.append(HEX_CHARS[b & 0x0F]);
        }
        return sb.toString();
    }
}