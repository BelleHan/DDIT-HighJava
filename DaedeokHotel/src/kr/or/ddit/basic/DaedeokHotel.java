package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DaedeokHotel {
   private HashMap<Integer, Room> hotelMap;
   private Scanner scan;

   // ������
   public DaedeokHotel() {
      hotelMap = new HashMap<>();
      scan = new Scanner(System.in);

      // ���� �ʱ�ȭ
      for (int i = 2; i <= 4; i++) {
         String roomType = null;
         switch (i) {
      
         case 2:
            roomType = "�̱۷�";
            break;
         case 3:
            roomType = "�����";
            break;
         case 4:
            roomType = "����Ʈ��";
            break;
         }

         for (int j = 1; j <= 9; j++) {
            int roomNumber = i * 100 + j;
            Room room = new Room(roomNumber, roomType);
            hotelMap.put(roomNumber, room);
         }
      }
   }

   public static void main(String[] args) {
      new DaedeokHotel().hotelStart();
   }

   // ���� �޼���
   private void hotelStart() {
      System.out.println();
      System.out.println("**************************************");
      System.out.println("         ȣ�ڹ��� �������ϴ�. �������");
      System.out.println("**************************************");
      System.out.println();

      while (true) {
         int choice = displayMenu();
         switch (choice) {
         case 1: // üũ��
            checkIn();
            break;
         case 2: // üũ�ƿ�
            checkOut();
            break;
         case 3: // ���� ����
            showRoom();
            break;
         case 4: // ��������
            saveHotel();
            break;
         case 0: // ���� ����
            System.out.println("*************************");
            System.out.println("      ȣ�ڹ��� �ݾҽ��ϴ�.");
            System.out.println("*************************");
            return;
         default:
            System.out.println("�߸� �Է��߽��ϴ�.");
         }
      }
   }

   // üũ �ƿ��� �����ϴ� �޼���
   private void checkOut() {
      System.out.println();
      System.out.println("----------------------------------------------");
      System.out.println("    üũ�ƿ� �۾�");
      System.out.println("----------------------------------------------");
      System.out.println("  üũ�ƿ��� �� ��ȣ�� �Է��ϼ���.");
      System.out.print(" ���ȣ �Է� >> ");
      int num = scan.nextInt();

      // �Է��� ���ȣ�� Map�� Ű���� ������ �߸� �Է��� ���ȣ�̴�.
      if (!hotelMap.containsKey(num)) {
         System.out.println(num + "ȣ ������ �������� �ʽ��ϴ�.");
      } else if (hotelMap.get(num).getGuestName() == null) { // �ش� ���ǿ� �ٸ� �մ��� �ִ��� �˻�
         System.out.println(num + "ȣ ���ǿ��� üũ���� �մ��� �����ϴ�.");
      } else {
         // üũ �ƿ� �۾��� �ش� ������ �մ� �̸��� null�� �����ϸ� �ȴ�.

         // ���� �մ� �̸� ���ϱ�
         String name = hotelMap.get(num).getGuestName();

         hotelMap.get(num).setGuestName(null); // �մ� �̸��� null�� �����ϱ�

         System.out.println(num + "ȣ ������ " + name + "���� üũ�ƿ��� �Ϸ��߽��ϴ�.");
      }
   }

   // ���� ���¸� ����ϴ� �޼���
   private void showRoom() {
      System.out.println();

      // ���ȣ�� ������� ������ �ϱ� ���ؼ� ���ȣ�� List�� ���� �� �����Ѵ�.
      ArrayList<Integer> roomNumList = new ArrayList<>(hotelMap.keySet());
      Collections.sort(roomNumList); // ���ȣ�� �������� ����

      System.out.println("-----------------------------------------");
      System.out.println("   ���� ���� ����");
      System.out.println("-----------------------------------------");
      System.out.println("   �� ��ȣ    �� ����      ������ �̸�");
      System.out.println("-----------------------------------------");

      // List���� ���ȣ�� �ϳ��� �����ͼ� Map�� Ű�� �� �ش� ���ȣ�� �ش��ϴ�
      // Room��ü�� ���ؼ� ����Ѵ�.
      for (int roomNum : roomNumList) {
         Room r = hotelMap.get(roomNum);
         System.out.print(roomNum + "\t" + r.getRoomType() + "\t");
         String name = " -";
         if (r.getGuestName() != null) {
            name = r.getGuestName();
         }
         System.out.println(name);
      }
      System.out.println("-----------------------------------------");
      System.out.println();

   }

   //ȣ�������� ����� ���� �о���� �޼���
      private HashMap<Integer, Room> fileRead(){
         
         HashMap<Integer, Room> Map = null;
         File file = new File("D:/d_other/hotel.dat");
         
         
         if (!file.exists()) {
            return null;
         }
         
         
         ObjectInputStream ois = null;
         try {
            //��ü �Է¿� ��Ʈ�� ����
            ois = new ObjectInputStream(
                  new BufferedInputStream(
                    new FileInputStream(file)
                 )
            );
            
            //��ü�� �о ������ ����
            try {
               Map = (HashMap<Integer, Room>) ois.readObject();
            } catch (ClassNotFoundException e) {
               return null;
            }
         } catch (FileNotFoundException e) {
            return null;
         } catch (IOException e) {
            return null;
         }finally {
            if (ois!=null)try {ois.close();} catch (IOException e2) {}
         }
         return Map;
      }
      
      
      //ȣ�� ������ ���Ϸ� �����ϴ� �޼���
      private void saveHotel() {
         ObjectOutputStream oos = null;
         try {
            
            oos = new ObjectOutputStream(
                  new BufferedOutputStream(
                        new FileOutputStream("D:/d_other/hotel.dat")));
            oos.writeObject(hotelMap);
            oos.flush();
            
            System.out.println("���� �Ϸ�");
            
         } catch (IOException e) {
            e.printStackTrace();
         }finally {
            if(oos!=null)try {oos.close();} catch (IOException e2) {}
         }
            
      }
   // üũ���ϴ� �޼���
   private void checkIn() {

      System.out.println("----------------------------------------------");
      System.out.println("    üũ�� �۾�");
      System.out.println("----------------------------------------------");
      System.out.println("* 201~209 : �̱۷�");
      System.out.println("* 301~309 : �����");
      System.out.println("* 401~409 : ����Ʈ��");
      System.out.println("----------------------------------------------");
      System.out.print("�� ��ȣ �Է� >> ");
      int num = scan.nextInt();

      // �Է��� ���ȣ�� Map�� Ű���� ������ �߸� �Է��� ���ȣ�̴�.
      if (!hotelMap.containsKey(num)) {
         System.out.println(num + "ȣ ������ �������� �ʽ��ϴ�.");
      } else if (hotelMap.get(num).getGuestName() != null) { // �ش� ���ǿ� �ٸ� �մ��� �ִ��� �˻�
         System.out.println(num + "ȣ ���ǿ��� �̹� �մ��� �ֽ��ϴ�.");
      } else {
         // üũ�� �۾� �����ϱ�
         System.out.println("������ üũ�� �Ͻðڽ��ϱ�?");
         System.out.print(" �̸� �Է�>> ");
         String name = scan.next();

         // �Է� ���� ������ �̸��� �ش� ������ ������ ��ܿ� �ִ´�.
         hotelMap.get(num).setGuestName(name);
         System.out.println(name + "���� " + num + "ȣ ���ǿ� üũ�� �Ǿ����ϴ�.");

      }

   }

   // �޴��� ����ϰ� ������ �۾� ��ȣ�� ��ȯ�ϴ� �޼���
   private int displayMenu() {
      System.out.println("----------------------------------------------");
      System.out.println("  � ������ �Ͻðڽ��ϱ�?");
      System.out.println("  1. üũ��    2. üũ�ƿ�    3. ���ǻ���    4. ��������    0. ��������");
      System.out.println("----------------------------------------------");
      System.out.print(" ����>>   ");
      int num = scan.nextInt();
      return num;
   }
}

class Room implements Serializable {
   private static final long serialVersionUID = -6973482964023581752L;
   
   private int roomNumber; // ���ȣ
   private String roomType; // ������
   private String guestName; // �մ��̸�

   // ������
   public Room(int roomNumber, String roomType) {
      super();
      this.roomNumber = roomNumber;
      this.roomType = roomType;
   }

   public int getRoomNumber() {
      return roomNumber;
   }

   public void setRoomNumber(int roomNumber) {
      this.roomNumber = roomNumber;
   }

   public String getRoomType() {
      return roomType;
   }

   public void setRoomType(String roomType) {
      this.roomType = roomType;
   }

   public String getGuestName() {
      return guestName;
   }

   public void setGuestName(String guestName) {
      this.guestName = guestName;
   }

}