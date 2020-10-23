package org.example.util;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * 单币种货币类，处理货币算术、币种和取整。
 *
 * <p>
 * 货币类中封装了货币金额和币种。目前金额在内部是long类型表示， 单位是所属币种的最小货币单位（对人民币是分）。
 *
 * <p>
 * 目前，货币实现了以下主要功能：<br>
 * <ul>
 * <li>支持货币对象与double(float)/long(int)/String/BigDecimal之间相互转换。
 * <li>货币类在运算中提供与JDK中的BigDecimal类似的运算接口， BigDecimal的运算接口支持任意指定精度的运算功能，能够支持各种
 * 可能的财务规则。
 * <li>货币类在运算中也提供一组简单运算接口，使用这组运算接口，则在 精度处理上使用缺省的处理规则。
 * <li>推荐使用Money，不建议直接使用BigDecimal的原因之一在于，
 * 使用BigDecimal，同样金额和币种的货币使用BigDecimal存在多种可能 的表示，例如：new BigDecimal("10.5")与new
 * BigDecimal("10.50") 不相等，因为scale不等。使得Money类，同样金额和币种的货币只有 一种表示方式，new
 * Money("10.5")和new Money("10.50")应该是相等的。
 * <li>不推荐直接使用BigDecimal的另一原因在于， BigDecimal是Immutable，
 * 一旦创建就不可更改，对BigDecimal进行任意运算都会生成一个新的 BigDecimal对象，因此对于大批量统计的性能不够满意。Money类是
 * mutable的，对大批量统计提供较好的支持。
 * <li>提供基本的格式化功能。
 * <li>Money类中不包含与业务相关的统计功能和格式化功能。业务相关的功能 建议使用utility类来实现。
 * <li>Money类实现了Serializable接口，支持作为远程调用的参数和返回值。
 * <li>Money类实现了equals和hashCode方法。
 * </ul>
 * <p>
 * TODO: 必须处理运算中的溢出情形
 * 金钱相关计算类
 *
 * @author yongSen.wang
 * @date 2020/6/27 17:41
 */
public class Money implements Serializable, Comparable<Money> {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long  serialVersionUID        = 6009335074727417445L;

    /**
     * 缺省的币种代码，为CNY（人民币）。
     */
    public static final String DEFAULT_CURRENCY_CODE   = "CNY";

    /**
     * 缺省的取整模式，为<code>BigDecimal.ROUND_HALF_EVEN
     * （四舍五入，当小数为0.5时，则取最近的偶数）。
     */
    public static final int    DEFAULT_ROUNDING_MODE   = BigDecimal.ROUND_HALF_EVEN;

    /**
     * 默认保留位数为6位
     */
    public static final int    DEFAULT_FRACTION_DIGITS = 6;

    /**
     * 一组可能的元/分换算比例。
     *
     * <p>
     * 此处，“分”是指货币的最小单位，“元”是货币的最常用单位， 不同的币种有不同的元/分换算比例，如人民币是100，而日元为1。
     */
    private static final int[] centFactors             = new int[] { 1, 10, 100, 1000 };

    /**
     * 金额，以元为单位。
     */
    private BigDecimal         yuan;

    /**
     * 币种。
     */
    private Currency           currency;

    // 构造器 ====================================================

