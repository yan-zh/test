package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.CertifyDto;
import com.example.demo.entity.normal.Certify;
import com.example.demo.service.CertifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Certify Controller
 *
 * @author KuaiYang 18722043
 * @since 2021-04-22
 */
@RestController
@RequestMapping("/reviewer")
public class CertifyController {
    @Autowired
    private CertifyService certifyService;

    /**
     * Update Certify Information
     */
    //就是检测一下哪个匹配的上
    //改啦！！！！GY
    @RequestMapping(value = "/paperReview", method = RequestMethod.POST)
    public Object updateCertify(@RequestBody CertifyDto certifyDto) {
        JSONObject jsonObject = new JSONObject();
        Integer rev_id = certifyDto.getRev_id();
        Integer pap_id = certifyDto.getPap_id();
        String common = certifyDto.getCommon();

        Certify certify = certifyService.getCertify(pap_id);

        if (rev_id == certify.getRevOne()) {
            certifyService.updateCertify_one(common);
            boolean flag = certifyService.updateCertify_one(common);
            if (flag) {
                jsonObject.put("certify", 400);
                jsonObject.put("message", "Update Successfully");
                return jsonObject;
            }
        }
        if (rev_id == certify.getRevTwo()) {
            certifyService.updateCertify_two(common);
            boolean flag = certifyService.updateCertify_two(common);
            if (flag) {
                jsonObject.put("certify", 400);
                jsonObject.put("message", "Update Successfully");
                return jsonObject;
            }
        }
        if (rev_id == certify.getRevThree()) {
            certifyService.updateCertify_three(common);
            boolean flag = certifyService.updateCertify_three(common);
            if (flag) {
                jsonObject.put("certify", 400);
                jsonObject.put("message", "Update Successfully");
                return jsonObject;
            }

        }
        jsonObject.put("certify", 0);
        jsonObject.put("message", "Update Failed");
        return jsonObject;
    }




}





