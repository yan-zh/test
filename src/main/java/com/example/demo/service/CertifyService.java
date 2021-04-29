package com.example.demo.service;

import com.example.demo.entity.normal.Certify;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-23
 */
public interface CertifyService extends IService<Certify> {
    /*
       Update Certify information into databases
        */
    public boolean updateCertify_one(String common);
    public boolean updateCertify_two(String common);
    public boolean updateCertify_three(String common);

    public Certify getCertify(Integer pap_id);
}
