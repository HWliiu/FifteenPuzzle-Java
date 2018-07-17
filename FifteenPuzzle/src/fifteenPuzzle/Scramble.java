package fifteenPuzzle;

import java.util.Random;

public class Scramble {

	public int[] scramble_arr;
	private int n;

	public Scramble(int n) {
		this.n = n;
		scramble_arr = new int[n * n]; // �����Ŵ�������

		// Ԥ��ֵ
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

	// ״̬�Ŀɸ�ԭ�Լ���
	private boolean isOK(int scramble[]) {
		int[] used = new int[n * n]; // ������֤���� Ĭ�ϳ�ʼ��Ϊ0
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
		bianma++; // ����

		// �������ż��֤
		for (int i = 0; i < scramble.length; i++) {
			if (scramble[i] == 0 && i != 0) {
				if (i % n == 0 && n % 2 == 0)
					bianma++;
				if ((i % n + i / n + 1) % 2 == 1) {
//					System.out.println("��ż:"+i);
					bianma++;
				}
			}
		}

		// System.out.println("������:" + bianma);
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
