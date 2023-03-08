package mybatisDAO;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import util.MyBatisUtil;

public class MyBatisDAO {
	
	public <T> T selectOne(String statement) {
		SqlSession sqlSession = MyBatisUtil.getInstance(); //세션열기
		
		T obj = null;
		
		try {
			obj = sqlSession.selectOne(statement);
		} catch (PersistenceException e) {
			throw new RuntimeException("MyBatisDAO selectOne메소드 실행 중 에러 발생",e);
		} finally {
			sqlSession.close();
		} return obj;
	}
	
	public <T> T selectOne(String statement, Object param) {
		SqlSession sqlSession = MyBatisUtil.getInstance(); //세션열기
		
		T obj = null;
		
		try {
			obj = sqlSession.selectOne(statement, param);
		} catch (PersistenceException e) {
			throw new RuntimeException("MyBatisDAO selectOne메소드 실행 중 에러 발생",e);
		} finally {
			sqlSession.close();
		} return obj;
	}
	
	public <T> List<T> selectList(String statement) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		List<T> list = Collections.EMPTY_LIST;
		
		try {
			
			list = sqlSession.selectList(statement);
		} catch (PersistenceException e) {
			throw new RuntimeException("MyBatisDAO selectList메소드 실행 중 에러 발생",e);
		} finally {
			sqlSession.close();
		} return list;
	}

	public <T> List<T> selectList(String statement, Object param) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		List<T> list = Collections.EMPTY_LIST;
		
		try {
			list = sqlSession.selectList(statement, param);
		} catch (PersistenceException e) {
			throw new RuntimeException("MyBatisDAO selectList메소드 실행 중 에러 발생",e);
		} finally {
			sqlSession.close();
		} return list;
	}
	
	public int insert(String statement, Object param) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.insert(statement, param);
			sqlSession.commit();
		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("MyBatisDAO insert메소드 실행 중 에러 발생",e);
		} finally {
			sqlSession.close();
		} return cnt;
	}
	
	public int update(String statement, Object param) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.update(statement, param);
			sqlSession.commit();
		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("MyBatisDAO update메소드 실행 중 에러 발생",e);
		} finally {
			sqlSession.close();
		} return cnt;
	}
	
	public int delete(String statement, Object param) {
		SqlSession sqlSession = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = sqlSession.delete(statement, param);
			sqlSession.commit();
		} catch (PersistenceException e) {
			sqlSession.rollback();
			throw new RuntimeException("MyBatisDAO delete메소드 실행 중 에러 발생",e);
		} finally {
			sqlSession.close();
		} return cnt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
