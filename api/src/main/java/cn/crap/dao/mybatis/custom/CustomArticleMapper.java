package cn.crap.dao.mybatis.custom;

import cn.crap.dao.mybatis.custom.SqlProvider;
import cn.crap.model.mybatis.Article;
import cn.crap.utils.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nico 2017-07-28
 */
@Service
public class CustomArticleMapper {

	@Autowired
	JdbcTemplate jdbcTemplate;


    public List<String> queryArticleCategoryByUserId(String userId){
		return jdbcTemplate.queryForList("select distinct category from article where moduleId in( select id from Module where userId=?", new Object[]{userId},  String.class);
	}

    public List<String> queryArticleCategoryByWeb(){
		return jdbcTemplate.queryForList("select distinct category from article where moduleId='web'",  String.class);

	}

    public List<String> queryArticleCategoryByModuleIdAndType(String moduleId, String type){
		return jdbcTemplate.queryForList("select distinct category from article where moduleId=? and type=?", new Object[]{moduleId, type}, String.class);
	}

    public void updateClickById(String id){
		jdbcTemplate.update("update article set click=click+1 where id=?", id);
	}
}