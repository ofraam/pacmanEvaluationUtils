import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;





public class MergeResults {

	public static void main(String[] args) {
		try
		{
			
			
			String path = "data";
			File file = new File(path);
			String[] directories = file.list(new FilenameFilter() {
			  @Override
			  public boolean accept(File current, String name) {
			    return new File(current, name).isDirectory();
			  }
			});
			ArrayList<String[]> results = new ArrayList<String[]>();
			String [] titleLines = new String[9];
			titleLines[0]="trainingEpisodes";
			titleLines[1]="reward";
			titleLines[2]="teaching";
			titleLines[3]="attention";
			titleLines[4]="episodeLength";
			titleLines[5]="sumTeaching";
			titleLines[6]="sumAttention";
			titleLines[7]="sumEpisodeLength";
			titleLines[8]="teachingStrategy";
			results.add(titleLines);
			
			for (int i = 0;i<directories.length;i++)
			{
				double sumTeaching = 0;
				double sumAttention = 0;
				double sumEpisodeLength = 0;
				String dir = directories[i];
				System.out.println(dir);
				//make sure that avg_curve is correct
//				LearningCurve[] curves = new LearningCurve[30];
//				for (int x=0; x<30; x++)
//					curves[x] = new LearningCurve(100+1, 10, path+"/"+dir+"/curve"+x);
//				for (int j=0;j<30;j++)
//				{
//					
//					curves[j].save(path+"/"+dir+"/curve"+j);
//					
//				}
//				LearningCurve avgCurve = new LearningCurve(Arrays.copyOf(curves, 30));
//				avgCurve.save(path+"/"+dir+"/avg_curve");
//				
				
				String filename = path+"/"+dir+"/avg_curve";
				BufferedReader br = new BufferedReader(new FileReader(filename));
				String line = br.readLine();
				while (line!=null)
				{
					String[] currRes = new String[9];
					currRes[8]=dir;
					String configuration = dir;
					String[] values = line.split("\t");
					for (int j =0;j<values.length;j++)
					{
						currRes[j]=values[j];
					}
					sumTeaching+=Double.parseDouble(currRes[2]);
					sumAttention+=Double.parseDouble(currRes[3]);
					sumEpisodeLength+=Double.parseDouble(currRes[4]);
					currRes[5]=Double.toString(sumTeaching);
					currRes[6]=Double.toString(sumAttention);
					currRes[7]=Double.toString(sumEpisodeLength);
					results.add(currRes);
					line = br.readLine();
				}
				
			}
			
			writeResultsToFile("DataForWriteup/train300all_Dec16.csv",results);
		}
		catch(Exception e)
		{
			System.out.println
			(e.getStackTrace());
		}
		

	}
	
	public static void writeResultsToFile(String filename, ArrayList<String[]> results)
	{
		try {
			FileWriter fstream;
		    BufferedWriter out;
			fstream = new FileWriter(filename);
		    out = new BufferedWriter(fstream);
		   
		    for (String[] res:results)
		    {
		    	StringBuilder sb = new StringBuilder();
		    	for (int i = 0;i<res.length;i++)
		    	{
		    		 sb.append(res[i]);
		    		 if (i!=res.length-1)
		    			 sb.append(",");
		    		 else
		    			 sb.append("\n");
		    		 
		    	}
		    	out.write(sb.toString());
		    	out.flush();
		    }
		    fstream.close();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
