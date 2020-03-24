package com.hnxx.zy.clms.core.service.impl;

import com.hnxx.zy.clms.core.entity.Registration;
import com.hnxx.zy.clms.core.mapper.RegistrationMapper;
import com.hnxx.zy.clms.core.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: chenii
 * @date: 2020/3/19 13:04
 * @version: 1.0
 * @desc:
 */
@Service
public class RegistrationImpl implements RegistrationService {

    @Autowired
    private RegistrationMapper registrationMapper;

    @Override
    public void saveRegist(Registration registration) {
        registrationMapper.saveRegist(registration);
    }

    @Override
    public List<Registration> getRegisListById(Integer id) {
        return registrationMapper.getRegisListById(id);
    }

    @Override
    public List<Registration> getRegisListByDate(String date) {
        return registrationMapper.getRegisListByDate(date);
    }
}
