package kelvipadilla.me.crm.services;

import kelvipadilla.me.crm.dtos.ProjectDto;
import kelvipadilla.me.crm.models.Client;
import kelvipadilla.me.crm.models.Project;
import kelvipadilla.me.crm.repositories.ClientRepository;
import kelvipadilla.me.crm.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ClientRepository clientRepository) {
        this.projectRepository = projectRepository;
        this.clientRepository = clientRepository;
    }

    public Page<Project> findAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Optional<Project> findProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    public Project createProject(ProjectDto projectDto) {
        Client client = clientRepository.findById(projectDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + projectDto.getClientId()));

        Project newProject = new Project();
        newProject.setClient(client);
        newProject.setName(projectDto.getName());
        newProject.setDescription(projectDto.getDescription());
        newProject.setStatus(projectDto.getStatus());
        return projectRepository.save(newProject);
    }

    public Optional<Project> updateProject(Integer id, ProjectDto updatedProjectDto) {
        return projectRepository.findById(id)
                .map(project -> {
                    if (updatedProjectDto.getClientId() != null) {
                        Client client = clientRepository.findById(updatedProjectDto.getClientId())
                                .orElseThrow(() -> new RuntimeException("Client not found with id: " + updatedProjectDto.getClientId()));
                        project.setClient(client);
                    }
                    project.setName(updatedProjectDto.getName());
                    project.setDescription(updatedProjectDto.getDescription());
                    project.setStatus(updatedProjectDto.getStatus());
                    return projectRepository.save(project);
                });
    }

    public boolean deleteProject(Integer id) {
        return projectRepository.findById(id)
                .map(project -> {
                    projectRepository.delete(project);
                    return true;
                }).orElse(false);
    }
}
