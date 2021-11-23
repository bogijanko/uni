/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "lek")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lek.findAll", query = "SELECT l FROM Lek l")
    , @NamedQuery(name = "Lek.findById", query = "SELECT l FROM Lek l WHERE l.id = :id")
    , @NamedQuery(name = "Lek.findByNaziv", query = "SELECT l FROM Lek l WHERE l.naziv = :naziv")
    , @NamedQuery(name = "Lek.findByProizvodjac", query = "SELECT l FROM Lek l WHERE l.proizvodjac = :proizvodjac")
    , @NamedQuery(name = "Lek.findByJacina", query = "SELECT l FROM Lek l WHERE l.jacina = :jacina")
    , @NamedQuery(name = "Lek.findByCena", query = "SELECT l FROM Lek l WHERE l.cena = :cena")
    , @NamedQuery(name = "Lek.findByKolicina", query = "SELECT l FROM Lek l WHERE l.kolicina = :kolicina")})
public class Lek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "naziv")
    private String naziv;
    @Basic(optional = false)
    @Column(name = "proizvodjac")
    private String proizvodjac;
    @Basic(optional = false)
    @Column(name = "jacina")
    private int jacina;
    @Basic(optional = false)
    @Column(name = "cena")
    private int cena;
    @Basic(optional = false)
    @Column(name = "kolicina")
    private int kolicina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idLeka")
    private List<StavkaPorudzbine> stavkaPorudzbineList;

    public Lek() {
    }

    public Lek(Integer id) {
        this.id = id;
    }

    public Lek(Integer id, String naziv, String proizvodjac, int jacina, int cena, int kolicina) {
        this.id = id;
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
        this.jacina = jacina;
        this.cena = cena;
        this.kolicina = kolicina;
    }
     public Lek(Integer id, int kolicina) {
        this.id = id;
        this.kolicina = kolicina;
    }
    public Lek(String naziv, String proizvodjac, int jacina, int cena, int kolicina) {
        this.naziv = naziv;
        this.proizvodjac = proizvodjac;
        this.jacina = jacina;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getJacina() {
        return jacina;
    }

    public void setJacina(int jacina) {
        this.jacina = jacina;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @XmlTransient
    public List<StavkaPorudzbine> getStavkaPorudzbineList() {
        return stavkaPorudzbineList;
    }

    public void setStavkaPorudzbineList(List<StavkaPorudzbine> stavkaPorudzbineList) {
        this.stavkaPorudzbineList = stavkaPorudzbineList;
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
        if (!(object instanceof Lek)) {
            return false;
        }
        Lek other = (Lek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv + " " + jacina + "mg";
    }
    
}
