package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tag;
    private String name;

}
