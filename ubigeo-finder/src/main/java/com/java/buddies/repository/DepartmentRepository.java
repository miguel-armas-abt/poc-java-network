package com.java.buddies.repository;

import com.java.buddies.commons.JsonFileReader;
import com.java.buddies.commons.PropertiesReader;
import com.java.buddies.dto.DepartmentDTO;
import java.util.Comparator;
import java.util.List;

public class DepartmentRepository {

  private final PropertiesReader propertiesReader;
  private final JsonFileReader jsonFileReader;

  public DepartmentRepository() {
    propertiesReader = new PropertiesReader();
    jsonFileReader = new JsonFileReader();
  }

  public DepartmentDTO findByDepartmentId(String departmentId) {
    List<DepartmentDTO> departments = findAll();
    for (DepartmentDTO department: departments) {
      if(department.getId().equals(departmentId))
        return department;
    }
    throw new IllegalArgumentException("No such department");
  }

  public List<DepartmentDTO> findAll() {
    String filePath = propertiesReader.getProperty("ubigeo.departments.path");
    List<DepartmentDTO> departments = jsonFileReader.readListFromFile(DepartmentDTO.class, filePath);
    departments.sort(Comparator.comparing(DepartmentDTO::getId));
    return departments;
  }
}
