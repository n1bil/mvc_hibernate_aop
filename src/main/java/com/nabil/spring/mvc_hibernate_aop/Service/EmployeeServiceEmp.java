package com.nabil.spring.mvc_hibernate_aop.Service;

/*Аннотация @Service отмечает класс, содержащий бизнес-логику
В иерархии компонентов приложения Service является
соединительным звеном между Controller-ом и DAO

@Service - это специализированный @Component.
При поиске компонентов, Spring также будет регистрировать
все классы с аннотацией @Service в Spring Container-e*/

import com.nabil.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.nabil.spring.mvc_hibernate_aop.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceEmp implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployee();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
}
