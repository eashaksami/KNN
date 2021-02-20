import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class KNNVariation {
    static int k,count=0,fold,testLength;
    static double validation=0.0;
    public static void main(String[] args) throws Exception {
        double x=0.0;
        int columns = 0;
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter value of k: ");
        k = sc.nextInt();
        BufferedReader br=new BufferedReader(new FileReader("src/sensor_readings_24.txt"));
        String line=null;
        int rows=0;
        while ((line=br.readLine())!=null)
        {
            columns=line.split(",").length;
            String temp[]= line.split(",");
            rows++;
        }
        br.mark(0);
        br.reset();
        //br.close();
        System.out.println(columns+" "+rows);
        double arr[]=new double[columns-1];
        /*System.out.println("Enter the values:");
        for(int i=0;i<columns-1;i++){
            double value=sc.nextDouble();
            arr[i]=value;
        }*/
        System.out.print("Enter fold: ");
        fold=sc.nextInt();
        testLength=rows/fold;
        int z=0;
        int[] array=new int[fold];
        while(true) {
            if (z >= fold) break;
                //int i=(testLength*z);i<(testLength*(z+1));i++ For test
            else {
                for (int i=(testLength*z);i<(testLength*(z+1));i++) {
                    calculateKNN(i,z,columns);
                }
                validation+=(double)count/testLength;
            }
            z++;
            count=0;
        }
        System.out.println(validation/fold);
        //for(int i=0;i<z;i++) System.out.println(brr[i]);
    }

    public static void calculateKNN(int r,int itr,int columns) throws Exception {
        Scanner sc = new Scanner(System.in);
        String line = null;
        String lineN = Files.readAllLines(Paths.get("src/sensor_readings_24.txt")).get(r);
        double arr[] = new double[columns - 1];
        String str = null;
        for (int i = 0; i < columns - 1; i++) {
            //double value=sc.nextDouble();
            //arr[i]=value;
            String temp[] = lineN.split(",");
            arr[i] = Double.parseDouble(temp[i]);
            str=temp[columns-1];
        }
        //String temp[]= line.split(",");
        BufferedReader br = new BufferedReader(new FileReader("src/sensor_readings_24.txt"));
        //double[] brr=new double[z];
        TreeMap<Double, String> map = new TreeMap<Double, String>();
        double x = 0.0;
        int z = 0;
        while ((line = br.readLine()) != null) {
            if ((z>=testLength*itr)&&(z<testLength*(itr+1)))     //int i=(testLength*z);i<(testLength*(z+1));i++ For test
            {
                z++;
                continue;
            }

            else {
            String temp[] = line.split(",");
            for (int i = 0; i < columns - 1; i++) {
                x += Math.pow((arr[i] - (Double.parseDouble(temp[i]))), 2);
            }
            double output = Math.sqrt(x);
            //System.out.println(output);
            map.put(output, temp[columns - 1]);
            //brr[z]=output;
            x = 0;
            z++;
            }
        }
            //System.out.println(map);
            br.close();
            //int testLength=rows/fold;
            //int z=0;
            z = 0;
            String[] type = new String[k];
            for (Map.Entry m : map.entrySet()) {
                if (z > k - 1)
                    break;
                else {
                    type[z] = (String) m.getValue();
                    //System.out.println(m.getKey() + " " + m.getValue());
                    z++;
                }
            }
            //for (int i = 0; i < type.length; i++) System.out.println(type[i]);
            findDistinct(type,str);
    }

    public static void findDistinct(String arr[],String str1) {
        int n=arr.length;
        String[] q=new String[n];
        int k=0;
        for (int i = 0; i < n; i++)
        {
            int j;
            for (j = 0; j < i; j++){
                if (arr[i].equals(arr[j]))
                    break;
            }
            //System.out.println(i+" "+j);
            if (i == j){
                q[k]=arr[i];
                k++;
                //System.out.print( arr[i] + " ");
            }
        }
        TreeMap<Integer,String> map1=new TreeMap<Integer, String>();
        for(int i=0;i<k;i++){
            map1.put(search(arr,q[i]),q[i]);
            //System.out.println(search(arr,q[i])+" "+q[i]);
        }
        //System.out.println(map1.lastEntry().getValue());
        String str=map1.lastEntry().getValue();
        if(str.equals(str1))count++;
        //System.out.println(count);
    }
    public static int search(String[]arr, String s)
    {
        int counter = 0;
        for (int j = 0; j < arr.length; j++)
            if (s.equals(arr[j]))
                counter++;

        return counter;
    }
}

