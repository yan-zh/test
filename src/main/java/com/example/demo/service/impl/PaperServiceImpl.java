package com.example.demo.service.impl;

import com.example.demo.entity.normal.Paper;
import com.example.demo.mapper.PaperMapper;
import com.example.demo.service.PaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * this entity store data of each papers 服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    /**
     * Insert
     *
     * @param paper
     * @return
     */
    @Override
    public boolean insertPaper(Paper paper) {
        return paperMapper.insertPaper(paper) > 0;
    }

    /**
     * Update
     *
     * @param paper
     * @return
     */
    @Override
    public boolean updatePaper(Paper paper) {
        return paperMapper.updatePaper(paper) > 0;
    }

    /**
     * To allocate the reviewer
     *
     * @param paper
     * @return
     */
    @Override
    public boolean reviewerAllocate(Paper paper) {
        return paperMapper.reviewerAllocate(paper) > 0;
    }


}
