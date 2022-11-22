package com.nabil.spring.mvc_hibernate_aop.dao;

/*DAO(Data Access Object) - вспомогательный компонент,
ответственный за работу с БД

При использовании аннотации @Transactional, Spring берет
на себя ответственность за открытие и закрытие транзакции

@Repository - это специализированный @Component.
Данная аннотация используется для DAO.
При поиске компонентов, Spring также будет регистрировать все DAO
с аннотацией @Repository в Spring Container-e*/

import com.nabil.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImplementation implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployee() {
        Session session = sessionFactory.getCurrentSession();
//        List<Employee> allEmployees = session.createQuery("from Employee"
//                        , Employee.class)
//                .getResultList();
        Query<Employee> query = session.createQuery("from Employee", //получить всех сотрудников из базы используя hql
                Employee.class);
        return query.getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();

        Employee employee = session.get(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("delete from Employee " +
                "where id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
