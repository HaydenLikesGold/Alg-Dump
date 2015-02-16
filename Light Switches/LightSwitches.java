import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
* Created by hgoldstien on 11/6/14.
*/
public class LightSwitches {

		private static ArrayList<Integer> getDivs(int x, int n)
		{
				ArrayList<Integer> solution = new ArrayList<Integer>();
				for (int i = 2; i < n; i++)
				{
						if (x%i ==0)
						{
								solution.add(i);
						}
				}
				return solution;
		}
		private static boolean buldStatus(int n, int t, int b)
		{
				int max = t%n;
				boolean status = false;
				for (int i = 0; i<=max; i++)
				{
						if (i == 1)
								status = true;
						if (i > 1)
						{
								ArrayList<Integer> divs = getDivs(b, n);
								for (Integer div : divs)
								{
										if (div <= max)
										{
												status = !status;
										}
								}
								return status;
						}
				}
				return status;
		}

		public static void main(String[] args)
		{
				Scanner input = new Scanner(System.in);
				Integer caseNumber = 0;
				while(input.hasNextLine())
				{
						caseNumber++;
						int n = input.nextInt();
						int t = input.nextInt();
						int b = input.nextInt();
						boolean status = buldStatus(n, t, b);

						System.out.println("Case "+caseNumber+": "+status);
				}
		}
}
