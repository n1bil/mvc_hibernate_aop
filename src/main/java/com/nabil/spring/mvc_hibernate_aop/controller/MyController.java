package com.nabil.spring.mvc_hibernate_aop.controller;

import com.nabil.spring.mvc_hibernate_aop.Service.EmployeeService;
import com.nabil.spring.mvc_hibernate_aop.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/")
    public String showAllEmployees(Model model) { //создаем Model
        List<Employee> allEmployees =  employeeService.getAllEmployees();
        model.addAttribute("allEmps", allEmployees); //добавляем в Model attribute, который называется allEmps, значение allEmployees(все наши работники)
                                                                //view будет использовать данную модель, возьмет из аттрибута всю информацию для отображения в браузере
        return "all-employees";
    }

    @RequestMapping("/addNewEmployee")
    public String addNewEmployee(Model model) {
        Employee employee = new Employee(); //создаем нового сотрудника, поля пусты
        model.addAttribute("employee", employee); //когда будем заполнять форму на сайте, то будем добавлять информацию этому работнику

        return "employee-info";
    }

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        employeeService.saveEmployee(employee);

        return "redirect:/"; //перенаправление на начальную страницу(начальная страница - /)
    }

    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model) {

        Employee employee = employeeService.getEmployee(id); //получаем уже заполненого работника
        model.addAttribute("employee", employee);

        return "employee-info";
    }

    @RequestMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("empId") int id) {

        employeeService.deleteEmployee(id);

        return "redirect:/";
    }
}
