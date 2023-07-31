package am.hitech.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "language")
    private String language;

    @Column(name = "version")
    private String version;

    @Column(name = "team_lead_id")
    private Integer teamLeadId;

    @Column(name = "progress")
    private Integer progress;
}
