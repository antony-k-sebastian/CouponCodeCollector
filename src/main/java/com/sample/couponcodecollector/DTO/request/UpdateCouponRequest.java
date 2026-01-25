package com.sample.couponcodecollector.DTO.request;

import java.time.LocalDate;

import com.sample.couponcodecollector.Enum.UsageType;
import com.sample.couponcodecollector.Enum.Visibility;

public class UpdateCouponRequest {
    private String description;
    private String company;
    private Integer maxUsage;
    private Visibility visibility;
    private String code;
    private UsageType usageType;
    private LocalDate expiryDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getMaxUsage() {
        return maxUsage;
    }

    public void setMaxUsage(Integer maxUsage) {
        this.maxUsage = maxUsage;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
