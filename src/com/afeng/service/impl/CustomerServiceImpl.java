package com.afeng.service.impl;

import com.afeng.dao.CustomerDao;
import com.afeng.dao.impl.CustomerDaoImpl;
import com.afeng.domain.Customer;
import com.afeng.service.CustomerService;
import com.afeng.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
//创建customerDao层对象
    CustomerDao customerDao = new CustomerDaoImpl();

    /**
     * 在service层进行事务的管理
     * 调用dao层对象进行数据库的查询
     * 并处理好可能发生的异常
     * @return 所有的Customer对象集合
     */
    @Override
    public List<Customer> findAll() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> list = customerDao.findAll();
        transaction.commit();
        return list;
    }
    /**
     * 在service层进行事务的管理
     * 调用dao层对象进行数据库的查询
     * 并处理好可能发生的异常
     * @return 查询到的用户输入
     */
    @Override
    public List<Customer> findByUserInput(String custName) {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> list = customerDao.findByUserInput(custName);
        transaction.commit();
        return list;
    }
}
