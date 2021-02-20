import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        double a,b,c,d,x=0.0;
        String s;
        int columns = 0;
        BufferedReader br=new BufferedReader(new FileReader("src/sensor_readings_24.txt"));
        String line=null;
        int z=0;
        while ((line=br.readLine())!=null)
        {
            columns=line.split(",").length;
            String temp[]= line.split(",");
                /*a= Double.parseDouble(temp[0]);
                b= Double.parseDouble(temp[1]);
                c= Double.parseDouble(temp[2]);
                d= Double.parseDouble(temp[3]);
                s=temp[4];
                System.out.println(a+" "+b+" "+c+" "+d+" "+s);*/
            z++;
        }
        br.mark(0);
        br.reset();
        //br.close();
        System.out.println(columns+" "+z);
        double arr[]=new double[columns-1];
        for(int i=0;i<columns-1;i++){
            double value=sc.nextDouble();
            arr[i]=value;
        }
        br = new BufferedReader(new FileReader("src/sensor_readings_24.txt"));
        //double[] brr=new double[z];
        TreeMap<Double,String> map=new TreeMap<Double, String>();
        z=0;
        while ((line=br.readLine())!=null)
        {
            String temp[]= line.split(",");
            for(int i=0;i<columns-1;i++){
                x+=Math.pow((arr[i]-(Double.parseDouble(temp[i]))),2);
            }
            double output=Math.sqrt(x);
            System.out.println(output);
            map.put(output,temp[columns-1]);
            //brr[z]=output;
            x=0;
            //s=temp[4];
            z++;
        }
        br.close();
        int fold=sc.nextInt();
        //for(int i=0;i<z;i++) System.out.println(brr[i]);
        z=0;
        String[] type=new String[fold];
        //double[]
        for(Map.Entry m:map.entrySet())
        {
            if(z>fold-1)break;
            else{
                type[z]= (String) m.getValue();
                System.out.println(m.getKey()+" "+m.getValue());
                z++;
            }
        }
        findDistinct(type);
    }
    static void findDistinct(String arr[]){
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
        System.out.println(map1.lastEntry());
    }
    static int search(String[]arr, String s)
    {
        int counter = 0;
        for (int j = 0; j < arr.length; j++)

                /* checking if string given in query is
                  present in the given string. If present,
                  increase times*/
            if (s.equals(arr[j]))
                counter++;

        return counter;
    }
}

