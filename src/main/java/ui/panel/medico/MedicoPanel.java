package ui.panel.medico;

import entidades.Medico;
import service.MedicoDataService;
import ui.panel.MainPanel;
import ui.style.BasicStyle;
import ui.window.MainWindow;
import utils.FileLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static ui.panel.BasicPanel.createBasicPanel;

public class MedicoPanel {
    private JPanel panel;

    private MedicoDataService service;

    private JLabel lblMedico;
    private JButton btnMedicoStatus;
    private JButton btnMedicoEdit;
    private JButton btnMedicoAdd;

    private JTable tableMedicos;

    private JButton btnBack;

    public MedicoPanel() {
        panel = createBasicPanel();
        service = new MedicoDataService();

        btnBack = BasicStyle.getBackBtn();
        btnBack.addActionListener((e -> {
            MainPanel mainPanel = new MainPanel();
            MainWindow.changePage(mainPanel.getPanel());
        }));

        btnMedicoAdd = BasicStyle.getCustomBtn("Agregar", 150, 60);
        btnMedicoAdd.setIcon(FileLoader.getImageIcon("plus32"));
        btnMedicoAdd.addActionListener(e -> {
            MainWindow.changePage(new MedicoAddPanel().getPanel());
        });

        btnMedicoStatus = BasicStyle.getCustomBtn("Status",150, 60);
        btnMedicoStatus.setIcon(FileLoader.getImageIcon("delete32"));
        btnMedicoStatus.setEnabled(false);
        btnMedicoStatus.addActionListener(e -> {
            /*
             * Se va buscar el indice de la Row seleccionada en la tabla
             * Con el indice del row, obtenemos el id_medico y el status actual
             * Se llama al Dataservice para actualizar el status
             * */
            Integer indexRow = tableMedicos.getSelectedRow();
            int id_medico = (int) tableMedicos.getValueAt(indexRow, 0);
            boolean status = tableMedicos.getValueAt(indexRow, 5 ) == "Activo" ? false : true;
            boolean result = service.updateMedicoStatus(id_medico, status);

            /*
             * Si el resultado de actualizar el status es true
             * */
            if (result) {
                System.out.println(String.format("Medico status updated"));
                btnMedicoStatus.setEnabled(false);
                btnMedicoStatus.setIcon(FileLoader.getImageIcon("delete32"));
                btnMedicoEdit.setEnabled(false);
                btnMedicoStatus.setText("Status");
                lblMedico.setText("");
                tableMedicos.changeSelection(indexRow, 0, true, false);

                String[] columnNames = { "ID", "Apellido", "Nombre", "DNI", "Precio Consulta", "Status" };
                DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
//                if (column == 4)
//                    return true;
//                else
                        return false;
                    }
                };

                tableMedicos.setModel(tableModel);
                loadMedicosTable(tableModel);
            } else {
                System.out.println(String.format("Error updating medico status"));
            }
        });

        btnMedicoEdit = BasicStyle.getCustomBtn("Editar",150, 60);
        btnMedicoEdit.setEnabled(false);
        btnMedicoEdit.setIcon(FileLoader.getImageIcon("edit32"));
        btnMedicoEdit.addActionListener((e -> {
            Integer indexRow = tableMedicos.getSelectedRow();
            int id_medico = (int) tableMedicos.getValueAt(indexRow, 0);
            MainWindow.changePage(new MedicoEditPanel(id_medico).getPanel());
        }));

        lblMedico = new JLabel();
        lblMedico.setText("");
        lblMedico.setFont(BasicStyle.getMenuBtnFont());
        lblMedico.setPreferredSize(new Dimension(300, 60));
        lblMedico.setVerticalAlignment(SwingConstants.CENTER);
        lblMedico.setHorizontalAlignment(SwingConstants.LEADING);

        panel.add(createPage());
    }

    private JPanel createPage() {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(Color.blue);
        /*CABECERA*/
        page.add(createHeaderPanel(),BorderLayout.PAGE_START);
        /*CUERPO*/
        page.add(createBodyPanel(),BorderLayout.CENTER);
        page.setVisible(true);
        return page;
    }

    private JPanel createHeaderPanel() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.darkGray);
        contentPane.setPreferredSize(new Dimension(800, 80));

        JLabel lblTitulo = BasicStyle.getHeaderTitle("Listado de medicos");
        contentPane.add(lblTitulo, BorderLayout.CENTER);

        contentPane.add(btnBack, BorderLayout.WEST);
        return contentPane;
    }

    private JPanel createBodyPanel() {
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setBackground(Color.lightGray);

        String[] columnNames = { "ID", "Apellido", "Nombre", "DNI", "Precio Consulta", "Status" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableMedicos = new JTable(tableModel);
        createMedicosTable();

        loadMedicosTable(tableModel);

        contentPane.add(new JScrollPane(tableMedicos));

        JPanel btnPanel = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        btnPanel.setLayout(layout);

        btnPanel.add(lblMedico);
        btnPanel.add(btnMedicoStatus);
        btnPanel.add(btnMedicoEdit);
        btnPanel.add(btnMedicoAdd);
        contentPane.add(btnPanel);
        return contentPane;
    }

    private void loadMedicosTable(DefaultTableModel tableModel) {
        java.util.List<Medico> list = service.SelectAll(false);
        list.forEach(medico -> {
            //Object[] data = {medico.getApellido(), medico.getNombre(), medico.getDni(), medico.getPrecio_consulta(), medico.getStatus() ? "Activo" : "Inactivo"};
            Object[] data = medico.medicoToRow();
            tableModel.addRow(data);
        });
    }

    private void createMedicosTable() {
        tableMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableMedicos.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                Integer indexRow = tableMedicos.getSelectedRow();
                if(indexRow>=0) {
                    String apellido = (String) tableMedicos.getValueAt(indexRow, 1);
                    String nombre = (String) tableMedicos.getValueAt(indexRow, 2);
                    String status = (String)tableMedicos.getValueAt(indexRow, 5);

                    String medicoFullname = String.format("Medico: %s, %s", apellido, nombre, status);
                    String btnText = status == "Activo" ? "Desactivar" : "Activar";

                    lblMedico.setText(medicoFullname);
                    btnMedicoStatus.setText(btnText);

                    if(status == "Activo") {
                        btnMedicoStatus.setIcon(FileLoader.getImageIcon("delete32"));
                    } else {
                        btnMedicoStatus.setIcon(FileLoader.getImageIcon("ok32"));
                    }

                    btnMedicoStatus.setEnabled(true);
                    btnMedicoEdit.setEnabled(true);
                }
            }
        });

        tableMedicos.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                boolean status = tableMedicos.getValueAt(row, 5 ) == "Activo" ? true : false;
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    c.setBackground(status ? new Color(0, 128, 0, 255) :  new Color(128, 0, 0, 255));
                } else {
                    c.setBackground(status ? new Color(0, 128, 0, 80) :  new Color(128, 0, 0, 80));
                }
                return c;
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
}
