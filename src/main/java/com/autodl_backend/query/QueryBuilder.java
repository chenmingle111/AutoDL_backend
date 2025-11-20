package com.autodl_backend.query;

import com.autodl_backend.page.PageRequest;
import lombok.Data;
import java.util.*;


/**
 * SQL查询构建器
 */
@Data
public class QueryBuilder {

    private String tableName;
    private List<String> conditions = new ArrayList<>();
    private List<Object> params = new ArrayList<>();
    private String orderBy;
    private Integer limitValue;
    private Integer offsetValue;

    public QueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 添加WHERE条件
     */
    public QueryBuilder where(String field, String operator, Object value) {
        conditions.add(field + " " + operator + " ?");
        params.add(value);
        return this;
    }

    /**
     * 添加IN条件
     */
    public QueryBuilder whereIn(String field, List<?> values) {
        if (values != null && !values.isEmpty()) {
            String placeholders = String.join(",", Collections.nCopies(values.size(), "?"));
            conditions.add(field + " IN (" + placeholders + ")");
            params.addAll(values);
        }
        return this;
    }

    /**
     * 添加BETWEEN条件
     */
    public QueryBuilder whereBetween(String field, Object start, Object end) {
        conditions.add(field + " BETWEEN ? AND ?");
        params.add(start);
        params.add(end);
        return this;
    }

    /**
     * 设置排序
     */
    public QueryBuilder orderBy(String field, String direction) {
        this.orderBy = "ORDER BY " + field + " " + direction;
        return this;
    }

    public QueryBuilder orderBy(String field) {
        return orderBy(field, "ASC");
    }

    /**
     * 设置LIMIT
     */
    public QueryBuilder limit(Integer limit) {
        this.limitValue = limit;
        return this;
    }

    /**
     * 设置OFFSET
     */
    public QueryBuilder offset(Integer offset) {
        this.offsetValue = offset;
        return this;
    }

    /**
     * 设置分页
     */
    public QueryBuilder paginate(PageRequest pageRequest) {
        pageRequest.validate();
        this.limitValue = pageRequest.getLimit();
        this.offsetValue = pageRequest.getSqlOffset();
        return this;
    }

    /**
     * 构建SELECT语句
     */
    public String buildSelect(String fields) {
        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(fields).append(" FROM ").append(tableName);

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        if (orderBy != null) {
            sql.append(" ").append(orderBy);
        }

        if (limitValue != null) {
            sql.append(" LIMIT ").append(limitValue);
        }

        if (offsetValue != null) {
            sql.append(" OFFSET ").append(offsetValue);
        }

        return sql.toString();
    }

    public String buildSelect() {
        return buildSelect("*");
    }

    /**
     * 构建COUNT语句
     */
    public String buildCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM ");
        sql.append(tableName);

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        return sql.toString();
    }

    /**
     * 获取参数数组
     */
    public Object[] getParamsArray() {
        return params.toArray();
    }
}