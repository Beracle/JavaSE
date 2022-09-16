import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Book> LIST = new ArrayList<>();

    public static void main(String[] args) {
        readData();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("================ ͼ�����ϵͳ ================");
            System.out.println("1.������Ϣ");
            System.out.println("2.�޸���Ϣ");
            System.out.println("3.��ѯͼ���б�");
            System.out.println("4.ɾ��ͼ��");
            System.out.println("����������˳�ϵͳ��");
            String str = scanner.nextLine();
            switch (str){
                case "1":
                    insertBook(scanner);
                    break;
                case "2":
                    modifyBook(scanner);
                    break;
                case "3":
                    showBooks();
                    break;
                case "4":
                    deleteBook(scanner);
                    break;
                default:
                    saveData();
                    scanner.close();
                    return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void readData(){
        File file = new File("data");
        if(file.exists()) {
            try(ObjectInputStream inputStream = new ObjectInputStream((new FileInputStream("data")))){
                LIST = (List<Book>) inputStream.readObject();
            } catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        } else{
            LIST = new ArrayList<>();
        }
    }

    private static void saveData(){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("data"))){
            outputStream.writeObject(LIST);
            outputStream.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void modifyBook(Scanner scanner){
        int i = 0;
        for(Book book: LIST) System.out.println(i+"."+book);
        int index = scanner.nextInt();
        scanner.nextLine();
        if(index >= LIST.size())
            System.out.println("��������");
        else {
            LIST
                    .get(index)
                    .name(scanner.nextLine())
                    .author(scanner.nextLine())
                    .price(scanner.nextDouble());
        }
        scanner.nextLine();
    }

    private static void showBooks(){
        LIST.forEach(System.out::println);
    }

    private static void deleteBook(Scanner scanner){
        int i = 0;
        for (Book book: LIST){
            System.out.println(i + "." + book);
        }
        int index = scanner.nextInt();
        if (index >= LIST.size())
            System.out.println("��������");
        else
            LIST.remove(i);
        scanner.nextLine();
    }

    private static void insertBook(Scanner scanner){
        Book book = new Book()
                .name(scanner.nextLine())
                .author(scanner.nextLine())
                .price(scanner.nextDouble());
        LIST.add(book);
        scanner.nextLine();
    }

    private static class Book implements Serializable{
        String name;
        String author;
        transient double price;

        public Book name(String name){
            this.name = name;
            return this;
        }

        public Book author(String author){
            this.author = author;
            return this;
        }

        public Book price(double price){
            this.price = price;
            return this;
        }

        @Override
        public String toString() {
            return "�鼮{" +
                    "����='" + name + '\'' +
                    ", ����='" + author + '\'' +
                    ", �۸�=" + price +
                    '}';
        }
    }

}

