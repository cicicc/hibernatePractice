package com.afeng.practiceCode;

import com.afeng.utils.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import java.util.List;

/**
     * 练习hibernate的join查询
     */
public class practiceJoinFind {
    /**
     * HQL的inner join练习
     */
    @Test
    public void practiceJoin1(){
        //获得session对象
        Session session = HibernateUtils.getCurrentSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        //HQL的inner join查询
        Query query = session.createQuery("from Customer c inner join c.linkMans ");
        List list = query.list();
        System.out.println(list);
        //提交事务
        transaction.commit();
    }

    /**
     * 正常SQL的innerJoin练习
     */
    @Test
    public void practiceJoin2(){
        //获得session对象
        Session session = HibernateUtils.getCurrentSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        //HQL的inner join查询
        Query query = session.createSQLQuery("select * from customer c inner join linkman l on c.cust_id = l.lkm_cust_id");
        List list = query.list();
        System.out.println(list);
        //提交事务
        transaction.commit();
    }
    /**
     * 迫切内连接
     * 使用迫切内连接和使用其他正常内连接在SQL语句上并没有什么较大的差别 fetch关键字其实是HQL查询的所特有的
     * 目的是讲所得数据封装到一个对象中 而不是封装到两个对象中去 这样在得到list集合时 得到的就不是一个object数组集合了
     * 而是一个Customer对象集合
     */
    @Test
    public void practiceJoin3(){
        //获得session对象
        Session session = HibernateUtils.getCurrentSession();
        // 开启事务
        Transaction transaction = session.beginTransaction();
        //HQL的inner join查询
        Query query = session.createQuery("from Customer c inner join fetch c.linkMans");
        List list = query.list();
        System.out.println(list);
        //提交事务
        transaction.commit();
    }
}
