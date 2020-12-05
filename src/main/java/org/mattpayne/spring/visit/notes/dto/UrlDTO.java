package org.mattpayne.spring.visit.notes.dto;

import org.mattpayne.spring.visit.notes.entity.Tag;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UrlDTO {
    private Long id;

    @NotNull
    private String url;
    private String tagsStr; // blank space delimited...
    private Set<TagDTO> tags = new HashSet<>();

    public UrlDTO() {}
    public UrlDTO(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlDTO urlDTO = (UrlDTO) o;
        return Objects.equals(url, urlDTO.url);
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

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public String getTagsStr() {
        return tagsStr;
    }

    public void setTagsStr(String tagsStr) {
        this.tagsStr = tagsStr;
    }
}
