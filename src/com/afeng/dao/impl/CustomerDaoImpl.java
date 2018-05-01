package com.afeng.dao.impl;

import com.afeng.dao.CustomerDao;
import com.afeng.domain.Customer;
import com.afeng.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public List<Customer> findAll() {
        //在数据库中进行查询
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();




        return null;
    }
}
