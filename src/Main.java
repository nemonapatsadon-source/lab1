import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        if (n < 0) {
            System.err.println("Invalid input: n must be non-negative");
            return;
        }

        int[] arr;
        try {
            arr = readNumbers(br, n);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
            return;
        }

        mergeSort(arr, 0, arr.length - 1);

        printNumbers(arr);
    }

    static int[] readNumbers(BufferedReader br, int n) throws IOException {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(br.readLine().trim());
            if (value < 0) {
                throw new IllegalArgumentException(
                        "number #" + (i + 1) + " (" + value + ") must be non-negative");
            }
            arr[i] = value;
        }
        return arr;
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else                  temp[k++] = arr[j++];
        }
        while (i <= mid)   temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int t = 0; t < temp.length; t++) {
            arr[left + t] = temp[t];
        }
    }

    static void printNumbers(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int x : arr) {
            sb.append(x).append('\n');
        }
        System.out.print(sb);
    }
}