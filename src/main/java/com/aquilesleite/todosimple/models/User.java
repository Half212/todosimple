package com.aquilesleite.todosimple.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id",unique = true)
    private Long id;

    @Column(name= "username",length=100,nullable = false,unique = true)
    @NotBlank(groups = CreateUser.class)
    @Size(groups = CreateUser.class, min =2, max=100)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password",length = 50,nullable = false)
    @NotBlank(groups = {CreateUser.class,UpdateUser.class})
    @Size(groups = {CreateUser.class,UpdateUser.class}, min=8,max=50)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(groups = {CreateUser.class, UpdateUser.class}) @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 50) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(groups = {CreateUser.class, UpdateUser.class}) @Size(groups = {CreateUser.class, UpdateUser.class}, min = 8, max = 50) String password) {
        this.password = password;
    }

    public @NotBlank(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 100) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 100) String username) {
        this.username = username;
    }
     @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User user))
            return false;
        User other = (User) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
            else if (!this.id.equals(other.id))
                return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);

    }

    @Override
    public int hashCode() {
        final int prime = 61;
        int result = 1;
        result = prime*result + ((this.id == null ) ? 0 : this.id.hashCode());
        return result;
    }
}

