package com.example.Foreign.Affairs.Ministry.API.RequestObject;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SchadualAddNews {

    private String endDate;

    private Boolean isActive;
    private String countryName;

    private String regionName;

}
