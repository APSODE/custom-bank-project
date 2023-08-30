package controller;

import java.io.*;

public class Serializer <T> {
    /*
    제네릭 타입은 컴파일 과정에서만 타입 데이터를 저장하고 컴파일 이후에는 Object를 타입으로 가지므로
    역직렬화 과정에서 타입 캐스팅 관련 문제가 발생할 가능성이 존재한다.
    따라서 Serializer클래스의 생성자의 파라미터로 추가로 타입 데이터를 전달받아서 객체 내부 상수 필드에 타입을 저장하여
    컴파일 과정 이후에 제네릭 타입이 Object타입으로 변경되어 발생하는 타입 캐스팅 문제를 해결한다.
     */

    private final Class<T> targetObjectType;

    public Serializer(Class<T> targetObjectType) {
        // 제네릭 타입의 저장을 위한 필드 초기화
        this.targetObjectType = targetObjectType;
    }

    public void saveObject(T targetObject, String saveFileName) throws IOException{
        // try-with-resources
        try (
                FileOutputStream fos = new FileOutputStream(saveFileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(
                    targetObject
            );

        } catch (IOException IOE) {
            Printer.print(
                    "파일 입출력 과정에서 에러가 발생하였습니다.\n" +
                    "stack trace를 참조하십시오."
            );
            IOE.printStackTrace();
        }
    }

    public T loadObject(String targetFileName) throws IOException{
        // 제네릭을 통해 지정한 타입의 오브젝트가 저장될 변수
        T loadedObject = null;

        // try-with-resources
        try (
                FileInputStream fis = new FileInputStream(targetFileName);
                ObjectInputStream ois = new ObjectInputStream(fis)) {

            loadedObject = this.targetObjectType.cast(
                    ois.readObject()
            );
        } catch (ClassNotFoundException CNFE) {
            Printer.print(
                    "역직렬화 대상의 클래스와 일치하는 클래스를 찾을 수 없습니다.\n" +
                    "역직렬화 대상의 클래스가 존재하는지 다시 한번 확인하십시오."
            );

        } catch (ClassCastException CCE) {
            Printer.print(
                    "시리얼라이저에 등록된 타입과 일치하지 않은 오브젝트 데이터 파일이 역직렬화 되었습니다.\n" +
                    "오브젝트 데이터 파일의 타입을 다시 한번 확인하십시오."
            );

        } catch (IOException IOE) {
            Printer.print(
                    "파일 입출력 과정에서 에러가 발생하였습니다.\n" +
                    "stack trace를 참조하십시오."
            );
            IOE.printStackTrace();
        }

        // nullable
        return loadedObject;
    }
}
