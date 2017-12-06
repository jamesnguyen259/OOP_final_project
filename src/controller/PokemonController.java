package controller;

import model.GameState;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class PokemonController {

	static final Random RANDOM = new Random();
	static final int[] UU = { 0, 0, 1, -1 };
	static final int[] VV = { 1, -1, 0, 0 };

	public static int[][] getNewMatrix() {// tao mot ma tran va set cac so ngau nhien tu 1-36 cho no
		int[][] a = new int[GameState.MAX_ROWS][GameState.MAX_COLUMNS];// tao mot ma tran (Max_rows x Max_columns)
		int i, j, k, t, remain, key;
		boolean stop;

		if (GameState.POKEMON_COLUMNS > GameState.MAX_COLUMNS || GameState.POKEMON_ROWS > GameState.MAX_ROWS) {
			return null;
		}
		for (i = 0; i < GameState.POKEMON_ROWS + 2; i++)
			for (j = 0; j < GameState.POKEMON_COLUMNS + 2; j++)
				a[i][j] = 0;
		remain = 60; // tong so hinh
		for (k = 1; k <= 15; k++) {
			for (t = 1; t <= 4; t++) {
				key = RANDOM.nextInt(remain--) + 1;
				stop = false;
				for (i = 1; i <= GameState.POKEMON_ROWS; i++) {
					if (stop)
						break;
					else
						for (j = 1; j <= GameState.POKEMON_COLUMNS; j++)
							if (a[i][j] == 0) {
								key--;
								if (key == 0) {
									stop = true;
									a[i][j] = k;
									break;
								}
							}
				}
			}
		}
		return a;
	}

	// Kiem tra xem hai diem co duong di ngan nhat giua hai hinh
	public static ArrayList<Point> checkPath(int[][] a, int i1, int j1, int i2,
			int j2) {
		if (i1 == i2 && j1 == j2)
			return null;
		if (a[i1][j1] == 0 || a[i2][j2] == 0)
			return null;
		if (a[i1][j1] != a[i2][j2])
			return null;

		int fist, last, i, j, t;
		Point[] queue = new Point[GameState.MAX_ROWS * GameState.MAX_COLUMNS];// luu tru cac dinh de tim
		Point[][] dad = new Point[GameState.MAX_ROWS][GameState.MAX_COLUMNS];//
		int[][] count = new int[GameState.MAX_ROWS][GameState.MAX_COLUMNS];

		for (i = 0; i < (GameState.MAX_ROWS * GameState.MAX_COLUMNS); i++)
			queue[i] = new Point();
		fist = 0;
		last = 0;
		queue[0].x = i1;
		queue[0].y = j1;
		for (i = 0; i < GameState.MAX_ROWS; i++)
			for (j = 0; j < GameState.MAX_COLUMNS; j++)
				dad[i][j] = new Point(-1, -1);
		dad[i1][j1].x = -2;
		count[i1][j1] = 0;

		boolean[] canGo = new boolean[4];
		int[] p = new int[4];
		int[] q = new int[4];

		while (fist <= last) {
			i = queue[fist].x;
			j = queue[fist].y;
			fist++;
			for (t = 0; t < 4; t++) {
				canGo[t] = true;
				p[t] = i;
				q[t] = j;
			}
			do {
				for (t = 0; t < 4; t++)
					if (canGo[t]) {
						p[t] += UU[t];
						q[t] += VV[t];
						if (!myInside(p[t], q[t])) {
							canGo[t] = false;
							continue;
						}
						if (p[t] == i2 && q[t] == j2) {
							dad[p[t]][q[t]].x = i;
							dad[p[t]][q[t]].y = j;
							return createArrayList(dad, i2, j2);
						}
						if (a[p[t]][q[t]] > 0) {
							canGo[t] = false;
							continue;
						}
						if (dad[p[t]][q[t]].x != -1)
							continue;
						if (count[i][j] == 2)
							continue;
						last++;
						queue[last].x = p[t];
						queue[last].y = q[t];
						dad[p[t]][q[t]].x = i;
						dad[p[t]][q[t]].y = j;
						count[p[t]][q[t]] = count[i][j] + 1;
					}
			} while (canGo[0] || canGo[1] || canGo[2] || canGo[3]);
		}
		return null;
	}

	// Tao mot day cac diem de ve duong noi diem dau voi diem cuoi
	public static ArrayList<Point> createArrayList(Point[][] dad, int i, int j) {
		ArrayList<Point> arrayList = new ArrayList<Point>();
		int p, q;
		do {
			arrayList.add(new Point(i, j));
			p = dad[i][j].x;
			q = dad[i][j].y;
			i = p;
			j = q;
		} while (i != -2);
		return arrayList;
	}

	// Xet cai duong co nam trong ma tran hay khong
	public static boolean myInside(int i, int j) {
		return i >= 0 && i < GameState.MAX_ROWS && j >= 0 && j < GameState.MAX_COLUMNS;
	}

	// Coi thu con duong an hay khog neu con thi tra ve false khong thi true
	public static boolean isEnd(int[][] a) {
		int i, j;
		for (i = 1; i <= GameState.POKEMON_ROWS; i++)
			for (j = 1; j <= GameState.POKEMON_COLUMNS; j++)
				if (a[i][j] > 0)
					if (findTwin(a, i, j) != null)
						return false;
		return true;
	}

	// Xet xem con duong an hay khong
	public static Point findTwin(int[][] a, int i1, int j1) {
		int fist, last, i, j, t;
		Point[] queue = new Point[GameState.MAX_ROWS * GameState.MAX_COLUMNS];
		boolean[][] cx = new boolean[GameState.MAX_ROWS][GameState.MAX_COLUMNS];
		int[][] count = new int[GameState.MAX_ROWS][GameState.MAX_COLUMNS];

		for (i = 0; i < (GameState.MAX_ROWS * GameState.MAX_COLUMNS); i++)
			queue[i] = new Point();
		fist = 0;
		last = 0;
		queue[0].x = i1;
		queue[0].y = j1;
		for (i = 0; i < GameState.MAX_ROWS; i++)
			for (j = 0; j < GameState.MAX_COLUMNS; j++)
				cx[i][j] = true;
		cx[i1][j1] = false;
		count[i1][j1] = 0;

		boolean[] canGo = new boolean[4];
		int[] p = new int[4];
		int[] q = new int[4];

		while (fist <= last) {
			i = queue[fist].x;
			j = queue[fist].y;
			fist++;
			for (t = 0; t < 4; t++) {
				canGo[t] = true;
				p[t] = i;
				q[t] = j;
			}
			do {
				for (t = 0; t < 4; t++)
					if (canGo[t]) {
						p[t] += UU[t];
						q[t] += VV[t];
						if (!myInside(p[t], q[t])) {
							canGo[t] = false;
							continue;
						}
						if (a[p[t]][q[t]] == a[i1][j1] && cx[p[t]][q[t]])
							return new Point(p[t], q[t]);
						if (a[p[t]][q[t]] > 0) {
							canGo[t] = false;
							continue;
						}
						if (!cx[p[t]][q[t]])
							continue;
						if (count[i][j] == 2)
							continue;
						last++;
						queue[last].x = p[t];
						queue[last].y = q[t];
						cx[p[t]][q[t]] = false;
						count[p[t]][q[t]] = count[i][j] + 1;
					}
			} while (canGo[0] || canGo[1] || canGo[2] || canGo[3]);
		}
		return null;
	}

	// Sua lai ma tran theo tung Level
	public static void fixMatrix(int[][] a, int fixType) {
		if (fixType == 1)
			return;// level 1

		if (fixType == 2) {// level 2
			fixZone(a, 1, 1, GameState.POKEMON_ROWS, GameState.POKEMON_COLUMNS, 0);
		} else if (fixType == 3) {// level 3
			fixZone(a, 1, 1, GameState.POKEMON_ROWS, GameState.POKEMON_COLUMNS, 1);
		}
	}

	// Sua lai tung phan vung cua ma tran
	public static void fixZone(int[][] a, int i1, int j1, int i2, int j2,
			int vector) {
		int i, j, p, q;
		boolean stop;
		do {
			stop = true;
			for (i = i1; i <= i2; i++)
				for (j = j1; j <= j2; j++)
					if (a[i][j] > 0) {
						p = i + UU[vector];
						q = j + VV[vector];
						if (p >= 1 && p <= GameState.POKEMON_ROWS && q >= 1 && q <= GameState.POKEMON_COLUMNS) {
							if (a[p][q] == 0) {
								swap(a, i, j, p, q);
								stop = false;
							}
						}
					}
		} while (!stop);
	}

	// doi vi tri hai hinh
	public static void swap(int[][] a, int i1, int j1, int i2, int j2) {
		int tmp = a[i1][j1];
		a[i1][j1] = a[i2][j2];
		a[i2][j2] = tmp;
	}

	public static int countArrays(int[][] a) {
		int count = 0;
		for (int i = 0; i <= GameState.POKEMON_ROWS; i++) {
			for (int j = 0; j <= GameState.POKEMON_COLUMNS; j++) {
				if (a[i][j] > 0)
					count++;
			}
		}
		return count;

	}
}
