package com.pm.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull(message = "name is required")
    @Size(min = 2,max = 35,message = "name character must be between 2 and 35")
    private String name;

    @NotNull(message = "email is required")
    @Email
    @Column(unique = true)
    private String email;

    @NotNull(message = "address is required")
    @Size(min = 5,max = 100,message = "address must be have 5 character and max 100 character")
    private String address;

    @NotNull(message = "gender is required")
    private String gender;

    @NotNull(message = "date of birth required")
    private LocalDate dateOfBirth;

    @NotNull(message = "register date is required")
    private LocalDate registerDate;

}
