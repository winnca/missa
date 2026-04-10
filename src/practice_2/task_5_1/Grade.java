package practice_2.task_5_1;

public enum Grade {
    A("Excellent", 5.0),
    B("Good", 3.0),
    C("Normal", 2.0),
    D("Poor", 1.0),
    F("Fail", 0.0);

    String description;

    double gpaValue;

    Grade(String description, double gpaValue) {
        this.description=description;
        this.gpaValue=gpaValue;
    }

    public String getDescription() {
        return description;
    }

    public double getGpaValue() {
        return gpaValue;
    }

    static Grade fromScore(int score){
        if (score >= 90) return A;
        else if (score >= 80) return B;
        else if (score >= 70) return C;
        else if (score >= 60) return D;
        else return F;
    }
    boolean isPassing(){
        return (this != F && this != D);
    }
}
