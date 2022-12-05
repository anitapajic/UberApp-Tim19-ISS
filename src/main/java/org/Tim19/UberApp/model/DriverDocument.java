package org.Tim19.UberApp.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DriverDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="docName",unique = true, nullable = false)
    private String docName;

    @Column(name = "documentImage", nullable = false)
    private String documentImage;

    @Column(name="driverId", nullable = false)
    private Integer driverId;


    public DriverDocument(){super();}

    public DriverDocument(String docName, String documentImage, Integer driverId) {
        super();
        this.docName = docName;
        this.documentImage = documentImage;
        this.driverId = driverId;
    }


    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DriverDocument d = (DriverDocument) o;
        if (d.driverId == null || driverId == null) {
            return false;
        }
        return Objects.equals(driverId, d.driverId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(driverId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + docName + '\'' +
                ", lastname='" + documentImage + '\'' +
                ", profilePicture='" + driverId + '\'' +
                '}';
    }
}
