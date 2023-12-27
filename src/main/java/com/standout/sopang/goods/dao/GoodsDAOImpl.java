package com.standout.sopang.goods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.standout.sopang.goods.dto.GoodsDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.standout.sopang.goods.vo.GoodsVO;
import com.standout.sopang.goods.vo.ImageFileVO;

@Repository("goodsDAO")
public class GoodsDAOImpl  implements GoodsDAO{
	@Autowired
	private SqlSession sqlSession;

	
	@Override
	public List<GoodsVO> selectGoodsList(String goodsStatus ) throws DataAccessException {
		List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsList",goodsStatus);
		return goodsList;	
	}

	@Override
	public List<GoodsVO> selectMenusList(String goodsSort) throws DataAccessException {
		List<GoodsVO> goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectMenusList",goodsSort);
		return goodsList;
	}

	
	@Override
	public List<GoodsVO> selectGoodsByMenuGoods(String menuGoods) throws DataAccessException {
		ArrayList goodsList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsByMenuGoods",menuGoods);
		 return goodsList;
	}
	
	@Override
	public List<String> selectKeywordSearch(String keyword) throws DataAccessException {
	   List<String> list=(ArrayList)sqlSession.selectList("mapper.goods.selectKeywordSearch",keyword);
	   return list;
	}
	
	//�˻�
	@Override
	public ArrayList selectGoodsBySearchWord(Map<String, String> map) throws DataAccessException{
		ArrayList list=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsBySearchWord",map);
		 return list;
	}
	
	@Override
	public GoodsVO selectGoodsDetail(String goods_id) throws DataAccessException{
		GoodsVO goodsVO=(GoodsVO)sqlSession.selectOne("mapper.goods.selectGoodsDetail",goods_id);
		return goodsVO;
	}
	
	@Override
	public List<ImageFileVO> selectGoodsDetailImage(String goods_id) throws DataAccessException{
		List<ImageFileVO> imageList=(ArrayList)sqlSession.selectList("mapper.goods.selectGoodsDetailImage",goods_id);
		return imageList;
	}
	
}
