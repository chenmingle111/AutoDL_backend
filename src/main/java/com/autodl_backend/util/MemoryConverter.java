package com.autodl_backend.util;
/**
 * 内存大小转换器
 */
public class MemoryConverter {

    private static final long GB_IN_BYTES = 1024L * 1024L * 1024L;
    private static final long MB_IN_BYTES = 1024L * 1024L;

    /**
     * GB转字节
     */
    public static Long gbToBytes(Integer gb) {
        return gb * GB_IN_BYTES;
    }

    /**
     * 字节转GB
     */
    public static Integer bytesToGb(Long bytes) {
        return (int) (bytes / GB_IN_BYTES);
    }

    /**
     * 格式化内存大小显示
     */
    public static String formatMemory(Long bytes) {
        if (bytes >= GB_IN_BYTES) {
            double gb = bytes / (double) GB_IN_BYTES;
            return String.format("%.1fGB", gb);
        }
        double mb = bytes / (double) MB_IN_BYTES;
        return String.format("%.1fMB", mb);
    }
}