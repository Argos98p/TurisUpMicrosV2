package com.turisup.resourcesservice.Controller;

import com.turisup.resourcesservice.Model.Tag;
import com.turisup.resourcesservice.Service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resources")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/tag/add")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag){
        Tag tag2 = tagService.addTag(tag);
        return new ResponseEntity<>(tag2, HttpStatus.OK);
    }

    @GetMapping("/tag/all")
    public ResponseEntity<List<Tag>> allUser(){
        List<Tag> tags = tagService.allTags();
        return new ResponseEntity<>(tags,HttpStatus.OK);
    }
}
