package com.example.StackOverflow.controllers;

import com.example.StackOverflow.dtos.TagDTO;
import com.example.StackOverflow.services.ITagService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/tag")
@AllArgsConstructor
public class TagController {
    private final ITagService tagService;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getTags() {
        List<TagDTO> dtos = tagService.getAllTags();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TagDTO> getTag(@PathVariable("id") Long id) {
        TagDTO dto = tagService.findTagById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertTag(@RequestBody TagDTO tagDTO) {
        Long id = tagService.insertTag(tagDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteTag(@PathVariable("id") Long id)
    {
        tagService.deleteTag(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<TagDTO> editTag(@RequestBody TagDTO tagDTO)
    {
        TagDTO dto = tagService.editTag(tagDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/byQuestion/{id}")
    public ResponseEntity<List<TagDTO>> getTagsByQuestion(@PathVariable("id") Long questionId) {
        List<TagDTO> dtos = tagService.getTagsByQuestion(questionId);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
