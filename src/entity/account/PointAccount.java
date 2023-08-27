package entity.account;

public class PointAccount extends Account{
    private long point;
    protected boolean IsPossibleToUsePoint(long amount){
    }
    public void setPoint(long point){
        this.point = point;
    }
}
