package service;

import javax.servlet.ServletContext;

import dao.BoardDao;
import dto.Board;

public class BoardService {
	private ServletContext application;
	
	public BoardService(ServletContext application) {
		this.application = application;
	}
	
	public void write(Board board) {
		System.out.println("게시글을 작성합니다.");
		BoardDao boardDao = (BoardDao) application.getAttribute("boardDao");
		boardDao.insert(board, null);
	}
}