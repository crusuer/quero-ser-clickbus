package br.com.manager.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Place {
    @Id
    @Column
    @NotNull
    private String name;
    @Column
    private String slug;
    @Column
    private String city;
    @Column
    private String state;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date updatedAt;
}
