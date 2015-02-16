import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
public class StateSubsetSum {
	//Globals
	public static String[] states;
	public static int[] areas;
	public static boolean[][] dynamicTable;
	public static String finalSubset;
	public static int runningIndex;

	private static int getAreaGoal(int[] array, double percentage){
		int area = 0;
		for(int a : array){area += a;}
		int goalArea = (int)(area*percentage);
		return goalArea;
	}

	private static boolean[][] dataTable(int[] areas){
		int goalArea = getAreaGoal(areas, 0.47);
		boolean[][] table = new boolean[50][goalArea + 1];

		for(int i = 1; i < goalArea+1; i++)
		{
			table[0][i] = (areas[0] == i);
		}
		for(int i = 0; i < 50; i++)
		{
			table[i][0] = true;
		}
		for(int i = 1; i < 50; i++){
			for(int v = 1; v < goalArea+1; v++){
				if(areas[i] > v){
					table[i][v] = table[i-1][v];
				}
				else{
					table[i][v] = table[i-1][v] || table[i-1][v-areas[i]];
				}
			}
		}
		return table;
	}

	public static void buildStateSubset(int i, int v){
		if(v == 0){return;}
		else{
			if(i == 0){
				if(v == areas[0]){
										finalSubset += states[i] + ", ";
										runningIndex++;
								}
				else{return;}
			}
			else{
				if(i > 0 && areas[i] > v && v > 0){
					buildStateSubset(i-1,v);
				}
				else{
					if(dynamicTable[i-1][v]){
						buildStateSubset(i-1,v);
					}
					else{
												finalSubset += states[i] + ", ";
												runningIndex++;
						buildStateSubset(i-1,v-areas[i]);
					}
				}
			}
		}
	}

	public static void printSuccess(){
		System.out.println("Yes, there is such a subset:");
		finalSubset = new String();
		runningIndex = 0;
		buildStateSubset(dynamicTable.length-1, dynamicTable[0].length-1);
		String solution = finalSubset.toString();
		System.out.println(solution.substring(0, solution.length() - 2));
	}

	public static void loadData() throws FileNotFoundException {
		Scanner ioMgmt;
		ioMgmt = new Scanner(new FileInputStream("test_files/areas.txt"));

		states = new String[50];
		areas = new int[50];

		for(int i = 0; i < 50; i++)
		{
			String full = ioMgmt.nextLine();
			String[] data = full.split(",");
			states[i] = data[0];
			areas[i] = Integer.parseInt(data[1]);
		}
		ioMgmt.close();
	}

	public static void main(String[] args) throws FileNotFoundException {
		loadData();

		dynamicTable = dataTable(areas);
		if(dynamicTable[dynamicTable.length-1][dynamicTable[0].length-1]){
			printSuccess();
		}
		else{System.out.println("No, there is not such a subset.");}
	}
}
