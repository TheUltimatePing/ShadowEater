package com.shadowEater;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class ShadowTable {
    public static JTable getTable(int x, int y) {
        return new JTable(new ShadowTableModel(y, x));
    }
}
