package com.sample.couponcodecollector.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sample.couponcodecollector.Enum.UsageType;
import com.sample.couponcodecollector.Enum.Visibility;

import jakarta.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "company")
    private String company;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "usage_type")
    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    @Column(name = "max_usage", nullable = true)
    private Integer maxUsage;

    @Column(name = "visibility", nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // Empty Constructors required by JPA
    public Coupon() {
    }

    // All Args Constructor
    public Coupon(String code, String description, UsageType usageType, Integer maxUsage, Visibility visibility, LocalDate expiryDate, String company) {
        this.code = code;
        this.description = description;
        this.usageType = usageType;
        this.maxUsage = maxUsage;
        this.visibility = visibility;
        this.expiryDate = expiryDate;
        this.company = company;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}