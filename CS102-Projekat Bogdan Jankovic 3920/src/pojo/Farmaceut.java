/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "farmaceut")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Farmaceut.findAll", query = "SELECT f FROM Farmaceut f")
    , @NamedQuery(name = "Farmaceut.findById", query = "SELECT f FROM Farmaceut f WHERE f.id = :id")
    , @NamedQuery(name = "Farmaceut.findByUsername", query = "SELECT f FROM Farmaceut f WHERE f.username = :username")
    , @NamedQuery(name = "Farmaceut.findByPassword", query = "SELECT f FROM Farmaceut f WHERE f.password = :password")})
public class Farmaceut implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    public Farmaceut() {
    }

    public Farmaceut(Integer id) {
        this.id = id;
    }

    public Farmaceut(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public Farmaceut(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof Farmaceut)) {
            return false;
        }
        Farmaceut other = (Farmaceut) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Farmaceut[ id=" + id + " ]";
    }
    
}
