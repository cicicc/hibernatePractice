package com.afeng.dao;

import com.afeng.domain.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();
}
