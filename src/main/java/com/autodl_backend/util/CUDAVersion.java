package com.autodl_backend.util;


import java.util.*;

/**
 * CUDA版本管理
 */
public class CUDAVersion {

    private static final Map<Integer, String> VERSION_MAP = new HashMap<>();

    static {
        VERSION_MAP.put(102, "10.2");
        VERSION_MAP.put(111, "11.1");
        VERSION_MAP.put(112, "11.2");
        VERSION_MAP.put(113, "11.3");
        VERSION_MAP.put(114, "11.4");
        VERSION_MAP.put(115, "11.5");
        VERSION_MAP.put(116, "11.6");
        VERSION_MAP.put(117, "11.7");
        VERSION_MAP.put(118, "11.8");
        VERSION_MAP.put(121, "12.1");
        VERSION_MAP.put(122, "12.2");
        VERSION_MAP.put(123, "12.3");
        VERSION_MAP.put(124, "12.4");
    }

    /**
     * 转换为字符串版本
     */
    public static String toString(Integer cudaV) {
        return VERSION_MAP.getOrDefault(cudaV, "Unknown(" + cudaV + ")");
    }

    /**
     * 从字符串解析版本号
     */
    public static Integer fromString(String versionStr) {
        for (Map.Entry<Integer, String> entry : VERSION_MAP.entrySet()) {
            if (entry.getValue().equals(versionStr)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 获取所有支持的CUDA版本
     */
    public static List<Map<String, Object>> getAllVersions() {
        List<Map<String, Object>> versions = new ArrayList<>();
        List<Integer> sortedKeys = new ArrayList<>(VERSION_MAP.keySet());
        Collections.sort(sortedKeys);

        for (Integer code : sortedKeys) {
            Map<String, Object> version = new HashMap<>();
            version.put("code", code);
            version.put("version", VERSION_MAP.get(code));
            versions.add(version);
        }
        return versions;
    }
}