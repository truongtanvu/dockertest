package com.example.demo9_account.controller;

import com.example.demo9_account.dto.AccountDTO;
import com.example.demo9_account.dto.DepartmentDTO;
import com.example.demo9_account.entity.Account;
import com.example.demo9_account.entity.Department;
import com.example.demo9_account.repository.DepartmentRepository;
import com.example.demo9_account.req.DepartmentCreateReq;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
public class DepartmentController {
    private DepartmentRepository departmentRepo;

    private ModelMapper modelMapper;

    public DepartmentController(DepartmentRepository departmentRepo, ModelMapper modelMapper){
        this.departmentRepo = departmentRepo;
        this.modelMapper = modelMapper;
    }
    @GetMapping("department")
    public ResponseEntity<?> getAll() {
        List<Department> departments = departmentRepo.findAll();

        List<DepartmentDTO> departmentDTOS = modelMapper.map(departments, new TypeToken<List<DepartmentDTO>>(){}.getType());
        return ResponseEntity.ok(departmentDTOS);
    }
    @GetMapping("department/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Optional<Department> opDepartment = departmentRepo.findById(id);

        if (opDepartment.isEmpty()) {
            return new ResponseEntity<>("Not found with id: " +id, HttpStatus.BAD_REQUEST);
        }

        Department department = opDepartment.get();
        DepartmentDTO departmentDTO = modelMapper.map(department, DepartmentDTO.class);

        return ResponseEntity.ok(departmentDTO);
    }

    @PostMapping("department")
    public  ResponseEntity<?> create(@RequestBody DepartmentCreateReq departmentCreateReq){
        Optional<Department> opDepartment = departmentRepo.findByDepartment(departmentCreateReq.getDepartment());
        if (opDepartment.isPresent()){
            return new ResponseEntity<>("Department Name is exists: " + departmentCreateReq.getDepartment(), HttpStatus.BAD_REQUEST);
        }
        Department department = modelMapper.map(departmentCreateReq, Department.class);
        departmentRepo.save(department);
        return ResponseEntity.ok("Create Successfully: " +department.getDepartment());
    }
}
