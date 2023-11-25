package com.example.workbridgeback.controller;

import com.example.workbridgeback.configuration.JwtAuthenticationFilter;
import com.example.workbridgeback.dao.ProjectDao;
import com.example.workbridgeback.dao.UserDao;
import com.example.workbridgeback.entity.ImageModel;
import com.example.workbridgeback.entity.Project;
import com.example.workbridgeback.entity.User;
import com.example.workbridgeback.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserDao userDao;

    /*@PreAuthorize("hasRole('Admin')")*/
    @PostMapping(value = {"/addNewProject"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Project addNewProject(@RequestPart("project") Project project,
                                 @RequestPart("imageFile") MultipartFile[] file) {


        try {
            String currentUser = JwtAuthenticationFilter.CURRENT_USER;
            User user = userDao.findByEmail( currentUser).get();


            project.setUser(user);

            Set<ImageModel> images = uplodImage(file);

            project.setProjectImages(images);
            return projectService.addNewProject(project);
        } catch (Exception e) {

            System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+e.getMessage());
            return project;
        }


    }


    public Set<ImageModel> uplodImage(MultipartFile[] multipartFiles) throws IOException{

        Set<ImageModel> imageModels = new HashSet<>();

        for(MultipartFile file: multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes());
            imageModels.add(imageModel);
        }
        return imageModels;
    }
   /* @PreAuthorize("hasRole('Admin')")*/
    @DeleteMapping({"/deleteProjectDetails/{projectId}"})
    public void deleteProjectDetailes(@PathVariable("projectId") Integer projectId) {
        projectService.deleteProjectDetails(projectId);
    }

/*

    @GetMapping({"/getProjectByUser"})
    public List<Project> getProjectByUser() {
        System.out.println("ekhem user ");
        return projectService.getProjectByUser();

    }

    @GetMapping({"/getAllProjects"})
    public List<Project> getAllProjects(@RequestParam(defaultValue = "0") int pageNumber
            , @RequestParam(defaultValue = "") String searchKey) {
        return projectService.getAllProjects(pageNumber, searchKey);
    }
    @GetMapping({"/getProjectDetailsById/{projectId}"})
    public Project getProjectDetailsById(@PathVariable("projectId") Integer projectId) {

        return projectService.getProjectDetailsById(projectId);

    }


    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProductDetails/{isSingeProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable(name="isSingeProductCheckout") boolean isSingeProductCheckout,
                                           @PathVariable(name= "productId") Integer productId) {

        return productService.getProductDetails(isSingeProductCheckout, productId);


    }
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteProjectDetails/{projectId}"})
    public void deleteProjectDetailes(@PathVariable("projectId") Integer projectId) {
        projectService.deleteProjectDetails(projectId);
    }
*/

}