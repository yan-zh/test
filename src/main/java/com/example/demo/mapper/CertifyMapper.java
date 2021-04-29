package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.normal.Certify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.reviewerArrangement_reviewer.ReturnPaper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-23
 */
public interface CertifyMapper extends BaseMapper<Certify> {

    Certify getCertifyByPaperId(Integer paperId);

    List<Certify> getCertifyByReviewerId(Integer reviewerId);

    IPage<ReturnPaper> getReturnPaperByReviewerIdAndTimeAndKeyword(IPage ipage,@Param("reviewerId") Integer reviewerId,@Param("order") String order, @Param("keyword") String keyword);

    IPage<ReturnPaper> getReturnPaperByReviewerIdAndTime(IPage ipage,@Param("reviewerId") Integer reviewerId,@Param("order") String order);


    /**
     * To update the certify record
     * @param common
     * @return
     */
    public int updateCertify_one(String common);
    public int updateCertify_two(String common);
    public int updateCertify_three(String common);

    /**
     * To get the certify information
     * @param pap_id
     * @return
     */
    public Certify getCertify(Integer pap_id);


}
