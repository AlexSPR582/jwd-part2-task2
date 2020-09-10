package com.alexander.task2.dao;

import com.alexander.task2.entity.Text;
import com.alexander.task2.exception.DaoException;

public interface TextDao {
    Text read() throws DaoException;
}
