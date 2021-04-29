package com.example.demo.service.impl;

import com.example.demo.entity.normal.Certify;
import com.example.demo.mapper.CertifyMapper;
import com.example.demo.service.CertifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-23
 */
@Service
public class CertifyServiceImpl extends ServiceImpl<CertifyMapper, Certify> implements CertifyService {
    @Autowired
    private CertifyMapper certifyMapper;


    @Override
    public boolean updateCertify_one(String common) {
        return certifyMapper.updateCertify_one(common)>0;
    }

    @Override
    public boolean updateCertify_two(String common) {
        return certifyMapper.updateCertify_two(common)>0;
    }

    @Override
    public boolean updateCertify_three(String common) {
        return certifyMapper.updateCertify_three(common)>0;
    }

    @Override
    public Certify getCertify(Integer pap_id) {
        return certifyMapper.getCertify(pap_id);
    }

}
