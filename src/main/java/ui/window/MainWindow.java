package ui.window;

import entidades.Medico;
import service.MedicoDataService;
import sun.misc.ClassLoaderUtil;
import ui.style.BasicStyle;
import utils.FileLoader;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MainWindow extends basicWindow {

    private JPanel panel;
    private JButton btnMedicoWindow;
    private JButton btnMenuWindow;

    private JButton btnTest;

    public MainWindow() {


        btnMedicoWindow = createBtnMedicoWindow();
        btnMenuWindow = createBtnMenuWindow();

        btnTest = new JButton("TEST");
        btnTest.addActionListener(e -> {
            System.out.println("LPM");
        });

        btnMedicoWindow.addActionListener(e -> {
            panel.setVisible(false);
            panel = createMedicosPanel();
            panel.setVisible(true);
            window.add(panel, BorderLayout.CENTER);
        });

        btnMenuWindow.addActionListener(e -> {
            panel.setVisible(false);
            panel = createMainPanel();
            panel.setVisible(true);
            window.add(panel, BorderLayout.CENTER);
        });

        panel = createTestPanel();
        window.setTitle("Sistema de turnos medicos - IGANCIO 5000");
        window.add(panel, BorderLayout.CENTER);
        Image image = new FileLoader().getIcon("inicio");
        window.setIconImage(image);
    }

    private JButton createBtnMedicoWindow() {
        JButton button = new JButton("MENU MEDICO");
        return button;
    }

    private JButton createBtnMenuWindow() {
        JButton button = new JButton("VOLVER A MENU");
        return button;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.green);
        panel.add(btnMedicoWindow);
        return panel;
    }

    private JPanel createTestPanel() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.blue);
        contentPane.add(createHeaderPanel(),BorderLayout.PAGE_START);
        contentPane.add(createBodyPanel(),BorderLayout.CENTER);

        contentPane.setVisible(true);
        return contentPane;
    }

    private JPanel createHeaderPanel() {
        JPanel contentPane = new JPanel(new BorderLayout());

        contentPane.setBackground(Color.darkGray);
        contentPane.setPreferredSize(new Dimension(0, 80));

        JLabel lblTitulo = new JLabel("LISTADO DE MEDICOS");
        lblTitulo.setIcon(new ImageIcon(FileLoader.getIcon("inicio64")));
        lblTitulo.setHorizontalAlignment(JLabel.CENTER);
        lblTitulo.setVerticalAlignment(JLabel.CENTER);
        lblTitulo.setFont(BasicStyle.getTitleFont());
        lblTitulo.setForeground(Color.lightGray);

        contentPane.add(lblTitulo, BorderLayout.CENTER);

        contentPane.add(BasicStyle.getBackBtn("Volver a Menu"), BorderLayout.WEST);
        return contentPane;
    }

    private JPanel createBodyPanel() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Color.lightGray);

        String[] columnNames = { "Apellido", "Nombre", "DNI", "Precio Consulta", "Status" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
//                if (column == 4)
//                    return true;
//                else
                    return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableCellRenderer tableRenderer = table.getDefaultRenderer(JButton.class);
        table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableRenderer));

        // table.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());;

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting())
                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
        });

        List<Medico> list = MedicoDataService.SelectAll(false);
        list.forEach(medico -> {
             Object[] data = {medico.getApellido(), medico.getNombre(), medico.getDni(), medico.getPrecio_consulta(), medico.getStatus() ? "Activo" : "Inactivo"};
            //Object[] data = medico.medicoToRow();
            tableModel.addRow(data);
        });

        contentPane.add(new JScrollPane(table));
        contentPane.add(new JButton("BTN"));
        return contentPane;
    }

    private JPanel createMedicosPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
        panel.setBackground(Color.red);
        panel.add(btnMenuWindow);


        String[] columnNames = { "Apellido", "Nombre", "DNI", "Precio Consulta", "Status" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        panel.add(table.getTableHeader());

        List<Medico> list = MedicoDataService.SelectAll(false);
        list.forEach(medico -> {
            Object[] data = {medico.getApellido(), medico.getNombre(), medico.getDni(), medico.getPrecio_consulta(), medico.getStatus() ? "Activo" : "Inactivo"};
            tableModel.addRow(data);
        });

        panel.add(table);
        return panel;
    }

    class JTableButtonRenderer implements TableCellRenderer {
        private TableCellRenderer defaultRenderer;
        public JTableButtonRenderer(TableCellRenderer renderer) {
            defaultRenderer = renderer;
        }
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if(value instanceof Component)
                return (Component)value;
            return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    class JTableButtonModel extends AbstractTableModel {
        private Object[][] rows = {{"Button1", btnTest},{"Button2", new JButton("Button2")},{"Button3", new JButton("Button3")}, {"Button4", new JButton("Button4")}};
        private String[] columns = {"Count", "Buttons"};
        public String getColumnName(int column) {
            return columns[column];
        }
        public int getRowCount() {
            return rows.length;
        }
        public int getColumnCount() {
            return columns.length;
        }
        public Object getValueAt(int row, int column) {
            return rows[row][column];
        }
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
    }

}
