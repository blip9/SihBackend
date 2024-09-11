package com.taurus.sih.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="office_locations")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class OfficeLocations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long officeId;

    @Column(nullable = false, length = 100)
    private String officeName;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = false)
    private double radius = 200.0;

    @Column(nullable = false, length = 255)
    private String address;


}
