/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import exceptions.BrojKnjiziceNijeValidanException;
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
@Table(name = "klijent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Klijent.findAll", query = "SELECT k FROM Klijent k")
    , @NamedQuery(name = "Klijent.findById", query = "SELECT k FROM Klijent k WHERE k.id = :id")
    , @NamedQuery(name = "Klijent.findByIme", query = "SELECT k FROM Klijent k WHERE k.ime = :ime")
    , @NamedQuery(name = "Klijent.findByPrezime", query = "SELECT k FROM Klijent k WHERE k.prezime = :prezime")
    , @NamedQuery(name = "Klijent.findByBrojKnjizice", query = "SELECT k FROM Klijent k WHERE k.brojKnjizice = :brojKnjizice")})
public class Klijent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @Column(name = "broj_knjizice")
    private String brojKnjizice;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klijentId")
    private List<Porudzbina> porudzbinaList;

    public Klijent() {
    }

    public Klijent(Integer id) {
        this.id = id;
    }

    public Klijent(Integer id, String ime, String prezime, String brojKnjizice) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.brojKnjizice = brojKnjizice;
    }
    public Klijent(String ime, String prezime, String brojKnjizice) throws BrojKnjiziceNijeValidanException {
        this.ime = ime;
        this.prezime = prezime;
        this.setBrojKnjizice(brojKnjizice);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojKnjizice() {
        return brojKnjizice;
    }

    public void setBrojKnjizice(String brojKnjizice) throws BrojKnjiziceNijeValidanException {
      if (brojKnjizice.length() < 11 || brojKnjizice.length() > 13) {
            throw new BrojKnjiziceNijeValidanException();
        }
        this.brojKnjizice = brojKnjizice;
    }

    @XmlTransient
    public List<Porudzbina> getPorudzbinaList() {
        return porudzbinaList;
    }

    public void setPorudzbinaList(List<Porudzbina> porudzbinaList) {
        this.porudzbinaList = porudzbinaList;
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
        if (!(object instanceof Klijent)) {
            return false;
        }
        Klijent other = (Klijent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Klijent[ id=" + id + " ]";
    }
    
}
