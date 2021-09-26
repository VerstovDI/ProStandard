package ru.Aidar.ParsingProffStandards;

class Test {
    public void calculate(int[][] arr) {
        int summ = 0;
        if (arr.length > 1 && arr[0].length > 1) {
            int[] columnSumms = new int[arr.length];
            int[] vectorSumms = new int[arr[0].length];
            for (int[] column : arr) {
                for (int cell : column) {
                }
            }
        } else if (arr.length > 1) {
            for (int[] column : arr) {
                summ += column[0];
            }
        }
        //else if() то же самое
    }
}


