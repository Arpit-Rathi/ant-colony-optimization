import java.io.*;
public class AntColonyOptimization
{
    String edges[]={"AB","BC","CD","AD","BD","CB"};
    double pheromone[]={1,1,1,1,1,1};
    double heuristic[]={2,2,2,2,2,2};
    int alpha=1;
    int beta=-1;
    public AntColonyOptimization()
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
        String sequence=ob.readLine();
        int t=sequence.length();
        String input[]=new String[t-1];
        for(int i=0;i<t-2;i++)
        {
            input[i]=sequence.substring(i,i+2);
        }
        input[t-2]=sequence.substring(t-2);
        double weight=0;
        for(int i=0;i<t-1;i++)
        {
            int ind=findIndex(input[i]);
            updatePheromone(ind);
            updateHeuristic(ind);
            weight=weight+pheromone[ind];
        }
        System.out.println("SEQUENCE\tWEIGHT");
        System.out.println(sequence+"\t\t"+weight);
        System.out.println("EDGE\tPHEROMONE\tHEURISTIC");
        for(int i=0;i<6;i++)
        {
            System.out.println(edges[i]+"\t"+pheromone[i]+"\t\t"+heuristic[i]);
        }
    }
}