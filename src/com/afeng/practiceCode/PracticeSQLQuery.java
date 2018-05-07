package com.afeng.practiceCode;

import com.afeng.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

//使用hibernate进行原生的SQL联系
public class PracticeSQLQuery {
    @Test
    public void testSQLQuery1(){
            /**Arrays中toString源代码
             * public static String toString(Object[] a) {
             *         if (a == null)
             *             return "null"; 如果为null的话 那么就返回null
             *
             *         int iMax = a.length - 1;
             *         if (iMax == -1)
             *             return "[]";   如果为空的话 那么就返回空数组
             *      遍历数组 将每一个值取出来 放入一个缓冲区中 最终转换为字符串
             *         StringBuilder b = new StringBuilder();
             *         b.append('[');
             *         for (int i = 0; ; i++) {
             *             b.append(String.valueOf(a[i]));
             *             if (i == iMax)
             *                 return b.append(']').toString();
             *             b.append(", ");
             *         }
             *     }
             */
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //书写SQL语句
        String sql = "select * from customer";
        //创建SQLQuery对象执行查询
        List<Object[]> list = session.createSQLQuery(sql).list();
        for (Object[] customer : list) {
        System.out.println(Arrays.toString(customer));
        }
        //关闭事务
        HibernateUtils.commitTransaction();
    }
    //条件查询
    @Test
    public void testSQLQuery2(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //书写SQL语句
        String sql = "select * from customer where cust_id=?";
        //创建SQLQuery对象执行查询
        //只能返回list集合 还必须手动转为所查询对象POJO类型
        List<Object[]> list = session.createSQLQuery(sql).setParameter(0, 1).list();
        for (Object[] customer : list) {
            System.out.println(Arrays.toString(customer));
        }
        //关闭事务
        HibernateUtils.commitTransaction();
    }
    //分页查询 方法1
    @Test
    public void testSQLQuery3(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //书写SQL语句
        String sql = "select * from customer order by cust_level asc limit ?,? ";
        List<Object[]> list = session.createSQLQuery(sql).setParameter(0, 2).setParameter(1, 2).list();
        for (Object[] object : list) {
            System.out.println(Arrays.toString(object));
        }
        //关闭事务
        HibernateUtils.commitTransaction();
    }

    //分页查询 方法2
    @Test
    public void testSQLQuery4(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //书写SQL语句
        String sql = "select * from customer order by cust_level asc ";
        //方式2相比方式1似乎有点不伦不类 因为是SQL语句和hibernate方法的结合
        List<Object[]> list = session.createSQLQuery(sql).setFirstResult(2).setMaxResults(3).list();
        for (Object[] object : list) {
            System.out.println(Arrays.toString(object));
        }
        //关闭事务
        HibernateUtils.commitTransaction();
    }
}
