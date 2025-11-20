package com.autodl_backend.util;
import java.math.BigDecimal;
import java.math.RoundingMode;
/**
 * 价格计算器
 */
public class PriceCalculator {

    private static final BigDecimal THOUSAND = new BigDecimal("1000");
    private static final BigDecimal SECONDS_PER_HOUR = new BigDecimal("3600");

    /**
     * 将元转换为内部价格(元*1000)
     */
    public static Integer toInternalPrice(BigDecimal priceYuan) {
        return priceYuan.multiply(THOUSAND).intValue();
    }

    public static Integer toInternalPrice(Double priceYuan) {
        return toInternalPrice(BigDecimal.valueOf(priceYuan));
    }

    /**
     * 将内部价格转换为元
     */
    public static BigDecimal toYuan(Integer internalPrice) {
        return new BigDecimal(internalPrice).divide(THOUSAND, 3, RoundingMode.HALF_UP);
    }

    /**
     * 计算费用
     *
     * @param pricePerHour 每小时价格(元*1000)
     * @param durationSeconds 持续时间(秒)
     * @return 费用(元)
     */
    public static BigDecimal calculateCost(Integer pricePerHour, Integer durationSeconds) {
        BigDecimal hours = new BigDecimal(durationSeconds).divide(SECONDS_PER_HOUR, 6, RoundingMode.HALF_UP);
        BigDecimal priceYuan = toYuan(pricePerHour);
        return priceYuan.multiply(hours).setScale(4, RoundingMode.HALF_UP);
    }

    /**
     * 格式化价格显示
     */
    public static String formatPrice(Integer internalPrice) {
        BigDecimal yuan = toYuan(internalPrice);
        return String.format("¥%.3f", yuan);
    }
}