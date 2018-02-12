import java.io.*;
public class Comparison
{
    String edges[]={"AB","BC","CD","AD","BD","CB"};
    double pheromone[]={1,1,1,1,1,1};
    double heuristic[]={2,2,2,2,2,2};
    double weightFinal[]=new double[4];
    int alpha=1;
    int beta=-1;
    public Comparison()
    {
    }

    int updatePheromone(int t)
    {
        pheromone[t]=pheromone[t]+Math.pow(heuristic[t],beta);
        return 0;
    }

    int updateHeuristic(int t)
    {
        heuristic[t]=2*heuristic[t];
        return 0;
    }

    public int findIndex(String s)
    {
        int index=0;
        for(int i=0;i<6;i++)
        {
            if(s.equals(edges[i]))
            {
                index=i;
                break;
            }
        }
        return index;
    }

    public void main()throws IOException
    {
        BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the sequence");
        String sequence[]=new String[4];
        for(int i=0;i<4;i++)
        {
            sequence[i]=ob.readLine();
        }
        int k=0;
        System.out.println("SEQUENCE\tWEIGHT\tPRIORITY");
        double weight=0;
        for(int j=0;j<4;j++)
        {
            int t=sequence[j].length();
            String input[]=new String[t-1];
            for(int i=0;i<t-2;i++)
            {
                input[i]=sequence[j].substring(i,i+2);
            }
            input[t-2]=sequence[j].substring(t-2);
            weight=0;
            for(int i=0;i<t-1;i++)
            {
                int ind=findIndex(input[i]);
                updatePheromone(ind);
                updateHeuristic(ind);
                weight=weight+pheromone[ind];
            }
            weightFinal[k++]=weight;
        }
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4-1-i;j++)
            {
                if(weightFinal[j]<weightFinal[j+1])
                {
                    double temp1=weightFinal[j];
                    weightFinal[j]=weightFinal[j+1];
                    weightFinal[j+1]=temp1;
                    String temp2=sequence[j];
                    sequence[j]=sequence[j+1];
                    sequence[j+1]=temp2;
                }
            }
        }
        for(int i=0;i<4;i++)
        {
            System.out.println(sequence[i]+"\t\t"+weightFinal[i]+"\t"+(i+1));
        }
        System.out.println("EDGE\tPHEROMONE\tHEURISTIC");
        for(int i=0;i<6;i++)
        {
            System.out.println(edges[i]+"\t"+pheromone[i]+"\t\t"+heuristic[i]);
        }
    }
}