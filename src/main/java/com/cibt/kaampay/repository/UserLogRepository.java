/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cibt.kaampay.repository;

import com.cibt.kaampay.entity.UserLog;
import java.util.List;

/**
 *
 * @author Derslived
 */
public interface UserLogRepository extends CRUD<UserLog> {
    List<UserLog> findByUserId(int id)throws Exception;
}
