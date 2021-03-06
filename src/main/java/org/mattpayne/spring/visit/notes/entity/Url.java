package org.mattpayne.spring.visit.notes.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Url implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToMany(mappedBy = "urls")
    private Set<Tag> tags = new HashSet<>();

    public Url() { this.url = null; }
    public Url(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        String tagsStr;
        Set<String> tagNames = new TreeSet<>(); // sort in RAM
        for (Tag tag: this.getTags()) {
            tagNames.add(tag.getTag());
        }
        tagsStr=String.join(",", tagNames);
        return "Url{" +
                "id=" + id +
                ", url='" + this.url + '\'' +
                ", tags=" + tagsStr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url1 = (Url) o;
        return Objects.equals(url, url1.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
