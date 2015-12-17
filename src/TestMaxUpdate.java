
public class TestMaxUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] weights = new double[4];
		weights[0]=1;
		weights[1]=1;
		weights[2]=1;
		weights[3]=1;
		
		double alpha = 0.1;
		double[] maxAction = {-100,3,10,-4};
		double[]  advisedAction={-20,-2,-5,1};
		double diff = evaluate(weights,maxAction)-evaluate(weights,advisedAction);

		System.out.println("diff = "+diff);
		double[] newWeights = new double[weights.length];
		for (int run = 0;run<20;run++)
		{
			for (int i =0;i<weights.length;i++)
			{
				newWeights[i] = weights[i];
				for (int j=0;j<weights.length;j++)
				{
					if (j!=i)
					{
						newWeights[i] = newWeights[i] + alpha * (weights[j]*((maxAction[j]-advisedAction[j])*(maxAction[i]-advisedAction[i])));
//						newWeights[i] = newWeights[i] + alpha * (weights[j]*((maxAction[j]-advisedAction[j])));
					}
				}
			}
			double[] weightsBefore = weights;
			weights = newWeights; 
		}
		
		double diffAfter = evaluate(weights,maxAction)-evaluate(weights,advisedAction);
		System.out.println(diff);
		System.out.println(diffAfter);
	}
	
	public static double evaluate(double[] weights, double [] vec)
	{
		double value = 0;
		for (int j=0;j<weights.length;j++)
		{
			value+=weights[j]*vec[j];
		}
		return value;
	}
}
