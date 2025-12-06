package com.shadowEater;

import javax.swing.table.AbstractTableModel;

public class ShadowTableModel extends AbstractTableModel {

    private final Object[][] data;
    private final String[] columns;

    public ShadowTableModel(int rows, int cols) {
        this.data = new Object[rows][cols];
        this.columns = new String[] {"Paid", "Free"};
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return data[0].length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    @Override
    public boolean isCellEditable(int r, int c) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
