package org.danieska.entity;

import jakarta.persistence.*;
@Entity
@Table(name = "suppilier01")
public class Supplier01 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "celular")
    private String celular;

    @Column(name = "direccion")
    private String direccion;

    public Supplier01() {
    }

    public Supplier01(String primerNombre, String celular, String direccion) {
        this.primerNombre = primerNombre;
        this.celular = celular;
        this.direccion = direccion;

    }
    public int getId(){return id;}

    public void setId(int id){this.id = id;}

    public String getPrimerNombre(){return primerNombre;}

    public void  setPrimerNombre(String primerNombre){this.primerNombre = primerNombre;}

    public String getCelular(){return celular;}

    public void setCelular(String celular){this.celular = celular; }

    public String getDireccion(){return direccion;}

    public void setDireccion(String direccion){this.direccion = direccion;}

    @Override
    public String toString(){
        return "Supplier{" +
                "id=" + id +
                ", primerNombre='" + primerNombre + '\'' +
                ", celular='" + celular + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
