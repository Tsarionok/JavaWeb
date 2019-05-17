package by.epam.firsttask.dao;

import by.epam.firsttask.entity.FoodType;
import by.epam.firsttask.entity.TransportType;
import by.epam.firsttask.entity.Voucher;
import by.epam.firsttask.entity.VoucherType;
import by.epam.firsttask.service.FromMenuToMenu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class TravelCompany {

    private static final Logger log = LogManager.getLogger(TravelCompany.class);
    private static Path dataBase;

    public TravelCompany() {
        dataBase = Paths.get("dataBase/voucherBase.txt");
        try {
            if (Files.notExists(dataBase)) {
                Files.createFile(dataBase);
            }
        }
        catch (IOException ioException){
            log.error("FileCreatingException");
        }
    }

    public void writeVoucher(TransportType transportType, FoodType foodType,
                             int dayAmount, VoucherType voucherType, double price) {

        List<String> voucherInfo = Arrays.asList(transportType.toString(),
                                                 foodType.toString(),
                                                 Integer.toString(dayAmount),
                                                 voucherType.toString(),
                                                 Double.toString(price),
                                                 "\n");
        try {
            Files.write(dataBase, voucherInfo, Charset.forName("UTF-8"), StandardOpenOption.APPEND);

        } catch (NullPointerException nullPointer){
            log.error("NullPointerException");
        }
        catch (IOException ioException) {
            log.error("FileWritingException");
        }
    }

    public void writeVoucher(Voucher voucher){
        writeVoucher(voucher.getTransportType(), voucher.getFoodType(), voucher.getDayAmount(),
                voucher.getVoucherType(), voucher.getPrice());
    }

    public void readVoucher() {
        try {

            Reader reader = Files.newBufferedReader(dataBase, Charset.forName("UTF-8"));
            String info = ((BufferedReader) reader).readLine();

            while (info != null){
                System.out.println("Transport type: " + info);
                info = ((BufferedReader) reader).readLine();
                System.out.println("Food type: " + info);
                info = ((BufferedReader) reader).readLine();
                System.out.println("Amount of days: " + info);
                info = ((BufferedReader) reader).readLine();
                System.out.println("Vouchers type: " + info);
                info = ((BufferedReader) reader).readLine();
                System.out.println("Price: " + info);
                info = ((BufferedReader) reader).readLine();
                info = ((BufferedReader) reader).readLine();
                info = ((BufferedReader) reader).readLine();
                System.out.println();
            }
        } catch (IOException ioException) {
            log.error("FileReadingException");
        }
    }
}
