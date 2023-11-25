package com.example.workbridgeback.service;

import com.example.workbridgeback.dao.ProjectDao;
import com.example.workbridgeback.dao.UserDao;
import com.example.workbridgeback.entity.ImageModel;
import com.example.workbridgeback.entity.Project;
import com.example.workbridgeback.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.workbridgeback.configuration.JwtAuthenticationFilter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectService {
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private UserDao userDao;

    public Project addNewProject(Project project) {


        return projectDao.save(project);
    }

    public void deleteProjectDetails(Integer id) {projectDao.deleteById(id);
    }
    public Project getProjectDetailsById(Integer id) {

        return projectDao.findById(id).get();
    }
    public List<Project> getAllProjects(int pageNumber, String searchKey){
        Pageable pageable = PageRequest.of(pageNumber, 8);

        if(searchKey.equals("")) {
            return (List<Project>) projectDao.findAll(pageable);
        }else {
            return (List<Project>)projectDao.findByProjectNameContainingIgnoreCaseOrProjectDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
        }

    }

/*



    public List<Project> getProjectByUser() {
        String currentUser = JwtAuthenticationFilter.CURRENT_USER;
        User user = userDao.findByEmail( currentUser).get();

        return projectDao.findByUser(user);
    }


*/
}
