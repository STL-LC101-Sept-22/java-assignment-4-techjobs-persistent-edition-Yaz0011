package org.launchcode.techjobs.persistent.models;

import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Employer extends AbstractEntity {
    @NotBlank
    @Size(min = 1, max = 255)

    private String location;
    @OneToMany
    //changed the the value from id to employer_id
    @JoinColumn(name = "employer_id")
    private List<Job> jobs;
    public Employer() {
        jobs = new ArrayList<>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
