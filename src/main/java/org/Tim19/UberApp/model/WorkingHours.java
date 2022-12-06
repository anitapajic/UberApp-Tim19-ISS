package org.Tim19.UberApp.model;

import javax.persistence.*;

@Entity(name = "working_hours")
public class WorkingHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "start", nullable = false)
    private String start;

    @Column(name = "endd", nullable = false)
    private String endd;

    public WorkingHours() {
        super();
    }

    public WorkingHours(Integer id, String start, String end) {
        super();
        this.id = id;
        this.start = start;
        this.endd = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return endd;
    }

    public void setEnd(String end) {
        this.endd = end;
    }
}
