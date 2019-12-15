package com.luochaoqun.plus.ideas.xiaoyuandiancan.shop.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author luochaoqun
 * @since 2019-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    private String shopName;

    private BigDecimal lat;

    private BigDecimal longitude;

    private String address;

    private String tel;

    private String businessStartTime;

    private String businessEndTime;

    private String supportDeliveryFlag;

    private String businessLicenseImg;

    private LocalDateTime createTime;

    private Integer createBy;

    private LocalDateTime updateTime;

    private Integer updateBy;


}
