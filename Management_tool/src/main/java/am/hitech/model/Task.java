package am.hitech.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "creator_id")
    private int creatorId;

    @Column(name = "assignee_id")
    private Integer assignedId;

    @Column(name = "summary")
    private String summary;

    @Column(name = "dsc")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "priority")
    private String priority;

    @Column(name = "progress")
    private int progress;

    @Column(name = "created_date")
    private long createdDate;

    @Column(name = "due_date")
    private Long dueDate;

    @Column(name = "estimate_time")
    private Integer estimateTime;

    @Column(name = "project_id")
    private Integer projectId;
}