    /**
     * 缺省构造器。
     *
     * <p>
     * 创建一个具有缺省金额（0）和缺省币种的货币对象。
     */
    public Money() {
        this(BigDecimal.ZERO);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>元和缺省币种的货币对象。
     *
     * @param amount 金额，以元为单位。
     */
    public Money(String amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>元和指定币种<code>currency</code>的货币对象。
     *
     * @param amount   金额，以元为单位。
     * @param currency 币种。
     */
    public Money(String amount, Currency currency) {
        // 如果金额为null，宁愿让它报错
        this(new BigDecimal(amount == null ? null : StringUtils.trim(amount)), currency);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>元和指定币种<code>currency</code>的货币对象。
     * 如果金额不能转换为整数分，则使用指定的取整模式<code>roundingMode</code>取整。
     *
     * @param amount       金额，以元为单位。
     * @param currency     币种。
     * @param roundingMode 取整模式。
     */
    public Money(String amount, Currency currency, int roundingMode) {
        this(new BigDecimal(amount), currency, roundingMode);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>和缺省币种的货币对象。 如果金额不能转换为整数分，则使用缺省取整模式
     * <code>DEFAULT_ROUNDING_MODE</code>取整。
     *
     * @param amount 金额，以元为单位。
     */
    public Money(BigDecimal amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有参数<code>amount</code>指定金额和缺省币种的货币对象。 如果金额不能转换为整数分，则使用指定的取整模式
     * <code>roundingMode</code>取整。
     *
     * @param amount       金额，以元为单位。
     * @param roundingMode 取整模式
     */
    public Money(BigDecimal amount, int roundingMode) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE), roundingMode);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>和指定币种的货币对象。 如果金额不能转换为整数分，则使用缺省的取整模式
     * <code>DEFAULT_ROUNDING_MODE</code>进行取整。
     *
     * @param amount   金额，以元为单位。
     * @param currency 币种
     */
    public Money(BigDecimal amount, Currency currency) {
        this(amount, currency, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 构造器。
     *
     * <p>
     * 创建一个具有金额<code>amount</code>和指定币种的货币对象。 如果金额不能转换为整数分，则使用指定的取整模式
     * <code>roundingMode</code>取整。
     *
     * @param amount       金额，以元为单位。
     * @param currency     币种。
     * @param roundingMode 取整模式。
     */
    public Money(BigDecimal amount, Currency currency, int roundingMode) {
        this.currency = currency;
        this.yuan = rounding(amount, roundingMode);
    }

    // Bean方法 ====================================================

    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，以元为单位。（不进行四舍五入）
     */
    public BigDecimal getAmount() {
        return yuan;
    }

    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，以元为单位。（转化为标准货币）
     */
    public BigDecimal getAmountYuan() {
        return roundingDecimal(yuan, currency.getDefaultFractionDigits());
    }


    /**
     * 设置本货币对象代表的金额数。
     *
     * @param amount 金额数，以DEFAULT_FRACTION_DIGITS为单位。
     */
    public void setAmount(BigDecimal amount) {
        yuan = rounding(amount.movePointRight(DEFAULT_FRACTION_DIGITS), BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * 获取本货币对象代表的金额数。
     *
     * @return 金额数，小数点位数大小的数
     */
    public long getValue(BigDecimal amount) {
        return amount.movePointRight(DEFAULT_FRACTION_DIGITS)
            .setScale(DEFAULT_FRACTION_DIGITS, DEFAULT_ROUNDING_MODE).longValue();
    }

    /**
     * 获取本货币对象代表的币种。
     *
     * @return 本货币对象所代表的币种。
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * 获取本货币币种的元/分换算比率。
     *
     * @return 本货币币种的元/分换算比率。
     */
    public int getCentFactor() {
        return centFactors[currency.getDefaultFractionDigits()];
    }

    // 基本对象方法 ===================================================

    /**
     * 判断本货币对象与另一对象是否相等。
     *
     * <p>
     * 本货币对象与另一对象相等的充分必要条件是：<br>
     * <ul>
     * <li>另一对象也属货币对象类。
     * <li>金额相同。
     * <li>币种相同。
     * </ul>
     *
     * @param other 待比较的另一对象。
     * @return <code>true</code>表示相等，<code>false</code>表示不相等。
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof Money) && equals((Money) other);
    }

    /**
     * 判断本货币对象与另一货币对象是否相等。
     *
     * <p>
     * 本货币对象与另一货币对象相等的充分必要条件是：<br>
     * <ul>
     * <li>金额相同。
     * <li>币种相同。
     * </ul>
     *
     * @param other 待比较的另一货币对象。
     * @return <code>true</code>表示相等，<code>false</code>表示不相等。
     */
    public boolean equals(Money other) {
        return currency.equals(other.currency) && (getValue(yuan) == getValue(other.yuan));
    }

    /**
     * 计算本货币对象的杂凑值。
     *
     * @return 本货币对象的杂凑值。
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        long value = getValue(yuan);
        return (int) (value ^ (value >>> 32));
    }

    // Comparable接口 ========================================

    /**
     * 货币比较。
     *
     * <p>
     * 比较本货币对象与另一货币对象的大小。 如果待比较的两个货币对象的币种不同，则抛出
     * <code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额少于待比较货币对象，则返回-1。 如果本货币对象的金额等于待比较货币对象，则返回0。
     * 如果本货币对象的金额大于待比较货币对象，则返回1。
     *
     * @param other 另一对象。
     * @return -1表示小于，0表示等于，1表示大于。
     * @throws IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     */
    @Override
    public int compareTo(Money other) {
        assertSameCurrencyAs(other);

        return Long.compare(getValue(yuan), getValue(other.yuan));
    }

    /**
     * 货币比较。
     *
     * <p>
     * 判断本货币对象是否大于另一货币对象。 如果待比较的两个货币对象的币种不同，则抛出
     * <code>java.lang.IllegalArgumentException</code>。
     * 如果本货币对象的金额大于待比较货币对象，则返回true，否则返回false。
     *
     * @param other 另一对象。
     * @return true表示大于，false表示不大于（小于等于）。
     * @throws IllegalArgumentException 待比较货币对象与本货币对象的币种不同。
     */
    public boolean greaterThan(Money other) {
        return compareTo(other) > 0;
    }

    /**
     * 是否大于等于
     *
     * @param other
     * @return
     */
    public boolean greaterEqualThan(Money other) {
        return compareTo(other) >= 0;
    }

    /**
     * 是否小于
     *
     * @param other
     * @return
     */
    public boolean lessThan(Money other) {
        return compareTo(other) < 0;
    }

    /**
     * 是否小于等于
     *
     * @param other
     * @return
     */
    public boolean lessEqualThan(Money other) {
        return compareTo(other) <= 0;
    }

    // 货币算术 ==========================================

    /**
     * 货币加法。
     *
     * <p>
     * 如果两货币币种相同，则返回一个新的相同币种的货币对象，其金额为 两货币对象金额之和，本货币对象的值不变。 如果两货币对象币种不同，抛出
     * <code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为加数的货币对象。
     * @return 相加后的结果。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money add(Money other) {
        assertSameCurrencyAs(other);

        return newMoneyWithSameCurrency(yuan.add(other.yuan));
    }

    /**
     * 货币累加。
     *
     * <p>
     * 如果两货币币种相同，则本货币对象的金额等于两货币对象金额之和，并返回本货币对象的引用。 如果两货币对象币种不同，抛出
     * <code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为加数的货币对象。
     * @return 累加后的本货币对象。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money addTo(Money other) {
        assertSameCurrencyAs(other);

        this.yuan = yuan.add(other.yuan);

        return this;
    }

    /**
     * 货币减法。
     *
     * <p>
     * 如果两货币币种相同，则返回一个新的相同币种的货币对象，其金额为 本货币对象的金额减去参数货币对象的金额。本货币对象的值不变。
     * 如果两货币币种不同，抛出<code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为减数的货币对象。
     * @return 相减后的结果。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money subtract(Money other) {
        assertSameCurrencyAs(other);

        return newMoneyWithSameCurrency(yuan.subtract(other.yuan));
    }

    /**
     * 货币累减。
     *
     * <p>
     * 如果两货币币种相同，则本货币对象的金额等于两货币对象金额之差，并返回本货币对象的引用。 如果两货币币种不同，抛出
     * <code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 作为减数的货币对象。
     * @return 累减后的本货币对象。
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    public Money subtractFrom(Money other) {
        assertSameCurrencyAs(other);

        this.yuan = yuan.subtract(other.yuan);

        return this;
    }

    /**
     * 货币乘法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额乘以乘数。
     * 本货币对象的值不变。如果相乘后的金额不能转换为整数分，使用缺省的取整模式 <code>DEFUALT_ROUNDING_MODE</code>
     * 进行取整。
     *
     * @param val 乘数
     * @return 相乘后的结果。
     */
    public Money multiply(BigDecimal val) {
        return multiply(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 货币累乘。
     *
     * <p>
     * 本货币对象金额乘以乘数，并返回本货币对象。 如果相乘后的金额不能转换为整数分，使用缺省的取整方式
     * <code>DEFUALT_ROUNDING_MODE</code>进行取整。
     *
     * @param val 乘数
     * @return 累乘后的结果。
     */
    public Money multiplyBy(BigDecimal val) {
        return multiplyBy(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 货币乘法。(分为单位)
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额乘以乘数。
     * 本货币对象的值不变。如果相乘后的金额不能转换为整数分，使用指定的取整方式 <code>roundingMode</code>进行取整。
     *
     * @param val          乘数
     * @param roundingMode 取整方式
     * @return 相乘后的结果。
     */
    public Money multiply(BigDecimal val, int roundingMode) {
        BigDecimal newCent = yuan.multiply(val);

        return newMoneyWithSameCurrency(rounding(newCent, roundingMode));
    }

    /**
     * 货币乘法。(元为单位)
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额乘以乘数。
     * 本货币对象的值不变。如果相乘后的金额不能转换为整数分，使用指定的取整方式 <code>roundingMode</code>进行取整。
     *
     * @param val          乘数
     * @param newScale     保留位数
     * @return 相乘后的结果。
     */
    public Money multiply(int newScale, BigDecimal val) {
        BigDecimal newCent = yuan.multiply(val);

        return new Money(roundingDecimal(newCent, newScale));
    }

    /**
     * 货币累乘。
     *
     * <p>
     * 本货币对象金额乘以乘数，并返回本货币对象。 如果相乘后的金额不能转换为整数分，使用指定的取整方式
     * <code>roundingMode</code>进行取整。
     *
     * @param val          乘数
     * @param roundingMode 取整方式
     * @return 累乘后的结果。
     */
    public Money multiplyBy(BigDecimal val, int roundingMode) {
        BigDecimal newCent = yuan.multiply(val);

        this.yuan = rounding(newCent, roundingMode);

        return this;
    }

    /**
     * 货币除法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额除以除数。
     * 本货币对象的值不变。如果相除后的金额不能转换为整数分，使用缺省的取整模式 <code>DEFAULT_ROUNDING_MODE</code>
     * 进行取整。
     *
     * @param val 除数
     * @return 相除后的结果。
     */
    public Money divide(BigDecimal val) {
        return divide(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 货币除法。
     *
     * <p>
     * 返回一个新的货币对象，币种与本货币对象相同，金额为本货币对象的金额除以除数。
     * 本货币对象的值不变。如果相除后的金额不能转换为整数分，使用指定的取整模式 <code>roundingMode</code>进行取整。
     *
     * @param val          除数
     * @param roundingMode 取整
     * @return 相除后的结果。
     */
    public Money divide(BigDecimal val, int roundingMode) {
        BigDecimal newCent = yuan.divide(val, DEFAULT_FRACTION_DIGITS, roundingMode);

        return newMoneyWithSameCurrency(newCent);
    }

    /**
     * 货币累除。
     *
     * <p>
     * 本货币对象金额除以除数，并返回本货币对象。 如果相除后的金额不能转换为整数分，使用缺省的取整模式
     * <code>DEFAULT_ROUNDING_MODE</code>进行取整。
     *
     * @param val 除数
     * @return 累除后的结果。
     */
    public Money divideBy(BigDecimal val) {
        return divideBy(val, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 货币累除。
     *
     * <p>
     * 本货币对象金额除以除数，并返回本货币对象。 如果相除后的金额不能转换为整数分，使用指定的取整模式
     * <code>roundingMode</code>进行取整。
     *
     * @param val 除数
     * @return 累除后的结果。
     */
    public Money divideBy(BigDecimal val, int roundingMode) {

        this.yuan = yuan.divide(val, roundingMode);

        return this;
    }

    // 格式化方法 =================================================

    /**
     * 生成本对象的缺省字符串表示
     */
    @Override
    public String toString() {
        return getAmount().toString();
    }

    /**
     * 生成简单的字符串值，即如果是有角、分的，则显示，否则只显示元
     *
     * @return
     */
    public String toSimpleString() {
        String string = toString();
        if (StringUtils.endsWith(string, ".00") || StringUtils.endsWith(string, ".0")) {
            return StringUtils.substring(string, 0, string.length() - 3);
        }
        return string;
    }

    // 内部方法 ===================================================

    /**
     * 断言本货币对象与另一货币对象是否具有相同的币种。
     *
     * <p>
     * 如果本货币对象与另一货币对象具有相同的币种，则方法返回。 否则抛出运行时异常
     * <code>java.lang.IllegalArgumentException</code>。
     *
     * @param other 另一货币对象
     * @throws IllegalArgumentException 如果本货币对象与另一货币对象币种不同。
     */
    protected void assertSameCurrencyAs(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("Money math currency mismatch.");
        }
    }

    /**
     * 对BigDecimal型的值按指定取整方式取整。（默认6位取整）
     *
     * @param val          待取整的BigDecimal值
     * @param roundingMode 取整方式
     * @return 取整后的long型值
     */
    protected BigDecimal rounding(BigDecimal val, int roundingMode) {
        return val.setScale(DEFAULT_FRACTION_DIGITS, roundingMode);
    }

    protected BigDecimal roundingDecimal(BigDecimal val, int newScale) {
        return val.setScale(newScale, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 创建一个币种相同，具有指定金额的货币对象。
     *
     * @param yuan 金额，以元为单位
     * @return 一个新建的币种相同，具有指定金额的货币对象
     */
    protected Money newMoneyWithSameCurrency(BigDecimal yuan) {
        Money money = new Money(yuan, currency);

        money.yuan = yuan;

        return money;
    }

    /**
     * 设置货币的元值。
     *
     * @param l
     */
    public void setYuan(BigDecimal l) {
        yuan = l;
    }

    public static boolean isValidFormat(String moneyStr) {
        if (StringUtils.isBlank(moneyStr)) {
            return false;
        }
        return moneyStr.matches("(-)?[0-9]{1,13}+(.[0-9]{1,2})?");
    }

    public static int[] getCentfactors() {
        return centFactors;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public Money clone() {
        Money money = new Money();
        money.setCurrency(this.currency);
        money.setYuan(this.yuan);
        return money;
    }

    public static enum LevelEnum {
                                  YI("亿"),

                                  WAN("万");

        private String message;

        private LevelEnum(String message) {
            this.message = message;
        }

        public String message() {
            // TODO Auto-generated method stub
            return message;
        }

    }
}
