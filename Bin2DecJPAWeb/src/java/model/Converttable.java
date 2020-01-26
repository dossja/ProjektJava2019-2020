/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Default entity class for database
 *
 * @author Dominika Matyja
 * @version 1.0
 */
@Entity
@Table(name = "converttable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Converttable.findAll", query = "SELECT c FROM Converttable c")
    , @NamedQuery(name = "Converttable.findById", query = "SELECT c FROM Converttable c WHERE c.id = :id")
    , @NamedQuery(name = "Converttable.findByNumber", query = "SELECT c FROM Converttable c WHERE c.number = :number")
    , @NamedQuery(name = "Converttable.findByResult", query = "SELECT c FROM Converttable c WHERE c.result = :result")
    , @NamedQuery(name = "Converttable.findBySideOfConversion", query = "SELECT c FROM Converttable c WHERE c.sideOfConversion = :sideOfConversion")})
public class Converttable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "number")
    private String number;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "result")
    private String result;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sideOfConversion")
    private String sideOfConversion;

    public Converttable() {
    }

    public Converttable(Integer id) {
        this.id = id;
    }

    public Converttable(Integer id, String number, String result, String sideOfConversion) {
        this.id = id;
        this.number = number;
        this.result = result;
        this.sideOfConversion = sideOfConversion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSideOfConversion() {
        return sideOfConversion;
    }

    public void setSideOfConversion(String sideOfConversion) {
        this.sideOfConversion = sideOfConversion;
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
        if (!(object instanceof Converttable)) {
            return false;
        }
        Converttable other = (Converttable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Converttable[ id=" + id + " ]";
    }
    
}
