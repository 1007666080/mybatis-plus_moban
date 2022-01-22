package com.x.generate.service.impl;

import com.x.generate.beans.Employee;
import com.x.generate.mapper.EmployeeMapper;
import com.x.generate.service.EmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author NB_PLUS
 * @since 2022-01-22
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
