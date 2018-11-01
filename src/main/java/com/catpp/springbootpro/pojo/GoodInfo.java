package com.catpp.springbootpro.pojo;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "good_info")
public class GoodInfo {
    /**
     * 商品编号
     */
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 商品名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 单价
     */
    @Column(name = "PRICE")
    private BigDecimal price;

    /**
     * 单位
     */
    @Column(name = "UNIT")
    private String unit;

    /**
     * 获取商品编号
     *
     * @return ID - 商品编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置商品编号
     *
     * @param id 商品编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     *
     * @return NAME - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取单价
     *
     * @return PRICE - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取单位
     *
     * @return UNIT - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
}