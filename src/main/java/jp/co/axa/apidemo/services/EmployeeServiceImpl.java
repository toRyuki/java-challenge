package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


/**
 * employee service class
 * class which used to handle table data related to employee table
 * @author ryu
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository; 

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Get all employees
     * @return　employees
     */
    @Transactional
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /**
     * Get employee with an employeeId
     * @param　employeeId Long
     * @return　Employee
     */
    @Transactional
    public Employee getEmployee(Long employeeId){
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        if(optEmp.isPresent()){
            return optEmp.get();
        } else {
            throw new EmployeeResourceNotFoundException(employeeId);
        }
    }

    /**
     * Insert an employee
     * @param　employee Employee
     */
    @Transactional
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    /**
     * Delete an employee
     * @param　employee Long
     */
    @Transactional
    public void deleteEmployee(Long employeeId){
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        if(optEmp.isPresent()){
            employeeRepository.deleteById(employeeId);
        }else{
            throw new EmployeeResourceNotFoundException(employeeId);
        }

    }

    /**
     * Update an employee
     * @param　employee Employee
     * @return  Boolean
     */
    @Transactional
    public Boolean updateEmployee(Employee employee){
     
        Employee entity = new Employee();
        copyBeanToEntityForUpdate(employee, entity);
        Employee saveEntity = employeeRepository.save(entity);
        if(saveEntity.getId()!=null){
          return true;
        } else {
          return false;
        }
            
    }

    private void copyBeanToEntityForUpdate(Employee employee, Employee entity) {
   
        entity.setId(employee.getId());

        Optional<String> department = Optional.ofNullable(employee.getDepartment());
        entity.setDepartment(department.orElse(""));
        
        Optional<String> name = Optional.ofNullable(employee.getName());
        entity.setName(name.orElse(""));

        Optional<Integer> salary = Optional.ofNullable(employee.getSalary());
        entity.setSalary(salary.orElse(0));

      }

      

}