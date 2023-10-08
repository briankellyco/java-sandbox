package co.bk.javaskills.puzzles.strings;

/**
 * Visualise the cross as two intersecting diagonals and the task becomes easy.
 */
public class DrawCrossSimple {

	public static void main(String[] args) {
		drawX(7);
	}

	/**
	* Number input must be an odd number > 3
	* @param width
	*/
	public static void drawX(int width) {

		//Array to output final "X" shape
		int starOnePos = 0;
		int starTwoPos = width - 1;

		// for each line
		for (int i=0; i<=width; i++){
		
			// for each char
			for(int j=0; j<width; j++) {
				System.out.print(j == starOnePos || j == starTwoPos ? "*" : " ");
			}
			
			System.out.print("\n");
			
			// update star positions
			starOnePos++;
			starTwoPos--;
		}
	}	
}
