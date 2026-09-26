package br.edu.cs.poo.ac.seguro.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoEmpresa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoEmpresaMediator;

public class TelaSeguradoEmpresa extends JFrame implements ActionListener {

    private SeguradoEmpresaMediator mediator = SeguradoEmpresaMediator.getInstancia();

    private JTextField txtCnpj;
    private JTextField txtNome;
    private JTextField txtDataAbertura;
    private JTextField txtFaturamento;
    private JCheckBox chkLocadora;
    private JTextField txtLogradouro;
    private JTextField txtCep;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtPais;
    private JTextField txtEstado;
    private JTextField txtCidade;

    private JButton btnIncluir;
    private JButton btnAlterar;
    private JButton btnExcluir;
    private JButton btnBuscar;
    private JButton btnLimpar;

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelaSeguradoEmpresa() {
        super("Cadastro de Segurado Empresa");
        montarTela();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 520);
        setLocationRelativeTo(null);
    }

    private void montarTela() {
        JPanel painelDados = new JPanel(new GridLayout(0, 2, 5, 5));

        painelDados.add(new JLabel("CNPJ:"));
        txtCnpj = new JTextField();
        painelDados.add(txtCnpj);

        painelDados.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelDados.add(txtNome);

        painelDados.add(new JLabel("Data Abertura (dd/mm/aaaa):"));
        txtDataAbertura = new JTextField();
        painelDados.add(txtDataAbertura);

        painelDados.add(new JLabel("Faturamento:"));
        txtFaturamento = new JTextField();
        painelDados.add(txtFaturamento);

        painelDados.add(new JLabel("É locadora de veículos?"));
        chkLocadora = new JCheckBox();
        painelDados.add(chkLocadora);

        painelDados.add(new JLabel("Logradouro:"));
        txtLogradouro = new JTextField();
        painelDados.add(txtLogradouro);

        painelDados.add(new JLabel("CEP:"));
        txtCep = new JTextField();
        painelDados.add(txtCep);

        painelDados.add(new JLabel("Número:"));
        txtNumero = new JTextField();
        painelDados.add(txtNumero);

        painelDados.add(new JLabel("Complemento:"));
        txtComplemento = new JTextField();
        painelDados.add(txtComplemento);

        painelDados.add(new JLabel("País:"));
        txtPais = new JTextField();
        painelDados.add(txtPais);

        painelDados.add(new JLabel("Estado (UF):"));
        txtEstado = new JTextField();
        painelDados.add(txtEstado);

        painelDados.add(new JLabel("Cidade:"));
        txtCidade = new JTextField();
        painelDados.add(txtCidade);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        btnIncluir = new JButton("Incluir");
        btnAlterar = new JButton("Alterar");
        btnExcluir = new JButton("Excluir");
        btnBuscar = new JButton("Buscar");
        btnLimpar = new JButton("Limpar");

        btnIncluir.addActionListener(this);
        btnAlterar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnLimpar.addActionListener(this);

        painelBotoes.add(btnIncluir);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnBuscar);
        painelBotoes.add(btnLimpar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(painelDados, BorderLayout.CENTER);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);
    }

    private SeguradoEmpresa montarSeguradoEmpresa() {
        Endereco endereco = new Endereco(
                txtLogradouro.getText(),
                txtCep.getText(),
                txtNumero.getText(),
                txtComplemento.getText(),
                txtPais.getText(),
                txtEstado.getText(),
                txtCidade.getText());

        LocalDate dataAbertura;
        try {
            dataAbertura = LocalDate.parse(txtDataAbertura.getText(), FORMATO_DATA);
        } catch (Exception e) {
            dataAbertura = null;
        }

        double faturamento;
        try {
            faturamento = Double.parseDouble(txtFaturamento.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            faturamento = -1;
        }

        return new SeguradoEmpresa(txtNome.getText(), endereco, dataAbertura,
                BigDecimal.ZERO, txtCnpj.getText(), faturamento, chkLocadora.isSelected());
    }

    private void preencherCampos(SeguradoEmpresa seg) {
        txtNome.setText(seg.getNome());
        txtDataAbertura.setText(seg.getDataAbertura().format(FORMATO_DATA));
        txtFaturamento.setText(String.valueOf(seg.getFaturamento()));
        chkLocadora.setSelected(seg.isEhLocadoraDeVeiculos());
        txtLogradouro.setText(seg.getEndereco().getLogradouro());
        txtCep.setText(seg.getEndereco().getCep());
        txtNumero.setText(seg.getEndereco().getNumero());
        txtComplemento.setText(seg.getEndereco().getComplemento());
        txtPais.setText(seg.getEndereco().getPais());
        txtEstado.setText(seg.getEndereco().getEstado());
        txtCidade.setText(seg.getEndereco().getCidade());
    }

    private void limparCampos() {
        txtCnpj.setText("");
        txtNome.setText("");
        txtDataAbertura.setText("");
        txtFaturamento.setText("");
        chkLocadora.setSelected(false);
        txtLogradouro.setText("");
        txtCep.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtPais.setText("");
        txtEstado.setText("");
        txtCidade.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object origem = e.getSource();
        String resultado;

        if (origem == btnIncluir) {
            resultado = mediator.incluirSeguradoEmpresa(montarSeguradoEmpresa());
            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Segurado incluído com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, resultado, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (origem == btnAlterar) {
            resultado = mediator.alterarSeguradoEmpresa(montarSeguradoEmpresa());
            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Segurado alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, resultado, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (origem == btnExcluir) {
            resultado = mediator.excluirSeguradoEmpresa(txtCnpj.getText());
            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Segurado excluído com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, resultado, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (origem == btnBuscar) {
            SeguradoEmpresa seg = mediator.buscarSeguradoEmpresa(txtCnpj.getText());
            if (seg != null) {
                preencherCampos(seg);
            } else {
                JOptionPane.showMessageDialog(this, "Segurado não encontrado", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else if (origem == btnLimpar) {
            limparCampos();
        }
    }

    public static void main(String[] args) {
        TelaSeguradoEmpresa tela = new TelaSeguradoEmpresa();
        tela.setVisible(true);
    }
}