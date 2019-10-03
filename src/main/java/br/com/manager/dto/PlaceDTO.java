package br.com.manager.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PlaceDTO {
    @NonNull
    private String name;
    private String slug;
    private String city;
    private String state;
}
