package jumble_jump.view;

public class Output {

    public static void printError(String error) {
        System.out.printf("\n⚠ %s\n",error);
    }


    public static void printServiceMessage(String message) {
        System.out.print(message);
    }

    public static void printIntermediateStepAndCount(int numberOfSolving,String intermediateStep){
        printNumberOfSolving(numberOfSolving);
        printIntermediateStep(intermediateStep);
        System.out.println();
    }

    private static void printNumberOfSolving(int numberOfSolving){
        System.out.printf("풀이 횟수 : %d회\n", numberOfSolving);
    }

    private static void printIntermediateStep(String intermediateStep) {
        System.out.printf("풀이과정 : %s\n", intermediateStep);
    }

    public static void printResult(int numberOfSolving,String Problem,Number result){
        System.out.println("\n-------결과를 출력합니다.-------\n");
        printProblem(Problem);
        printTotalNumberOfSolving(numberOfSolving);
        System.out.println();
        printTotalNumberOfSolving(result);
    }

    private static void printProblem(String Problem) {
        System.out.printf("문제 : %s\n", Problem);
    }

    private static void printTotalNumberOfSolving(int numberOfSolving){
        System.out.printf("총 풀이 횟수 : %d회\n", numberOfSolving);
    }

    private static void printTotalNumberOfSolving(Number result){
        System.out.printf("답 : %s\n", result);
    }



}

