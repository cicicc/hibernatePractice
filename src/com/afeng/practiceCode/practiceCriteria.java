package com.afeng.practiceCode;

import com.afeng.domain.Customer;
import com.afeng.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

/**
 * criteria是一个完全面向对象的 可扩展的条件查询语句
 * 注: 仅用于查询
 */
public class practiceCriteria {
    //查询customer表中所有的信息并打印
    @Test
    public void testCriteria1(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //进行criteria语句查询
        Criteria criteria = session.createCriteria(Customer.class);
        List<Customer> list = criteria.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }

        //提交事务
        HibernateUtils.commitTransaction();
    }
    //查询Customer表中id特定的对象信息
    //注:此处用的ideq,hibernate会自动寻找表中的主键
    @Test
    public void testCriteria2(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //进行criteria语句查询
        Criteria criteria = session.createCriteria(Customer.class);
        List<Customer> list = criteria.add(Restrictions.idEq(6)).list();
        System.out.println(list);

        //提交事务
        HibernateUtils.commitTransaction();
    }

    //between条件查询
    @Test
    public void testCriteria3(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //进行criteria语句查询
        Criteria criteria = session.createCriteria(Customer.class);
        List<Customer> list = criteria.add(Restrictions.between("cust_id", 2, 8)).list();
        System.out.println(list);

        //提交事务
        HibernateUtils.commitTransaction();
    }

    //eq条件查询
    @Test
    public void testCriteria4(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //进行criteria语句查询
        Criteria criteria = session.createCriteria(Customer.class);
        List<Customer> list = criteria.add(Restrictions.eq("cust_source", "self")).list();
        System.out.println(list);

        //提交事务
        HibernateUtils.commitTransaction();
    }
    //分页查询
    @Test
    public void testCriteria5(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //进行criteria语句查询
        Criteria criteria = session.createCriteria(Customer.class);
//        criteria.add(Restrictions.)
        //设置第一个返回的数据在总数据集中的位置 从零开始
        criteria.setFirstResult(5);
        List<Customer> list = criteria.setMaxResults(3).list();
        System.out.println(list);

        //提交事务
        HibernateUtils.commitTransaction();
    }
    //返回唯一的查询结果 若结果不是唯一则报错
    @Test
    public void testCriteria6(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //进行criteria语句查询 结果集不是唯一对象但是使用uniqueResult会报错
//        Criteria criteria = session.createCriteria(Customer.class);
//        Object result = criteria.uniqueResult();
//        System.out.println(result);
        Criteria criteria = session.createCriteria(Customer.class);
        Customer result = (Customer) criteria.add(Restrictions.idEq(6)).uniqueResult();
        System.out.println(result);
        //提交事务
        HibernateUtils.commitTransaction();
    }

    //Criteria排序查询
    @Test
    public void testCriteria7(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //进行criteria语句查询
        Criteria criteria = session.createCriteria(Customer.class);
        List list = criteria.addOrder(Order.asc("cust_id")).list();
        System.out.println(list);

        //提交事务
        HibernateUtils.commitTransaction();
    }
    //Criteria统计查询
    @Test
    public void testCriteria8(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //进行criteria语句查询
        Criteria criteria = session.createCriteria(Customer.class);
        List list = criteria.setProjection(Projections.avg("cust_id")).list();
        System.out.println(list);

        //提交事务
        HibernateUtils.commitTransaction();
    }
    //离线查询
    @Test
    public void testCriteria9(){
        //创建离线查询对象
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
        //添加查询条件
        criteria.setProjection(Projections.count("cust_id"));
        //获得session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        Transaction transaction = session.beginTransaction();
        //将session对象与criteria对象进行绑定
        List list = criteria.getExecutableCriteria(session).list();
        System.out.println(list);
        //提交事务
        transaction.commit();
    }
}
