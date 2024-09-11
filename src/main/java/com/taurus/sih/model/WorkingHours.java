package com.taurus.sih.model;

import jakarta.persistence.*;

@Entity
@Table(name = "working_hours")

public class WorkingHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private java.sql.Date date;

    @Column(nullable = false)
    private double totalWorkingHours;

    @Column(nullable = false)
    private boolean manualAdjustments = false;

    @Column(nullable = false)
    private java.sql.Timestamp lastUpdated;

}