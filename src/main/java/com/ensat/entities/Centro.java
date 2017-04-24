/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensat.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "centro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centro.findAll", query = "SELECT c FROM Centro c")
    , @NamedQuery(name = "Centro.findByJefeBrigada", query = "SELECT c FROM Centro c WHERE c.jefeBrigada = :jefeBrigada")
    , @NamedQuery(name = "Centro.findByName", query = "SELECT c FROM Centro c WHERE c.name = :name")
    , @NamedQuery(name = "Centro.findById", query = "SELECT c FROM Centro c WHERE c.id = :id")})
public class Centro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "jefe_brigada")
    private String jefeBrigada;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "centroIdId")
    private List<Agente> agenteList;

    public Centro() {
    }

    public Centro(Integer id) {
        this.id = id;
    }

    public String getJefeBrigada() {
        return jefeBrigada;
    }

    public void setJefeBrigada(String jefeBrigada) {
        this.jefeBrigada = jefeBrigada;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Agente> getAgenteList() {
        return agenteList;
    }

    public void setAgenteList(List<Agente> agenteList) {
        this.agenteList = agenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centro)) {
            return false;
        }
        Centro other = (Centro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "presupuesto.controller.Centro[ id=" + id + " ]";
    }
    
}
