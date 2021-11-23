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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Korisnik
 */
@Entity
@Table(name = "porudzbina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porudzbina.findAll", query = "SELECT p FROM Porudzbina p")
    , @NamedQuery(name = "Porudzbina.findById", query = "SELECT p FROM Porudzbina p WHERE p.id = :id")})
public class Porudzbina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "klijent_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Klijent klijentId;
    @JoinColumn(name = "stavka_porudzbine_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StavkaPorudzbine stavkaPorudzbineId;

    public Porudzbina() {
    }

    public Porudzbina(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klijent getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Klijent klijentId) {
        this.klijentId = klijentId;
    }

    public StavkaPorudzbine getStavkaPorudzbineId() {
        return stavkaPorudzbineId;
    }

    public void setStavkaPorudzbineId(StavkaPorudzbine stavkaPorudzbineId) {
        this.stavkaPorudzbineId = stavkaPorudzbineId;
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
        if (!(object instanceof Porudzbina)) {
            return false;
        }
        Porudzbina other = (Porudzbina) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.Porudzbina[ id=" + id + " ]";
    }
    
}
