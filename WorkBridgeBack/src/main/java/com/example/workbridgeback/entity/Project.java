package com.example.workbridgeback.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

import javax.persistence.*;
@Entity

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer projectId;
    private String projectName;
    @Column(length = 1000)
    private String projectDescription;
    private String projectCreated;

    private Integer projectDuration;
    private String projectDomain;
    private String projectState;
    private String projectTechnology;

    @ManyToMany(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
    @JoinTable(name = "project_images",
            joinColumns = {
                    @JoinColumn(name = "project_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "image_id")
            })
    private Set<ImageModel> projectImages;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Project() {
    }


    public Project(Integer projectId, String projectName, String projectDescription, String projectCreated, Integer projectDuration, String projectDomain, String projectState, String projectTechnology) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectCreated = projectCreated;
        this.projectDuration = projectDuration;
        this.projectDomain = projectDomain;
        this.projectState = projectState;
        this.projectTechnology = projectTechnology;

    }

    public Set<ImageModel> getProjectImages() {
        return projectImages;
    }

    public void setProjectImages(Set<ImageModel> projectImages) {
        this.projectImages = projectImages;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectCreated() {
        return projectCreated;
    }

    public void setProjectCreated(String projectCreated) {
        this.projectCreated = projectCreated;
    }



    public Integer getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(Integer projectDuration) {
        this.projectDuration = projectDuration;
    }

    public String getProjectDomain() {
        return projectDomain;
    }

    public void setProjectDomain(String projectDomain) {
        this.projectDomain = projectDomain;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getProjectTechnology() {
        return projectTechnology;
    }

    public void setProjectTechnology(String projectTechnology) {
        this.projectTechnology = projectTechnology;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
