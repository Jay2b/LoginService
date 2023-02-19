package com.login.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "street")
    @NotBlank(message = "*Please provide your street")
    private String street;

    @Column(name = "city")
    @NotBlank(message = "*Please provide your city")
    private String city;

    @Column(name = "postal_code")
    @NotBlank(message = "*Please provide your postal code")
    private String postalCode;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;


}
