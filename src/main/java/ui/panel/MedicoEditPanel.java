package ui.panel;

import entidades.Medico;
import service.MedicoDataService;
import ui.style.BasicStyle;
import ui.window.MainWindow;
import utils.JTextFloatFilter;
import utils.JTextIntegerFilter;
import utils.JTextUppercaseFilter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MedicoEditPanel {

    private JPanel panel;
    private Medico seletectedMedico;
    private JButton btnBack;

    /*UI*/
    private JLabel lblApellido;
    private JLabel lblNombre;
    private JLabel lblDNI;
    private JLabel lblPrecio;

    private JTextField txtApellido;
    private JTextField txtNombre;
    private JTextField txtDNI;
    private JTextField txtPrecio;

    private JButton btnGuardar;

    public MedicoEditPanel(int idMedico) {
        seletectedMedico = MedicoDataService.getMedicoByID(idMedico);

        initUI();

        btnBack = BasicStyle.getBackBtn("Volver a menú");
        btnBack.addActionListener((e -> {
            MedicoPanel medicoPanel = new MedicoPanel();
            MainWindow.changePage(medicoPanel.getPanel());
        }));

        panel = BasicPanel.createBasicPanel();
        panel.add(createPage());
    }

    private void initUI() {
        btnBack = BasicStyle.getBackBtn("Volver a menú");
        btnBack.addActionListener((e -> {
            MedicoPanel medicoPanel = new MedicoPanel();
            MainWindow.changePage(medicoPanel.getPanel());
        }));

        btnGuardar = BasicStyle.getTableBtn("Guardar cambios");
        btnGuardar.addActionListener((e -> {
            boolean control = validateForm();
            if (control) {
                Medico medico = getUpdatedMedico();
                boolean controlGuardado = MedicoDataService.updateMedico(medico);
                MedicoPanel medicoPanel = new MedicoPanel();
                MainWindow.changePage(medicoPanel.getPanel());
            } else {
                System.out.println(String.format("Not valid: %s", control));
            }
        }));

        lblApellido = getFormLbl("Apellido:");
        lblNombre = getFormLbl("Nombre:");
        lblDNI = getFormLbl("DNI:");
        lblPrecio = getFormLbl("Precio consulta:");

        txtApellido = getFormStringTextField(seletectedMedico.getApellido());
        txtNombre = getFormStringTextField(seletectedMedico.getNombre());
        txtDNI = getFormNumberTextField(seletectedMedico.getDni().toString());
        txtPrecio = getFormFloatTextField(BasicStyle.floatFormatted(seletectedMedico.getPrecio_consulta()));
    }

    private Medico getUpdatedMedico() {
        String apellido = txtApellido.getText();
        String nombre = txtNombre.getText();
        String dni = txtDNI.getText();
        String precio = txtPrecio.getText();

        precio = precio.replace(".","").replace(',','.');

        Medico medico = new Medico(seletectedMedico);
        medico.setApellido(apellido);
        medico.setNombre(nombre);
        medico.setDni(Integer.valueOf(dni));
        medico.setPrecio_consulta(Float.valueOf(precio));
        return medico;
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

        JLabel lblTitulo = BasicStyle.getHeaderTitle("Editar medico");
        contentPane.add(lblTitulo, BorderLayout.CENTER);

        contentPane.add(btnBack, BorderLayout.WEST);
        return contentPane;
    }

    private JPanel createBodyPanel() {
        JPanel contentPane = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 5, 0);
        contentPane.setLayout(layout);
        contentPane.setPreferredSize(new Dimension(800, 490));

        JLabel space = getFormLbl("");
        space.setPreferredSize(new Dimension(800, 100));
        contentPane.add(space);
        contentPane.add(lblApellido);
        contentPane.add(txtApellido);
        contentPane.add(lblNombre);
        contentPane.add(txtNombre);
        contentPane.add(lblDNI);
        contentPane.add(txtDNI);
        contentPane.add(lblPrecio);
        contentPane.add(txtPrecio);
        contentPane.add(btnGuardar);
        return contentPane;
    }

    private JTextField getFormStringTextField(final String title) {
        JTextField textField = new JTextField(title);
        AbstractDocument firstNameDoc = (AbstractDocument) textField.getDocument();
        firstNameDoc.setDocumentFilter(new JTextUppercaseFilter());
        textField.setPreferredSize(new Dimension(400, 50));
        return textField;
    }

    private JTextField getFormNumberTextField(final String title) {
        JTextField textField = new JTextField(title);
        PlainDocument doc = (PlainDocument) textField.getDocument();
        doc.setDocumentFilter(new JTextIntegerFilter());
        textField.setPreferredSize(new Dimension(400, 50));
        return textField;
    }

    private JTextField getFormFloatTextField(final String title) {
        JTextField field = new JTextField();
        field.setDocument(new JTextFloatFilter(JTextFloatFilter.FLOAT));
        field.setPreferredSize(new Dimension(400, 50));
        field.setText(title);
        return field;
    }

    private JLabel getFormLbl(final String title) {
        JLabel label = new JLabel(title);
        label.setFont(BasicStyle.getCustomFont(20));
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(200, 50));
        return label;
    }

    public JPanel getPanel() {
        return panel;
    }

    private boolean validateForm() {
        String apellido = txtApellido.getText();
        String nombre = txtNombre.getText();
        String dni = txtDNI.getText();
        String precio = txtPrecio.getText();
        precio = precio.replace(".","").replace(',','.');
        float precioFloat;

        try {
            precioFloat = Float.valueOf(precio);
        } catch (Exception e) {
            precioFloat = seletectedMedico.getPrecio_consulta();
            txtPrecio.setText(BasicStyle.floatFormatted(precioFloat));
            return false;
        }

        if ( apellido.isEmpty() || nombre.isEmpty() || dni.isEmpty() || precio.isEmpty() ) {
            System.out.println("NO PUEDE HABER CAMPOS VACIOS");
            return false;
        }

        boolean controlApellido = seletectedMedico.getApellido().equalsIgnoreCase(apellido);
        boolean controlNombre = seletectedMedico.getNombre().equalsIgnoreCase(nombre);
        boolean controlDNI = seletectedMedico.getDni().compareTo(Integer.valueOf(dni)) == 0 ? true : false;
        boolean controlPrecio = seletectedMedico.getPrecio_consulta().compareTo(precioFloat) == 0 ? true : false;

        if ( !controlApellido || !controlNombre || !controlDNI || !controlPrecio ) {
            return true;
        }

        return false;
    }
}
