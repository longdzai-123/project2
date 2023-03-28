package com.example.project2.rest;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache")
public class RestCacheController {
	
	@Autowired
	CacheManager cacheManager;
    
	@GetMapping("/")
	public List<String> getCache() {
      
       
//     RestTemplate : GOI API CUA server khac de check du lieu
//     Webclient 
//       RestTemplate restTemplate = new RestTemplate();
//       ResponseDTO<PageDTO<UserDTO>> responseDTO = restTemplate.getForObject("http://localhost:8081/api/user/get/18",ResponseDTO.class);      
//       System.out.println(responseDTO.getStatus());
       
       List<String> cacheNames = cacheManager.getCacheNames().stream().collect(Collectors.toList());
	   return cacheNames;
	}
	
	@DeleteMapping("/{name}")
	public void deleteCache(@PathVariable("name") String name) {
		Cache cache = cacheManager.getCache(name);
		if(cache != null) {
			cache.clear(); // xoa het du lieu trong cache cua users di  
		}
	}
}
