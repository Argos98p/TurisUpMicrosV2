package com.turisup.usermicro.Controller;

import com.turisup.usermicro.Model.Tag;
import com.turisup.usermicro.Model.User;
import com.turisup.usermicro.Repository.TagRepository;
import com.turisup.usermicro.Service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/api/v2/tag/add")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag){
        Tag tag2 = tagService.addTag(tag);
        return new ResponseEntity<>(tag2, HttpStatus.OK);
    }

    @GetMapping("/api/v2/tag/all")
    public ResponseEntity<List<Tag>> allUser(){
        List<Tag> tags = tagService.allTags();
        return new ResponseEntity<>(tags,HttpStatus.OK);
    }
}
