package org.mattpayne.spring.visit.notes.dto;

import org.mattpayne.spring.visit.notes.entity.Url;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TagDTO {
    private Long id;
    private String tag;
    private Set<UrlDTO> urls = new HashSet<>();

    public TagDTO() {}
    public TagDTO(Long id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<UrlDTO> getUrls() {
        return urls;
    }

    public void setUrls(Set<UrlDTO> urls) {
        this.urls = urls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDTO tagDTO = (TagDTO) o;
        return Objects.equals(tag, tagDTO.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
