package fifteenPuzzle;

import java.util.Random;

public class Scramble {

	public int[] scramble_arr;
	private int n;

	public Scramble(int n) {
		this.n = n;
		scramble_arr = new int[n * n]; // 定义存放打乱数组

		// 预设值
		for (int i = 0; i < n * n; i++) {
			scramble_arr[i] = i;
		}

	}

	public int[] getNew() {
		shuffle(scramble_arr, new Random());
		while (!isOK(scramble_arr)) {
			shuffle(scramble_arr, new Random());
		}
		return scramble_arr;
	}

	// 状态的可复原性检验
	private boolean isOK(int scramble[]) {
		int[] used = new int[n * n]; // 定义验证数组 默认初始化为0
		int bianma = 0;
		int t = 0;
		for (int i = 0; i < n * n; i++) {
			used[i] = 0;
		}
		while (!isOver(used)) {
			if (used[t] == 1) {
				for (int i = 0; i < n * n; i++) {
					if (used[i] == 0) {
						t = i;
						break;
					}
				}
			} else {
				used[t] = 1;
				t = scramble[t];
			}
			bianma++;
		}
		bianma++; // 保持

		// 缓冲块奇偶验证
		for (int i = 0; i < scramble.length; i++) {
			if (scramble[i] == 0 && i != 0) {
				if (i % n == 0 && n % 2 == 0)
					bianma++;
				if ((i % n + i / n + 1) % 2 == 1) {
//					System.out.println("奇偶:"+i);
					bianma++;
				}
			}
		}

		// System.out.println("编码数:" + bianma);
		return (bianma % 2 == 0);

	}

	private boolean isOver(int used[]) {
		for (int i = 0; i < n * n; i++) {
			if (used[i] == 0) {
				return false;
			}
		}
		return true;
	}

	private void shuffle(int[] array, Random random) {
		for (int i = array.length; i >= 1; i--) {
			swap(array, i - 1, random.nextInt(i));
		}
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
