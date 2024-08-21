package prac.shpp.printers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prac.shpp.entities.Table;
import prac.shpp.validators.OverflowValidator;

import java.math.BigDecimal;
import java.util.List;

import static prac.shpp.App.LOGGER;

public class TablePrinter {

    public static final Logger TABLE_LOGGER = LoggerFactory.getLogger("com.example.table");

    private static final StringBuilder sb = new StringBuilder();

    private static final String OVERFLOW = "OVERFLOW";

    private static final char SPACE = ' ';

    private static final char NEW_LINE = '\n';

    private static final char HORIZONTAL_DASH = '-';

    private static final char VERTICAL_DASH = '|';

    private static final char JOINT = '+';

    private static final int COLUMN_WIDTH = 14;

    private TablePrinter() {
        throw new IllegalStateException("Utility class");
    }

    public static void printTable(Table table) {
        LOGGER.debug("Table printer started.");

        List<BigDecimal> header = table.getHeader();
        List<List<BigDecimal>> numberTable = table.getNumberTable();

        int tableSize = header.size();
        LOGGER.debug("Table size: {}", tableSize);
        int tableWidth = (tableSize + 1) * COLUMN_WIDTH + (tableSize + 2);
        LOGGER.trace("Table width: {}", tableSize);

        sb.append(NEW_LINE);
        addHeader(header, tableWidth);
        addTableRows(header, numberTable, tableWidth);

        TABLE_LOGGER.info("{}", sb);
        LOGGER.debug("Table printer ended its job.");
    }

    private static void addTableRows(List<BigDecimal> header, List<List<BigDecimal>> numberTable, int tableWidth) {
        for (int i = 0; i < numberTable.size(); ++i) {
            addNumberCell(header.get(i));
            addNumberRow(numberTable.get(i));
            addNewLine(tableWidth);
        }
    }

    private static void addHeader(List<BigDecimal> header, int tableWidth) {
        addNewLine(tableWidth);
        addEmptyCell();
        addNumberRow(header);
        addNewLine(tableWidth);
    }


    private static void addSpaces(int length) {
        TablePrinter.sb.append(String.valueOf(SPACE).repeat(Math.max(0, length)));
    }

    private static void addNewLine(int length) {
        for (int i = 0; i < length; ++i) {
            if (i % (COLUMN_WIDTH + 1) == 0) {
                TablePrinter.sb.append(JOINT);
            } else {
                TablePrinter.sb.append(HORIZONTAL_DASH);
            }
        }
        TablePrinter.sb.append(NEW_LINE);
    }

    private static void addNumberRow(List<BigDecimal> numberRow) {
        for (BigDecimal bigDecimal : numberRow) {
            addNumberCell(bigDecimal);
        }

        TablePrinter.sb.append(VERTICAL_DASH);
        TablePrinter.sb.append(NEW_LINE);
    }

    private static void addNumberCell(BigDecimal number) {
        TablePrinter.sb.append(VERTICAL_DASH);

        if (OverflowValidator.checkIfOverflowed(number)) {
            TablePrinter.sb.append(String.valueOf(SPACE).repeat(Math.max(0, COLUMN_WIDTH - OVERFLOW.length())));
            TablePrinter.sb.append(OVERFLOW);
        } else {
            int numberDigits = getNumberLength(number);
            TablePrinter.sb.append(String.valueOf(SPACE).repeat(Math.max(0, COLUMN_WIDTH - numberDigits)));
            TablePrinter.sb.append(number);
        }

    }

    private static void addEmptyCell() {
        TablePrinter.sb.append(VERTICAL_DASH);
        addSpaces(COLUMN_WIDTH);
    }

    private static int getNumberLength(BigDecimal n) {
        return n.toString().length();
    }

    public static String getPrintedTable() {
        return sb.toString();
    }
}
