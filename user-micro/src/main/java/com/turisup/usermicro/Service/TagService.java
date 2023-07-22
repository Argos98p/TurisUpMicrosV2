package com.turisup.usermicro.Service;

import com.turisup.usermicro.Model.Tag;
import com.turisup.usermicro.Model.User;
import com.turisup.usermicro.Repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    public List<Tag> allTags(){
        return  tagRepository.findAll();
    }

    public Tag addTag(Tag tag){
        return tagRepository.save(tag);
    }

    public Optional<Tag> findById(String id){
        return tagRepository.findById(id);
    }
}
