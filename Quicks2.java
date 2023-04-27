import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quicks2 {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] partition(int[] arr, int l, int r) {
        if (arr[l] < arr[r])
            swap(arr, r, l);

        int j = l + 1;
        int g = r - 1, k = l + 1, p = arr[l], q = arr[r];

        while (k <= g) {
            if (arr[k] >= p) {
                swap(arr, k, j);
                j++;
            } else if (arr[k] < q) {
                while (arr[g] < q && k < g)
                    g--;
                swap(arr, k, g);
                g--;
                if (arr[k] > p) {
                    swap(arr, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        swap(arr, l, j);
        swap(arr, r, g);

        return new int[] { j, g };
    }

    public static int[] qsort(int[] arr, int low, int high) {
        if (low < high) {
            int[] piv;
            piv = partition(arr, low, high);

            int[] left = qsort(arr, low, piv[0] - 1);
            int[] middle = qsort(arr, piv[0] + 1, piv[1] - 1);
            int[] right = qsort(arr, piv[1] + 1, high);

            int[] result = new int[left.length + middle.length + right.length];
            int i = 0;
            for (int value : left) {
                result[i++] = value;
            }
            for (int value : middle) {
                result[i++] = value;
            }
            for (int value : right) {
                result[i++] = value;
            }
            return result;
        }
        return new int[] { arr[low] };
    }

    public static int[] qsort(int[] data) {
        int low = 0;
        int high = data.length - 1;
        if (low < high) {
            int[] piv;
            piv = partition(data, low, high);

            int[] left = qsort(data, low, piv[0] - 1);
            int[] middle = qsort(data, piv[0] + 1, piv[1] - 1);
            int[] right = qsort(data, piv[1] + 1, high);

            int[] result = new int[left.length + middle.length + right.length];
            int i = 0;
            for (int value : left) {
                result[i++] = value;
            }
            for (int value : middle) {
                result[i++] = value;
            }
            for (int value : right) {
                result[i++] = value;
            }
            return result;
        }
        return new int[] { data[low] };
    }
    public static boolean isSorted(int[] data){
        // the code iterats through the array and checks the element at the position i and the element at position i+1
         for (int i = 0; i < data.length - 1; i++) {
             if ( data[i] < data[i + 1]) {
                 return false;
             }
         }
         return true;
     }
 
     public static void main(String[] args) {
 
         // Standart-In
         int[] out = standardIn();
 
         if (out.length < 20){
 
             System.out.println( "Befor: ");
             System.out.println( Arrays.toString(out));
 
 
 
             out = qsort(out);
 
 
 
             System.out.println( "After: ");
             System.out.println(Arrays.toString(out));
         }else{
             Instant start = Instant.now();
 
             out = qsort(out);
 
             Instant finish = Instant.now();
             long time = Duration.between(start, finish).toMillis();
             System.out.println("Time: " + time);
         }
 
         // if the Array out is not sorted then the error is issued
         assert (isSorted(out) != false):"Array ist nicht sortiert";
 
         System.out.println("Min: " + out[out.length -1] + ", Med: " + (double)((out[0] + out[out.length-1])/2) + ", Max: " + out[0] );
 
     }
 
     public static int[] standardIn() {
 
         Scanner input = new Scanner(System.in);
         ArrayList<Integer> puffer = new ArrayList<Integer>();
 
         while ( input.hasNextLine() ){
             try {
                 puffer.add(Integer.parseInt(input.nextLine()));
 
             }catch (NumberFormatException e){
                 System.err.println("Input list contains a non-number.");
                 e.getStackTrace();
             }
         }
         int[] out = new int[puffer.size()];
 
         //input.close();                          // Der Scaner wir nicht mehr gebracuht daher geben wir den benötigten Speicher wieder frei.
 
         for(int i=0;i< out.length; i++){        // Alle Elemente aus der ArrayListe wird in dem Array out kopiert.
             out[i] = puffer.get(i);
         }
         return out;
     }
}