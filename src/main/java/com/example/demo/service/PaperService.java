package com.example.demo.service;

import com.example.demo.entity.normal.Paper;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * this entity store data of each papers 服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
public interface PaperService extends IService<Paper> {
    /**
     * Inster
     * @param paper
     * @return
     */
    public boolean insertPaper(Paper paper);

    /**
     * Update
     * @param paper
     * @return
     */
    public boolean updatePaper(Paper paper);

    /**
     * To allocate the reviewer
     * @param paper
     * @return
     */
    public boolean reviewerAllocate(Paper paper);
}
