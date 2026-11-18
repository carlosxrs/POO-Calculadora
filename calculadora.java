package Calculadora;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class calculadora {

	private JFrame janelaPrincipal;
	private JTextField txtVisor;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculadora calc = new calculadora();
					calc.janelaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public calculadora() {
		montarTela();
	}

	double primeiroValor, segundoValor;          
	String operacaoAtual = "";                 
	boolean limparTela = true;      

	private void inserirDigito(String digito) {
		if (limparTela || txtVisor.getText().equals("0")) {
			txtVisor.setText(digito);
			limparTela = false;
		} else {
			txtVisor.setText(txtVisor.getText() + digito);
		}
	}

	private void adicionarVirgula() {
		if (limparTela) {
			txtVisor.setText("0,");
			limparTela = false;
		} else if (!txtVisor.getText().contains(",")) {
			txtVisor.setText(txtVisor.getText() + ",");
		}
	}

	private double lerTela() {
		return Double.parseDouble(txtVisor.getText().replace(",", "."));
	}

	private void exibirResultado(double res) {
		if (res == Math.rint(res) && Math.abs(res) < 1e15) {
			txtVisor.setText(String.valueOf((long) res));
		} else {
			txtVisor.setText(String.valueOf(res).replace(".", ","));
		}
	}


	public double calcularAdicao(double x, double y) {
		return x + y;
	}

	public double calcularSubtracao(double x, double y) {
		return x - y;
	}

	public double calcularMultiplicacao(double x, double y) {
		return x * y;
	}

	public double calcularDivisao(double x, double y) {
		return x / y;
	}

	public double calcularPotencia(double x, double y) {
		return Math.pow(x, y);
	}

	private void dispararErro() {
		txtVisor.setText("Erro");
		operacaoAtual = "";
		limparTela = true;
	}

	private boolean executarCalculo() {
		segundoValor = lerTela();

		if (operacaoAtual.equals("somar")) {
			primeiroValor = calcularAdicao(primeiroValor, segundoValor);
		} else if (operacaoAtual.equals("subtrair")) {
			primeiroValor = calcularSubtracao(primeiroValor, segundoValor);
		} else if (operacaoAtual.equals("multiplicar")) {
			primeiroValor = calcularMultiplicacao(primeiroValor, segundoValor);
		} else if (operacaoAtual.equals("dividir")) {
			if (segundoValor == 0) {      
				dispararErro();
				return false;
			}
			primeiroValor = calcularDivisao(primeiroValor, segundoValor);
		} else if (operacaoAtual.equals("potenciar")) {
			primeiroValor = calcularPotencia(primeiroValor, segundoValor);
		}

		if (Double.isNaN(primeiroValor) || Double.isInfinite(primeiroValor)) {
			dispararErro();
			return false;
		}

		exibirResultado(primeiroValor);
		return true;
	}

	private void selecionarOperacao(String novaOp) {
		if (txtVisor.getText().equals("Erro")) {
			return;
		}
		if (!operacaoAtual.isEmpty() && !limparTela) {
			if (!executarCalculo()) {
				return;
			}
		} else {
			primeiroValor = lerTela();
		}
		operacaoAtual = novaOp;
		limparTela = true;
	}

	private void acionarIgual() {
		if (operacaoAtual.isEmpty() || txtVisor.getText().equals("Erro")) {
			return;
		}
		if (executarCalculo()) {
			operacaoAtual = "";
		}
		limparTela = true;
	}

	private void zerarTudo() {
		txtVisor.setText("0");
		primeiroValor = 0;
		segundoValor = 0;
		operacaoAtual = "";
		limparTela = true;
	}

	private void montarTela() {
		janelaPrincipal = new JFrame();
		janelaPrincipal.setTitle("Carlos Eduardo Rodrigues de Sousa - RA: 0024303");
		
		//(04/06/2007)(10, 27, 207)
		janelaPrincipal.getContentPane().setBackground(new Color(10, 27, 207));
		janelaPrincipal.getContentPane().setLayout(null);

		txtVisor = new JTextField();
		txtVisor.setText("0");
		txtVisor.setEditable(false);
		txtVisor.setHorizontalAlignment(SwingConstants.RIGHT);
		txtVisor.setFont(new Font("Arial Black", Font.PLAIN, 50));
		txtVisor.setBounds(10, 30, 439, 73);
		janelaPrincipal.getContentPane().add(txtVisor);
		txtVisor.setColumns(10);

		JButton b0 = new JButton("0");
		b0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("0");
			}
		});
		b0.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b0.setBounds(104, 427, 72, 64);
		janelaPrincipal.getContentPane().add(b0);

		JButton b1 = new JButton("1");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("1");
			}
		});
		b1.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b1.setBounds(10, 341, 72, 64);
		janelaPrincipal.getContentPane().add(b1);

		JButton b2 = new JButton("2");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("2");
			}
		});
		b2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b2.setBounds(104, 341, 72, 64);
		janelaPrincipal.getContentPane().add(b2);

		JButton b3 = new JButton("3");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("3");
			}
		});
		b3.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b3.setBounds(201, 341, 72, 64);
		janelaPrincipal.getContentPane().add(b3);

		JButton b8 = new JButton("8");
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("8");
			}
		});
		b8.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b8.setBounds(104, 193, 72, 64);
		janelaPrincipal.getContentPane().add(b8);

		JButton b7 = new JButton("7");
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("7");
			}
		});
		b7.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b7.setBounds(10, 193, 72, 64);
		janelaPrincipal.getContentPane().add(b7);

		JButton b9 = new JButton("9");
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("9");
			}
		});
		b9.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b9.setBounds(201, 193, 72, 64);
		janelaPrincipal.getContentPane().add(b9);

		JButton b5 = new JButton("5");
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("5");
			}
		});
		b5.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b5.setBounds(104, 268, 72, 64);
		janelaPrincipal.getContentPane().add(b5);

		JButton b4 = new JButton("4");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("4");
			}
		});
		b4.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b4.setBounds(10, 268, 72, 64);
		janelaPrincipal.getContentPane().add(b4);

		JButton b6 = new JButton("6");
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inserirDigito("6");
			}
		});
		b6.setFont(new Font("Tahoma", Font.PLAIN, 50));
		b6.setBounds(201, 268, 72, 64);
		janelaPrincipal.getContentPane().add(b6);

		JButton bLimpar = new JButton("c");
		bLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zerarTudo();
			}
		});
		bLimpar.setFont(new Font("Tahoma", Font.PLAIN, 50));
		bLimpar.setBounds(10, 114, 72, 64);
		janelaPrincipal.getContentPane().add(bLimpar);

		JButton bSoma = new JButton("+");
		bSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarOperacao("somar");
			}
		});
		bSoma.setFont(new Font("Tahoma", Font.PLAIN, 50));
		bSoma.setBounds(364, 341, 72, 64);
		janelaPrincipal.getContentPane().add(bSoma);

		JButton bMult = new JButton("*");
		bMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarOperacao("multiplicar");
			}
		});
		bMult.setFont(new Font("Tahoma", Font.PLAIN, 50));
		bMult.setBounds(364, 193, 72, 64);
		janelaPrincipal.getContentPane().add(bMult);

		JButton bDiv = new JButton("/");
		bDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarOperacao("dividir");
			}
		});
		bDiv.setFont(new Font("Tahoma", Font.PLAIN, 50));
		bDiv.setBounds(364, 114, 72, 64);
		janelaPrincipal.getContentPane().add(bDiv);

		JButton bPot = new JButton("^");
		bPot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarOperacao("potenciar");
			}
		});
		bPot.setFont(new Font("Tahoma", Font.PLAIN, 50));
		bPot.setBounds(272, 114, 72, 64);
		janelaPrincipal.getContentPane().add(bPot);

		JButton bSub = new JButton("-");
		bSub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarOperacao("subtrair");
			}
		});
		bSub.setFont(new Font("Tahoma", Font.PLAIN, 50));
		bSub.setBounds(364, 268, 72, 64);
		janelaPrincipal.getContentPane().add(bSub);

		JButton bIgual = new JButton("=");
		bIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acionarIgual();
			}
		});
		bIgual.setFont(new Font("Tahoma", Font.PLAIN, 50));
		bIgual.setBounds(364, 437, 72, 64);
		janelaPrincipal.getContentPane().add(bIgual);

		JButton bVirg = new JButton(",");
		bVirg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarVirgula();
			}
		});
		bVirg.setFont(new Font("Tahoma", Font.PLAIN, 50));
		bVirg.setBounds(272, 437, 72, 64);
		janelaPrincipal.getContentPane().add(bVirg);
		
		janelaPrincipal.setBounds(100, 100, 475, 565);
	    janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
