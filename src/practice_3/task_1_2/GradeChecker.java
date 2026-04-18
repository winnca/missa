package practice_3.task_1_2;

public class GradeChecker {
    static String getGrade(int score){
        int gradeChecker;
        if (score >= 90 && score <= 100) {
            gradeChecker = 5;
        } else if (score >= 80 && score <= 89){
            gradeChecker = 4;
        } else if (score >= 70 && score <= 79){
            gradeChecker = 3;
        } else if (score >= 60 && score <= 69){
            gradeChecker = 2;
        } else gradeChecker = 0;
        switch (gradeChecker){
            case 5:
                return "Отлично (A)";
            case 4:
                return "Хорошо (B)";
            case 3:
                return "Удовлетворительно (C)";
            case 2:
                return "Слабо (D)";
            default:
                return "Неудовлетворительно (F)";
        }
    }
    static String getGradeArrow(int score){
        int gradeChecker = 0;
        if (score >= 90 && score <= 100) gradeChecker = 5;
        else if (score >= 80 && score <= 89) gradeChecker = 4;
        else if (score >= 70 && score <= 79) gradeChecker = 3;
        else if (score >= 60 && score <= 69) gradeChecker = 2;
        else gradeChecker = 1;
        return switch (gradeChecker){
            case 5 -> "Отлично (A)";
            case 4 -> "Хорошо (B)";
            case 3 -> "Удовлетворительно (C)";
            case 2 -> "Слабо (D)";
            default -> "Неудовлетворительно (F)";
        };
    }

    public static void main(String[] args) {
        int[] testScores = {95, 85, 73, 62, 45, 100, 0};
        for (int score : testScores){
            System.out.println(score + " " + getGrade(score));
        }
        System.out.println();
        for (int score : testScores){
            System.out.println(score + " " + getGradeArrow(score));
        }
    }
}
