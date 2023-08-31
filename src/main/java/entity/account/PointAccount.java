package entity.account;

import controller.exceptions.PointException;
import entity.user.UserAccount;
import controller.Judger;

import java.io.Serializable;

public class PointAccount extends Account implements Serializable {

    private long point;
    public PointAccount(UserAccount userAccount) {
        super(userAccount);
    }

    public long getPoint() {
        return point;
    }

    public boolean earnPoints(long amount) {
        // ����Ʈ ���� �������� �Ա��̶� ����谡 �־����� �𸣰���
        // �ش� if�� ���� ���ǽ��� false���� ����Ʈ ������ ���� �ʾƵ� �ش� �޼ҵ�� true�� ������
        if (Judger.isPossibleToWithdraw(long balance,amount)) {
            this.point += (long) (amount * 0.03);
        }
        return true;
    }

    public boolean usePoints(long amount) throws PointException {
        // Exception�� �߻���ų�� Judger���� �߻���Ű�� ���� �ƴ� �ش� �޼ҵ� �ȿ��� �߻���Ű�� ���� ����
        if (Judger.isPossibleToUsePoint(this.getBalance(), amount, this.point)){
            if (amount <= this.point) {
                this.point -= amount;

            } else {
                this.setBalance(this.getBalance() + this.point - amount);

            }
        }
        return true;
    }

    // usePoints �����丵 ���� �ڵ�
    private boolean exampleUsePointsMethod(long amount) throws PointException {
        if (!Judger.isOverThanMinUsePoint(this.point)) {
            throw new PointException("����Ʈ ����� 5000����Ʈ���� ����� �����մϴ�.");
        }

        if (!Judger.isShortOfBalance(this.getBalance(), amount, this.point)) {
            throw new PointException("������ �ܾ��� �����մϴ�.");
        }

        if (amount <= this.point) {
            this.point -= amount;
        } else {
            this.setBalance(
                    this.getBalance() + this.point - amount
            );
        }

        return true;
    }
}