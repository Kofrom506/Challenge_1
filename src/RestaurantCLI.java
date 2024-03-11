
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
class Order {
    String foodName;
    double price;
    int quantity;

    public Order(String foodName, double price, int quantity) {
        this.foodName = foodName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}

public class RestaurantCLI {
    private static List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double total = 0.0;
        int qty = 0;
        System.out.println("===========================");
        System.out.println("Selamat Datang di BinarFud!");
        System.out.println("===========================");
        while (true) {

        System.out.println("Silahkan Pilih Makanan:");
        System.out.println("1. Nasi Goreng | 15.000");
        System.out.println("2. Mie Goreng |  13.000");
        System.out.println("3. Nasi + Ayam |  18.000");
        System.out.println("4. Es Teh Manis |  3.000");
        System.out.println("5. Es Jeruk |  5.000");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar Aplikasi\n");


            System.out.print("=> ");
            int choice = scanner.nextInt();

            Order order = null;
            System.out.println("===========================");
            System.out.println("Berapa Pesanan Anda");
            System.out.println("===========================");

            switch (choice) {
                case 1:
                    System.out.println("Nasi Goreng | 15.000");
                    System.out.println("(Input 0 untuk kembali)");

                    System.out.print("qty => ");
                    qty = scanner.nextInt();
                    if (qty == 0){
                        continue;
                    }
                    order = new Order("Nasi Goreng", 15000, qty);
                     break;
                case 2:
                    System.out.println("Mie Goreng | 13.000");
                    System.out.println("(Input 0 untuk kembali)");

                    System.out.print("qty => ");
                    qty = scanner.nextInt();

                    if (qty == 0){
                        continue;
                    }
                    order = new Order("Mie Goreng", 13000, qty);
                    break;
                case 3:
                    System.out.println("Nasi + Ayam | 18.000");
                    System.out.println("(Input 0 untuk kembali)");

                    System.out.print("qty => ");
                    qty = scanner.nextInt();
                    if (qty == 0){
                        continue;
                    }
                    order = new Order("Nasi + Ayam", 18000, qty);
                    break;
                case 4:
                    System.out.println("Es Teh Manis | 3.000");
                    System.out.println("(Input 0 untuk kembali)");

                    System.out.print("qty => ");
                    qty = scanner.nextInt();
                    if (qty == 0){
                        continue;
                    }
                    order = new Order("Es Teh Manis", 3000, qty);

                    break;
                case 5:
                    System.out.println("Es Jeruk | 5.000");
                    System.out.println("(Input 0 untuk kembali)");

                    System.out.print("qty => ");
                    qty = scanner.nextInt();
                    if (qty == 0){
                        continue;
                    }
                    order = new Order("Es Jeruk", 5000, qty);

                    break;
                case 99:
                    confirmBill();
                    System.out.println("1. Konfirmasi dan Bayar");
                    System.out.println("2. Kembali Ke Menu Utama");
                    System.out.println("0. Keluar Aplikasi");
                    System.out.print("=> ");
                    choice = scanner.nextInt();

                    switch (choice){
                        case 1:
                            printBill();
                            return;
                        case 2:
                            continue;
                        case 0:
                            break;
                        default:
                            continue;
                    }
                case 0:
                    System.out.println("Sukses Keluar Aplikasi");
                    return;
                default:
                    System.out.println("Perintah Tidak Dikenal. Silahkan Ulangi Input");
                    continue;

            }

            total += order.getTotalPrice();
            orders.add(order);
            System.out.println("\n");


        }


    }
    private static void confirmBill() {
        System.out.println("====================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("====================");
        for (Order order : orders) {
            System.out.println(order.foodName + "\t" + order.quantity + "\t"  +  order.price );
        }
        System.out.println("-------------------------+");

        System.out.println("Total : \t" + orders.stream().mapToInt(order -> order.quantity).sum()+  "\t" + orders.stream().mapToDouble(Order::getTotalPrice).sum());
    }

    private static void printBill() {
        try (FileWriter fileWriter = new FileWriter("bill.txt")) {
            fileWriter.write("====================\n");
            fileWriter.write("Binarfud\n");
            fileWriter.write("====================\n");
            fileWriter.write("Terimakasih Telah sudah memesan di Binarfud \n\n");
            fileWriter.write("Dibawah ini adalah pesanan anda \n\n");

            for (Order order : orders) {
                fileWriter.write(order.foodName + "\t" + order.quantity + "\t" + order.price + "\n");
            }

            fileWriter.write("-------------------------+\n");
            fileWriter.write("Total : \t" + orders.stream().mapToInt(order -> order.quantity).sum() + "\t" + orders.stream().mapToDouble(Order::getTotalPrice).sum() + "\n\n");
            fileWriter.write("Pembayaran: BinarCash \n\n");
            fileWriter.write("========================= \n");
            fileWriter.write("Simpan Struk Ini sebagai\n");
            fileWriter.write("Bukti Pembayaran\n");
            fileWriter.write("========================= \n");
            System.out.println("Sukses Mencetak Bon pada bill.txt");


        } catch (IOException e) {
            System.out.println("Error Writing File");
            e.printStackTrace();
        }
    }
}
