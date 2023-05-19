package com.example.Foreign.Affairs.Ministry.API.Mailling;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class EmailDetails {

    // Class data members
    private String recipient="abdullah-alkharousi@outlook.com";
    private String msgBody="****New News Add****";
    private String subject="Forign Affairs Ministry";
    private String attachment;
    private String endDate="2023-10-17T06:00:27.407+00:00";
    private Boolean isActive=Boolean.TRUE;
    private String countryName="Oman";
    private String regionName="Muscat";
    private int id;
}