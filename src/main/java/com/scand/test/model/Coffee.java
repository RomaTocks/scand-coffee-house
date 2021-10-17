package com.scand.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@MappedSuperclass
public abstract class Coffee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
