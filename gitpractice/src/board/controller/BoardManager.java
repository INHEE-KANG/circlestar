package board.controller;

import java.util.ArrayList;
import java.util.Scanner;

import board.model.vo.Board;

public class BoardManager {
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
	
		

	}
}
