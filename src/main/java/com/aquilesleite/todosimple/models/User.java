package com.aquilesleite.todosimple.models;


import com.aquilesleite.todosimple.models.enums.ProfileEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = User.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data

public class User {
  

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id",unique = true)
    private Long id;

    @Column(name= "username",length=100,nullable = false,unique = true)
    @NotBlank()
    @Size(min =2, max=100)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password",length = 100,nullable = false)
    @NotBlank()
    @Size(min=8,max=50)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Task> tasks = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CollectionTable(name="user_profile")
    @Column(name = "profile", nullable = false)
    private Set<Integer> profiles = new HashSet<>();

    public Set<ProfileEnum> getProfiles(){
        return this.profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(ProfileEnum profileEnum){
        this.profiles.add(profileEnum.getCode()); 
    }

}

