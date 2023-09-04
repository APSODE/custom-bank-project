package controller;

import entity.account.*;
import entity.user.User;
import entity.user.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class SerializerTest {
    private Serializer<User> userSerializer;
    private Serializer<UserAccount> userAccountSerializer;
    private Serializer<Account> accountSerializer;
    private Serializer<InstallmentAccount> installmentAccountSerializer;
    private Serializer<MinusAccount> minusAccountSerializer;
    private Serializer<PointAccount> pointAccountSerializer;
    private Serializer<SavingAccount> savingAccountSerializer;

    @BeforeEach
    void createSerializer() {
        this.userSerializer = new Serializer<>(User.class);
        this.userAccountSerializer = new Serializer<>(UserAccount.class);
        this.accountSerializer = new Serializer<>(Account.class);
        this.installmentAccountSerializer = new Serializer<>(InstallmentAccount.class);
        this.minusAccountSerializer = new Serializer<>(MinusAccount.class);
        this.pointAccountSerializer = new Serializer<>(PointAccount.class);
        this.savingAccountSerializer = new Serializer<>(SavingAccount.class);
    }


    @DisplayName("Serializable 확인")
    @Test
    void SerializableCheck() {

    }

    @DisplayName("객체 저장 테스트")
    @Test
    void saveObject() throws IOException {
        //given
        User testUser = User.CreateObject(
                "test user",
                20,
                "test phone number"
        );

        UserAccount testUserAccount = UserAccount.CreateObject(
                testUser,
                "testId",
                "testPw"
        );

        Account testAccount = Account.CreateObject(
                testUserAccount
        );

        MinusAccount testMinusAccount = MinusAccount.CreateObject(
                testUserAccount
        );

        InstallmentAccount testInstallmentAccount = InstallmentAccount.CreateObject(
                LocalDate.now(),
                0.5,
                testUserAccount
        );

        PointAccount testPointAccount = PointAccount.CreateObject(
                testUserAccount
        );

        SavingAccount testSavingAccount = SavingAccount.CreateObject(
                testUserAccount
        );


        //when

        userSerializer.saveObject(
                testUser,
                "UserObjectData.cbp"
        );

        userAccountSerializer.saveObject(
                testUserAccount,
                "testUserAccountObject.cbp"
        );

        accountSerializer.saveObject(
                testAccount,
                "testAccountObject.cbp"
        );

        installmentAccountSerializer.saveObject(
                testInstallmentAccount,
                "testInstallmentAccount.cbp"
        );

        minusAccountSerializer.saveObject(
                testMinusAccount,
                "testMinusAccount.cbp"
        );

        pointAccountSerializer.saveObject(
                testPointAccount,
                "testPointAccount.cbp"
        );

        savingAccountSerializer.saveObject(
                testSavingAccount,
                "testSavingAccount.cbp"
        );

        //then
        //정상적으로 오브젝트 데이터 파일이 생성되었는지 확인
        String[] testObjectFileDirs = {
                "UserObjectData.cbp",
                "testUserAccountObject.cbp",
                "testAccountObject.cbp",
                "testInstallmentAccount.cbp",
                "testMinusAccount.cbp",
                "testPointAccount.cbp",
                "testSavingAccount.cbp"
        };

        for (String testObjectFileDir : testObjectFileDirs) {
            File testObjectFile = new File("\\" + testObjectFileDir);
            assertEquals("\\" + testObjectFileDir, testObjectFile.getPath());
        }
    }

    @DisplayName("객체 로드 테스트")
    @Test
    void loadObject() {
        //given
        String userFileDir = "UserObjectData.cbp";
        String userAccountFileDir = "testUserAccountObject.cbp";
        String accountFileDir = "testAccountObject.cbp";
        String installmentAccountFileDir = "testInstallmentAccount.cbp";
        String minusAccountFileDir = "testMinusAccount.cbp";
        String pointAccountFileDir = "testPointAccount.cbp";
        String savingAccountFileDir = "testSavingAccount.cbp";

        //when
        User testUser = this.userSerializer.loadObject(userFileDir);

        UserAccount testUserAccount = this.userAccountSerializer.loadObject(userAccountFileDir);

        Account testAccount = this.accountSerializer.loadObject(accountFileDir);

        InstallmentAccount testInstallmentAccount = this.installmentAccountSerializer.loadObject(installmentAccountFileDir);

        MinusAccount testMinusAccount = this.minusAccountSerializer.loadObject(minusAccountFileDir);

        PointAccount testPointAccount = this.pointAccountSerializer.loadObject(pointAccountFileDir);

        SavingAccount testSavingAccount = this.savingAccountSerializer.loadObject(savingAccountFileDir);

        //then
        assertThat(testUser)
                .isInstanceOf(User.class);

        assertThat(testUserAccount)
                .isInstanceOf(UserAccount.class);

        assertThat(testAccount)
                .isInstanceOf(Account.class);

        assertThat(testInstallmentAccount)
                .isInstanceOf(InstallmentAccount.class);

        assertThat(testMinusAccount)
                .isInstanceOf(MinusAccount.class);

        assertThat(testPointAccount)
                .isInstanceOf(PointAccount.class);

        assertThat(testSavingAccount)
                .isInstanceOf(SavingAccount.class);

    }
}