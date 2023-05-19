package com.example.Foreign.Affairs.Ministry.API.Modell;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
public class Policy extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String countryName;

    private String regionName;
    @Column(length = 10000)
    private String titleOfPolicy;
    @Column(length = 10000)
    private String detailsOfPolicy;
}
