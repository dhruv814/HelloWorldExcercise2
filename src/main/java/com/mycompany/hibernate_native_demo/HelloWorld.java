/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernate_native_demo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author dhruv
 */
@Entity
@Table(name = "helloworld")
public class HelloWorld implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE1")
    @SequenceGenerator(name = "SEQUENCE1", sequenceName = "HelloWorld_id_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "message")
    private String message;

    public HelloWorld() {
    }

    public HelloWorld(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", message=" + message + '}';
    }

}
