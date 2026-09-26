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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.cs.poo.ac.seguro.entidades.Endereco;
import br.edu.cs.poo.ac.seguro.entidades.SeguradoPessoa;
import br.edu.cs.poo.ac.seguro.mediators.SeguradoPessoaMediator;

public class TelaSeguradoPessoa extends JFrame implements ActionListener {

    private SeguradoPessoaMediator mediator = SeguradoPessoaMediator.getInstancia();

    private JTextField txtCpf;
    private JTextField txtNome;
    private JTextField txtDataNascimento;
    private JTextField txtRenda;
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

    public TelaSeguradoPessoa() {
        super("Cadastro de Segurado Pessoa");
        montarTela();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);
    }

    private void montarTela() {
        JPanel painelDados = new JPanel(new GridLayout(0, 2, 5, 5));

        painelDados.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        painelDados.add(txtCpf);

        painelDados.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painelDados.add(txtNome);

        painelDados.add(new JLabel("Data Nascimento (dd/mm/aaaa):"));
        txtDataNascimento = new JTextField();
        painelDados.add(txtDataNascimento);

        painelDados.add(new JLabel("Renda:"));
        txtRenda = new JTextField();
        painelDados.add(txtRenda);

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

    private SeguradoPessoa montarSeguradoPessoa() {
        Endereco endereco = new Endereco(
                txtLogradouro.getText(),
                txtCep.getText(),
                txtNumero.getText(),
                txtComplemento.getText(),
                txtPais.getText(),
                txtEstado.getText(),
                txtCidade.getText());

        LocalDate dataNascimento;
        try {
            dataNascimento = LocalDate.parse(txtDataNascimento.getText(), FORMATO_DATA);
        } catch (Exception e) {
            dataNascimento = null;
        }

        double renda;
        try {
            renda = Double.parseDouble(txtRenda.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            renda = -1;
        }

        return new SeguradoPessoa(txtNome.getText(), endereco, dataNascimento,
                BigDecimal.ZERO, txtCpf.getText(), renda);
    }

    private void preencherCampos(SeguradoPessoa seg) {
        txtNome.setText(seg.getNome());
        txtDataNascimento.setText(seg.getDataNascimento().format(FORMATO_DATA));
        txtRenda.setText(String.valueOf(seg.getRenda()));
        txtLogradouro.setText(seg.getEndereco().getLogradouro());
        txtCep.setText(seg.getEndereco().getCep());
        txtNumero.setText(seg.getEndereco().getNumero());
        txtComplemento.setText(seg.getEndereco().getComplemento());
        txtPais.setText(seg.getEndereco().getPais());
        txtEstado.setText(seg.getEndereco().getEstado());
        txtCidade.setText(seg.getEndereco().getCidade());
    }

    private void limparCampos() {
        txtCpf.setText("");
        txtNome.setText("");
        txtDataNascimento.setText("");
        txtRenda.setText("");
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
            resultado = mediator.incluirSeguradoPessoa(montarSeguradoPessoa());
            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Segurado incluído com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, resultado, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (origem == btnAlterar) {
            resultado = mediator.alterarSeguradoPessoa(montarSeguradoPessoa());
            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Segurado alterado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, resultado, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (origem == btnExcluir) {
            resultado = mediator.excluirSeguradoPessoa(txtCpf.getText());
            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Segurado excluído com sucesso!");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, resultado, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (origem == btnBuscar) {
            SeguradoPessoa seg = mediator.buscarSeguradoPessoa(txtCpf.getText());
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
        TelaSeguradoPessoa tela = new TelaSeguradoPessoa();
        tela.setVisible(true);
    }
}