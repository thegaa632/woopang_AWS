package com.standout.sopang.admin.goods.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.standout.sopang.goods.dto.ImageFileDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.standout.sopang.goods.vo.GoodsVO;
import com.standout.sopang.goods.vo.ImageFileVO;
import com.standout.sopang.order.vo.OrderVO;

@Repository("adminGoodsDAO")
public class AdminGoodsDAOImpl  implements AdminGoodsDAO{
	@Autowired
	private SqlSession sqlSession;

	
	//상품관리 - 상품리스트
	@Override
	public List<GoodsVO>selectNewGoodsList(Map condMap) throws DataAccessException {
		ArrayList<GoodsVO>  goodsList=(ArrayList)sqlSession.selectList("mapper.admin.goods.selectNewGoodsList",condMap);
		return goodsList;
	}
	
	
	//상품추가 - 상품정보
	@Override
	public int insertNewGoods(Map newGoodsMap) throws DataAccessException {
		sqlSession.insert("mapper.admin.goods.insertNewGoods",newGoodsMap);
		return Integer.parseInt((String)newGoodsMap.get("goods_id"));
	}
	
	
	
	//상품추가 - 이미지
	@Override
	public void insertGoodsImageFile(List fileList)  throws DataAccessException {
		//fileList 리스트를 돌려 하나씩 sql을 실행해 insert한다.
		for(int i=0; i<fileList.size();i++){
			ImageFileVO imageFileVO=(ImageFileVO)fileList.get(i);
			sqlSession.insert("mapper.admin.goods.insertGoodsImageFile",imageFileVO);
		}
	}
		
	
	
	//상품삭제
	@Override
	public void deleteGoods(String goods_id) throws Exception {
		//상품정보 삭제
		sqlSession.insert("mapper.admin.goods.deleteGoods",goods_id);
		//상품이미지 삭제
		sqlSession.insert("mapper.admin.goods.deleteimages",goods_id);
	}

	
	
	//상품수정 - 상품정보
	@Override
	public void modifyGoods(String goods_id, Map newGoodsMap) throws Exception {
		sqlSession.update("mapper.admin.goods.modifyGoods",newGoodsMap);
	}

	
	//상품수정 - 이미지
	@Override
	public void modifyImages(List<ImageFileDTO> imageFileList) throws Exception {
		//fileList 리스트를 돌려 하나씩 sql을 실행해 수정한다.
		for(int i=0; i<imageFileList.size();i++){
			ImageFileDTO imageFileDTO = imageFileList.get(i);
			sqlSession.update("mapper.admin.goods.modifyimages",imageFileDTO);
		}
		
	}


}
