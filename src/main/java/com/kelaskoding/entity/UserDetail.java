package com.kelaskoding.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kelaskoding.converter.StringAttributeConverter;

import org.hibernate.annotations.ColumnTransformer;

import lombok.Data;

@Entity
@Table(name = "tbl_users")
@Data
public class UserDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @ColumnTransformer(
        read = "AES_DECRYPT(UNHEX(email), 'this-is-test-key')", 
        write = "HEX(AES_ENCRYPT(?, 'this-is-test-key'))")
    @Column(length = 200, nullable = false, unique = true)
    private String email;
    
    @Convert(converter = StringAttributeConverter.class)
    @Column(length = 200, nullable = false, unique = true)
    private String cardNumber;

    @Column(length = 200, nullable = false)
    private String expiry;
}
