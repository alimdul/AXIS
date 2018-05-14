package ludapivovarevich.view;

import com.doszhan.CustomServiceStub;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelTable extends AbstractTableModel {

    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
    private static final int FOURTH_COLUMN = 3;
    private static final int COLUMN_COUNT = 4;
    private List<CustomServiceStub.Plant> tableData;

    public ModelTable()
    {
        tableData = new ArrayList<>();
    }

    public String getColumnName(int columnIndex)
    {
        switch(columnIndex)
        {
            case FIRST_COLUMN: return "Номер";
            case SECOND_COLUMN: return "Название цветка";
            case THIRD_COLUMN: return "Размер листа";
            case FOURTH_COLUMN: return "Размер стебля";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CustomServiceStub.Plant rows = tableData.get(rowIndex);

        String str = null;

        if(columnIndex == FIRST_COLUMN)
        {
            str = String.valueOf(rows.getId());
        }
        if(columnIndex == SECOND_COLUMN)
        {
            str = rows.getPlant();
        }
        else if(columnIndex == THIRD_COLUMN)
        {
            str = rows.getLeaf();
        }
        else if(columnIndex == FOURTH_COLUMN)
        {
            str = rows.getStem();
        }

        return str;
    }

    public int getRowCount()
    {
        return tableData.size();
    }

    public int getColumnCount()
    {
        return COLUMN_COUNT;
    }

    public void addDate(CustomServiceStub.Plant plant)
    {
        tableData.add(plant);
        fireTableDataChanged();
    }

    public void addNotation(List<CustomServiceStub.Plant> list)
    {
        deleteAllNotations();

        for(int i = 0; i < list.size(); i++){
            addDate(list.get(i));
        }
    }

    public void deleteAllNotations()
    {
        tableData.clear();
        fireTableDataChanged();
    }
}