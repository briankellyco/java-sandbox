package co.bk;

public class CodilityTask01 {

    public static void main(String[] args) {

            /*
             * Codility reported this test dataset as failing but I believe the correct solution is returned.
             *
             * Code has not been modified since test session.
             */
            int U = 3;
            int L = 2;
            int[] C = {2, 1, 1, 0, 1};

            // correct example
//        int U = 2;
//        int L = 3;
//        int[] C = {2, 0, 2, 0};

            CodilityTask01 codilityTask = new CodilityTask01();
            String result = codilityTask.solution(U,L,C);
            System.out.println("result: " + result);
    }


    /*
     * U = upper row sum
     * L = lower row sum
     * C = sum of ints in column K
     *
     * RETURN string of "numbersU,numbersL" or "IMPOSSIBLE"
     */
    public String solution(int U, int L, int[] C) {

        int rowLen = 2;
        int colLen = C.length;

        Integer[][] matrix = new Integer[rowLen][colLen];

        int sumColumnValues = 0;
        int upperRowBoundCount = 0;
        for(int j = 0; j < colLen; j++) {

            int columnValue = C[j];

            sumColumnValues += columnValue;

            System.out.println(columnValue);
            if (columnValue == 2) {
                matrix[0][j] = 1;
                matrix[1][j] = 1;
                upperRowBoundCount++;
            } else if (columnValue == 1) {

                if (upperRowBoundCount < U) {
                    matrix[0][j] = 1;
                    matrix[1][j] = 0;
                    upperRowBoundCount++;
                } else {
                    matrix[0][j] = 0;
                    matrix[1][j] = 1;
                }

            } else {
                matrix[0][j] = 0;
                matrix[1][j] = 0;
            }
        }

        int sumU = 0;
        String numbersU = "";
        int sumL = 0;
        String numbersL = "";
        for (int row=0; row < matrix.length; row++)  {
            for (int col=0; col < matrix[row].length; col++) {

                if (row == 0) {
                    int value = matrix[row][col];
                    sumU += value;
                    numbersU += value;
                } else if (row == 1) {
                    // 2nd row
                    int value = matrix[row][col];
                    sumL += value;
                    numbersL += value;
                }
            }
        }

        if (sumColumnValues != U + L) {
            return "IMPOSSIBLE";
        }

        return numbersU + "," + numbersL;
    }
}
