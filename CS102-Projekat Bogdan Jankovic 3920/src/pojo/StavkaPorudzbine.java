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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "stavka_porudzbine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StavkaPorudzbine.findAll", query = "SELECT s FROM StavkaPorudzbine s")
    , @NamedQuery(name = "StavkaPorudzbine.findById", query = "SELECT s FROM StavkaPorudzbine s WHERE s.id = :id")
    , @NamedQuery(name = "StavkaPorudzbine.findByKolicina", query = "SELECT s FROM StavkaPorudzbine s WHERE s.kolicina = :kolicina")})
public class StavkaPorudzbine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "kolicina")
    private int kolicina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stavkaPorudzbineId")
    private List<Porudzbina> porudzbinaList;
    @JoinColumn(name = "id_leka", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lek idLeka;

    public StavkaPorudzbine() {
    }

    public StavkaPorudzbine(Integer id) {
        this.id = id;
    }

    public StavkaPorudzbine(Integer id, int kolicina) {
        this.id = id;
        this.kolicina = kolicina;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @XmlTransient
    public List<Porudzbina> getPorudzbinaList() {
        return porudzbinaList;
    }

    public void setPorudzbinaList(List<Porudzbina> porudzbinaList) {
        this.porudzbinaList = porudzbinaList;
    }

    public Lek getIdLeka() {
        return idLeka;
    }

    public void setIdLeka(Lek idLeka) {
        this.idLeka = idLeka;
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
        if (!(object instanceof StavkaPorudzbine)) {
            return false;
        }
        StavkaPorudzbine other = (StavkaPorudzbine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pojo.StavkaPorudzbine[ id=" + id + " ]";
    }
    
}
