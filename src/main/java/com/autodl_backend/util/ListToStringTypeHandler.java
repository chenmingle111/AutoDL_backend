package com.autodl_backend.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MyBatis 类型转换器：实现 List<String> ↔ VARCHAR（逗号分隔字符串）的自动转换
 */
// 声明该转换器处理的 Java 类型（List<String>）和 JDBC 类型（VARCHAR）
@MappedTypes({List.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class ListToStringTypeHandler extends BaseTypeHandler<List<String>> {

    // 1. 插入/更新时：将 List<String> 转为 VARCHAR 字符串，设置到 SQL 参数中
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        // 用逗号拼接 List 为字符串（如 ["A","B"] → "A,B"）
        String value = parameter.stream().collect(Collectors.joining(","));
        ps.setString(i, value);
    }

    // 2. 查询时：从 ResultSet 中获取 VARCHAR 字符串，转为 List<String>
    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return convertStringToList(value);
    }

    // 3. 查询时：从 ResultSet 中按列索引获取 VARCHAR 字符串，转为 List<String>
    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return convertStringToList(value);
    }

    // 4. 查询时：从 CallableStatement 中获取 VARCHAR 字符串，转为 List<String>
    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return convertStringToList(value);
    }

    /**
     * 辅助方法：将 VARCHAR 字符串转为 List<String>
     * @param value 数据库中的字符串（如 "A,B,C"），可能为 null
     * @return 拆分后的 List，若 value 为 null/空则返回空 List
     */
    private List<String> convertStringToList(String value) {
        if (value == null || value.trim().isEmpty()) {
            return List.of(); // 返回空 List（避免 null 指针）
        }
        // 按逗号拆分字符串（如 "A,B" → ["A","B"]）
        return Arrays.stream(value.split(","))
                .map(String::trim) // 去除每个元素的前后空格（避免存储时的空格问题）
                .collect(Collectors.toList());
    }
}