package com.taurus.sih.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "check_in_out_events")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CheckInOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private java.sql.Timestamp checkInTime;


    @Column(nullable = true)
    private java.sql.Timestamp checkOutTime;

    @Column(nullable = false)
    private double checkInLatitude;

    @Column(nullable = false)
    private double checkInLongitude;

    @Column(nullable = true)
    private Double checkOutLatitude;

    @Column(nullable = true)
    private Double checkOutLongitude;

    @ManyToOne
    @JoinColumn(name = "office_id")
    private OfficeLocations office;

    @Column(nullable = false)
    private boolean isManual = false;

}
