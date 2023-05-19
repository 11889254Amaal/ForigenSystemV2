package com.example.Foreign.Affairs.Ministry.API.Modell;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class News extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String countryName;

    private String regionName;

    @Column(length = 10000)
    private String topicOfNews;

    @Column(length = 10000)
    private String detailsOfNews;
}
