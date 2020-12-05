package org.mattpayne.spring.visit.notes.service;

import org.mattpayne.spring.visit.notes.dto.TagDTO;
import org.mattpayne.spring.visit.notes.dto.UrlDTO;
import org.mattpayne.spring.visit.notes.entity.Tag;
import org.mattpayne.spring.visit.notes.entity.Url;
import org.mattpayne.spring.visit.notes.repository.TagRepository;
import org.mattpayne.spring.visit.notes.repository.UrlRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final TagRepository tagRepository;

    public UrlService(UrlRepository urlRepository, TagRepository tagRepository) {
        this.urlRepository = urlRepository;
        this.tagRepository = tagRepository;
    }

    @Transactional
    public Long addUrl(UrlDTO urlDTO) {
        List<String> tagStrs = new ArrayList<>();
        if (!urlDTO.getTagsStr().trim().isEmpty()) {
            String[] aray = (urlDTO.getTagsStr().split(" "));
            for (String t : aray) {
                tagStrs.add(t);
            }
        }
        return addUrl(urlDTO.getUrl(), tagStrs);
    }

    @Transactional
    public Long addUrl(String urlStr, List<String> tagStrs) {
        Url url = new Url(urlStr);
        url = urlRepository.save(url);
        for (String tagStr : tagStrs) {
            Tag tag = null;
            Optional<Tag> optional = tagRepository.findByTag(tagStr);
            tag = optional.isPresent() ? optional.get() : new Tag(tagStr);
            tag.addUrl(url);
            tagRepository.save(tag);
        }
        return url.getId();
    }

    @Transactional
    public List<UrlDTO> findAllUrls() {
        List<Url> lazyUrls = urlRepository.findAll();
        List<UrlDTO> loadedUrls = new ArrayList<>();
        for (Url lu : lazyUrls) {
            UrlDTO url = new UrlDTO(lu.getId(), lu.getUrl());
            copyTags(lu, url);
            loadedUrls.add(url);
        }
        return loadedUrls;
    }

    private void copyTags(Url lu, UrlDTO url) {
        for (Tag t : lu.getTags()) {
            url.getTags().add(new TagDTO(t.getId(), t.getTag()));
        }
    }

    // commented out @Transactional to demo an error: @Transactional
    public UrlDTO findUrlById(Long id) {
        Optional<Url> optionalUrl = urlRepository.findById(id);
        UrlDTO urlDto=null;
        if (optionalUrl.isPresent()) {
            Url lazyUrl = optionalUrl.get();
            urlDto = new UrlDTO(lazyUrl.getId(),lazyUrl.getUrl());
            copyTags(lazyUrl,urlDto);
        }
        return urlDto;
    }
}
