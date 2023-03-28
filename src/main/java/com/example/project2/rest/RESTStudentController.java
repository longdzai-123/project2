package com.example.project2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project2.dto.PageDTO;
import com.example.project2.dto.ResponseDTO;
import com.example.project2.dto.StudentDTO;
import com.example.project2.service.StudentService;

@RestController
@RequestMapping("api/student")
public class RESTStudentController {
     @Autowired
     StudentService studentService;
     
     @PostMapping("/")
     public ResponseDTO<StudentDTO> add(@RequestBody StudentDTO studentDTO) throws IllegalStateException{	 
    	 studentService.create(studentDTO);   	 
    	 return ResponseDTO.<StudentDTO>builder().status(200).data(studentDTO).build();
     }
     
     @PutMapping("/")
     public ResponseDTO<StudentDTO> update(@RequestBody StudentDTO studentDTO){
    	 studentService.update(studentDTO);
    	 return ResponseDTO.<StudentDTO>builder().status(200).data(studentDTO).build();
     }
     
     @DeleteMapping("/{id}")
     public ResponseDTO<StudentDTO> delete(@PathVariable("id") int id){
    	 //studentService.delete(id);
    	 return ResponseDTO.<StudentDTO>builder().status(200).build();
     }
     
     
     @GetMapping("/search")
     public ResponseDTO<PageDTO<StudentDTO>> search(@RequestParam(name ="name", required = false) String name,@RequestParam(name="studentCode",required = false) String studentCode, @RequestParam(name="page",required = false) Integer page,@RequestParam(name="size",required = false) Integer size){
    	 
    	 size = size == null ? 10 : size;
    	 page = page == null ? 0 : page;
    	 	 
    	 PageDTO<StudentDTO> pageDTO = studentService.search(name, studentCode, page, size);
    	   	 
    	 return ResponseDTO.<PageDTO<StudentDTO>>builder().status(200).data(pageDTO).build();
     }
     
     
}
