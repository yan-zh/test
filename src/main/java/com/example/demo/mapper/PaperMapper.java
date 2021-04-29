package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.normal.MyPaper;
import com.example.demo.entity.normal.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.PaperDetail.PaperDetail;
import com.example.demo.entity.FirstPagePDisplayAndSearch.PaperForShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * this entity store data of each papers Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2021-04-19
 */
public interface PaperMapper extends BaseMapper<Paper> {
    IPage<MyPaper> getPaperList(IPage page);

    IPage<MyPaper> searchPaper(IPage page, @Param("input") String input, @Param("select") String select);

    IPage<PaperForShow> getPaperListForShow(IPage iPage);

    IPage<PaperForShow> searchPaperForShow(IPage page, @Param("input") String input, @Param("select") String select);

    IPage<PaperForShow> searchPaperByNameForShow(IPage page, @Param("name") String name);

    Paper getPaperByPaperId(Integer id);

    PaperDetail getPaperDetailById(Integer id);

    List<Paper> getPaperByAutId(Integer autId);

    List<Paper> getPaperByAutIdATimeAKeyword(Integer autId, String order, String keyword);

    List<Paper> getPaperByAutIdATime(Integer autId, String order);



    /**
     * Insert the paper
     */
    public int insertPaper(Paper paper);

    /**
     * Update the paper
     * @param paper
     * @return
     */
    public int updatePaper(Paper paper);

    /**
     * To allocate three reviewer
     * @param paper
     * @return
     */
    public int reviewerAllocate(Paper paper);



}
