package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.example.demo.validations.OnAdd;
import com.example.demo.validations.OnUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Mascota de la tienda de mascotas")
@Entity
@Data
public class Pet {

    @ApiModelProperty(notes = "Identificador interno de la mascota", example = "1234")
    @Null(groups = OnAdd.class)
    @NotNull(groups = OnUpdate.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tag;
    private String name;

    @ApiModelProperty(notes = "Edad de la mascota entre 1 y 5 años", example = "2")
    @Min(1)
    @Max(5)
    private Integer age;

    @ApiModelProperty(notes = "Direccion ip de la mascota robot", example = "127.0.0.1")
    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ip;
}
