package comptedit_db;
// Generated 3 juin 2014 00:48:18 by Hibernate Tools 3.6.0



/**
 * Entreprise generated by hbm2java
 */
public class Entreprise  implements java.io.Serializable {


     private Long idEntreprise;
     private String nameEntreprise;
     private String descriptionEntreprise;

    public Entreprise() {
    }

    public Entreprise(String nameEntreprise, String descriptionEntreprise) {
       this.nameEntreprise = nameEntreprise;
       this.descriptionEntreprise = descriptionEntreprise;
    }
   
    public Long getIdEntreprise() {
        return this.idEntreprise;
    }
    
    public void setIdEntreprise(Long idEntreprise) {
        this.idEntreprise = idEntreprise;
    }
    public String getNameEntreprise() {
        return this.nameEntreprise;
    }
    
    public void setNameEntreprise(String nameEntreprise) {
        this.nameEntreprise = nameEntreprise;
    }
    public String getDescriptionEntreprise() {
        return this.descriptionEntreprise;
    }
    
    public void setDescriptionEntreprise(String descriptionEntreprise) {
        this.descriptionEntreprise = descriptionEntreprise;
    }




}

