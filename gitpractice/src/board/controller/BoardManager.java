package board.controller;

import java.util.ArrayList;
<<<<<<< HEAD
=======
<<<<<<< HEAD
import java.util.Scanner;

import board.model.vo.Board;

public class BoardManager {
	ArrayList<Board> list = new ArrayList<Board>();
	Scanner sc = new Scanner(System.in);
	
	public void writeBoard() {
		System.out.println("새 게시글 쓰기 입니다");
		System.out.print("글제목 : ");
		String title = sc.nextLine();
		System.out.print("작성자 : ");
		String author = sc.nextLine();
		System.out.print("글내용 : ");
		String content = sc.nextLine();
		list.add(new Board(title, author, content));
=======
import java.util.Iterator;
import java.util.List;
>>>>>>> 78a3ca3ab00db2f15f6004950a44a11315e051e8
import java.util.Scanner;

import board.model.vo.Board;

public class BoardManager {
<<<<<<< HEAD
	private ArrayList<Board> list = new ArrayList<Board>();

	public void displayAllList() {

		if (list.isEmpty()) {
			System.out.println("�Խ����� �����.");
		} else {
			for (Board b : list) {
				System.out.println(b);
			}
		}
	}

	public void modifyContent() {
		Scanner sc = new Scanner(System.in);

		System.out.println("������ �� ��ȣ: ");
		int num=sc.nextInt();
		System.out.println("������ ���� : ");
		String newContent=sc.nextLine();
		for(int i=0; i<list.size();i++) {
			if(list.get(i).getBoardNo()==num) {
				list.get(i).setBoardContent(newContent);
				
			}
		}
//		list�� for������ ���鼭 ��� �Է¹��� ������ ��ȣ�� list�� ����ִ� ��ȣ�� ��ġ�ϸ� ������ �ؼ� if��.
//		if�� �ȿ��� ������ ������ �޾ƾ���. System.out.println();
//		exit�� �Է¹ް� �Ǹ� ���ѷ����� ������������. 
	
		

=======
	
	Scanner sc=new Scanner(System.in);

	public void displayBoard(){
		System.out.println("조회하실 글 번호를 입력하세요");
		int searchNo=sc.nextInt();
		ArrayList<Board> boardList=new ArrayList<Board>();
		for(int i=0; i<boardList.size(); i++) {
			if(boardList.get(i).getBoardNo()==searchNo) {
				System.out.println(boardList.get(i).getBoardContent().toString());
			}
		}
		
	}
	
	public void deleteBoard(){
		System.out.println("삭제할 글 번호를 입력하세요");
		int deleteNo=sc.nextInt();
		ArrayList<Board> boardList=new ArrayList<Board>();
		for(int i=0; i<boardList.size(); i++) {
			if(boardList.get(i).getBoardNo()==deleteNo) {
				System.out.println(boardList.get(i).getBoardContent().toString());
			}
		}
		System.out.println("정말로 삭제하시겠습니까?(y/n)");
		if(sc.next()=="y" || sc.next()=="Y") {
			for(int i=0;i<boardList.size();i++) {
				if(boardList.get(i).getBoardNo()==deleteNo)
					boardList.remove(i);
					System.out.println(deleteNo+"번 글이 삭제되었습니다.");
			}
		}
		
>>>>>>> origin/inhee
>>>>>>> 78a3ca3ab00db2f15f6004950a44a11315e051e8
	}
}
